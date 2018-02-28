package sha.work.util;

import java.io.File;

public class FileUtil {
	
	//PATH
	private static final String ROOT_PATH = System.getProperty("user.dir");
	
	private static final String DDL_PATH = "ddl";
	
	private static final String DOMAIN_PATH = "domain";
	
	private static final String LOTO_PATH = "loto";
	
	private static final String INIT_DATA_PATH = "iniData";
	
	private static final String JSON_FILE_PATH = "jsonData";

	//EXTENTION
	private static final String TXT = ".txt";

	private static final String CSV = ".csv";
	
	private static final String JSON = ".json";
	
	//FILE NAME
	private static final String LOTO6 = "loto6_20180216";
	private static final String LOTO7 = "loto7_20180216";
	private static final String NUMBERS4 = "numberS4_20180216";
	private static final String NUMBERS3 = "numberS3_20180216";
	private static final String BINGO5 = "bingo5_20180216";
	private static final String MINILOTO = "miniloto_20180216";
	
	private static final String LOTO7_P1 = "loto7P1Data";
	
	//PATH
	public static String getRootPath() {
		return	ROOT_PATH;
	}
	
	public static String getDdlPath() {
		return getRootPath() + File.separator + DDL_PATH;
	}
	
	public static String getInitDataFilePath() {
		return getDdlPath() + File.separator + INIT_DATA_PATH;
	}
	
	public static String getJsonDataFilePath() {
		return getRootPath() + File.separator + JSON_FILE_PATH;
	}
	
	//INIT DATA FILE
	
	public static String getLoto7ImportDataFileTxt() {
		return getInitDataFilePath() + File.separator + LOTO7 + TXT;
	}
	
	public static String getLoto7ImportDataFileCsv() {
		return getInitDataFilePath() + File.separator + LOTO7 + CSV;
	}

	public static String getLoto6ImportDataFileTxt() {
		return getInitDataFilePath() + File.separator + LOTO6 + TXT;
	}
	
	public static String getLoto6ImportDataFileCsv() {
		return getInitDataFilePath() + File.separator + LOTO6 + CSV;
	}
	
	public static String getNumberS4ImportDataFileTxt() {
		return getInitDataFilePath() + File.separator + NUMBERS4 + TXT;
	}
	
	public static String getNumberS4ImportDataFileCsv() {
		return getInitDataFilePath() + File.separator + NUMBERS4 + CSV;
	}
	
	public static String getNumberS3ImportDataFileTxt() {
		return getInitDataFilePath() + File.separator + NUMBERS3 + TXT;
	}
	
	public static String getNumberS3ImportDataFileCsv() {
		return getInitDataFilePath() + File.separator + NUMBERS3 + CSV;
	}
	
	public static String getBinGo5ImportDataFileTxt() {
		return getInitDataFilePath() + File.separator + BINGO5 + TXT;
	}
	
	public static String getBinGo5ImportDataFileCsv() {
		return getInitDataFilePath() + File.separator + BINGO5 + CSV;
	}
	
	public static String getMiniLotoImportDataFileTxt() {
		return getInitDataFilePath() + File.separator + MINILOTO + TXT;
	}
	
	public static String getMiniLotoImportDataFileCsv() {
		return getInitDataFilePath() + File.separator + MINILOTO + CSV;
	}
	
	//JSON FILE
	public static String getLoto7P1DataFileJson() {
		return getJsonDataFilePath() + File.separator + LOTO7_P1 + JSON;
	}
}
