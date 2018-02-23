package sha.work.mapper.loto;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import sha.work.dto.loto.NumberS3;
import sha.work.dto.loto.def.NumberS3Def;

@Mapper
public interface NumberS3Mapper extends NumberS3Def {

	@Select("SELECT * FROM " + TABLE + " WHERE TURN = #{turn}")
    NumberS3 findByTurn(@Param("turn") int turn);

	
	@Insert("INSERT INTO " 
	        + TABLE 
	        + " ( "
            + " " + TURN
            + ", " + OPEN_DT
            + ", " + L1
            + ", " + L2
            + ", " + L3
            + " ) "
	        + " VALUES ("
	        + " #{turn}"
	        + ", #{openDt}"
	        + ", #{l1}"
	        + ", #{l2}"
	        + ", #{l3})")
	void save(NumberS3 numberS3);

}
