package sha.framework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class FileFilter {

  public static void main(String[] args) {
    File file = new File(
        "D:\\sya-kt\\work\\talend\\ジョブ開発仕様書\\01.PricePF\\ok_まとめ買い売価・限定情報取込_PRC_OUT_multiPromotionMaster"
        + "\\[全件]売変仕掛け情報.txt");
    File fileTo = new File(
        "D:\\sya-kt\\work\\talend\\ジョブ開発仕様書\\01.PricePF\\ok_まとめ買い売価・限定情報取込_PRC_OUT_multiPromotionMaster"
        + "\\UQCD_PRICE_CHANGE_PROMO.csv");

    convertFile(file, fileTo);
    
    file = new File(
        "D:\\sya-kt\\work\\talend\\ジョブ開発仕様書\\01.PricePF\\ok_まとめ買い売価・限定情報取込_PRC_OUT_multiPromotionMaster"
        + "\\[全件]売変設定情報_親.txt");
    fileTo = new File(
        "D:\\sya-kt\\work\\talend\\ジョブ開発仕様書\\01.PricePF\\ok_まとめ買い売価・限定情報取込_PRC_OUT_multiPromotionMaster"
        + "\\UQCD_PRICE_CHANGE_PARENT.csv");

    convertFile(file, fileTo);
    
    file = new File(
        "D:\\sya-kt\\work\\talend\\ジョブ開発仕様書\\01.PricePF\\ok_まとめ買い売価・限定情報取込_PRC_OUT_multiPromotionMaster"
        + "\\[全件]売変設定情報_子.txt");
    fileTo = new File(
        "D:\\sya-kt\\work\\talend\\ジョブ開発仕様書\\01.PricePF\\ok_まとめ買い売価・限定情報取込_PRC_OUT_multiPromotionMaster"
        + "\\UQCD_PRICE_CHANGE_CHILD.csv");

    convertFile(file, fileTo);
    
  }
  
  private static void convertFile(File src, File dst) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(src));
      PrintWriter pw = new PrintWriter(
          new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dst),"UTF-8")));

      
      String str;
      int index = 0;
      while((str = br.readLine()) != null){
        String trg = "";
        str = str.replace(" ", "");
        str = str.replace("\t", ",");
        if(index == 0) {
          trg = str;
        } else {
          String[] chips = str.split(",");
          int subIdx = 0;
          for(String chip : chips) {
            if(subIdx > 0) {
              trg += ",";
            }
            trg += "\"" + chip + "\"";
            subIdx++;
          }
        }
        pw.print(trg);
        pw.print("\r\n");
        index++;
      }

      br.close();
      pw.close();
      
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
