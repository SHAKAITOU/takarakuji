package sha.work.mapper.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import sha.work.dto.domain.User;

@Mapper
public interface UserMapper {
	
	static final String SQL1    = "<script>"
									+ " SELECT * FROM USERS WHERE 1 = 1  "
									+ " <if test=\"name != null\">and NAME = #{name} </if>"
									+ "</script>";

	
	@Select("SELECT * FROM USERS WHERE NAME = #{name}")
    User findByName(@Param("name") String name);
	
	@Select("SELECT * FROM USERS WHERE ID = #{id}")
    User findById(@Param("id") int id);
	
	@Insert("INSERT INTO USERS VALUES (#{id}, #{name})")
	void save(@Param("id") int id, @Param("name") String name);
	
	@Insert("INSERT INTO USER VALUES (#{id}, #{name})")
	void saveError(@Param("id") int id, @Param("name") String name);
	
	//@SelectProvider(type = CommonSqlBuilder.class, method = "buildGetUsersByName")
	//User getByNameLoad(String name);
	
	@Select(SQL1)
	User getByName(@Param("name") String name);
	
}
