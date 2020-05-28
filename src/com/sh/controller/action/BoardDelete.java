package com.sh.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.dao.BoardDAO;
import com.sh.dto.BoardVO;

public class BoardDelete implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.deleteBoard(num);
		
		List<BoardVO> boardList = bDao.selectAllBoards(1);
		
		request.setAttribute("boardList", boardList);
		
		String url = "BoardServlet?command=board_list&pageNum=1";
		response.sendRedirect(url);
	}

}
