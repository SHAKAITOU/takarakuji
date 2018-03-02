package sha.work.mapper.loto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sha.work.dto.loto.Loto7;
import sha.work.entity.in.Loto7AnalysisP1In;
import sha.work.entity.in.Loto7ShowDataIn;
import sha.work.entity.query.NumberAndTurnsQuery;

@Mapper
public interface Loto7Mapper {

    Loto7 findByTurn(@Param("turn") int turn);

    List<Loto7> getAll();
    
    int getTotalCnt();
    
    List<Loto7> getPageList(Loto7ShowDataIn dataIn);

	List<NumberAndTurnsQuery> getNomalNumberSum(Loto7AnalysisP1In loto7AnalysisP1);
	
	List<NumberAndTurnsQuery> getBonusNumber1Sum(Loto7AnalysisP1In loto7AnalysisP1);
	
	List<NumberAndTurnsQuery> getBonusNumber2Sum(Loto7AnalysisP1In loto7AnalysisP1);	
	
	void save(Loto7 loto7);

}
