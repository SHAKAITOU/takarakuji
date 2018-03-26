package sha.work.mapper.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import sha.work.dto.domain.User;

@Mapper
public interface UserMapper {

	
	@Select("SELECT * FROM D_USERS WHERE NAME = #{name}")
    User getByName(@Param("name") String name);
	
	@Select("SELECT * FROM D_USERS WHERE ID = #{id}")
    User getById(@Param("id") int id);
	
	@Select("SELECT * FROM D_USERS WHERE CODE = #{code}")
	User getByCode(@Param("code") String code);
	
	@Insert("INSERT INTO D_USERS "
			+ "("
			+ "ID"
			+ ",CODE"
			+ ",NAME"
			+ ",PW"
			+ ",VALID"
			+ ",ROLE"
			+ ")"
			+ "VALUES ("
			+ "#{id} "
			+ ",#{name}"
			+ ",#{code}"
			+ ",#{pw}"
			+ ",#{valid}"
			+ ",#{role}"
			+ ")")
	void save(User user);

	
	//@SelectProvider(type = CommonSqlBuilder.class, method = "buildGetUsersByName")
	//User getByNameLoad(String name);
	
	
}
