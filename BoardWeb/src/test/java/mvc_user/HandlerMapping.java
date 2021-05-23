package mvc_user;

import java.util.HashMap;
import java.util.Map;

// HandlerMapping 클래스 : Controller를 등록하고, 해당 Controller를 리턴하는 클래스
public class HandlerMapping {
	private Map<String, Controller> mappings;
	
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		mappings.put("/login.do", new LoginController());
		mappings.put("/logout.do", new LogoutController());
		mappings.put("/getBoardList.do", new GetBoardListController());
		mappings.put("/getBoard.do", new GetBoardController());
		mappings.put("/insertBoard.do", new InsertBoardController());
		mappings.put("/updateBoard.do", new UpdateBoardController());
		mappings.put("/deleteBoard.do", new DeleteBoardController());
	}

	public Controller getController(String path) {
		return mappings.get(path);
	}
	
}
