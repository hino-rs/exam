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
				
				s.setNo(rSet.getString("no"));
				s.setName(rSet.getString("name"));
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
		tool.Logger.dao("StudentDao save");
		
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
				tool.Logger.debug("student updateへ");
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
}