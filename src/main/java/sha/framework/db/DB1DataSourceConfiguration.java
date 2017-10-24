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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@MapperScan(basePackages = DBConfig.DB1_MAPPER_PACKAGE, sqlSessionTemplateRef = DBConfig.DB1_SQLSESSION_BEAN_NAME)
public class DB1DataSourceConfiguration {
	
	@Autowired
	private DB1Properties db1Properties;
	
	@Autowired
	private ApplicationContext context;
 
	@Bean(name = { DBConfig.DB1_SOURCE_BEAN_NAME })
	@ConfigurationProperties(prefix = DBConfig.DB1_YML_SOURCE_NAME)
	@Primary
	public DataSource dataSource() {
		return db1Properties.getDataSource();
	}
	
	@Bean(name = DBConfig.DB1_SQLSESSION_BEAN_NAME)
	@Primary
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier(DBConfig.DB1_SOURCE_BEAN_NAME) DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		// MyBatis のコンフィグレーションファイル
		//bean.setConfigLocation(context.getResource(context.getEnvironment().getProperty("mybatis.config")));
		ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(
                new DefaultResourceLoader());

		bean.setMapperLocations(resolver.getResources(DBConfig.DB1_MAPPER_XML_PATH));
		return new SqlSessionTemplate(bean.getObject());
	}
 
	@Bean(name = "db1DataSourceInitializer")
	@Primary
	public DataSourceInitializer dataSourceInitializer(@Qualifier(DBConfig.DB1_SOURCE_BEAN_NAME) DataSource dataSource) {
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
