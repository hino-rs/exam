package bean;

import java.io.Serializable;

public class TestListStudent implements Serializable {

    private String subjectName;   // 科目名
    private String subjectCd;     // 科目コード
    private int num;              // 回数
    private int point;            // 得点

    // 科目名
    public String getSubjectName() {
        return subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    // 科目コード
    public String getSubjectCd() {
        return subjectCd;
    }
    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    // 回数
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }

    // 得点
    public int getPoint() {
        return point;
    }
    public void setPoint(int point) {
        this.point = point;
    }
}
