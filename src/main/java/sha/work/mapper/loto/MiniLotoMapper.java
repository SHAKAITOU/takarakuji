package sha.work.mapper.loto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sha.work.dto.loto.MiniLoto;

@Mapper
public interface MiniLotoMapper {

    MiniLoto findByTurn(@Param("turn") int turn);

	void save(MiniLoto miniLoto);

}
