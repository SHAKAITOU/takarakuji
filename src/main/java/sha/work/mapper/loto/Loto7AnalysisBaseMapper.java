package sha.work.mapper.loto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sha.work.dto.loto.Loto7AnalysisBase;

@Mapper
public interface Loto7AnalysisBaseMapper {
	
	int isExist(@Param("turn") int turn);

	void save(Loto7AnalysisBase analysisBase);
	
	void update(Loto7AnalysisBase analysisBase);

}
