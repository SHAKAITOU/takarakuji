package sha.work.service.loto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sha.framework.service.BaseService;
import sha.work.common.HanyoType;
import sha.work.dto.domain.Hanyo;
import sha.work.entity.loto.Loto7AnalysisP1;
import sha.work.entity.loto.Loto7AnalysisP1Result;
import sha.work.entity.loto.NumberAndTurns;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.domain.HanyoMapper;
import sha.work.mapper.loto.Loto7Mapper;

@Service
public class Loto7AnalysisP1Service extends BaseService {

	/** DB access class. */
	@Autowired
	private Loto7Mapper loto7Mapper;
	
	@Autowired
	private HanyoMapper hanyoMapper;


	public List<Loto7AnalysisP1Result> analysis() throws TKRKScreenException {
		
		List<Hanyo> loto7Hanyo = hanyoMapper.find(HanyoType.LOTO7.getId());
		
		Loto7AnalysisP1 loto7AnalysisP1 = new Loto7AnalysisP1();
		loto7AnalysisP1.setLoto7Hanyo(loto7Hanyo);
		
		
		List<Loto7AnalysisP1Result> resultList = new ArrayList<>();
		List<NumberAndTurns> nomalNumberList = loto7Mapper.getNomalNumberSum(loto7AnalysisP1);
		List<NumberAndTurns> bonusNumber1List = loto7Mapper.getBonusNumber1Sum(loto7AnalysisP1);
		List<NumberAndTurns> bonusNumber2List = loto7Mapper.getBonusNumber2Sum(loto7AnalysisP1);
		
		for(int i=0; i<nomalNumberList.size(); i++) {
			Loto7AnalysisP1Result result = new Loto7AnalysisP1Result();
			result.setNumberName(nomalNumberList.get(i).getNumberName());
			result.setNumberValue(nomalNumberList.get(i).getNumberValue());
			result.setBonusNumber1Value(bonusNumber1List.get(i).getNumberValue());
			result.setBonusNumber2Value(bonusNumber2List.get(i).getNumberValue());
			resultList.add(result);
		}		

		return resultList;
	}
}
