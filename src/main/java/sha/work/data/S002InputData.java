package sha.work.data;

import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import sha.framework.data.InputBaseData;


@Getter
@Setter
public class S002InputData extends InputBaseData{
	
    private long id;
    @Pattern(regexp = "[0-9]*")
    private String content;
}
