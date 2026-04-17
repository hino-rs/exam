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

    // JOIN して Test + Student + Subject + School をまとめて取得
    private String baseSql =
        "SELECT t.student_no, t.subject_cd, t.school_cd, t.no, t.point, t.class_num, " +
        "s.name AS student_name, s.ent_year, s.class_num AS student_class, s.is_attend, " +
        "sub.name AS subject_name, sch.name AS school_name " +
        "FROM test t " +
        "JOIN student s ON t.student_no = s.student_no " +
        "JOIN subject sub ON t.subject_cd = sub.cd " +
        "JOIN school sch ON t.school_cd = sch.cd ";

    // ------------------------------------------------------------
    // 1件取得（student + subject + school + no）
    // ------------------------------------------------------------
    public Test get(Student student, Subject subject, School school, int no) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            baseSql + " WHERE t.student_no = ? AND t.subject_cd = ? AND t.school_cd = ? AND t.no = ?"
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
    // ResultSet → List<Test> へ変換
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
    // 条件検索（入学年度・クラス・科目・回数・学校）
    // ------------------------------------------------------------
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            baseSql +
            " WHERE s.ent_year = ? AND s.class_num = ? AND t.subject_cd = ? AND t.no = ? AND t.school_cd = ?"
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
    // テスト結果をまとめて保存
    // ------------------------------------------------------------
    public boolean save(List<Test> list) throws Exception {
        Connection con = getConnection();
        con.setAutoCommit(false);

        try {
            for (Test test : list) {
                save(test, con);
            }
            con.commit();
            return true;

        } catch (Exception e) {
            con.rollback(); // 1件でも失敗したら全て取り消す
            throw e;

        } finally {
            con.close();
        }
    }

    // ------------------------------------------------------------
    // テスト1件を保存（UPDATE）
    // ------------------------------------------------------------
    public boolean save(Test test, Connection con) throws Exception {

        PreparedStatement st = con.prepareStatement(
            "UPDATE test SET point = ? WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?"
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
    // JOIN 結果を Test オブジェクトへ変換
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
