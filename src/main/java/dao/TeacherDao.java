package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Teacher;

public class TeacherDao extends DAO {
	public Teacher login(String id, String password) throws Exception {
		Teacher teacher = null;
		
		Connection con = getConnection();
		SchoolDao schoolDao = new SchoolDao();
		
		PreparedStatement st;
		st = con.prepareStatement("SELECT * FROM teacher WHERE id=? AND password=?");
		st.setString(1, id);
		st.setString(2, password);
		ResultSet rs = st.executeQuery();
		
		if (rs.next()) {
	        teacher = new Teacher();
	        teacher.setId(rs.getString("id"));
	        teacher.setPassword(rs.getString("password"));
	        teacher.setName(rs.getString("name"));
	        teacher.setSchool(schoolDao.get(rs.getString("school_cd")));
	    }
		
		st.close();
		con.close();
		return teacher;
	}
	
	public Teacher get(String id) throws Exception {
		Teacher teacher = new Teacher();
		SchoolDao sDao = new SchoolDao();
		
		Connection con = getConnection();
		
		PreparedStatement st;
		st = con.prepareStatement("SELECT * FROM teacher WHERE id = ?");
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		
		teacher.setId(rs.getString("id"));
		teacher.setName(rs.getString("name"));
		teacher.setPassword(rs.getString("password"));
		teacher.setSchool(sDao.get(rs.getString("school_cd")));
		
		return teacher;
	}
	
	public List<Teacher> getAll() throws Exception {
		tool.Logger.dao("teacher filter");
		
		Teacher t = null;
		List<Teacher> list = new ArrayList<>();
		
		Connection con = getConnection();
		PreparedStatement st;
		
		st = con.prepareStatement("SELECT * FROM teacher");
		ResultSet rs = st.executeQuery();
		
		SchoolDao dao = new SchoolDao();
		
		while (rs.next()) {
			t = new Teacher();
			t.setId(rs.getString("id"));
			t.setName(rs.getString("name"));
			t.setSchool(dao.get(rs.getString("school_cd")));
			list.add(t);
		}
		
		st.close();
		con.close();
		return list;
	}
	
	public boolean update(Teacher teacher) throws Exception {
		tool.Logger.dao("teacher update");
		
		Connection con = getConnection();
		PreparedStatement st;
		
		st = con.prepareStatement("UPDATE teacher SET name=?, school_cd=? WHERE id=?");
		st.setString(1, teacher.getName());
		st.setString(2, teacher.getSchool().getCd());
		st.setString(3, teacher.getId());
		
		int result = st.executeUpdate();

        st.close();
        con.close();

        return result == 1;
	}
	
	public boolean delete(String id) throws Exception {
		tool.Logger.dao("teacher delete");
		
		Connection con = getConnection();
		PreparedStatement st;
		
		st = con.prepareStatement("DELETE teacher WHERE id = ?");
		st.setString(1, id);
		
		int result = st.executeUpdate();
		
		st.close();
		con.close();
		
		return result == 1;
	}
	
	public boolean create(Teacher teacher) throws Exception {
		tool.Logger.dao("teacher create");
		
		Connection con = getConnection();
		PreparedStatement st;
		
		st = con.prepareStatement("INSERT INTO teacher VALUES(?,?,?,?)");
		st.setString(1, teacher.getId());
		st.setString(2, teacher.getPassword());
		st.setString(3, teacher.getName());
		st.setString(4, teacher.getSchool().getCd());
		
		int result = st.executeUpdate();
		
		st.close();
		con.close();
		
		return result == 1;
	}
	
	public boolean isUnique(String id) throws Exception {
		tool.Logger.dao("teacher isUnique");
		
		Connection con = getConnection();
		PreparedStatement st = null;
		
		st = con.prepareStatement("SELECT TRUE FROM teacher WHERE id = ?");	
		st.setString(1, id);
		
		ResultSet rs = st.executeQuery();
		
		boolean result = false;
		
		if (!rs.next()) {
			result = true;
		}
		
		st.close();
		con.close();
		
		return result;
	}
}
