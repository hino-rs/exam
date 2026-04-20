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
		tool.Logger.dao("subject get");
		
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
		tool.Logger.dao("subject filter");
		
		Subject s = null;
		List<Subject> list = new ArrayList<>();
		
		Connection con = getConnection();
		PreparedStatement st;
		
		st = con.prepareStatement("SELECT * FROM subject WHERE school_cd = ?");
		st.setString(1, school.getCd());
		ResultSet rs = st.executeQuery();
		
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
	
	public String save(String cd, String name, String schoolCd) throws Exception {
		tool.Logger.dao("subject save");
		
		Connection con = getConnection();
		PreparedStatement uniqueCheck;
		
		uniqueCheck = con.prepareStatement("SELECT TRUE FROM subject WHERE cd = ?");
		uniqueCheck.setString(1, cd);
		
		ResultSet uniqueCheckResult = uniqueCheck.executeQuery();
		
		if (uniqueCheckResult.next()) {
			if (uniqueCheckResult.getBoolean(1)) {
				tool.Logger.error("subject: ユニーク違反");
				return "DUPLICATE";
			}
		}
		
		uniqueCheck.close();
		
		PreparedStatement st;
		
		st = con.prepareStatement("INSERT INTO subject VALUES(?, ?, ?)");
		st.setString(1, schoolCd);
		st.setString(2, cd);
		st.setString(3, name);
		
		int rs = st.executeUpdate();
		
		String result = "FAILURE";
		
		if (rs > 0) {
			result = "SUCCESS";
		}
		
		st.close();
		con.close();
		return result;
	}
	
	public boolean update(String target, String newName) throws Exception {
		tool.Logger.dao("subject update");
		
		boolean result = false;
		Connection con = getConnection();
		PreparedStatement st;
		
		st = con.prepareStatement("UPDATE subject SET name = ? WHERE cd = ?");
		st.setString(1, newName);
		st.setString(2, target);
		
		int rs = st.executeUpdate();
		
		
		if (rs > 0) {
			result = true;
		}
		
		st.close();
		con.close();
		
		return result;
	}
	
	public boolean delete(Subject subject) throws Exception {
		tool.Logger.dao("subject delete");
		
		boolean result = false;
		Connection con = getConnection();
		PreparedStatement st;
		
		st = con.prepareStatement("DELETE FROM subject WHERE cd = ?");
		st.setString(1, subject.getCd());
		
		int rs = st.executeUpdate();
		if (rs > 0) {
			result = true;
		}
		
		st.close();
		con.close();
		
		return result;
	}
}
