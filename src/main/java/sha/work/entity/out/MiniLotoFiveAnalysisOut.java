package sha.work.entity.out;

import java.util.List;

import lombok.Data;
import sha.work.entity.query.MiniLotoFiveQuery;

@Data
public class MiniLotoFiveAnalysisOut {
	List<MiniLotoFiveQuery> fiveList;
}
