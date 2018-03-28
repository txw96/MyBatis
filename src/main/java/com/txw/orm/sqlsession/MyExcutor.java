package com.txw.orm.sqlsession;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.txw.orm.bean.User;

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
public class MyExcutor implements Excutor {

	private MyConfiguration configuration = new MyConfiguration();

	@SuppressWarnings("unchecked")
	@Override
	public <T> T query(String sql, Object obj) {
		Connection connection = getConnection();
		ResultSet set = null;
		PreparedStatement pre = null;
		try {
			pre = connection.prepareStatement(sql);
			// 设置参数
			pre.setString(1, obj.toString());
			set = pre.executeQuery();
			User user = new User();
			while (set.next()) {
				user.setId(set.getLong(1));
				user.setName(set.getString(2));
				user.setPassword(set.getString(3));
			}
			return (T) user;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (set != null) {
					set.close();
				} else if (pre != null) {
					pre.close();
				} else if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private Connection getConnection() {
		Connection connection = configuration.build("config.xml");
		return connection;
	}
}
