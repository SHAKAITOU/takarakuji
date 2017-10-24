package sha.work.mapper.db1;

public class CommonSqlBuilder {
	
	public static final String SQL1  = "<if test=\"name != null\">and NAME = #{name} </if>";


	public static String getCommonSQL1() {
		StringBuffer sb = new StringBuffer();
		sb.append(SQL1);
		return sb.toString();
	}
}
