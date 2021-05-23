package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;

// DAO(Data Access Object)
//@Repository("boardDAO")
public class BoardDAO {
	// JDBC 관련 객체
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// sql 명령문
	private final String BOARD_INSERT = 
			"insert into board(seq, title, writer, content) values(board_seq.nextval,?,?,?)";
	private final String BOARD_UPDATE = 
			"update board set title=?, content=?, writer=? where seq=?";
	private final String BOARD_DELETE = "delete board where seq=?";
	private final String BOARD_GET = "select * from board where seq=?";
	//private final String BOARD_LIST = "select * from board order by regdate desc";
	private final String BOARD_CNT = "update board set cnt=cnt+1 where seq=?";
	
	// 검색 관련 sql문
	private final String BOARD_LIST_TITLE = 
			"select * from board where title like '%'||?||'%' order by seq desc";
	private final String BOARD_LIST_CONTENT = 
			"select * from board where content like '%'||?||'%' order by seq desc";
	private final String BOARD_LIST_WRITER = 
			"select * from board where writer like '%'||?||'%' order by seq desc";
	
	// 글 등록
	public void insertBoard(BoardVO vo) {
		System.out.println("===> JDBC로 insertBoard() 처리");
		try {
			conn = JDBCUtil.getConnection();	
			pstmt = conn.prepareStatement(BOARD_INSERT);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("### insertBoard() 에러 ###");
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// 글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> JDBC로 updateBoard() 처리");
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_UPDATE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.setInt(4, vo.getSeq());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("### updateBoard() 에러 ###");
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// 글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JDBC로 deleteBoard() 처리");
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_DELETE);
			pstmt.setInt(1, vo.getSeq());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("### deleteBoard() 에러 ###");
		} finally {
			JDBCUtil.close(conn, pstmt);
		}
	}
	
	// 글 상세 보기 - 글 1건 -> 조회수 1 증가
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> JDBC로 getBoard() 처리");
		BoardVO board = null;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_GET);
			pstmt.setInt(1, vo.getSeq());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
			}
			
			// 조회수 1 증가
			pstmt = conn.prepareStatement(BOARD_CNT);
			pstmt.setInt(1, vo.getSeq());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("### getBoard() 에러 ###");
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return board;
	}
	
	// 글 목록 보기 - 글 전체
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> JDBC로 getBoardList() 처리");
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			conn = JDBCUtil.getConnection();
			//pstmt = conn.prepareStatement(BOARD_LIST);
			
			if(vo.getSearchCondition().equals("title")) {
				pstmt = conn.prepareStatement(BOARD_LIST_TITLE);
			} else if(vo.getSearchCondition().equals("content")) {
				pstmt = conn.prepareStatement(BOARD_LIST_CONTENT);
			} else if(vo.getSearchCondition().equals("writer")) {
				pstmt = conn.prepareStatement(BOARD_LIST_WRITER);
			}
			pstmt.setString(1, vo.getSearchKeyword());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
				boardList.add(board);
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("### getBoardList() 에러 ###");
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return boardList;
	}
	
}
