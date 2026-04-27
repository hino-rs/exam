package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends DAO {
	
	private static final String baseSql =
		    "SELECT "
		  + "    s.ent_year, "                     // 入学年度
		  + "    s.no AS student_no, "             // 学生番号
		  + "    s.name AS student_name, "         // 氏名
		  + "    s.class_num AS student_class, "   // 現在のクラス
		  + "    a.no AS no1, "                    // 1回目の回数
		  + "    a.point AS point1, "              // 1回目の点数
		  + "    b.no AS no2, "                    // 2回目の回数
		  + "    b.point AS point2 "               // 2回目の点数
		  + "FROM test a "                         // 1回目のテストを基点にする
		  + "JOIN student s ON a.student_no = s.no "          // 学生テーブルと JOIN
		  + "JOIN subject sub ON a.subject_cd = sub.cd "      // 科目テーブルと JOIN
		  + "JOIN school sch ON a.school_cd = sch.cd "        // 学校テーブルと JOIN
		  + "LEFT JOIN test b ON a.student_no = b.student_no " // 2回目のテストを LEFT JOIN で横並び配置
		  + "    AND a.subject_cd = b.subject_cd "
		  + "    AND a.school_cd = b.school_cd "
		  + "    AND b.no = 2 "                                // 2回目
		  + "WHERE a.no = 1 "                                  // 1回目がある学生だけ表示
		  + "  AND a.subject_cd = ? "                          // 科目
		  + "  AND a.school_cd = ? "                           // 学校
		  + "  AND s.ent_year = ? "                            // 入学年度
		  + "  AND s.class_num = ? ";                          // クラス
	
	
	public List<TestListSubject> filter (
		int entYear,
		String classNum,
		Subject subject,
		String schoolCd
	) throws Exception {
		tool.Logger.dao("TestListSubject filter");

		TestListSubject  t = null;
		List<TestListSubject> list = new ArrayList<>();
		
		Connection con = getConnection();
		PreparedStatement st;
		
		st = con.prepareStatement(baseSql);
		st.setString(1, subject.getCd());
		st.setString(2, schoolCd);
		st.setInt(3, entYear);
		st.setString(4, classNum);
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			t = new TestListSubject();
			Map<Integer, Integer> map = new HashMap<>();
			
			t.setEntYear(rs.getInt("ent_year"));
			t.setStudentNo(rs.getString("student_no"));
			t.setStudentName(rs.getString("student_name"));
			t.setClassNum(rs.getString("class_num"));
			t.setPoints(map);
			t.setPoint(1, rs.getInt("point1"));
			t.setPoint(2, rs.getInt("point2"));
		}
		
		st.close();
		con.close();
		
		return list;
	}
}
