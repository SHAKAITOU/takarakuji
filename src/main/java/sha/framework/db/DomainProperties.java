package sha.framework.db;



import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(DBConfig.DOMAIN_YML_SOURCE_ROOT_NAME)
public class DomainProperties {
	
	public DataSource getDataSource() {
		return DataSourceBuilder.create().build();
	}


}
