package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost/challenge_java"; // データベースのURL
		String username = "root"; // データベースのユーザー名
		String password = "AllNightNippon0"; // データベースのパスワード

		// データベース接続用の変数
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			// データベースに接続
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("データベース接続成功：" + connection);

			// 武者小路勇気の数学と英語の点数を更新
			statement = connection.createStatement();
			String updateQuery = "UPDATE scores SET score_math = 95, score_english = 90 WHERE id = 5";
			int rowsUpdated = statement.executeUpdate(updateQuery);
			System.out.println(rowsUpdated + "件のレコードが更新されました");

			// 点数順に並べ替えて取得
			String selectQuery = "SELECT id, name, score_math, score_english FROM scores ORDER BY score_math DESC, score_english DESC";
			resultSet = statement.executeQuery(selectQuery);

			// 結果の表示
			int count = 1;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int mathScore = resultSet.getInt("score_math");
				int englishScore = resultSet.getInt("score_english");
				System.out
						.println(count + "件目：生徒ID=" + id + "／氏名=" + name + "／数学=" + mathScore + "／英語=" + englishScore);
				count++;
			}
		} catch (SQLException e) {
			System.out.println("データベース接続エラー：" + e.getMessage());
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ignore) {
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ignore) {
				}

			}
		}
	}
}