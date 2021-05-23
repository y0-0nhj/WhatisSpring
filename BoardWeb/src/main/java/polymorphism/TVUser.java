package polymorphism;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {
	public static void main(String[] args) {
		// SamsungTv에서 LgTV로 바꾸게 되면 소스 코드를 전부 고쳐야 한다.
		// 결합도 높은 프로그램 -> 해결책 : 상속과 다형성을 활용
		// 상속과 다형성을 구현하여 소스코드의 일부부만 수정함으로써 결합도를 낮추기는 했지만, 
		// 소스코드를 변경해야 해서 결합도 없어진 것은 아님.
		// --> 해결책 : Spring 컨테이너를 사용함.
		
		// 1. 클래스의 객체를 직접 생성 -> 결합도 높음
		// 2. 상속과 다형성을 구현 -> 소스코드를 고쳐야 하므로 결합도 높음
		/*
		TV tv = new LgTV();
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		*/
		
		// 3. Factory 디자인 패턴을 직접 만들어 사용 -> 소스코드를 고치지 않음. 결합도 낮춤.
		/*
		BeanFactory factory = new BeanFactory();
		TV tv = (TV)factory.getBean(args[0]);
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		*/
		
		// 4. 스프링 컨테이너를 사용
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext2.xml");
		
		TV tv = (TV)factory.getBean("tv");
		tv.powerOn();
		
		
		tv.volumeUp();
		
		tv.volumeDown();
		tv.powerOff();
		
//		TV tv1 = (TV)factory.getBean("tv");
//		TV tv2 = (TV)factory.getBean("tv");
//		TV tv3 = (TV)factory.getBean("tv");
		
		factory.close();
	}

}
