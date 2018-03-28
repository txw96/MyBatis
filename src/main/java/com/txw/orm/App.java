package com.txw.orm;

import com.txw.orm.bean.User;
import com.txw.orm.mapper.UserMapper;
import com.txw.orm.sqlsession.MySqlsession;

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
public class App {

	public static void main(String[] args) {
		MySqlsession sqlsession = new MySqlsession();
		UserMapper mapper = sqlsession.getMapper(UserMapper.class);
		User user = mapper.getById(1L);
		System.out.println(user.getName());
	}
}
