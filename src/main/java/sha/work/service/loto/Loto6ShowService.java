package sha.work.service.loto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sha.framework.service.BaseService;
import sha.work.dto.loto.Loto6;
import sha.work.entity.in.Loto6ShowDataIn;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.loto.Loto6Mapper;

@Service
public class Loto6ShowService extends BaseService {

	/** DB access class. */
	@Autowired
	private Loto6Mapper loto6Mapper;

	public int getMaxTurn() throws TKRKScreenException {
		
		return loto6Mapper.getTotalCnt();
	}

	public  List<Loto6> getData(Loto6ShowDataIn dataIn) throws TKRKScreenException {
		return loto6Mapper.getPageList(dataIn);
	}
}
