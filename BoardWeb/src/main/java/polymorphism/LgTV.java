package polymorphism;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component("tv")
public class LgTV implements TV {
	// 1번 방법
//	@Autowired 
//	@Qualifier("apple")
	
	// 2번 방법
//	@Inject 
//	@Qualifier("apple")
	
	// 3번 방법
	@Resource(name="sony")
	private Speaker speaker;
	
	public LgTV() {
		System.out.println("▣ LgTV 객체 생성 (1)");
	}
	
	@Override
	public void powerOn() { 
		System.out.println("▣ LgTV --- 전원을 켠다.");
	}
	
	@Override
	public void powerOff() { 
		System.out.println("▣ LgTV --- 전원을 끈다.");
	}
	
	@Override
	public void volumeUp() { 
		//System.out.println("▣ LgTV --- 소리를 올린다.");
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() { 
		//System.out.println("▣ LgTV --- 소리를 내린다.");
		speaker.volumeDown();
	}
	
}
