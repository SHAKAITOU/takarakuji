package sha.work.entity.out;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sha.work.dto.loto.MiniLoto;
import sha.work.entity.in.MiniLotoShowDataIn;

@Data
@EqualsAndHashCode(callSuper=false)
public class MiniLotoShowDataOut extends MiniLotoShowDataIn {

	private List<MiniLoto> miniLotoList;

}
