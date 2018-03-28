package com.txw.orm.sqlsession;

import java.io.InputStream;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mysql.jdbc.Connection;
import com.txw.orm.config.Function;
import com.txw.orm.config.MapperBean;

/**<pre>
 ************************************************************************************* 
 * [标题:类]
 ************************************************************************************* 
 * [描述:] 读取与解析配置信息，并返回处理后的Environment
 ************************************************************************************* 
 * @auth txw Team / 亿美团队 {18373282867@139.com}
 *************************************************************************************
 * @date 2018年3月25日 上午11:29:37
 *************************************************************************************
 * @version Version 1.0.0 Beta / v1.0.0 测试版
 ************************************************************************************* 
 * <pre>
 */

public class MyConfiguration {

	private static ClassLoader loader = ClassLoader.getSystemClassLoader();

	public Connection build(String resource) {
		try {
			Element element = getElement(resource);
			return evalDataSource(element);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private Connection evalDataSource(Element node) {
		String database = node.getName();
		if (node == null || !"database".equals(database)) {
			throw new RuntimeException("root should be <database>");
		}
		String driverClassName = null;
		String url = null;
		String username = null;
		String password = null;
		// 获取属性节点
		for (Object item : node.elements("properety")) {
			Element i = (Element) item;
			String val = getValue(i);
			String name = i.attributeValue("name");
			if (null == name || val == null) {
				throw new RuntimeException("[database] <property> should contain name and value");
			}
			// 赋值
			switch (name) {
			case "url":
				url = val;
				break;
			case "username":
				username = val;
				break;
			case "password":
				password = val;
				break;
			case "driverClassName":
				driverClassName = val;
				break;
			default:
				throw new RuntimeException("[database] <property> unknown name");
			}
		}
		Connection connection = null;
		try {
			Class.forName(driverClassName);
			// 建立数据库连接
			connection = (Connection) DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			throw new RuntimeException("建立数据库连接失败");
		}
		return connection;
	}

	private String getValue(Element node) {
		return node.hasContent() ? node.getText() : node.attributeValue("value");
	}

	@SuppressWarnings("unchecked")
	public MapperBean readMapper(String path) {
		MapperBean mapper = new MapperBean();
		Element element = getElement(path);
		mapper.setInterfaceName(element.attributeValue("nameSpace").trim());
		List<Function> functions = new ArrayList<>();
		for (Iterator<Element> iterator = element.elementIterator(); iterator.hasNext();) {
			Function fun = new Function();
			Element ele = iterator.next();
			String sqlType = ele.getName().trim();
			String funcName = ele.attributeValue("id").trim();
			String sql = ele.getText().trim();
			String resultType = ele.attributeValue("resultType").trim();
			fun.setSqlType(sqlType);
			fun.setFuncName(funcName);
			Object newInstance = null;
			try {
				newInstance = Class.forName(resultType).getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				throw new RuntimeException("类 newInstance 失败" + resultType);
			}
			fun.setSql(sql);
			fun.setResultType(newInstance);
			functions.add(fun);
		}
		mapper.setFunctions(functions);
		return mapper;
	}

	private Element getElement(String path) {
		InputStream stream = loader.getResourceAsStream(path);
		SAXReader reader = new SAXReader();
		Element element = null;
		try {
			Document doc = reader.read(stream);
			element = doc.getRootElement();
		} catch (DocumentException e) {
			throw new RuntimeException("getElement失败,path=" + path);
		}
		return element;
	}

}
