package sha.work.enums;

public enum Loto6AnalysisType {

	TOTAL_AVG			(1, "totalAvg", 	"当選数平均値"),
	MAX_MIN_DIFF		(2, "maxMinDiff", 	"最大と最小数差値"),
	MAX_NUM_DIFF		(3, "maxNumDiff", 	"数間差値最大値"),
	MIN_NUM_DIFF		(4, "minNumDiff", 	"数間差値最小値"),
	NUM_DIFF_AVG		(5, "numDiffAvg", 	"数間差値平均値"),
	EVEN_NUM_CNT		(6, "evenNumCnt", 	"偶数当選個数"),
	ODD_NUM_CNT		(7, "oddNumCnt", 	"奇数当選個数"),
	SERIAL_NUM_CNT	(8, "serialNumCnt", "当選数連続個数"),
	LEFT_AREA_NUM_CNT	(9, "leftAreaNumCnt", "低区[1-14]当選数個数"),
	CENTER_AREA_NUM_CNT	(10, "centerAreaNumCnt", "中区[15-29]当選数個数"),
	RIGHT_AREA_NUM_CNT	(11, "rightAreaNumCnt", "高区[30-43]当選数個数"),
	;

    /** type. */
    private int id;
    private String key;
    private String name;

    private Loto6AnalysisType(int id, String key, String name) {
        this.id = id;
        this.key = key;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public String getKey() {
        return key;
    }
    
    public String getName() {
        return name;
    }
    
    public Loto6AnalysisType valueOf(int id) {
    	for(Loto6AnalysisType type : Loto6AnalysisType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public Loto6AnalysisType keyOf(String key) {
    	for(Loto6AnalysisType type : Loto6AnalysisType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public Loto6AnalysisType nameOf(String name) {
    	for(Loto6AnalysisType type : Loto6AnalysisType.values()) {
    		if(name.equals(type.getName())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
