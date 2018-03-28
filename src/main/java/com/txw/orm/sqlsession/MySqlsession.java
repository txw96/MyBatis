package com.txw.orm.sqlsession;

import java.lang.reflect.Proxy;

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
public class MySqlsession {

	private Excutor excutor = new MyExcutor();
	private MyConfiguration configuration = new MyConfiguration();

	public <T> T selectOne(String statement, Object paramenter) {
		return excutor.query(statement, paramenter);
	}

	@SuppressWarnings("unchecked")
	public <T> T getMapper(Class<T> clazz) {
		T bean = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz },
		        new MyMapperProxy(configuration, this));
		return bean;
	}
}
