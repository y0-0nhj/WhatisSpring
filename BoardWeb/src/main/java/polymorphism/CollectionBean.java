package polymorphism;

import java.util.Map;
import java.util.Properties;

public class CollectionBean {
	
	// 1. List인 경우
	/*
	private List<String> addressList;
	
	// 세터 인젝션을 하기 위한 세터 메소드
	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;
	}

	public List<String> getAddressList() {
		return addressList;
	}
	*/
	
	// 2. Set인 경우 
	/*
	private Set<String> addressSet;
	
	// 세터 인젝션을 하기 위한 세터 메소드
	public void setAddressSet(Set<String> addressSet) {
		this.addressSet = addressSet;
	}

	public Set<String> getAddressSet() {
		return addressSet;
	}
	*/
	
	// 3. Map인 경우
	/*
	private Map<String, String> addressMap;
	
	// 세터 인젝션을 하기 위한 세터 메소드
	public void setAddressMap(Map<String, String> addressMap) {
		this.addressMap = addressMap;
	}

	public Map<String, String> getAddressMap() {
		return addressMap;
	}
	*/
	
	// 4. Properties인 경우
	private Properties addressProp;
	
	// 세터 인젝션을 하기 위한 세터 메소드
	public void setAddressProp(Properties addressProp) {
		this.addressProp = addressProp;
	}

	public Properties getAddressProp() {
		return addressProp;
	}
}
