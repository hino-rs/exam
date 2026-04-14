package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;
import bean.Teacher;

public class TeacherDao extends DAO {
	public Teacher login(String id, String password) throws Exception {
		Teacher teacher = null;
		
		Connection con = getConnection();
		
		PreparedStatement st;
		st = con.prepareStatement("SELECT * FROM teacher WHERE id=? AND password=?");
		st.setString(1, password);
		st.setString(2, password);
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			teacher = new Teacher();
			teacher.setId(rs.getString("id"));
			teacher.setName(rs.getString("name"));
			teacher.setPassword(rs.getString("password"));
			teacher.setSchool(new School());
		}
		
		st.close();
		con.close();
		return teacher;
	}
}