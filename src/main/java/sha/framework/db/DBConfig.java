package sha.framework.db;

public class DBConfig {

	public static final String DB1_YML_SOURCE_ROOT_NAME = "spring.db1";
	public static final String DB1_YML_SOURCE_NAME = DB1_YML_SOURCE_ROOT_NAME+".datasource";
	public static final String DB1_SOURCE_BEAN_NAME = "db1DataSource";
	public static final String DB1_MAPPER_PACKAGE = "sha.work.mapper.db1";
	public static final String DB1_SQLSESSION_BEAN_NAME = "db1SqlSessionTemplate";
	public static final String DB1_MAPPER_XML_PATH = "classpath:sha/work/mapper/db1/*.xml";
	
	public static final String DB2_YML_SOURCE_ROOT_NAME = "spring.db2";
	public static final String DB2_YML_SOURCE_NAME = DB2_YML_SOURCE_ROOT_NAME+".datasource";
	public static final String DB2_SOURCE_BEAN_NAME = "db2DataSource";
	public static final String DB2_MAPPER_PACKAGE = "sha.work.mapper.db2";
	public static final String DB2_SQLSESSION_BEAN_NAME = "db2SqlSessionTemplate";
	public static final String DB2_MAPPER_XML_PATH = "classpath:sha/work/mapper/db2/*.xml";
	
}
