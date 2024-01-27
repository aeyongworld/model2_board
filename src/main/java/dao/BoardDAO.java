package dao;

import com.study.connection.DBManager;
import dto.Board;

import java.sql.*;
import java.util.ArrayList;

public class BoardDAO {

    private Connection conn;
    private ResultSet rs;

    public BoardDAO() {
        conn = DBManager.getConnection();
    }


    // 게시글 작성하는 메서드
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


    // 게시판에 게시글 목록 불러오는 메서드
    public ArrayList<Board> getList() {
        String SQL = "SELECT b.board_id, c.category_name, b.title, b.username, b.view_count, b.created_date, b.updated_date FROM board b JOIN category c ON b.category_id = c.category_id ORDER BY b.board_id DESC";
        ArrayList<Board> list = new ArrayList<>();

        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
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
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }




    // 게시글 하나씩 조회할 때 게시글 정보 불러오는 메서드
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
                upViewCount(boardId);
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


    // 게시글 수정하는 메서드
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

            modifyUpdatedDate(boardId);

            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1; // DB 오류
    }


    // 게시글 삭제하는 메서드
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


    // 조회수 증가 메서드
    public void upViewCount(int boardId) {
        String SQL = "UPDATE board SET view_count = view_count + 1 WHERE board_id = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, boardId);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 수정일자 업데이트 메서드
    public void modifyUpdatedDate(int boardId) {
        String SQL = "UPDATE board SET updated_date = NOW() WHERE board_id = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, boardId);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
