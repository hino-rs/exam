package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
		
		con.close();
		st.close();
			
		return school;
	}
	
	public List<String> getAllSchoolCd() throws Exception {
		List<String> list = new ArrayList<>();
		
		Connection con = getConnection();
		PreparedStatement st;
		
		st = con.prepareStatement("SELECT cd FROM school");
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			list.add(rs.getString("cd"));
		}
		
		con.close();
		st.close();
		
		return list;
	}
}