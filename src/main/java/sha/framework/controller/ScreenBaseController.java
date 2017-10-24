package sha.framework.controller;


import org.springframework.beans.factory.annotation.Autowired;

import sha.framework.data.InputBaseData;
import sha.framework.data.OutputBaseData;
import sha.framework.service.BaseService;
import sha.framework.util.LogCommonUtil;
import sha.work.exception.TKRKScreenException;


public abstract class ScreenBaseController<S extends InputBaseData, T extends OutputBaseData> extends BaseController<S, T> {

	@Autowired
	private LogCommonUtil log;

    protected void execService(S input, T output, BaseService<S, T> service, String... param ) throws TKRKScreenException {

    	validate(input, output);
    	super.EditCommonItem(input, output);
    	EditScreenCommonItem(input, output);

	    if (service != null) {
	    	service.processMainLogic(input, output);
	    }
	    
   }

    /**
     * 共通情報の編集
     *
     * @param input インプットデータ
     * @param output アウトプットデータ
     */
    protected void EditScreenCommonItem(S input, T output) throws TKRKScreenException{
    	log.info("CONTROLLERテスト", "### start EditScreenCommonItem ###");
    }



}
