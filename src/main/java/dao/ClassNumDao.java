package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends DAO {

    // 1件取得（class_num + school_cd）
    public ClassNum get(String classNum, School school) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "SELECT class_num FROM class_num WHERE class_num = ? AND school_cd = ?"
        );

        st.setString(1, classNum);
        st.setString(2, school.getCd());

        ResultSet rs = st.executeQuery();

        ClassNum cn = null;
        if (rs.next()) {
            cn = new ClassNum();
            cn.setClass_num(rs.getString("class_num"));
            cn.setSchool(school);
        }

        rs.close();
        st.close();
        con.close();

        return cn;
    }

    // クラス一覧を取得（学校ごと）
    public List<String> filter(School school) throws Exception {

        List<String> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "SELECT class_num FROM class_num WHERE school_cd = ? ORDER BY class_num"
        );

        st.setString(1, school.getCd());

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            list.add(rs.getString("class_num"));
        }

        rs.close();
        st.close();
        con.close();

        return list; 
    }

    // クラス名を保存（INSERT）
    public boolean save(ClassNum classNum) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "INSERT INTO class_num (class_num, school_cd) VALUES (?, ?)"
        );

        st.setString(1, classNum.getClass_num());
        st.setString(2, classNum.getSchool().getCd());

        int result = st.executeUpdate();

        st.close();
        con.close();

        return result == 1;
    }

    // クラス名を変更（UPDATE）
    public boolean save(ClassNum classNum, String newClassNum) throws Exception {

        Connection con = getConnection();
        PreparedStatement st = con.prepareStatement(
            "UPDATE class_num SET class_num = ? WHERE class_num = ? AND school_cd = ?"
        );

        st.setString(1, newClassNum);
        st.setString(2, classNum.getClass_num());
        st.setString(3, classNum.getSchool().getCd());

        int result = st.executeUpdate();

        st.close();
        con.close();

        return result == 1;
    }
    
}
