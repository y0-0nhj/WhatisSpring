package mvc_user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
	
	// 생성자 이전에 동작하는 초기자 메소드 - 클래스의 초기 설정 작업을 함
	@Override
	public void init() throws ServletException {
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		viewResolver.setPrefix("./");
		viewResolver.setSuffix(".jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 인코딩 방식 설정
		request.setCharacterEncoding("utf-8");
		// 1. 클라이언트 요청 경로를 추출
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println("path : " + path);
				
		// 2. 요청한 경로에 해당하는 Controller를 HandlerMapping에서 검색
		Controller ctrl = handlerMapping.getController(path);
		
		// 3. 검색한 Controller를 실행
		String viewName = ctrl.handleRequest(request, response);
		
		// 4. viewName에 해당하는 화면을 ViewResolver를 통해 검색
		String view = null;
		if(!viewName.contains(".do")) { // jsp로 이동하는 경우
			view = viewResolver.getView(viewName);
		} else {                        // do로 이동하는 경우
			view = viewName;
		}
		
		// 5. 검색한 화면으로 이동
		response.sendRedirect(view);
	}

}
