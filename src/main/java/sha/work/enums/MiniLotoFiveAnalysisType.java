package sha.work.enums;

public enum MiniLotoFiveAnalysisType {

	PLACE1			(1, "place1", 	"1位数字"),
	PLACE2			(2, "place2", 	"2位数字"),
	PLACE3			(3, "place3", 	"3位数字"),
	PLACE4			(4, "place4", 	"4位数字"),
	PLACE5			(5, "place5", 	"5位数字"),
	;

    /** type. */
    private int id;
    private String key;
    private String name;

    private MiniLotoFiveAnalysisType(int id, String key, String name) {
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
    
    public MiniLotoFiveAnalysisType valueOf(int id) {
    	for(MiniLotoFiveAnalysisType type : MiniLotoFiveAnalysisType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public MiniLotoFiveAnalysisType keyOf(String key) {
    	for(MiniLotoFiveAnalysisType type : MiniLotoFiveAnalysisType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public MiniLotoFiveAnalysisType nameOf(String name) {
    	for(MiniLotoFiveAnalysisType type : MiniLotoFiveAnalysisType.values()) {
    		if(name.equals(type.getName())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
