package sha.work.service.loto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sha.framework.service.BaseService;
import sha.work.dto.loto.Loto7;
import sha.work.entity.in.Loto7ShowDataIn;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.loto.Loto7Mapper;

@Service
public class Loto7AddService extends BaseService {

	/** DB access class. */
	@Autowired
	private Loto7Mapper loto7Mapper;

	public int getMaxTurn() throws TKRKScreenException {
		
		return loto7Mapper.getTotalCnt();
	}

	public  List<Loto7> getData(Loto7ShowDataIn dataIn) throws TKRKScreenException {
		return loto7Mapper.getPageList(dataIn);
	}
}
