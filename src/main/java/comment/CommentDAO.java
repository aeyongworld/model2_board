package comment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {


    private Connection conn;
    private ResultSet rs;

    public CommentDAO() {
        try {
            String dbURL = "jdbc:mysql://localhost:3306/ebrainsoft_study?useUnicode=true&characterEncoding=UTF-8";
            String dbID = "ebsoft";
            String dbPassword = "ebsoft";
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDate() { // 댓글 작성일자 반환
        String SQL = "SELECT NOW()";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ""; // DB 오류
    }

    // 댓글 추가
    public int addComment(int boardId, String commentContent) {
        String SQL = "INSERT INTO comment " +
                "(board_id, comment_date, comment_content) VALUES " +
                "(?, ?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, boardId);
            pstmt.setString(2, getDate());
            pstmt.setString(3, commentContent);
            return pstmt.executeUpdate(); // 성공하면 1, 실패하면 0 반환
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // DB 오류
    }

    // 해당 게시글의 댓글 가져오기
    public List<Comment> getCommentsForBoard(int boardId) {
        List<Comment> comments = new ArrayList<>();
        String SQL = "SELECT * FROM comment WHERE board_id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, boardId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("comment_id"));
                comment.setBoardId(rs.getInt("board_id"));
                comment.setCommentContent(rs.getString("comment_content"));
                comment.setCommentDate(rs.getString("comment_date"));
                comments.add(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }
}
