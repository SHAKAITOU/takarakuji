package sha.work.mapper.loto;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import sha.work.dto.domain.Hanyo;
import sha.work.dto.domain.def.HanyoDef;

@Mapper
public interface HanyoMapper extends HanyoDef {

	@Select("SELECT * FROM " + TABLE + " WHERE id = #{id}")
    List<Hanyo> find(@Param("id") int id);

	
	@Insert("INSERT INTO " 
	        + TABLE 
	        + " ( "
            + " " + ID
            + ", " + CODE
            + ", " + NAME
            + ", " + VALUE
            + " ) "
	        + " VALUES ("
	        + " #{id}"
	        + ", #{code}"
	        + ", #{name}"
	        + ", #{value})")
	void save(Hanyo hanyo);
	

}
