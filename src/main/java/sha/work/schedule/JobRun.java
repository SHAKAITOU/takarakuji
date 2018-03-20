package sha.work.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import sha.framework.util.LogCommonUtil;
import sha.work.service.batch.Loto6BatchService;
import sha.work.service.batch.Loto7BatchService;
import sha.work.service.batch.MiniLotoBatchService;

@Component
public class JobRun {
	
	private int extIdxLoto7 = 0;
	private int extIdxLoto6 = 0;
	private int extIdxMiniLoto = 0;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	
	@Autowired
	CronSetting cron;
	
	@Autowired
	private LogCommonUtil log;
	
	@Autowired
	private Loto7BatchService loto7BatchService;
	
	@Autowired
	private Loto6BatchService loto6BatchService;
	
	@Autowired
	private MiniLotoBatchService miniLotoBatchService;
	 
	@Scheduled(cron="${cron.loto7Cron}")
	public void executeLoto7Batch() {
		
		loto7BatchService.batch();		
		log.info("LOTO7バッチ", "実行回数: " + ++extIdxLoto7 + ", 実行時間: " + sdf.format(new Date()));
	}
	
	@Scheduled(cron="${cron.loto6Cron}")
	public void executeLoto6Batch() {
		
		loto6BatchService.batch();
		
		log.info("LOTO6バッチ", "実行回数: " + ++extIdxLoto6 + ", 実行時間: " + sdf.format(new Date()));
	}
	
	@Scheduled(cron="${cron.miniLotoCron}")
	public void executeMiniLotoBatch() {
		
		miniLotoBatchService.batch();
		
		log.info("MINILOTOバッチ", "実行回数: " + ++extIdxMiniLoto + ", 実行時間: " + sdf.format(new Date()));
	}
}
