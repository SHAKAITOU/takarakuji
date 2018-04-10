
package sha.framework.log.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * the configuration of log.
 * @author Fast Retailing
 * @version $Revision$
 */
@Component
@Configuration
@ComponentScan(basePackages = {"sha.framework.log"})
public class LogConfiguration {
}
