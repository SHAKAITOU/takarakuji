package sha.framework.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessMessage {

	private String messageCode;

	private String messageContent;

	private MessageLevel messageLevel;

	private String relationItem;
}
