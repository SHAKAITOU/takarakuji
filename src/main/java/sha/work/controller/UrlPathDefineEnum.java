package sha.work.controller;

import org.springframework.web.bind.annotation.RequestMethod;


/**
 * リザルトコードのEnumクラス。
 */
public enum UrlPathDefineEnum {

	/**	CUSTOMER_INIT */
	CUSTOMER_INIT("/customer/init", "get");
	
	private String value;
	
	private String method;

	private UrlPathDefineEnum(String value, String method) {
	  this.value = value;
	  this.method = method;
	}
	
	public RequestMethod getMethod() {
	  return RequestMethod.valueOf(this.method);
	}
	
	public String getValue() {
	  return this.value;
	}
}
