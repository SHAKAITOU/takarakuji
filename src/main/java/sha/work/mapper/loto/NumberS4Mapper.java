package sha.work.mapper.loto;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import sha.work.dto.loto.NumberS4;
import sha.work.dto.loto.def.NumberS4Def;

@Mapper
public interface NumberS4Mapper extends NumberS4Def {

	@Select("SELECT * FROM " + TABLE + " WHERE TURN = #{turn}")
    NumberS4 findByTurn(@Param("turn") int turn);

	
	@Insert("INSERT INTO " 
	        + TABLE 
	        + " ( "
            + " " + TURN
            + ", " + OPEN_DT
            + ", " + L1
            + ", " + L2
            + ", " + L3
            + ", " + L4
            + " ) "
	        + " VALUES ("
	        + " #{turn}"
	        + ", #{openDt}"
	        + ", #{l1}"
	        + ", #{l2}"
	        + ", #{l3}"
	        + ", #{l4})")
	void save(NumberS4 numberS4);

}
