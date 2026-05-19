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
    public boolean update(ClassNum classNum, String newClassNum) throws Exception {

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
    
    public List<ClassNum> getAll() throws Exception {
		ClassNum c = null;
		List<ClassNum> list = new ArrayList<>();
		
		Connection con = getConnection();
		PreparedStatement st;
		
		st = con.prepareStatement("SELECT * FROM class_num");
		ResultSet rs = st.executeQuery();
		
		SchoolDao dao = new SchoolDao();
		
		while (rs.next()) {
			c = new ClassNum();
			c.setClass_num(rs.getString("class_num"));
			c.setSchool(dao.get(rs.getString("school_cd")));
			list.add(c);
		}
		
		st.close();
		con.close();
		return list;
	}
    
//    public boolean create(ClassNum classnum) throws Exception {
//		Connection con = getConnection();
//		PreparedStatement st;
//		
//		st = con.prepareStatement("INSERT INTO class_num VALUES(?,?,?,?)");
//		st.setString(1, classnum.getClass_num());
//		st.setString(2, classnum.getSchool().getCd());
//		
//		int result = st.executeUpdate();
//		
//		st.close();
//		con.close();
//		
//		return result == 1;
//	}
//    
//    public boolean update(ClassNum classnum) throws Exception {
//		Connection con = getConnection();
//		PreparedStatement st;
//		
//		st = con.prepareStatement("UPDATE class_num SET class_num=? WHERE class_num=? and school_cd=?");
//		st.setString(1, classnum.getClass_num());
//		st.setString(2, classnum.getSchool().getCd());
//		
//		int result = st.executeUpdate();
//
//        st.close();
//        con.close();
//
//        return result == 1;	}
    

//---------------------------------------------------------------------------------
//  追加 ： クラスの削除　delete(String classNum, School school) 
//          School オブジェクトを持っている場合に使用
// ---------------------------------------------------------------------------------
    public boolean delete(String classNum, School school) throws Exception {

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "DELETE FROM class_num WHERE class_num = ? AND school_cd = ?"
        );

        st.setString(1, classNum);
        st.setString(2, school.getCd());

        int result = st.executeUpdate();

        st.close();
        con.close();

        return result == 1;
    }
    
 // ------------------------------------------------------------
 //  追加 ： クラスの削除delete(String school_cd, String class_num)
 // （ClassNumDeleteExecuteAction 用 オーバーロード）School オブジェクトを持っていないとき
 // ------------------------------------------------------------
	 public boolean delete(String school_cd, String class_num) throws Exception {
	
	     Connection con = getConnection();
	
	     PreparedStatement st = con.prepareStatement(
	         "DELETE FROM class_num WHERE class_num = ? AND school_cd = ?"
	     );
	
	     // class_num と school_cd をそのまま SQL にセット
	     st.setString(1, class_num);
	     st.setString(2, school_cd);
	
	     int result = st.executeUpdate();
	
	     st.close();
	     con.close();
	
	     // 1件削除できたら true を返す
	     return result == 1;
	 }    
	 
// ------------------------------------------------------------
// 追加：ClassNum オブジェクトを受け取って削除する delete
// ------------------------------------------------------------
	public boolean delete(ClassNum cn) throws Exception {

	    Connection con = getConnection();

	    PreparedStatement st = con.prepareStatement(
	        "DELETE FROM class_num WHERE class_num = ? AND school_cd = ?"
	    );

	    st.setString(1, cn.getClass_num());
	    st.setString(2, cn.getSchool().getCd());

	    int result = st.executeUpdate();

	    st.close();
	    con.close();

	    return result == 1;
	}	 
	 
// ------------------------------------------------------------
//  追加 ： 指定クラスに学生が存在するかチェック
// ------------------------------------------------------------
	public boolean hasStudents(String class_num, School school) throws Exception {

	    Connection con = getConnection();
	    PreparedStatement st = con.prepareStatement(
	        "SELECT COUNT(*) FROM student WHERE class_num = ? AND school_cd = ?"
	    );

	    st.setString(1, class_num);
	    st.setString(2, school.getCd());

	    ResultSet rs = st.executeQuery();
	    rs.next();
	    int count = rs.getInt(1);

	    rs.close();
	    st.close();
	    con.close();

	    return count > 0;
	}

// ------------------------------------------------------------
//  追加 ： 指定クラスにテスト結果が存在するかチェック
// ------------------------------------------------------------
	public boolean hasScores(String class_num, School school) throws Exception {

	    Connection con = getConnection();
	    PreparedStatement st = con.prepareStatement(
	        "SELECT COUNT(*) FROM TEST WHERE class_num = ? AND school_cd = ?"
	    );

	    st.setString(1, class_num);
	    st.setString(2, school.getCd());

	    ResultSet rs = st.executeQuery();
	    rs.next();
	    int count = rs.getInt(1);

	    rs.close();
	    st.close();
	    con.close();

	    return count > 0;
	}

}
