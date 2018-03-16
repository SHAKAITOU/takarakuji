package sha.work.entity.out;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sha.work.dto.loto.Loto7;
import sha.work.entity.in.Loto7EstimateDataIn;

@Data
@EqualsAndHashCode(callSuper=false)
public class Loto7EstimateDataOut extends Loto7EstimateDataIn {

	private List<Loto7> loto7List;
	

}
