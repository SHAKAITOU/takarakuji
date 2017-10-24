package sha.framework.db;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@MapperScan(basePackages = DBConfig.DB2_MAPPER_PACKAGE, sqlSessionTemplateRef = DBConfig.DB2_SQLSESSION_BEAN_NAME)
public class DB2DataSourceConfiguration {
	@Autowired
	private DB2Properties db2Properties;
	
	@Autowired
	private ApplicationContext context;

 
	@Bean(name = { DBConfig.DB2_SOURCE_BEAN_NAME })
	@ConfigurationProperties(prefix = DBConfig.DB2_YML_SOURCE_NAME)
	public DataSource dataSource() {
		return db2Properties.getDataSource();
	}
	
	@Bean(name = DBConfig.DB2_SQLSESSION_BEAN_NAME)
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier(DBConfig.DB2_SOURCE_BEAN_NAME) DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		// MyBatis のコンフィグレーションファイル
		//bean.setConfigLocation(context.getResource(context.getEnvironment().getProperty("mybatis.config")));
		ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(
                new DefaultResourceLoader());
		bean.setMapperLocations(resolver.getResources(DBConfig.DB2_MAPPER_XML_PATH));
		return new SqlSessionTemplate(bean.getObject());
	}
 
	@Bean(name = "db2DataSourceInitializer")
	public DataSourceInitializer dataSourceInitializer(@Qualifier(DBConfig.DB2_SOURCE_BEAN_NAME) DataSource dataSource) {
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSource);
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
// schema,dataをpropertiesファイルで指定しない場合、Location must not be nullが出るため、nullの場合は処理を行わないように修正(2016/06/28)
//		databasePopulator.addScripts(
//				context.getResource(otherProperties.getDatasource().getSchema()),
//				context.getResource(otherProperties.getDatasource().getData()));
//		Optional.ofNullable(db2Properties.getDatasource().getSchema())
//			.map(schema -> context.getResource(schema))
//			.ifPresent(resource -> databasePopulator.addScript(resource));
//		Optional.ofNullable(db2Properties.getDatasource().getData())
//			.map(data -> context.getResource(data))
//			.ifPresent(resource -> databasePopulator.addScript(resource));
 
		dataSourceInitializer.setDatabasePopulator(databasePopulator);
		dataSourceInitializer.setEnabled(true);
 
		return dataSourceInitializer;
	}

}
