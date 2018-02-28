package sha.work.service.loto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sha.framework.service.BaseService;
import sha.work.common.CommonConstants;
import sha.work.entity.out.Loto7ShowDataOut;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.loto.Loto7Mapper;

@Service
public class Loto7ShowService extends BaseService {

	/** DB access class. */
	@Autowired
	private Loto7Mapper loto7Mapper;


	public Loto7ShowDataOut getData(int pageIdx) throws TKRKScreenException {
		
		Loto7ShowDataOut dataOut = new Loto7ShowDataOut();
		loto7Mapper.getTotalCnt();
		dataOut.setLoto7List(loto7Mapper.getPageList(CommonConstants.MAX_PAGE_COUNT, pageIdx));

		return dataOut;
	}
}
