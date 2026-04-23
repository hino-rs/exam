package dao;

public class TestListStudentDao extends DAO {
	
	// ------------------------------------------------------------
    // baseSql : テスト1回目と2回目　横並び配置用テーブル
	// ------------------------------------------------------------
	private static final String baseSql =
		    "SELECT "
		  + "    s.ent_year, "                     // 入学年度
		  + "    s.no AS student_no, "             // 学生番号
		  + "    s.name AS student_name, "         // 氏名
		  + "    s.class_num AS student_class, "   // 現在のクラス
		  + "    a.no AS no1, "                    // 1回目の回数
		  + "    a.point AS point1, "              // 1回目の点数
		  + "    b.no AS no2, "                    // 2回目の回数
		  + "    b.point AS point2 "               // 2回目の点数
		  + "FROM test a "                         // 1回目のテストを基点にする
		  + "JOIN student s ON a.student_no = s.no "          // 学生テーブルと JOIN
		  + "JOIN subject sub ON a.subject_cd = sub.cd "      // 科目テーブルと JOIN
		  + "JOIN school sch ON a.school_cd = sch.cd "        // 学校テーブルと JOIN
		  + "LEFT JOIN test b ON a.student_no = b.student_no " // 2回目のテストを LEFT JOIN で横並び配置
		  + "    AND a.subject_cd = b.subject_cd "
		  + "    AND a.school_cd = b.school_cd "
		  + "    AND b.no = 2 "                                // 2回目
		  + "WHERE a.no = 1 "                                  // 1回目がある学生だけ表示
		  + "  AND a.subject_cd = ? "                          // 科目
		  + "  AND a.school_cd = ? "                           // 学校
		  + "  AND s.ent_year = ? "                            // 入学年度
		  + "  AND s.class_num = ? ";                          // クラス
	
	}