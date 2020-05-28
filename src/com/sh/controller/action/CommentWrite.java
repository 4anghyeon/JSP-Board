package com.sh.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.dao.BoardDAO;
import com.sh.dto.CommentVO;

public class CommentWrite implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CommentVO cVo = new CommentVO();
		cVo.setbNo(Integer.parseInt(request.getParameter("commentNum")));
		cVo.setName(request.getParameter("commentName"));
		cVo.setPass(request.getParameter("commentPass"));
		cVo.setContent(request.getParameter("commentContent"));
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.insertComment(cVo);
		
		
		String url = "BoardServlet?command=board_view&num="+request.getParameter("commentNum");
		response.sendRedirect(url);
	}

}
