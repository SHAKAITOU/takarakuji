package sha.work.controller;

import org.springframework.web.bind.annotation.RequestMethod;

/**
 * リザルトコードのEnumクラス。
 */
public class UrlPathDefine {

	/**	CUSTOMER_INIT */
	public static final String CUSTOMER_INIT = "/customer/init";
	
	public static final RequestMethod CUSTOMER_INIT_METHOD = RequestMethod.GET;

	/** CUSTOMER_SEARCH */
	public static final String CUSTOMER_SEARCH = "/customer/search";

	
}
