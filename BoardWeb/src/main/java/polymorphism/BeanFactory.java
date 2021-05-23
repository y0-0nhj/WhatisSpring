package polymorphism;

// Factory 디자인 패턴 - 빈(인스턴스)을 만들어 리턴하는 디자인 패턴
public class BeanFactory {
	
	public Object getBean(String beanName) {
		if(beanName.equals("samsung")) return new SamsungTV();
		else if(beanName.equals("lg")) return new LgTV();
		else return null;
	}

}
