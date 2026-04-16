package bean;

import java.io.Serializable;

public class Test implements Serializable {

    private String studentNo; // 学生番号
    private String subjectCd; // 科目コード
    private String schoolCd;  // 学校コード
    private int no;           // 回数
    private int point;        // 得点
    private String classNum;  // クラス番号

 // 学生番号
    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }
 // 科目コード
    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }
 // 学校コード
    public String getSchoolCd() {
        return schoolCd;
    }

    public void setSchoolCd(String schoolCd) {
        this.schoolCd = schoolCd;
    }
 // 回数
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
 // 得点
    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
 // クラス番号
    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
}
