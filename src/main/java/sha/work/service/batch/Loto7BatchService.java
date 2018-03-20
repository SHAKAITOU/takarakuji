package sha.work.service.batch;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import sha.framework.service.BaseService;
import sha.work.exception.TKRKScreenException;
import sha.work.service.loto.Loto7AnalysisP1Service;
import sha.work.service.loto.Loto7AnalysisP2Service;
import sha.work.service.loto.Loto7SevenAnalysisDataCreateService;
import sha.work.util.FileUtil;

@Service
public class Loto7BatchService extends BaseService {

	@Autowired
	private Loto7AnalysisP1Service loto7AnalysisP1Service;
	
	@Autowired
	private Loto7AnalysisP2Service loto7AnalysisP2Service;	
	
	@Autowired
	private Loto7SevenAnalysisDataCreateService loto7SevenAnalysisDataCreateService;
	
	public void batch() {
		ObjectMapper objectMapper = new ObjectMapper();
		
		//----------------------------------------------------------------------
		//Loto7AnalysisP1Service
		try {
		    File dir = new File(FileUtil.getJsonDataFilePath());
		    if (!dir.exists()) {
		        dir.mkdirs();
		    }
		    File file = new File(FileUtil.getLoto7P1DataFileJson());
			if(file.exists()) {
				file.delete();
			}
			file.createNewFile();
			objectMapper.writeValue(file, loto7AnalysisP1Service.analysis());
		} catch (IOException e) {
			throw new TKRKScreenException(e);
		}
		
		//----------------------------------------------------------------------
		//Loto7AnalysisP2Service
		try {
		    File dir = new File(FileUtil.getJsonDataFilePath());
		    if (!dir.exists()) {
		        dir.mkdirs();
		    }
		    File file = new File(FileUtil.getLoto7P2DataFileJson());
			if(file.exists()) {
				file.delete();
			}
			file.createNewFile();
			objectMapper.writeValue(file, loto7AnalysisP2Service.summary());
		} catch (IOException e) {
			throw new TKRKScreenException(e);
		}
		
		//----------------------------------------------------------------------
		//Loto7SevenAnalysisDataCreateService
		try {
		    File dir = new File(FileUtil.getJsonDataFilePath());
		    if (!dir.exists()) {
		        dir.mkdirs();
		    }
		    File file = new File(FileUtil.getLoto7SevenDataFileJson());
			if(file.exists()) {
				file.delete();
			}
			file.createNewFile();
			objectMapper.writeValue(file, loto7SevenAnalysisDataCreateService.analysis());
		} catch (IOException e) {
			throw new TKRKScreenException(e);
		}
	}
}
