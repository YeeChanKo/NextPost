package net.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewsDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public NewsDAO() {
		conn = getConnection();
	}

	private Connection getConnection() {
		String url = "jdbc:mysql://192.168.56.101/NewsSite";
		String id = "popi";
		String pw = "popi1004";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			System.out.println("[DB Connection Failed] : " + e.getMessage());
			return null;
		}
	}

	public void insertPost(Post post) {
		String sql = "INSERT INTO NEWS(title,writer,content,creation) VALUES(?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, post.getTitle());
			pstmt.setString(2, post.getWriter());
			pstmt.setString(3, post.getContent());
			pstmt.setString(4, post.getCreation());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[insertPost Failed] : " + e.getMessage());
		} finally {
			closeResources();
		}
	}

	public void insertComment(Comment comment) {
		String sql = "INSERT INTO COMMENT(nid,writer,content,creation) VALUES(?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getNid());
			pstmt.setString(2, comment.getWriter());
			pstmt.setString(3, comment.getContent());
			pstmt.setString(4, comment.getCreation());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[insertComment Failed] : " + e.getMessage());
		} finally {
			closeResources();
		}
	}

	public Post extractPost(int nid) {
		String sql = "SELECT * FROM news WHERE nid=" + nid;
		Post result = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = new Post(rs.getInt("nid"), rs.getString("title"), rs.getString("writer"),
						rs.getString("content"), rs.getString("creation"));
			}
		} catch (SQLException e) {
			System.out.println("[extractPost Failed] : " + e.getMessage());
		} finally {
			closeResources();
		}
		return result;
	}

	public ArrayList<Post> extractAllPosts() {
		String sql = "SELECT * FROM news";
		ArrayList<Post> result = new ArrayList<Post>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Post tempPost = new Post(rs.getInt("nid"), rs.getString("title"), rs.getString("writer"),
						rs.getString("content"), rs.getString("creation"));
				result.add(tempPost);
			}
		} catch (SQLException e) {
			System.out.println("[extractAllPosts Failed] : " + e.getMessage());
		} finally {
			closeResources();
		}
		return result;
	}

	public ArrayList<Comment> extractAllComments(int nid) {
		String sql = "SELECT * FROM comment WHERE nid=" + nid;
		ArrayList<Comment> result = new ArrayList<Comment>();

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Comment tempComment = new Comment(rs.getInt("cid"), rs.getInt("nid"), rs.getString("writer"),
						rs.getString("content"), rs.getString("creation"));
				result.add(tempComment);
			}
		} catch (SQLException e) {
			System.out.println("[extractAllComments Failed] : " + e.getMessage());
		} finally {
			closeResources();
		}
		return result;
	}

	private void closeResources() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("[closeResource Failed] : " + e.getMessage());
		}
	}
}