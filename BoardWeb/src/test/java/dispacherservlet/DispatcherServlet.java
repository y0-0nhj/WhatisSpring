package dispacherservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.biz.member.MemberVO;
import com.springbook.biz.member.impl.MemberDAO;

public class DispatcherServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println("path : " + path);
		
		// out 객체
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		/*
		if(path.equals("/login.do")) {
			out.print("<html><body>로그인 처리</body></html>");
		} else if(path.equals("/logout.do")) {
			out.print("<html><body>로그아웃 처리</body></html>");
		} else if(path.equals("/insertBoard.do")) {
			out.print("<html><body>글등록 처리</body></html>");
		} else if(path.equals("/updateBoard.do")) {
			out.print("<html><body>글수정 처리</body></html>");
		} else if(path.equals("/deleteBoard.do")) {
			out.print("<html><body>글삭제 처리</body></html>");
		} else if(path.equals("/getBoard.do")) {
			out.print("<html><body>글상세보기 처리</body></html>");
		} else if(path.equals("/getBoardList.do")) {
			out.print("<html><body>글목록보기 처리</body></html>");
		}
		*/
		
		if(path.equals("/login.do")) {
			System.out.println("===> 로그인 처리");
			
			// 1. 사용자 정보 추출
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");

			MemberVO vo = new MemberVO();
			vo.setId(id);
			vo.setPwd(pwd);

			// 2. DB 연동 처리
			MemberDAO memberDAO = new MemberDAO();
			MemberVO member = memberDAO.getMember(vo);

			// 3. 화면 이동
			if(member != null) { // 로그인 성공
				response.sendRedirect("getBoardList.do");
			} else { // 로그인 실패
				response.sendRedirect("login.jsp");
			}
		} else if(path.equals("/logout.do")) {
			System.out.println("===> 로그아웃 처리");
			// 1. 세션 연결과 세션 무효화
			HttpSession session = request.getSession();
			session.invalidate();
			// 2. 화면 이동
			response.sendRedirect("login.jsp");
		} else if(path.equals("/insertBoard.do")) {
			System.out.println("===> 글 등록 처리");
			request.setCharacterEncoding("utf-8");

			// 1. 사용자 입력 정보 추출
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			// 2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setWriter(writer);
			vo.setContent(content);
		
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.insertBoard(vo);
			// 3. 화면 이동
			response.sendRedirect("getBoardList.do");
		} else if(path.equals("/updateBoard.do")) {
			System.out.println("===> 글 수정 처리");
			request.setCharacterEncoding("utf-8");

			// 1. 사용자 정보 추출
			String seq = request.getParameter("seq");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			// 2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			vo.setTitle(title);
			vo.setContent(content);
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.updateBoard(vo);
			// 3. 화면 이동
			response.sendRedirect("getBoardList.do");
		} else if(path.equals("/deleteBoard.do")) {
			System.out.println("===> 글 삭제 처리");
			// 1. 사용자 정보 추출
			String seq = request.getParameter("seq");
			// 2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));

			BoardDAO boardDAO = new BoardDAO();
			boardDAO.deleteBoard(vo);
			// 3. 화면 이동
			response.sendRedirect("getBoardList.do");
		} else if(path.equals("/getBoard.do")) {
			System.out.println("===> 글 상세 보기 처리");
			
			// 1. 글번호 추출
			String seq = request.getParameter("seq");
			// 2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			BoardDAO boardDAO = new BoardDAO();
			BoardVO board = boardDAO.getBoard(vo);
			// 3. 세션 생성 및 저장
			HttpSession session = request.getSession();
			session.setAttribute("board", board);
			// 4. 화면 이동
			response.sendRedirect("getBoard.jsp");	
		} else if(path.equals("/getBoardList.do")) {
			System.out.println("===> 글 목록 보기 처리");
			
			// 1. DB 연동 처리
			BoardVO vo = new BoardVO();
			BoardDAO boardDAO = new BoardDAO();
			List<BoardVO> boardList = boardDAO.getBoardList(vo);
			// 2. 세션 생성 및 저장
			HttpSession session = request.getSession();
			session.setAttribute("boardList", boardList);
			// 3. 화면 이동
			response.sendRedirect("getBoardList.jsp");
		}
	}

}
