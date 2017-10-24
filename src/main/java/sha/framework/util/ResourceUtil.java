package sha.framework.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class ResourceUtil {
	

	@Autowired 
	private MessageSource msg;
	
//	private static MessageUtil instance;
//	@PostConstruct
//    public void registerInstance() {
//        instance = this;
//    }

	
	public String getContext(Locale location, String key, String... args) {		
		return msg.getMessage(key, args, location);
	}
	
	
}
