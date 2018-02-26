package sha.work.mapper.loto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sha.work.dto.loto.NumberS4;

@Mapper
public interface NumberS4Mapper {

    NumberS4 findByTurn(@Param("turn") int turn);

	void save(NumberS4 numberS4);

}
