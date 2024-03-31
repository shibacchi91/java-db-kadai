package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Posts_Chapter07 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Connection con = null;
		Statement selectStatement = null;
		ResultSet resultSet = null;

		try {
			//データベースに接続
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"AllNightNippon0");
			System.out.println("データベース接続成功" + con);

			// テーブルにデータを追加
			String insertQuery = "INSERT INTO posts (user_id, posted_at, post_content, likes) " +
					"VALUES " +
					"(1003, '2023-02-08', '昨日の夜は徹夜でした・・', 13), " +
					"(1002, '2023-02-08', 'お疲れ様です！', 12), " +
					"(1003, '2023-02-09', '今日も頑張ります！', 18), " +
					"(1001, '2023-02-09', '無理は禁物ですよ！', 17), " +
					"(1002, '2023-02-10', '明日から連休ですね！', 20)";
			Statement insertStatement = con.createStatement();
			int recordsInserted = insertStatement.executeUpdate(insertQuery);
			System.out.println(recordsInserted + "件のレコードが追加されました");

			//ユーザーIDが1002の投稿を検索
			String selectQuery = "SELECT posted_at, post_content, likes FROM posts WHERE user_id = 1002";
			selectStatement = con.createStatement();
			resultSet = selectStatement.executeQuery(selectQuery);
			System.out.println("ユーザーIDが1002の投稿を検索しました");

			int count = 0;
			while (resultSet.next()) {
				count++;
				Date postedAt = resultSet.getDate("posted_at");
				String postContent = resultSet.getString("post_content");
				int likes = resultSet.getInt("likes");
				System.out.println(
						count + "件目:" +
								"投稿日時=" + postedAt +
								"/投稿内容=" + postContent +
								"/いいね数=" + likes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (selectStatement != null) {
					selectStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
