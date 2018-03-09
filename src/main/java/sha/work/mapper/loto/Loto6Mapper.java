package sha.work.mapper.loto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sha.work.dto.loto.Loto6;
import sha.work.entity.in.Loto6AnalysisP1In;
import sha.work.entity.in.Loto6ShowDataIn;
import sha.work.entity.query.NumberAndTurnsQuery;

@Mapper
public interface Loto6Mapper {

    Loto6 findByTurn(@Param("turn") int turn);
    List<Loto6> getAll();
    
    int getTotalCnt();
    
    List<Loto6> getPageList(Loto6ShowDataIn dataIn);

	List<NumberAndTurnsQuery> getNomalNumberSum(Loto6AnalysisP1In loto6AnalysisP1);
	
	List<NumberAndTurnsQuery> getBonusNumber1Sum(Loto6AnalysisP1In loto6AnalysisP1);

	void save(Loto6 loto6);

}
