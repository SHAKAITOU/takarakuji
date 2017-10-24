package sha.framework.data;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;
import sha.framework.message.ProcessMessage;

/**
 * アウトプットデータのベースクラス
 *
 * @author 000001A006PT9
 *
 */
@Getter
@Setter
public class OutputBaseData {

	private ResultCode resultCode;

	private HttpStatus httpStatus;

	private ProcessMessage message;
}
