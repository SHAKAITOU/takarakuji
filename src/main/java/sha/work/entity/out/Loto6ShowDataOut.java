package sha.work.entity.out;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sha.work.dto.loto.Loto6;
import sha.work.entity.in.Loto6ShowDataIn;

@Data
@EqualsAndHashCode(callSuper=false)
public class Loto6ShowDataOut extends Loto6ShowDataIn {

	private List<Loto6> loto6List;

}
