package sha.work.data;

import lombok.Getter;
import lombok.Setter;
import sha.framework.data.InputBaseData;
import sha.framework.data.OutputBaseData;
import sha.work.mapper.domain.User;

@Getter
@Setter
public class S002OutputData extends OutputBaseData {
	private User user;
	
	private String msg1;
	private String msg2;
	private String msg3;
	
	private InputBaseData inputBaseData;
	
	private String localProp;
}
