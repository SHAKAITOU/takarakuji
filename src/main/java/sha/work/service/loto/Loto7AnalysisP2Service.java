package sha.work.service.loto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sha.framework.service.BaseService;
import sha.work.dto.loto.Loto7;
import sha.work.entity.in.Loto7ShowDataIn;
import sha.work.entity.out.Loto7AnalysisP2Out;
import sha.work.entity.query.NumberAndTurnsQuery;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.loto.Loto7AnalysisBaseMapper;
import sha.work.mapper.loto.Loto7Mapper;

@Service
public class Loto7AnalysisP2Service extends BaseService {

	/** DB access class. */
	@Autowired
	private Loto7AnalysisBaseMapper loto7AnalysisBaseMapper;


	public Loto7AnalysisP2Out summary() throws TKRKScreenException {
		
		Loto7AnalysisP2Out analysisP2Out = new Loto7AnalysisP2Out();
		analysisP2Out.setSummaryTotalAvgList(loto7AnalysisBaseMapper.summaryTotalAvg());
		return analysisP2Out;
	}
}
