package sha.work.mapper.db2;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import sha.work.mapper.domain.User;

@Mapper
public interface User2Mapper {

	@Select("SELECT * FROM USERS WHERE NAME = #{name}")
    User findByName(@Param("name") String name);
	
	@Select("SELECT * FROM USERS WHERE ID = #{id}")
    User findById(@Param("id") int id);
	
	@Insert("INSERT INTO USERS VALUES (#{id}, #{name})")
	void save(@Param("id") int id, @Param("name") String name);
	
	@Insert("INSERT INTO USER VALUES (#{id}, #{name})")
	void saveError(@Param("id") int id, @Param("name") String name);

}
