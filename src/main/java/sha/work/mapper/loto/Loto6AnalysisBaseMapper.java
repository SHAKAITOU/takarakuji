package sha.work.mapper.loto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sha.work.dto.loto.Loto6AnalysisBase;
import sha.work.entity.query.NumberAndTurnsQuery;

@Mapper
public interface Loto6AnalysisBaseMapper {
	
	int isExist(@Param("turn") int turn);
	
	List<NumberAndTurnsQuery> summaryTotalAvg();
	
	List<NumberAndTurnsQuery> summaryMaxNumDiff();
	
	List<NumberAndTurnsQuery> summaryMinNumDiff();
	
	List<NumberAndTurnsQuery> summaryMaxMinDiff();
	
	List<NumberAndTurnsQuery> summaryNumDiffAvg();
	
	List<NumberAndTurnsQuery> summaryEvenNumCnt();
	
	List<NumberAndTurnsQuery> summaryOddNumCnt();
	
	List<NumberAndTurnsQuery> summarySerialNumCnt();
	
	List<NumberAndTurnsQuery> summaryLeftAreaNumCnt();
	
	List<NumberAndTurnsQuery> summaryCenterAreaNumCnt();
	
	List<NumberAndTurnsQuery> summaryRightAreaNumCnt();

	void save(Loto6AnalysisBase analysisBase);
	
	void update(Loto6AnalysisBase analysisBase);

}
