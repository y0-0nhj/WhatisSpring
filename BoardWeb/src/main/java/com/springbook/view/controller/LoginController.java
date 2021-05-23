package com.springbook.view.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbook.biz.member.MemberService;
import com.springbook.biz.member.MemberVO;
import com.springbook.biz.member.impl.MemberDAO;

/*
@RequestMapping 역할
1. 역할: 요청을 해당 메소드로 매핑하는 기능.
2. 요청: get 방식, post 방식
- 이 두가지의 요청에 따라 다른 기능을 수행하도록 설정

get 방식: 입력화면을 요청할 때
post 방식: 입력화면에서 submit 버튼을 클릭할 때

*/
@Controller
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	
	// get 방식 처리
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(MemberVO vo) {
		System.out.println("===> 로그인 화면으로 이동");
		
		//vo.setId("aaaa1111");
		//vo.setPwd("1234");
		return "login.jsp";
	}

	// post 방식 처리
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(MemberVO vo, HttpSession session) {
		System.out.println("===> 로그인 인증 처리");
		
		/*
		// 로그인에서 아이디나 비밀번호가 없을 때 예외를 발생시켜서 예외처리하는 방법
		if(vo.getId() == null  || vo.getId().equals("")) {
			throw new IllegalArgumentException("아이디는 반드시 입력해야 합니다.");
		}
		
		if(vo.getPwd() == null  || vo.getPwd().equals("")) {
			throw new IllegalArgumentException("비밀번호는 반드시 입력해야 합니다.");
		}
		*/

		MemberVO member = memberService.getMember(vo);
		if(member != null) { // 로그인되었을 때
			session.setAttribute("memberName", member.getName());
			return "getBoardList.do";
		}
		else return "login.jsp";
	}

}
