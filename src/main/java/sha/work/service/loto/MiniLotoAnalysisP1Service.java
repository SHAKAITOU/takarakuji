package sha.work.service.loto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sha.framework.service.BaseService;
import sha.work.dto.domain.Hanyo;
import sha.work.entity.in.MiniLotoAnalysisP1In;
import sha.work.entity.out.MiniLotoAnalysisP1Out;
import sha.work.entity.query.MiniLotoAnalysisP1Query;
import sha.work.entity.query.NumberAndTurnsQuery;
import sha.work.enums.HanyoType;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.loto.HanyoMapper;
import sha.work.mapper.loto.MiniLotoMapper;

@Service
public class MiniLotoAnalysisP1Service extends BaseService {

	/** DB access class. */
	@Autowired
	private MiniLotoMapper miniLotoMapper;
	
	@Autowired
	private HanyoMapper hanyoMapper;


	public MiniLotoAnalysisP1Out analysis() throws TKRKScreenException {
		
		List<Hanyo> miniLotoHanyo = hanyoMapper.find(HanyoType.MINILOTO.getId());
		
		MiniLotoAnalysisP1In miniLotoAnalysisP1 = new MiniLotoAnalysisP1In();
		miniLotoAnalysisP1.setMiniLotoHanyo(miniLotoHanyo);
		
		
		List<MiniLotoAnalysisP1Query> resultList = new ArrayList<>();
		List<NumberAndTurnsQuery> nomalNumberList = miniLotoMapper.getNomalNumberSum(miniLotoAnalysisP1);
		List<NumberAndTurnsQuery> bonusNumber1List = miniLotoMapper.getBonusNumber1Sum(miniLotoAnalysisP1);
		
		for(int i=0; i<nomalNumberList.size(); i++) {
			MiniLotoAnalysisP1Query result = new MiniLotoAnalysisP1Query();
			result.setNumberName(nomalNumberList.get(i).getNumberName());
			result.setNumberValue(nomalNumberList.get(i).getNumberValue());
			result.setBonusNumber1Value(bonusNumber1List.get(i).getNumberValue());
			resultList.add(result);
		}	
		
		MiniLotoAnalysisP1Out analysisP1Out = new MiniLotoAnalysisP1Out();
		analysisP1Out.setP1List(resultList);

		return analysisP1Out;
	}
}
