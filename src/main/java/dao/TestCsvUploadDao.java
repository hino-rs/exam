package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.TestCsvUpload;


public class TestCsvUploadDao extends DAO{

// ---------------------------------------------------------------------
//  CSV 1 行分のデータ（TestCsvUpload）を受け取り、DB に登録または更新する
// ---------------------------------------------------------------------
    public void insertOrUpdate(TestCsvUpload csv) throws Exception {

    	Connection con = getConnection();
    	
    	PreparedStatement ps = con.prepareStatement(
        		"SELECT STUDENT_NO,SUBJECT_CD,SCHOOL_CD,NO FROM TEST "
        		+ "WHERE STUDENT_NO = ? "
        		+ "AND SUBJECT_CD =? "
        		+ "AND SCHOOL_CD = ? "
        		+ "AND NO = ?"	    			
        		);    	
    	ps.setString(1, csv.getStudentNo());
    	ps.setString(2, csv.getSubjectCd());
    	ps.setString(3, csv.getSchoolCd());
    	ps.setInt(4, csv.getNo());
    	
    	ResultSet rs = ps.executeQuery();
    	
    	// データの存在をチェック   
    	boolean exists = rs.next();  	
    	
        // データ有りの場合は UPDATE、データ無しの場合は INSERT を実行
    	if (exists) {
    		PreparedStatement psUpdate = con.prepareStatement(
    			"UPDATE TEST SET POINT = ?,CLASS_NUM = ? "
    			+ "WHERE STUDENT_NO = ? "
    			+ "AND SUBJECT_CD = ? "
    			+ "AND SCHOOL_CD = ? "
    			+ "AND NO = ?");
    		psUpdate.setInt(1, csv.getPoint());
    		psUpdate.setString(2,csv.getClassNum());
    		psUpdate.setString(3,csv.getStudentNo());
    		psUpdate.setString(4,csv.getSubjectCd());
    		psUpdate.setString(5,csv.getSchoolCd());
    		psUpdate.setInt(6,csv.getNo());
    		
    		psUpdate.executeUpdate();
    		
    	}else {
    		PreparedStatement psInsert = con.prepareStatement(
    			"INSERT INTO TEST ("
    			+ "STUDENT_NO,"
    			+ "SUBJECT_CD,"
    			+ "SCHOOL_CD,"
    			+ "NO,"
    			+ "POINT,"
    			+ "CLASS_NUM"
    			+ ") "
    			+ "VALUES(?,?,?,?,?,?)"
    		);
    		psInsert.setString(1,csv.getStudentNo());
    		psInsert.setString(2,csv.getSubjectCd());
    		psInsert.setString(3,csv.getSchoolCd());
    		psInsert.setInt(4,csv.getNo());
    		psInsert.setInt(5,csv.getPoint());
    		psInsert.setString(6,csv.getClassNum());
    		
    		psInsert.executeUpdate();
    	}
    	
    	rs.close();
    	ps.close();
    	con.close();
    }
}
