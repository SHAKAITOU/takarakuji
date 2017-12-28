package sha.work.controller.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sha.framework.controller.ScreenBaseController;
import sha.framework.util.UserInfoUtil;
import sha.work.controller.UrlPathDefine;
import sha.work.data.S001InputData;
import sha.work.data.S001OutputData;

@Controller
@RequestMapping(path = UrlPathDefine.CUSTOMER_INIT, method=RequestMethod.GET)
public class CustomerController extends ScreenBaseController{

	@Autowired
	private UserInfoUtil userInfoUtil;


	@ResponseBody
	public S001OutputData exapmle(@RequestParam String name, @RequestParam String address)  {

		S001InputData input = new S001InputData();
		input.setName(name);
		input.setAddress(address);
		
		input = (S001InputData)userInfoUtil.initInfo(1, input);

		S001OutputData output = new S001OutputData();
		output.setComment(name + ":" + address);
		output.setS001InputData(input);
		
		return output;
	}




}
