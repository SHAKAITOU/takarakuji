package sha.framework.db;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(DBConfig.LOTO_YML_SOURCE_ROOT_NAME)
public class LotoProperties {
	
	public DataSource getDataSource() {
		return DataSourceBuilder.create().build();
	}

}
