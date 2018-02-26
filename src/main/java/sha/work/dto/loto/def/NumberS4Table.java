package sha.work.dto.loto.def;

public enum NumberS4Table {

	 TURN		(0, "TURN"),  
	 OPEN_DT	(1, "OPEN_DT"),
	 L1			(2, "L1"),  
	 L2			(3, "L2"),
	 L3			(4, "L3"),
	 L4			(5, "L4");

	/** type. */
    private int id;
    private String name;

    private NumberS4Table(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public NumberS4Table valueOf(int id) {
    	for(NumberS4Table type : NumberS4Table.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public NumberS4Table nameOf(String name) {
    	for(NumberS4Table type : NumberS4Table.values()) {
    		if(name.equals(type.getName())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
