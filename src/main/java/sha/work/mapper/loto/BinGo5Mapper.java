package sha.work.mapper.loto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sha.work.dto.loto.BinGo5;

@Mapper
public interface BinGo5Mapper {

	BinGo5 findByTurn(@Param("turn") int turn);

	
	void save(BinGo5 binGo5);

}
