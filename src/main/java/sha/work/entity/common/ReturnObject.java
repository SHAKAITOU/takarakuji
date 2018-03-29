package sha.work.entity.common;

import lombok.Data;

@Data
public class ReturnObject {

	private int returnType;
	
	private String returnMsg;
	
	private Object returnObj;
}
