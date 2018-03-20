package sha.work.service.loto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sha.framework.service.BaseService;
import sha.work.entity.out.Loto7SevenAnalysisOut;
import sha.work.enums.HanyoType;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.loto.Loto7Mapper;

@Service
public class Loto7SevenAnalysisDataCreateService extends BaseService {

	/** DB access class. */
	@Autowired
	private Loto7Mapper loto7Mapper;

	@Transactional
	public Loto7SevenAnalysisOut analysis() throws TKRKScreenException {
		Loto7SevenAnalysisOut analysisOut = new Loto7SevenAnalysisOut();
		analysisOut.setSevenList(loto7Mapper.getSevenSum(HanyoType.LOTO7.getId()));
		return analysisOut;
	}
}
