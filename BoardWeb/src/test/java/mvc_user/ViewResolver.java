package mvc_user;

// ViewResolver 클래스 : Controller가 리턴한 View 이름에서 접두사(경로)와 접미사(파일확장자)를 붙여서 하나의 완성된 파일명을 생성
public class ViewResolver {
	private String prefix; // 접두사 -> 경로
	private String suffix; // 접미사 -> 파일 확장자
	
	public void setPrefix(String prefix) { 
		this.prefix = prefix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getView(String viewName) {
		return prefix + viewName + suffix;
	}
}
