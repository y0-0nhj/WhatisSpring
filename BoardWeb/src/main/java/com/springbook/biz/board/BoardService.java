package com.springbook.biz.board;

import java.util.List;

public interface BoardService {
	
	// 글 등록
	void insertBoard(BoardVO vo);
	
	// 글 수정
	void updateBoard(BoardVO vo);
	
	// 글 삭제
	void deleteBoard(BoardVO vo);
	
	// 글 상세 보기 - 1건
	BoardVO getBoard(BoardVO vo);
	
	// 글 목록 보기 - 전체
	List<BoardVO> getBoardList(BoardVO vo);
}
