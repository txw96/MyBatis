package com.txw.orm.config;

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
public class Function {

	private String sqlType;
	private String funcName;
	private String sql;
	private Object resultType;
	private String parameterType;

	public String getSqlType() {
		return sqlType;
	}

	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Object getResultType() {
		return resultType;
	}

	public void setResultType(Object resultType) {
		this.resultType = resultType;
	}

	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

}
