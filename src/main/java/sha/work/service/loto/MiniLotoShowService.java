package sha.work.service.loto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sha.framework.service.BaseService;
import sha.work.dto.loto.MiniLoto;
import sha.work.entity.in.MiniLotoShowDataIn;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.loto.MiniLotoMapper;

@Service
public class MiniLotoShowService extends BaseService {

	/** DB access class. */
	@Autowired
	private MiniLotoMapper miniLotoMapper;

	public int getMaxTurn() throws TKRKScreenException {
		
		return miniLotoMapper.getTotalCnt();
	}

	public  List<MiniLoto> getData(MiniLotoShowDataIn dataIn) throws TKRKScreenException {
		return miniLotoMapper.getPageList(dataIn);
	}
}
