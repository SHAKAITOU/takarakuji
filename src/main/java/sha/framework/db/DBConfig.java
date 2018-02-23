package sha.framework.db;

public class DBConfig {

	public static final String DOMAIN_YML_SOURCE_ROOT_NAME = "spring.domain";
	public static final String DOMAIN_YML_SOURCE_NAME = DOMAIN_YML_SOURCE_ROOT_NAME+".datasource";
	public static final String DOMAIN_SOURCE_BEAN_NAME = "domainDataSource";
	public static final String DOMAIN_MAPPER_PACKAGE = "sha.work.mapper.domain";
	public static final String DOMAIN_SQLSESSION_BEAN_NAME = "domainSqlSessionTemplate";
	public static final String DOMAIN_MAPPER_XML_PATH = "classpath:sha/work/mapper/domain/*.xml";
	
	public static final String LOTO_YML_SOURCE_ROOT_NAME = "spring.loto";
	public static final String LOTO_YML_SOURCE_NAME = LOTO_YML_SOURCE_ROOT_NAME+".datasource";
	public static final String LOTO_SOURCE_BEAN_NAME = "lotoDataSource";
	public static final String LOTO_MAPPER_PACKAGE = "sha.work.mapper.loto";
	public static final String LOTO_SQLSESSION_BEAN_NAME = "lotoSqlSessionTemplate";
	public static final String LOTO_MAPPER_XML_PATH = "classpath:sha/work/mapper/loto/*.xml";
	
}
