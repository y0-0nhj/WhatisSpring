package hello;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 서블릿(Servlet) - 자바로 만들어진 클래스
// 서블릿 클래스 

// HelloServlet hello = new HelloServlet();
// hello.doGet();

// IoC를 테스트 하기 위한 서블릿
// - new 통해서 인스턴스를 만들지 않고, 스프링 컨테이너가 실행시에 인스턴스를 만들어 넣어줌
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HelloServlet() {
        System.out.println("===> HelloServlet 객체 생성");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("===> doGet() 메소드 호출");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
