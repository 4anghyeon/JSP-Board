package com.sh.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.dao.BoardDAO;
import com.sh.dto.BoardVO;

public class BoarListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO bDao = BoardDAO.getInstance();
		int pageTotal = bDao.getTotalPageNum();
		
		List<BoardVO> boardList = bDao.selectAllBoards(Integer.parseInt(pageNum));
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("pageTotal", pageTotal);
		request.setAttribute("pageNum", Integer.parseInt(pageNum));
		
		String url = "/board/boardList.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		
	}

}
