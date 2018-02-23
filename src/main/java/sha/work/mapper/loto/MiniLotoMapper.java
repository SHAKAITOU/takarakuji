package sha.work.mapper.loto;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import sha.work.dto.loto.MiniLoto;
import sha.work.dto.loto.def.MiniLotoDef;

@Mapper
public interface MiniLotoMapper extends MiniLotoDef {

	@Select("SELECT * FROM " + TABLE + " WHERE TURN = #{turn}")
    MiniLoto findByTurn(@Param("turn") int turn);

	
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
	        + ", #{b1})")
	void save(MiniLoto miniLoto);

}
