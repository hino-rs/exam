package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends DAO {
	public Subject get(String cd) throws Exception {
		tool.Logger.dao("subject get");
		
		Subject s = null;
		
		Connection con = getConnection();
		
		PreparedStatement st;
		st = con.prepareStatement("SELECT * FROM subject WHERE cd=?");
		st.setString(1, cd);
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
	
	public boolean save(Subject subject) throws Exception {
		tool.Logger.dao("subject save");
		
		School school = subject.getSchool();
		String schoolCd = school.getCd();
		String cd = subject.getCd();
		String name = subject.getName();
		
		Connection con = getConnection();
		PreparedStatement st = null;
		int count = 0;
		
		try {
			Subject old = get(cd);
			if (old == null) {
				st = con.prepareStatement(
					"insert into subject(school_cd, cd, name) values(?, ?, ?)"
				);
				st.setString(1, schoolCd);
				st.setString(2, cd);
				st.setString(3, name);
			} else {
				st = con.prepareStatement(
					"UPDATE subject SET name=? where cd=?"
				);
				st.setString(1, name);
				st.setString(2, cd);
			}
			count = st.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isUnique(String cd) throws Exception {
		Connection con = getConnection();
		PreparedStatement st = null;
		
		st = con.prepareStatement("SELECT TRUE FROM subject WHERE cd = ?");	
		st.setString(1, cd);
		
		ResultSet rs = st.executeQuery();
		
		if (rs.next()) {
			return false;
		}
		return true;	
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
