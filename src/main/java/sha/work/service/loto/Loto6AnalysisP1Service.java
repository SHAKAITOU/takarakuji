package sha.work.service.loto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sha.framework.service.BaseService;
import sha.work.dto.domain.Hanyo;
import sha.work.entity.in.Loto6AnalysisP1In;
import sha.work.entity.out.Loto6AnalysisP1Out;
import sha.work.entity.query.Loto6AnalysisP1Query;
import sha.work.entity.query.NumberAndTurnsQuery;
import sha.work.enums.HanyoType;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.domain.HanyoMapper;
import sha.work.mapper.loto.Loto6Mapper;

@Service
public class Loto6AnalysisP1Service extends BaseService {

	/** DB access class. */
	@Autowired
	private Loto6Mapper loto6Mapper;
	
	@Autowired
	private HanyoMapper hanyoMapper;


	public Loto6AnalysisP1Out analysis() throws TKRKScreenException {
		
		List<Hanyo> loto6Hanyo = hanyoMapper.find(HanyoType.LOTO6.getId());
		
		Loto6AnalysisP1In loto6AnalysisP1 = new Loto6AnalysisP1In();
		loto6AnalysisP1.setLoto6Hanyo(loto6Hanyo);
		
		
		List<Loto6AnalysisP1Query> resultList = new ArrayList<>();
		List<NumberAndTurnsQuery> nomalNumberList = loto6Mapper.getNomalNumberSum(loto6AnalysisP1);
		List<NumberAndTurnsQuery> bonusNumber1List = loto6Mapper.getBonusNumber1Sum(loto6AnalysisP1);
		
		for(int i=0; i<nomalNumberList.size(); i++) {
			Loto6AnalysisP1Query result = new Loto6AnalysisP1Query();
			result.setNumberName(nomalNumberList.get(i).getNumberName());
			result.setNumberValue(nomalNumberList.get(i).getNumberValue());
			result.setBonusNumber1Value(bonusNumber1List.get(i).getNumberValue());
			resultList.add(result);
		}	
		
		Loto6AnalysisP1Out analysisP1Out = new Loto6AnalysisP1Out();
		analysisP1Out.setP1List(resultList);

		return analysisP1Out;
	}
}
