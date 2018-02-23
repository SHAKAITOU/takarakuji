package sha.work.mapper.loto;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import sha.work.dto.loto.Loto6;
import sha.work.dto.loto.def.Loto6Def;

@Mapper
public interface Loto6Mapper extends Loto6Def {

	@Select("SELECT * FROM " + TABLE + " WHERE TURN = #{turn}")
    Loto6 findByTurn(@Param("turn") int turn);

	
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
            + ", " + B1
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
	        + ", #{b1})")
	void save(Loto6 loto6);

}
