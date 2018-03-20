package sha.work.service.batch;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import sha.framework.service.BaseService;
import sha.work.exception.TKRKScreenException;
import sha.work.service.loto.MiniLotoAnalysisP1Service;
import sha.work.service.loto.MiniLotoAnalysisP2Service;
import sha.work.service.loto.MiniLotoFiveAnalysisDataCreateService;
import sha.work.util.FileUtil;

@Service
public class MiniLotoBatchService extends BaseService {

	@Autowired
	private MiniLotoAnalysisP1Service miniLotoAnalysisP1Service;
	
	@Autowired
	private MiniLotoAnalysisP2Service  miniLotoAnalysisP2Service;	
	
	@Autowired
	private MiniLotoFiveAnalysisDataCreateService  miniLotoFiveAnalysisDataCreateService;
	
	public void batch() {
		ObjectMapper objectMapper = new ObjectMapper();
		
		//----------------------------------------------------------------------
		//MiniLotoAnalysisP1Service
		try {
		    File dir = new File(FileUtil.getJsonDataFilePath());
		    if (!dir.exists()) {
		        dir.mkdirs();
		    }
		    File file = new File(FileUtil.getMiniLotoP1DataFileJson());
			if(file.exists()) {
				file.delete();
			}
			file.createNewFile();
			objectMapper.writeValue(file, miniLotoAnalysisP1Service.analysis());
		} catch (IOException e) {
			throw new TKRKScreenException(e);
		}
		
		//----------------------------------------------------------------------
		//MiniLotoAnalysisP2Service
		try {
		    File dir = new File(FileUtil.getJsonDataFilePath());
		    if (!dir.exists()) {
		        dir.mkdirs();
		    }
		    File file = new File(FileUtil.getMiniLotoP2DataFileJson());
			if(file.exists()) {
				file.delete();
			}
			file.createNewFile();
			objectMapper.writeValue(file, miniLotoAnalysisP2Service.summary());
		} catch (IOException e) {
			throw new TKRKScreenException(e);
		}
		
		//----------------------------------------------------------------------
		//MiniLotoFiveAnalysisDataCreateService
		try {
		    File dir = new File(FileUtil.getJsonDataFilePath());
		    if (!dir.exists()) {
		        dir.mkdirs();
		    }
		    File file = new File(FileUtil.getMiniLotoFiveDataFileJson());
			if(file.exists()) {
				file.delete();
			}
			file.createNewFile();
			objectMapper.writeValue(file, miniLotoFiveAnalysisDataCreateService.analysis());
		} catch (IOException e) {
			throw new TKRKScreenException(e);
		}
	}
}
