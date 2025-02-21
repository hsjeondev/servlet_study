package com.gn.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.gn.board.vo.Board;

public class BoardDao {
	
	public int insertMany(SqlSession session, List<Board> list) {
		return session.insert("boardMapper.insertMany", list);
	}
	
	public int createBoard(SqlSession session, Board board) {
		System.out.println("실행전 : " + board);
		int result = session.insert("boardMapper.boardInsert", board);
		System.out.println("실행후 : " + board);
		result = board.getBoardNo();
		return result;
	}
	
	public int deleteBoard(SqlSession session, int boardNo) {
		return session.delete("boardMapper.boardDelete", boardNo);
	}
	
	public int updateBoard(SqlSession session, Board board) {
		return session.update("boardMapper.boardUpdate", board);
	}
	
	public Board selectBoardThree(SqlSession session, Board board) {
		return session.selectOne("boardMapper.boardThree", board);
	}
	
	public Board selectBoardTwo(SqlSession session, Map<String, String> paramMap) {
		return session.selectOne("boardMapper.boardTwo", paramMap);
	}
	
	public Board selectBoardOne(SqlSession session, int boardNo) {
		return session.selectOne("boardMapper.boardOne", boardNo);
	}
	
	public List<Board> selectBoardList(SqlSession session, Board option) {
		// 매개변수 : mapper의 namespace.쿼리문의 id
		return session.selectList("boardMapper.boardList", option);
	}
}
