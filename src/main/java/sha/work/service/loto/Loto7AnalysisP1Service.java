package sha.work.service.loto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sha.framework.service.BaseService;
import sha.work.dto.loto.Hanyo;
import sha.work.entity.in.Loto7AnalysisP1In;
import sha.work.entity.out.Loto7AnalysisP1Out;
import sha.work.entity.query.Loto7AnalysisP1Query;
import sha.work.entity.query.NumberAndTurnsQuery;
import sha.work.enums.HanyoType;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.loto.HanyoMapper;
import sha.work.mapper.loto.Loto7Mapper;

@Service
public class Loto7AnalysisP1Service extends BaseService {

	/** DB access class. */
	@Autowired
	private Loto7Mapper loto7Mapper;
	
	@Autowired
	private HanyoMapper hanyoMapper;


	public Loto7AnalysisP1Out analysis() throws TKRKScreenException {
		
		List<Hanyo> loto7Hanyo = hanyoMapper.find(HanyoType.LOTO7.getId());
		
		Loto7AnalysisP1In loto7AnalysisP1 = new Loto7AnalysisP1In();
		loto7AnalysisP1.setLoto7Hanyo(loto7Hanyo);
		
		
		List<Loto7AnalysisP1Query> resultList = new ArrayList<>();
		List<NumberAndTurnsQuery> nomalNumberList = loto7Mapper.getNomalNumberSum(loto7AnalysisP1);
		List<NumberAndTurnsQuery> bonusNumber1List = loto7Mapper.getBonusNumber1Sum(loto7AnalysisP1);
		List<NumberAndTurnsQuery> bonusNumber2List = loto7Mapper.getBonusNumber2Sum(loto7AnalysisP1);
		
		for(int i=0; i<nomalNumberList.size(); i++) {
			Loto7AnalysisP1Query result = new Loto7AnalysisP1Query();
			result.setNumberName(nomalNumberList.get(i).getNumberName());
			result.setNumberValue(nomalNumberList.get(i).getNumberValue());
			result.setBonusNumber1Value(bonusNumber1List.get(i).getNumberValue());
			result.setBonusNumber2Value(bonusNumber2List.get(i).getNumberValue());
			resultList.add(result);
		}	
		
		Loto7AnalysisP1Out analysisP1Out = new Loto7AnalysisP1Out();
		analysisP1Out.setP1List(resultList);

		return analysisP1Out;
	}
}
