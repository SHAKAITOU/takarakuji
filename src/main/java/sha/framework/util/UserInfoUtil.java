package sha.framework.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import sha.framework.data.InputBaseData;
import sha.work.mapper.db1.UserMapper;
import sha.work.mapper.domain.User;

public class UserInfoUtil {

    @Autowired
    private UserMapper mapper;
	
	public InputBaseData initInfo(int userId, InputBaseData inputBaseData) {
		User user = mapper.findById(userId);
		inputBaseData.setUserId(userId);
		inputBaseData.setUserName(user.getName());
//		inputBaseData.setShopDefaultLang(Locale.JAPAN);
//		inputBaseData.setCurrentLang(Locale.JAPAN);

		return inputBaseData;
	}
	
	public InputBaseData setUserInfo(Map<String,String> allRequestParams, InputBaseData inputBaseData) {
		inputBaseData.setUserId(Integer.parseInt(allRequestParams.get("userId")));
		inputBaseData.setUserName(allRequestParams.get("userName"));
//		inputBaseData.setShopDefaultLang(Locale.JAPAN);
//		inputBaseData.setCurrentLang(Locale.JAPAN);
		return inputBaseData;
	}
}
