package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends DAO {
	private String baseSql = "SELECT * FROM student WHERE school_cd=? ";
	
	private List<Student> postFilter(ResultSet rSet, School school) throws Exception {
		List<Student> list = new ArrayList<>();
		try {
			while (rSet.next()) {
				Student s = new Student();
				
				s.setNo(tool.Sanitaizer.sanitaizing((rSet.getString("no"))));
				s.setName(tool.Sanitaizer.sanitaizing(rSet.getString("name")));
				s.setEntYear(rSet.getInt("ent_year"));
				s.setClassNum(rSet.getString("class_num"));
				s.setAttend(rSet.getBoolean("is_attend"));
				s.setSchool(school);
				
				list.add(s);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 番号から
	public Student get(String no) throws Exception {
		Student s = new Student();
		Connection con = getConnection();
		PreparedStatement st = null;
		
		try {
			st = con.prepareStatement("SELECT * FROM student WHERE no=?");
			st.setString(1, no);
			ResultSet rSet = st.executeQuery();
			
			SchoolDao schoolDao = new SchoolDao();
			
			if (rSet.next()) {
				s.setNo(rSet.getString("no"));
				s.setName(rSet.getString("name"));
				s.setEntYear(rSet.getInt("ent_year"));
				s.setClassNum(rSet.getString("class_num"));
				s.setAttend(rSet.getBoolean("is_attend"));
				s.setSchool(schoolDao.get(rSet.getString("school_cd")));
			} else {
				s = null;
			}
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
		return s;
	}
	
	// 学校, 入学した年, クラス番号, 在席 で絞り込み
	public List<Student> filter(
		School school, 
		int entYear,
		String classNum, 
		boolean isAttend
	) throws Exception {
		List<Student> list=new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement st = null;
		ResultSet rSet = null;
		String condition = "and ent_year=? and class_num=?";
		String order = " order by no asc";
		String conditionIsAttend = "";
		
		if (isAttend) {
			conditionIsAttend = "and is_attend=true";
		}else {
			conditionIsAttend = "and is_attend=false";
		}
		
		try {
			st = con.prepareStatement(baseSql + condition + conditionIsAttend + order);
			st.setString(1, school.getCd());
			st.setInt(2, entYear);
			st.setString(3, classNum);
			
			rSet = st.executeQuery();
			list = postFilter(rSet, school);
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
		
		return list;
	}
	
	// 学校, 入学した年, 在席 で絞り込み
	public List<Student> filter(
		School school, 
		int entYear, 
		boolean isAttend
	) throws Exception {
		List<Student> list=new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement st = null;
		ResultSet rSet = null;
		String condition = "and ent_year=? ";
		String order = " order by no asc";
		String conditionIsAttend = "";
		
		if (isAttend) {
			conditionIsAttend = "and is_attend=true";
		}else {
			conditionIsAttend = "and is_attend=false";
		}
		
		try {
			st = con.prepareStatement(baseSql + condition + conditionIsAttend + order);
			st.setString(1, school.getCd());
			st.setInt(2, entYear);
			
			rSet = st.executeQuery();
			list = postFilter(rSet, school);
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
		
		return list;
	}
	
	//  在席 で絞り込み
	public List<Student> filter(
		School school,
		boolean isAttend
	) throws Exception {
		List<Student> list=new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement st = null;
		ResultSet rSet = null;
		String order = " order by no asc";
		String conditionIsAttend = "";
		
		if (isAttend) {
			conditionIsAttend = "and is_attend=true";
		}else {
			conditionIsAttend = "and is_attend=false";
		}
		
		try {
			st = con.prepareStatement(baseSql + conditionIsAttend + order);
			st.setString(1, school.getCd());
			
			rSet = st.executeQuery();
			list = postFilter(rSet, school);
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
		
		return list;
	}
	
	// 追加・更新
	public boolean save(Student student) throws Exception {
		Connection con = getConnection();		
		PreparedStatement st = null;
		int count = 0;
		
		try {
			Student old = get(student.getNo());
			if (old == null) {
				st = con.prepareStatement(
						"insert into student(no, name, ent_year, class_num, is_attend, school_cd) values(?, ?, ?, ?, ?, ?)");
				st.setString(1, student.getNo());
				st.setString(2, student.getName());
				st.setInt(3, student.getEntYear());
				st.setString(4, student.getClassNum());
				st.setBoolean(5, student.getAttend());
				st.setString(6, student.getSchool().getCd());
			} else {
				st = con.prepareStatement(
					"update student set name=?, ent_year=?, class_num=?, is_attend=? where no=?");
				st.setString(5, student.getNo());
				st.setString(1, student.getName());
				st.setInt(2, student.getEntYear());
				st.setString(3, student.getClassNum());
				st.setBoolean(4, student.getAttend());
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
	
// ---------------------------------------------------------------------------------
//  追加 ： 学校のみで絞り込み（在学中フィルタなし・全件）
// ---------------------------------------------------------------------------------
	public List<Student> filter(School school) throws Exception {
	    List<Student> list = new ArrayList<>();
	    Connection con = getConnection();
	    PreparedStatement st = null;
	    ResultSet rSet = null;
	
	    String sql = baseSql + " order by no asc";
	
	    try {
	        st = con.prepareStatement(sql);
	        st.setString(1, school.getCd());
	
	        rSet = st.executeQuery();
	        list = postFilter(rSet, school);
	
	    } finally {
	        if (st != null) st.close();
	        if (con != null) con.close();
	    }
	
	    return list;
	}
	
// ---------------------------------------------------------------------------------
//  追加 ： 学校 + 入学年度で絞り込み（在学中フィルタなし・全件）
// ---------------------------------------------------------------------------------
	public List<Student> filter(School school, int entYear) throws Exception {
	    List<Student> list = new ArrayList<>();
	    Connection con = getConnection();
	    PreparedStatement st = null;
	    ResultSet rSet = null;
	
	    String sql = baseSql + "and ent_year=? order by no asc";
	
	    try {
	        st = con.prepareStatement(sql);
	        st.setString(1, school.getCd());
	        st.setInt(2, entYear);
	
	        rSet = st.executeQuery();
	        list = postFilter(rSet, school);
	
	    } finally {
	        if (st != null) st.close();
	        if (con != null) con.close();
	    }
	
	    return list;
	}
	
// ---------------------------------------------------------------------------------
//  追加 ： 学校 + 入学年度 + クラスで絞り込み（在学中フィルタなし・全件）
// ---------------------------------------------------------------------------------
	public List<Student> filter(School school, int entYear, String classNum) throws Exception {
	    List<Student> list = new ArrayList<>();
	    Connection con = getConnection();
	    PreparedStatement st = null;
	    ResultSet rSet = null;
	
	    String sql = baseSql + "and ent_year=? and class_num=? order by no asc";
	
	    try {
	        st = con.prepareStatement(sql);
	        st.setString(1, school.getCd());
	        st.setInt(2, entYear);
	        st.setString(3, classNum);
	
	        rSet = st.executeQuery();
	        list = postFilter(rSet, school);
	
	    } finally {
	        if (st != null) st.close();
	        if (con != null) con.close();
	    }
	
	    return list;
	}
	
// ---------------------------------------------------------------------------------
//  追加 ： 1 件の学生情報(Studentオブジェクト)を INSERT または UPDATE する
// ---------------------------------------------------------------------------------
	public void insertOrUpdate(Student st) throws Exception {

		Connection con = getConnection();
		
		//  学生番号（no）をキーとして既存データがあるか確認する
		PreparedStatement ps = con.prepareStatement(
	    		"SELECT no FROM student WHERE no = ?"
	    	);
		ps.setString(1, st.getNo());
		
		ResultSet rs = ps.executeQuery();
		
		Boolean exists = rs.next();
		
		if(exists) {
			PreparedStatement psUpdate = con.prepareStatement(
				"UPDATE student SET "
				+ "name=?, "
				+ "ent_year=?, "
				+ "class_num=?, "
				+ "is_attend=?, "
				+ "school_cd=? "
				+ "WHERE no=?"
			);			
     		psUpdate.setString(1, st.getName());
     		psUpdate.setInt(2, st.getEntYear());
     		psUpdate.setString(3, st.getClassNum());
     		psUpdate.setBoolean(4, st.getAttend());
     		psUpdate.setString(5, st.getSchool().getCd());
     		psUpdate.setString(6, st.getNo());
     		
     		psUpdate.executeUpdate();
     		
		}else {
			PreparedStatement psInsert = con.prepareStatement(
				"INSERT INTO student ("
				+ "no, "
				+ "name, "
				+ "ent_year, "
				+ "class_num, "
				+ "is_attend, "
				+ "school_cd) "
				+ "VALUES(?,?,?,?,?,?)"
			);
			psInsert.setString(1, st.getNo());
			psInsert.setString(2, st.getName());
			psInsert.setInt(3, st.getEntYear());
			psInsert.setString(4, st.getClassNum());
			psInsert.setBoolean(5, st.getAttend());
			psInsert.setString(6, st.getSchool().getCd());
			
			psInsert.executeUpdate();			
		}
		
    	rs.close();
    	ps.close();
    	con.close();	   
	}
	
// ---------------------------------------------------------------------------------
//  追加 ： 学生登録件数のカウント
// ---------------------------------------------------------------------------------
	public int countByClass(String school_cd, String class_num) throws Exception {
	    String sql = "SELECT COUNT(*) FROM student WHERE school_cd = ? AND class_num = ?";
	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, school_cd);
	        ps.setString(2, class_num);

	        ResultSet rs = ps.executeQuery();
	        rs.next();
	        return rs.getInt(1);
	    }
	}

}