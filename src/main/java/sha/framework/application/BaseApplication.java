package sha.framework.application;

import java.util.concurrent.Executor;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import sha.framework.db.DomainProperties;
import sha.framework.db.LotoProperties;
import sha.framework.handler.CustomerLocaleResolver;
import sha.framework.util.DateCommonUtil;
import sha.framework.util.JsonLogCommonUtil;
import sha.framework.util.LogCommonUtil;
import sha.framework.util.MessageSourceUtil;
import sha.framework.util.StringCommonUtil;
import sha.framework.util.UserInfoUtil;
/**
 * コマースアプリケーションクラス
 *
 * @author 000001A006PT9
 *
 */
@ComponentScan(basePackages="sha.*")
//@MapperScan(basePackages="com.nec.jp.commercepf.example.*")
@EnableAsync(proxyTargetClass=true)
@EnableConfigurationProperties({DataSourceProperties.class, DomainProperties.class, LotoProperties.class})
public class BaseApplication extends WebMvcConfigurerAdapter {
	
	@Bean
	public LocaleResolver localeResolver() {
	    return new CustomerLocaleResolver();
	}

	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	    lci.setParamName("lang");
	    return lci;
	}
	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(localeChangeInterceptor());
	}
	
	
	@Bean
	public LogCommonUtil getLogCommonUtil() {
		return new LogCommonUtil();
	}
	
	@Bean
	public StringCommonUtil getStringCommonUtil() {
		return new StringCommonUtil();
	}
	
	@Bean
	public MessageSourceUtil getResourceUtil() {
		return new MessageSourceUtil();
	}
	
	@Bean
	public UserInfoUtil getUserInfoUtil() {
		return new UserInfoUtil();
	}
	
	@Bean
	public DateCommonUtil getDateCommonUtil() {
		return new DateCommonUtil();
	}
	
	@Bean
	public JsonLogCommonUtil getJsonLogCommonUtil() {
		return new JsonLogCommonUtil();
	}

	/**
	 * 非同期処理のスレッドプールの作成
	 *
	 * @return スレッドプールの実行部品
	 */
	@Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {

		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // 並列処理数を 5 に制限
        executor.setMaxPoolSize(10); //
        executor.setQueueCapacity(1000); // 非同期処理を待つキューの長さを 500 までに制限
        executor.setKeepAliveSeconds(60);
        executor.setAllowCoreThreadTimeOut(true);

        return executor;
    }
}
