package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends DAO {
	
	// ------------------------------------------------------------
    // baseSql : JOIN して Test + Student + Subject + School をまとめて取得
	// ------------------------------------------------------------
	private static final String baseSql =
		    "SELECT " +
		    " t.student_no, " +					// 学生番号
		    " t.subject_cd, " +					// 科目コード
		    " t.school_cd, " +					// 学校コード
		    " t.no, " +							// 回数	
		    " t.point, " +						// 点数
		    " t.class_num, " +			        // テストを受けたときのクラス	
		    " s.name AS student_name, " +		// 学生名	
		    " s.ent_year, " +					// 入学年度	
		    " s.class_num AS student_class, " +	// 学生の現在のクラス
		    " s.is_attend, " +					// 在籍フラグ
		    " sub.name AS subject_name, " +		// 科目名
		    " sch.name AS school_name " +		// 学校名
		    "FROM test t " +					// テストテーブルを基点に JOIN
		    "JOIN student s ON t.student_no = s.no " +      // 学生テーブルと JOIN	
		    "JOIN subject sub ON t.subject_cd = sub.cd " +  // 科目テーブルと JOIN
		    "JOIN school sch ON t.school_cd = sch.cd ";		// 学校テーブルと JOIN

    // ------------------------------------------------------------
    // get : テスト1件を取得
    // ------------------------------------------------------------
    public Test get(Student student, Subject subject, School school, int no) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            baseSql + " WHERE t.student_no = ? "
            		+ "AND t.subject_cd = ? "
            		+ "AND t.school_cd = ? "
            		+ "AND t.no = ?"
        );

        st.setString(1, student.getNo());
        st.setString(2, subject.getCd());
        st.setString(3, school.getCd());
        st.setInt(4, no);

        ResultSet rs = st.executeQuery();

        Test test = null;
        if (rs.next()) {
            test = mapToTest(rs);
        }

        rs.close();
        st.close();
        con.close();

        return test;
    }

    // ------------------------------------------------------------
    // postFilter : ResultSet → List<Test> へ変換
    // ------------------------------------------------------------
    public List<Test> postFilter(ResultSet rs, School school) throws Exception {
        List<Test> list = new ArrayList<>();

        while (rs.next()) {
            Test test = mapToTest(rs);
            test.setSchool(school); 
            list.add(test);
        }

        return list;
    }

    // ------------------------------------------------------------
    // filter : 条件検索（入学年度・クラス・科目・回数・学校）
    // ------------------------------------------------------------
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            baseSql +
            " WHERE s.ent_year = ? "
            + "AND s.class_num = ? "
            + "AND t.subject_cd = ? "
            + "AND t.no = ? "
            + "AND t.school_cd = ?"
        );

        st.setInt(1, entYear);
        st.setString(2, classNum);
        st.setString(3, subject.getCd());
        st.setInt(4, num);
        st.setString(5, school.getCd());

        ResultSet rs = st.executeQuery();
        List<Test> list = postFilter(rs, school);

        rs.close();
        st.close();
        con.close();

        return list;
    }

    // ------------------------------------------------------------
    // save : テスト結果をまとめて保存
    // ------------------------------------------------------------
    public boolean update(Test test) throws Exception {
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "UPDATE test SET point = ? "
            + "WHERE student_no = ? "
            + "AND subject_cd = ? "
            + "AND school_cd = ? "
            + "AND no = ?"
        );

        st.setInt(1, test.getPoint());
        st.setString(2, test.getStudent().getNo());
        st.setString(3, test.getSubject().getCd());
        st.setString(4, test.getSchool().getCd());
        st.setInt(5, test.getNo());

        int result = st.executeUpdate();

        st.close();
        con.close();

        return result == 1;
    }

    // ------------------------------------------------------------
    // update : テスト1件を更新
    // ------------------------------------------------------------
    public boolean update(Test test, Connection con) throws Exception {

        PreparedStatement st = con.prepareStatement(
            "UPDATE test SET point = ? "
            + "WHERE student_no = ? "
            + "AND subject_cd = ? "
            + "AND school_cd = ? "
            + "AND no = ?"
        );

        st.setInt(1, test.getPoint());
        st.setString(2, test.getStudent().getNo());
        st.setString(3, test.getSubject().getCd());
        st.setString(4, test.getSchool().getCd());
        st.setInt(5, test.getNo());

        int result = st.executeUpdate();
        st.close();

        return result == 1;
    }
    
    // ------------------------------------------------------------
    // insert : テスト1件を保存
    // ------------------------------------------------------------
    public boolean insert(Test test) throws Exception {
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "INSERT INTO test "
            + "(student_no,"
            + " subject_cd,"
            + " school_cd,"
            + " no, point, class_num)" 
    		+ " VALUES (?, ?, ?, ?, ?, ?)"
        );

        st.setString(1, test.getStudent().getNo());
        st.setString(2, test.getSubject().getCd());
        st.setString(3, test.getSchool().getCd());
        st.setInt(4, test.getNo());
        st.setInt(5, test.getPoint());
        st.setString(6, test.getClassNum());

        int result = st.executeUpdate();

        st.close();
        con.close();

        return result == 1;
    }

    // ------------------------------------------------------------
    // mapToTest : JOIN 結果を Test オブジェクトへ変換
    // ------------------------------------------------------------
    private Test mapToTest(ResultSet rs) throws Exception {

        Test test = new Test();

        // Student
        Student student = new Student();
        student.setNo(rs.getString("student_no"));
        student.setName(rs.getString("student_name"));
        student.setEntYear(rs.getInt("ent_year"));
        student.setClassNum(rs.getString("student_class"));
        student.setAttend(rs.getBoolean("is_attend"));
        test.setStudent(student);

        // Subject
        Subject subject = new Subject();
        subject.setCd(rs.getString("subject_cd"));
        subject.setName(rs.getString("subject_name"));
        test.setSubject(subject);

        // School
        School school = new School();
        school.setCd(rs.getString("school_cd"));
        school.setName(rs.getString("school_name"));
        test.setSchool(school);

        // Test
        test.setNo(rs.getInt("no"));
        test.setPoint(rs.getInt("point"));
        test.setClassNum(rs.getString("class_num"));

        return test;
    }
}
