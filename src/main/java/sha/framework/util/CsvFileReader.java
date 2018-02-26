package sha.framework.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

public class CsvFileReader {

	public static List<List<String>> read(File src) {
		List<List<String>> csvData = new ArrayList<>(new ArrayList<>());

		try {

			BufferedReader br = new BufferedReader(new FileReader(src));

			String str;
			StringBuffer sb = new StringBuffer();
			while((str = br.readLine()) != null){
				csvData.add(CollectionUtils.arrayToList(str.split(",")));

			}

			br.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return csvData;
	}

	public static List<List<String>> read(String srcStr) {

		File src = new File(srcStr);
		
		return read(src);
	}
}
