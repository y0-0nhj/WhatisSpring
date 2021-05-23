package com.springbook.view.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springbook.biz.board.BoardListVO;
import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

/*
Command 객체
1. 스프링 내부에 존재하는 객체
2. 역할: 매개변수로 전달되는 값을 VO로 매핑시켜서 돌려주는 역할 
*/

/*
forward 방식: 이전 페이지의 url이 보이게 됨
redirect 방식: 현재 페이지의 url이 보이게 됨
*/

/* 
@RequestMapping : 요청 페이지에 대한 메소드의 매핑하는 기능 
@RequestParam : 매개변수를 매핑하는 기능
value: 전달되는 파라미터 이름
defaultValue: 전달되는 파라미터의 기본 설정값
required: 파라미터의 생략 여부

@ModelAttribute : 매핑된 메소드의 실행 결과로 Model을 생성하여 리턴하는 역할

@ResponseBody : 자바 객체를 Http 응답 프로토콜의 body로 변화하기 위해서 사용
- ResponseBody가 적용된 메소드의 실행결과는 JSON으로 변환되어 http body에 설정
*/

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	// json 방법으로 데이터를 전달
	@RequestMapping("/dataTransformJson.do")
	@ResponseBody
	public List<BoardVO> dataTransformJson(BoardVO vo) {
		vo.setSearchCondition("title");
		vo.setSearchKeyword("");
		
		List<BoardVO> boardList = boardService.getBoardList(vo);
		return boardList;
	}
	
	// xml 방법으로 데이터를 전달
	@RequestMapping("/dataTransformXml.do")
	@ResponseBody
	public BoardListVO dataTransformXml(BoardVO vo) {
		vo.setSearchCondition("title");
		vo.setSearchKeyword("");
		
		List<BoardVO> boardList = boardService.getBoardList(vo);
		BoardListVO boardListVO = new BoardListVO();
		boardListVO.setBoardList(boardList);
		return boardListVO;
	}
	
	
	// 글 등록
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardVO vo) throws Exception {
		/*
		// 파일 업로드 처리
		MultipartFile uploadFile = vo.getUploadFile();
		if(!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("c:/image_board/" + fileName));
		}
		*/
		
		boardService.insertBoard(vo);
		return "getBoardList.do";
	}
	
	// 글 수정
	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardVO vo) {
		boardService.updateBoard(vo);
		return "getBoardList.do";
	}
	
	// 글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		boardService.deleteBoard(vo);
		return "getBoardList.do";
	}
	
	// 검색 조건 목록 설정 - Map:: key: 텍스트, value: value 
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "title");
		conditionMap.put("내용", "content");
		conditionMap.put("작성자", "writer");
		return conditionMap;
	}

	// 글 전체 목록
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model){
		// 검색 조건의 null값 처리
		if(vo.getSearchCondition() == null) vo.setSearchCondition("title");
		if(vo.getSearchKeyword() == null) vo.setSearchKeyword("");
		
		model.addAttribute("boardList", boardService.getBoardList(vo));
		return "getBoardList.jsp";
	}
	
	// 글 상세 조회
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		model.addAttribute("board", boardService.getBoard(vo));
		return "getBoard.jsp";
	}

}
