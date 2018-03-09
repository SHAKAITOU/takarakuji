package sha.work.mapper.loto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sha.work.dto.loto.MiniLoto;
import sha.work.entity.in.MiniLotoAnalysisP1In;
import sha.work.entity.in.MiniLotoShowDataIn;
import sha.work.entity.query.NumberAndTurnsQuery;

@Mapper
public interface MiniLotoMapper {

    MiniLoto findByTurn(@Param("turn") int turn);
    List<MiniLoto> getAll();
    
    int getTotalCnt();
    
    List<MiniLoto> getPageList(MiniLotoShowDataIn dataIn);

	List<NumberAndTurnsQuery> getNomalNumberSum(MiniLotoAnalysisP1In miniLotoAnalysisP1);
	
	List<NumberAndTurnsQuery> getBonusNumber1Sum(MiniLotoAnalysisP1In miniLotoAnalysisP1);

	void save(MiniLoto miniLoto);

}
