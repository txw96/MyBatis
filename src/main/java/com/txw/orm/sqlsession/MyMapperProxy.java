package com.txw.orm.sqlsession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import com.txw.orm.config.Function;
import com.txw.orm.config.MapperBean;

/**<pre>
 ************************************************************************************* 
 * [标题：类]
 ************************************************************************************* 
 * [描述：]
 ************************************************************************************* 
 * @auth txw Team / 亿美团队 {18373282867@139.com}
 *************************************************************************************
 * @date 2018年3月25日 上午11:29:37
 *************************************************************************************
 * @version Version 1.0.0 Beta / v1.0.0 测试版
 ************************************************************************************* 
 * <pre>
 */
public class MyMapperProxy implements InvocationHandler {

	private MySqlsession sqlsession;
	private MyConfiguration configuration;

	public MyMapperProxy(MyConfiguration configuration, MySqlsession sqlsession) {
		this.sqlsession = sqlsession;
		this.configuration = configuration;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] objs) throws Throwable {
		MapperBean mapper = configuration.readMapper("UserMapper.xml");
		String funName = method.getDeclaringClass().getName();
		if (funName == null || !funName.equals(mapper.getInterfaceName())) {
			return null;
		}
		List<Function> functions = mapper.getFunctions();
		if (functions == null || functions.size() <= 0) {
			return null;
		}
		for (Function fun : functions) {
			if (method.getName().equals(fun.getFuncName())) {
				return sqlsession.selectOne(fun.getSql(), String.valueOf(objs[0]));
			}
		}
		return null;
	}

}
