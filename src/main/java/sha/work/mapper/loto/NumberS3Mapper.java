package sha.work.mapper.loto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sha.work.dto.loto.NumberS3;

@Mapper
public interface NumberS3Mapper {

    NumberS3 findByTurn(@Param("turn") int turn);

	void save(NumberS3 numberS3);

}
