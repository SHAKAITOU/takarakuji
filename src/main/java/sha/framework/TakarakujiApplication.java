package sha.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import sha.framework.application.BaseApplication;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TakarakujiApplication extends BaseApplication{

	public static void main(String[] args) {
		SpringApplication.run(TakarakujiApplication.class, args);
	}
	
}
