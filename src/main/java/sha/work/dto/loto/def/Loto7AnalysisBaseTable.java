package sha.work.dto.loto.def;

public enum Loto7AnalysisBaseTable {

	  TURN						(0, "TURN"),  
	  MAX_NUM					(1, "MAX_NUM"),
	  MIN_NUM					(2, "MIN_NUM"),
	  MAX_MIN_DIFF			(3, "MAX_MIN_DIFF"),
	  TOTAL_AVG				(4, "TOTAL_AVG"),
	  MAX_NUM_DIFF			(5, "MAX_NUM_DIFF"),
	  MIN_NUM_DIFF			(6, "MIN_NUM_DIFF"),
	  NUM_DIFF_AVG			(7, "NUM_DIFF_AVG"),
	  EVEN_NUM_CNT			(8, "EVEN_NUM_CNT"),
	  ODD_NUM_CNT				(9, "ODD_NUM_CNT"),
	  SERIAL_NUM_CNT			(10, "SERIAL_NUM_CNT"),
	  LEFT_AREA_NUM_CNT		(11, "LEFT_AREA_NUM_CNT"),
	  CENTER_AREA_NUM_CNT	(12, "CENTER_AREA_NUM_CNT"),
	  RIGHT_AREA_NUM_CNT		(13, "RIGHT_AREA_NUM_CNT");

	/** type. */
    private int id;
    private String name;

    private Loto7AnalysisBaseTable(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public Loto7AnalysisBaseTable valueOf(int id) {
    	for(Loto7AnalysisBaseTable type : Loto7AnalysisBaseTable.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public Loto7AnalysisBaseTable nameOf(String name) {
    	for(Loto7AnalysisBaseTable type : Loto7AnalysisBaseTable.values()) {
    		if(name.equals(type.getName())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
