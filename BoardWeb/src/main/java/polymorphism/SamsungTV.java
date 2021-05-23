package polymorphism;

public class SamsungTV implements TV {
	private Speaker speaker;
	private int price;
	
	// 1. 생성자 인젝션 활용하는 방법 : 생성자 오버로딩을 활용 
	// 생성자
	public SamsungTV() {
		System.out.println("■ SamsungTV 객체 생성 (1)");
	}
	
	// 생성자 오버로딩
	public SamsungTV(Speaker speaker) {
		System.out.println("■ SamsungTV 객체 생성 (2)");
		this.speaker = speaker;
	}
	
	// 생성자 오버로딩
	public SamsungTV(Speaker speaker, int price) {
		System.out.println("■ SamsungTV 객체 생성 (3)");
		this.speaker = speaker;
		this.price = price;
	}
	
	// 2. 세터 인젝션을 활용 : setter 메소드를 활용
	public void setSpeaker(Speaker speaker) {
		System.out.println("■ SamsungTV setSpeaker() 메소드 활용");
		this.speaker = speaker;
	}
	
	public void setPrice(int price) {
		System.out.println("■ SamsungTV setPrice() 메소드 활용");
		this.price = price;
	}
	
	@Override
	public void powerOn() { 
		System.out.println("■ SamsungTV --- 전원을 켠다. (가격: " + price + ")");
	}
	
	@Override
	public void powerOff() { 
		System.out.println("■ SamsungTV --- 전원을 끈다.");
	}
	
	@Override
	public void volumeUp() { 
		//speaker = new SonySpeaker();
		speaker.volumeUp();
		//System.out.println("■ SamsungTV --- 소리를 올린다.");
	}

	@Override
	public void volumeDown() { 
		//speaker = new SonySpeaker();
		speaker.volumeDown();
		//System.out.println("■ SamsungTV --- 소리를 내린다.");
	}
	
	/*
	// 초기화 작업
	public void initMethod() {
		System.out.println("SamsungTV 초기화 작업");
	}
	
	// 마무리 작업
	public void destroyMethod() {
		System.out.println("SamsungTV 마무리 작업");
	}
	*/
}
