package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.School;

public class SchoolDao extends DAO {
	public School get(String cd) throws Exception {
		School school = new School();
		
		Connection con = getConnection();
		
		PreparedStatement st;
		st = con.prepareStatement("SELECT * FROM school WHERE cd = ?");
		st.setString(1, cd);
		ResultSet rs = st.executeQuery();
		
		if (rs.next()) {
			school.setCd(rs.getString("cd"));
			school.setName(rs.getString("name"));
		}
			
		return school;
	}
}