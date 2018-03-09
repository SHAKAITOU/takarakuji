package sha.work.entity.out;

import java.util.List;

import lombok.Data;
import sha.work.entity.query.MiniLotoAnalysisP1Query;

@Data
public class MiniLotoAnalysisP1Out {
	private List<MiniLotoAnalysisP1Query> p1List;
}
