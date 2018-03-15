package sha.work.service.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sha.framework.service.BaseService;
import sha.work.entity.out.Loto6SixAnalysisOut;
import sha.work.enums.HanyoType;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.loto.Loto6Mapper;

@Service
public class Loto6SixAnalysisDataCreateService extends BaseService {

	/** DB access class. */
	@Autowired
	private Loto6Mapper loto6Mapper;

	@Transactional
	public Loto6SixAnalysisOut analysis() throws TKRKScreenException {
		Loto6SixAnalysisOut analysisOut = new Loto6SixAnalysisOut();
		analysisOut.setSixList(loto6Mapper.getSixSum(HanyoType.LOTO6.getId()));
		return analysisOut;
	}
}
