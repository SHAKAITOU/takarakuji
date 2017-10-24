package sha.framework.data;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * リザルトコードのEnumクラス。
 */
public enum ResultCode {

	/**	正常を表すリザルトコード。 */
	NORMAL(0),

	/**	異常を表すリザルトコード。 */
	ABNORMAL(1),

	/**	警告を表すリザルトコード。 */
	WARNING(2);

	/** リザルトコードの数値表現。 */
	private int resultNum;

	/**
	 * リザルトコードの数値表現を設定します。
	 *
	 * @param resultNum リザルトコードに設定する数値。
	 */
	private ResultCode(int resultNum) {
		this.resultNum = resultNum;
	}

	/**
	 * リザルトコードの数値表現を返却します。
	 *
	 * @return　リザルトコードの数値表現。
	 */
	@JsonValue
	public int toValue() {
		return resultNum;
	}
}
