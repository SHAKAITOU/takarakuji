package sha.work.mapper.loto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sha.work.dto.loto.Loto6;

@Mapper
public interface Loto6Mapper {

    Loto6 findByTurn(@Param("turn") int turn);

	
	void save(Loto6 loto6);

}
