package mvc_user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springbook.biz.member.MemberVO;
import com.springbook.biz.member.impl.MemberDAO;

public class LoginController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("===> 로그인 처리");
		
		// 1. 사용자 정보 추출
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		// 2. DB 연동 처리
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPwd(pwd);

		MemberDAO memberDAO = new MemberDAO();
		MemberVO member = memberDAO.getMember(vo);

		// 3. 경로 리턴
		if(member != null) { // 로그인 성공
			return "getBoardList.do";
		} else { // 로그인 실패
			return "login";
		}
	}

}
