package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends DAO {
	public Subject get(String cd, String name) throws Exception {
		Subject s = null;
		
		Connection con = getConnection();
		
		PreparedStatement st;
		st = con.prepareStatement("SELECT * FROM subject WHERE cd=? AND name=?");
		st.setString(1, cd);
		st.setString(2, name);
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			s = new Subject();
			s.setCd(rs.getString("cd"));
			s.setName(rs.getString("name"));
			s.setSchool(new School());
		}
		
		st.close();
		con.close();
		return s;
	}
	
	public List<Subject> filter(School school) throws Exception {
		Subject s = null;
		List<Subject> list = new ArrayList<>();
		
		Connection con = getConnection();
		PreparedStatement st;
		
		st = con.prepareStatement("SELECT * FROM subject WHERE school_cd = ?");
		st.setString(1, school.getCd());
		ResultSet rs = st.executeQuery();
		
		System.out.println(school.getCd());
		
		while (rs.next()) {
			s = new Subject();
			s.setCd(rs.getString("cd"));
			s.setName(rs.getString("name"));
			s.setSchool(new School());
			list.add(s);
		}
		
		st.close();
		con.close();
		return list;
	}
}