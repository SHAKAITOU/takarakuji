/**
 * @(#)LogConfiguration.java
 *
 * Copyright (c) 2017 Fast Retailing Corporation.
 */

package sha.framework.util.log;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * the configuration of log.
 * @author Fast Retailing
 * @version $Revision$
 */
@Component
@Configuration
@ComponentScan(basePackages = {"com.fastretailing.dcp.storecommon.log.api"})
public class LogConfiguration {

    /**
     * Common request logging filter.
     * @return APIRequestResponseLoggingFilter
     */
    @Bean
    public CommonsRequestLoggingFilter commonsRequestLoggingFilter() {
        CommonsRequestLoggingFilter filter = new ApiRequestResponseLoggingFilter();
        filter.setIncludeClientInfo(true);
        filter.setIncludeQueryString(true);
        filter.setIncludeHeaders(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(1024);
        return filter;
    }
}
