package bean;

import java.io.Serializable;

import javax.security.auth.Subject;

public class Test implements Serializable {

    private Student student;   // 学生オブジェクト
    private String classNum;   // クラス番号
    private Subject subject;   // 科目オブジェクト
    private School school;     // 学校オブジェクト
    private int no;            // 回数
    private int point;         // 得点

    // 学生
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    // クラス番号
    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    // 科目
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    // 学校
    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
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
}
