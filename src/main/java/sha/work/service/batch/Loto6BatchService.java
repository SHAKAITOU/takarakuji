package sha.work.service.batch;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import sha.framework.service.BaseService;
import sha.work.exception.TKRKScreenException;
import sha.work.service.loto.Loto6AnalysisP1Service;
import sha.work.service.loto.Loto6AnalysisP2Service;
import sha.work.service.loto.Loto6SixAnalysisDataCreateService;
import sha.work.util.FileUtil;

@Service
public class Loto6BatchService extends BaseService {

	@Autowired
	private Loto6AnalysisP1Service loto6AnalysisP1Service;
	
	@Autowired
	private Loto6AnalysisP2Service loto6AnalysisP2Service;	
	
	@Autowired
	private Loto6SixAnalysisDataCreateService loto6SixAnalysisDataCreateService;
	
	public void batch() {
		ObjectMapper objectMapper = new ObjectMapper();
		
		//----------------------------------------------------------------------
		//Loto6AnalysisP1Service
		try {
		    File dir = new File(FileUtil.getJsonDataFilePath());
		    if (!dir.exists()) {
		        dir.mkdirs();
		    }
		    File file = new File(FileUtil.getLoto6P1DataFileJson());
			if(file.exists()) {
				file.delete();
			}
			file.createNewFile();
			objectMapper.writeValue(file, loto6AnalysisP1Service.analysis());
		} catch (IOException e) {
			throw new TKRKScreenException(e);
		}
		
		//----------------------------------------------------------------------
		//Loto6AnalysisP2Service
		try {
		    File dir = new File(FileUtil.getJsonDataFilePath());
		    if (!dir.exists()) {
		        dir.mkdirs();
		    }
		    File file = new File(FileUtil.getLoto6P2DataFileJson());
			if(file.exists()) {
				file.delete();
			}
			file.createNewFile();
			objectMapper.writeValue(file, loto6AnalysisP2Service.summary());
		} catch (IOException e) {
			throw new TKRKScreenException(e);
		}
		
		//----------------------------------------------------------------------
		//Loto7SixAnalysisDataCreateService
		try {
		    File dir = new File(FileUtil.getJsonDataFilePath());
		    if (!dir.exists()) {
		        dir.mkdirs();
		    }
		    File file = new File(FileUtil.getLoto6SixDataFileJson());
			if(file.exists()) {
				file.delete();
			}
			file.createNewFile();
			objectMapper.writeValue(file, loto6SixAnalysisDataCreateService.analysis());
		} catch (IOException e) {
			throw new TKRKScreenException(e);
		}
	}
}
