package sha.work.mapper.loto;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import sha.work.dto.loto.BinGo5;
import sha.work.dto.loto.def.BinGo5Def;

@Mapper
public interface BinGo5Mapper extends BinGo5Def {

	@Select("SELECT * FROM " + TABLE + " WHERE TURN = #{turn}")
	BinGo5 findByTurn(@Param("turn") int turn);

	
	@Insert("INSERT INTO " 
	        + TABLE 
	        + " ( "
            + " " + TURN
            + ", " + OPEN_DT
            + ", " + L1
            + ", " + L2
            + ", " + L3
            + ", " + L4
            + ", " + L5
            + ", " + L6
            + ", " + L7
            + ", " + L8
            + " ) "
	        + " VALUES ("
	        + " #{turn}"
	        + ", #{openDt}"
	        + ", #{l1}"
	        + ", #{l2}"
	        + ", #{l3}"
	        + ", #{l4}"
	        + ", #{l5}"
	        + ", #{l6}"
	        + ", #{l7}"
	        + ", #{l8})")
	void save(BinGo5 binGo5);

}
