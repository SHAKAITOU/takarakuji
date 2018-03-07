package sha.work.enums;

public enum AnalysisType {

	TOTAL_AVG		(1, "totalAvg", 	"当選数平均値"),
	MAX_NUM_DIFF	(2, "maxNumDiff", 	"当選数最大差値"),
	MIN_NUM_DIFF	(3, "minNumDiff", 	"当選数最小差値");

    /** type. */
    private int id;
    private String key;
    private String name;

    private AnalysisType(int id, String key, String name) {
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
    
    public AnalysisType valueOf(int id) {
    	for(AnalysisType type : AnalysisType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public AnalysisType keyOf(String key) {
    	for(AnalysisType type : AnalysisType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public AnalysisType nameOf(String name) {
    	for(AnalysisType type : AnalysisType.values()) {
    		if(name.equals(type.getName())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
