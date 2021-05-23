package com.springbook.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

@Repository("boardDAO")
public class BoardDAOSpring2 {
	// sql 명령문
	
	// 트랙잭션 테스트
	//private final String BOARD_INSERT = "insert into board(seq, title, writer, content) values(?,?,?,?)";
	
	private final String BOARD_INSERT = "insert into board(seq, title, writer, content) values(board_seq.nextval,?,?,?)";
	private final String BOARD_UPDATE = "update board set title=?, content=?, writer=? where seq=?";
	private final String BOARD_DELETE = "delete board where seq=?";
	private final String BOARD_GET = "select * from board where seq=?";
	private final String BOARD_LIST = "select * from board";
	
	// 검색 관련 sql문
	private final String BOARD_LIST_TITLE = 
			"select * from board where title like '%'||?||'%' order by seq desc";
	private final String BOARD_LIST_CONTENT = 
			"select * from board where content like '%'||?||'%' order by seq desc";
	private final String BOARD_LIST_WRITER = 
			"select * from board where writer like '%'||?||'%' order by seq desc";

	// JdbcTemplate 객체의 의존성 주입
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 글 등록
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC2로 insertBoard() 기능 처리");
		jdbcTemplate.update(BOARD_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent());
	}
	
	// 글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC2로 updateBoard() 기능 처리");
		jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getWriter(), vo.getSeq());
	}
	
	// 글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC2로 deleteBoard() 기능 처리");
		jdbcTemplate.update(BOARD_DELETE, vo.getSeq());
	}
	
	// 글 상세보기 (1건)
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC2로 getBoard() 기능 처리");
		Object[] args = {vo.getSeq()};
		return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
	}
	
	// 글 목록보기(전체)
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> Spring JDBC2로 getBoardList() 기능 처리");
		
		Object[] args = {vo.getSearchKeyword()};
		if(vo.getSearchCondition().equals("title")) {
			return jdbcTemplate.query(BOARD_LIST_TITLE, args, new BoardRowMapper());
		} else if(vo.getSearchCondition().equals("content")) {
			return jdbcTemplate.query(BOARD_LIST_CONTENT, args, new BoardRowMapper());
		} else if(vo.getSearchCondition().equals("writer")) {
			return jdbcTemplate.query(BOARD_LIST_WRITER, args, new BoardRowMapper());
		}
		
		return null;
	}
	
	// Board의 구현체 클래스 - select문에서 ResultSet으로 돌려받는 값을 관리하는 클래스
	public class BoardRowMapper implements RowMapper<BoardVO> {
		@Override
		public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			BoardVO board = new BoardVO();
			board.setSeq(rs.getInt("seq"));
			board.setTitle(rs.getString("title"));
			board.setWriter(rs.getString("writer"));
			board.setContent(rs.getString("content"));
			board.setRegdate(rs.getDate("regdate"));
			board.setCnt(rs.getInt("cnt"));
			return board;
		}	
	}
}
