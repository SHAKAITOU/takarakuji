package sha.work.enums;

public enum Loto7SevenAnalysisType {

	PLACE1			(1, "place1", 	"1位数字"),
	PLACE2			(2, "place2", 	"2位数字"),
	PLACE3			(3, "place3", 	"3位数字"),
	PLACE4			(4, "place4", 	"4位数字"),
	PLACE5			(5, "place5", 	"5位数字"),
	PLACE6			(6, "place6", 	"6位数字"),
	PLACE7			(7, "place7", 	"7位数字"),
	;

    /** type. */
    private int id;
    private String key;
    private String name;

    private Loto7SevenAnalysisType(int id, String key, String name) {
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
    
    public Loto7SevenAnalysisType valueOf(int id) {
    	for(Loto7SevenAnalysisType type : Loto7SevenAnalysisType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public Loto7SevenAnalysisType keyOf(String key) {
    	for(Loto7SevenAnalysisType type : Loto7SevenAnalysisType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public Loto7SevenAnalysisType nameOf(String name) {
    	for(Loto7SevenAnalysisType type : Loto7SevenAnalysisType.values()) {
    		if(name.equals(type.getName())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
