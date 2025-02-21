package com.gn.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gn.board.service.BoardService;
import com.gn.board.vo.Board;

@WebServlet("/boardManytInsert")
public class BoardManytInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardManytInsertServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Board> list = new ArrayList<Board>();
		list.add(Board.builder().boardTitle("a").boardContent("b").boardWriter(22).build());
		list.add(Board.builder().boardTitle("가").boardContent("나").boardWriter(22).build());
		list.add(Board.builder().boardTitle("1").boardContent("2").boardWriter(22).build());
	
		int result = new BoardService().insertMany(list);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
