package sha.work.entity.out;

import java.util.List;

import lombok.Data;
import sha.work.entity.query.Loto7AnalysisP1Query;
import sha.work.entity.query.NumberAndTurnsQuery;

@Data
public class Loto7AnalysisP2Out {
	List<NumberAndTurnsQuery> summaryTotalAvgList;
}
