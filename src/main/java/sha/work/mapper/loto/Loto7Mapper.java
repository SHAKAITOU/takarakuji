package sha.work.mapper.loto;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import sha.work.dto.loto.Loto7;
import sha.work.dto.loto.def.Loto7Def;
import sha.work.entity.loto.Loto7AnalysisP1;
import sha.work.entity.loto.NumberAndTurns;

@Mapper
public interface Loto7Mapper extends Loto7Def {

	@Select("SELECT * FROM " + TABLE + " WHERE TURN = #{turn}")
    Loto7 findByTurn(@Param("turn") int turn);


	List<NumberAndTurns> getNomalNumberSum(Loto7AnalysisP1 loto7AnalysisP1);
	
	List<NumberAndTurns> getBonusNumber1Sum(Loto7AnalysisP1 loto7AnalysisP1);
	
	List<NumberAndTurns> getBonusNumber2Sum(Loto7AnalysisP1 loto7AnalysisP1);
	
	
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
            + ", " + B1
            + ", " + B2
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
	        + ", #{b1}"
	        + ", #{b2})")
	void save(Loto7 loto7);

}
