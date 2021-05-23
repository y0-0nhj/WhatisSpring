package mvc_user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("===> 로그아웃 처리");
		// 1. 세션 연결과 세션 무효화
		HttpSession session = request.getSession();
		session.invalidate();
		// 2. 화면 이동
		return "login";
	}

}
