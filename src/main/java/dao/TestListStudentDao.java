package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends DAO {

    // ------------------------------------------------------------
	// 学生別成績一覧（縦並び）用 SQL
    // ------------------------------------------------------------
    private static final String baseSql =
            "SELECT " +
            "  sub.name AS subject_name, " +
            "  sub.cd   AS subject_cd, " +
            "  t.no     AS num, " +
            "  t.point  AS point " +
            "FROM test t " +
            "JOIN subject sub ON t.subject_cd = sub.cd " +
            "JOIN student s   ON t.student_no = s.no " +
            "WHERE s.no = ? " +          // 学生番号
            "  AND s.school_cd = ? " +   // 学校コード
            "ORDER BY sub.cd, t.no";

    
    // ------------------------------------------------------------
    // ResultSet → List<TestListStudent> へ変換
    // ------------------------------------------------------------
    private List<TestListStudent> postFilter(ResultSet rs) throws Exception {
        List<TestListStudent> list = new ArrayList<>();

        while (rs.next()) {
            TestListStudent tls = new TestListStudent();
            tls.setSubjectName(rs.getString("subject_name"));
            tls.setSubjectCd(rs.getString("subject_cd"));
            tls.setNum(rs.getInt("num"));
            tls.setPoint(rs.getInt("point"));
            list.add(tls);
        }
        return list;
    }

    // ------------------------------------------------------------
    // filter:学生を条件に検索（学生番号＋学校）
    // ------------------------------------------------------------
    public List<TestListStudent> filter(Student student) throws Exception {

        List<TestListStudent> list;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(baseSql)) {

            stmt.setString(1, student.getNo());           // 学生番号
            stmt.setString(2, student.getSchool().getCd()); // 学校コード

            try (ResultSet rs = stmt.executeQuery()) {
                list = postFilter(rs);
            }
        }

        return list;
    }
}
