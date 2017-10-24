package sha.work.data;

import lombok.Getter;
import lombok.Setter;
import sha.framework.data.OutputBaseData;

@Getter
@Setter
public class S001OutputData extends OutputBaseData {
	private String comment;
	
	private String msg1;
	
	private S001InputData s001InputData;
}
