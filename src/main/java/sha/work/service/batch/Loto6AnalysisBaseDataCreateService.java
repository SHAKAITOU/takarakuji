package sha.work.service.batch;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sha.framework.service.BaseService;
import sha.work.dto.loto.Loto6;
import sha.work.dto.loto.Loto6AnalysisBase;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.loto.Loto6AnalysisBaseMapper;
import sha.work.mapper.loto.Loto6Mapper;

@Service
public class Loto6AnalysisBaseDataCreateService extends BaseService {

	/** DB access class. */
	@Autowired
	private Loto6Mapper loto6Mapper;
	
	@Autowired
	private Loto6AnalysisBaseMapper analysisBaseMapper;

	@Transactional
	public void analysis(int turn) throws TKRKScreenException {

		Loto6 loto6 = loto6Mapper.findByTurn(turn);
		saveData(analysisOnly(loto6));
	}
	
	@Transactional
	public void analysisAll() {
		List<Loto6> all = loto6Mapper.getAll();
		for(Loto6 loto6 : all) {
			saveData(analysisOnly(loto6));
		}
	}
	
	public Loto6AnalysisBase analysisOnly(Loto6 loto6) throws TKRKScreenException {
		
		Loto6AnalysisBase analysisBase = new Loto6AnalysisBase();
		
		int[] numbers = toArray(loto6);
		
		analysisBase.setTurn(loto6.getTurn());
		
		analysisBase.setMaxNum(getMaxNumber(numbers));
		
		analysisBase.setMinNum(getMinNumber(numbers));
		
		analysisBase.setMaxMinDiff(analysisBase.getMaxNum() - analysisBase.getMinNum());
		
		analysisBase.setTotalAvg(getTotalAvg(numbers));
		
		analysisBase.setMaxNumDiff(getMaxNumDiff(numbers));
		
		analysisBase.setMinNumDiff(getMinNumDiff(numbers));
		
		analysisBase.setNumDiffAvg(getNumDiffAvg(numbers));
		
		//偶数出る回数
		analysisBase.setEvenNumCnt(getEvenNumCnt(numbers)); 
		
		analysisBase.setOddNumCnt(getOddNumCnt(numbers));
		
		analysisBase.setSerialNumCnt(getSerialNumCnt(numbers));
		
		analysisBase.setLeftAreaNumCnt(getLeftAreaNumCnt(numbers));
		
		analysisBase.setCenterAreaNumCnt(getCenterAreaNumCnt(numbers));
		
		analysisBase.setRightAreaNumCnt(getRightAreaNumCnt(numbers));
		
		return analysisBase;
	}
	
	public void saveData(Loto6AnalysisBase analysisBase) {
		int cnt = analysisBaseMapper.isExist(analysisBase.getTurn());
		if(cnt > 0) {
			analysisBaseMapper.update(analysisBase);
		} else {
			analysisBaseMapper.save(analysisBase);
		}
	}
	
	private int[] toArray(Loto6 loto6) {
		return new int[] {
				loto6.getL1(), loto6.getL2(), loto6.getL3()
				, loto6.getL4(), loto6.getL5(), loto6.getL6() };
	}
	
	/**
	 * 偶数出る回数
	 * @param loto7
	 * @return
	 */
	private int getEvenNumCnt(int[] numbers) {
		int cnt = 0;
		for(int number : numbers) {
			if(number % 2 == 0) {
				cnt += 1; 
			}
		}
		return cnt;
	}
	
	/**
	 * 奇数出る回数
	 * @param loto7
	 * @return
	 */
	private int getOddNumCnt(int[] numbers) {
		int cnt = 0;
		for(int number : numbers) {
			if(number % 2 != 0) {
				cnt += 1; 
			}
		}
		return cnt;
	}
	
	private int getMaxNumber(int[] numbers) {
		return Arrays.stream(numbers).max().getAsInt();
	}
	
	private int getMinNumber(int[] numbers) {
		return Arrays.stream(numbers).min().getAsInt();
	}
	
	private int getTotalAvg(int[] numbers) {
		return (int)Math.round(Arrays.stream(numbers).average().getAsDouble());
	}
	
	private int getMaxNumDiff(int[] numbers) {
		return Arrays.stream(getNumDiff(numbers)).max().getAsInt();
	}
	
	private int getMinNumDiff(int[] numbers) {
		return Arrays.stream(getNumDiff(numbers)).min().getAsInt();
	}
	
	private int getNumDiffAvg(int[] numbers) {
		return (int)Math.round(Arrays.stream(getNumDiff(numbers)).average().getAsDouble());
	}
	
	private int[] getNumDiff(int[] numbers) {
		int [] numDiff = new int[numbers.length-1];
		int numA = 0;
		int numB = 0;
		int index = 0;
		for(int number : numbers) {
			if(index == 0) {
				numA = number;
			}
			else if(index > 0) {
				numB = number;
				int diff = Math.abs(numB - numA);
				numDiff[index-1] = diff;
				numA = numB;
			}
			index++;
		}
		return numDiff;
	}
	
	private int getSerialNumCnt(int[] numbers) {
		int serialNumCnt = 0;
		int numA = 0;
		int numB = 0;
		int index = 0;
		for(int number : numbers) {
			if(index > 0) {				
				numA = numB;
				numB = number;
				
				int diff = Math.abs(numB - numA);
				if(diff == 1) {
					serialNumCnt += 1;
				}
			}
			index++;
		}
		return serialNumCnt;
	}
	
	private int getLeftAreaNumCnt(int[] numbers) {
		int leftNumCnt = 0;
		for(int number : numbers) {
			if(number <= 14) {
				leftNumCnt += 1;
			}
		}
		
		return leftNumCnt;
	}
	
	private int getCenterAreaNumCnt(int[] numbers) {
		int centerNumCnt = 0;
		for(int number : numbers) {
			if(number > 14 && number <= 29) {
				centerNumCnt += 1;
			}
		}
		
		return centerNumCnt;
	}
	
	private int getRightAreaNumCnt(int[] numbers) {
		int rightNumCnt = 0;
		for(int number : numbers) {
			if(number > 29 && number <= 43) {
				rightNumCnt += 1;
			}
		}
		
		return rightNumCnt;
	}
	
}
