package sha.framework.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import sha.framework.data.InputBaseData;
import sha.framework.data.OutputBaseData;
import sha.framework.util.LogCommonUtil;
import sha.work.exception.TKRKScreenException;

public abstract class BaseService<S extends InputBaseData, T extends OutputBaseData> implements IService<S, T> {

	@Autowired
	private LogCommonUtil log;
	
	@Transactional
    public void processMainLogic(S input, T output) throws TKRKScreenException {
		log.info("サービスパラメータデータ", "["+this.getClass().getName()+"]", input);
		this.preMainLogic(input, output);
		this.execMainLogic(input, output);
		this.postMainLogic(input, output);
		log.info("サービスレスポンスデータ", "["+this.getClass().getName()+"]", output);
    }

	@Transactional
    @Async("threadPoolTaskExecutor")
    public void processAsyncMainLogic(S input, T output) throws TKRKScreenException {
		log.info("サービスパラメータデータ", "["+this.getClass().getName()+"]", input);
		this.preMainLogic(input, output);
		this.execMainLogic(input, output);
		this.postMainLogic(input, output);
		log.info("サービスレスポンスデータ", "["+this.getClass().getName()+"]", output);
	}

}
