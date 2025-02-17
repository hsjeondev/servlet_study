package com.gn.board.dao;

import static com.gn.common.sql.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.gn.board.vo.Attach;
import com.gn.board.vo.Board;

public class BoardDao {
	
	public List<Board> selectBoardList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> resultList = new ArrayList<Board>();
		try {
			String sql = "SELECT b.board_no ,b.board_title ,b.board_content ,m.member_name ,b.reg_date ,b.mod_date "
					+ " FROM board b "
					+ " JOIN member m "
					+ " ON b.board_writer = m.member_no ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int no = rs.getInt("b.board_no");
				String title = rs.getString("b.board_title");
				String content = rs.getString("b.board_content");
				String name = rs.getString("m.member_name");
				LocalDateTime regDate = rs.getTimestamp("b.reg_date").toLocalDateTime();
				LocalDateTime modDate = rs.getTimestamp("b.mod_date").toLocalDateTime();
				Board board = new Board(no, title, content, name, regDate, modDate);
				resultList.add(board);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			close(rs, pstmt);
		}
		System.out.println("dao : " + resultList);
		return resultList;
	}

	public int insertBoard(Board b, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int boardNo = 0;
		try {
			String sql = "INSERT INTO `board`(board_title ,board_content ,board_writer) "
					+ " VALUES(? ,? ,?)";
			// (1) 매개변수 추가
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setInt(3, b.getBoardWriter());
			
			boardNo = pstmt.executeUpdate();
			// (2) 생성된 키 반환
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				boardNo = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt);
		}
		return boardNo;
	}
	
	public int insertAttach(Attach a, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int attachNo = 0;
		try {
			String sql = "INSERT INTO `attach`(board_no ,ori_name ,new_name ,attach_path) "
					+ " VALUES (? ,? ,? ,?)";
			
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, a.getBoardNo());
			pstmt.setString(2, a.getOriName());
			pstmt.setString(3, a.getNewName());
			pstmt.setString(4, a.getAttachPath());
			attachNo = pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				attachNo = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt);
		}
		return attachNo;
	}
}
