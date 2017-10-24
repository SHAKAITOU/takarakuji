package sha.framework.db;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(DBConfig.DB2_YML_SOURCE_ROOT_NAME)
public class DB2Properties {
	
	public DataSource getDataSource() {
		return DataSourceBuilder.create().build();
	}

}
