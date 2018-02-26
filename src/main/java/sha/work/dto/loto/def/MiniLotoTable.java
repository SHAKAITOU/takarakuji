package sha.work.dto.loto.def;

public enum MiniLotoTable {

	 TURN		(0, "TURN"),  
	 OPEN_DT	(1, "OPEN_DT"),
	 L1			(2, "L1"),  
	 L2			(3, "L2"),
	 L3			(4, "L3"),
	 L4			(5, "L4"),
	 L5			(6, "L5"),
	 B1			(7, "B1");

	/** type. */
    private int id;
    private String name;

    private MiniLotoTable(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public MiniLotoTable valueOf(int id) {
    	for(MiniLotoTable type : MiniLotoTable.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public MiniLotoTable nameOf(String name) {
    	for(MiniLotoTable type : MiniLotoTable.values()) {
    		if(name.equals(type.getName())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
