package board;

import java.sql.*;
import java.util.ArrayList;

public class BoardDAO {

    private Connection conn;
    private ResultSet rs;

    public BoardDAO() {
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

    public String getDate() {
        String SQL = "SELECT NOW()";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ""; // DB 오류
    }


    public int getNext() { // 페이징 처리를 위해 다음에 작성될 게시물의 board_id 반환
        String SQL = "SELECT  board_name  FROM board ORDER BY board_id DESC";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                return rs.getInt(1) + 1;
            }
            return 1; // 첫 게시물
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // DB 오류
    }


    public int write(int categoryId, String username, String password, String title, String content) {
        String SQL = "INSERT INTO board " +
                "(category_id, username, password, title, content, created_date, updated_date, view_count) VALUES " +
                "(?, ?, ?, ?, ?, NOW(), NOW(), 0)";

        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, categoryId);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(4, title);
            pstmt.setString(5, content);

            return pstmt.executeUpdate(); // 성공하면 1, 실패하면 0 반환
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1; // DB 오류
    }



    public ArrayList<Board> getList(int pageNumber) { // 게시글 목록에서 게시물들을 불러오기
        String SQL = "SELECT b.board_id, c.category_name, b.title, b.username, b.view_count, b.created_date, b.updated_date FROM board b JOIN category c ON b.category_id = c.category_id WHERE b.board_id < ? ORDER BY b.board_id DESC LIMIT 10";
        ArrayList<Board> list = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Board board = new Board();
                board.setBoardId(rs.getInt(1));
                board.setCategoryName(rs.getString(2));
                board.setTitle(rs.getString(3));
                board.setUsername(rs.getString(4));
                board.setViewCount(rs.getInt(5));
                board.setCreatedDate(rs.getString(6));
                board.setUpdatedDate(rs.getString(7));
                list.add(board);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }



    public boolean nextPage(int pageNumber) { // 페이징 처리
        String SQL = "SELECT title, username, view_count, created_date, updated_date FROM board " +
                "where board_id < ? " +
                "ORDER BY board_id DESC LIMIT 10";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, getNext() - (pageNumber - 1) * 10);
            rs = pstmt.executeQuery();
            if(rs.next()) {
              return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



    public Board getBoard(int boardId) {
        String SQL = "SELECT " +
                "c.category_name, b.board_id, b.title, b.username, b.view_count, b.created_date, b.updated_date, b.content " +
                "FROM board b " +
                "   JOIN category c ON b.category_id = c.category_id " +
                "WHERE b.board_id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, boardId);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                Board board = new Board();

                board.setBoardId(rs.getInt(2));
                board.setCategoryName(rs.getString(1));
                board.setTitle(rs.getString(3));
                board.setUsername(rs.getString(4));
                board.setViewCount(rs.getInt(5));
                board.setCreatedDate(rs.getString(6));
                board.setUpdatedDate(rs.getString(7));
                board.setContent(rs.getString(8));

                return board;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public int update(int boardId, String boardUsername, String boardTitle, String boardContent) {
        String SQL = "UPDATE board " +
                "SET username = ?, title = ?, content = ? " +
                "WHERE board_id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, boardUsername);
            pstmt.setString(2, boardTitle);
            pstmt.setString(3, boardContent);
            pstmt.setInt(4, boardId);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1; // DB 오류
    }


    public int delete(int boardId) {
        String SQL = "DELETE FROM board WHERE board_id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, boardId);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1; // DB 오류
    }



}
