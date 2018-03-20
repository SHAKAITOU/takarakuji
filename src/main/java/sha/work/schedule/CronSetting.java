package sha.work.schedule;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
 
@Getter
@Setter
@Component
@ConfigurationProperties(prefix="cron")
public class CronSetting {
	private String loto7Cron;
	private String loto6Cron;
}
