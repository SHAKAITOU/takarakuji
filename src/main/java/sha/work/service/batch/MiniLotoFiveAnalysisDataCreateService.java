package sha.work.service.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sha.framework.service.BaseService;
import sha.work.entity.out.MiniLotoFiveAnalysisOut;
import sha.work.enums.HanyoType;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.loto.MiniLotoMapper;

@Service
public class MiniLotoFiveAnalysisDataCreateService extends BaseService {

	/** DB access class. */
	@Autowired
	private MiniLotoMapper miniLotoMapper;

	@Transactional
	public MiniLotoFiveAnalysisOut analysis() throws TKRKScreenException {
		MiniLotoFiveAnalysisOut analysisOut = new MiniLotoFiveAnalysisOut();
		analysisOut.setFiveList(miniLotoMapper.getFiveSum(HanyoType.MINILOTO.getId()));
		return analysisOut;
	}
}
