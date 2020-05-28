package com.sh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sh.dto.BoardVO;
import com.sh.dto.CommentVO;

import util.DBManager;

public class BoardDAO {
	private BoardDAO() {}
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	
	public List<BoardVO> selectAllBoards(int pageNum){
		String sql = "select  X.*\r\n" + 
				"from (\r\n" + 
				"    select rownum as rnum, A.*\r\n" + 
				"    from (\r\n" + 
				"        select *\r\n" + 
				"        from board\r\n" + 
				"        order by num desc\r\n" + 
				"        ) A\r\n" + 
				"    where rownum <="+pageNum*10+") X\r\n" + 
				"where X.rnum >="+(pageNum*10 -9);
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				BoardVO bVo = new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setPass(rs.getString("pass"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				bVo.setComment_num(rs.getInt("comment_num"));
				
				list.add(bVo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, stmt,rs);
		}
		return list;
	}
	
	public void insertBoard(BoardVO bVo) {
		String sql  = "insert into board("+"num,name,email,pass,title,content)" +"values(board_seq.nextval,?,?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getEmail());
			pstmt.setString(3, bVo.getPass());
			pstmt.setString(4, bVo.getTitle());
			pstmt.setString(5, bVo.getContent());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}

	public void updateReadCount(String num) {
		String sql = "update board set readcount=readcount+1 where num=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn=DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}

	public BoardVO selectOneBoardByNum(String num) {
		String sql = "select * from board where num = ?";
		
		BoardVO bVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn=DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bVo = new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setName(rs.getString("name"));
				bVo.setEmail(rs.getString("email"));
				bVo.setPass(rs.getString("pass"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		return bVo;
	}

	public void deleteBoard(String num) {
		String sql = "delete from board where num = ?";
		String sql2 = "ALTER SEQUENCE BOARD_SEQ INCREMENT BY -1";
		String sql3 = "SELECT BOARD_SEQ.NEXTVAL FROM DUAL ";
		String sql4 = "ALTER SEQUENCE BOARD_SEQ INCREMENT BY 1 ";
		String sql5 = "update board set num = num-1 where num>"+num;
		String sql6 = "update board_comment set bNo = bNo-1 where bNo>"+num;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;
		PreparedStatement pstmt6 = null;
		
		try {
			conn=DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
			
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.executeUpdate();
			pstmt3 = conn.prepareStatement(sql3);
			pstmt3.executeUpdate();
			pstmt4 = conn.prepareStatement(sql4);
			pstmt4.executeUpdate();
			pstmt5 = conn.prepareStatement(sql5);
			pstmt5.executeUpdate();
			pstmt6 = conn.prepareStatement(sql6);
			pstmt6.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
			DBManager.close(conn, pstmt2);
			DBManager.close(conn, pstmt3);
			DBManager.close(conn, pstmt4);
			DBManager.close(conn, pstmt5);
			DBManager.close(conn, pstmt6);
		}
		
	}

	public void updateBoard(BoardVO bVo) {
		String sql = "update board set name=?, email=? ,pass=?, title=?, content=? where num=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, bVo.getName());
			pstmt.setString(2, bVo.getEmail());
			pstmt.setString(3, bVo.getPass());
			pstmt.setString(4, bVo.getTitle());
			pstmt.setString(5, bVo.getContent());
			pstmt.setInt(6, bVo.getNum());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}

	public List<CommentVO> getCommentList(String num) {
		
		String sql = "select * from board_comment where bNo = ? order by cNo";
		List<CommentVO> comment_list = new ArrayList<CommentVO>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn=DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CommentVO cVo = new CommentVO();
				cVo.setcNo(rs.getInt("cNo"));
				cVo.setName(rs.getString("name"));
				cVo.setPass(rs.getString("pass"));
				cVo.setContent(rs.getString("content"));
				cVo.setbNo(rs.getInt("bNo"));
				
				comment_list.add(cVo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		return comment_list;
	}

	public void insertComment(CommentVO cVo) {
		
		String sql  = "insert into board_comment("+"cNo,name,pass,content,bNo)" +"values(board_comment_seq.nextval,?,?,?,?)";
		String sql2 = "update board set comment_num = comment_num+1 where num="+cVo.getbNo();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt.setString(1, cVo.getName());
			pstmt.setString(2, cVo.getPass());
			pstmt.setString(3, cVo.getContent());
			pstmt.setInt(4, cVo.getbNo());
			
			pstmt.executeUpdate();
			pstmt2.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
			DBManager.close(conn, pstmt2);
		}
	}

	public int getTotalPageNum() {
		String sql  = "select count(*) as count from board";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int pageTotal = 1;
		
		try {
			conn=DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pageTotal = rs.getInt("COUNT");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		
		return pageTotal;
	}

	
}
