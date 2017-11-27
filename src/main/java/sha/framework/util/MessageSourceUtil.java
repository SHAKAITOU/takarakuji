package sha.framework.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class MessageSourceUtil {
	

	@Autowired 
	private MessageSource msg;
	
//	private static MessageUtil instance;
//	@PostConstruct
//    public void registerInstance() {
//        instance = this;
//    }
	public String getContext(String key) {		
		return msg.getMessage(key, null, Locale.getDefault());
	}
	
	public String getContext(String key, String... args) {		
		return msg.getMessage(key, args, Locale.getDefault());
	}
	
	public String getContext(Locale location, String key, String... args) {		
		return msg.getMessage(key, args, location);
	}
}
