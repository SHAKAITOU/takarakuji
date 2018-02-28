package sha.work.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class HtmlResourceReader {




	public static void main(String[] args) {
		//convertLoto7(LotoConstant.LOTO7+LotoConstant.TXT, LotoConstant.LOTO7+LotoConstant.CSV);
		//convertLoto6(LotoConstant.LOTO6+Constant.TXT, LotoConstant.LOTO6+LotoConstant.CSV);
		//convertNumberS4(LotoConstant.NUMBERS4+LotoConstant.TXT, LotoConstant.NUMBERS4+LotoConstant.CSV);
		//convertNumberS3(LotoConstant.NUMBERS3+LotoConstant.TXT, LotoConstant.NUMBERS3+LotoConstant.CSV);
		//convertBinGo5(LotoConstant.BINGO5+LotoConstant.TXT, LotoConstant.BINGO5+LotoConstant.CSV);
		//convertMiniLoto(LotoConstant.MINILOTO+LotoConstant.TXT, LotoConstant.MINILOTO+LotoConstant.CSV);
	}


	private static void convertLoto7(String srcStr, String dstStr) {
		try {
			File src = new File(srcStr);
			File dst = new File(dstStr);      

			BufferedReader br = new BufferedReader(new FileReader(src));
			PrintWriter pw = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dst),"UTF-8")));


			String str;
			boolean nextYearIndex = false;
			boolean nextNumIndex = false;
			boolean nextSnumIndex = false;

			StringBuffer sb = new StringBuffer();
			while((str = br.readLine()) != null){

				if(str.startsWith("第")) {
					nextYearIndex = true;
					sb.append(str.replace("第", "").replace("回", "")).append(",");
					continue;
				} 

				if(nextYearIndex) {
					sb.append(str).append("/");
					nextYearIndex = false;
					nextNumIndex = true;
					continue;
				} 

				if(nextNumIndex) {
					nextYearIndex = false;
					nextNumIndex = false;
					nextSnumIndex = true;
					sb.append(str.substring(0, 5)).append(",");
					String[] nums = str.substring(6).split("　");
					for(String num:nums) {
						sb.append(num).append(",");
					}
					continue;
				}

				if(nextSnumIndex) {
					nextSnumIndex = false;
					String[] nums = str.split("　");
					sb.append(nums[0].replace("(", "").replace(")", "").replace(" ", "")).append(",");
					sb.append(nums[1].replace("(", "").replace(")", "").replace(" ", ""));
					sb.append("\r\n");
					continue;
				}

			}
			pw.print(sb);

			br.close();
			pw.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void convertLoto6(String srcStr, String dstStr) {
		try {
			File src = new File(srcStr);
			File dst = new File(dstStr);      

			BufferedReader br = new BufferedReader(new FileReader(src));
			PrintWriter pw = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dst),"UTF-8")));


			String str;
			boolean nextYearIndex = false;
			boolean nextNumAIndex = false;
			boolean nextNumBIndex = false;
			boolean nextSnumIndex = false;

			StringBuffer sb = new StringBuffer();
			while((str = br.readLine()) != null){

				if(str.startsWith("第")) {
					nextYearIndex = true;
					sb.append(str.replace("第", "").replace("回", "")).append(",");
					continue;
				} 

				if(nextYearIndex) {
					sb.append(str).append("/");
					nextYearIndex = false;
					nextNumAIndex = true;
					continue;
				} 

				if(nextNumAIndex) {
					nextYearIndex = false;
					nextNumAIndex = false;
					nextNumBIndex = true;
					sb.append(str.substring(0, 5)).append(",");
					String[] nums = str.substring(6).split("　");
					for(String num:nums) {
						sb.append(num).append(",");
					}
					continue;
				}

				if(nextNumBIndex) {
					nextNumBIndex = false;
					nextSnumIndex = true;
					String[] nums = str.split("　");
					for(String num:nums) {
						sb.append(num).append(",");
					}
					continue;
				}

				if(nextSnumIndex) {
					nextSnumIndex = false;
					sb.append(str.substring(0, 4).replace("(", "").replace(")", "").replace(" ", ""));
					sb.append("\r\n");
					continue;
				}

			}
			pw.print(sb);

			br.close();
			pw.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void convertNumberS4(String srcStr, String dstStr) {
		try {
			File src = new File(srcStr);
			File dst = new File(dstStr);      

			BufferedReader br = new BufferedReader(new FileReader(src));
			PrintWriter pw = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dst),"UTF-8")));


			String str;
			boolean nextYearIndex = false;

			StringBuffer sb = new StringBuffer();
			while((str = br.readLine()) != null){

				if(str.length() == 9) {
					nextYearIndex = true;
					String[] chip = str.split(" ");
					sb.append(chip[0]).append(",");
					sb.append(chip[1]).append("/");
					continue;
				} 

				if(nextYearIndex) {
					sb.append(str.substring(0, 5)).append(",");
					sb.append(str.substring(6, 7)).append(",");
					sb.append(str.substring(7, 8)).append(",");
					sb.append(str.substring(8, 9)).append(",");
					sb.append(str.substring(9, 10)).append("\r\n");

					nextYearIndex = false;
					continue;
				}
			}
			pw.print(sb);

			br.close();
			pw.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void convertNumberS3(String srcStr, String dstStr) {
		try {
			File src = new File(srcStr);
			File dst = new File(dstStr);      

			BufferedReader br = new BufferedReader(new FileReader(src));
			PrintWriter pw = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dst),"UTF-8")));


			String str;
			boolean nextYearIndex = false;
			boolean nextNumIndex = false;

			StringBuffer sb = new StringBuffer();
			while((str = br.readLine()) != null){

				if(str.length() == 4) {
					nextYearIndex = true;
					sb.append(str.replace(" ", "")).append(",");
					continue;
				} 
				
				if(nextYearIndex) {
					sb.append(str.replace(" ", "")).append("/");

					nextYearIndex = false;
					nextNumIndex = true;
					continue;
				}

				if(nextNumIndex) {
					sb.append(str.substring(1, 6)).append(",");


					sb.append(str.substring(8, 9)).append(",");
					sb.append(str.substring(9, 10)).append(",");
					sb.append(str.substring(10, 11)).append("\r\n");

					nextNumIndex = false;
					continue;
				}
			}
			pw.print(sb);

			br.close();
			pw.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void convertBinGo5(String srcStr, String dstStr) {
		try {
			File src = new File(srcStr);
			File dst = new File(dstStr);      

			BufferedReader br = new BufferedReader(new FileReader(src));
			PrintWriter pw = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dst),"UTF-8")));


			String str;
			boolean nextYearIndex = false;
			boolean nextNumIndex = false;
			boolean nextSnumIndex = false;
			boolean nextOnumIndex = false;

			StringBuffer sb = new StringBuffer();
			while((str = br.readLine()) != null){

				if(str.startsWith("第")) {
					nextYearIndex = true;
					sb.append(str.replace("第", "").replace("回", "")).append(",");
					continue;
				} 

				if(nextYearIndex) {
					sb.append(str).append("/");
					nextYearIndex = false;
					nextNumIndex = true;
					continue;
				} 

				if(nextNumIndex) {
					nextYearIndex = false;
					nextNumIndex = false;
					nextSnumIndex = true;
					sb.append(str.substring(0, 5)).append(",");
					String[] nums = str.substring(7).split("　");
					for(String num:nums) {
						sb.append(num).append(",");
					}
					continue;
				}

				if(nextSnumIndex) {
					nextSnumIndex = false;
					nextOnumIndex = true;
					String[] nums = str.split("　");
					sb.append(nums[0].replace(" ", "")).append(",");
					sb.append(nums[2].replace(" ", "")).append(",");
					continue;
				}
				
				if(nextOnumIndex) {
					nextOnumIndex = false;
					String[] nums = str.split("　");
					sb.append(nums[0].replace(" ", "")).append(",");
					sb.append(nums[1].replace(" ", "")).append(",");
					sb.append(nums[2].replace(" ", ""));
					sb.append("\r\n");
					continue;
				}

			}
			pw.print(sb);

			br.close();
			pw.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void convertMiniLoto(String srcStr, String dstStr) {
		try {
			File src = new File(srcStr);
			File dst = new File(dstStr);      

			BufferedReader br = new BufferedReader(new FileReader(src));
			PrintWriter pw = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dst),"UTF-8")));


			String str;
			boolean nextYearIndex = false;

			StringBuffer sb = new StringBuffer();
			while((str = br.readLine()) != null){

				if(str.length() == 9) {
					nextYearIndex = true;
					String[] chip = str.split(" ");
					sb.append(chip[0]).append(",");
					sb.append(chip[1]).append("/");
					continue;
				} 

				if(nextYearIndex) {
					sb.append(str.substring(0, 5)).append(",");
					String[] chip = str.substring(6, 26).split(" ");
					sb.append(chip[0]).append(",");
					sb.append(chip[1]).append(",");
					sb.append(chip[2]).append(",");
					sb.append(chip[3]).append(",");
					sb.append(chip[4]).append(",");
					sb.append(chip[5].replace("(", "").replace(")", ""));					
					sb.append("\r\n");

					nextYearIndex = false;
					continue;
				}
			}
			pw.print(sb);

			br.close();
			pw.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private static Document read(String resource) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringComments(false);
		dbf.setIgnoringElementContentWhitespace(false);
		dbf.setExpandEntityReferences(false);
		DocumentBuilder db = dbf.newDocumentBuilder();
		return db.parse(new InputSource(new StringReader(resource)));
	}
}
