package com.txw.orm.sqlsession;

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
public interface Excutor {
	
	public <T> T query(String statement, Object paramenter);
	
}