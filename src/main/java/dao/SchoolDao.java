package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class SchoolDao extends DAO {
	public School get(String cd) throws Exception {

	    Connection con = getConnection();
	    PreparedStatement st = con.prepareStatement(
	        "SELECT * FROM school WHERE cd = ?"
	    );
	    st.setString(1, cd);

	    ResultSet rs = st.executeQuery();

	    School school = null;   // ← ここが重要（存在しない場合は null）

	    if (rs.next()) {
	        school = new School();
	        school.setCd(rs.getString("cd"));
	        school.setName(rs.getString("name"));
	    }

	    rs.close();
	    st.close();
	    con.close();

	    return school;
	}
	
	public List<String> getAllSchoolCd() throws Exception {
		List<String> list = new ArrayList<>();
		
		Connection con = getConnection();
		PreparedStatement st;
		
		st = con.prepareStatement("SELECT cd FROM school");
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			list.add(rs.getString("cd"));
		}
		
		con.close();
		st.close();
		
		return list;
	}
	
	// ------------------------------------------------------------
    //  追加：学校一覧（School オブジェクト）を取得
    // ------------------------------------------------------------
    public List<School> getAll() throws Exception {

        List<School> list = new ArrayList<>();

        Connection con = getConnection();
        PreparedStatement st;

        // 学校コード順に並べて取得
        st = con.prepareStatement("SELECT * FROM school ORDER BY cd");
        ResultSet rs = st.executeQuery();

        // 1件ずつ School オブジェクトに詰めてリストへ
        while (rs.next()) {
            School s = new School();
            s.setCd(rs.getString("cd"));     // 学校コード
            s.setName(rs.getString("name")); // 学校名
            list.add(s);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }
    
    // ---------------------------------------------------------
    //  追加：学校の新規登録
    // ---------------------------------------------------------
    public boolean save(School school) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "INSERT INTO school (cd, name) VALUES (?, ?)"
        );

        st.setString(1, school.getCd());
        st.setString(2, school.getName());

        int result = st.executeUpdate();

        st.close();
        con.close();

        return result == 1;
    }
    
	 // ------------------------------------------------------------
	 //  追加：学校名の更新
	 // ------------------------------------------------------------
	 public boolean update(School school) throws Exception {
	
	     Connection con = getConnection();
	
	     PreparedStatement st = con.prepareStatement(
	         "UPDATE school SET name = ? WHERE cd = ?"
	     );
	
	     st.setString(1, school.getName());
	     st.setString(2, school.getCd());
	
	     int result = st.executeUpdate();
	
	     st.close();
	     con.close();
	
	     return result == 1;
	 }
}