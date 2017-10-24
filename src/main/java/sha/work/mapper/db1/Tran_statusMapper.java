package sha.work.mapper.db1;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * tran_infoテーブルにトラン情報を登録するリポジトリクラス。
 */
@Mapper
public interface Tran_statusMapper {

	/**
	 * 新規トラン情報をDBに登録します。
	 * 
	 * @param tran_no　番号
	 * @param subscribe_data データ
	 */
	//3箇所のテーブルに同じデータをインサート
    // tran_statusテーブル
    @Insert("INSERT  INTO tran_status(subcribe_data) VALUES(#{subscribe_data})")
    public void tran_status_Register(@Param("subscribe_data") String subscribe_data);
    // sales_dataテーブル
    @Insert("INSERT  INTO sales_data(subcribe_data) VALUES(#{subscribe_data})")
    public void sales_data_Register(@Param("subscribe_data") String subscribe_data);
    //tran_dataテーブル
    @Insert("INSERT  INTO tran_data(subcribe_data) VALUES(#{subscribe_data})")
    public void tran_data_Register(@Param("subscribe_data") String subscribe_data);
    

    


}

