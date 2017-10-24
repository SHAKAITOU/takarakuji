package sha.framework.controller;


import org.springframework.beans.factory.annotation.Autowired;

import sha.framework.data.InputBaseData;
import sha.framework.data.OutputBaseData;
import sha.framework.util.LogCommonUtil;
import sha.work.exception.TKRKScreenException;

/**
 * コントローラのベースクラス
 *
 * @author 000001A006PT9
 *
 */
public abstract class BaseController<S extends InputBaseData, T extends OutputBaseData> {

	@Autowired
	private LogCommonUtil log;
	
	/**
	 * PR層の入力チェック
	 *
	 * @param input インプットデータ
	 * @param output アウトプットデータ
	 */
    public abstract void validate(S input, T output) throws TKRKScreenException;

    /**
     * 共通情報の編集
     *
     * @param input インプットデータ
     * @param output アウトプットデータ
     */
    protected void EditCommonItem(S input, T output) throws TKRKScreenException{
    	log.info("CONTROLLERテスト", "### start EditCommonItem ###");
    }

}
