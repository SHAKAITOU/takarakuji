package sha.work.dto.loto.def;

public enum Loto6Table {

	 TURN		(0, "TURN"),  
	 OPEN_DT	(1, "OPEN_DT"),
	 L1			(2, "L1"),  
	 L2			(3, "L2"),
	 L3			(4, "L3"),
	 L4			(5, "L4"),
	 L5			(6, "L5"),
	 L6			(7, "L6"),
	 B1			(8, "B1");

	/** type. */
    private int id;
    private String name;

    private Loto6Table(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public Loto6Table valueOf(int id) {
    	for(Loto6Table type : Loto6Table.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public Loto6Table nameOf(String name) {
    	for(Loto6Table type : Loto6Table.values()) {
    		if(name.equals(type.getName())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
