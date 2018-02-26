package sha.work.mapper.loto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sha.work.dto.loto.Loto7;
import sha.work.entity.loto.Loto7AnalysisP1;
import sha.work.entity.loto.NumberAndTurns;

@Mapper
public interface Loto7Mapper {

    Loto7 findByTurn(@Param("turn") int turn);
    
    Loto7 getNomalNumbers(@Param("turn") int turn);
    
    List<Loto7> getAll();

	List<NumberAndTurns> getNomalNumberSum(Loto7AnalysisP1 loto7AnalysisP1);
	
	List<NumberAndTurns> getBonusNumber1Sum(Loto7AnalysisP1 loto7AnalysisP1);
	
	List<NumberAndTurns> getBonusNumber2Sum(Loto7AnalysisP1 loto7AnalysisP1);	
	
	void save(Loto7 loto7);

}
