package sha.work.entity.out;

import java.util.List;

import lombok.Data;
import sha.work.dto.loto.Loto7;

@Data
public class Loto7ShowDataOut {
	private int pageCn;
	private int pageIdx;
	private List<Loto7> loto7List;
}
