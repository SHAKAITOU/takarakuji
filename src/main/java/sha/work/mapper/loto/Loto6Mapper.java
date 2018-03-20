package sha.work.mapper.loto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sha.work.dto.loto.Loto6;
import sha.work.entity.in.Loto6AnalysisP1In;
import sha.work.entity.in.Loto6ShowDataIn;
import sha.work.entity.query.Loto6SixQuery;
import sha.work.entity.query.NumberAndTurnsQuery;

@Mapper
public interface Loto6Mapper {

    Loto6 findByTurn(@Param("turn") int turn);
    
    Loto6 findLastTurn();
    
    List<Loto6> getAll();
    
    int isExist(@Param("turn") int turn);
    
    int getTotalCnt();
    
    List<Loto6> getPageList(Loto6ShowDataIn dataIn);

	List<NumberAndTurnsQuery> getNomalNumberSum(Loto6AnalysisP1In loto6AnalysisP1);
	
	List<NumberAndTurnsQuery> getBonusNumber1Sum(Loto6AnalysisP1In loto6AnalysisP1);

	List<Loto6SixQuery> getSixSum(@Param("hanyoId") int hanyoId);
	
	void save(Loto6 loto6);

	void update(Loto6 lot6);
}
