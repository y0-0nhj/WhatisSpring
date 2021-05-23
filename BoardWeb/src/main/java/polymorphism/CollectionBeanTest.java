package polymorphism;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionBeanTest {
	public static void main(String[] args) {
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext2.xml");
		
		// 1. List 처리
		/*
		CollectionBean bean = (CollectionBean)factory.getBean("collectionBean");
		List<String> addressList = bean.getAddressList();
		
		for(String address: addressList) {
			System.out.println(address);
		}
		*/
		
		// 2. Set 처리
		/*
		CollectionBean bean = (CollectionBean)factory.getBean("collectionBean");
		Set<String> addressSet = bean.getAddressSet();
		
		for(String address: addressSet) {
			System.out.println(address);
		}
		*/
		
		// 3. Map 처리
		/*
		CollectionBean bean = (CollectionBean)factory.getBean("collectionBean");
		Map<String, String> addressMap = bean.getAddressMap();
		Set<String> names = addressMap.keySet();
		Iterator<String> it = names.iterator();
		
		while(it.hasNext()) {
			String name = it.next();
			String address = addressMap.get(name);
			System.out.println(name + " : " + address);
		}
		*/
		
		// 4. Properties 처리
		CollectionBean bean = (CollectionBean)factory.getBean("collectionBean");
		Properties addressProp = bean.getAddressProp();
		Set<?> names = addressProp.keySet();
		Iterator<?> it = names.iterator();
		
		while(it.hasNext()) {
			String name = (String)it.next();
			String address = addressProp.getProperty(name);
			System.out.println(name + " : " + address);
		}
		
		factory.close();
	}
}
