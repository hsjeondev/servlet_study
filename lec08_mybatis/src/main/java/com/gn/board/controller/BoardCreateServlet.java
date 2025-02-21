package com.gn.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gn.board.service.BoardService;
import com.gn.board.vo.Board;

@WebServlet("/boardCreate")
public class BoardCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardCreateServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardTitle = request.getParameter("board_title");
		String boardContent = request.getParameter("board_content");
		String temp = request.getParameter("board_writer");
		int boardWriter = 0;
		if(temp != null) {
			boardWriter = Integer.parseInt(temp);
		}
		Board board = Board.builder()
				.boardTitle(boardTitle)
				.boardContent(boardContent)
				.boardWriter(boardWriter)
				.build();
		int result = new BoardService().createBoard(board);
		System.out.println("서블릿 : " + result);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
