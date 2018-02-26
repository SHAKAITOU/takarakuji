package sha.work.common;

public enum Loto7Type {

	NOMAL_NUMBER(1, "通常数字"),
	BONUS_NUMBER1(2, "ボーナス数字１番"),
	BONUS_NUMBER2(3, "ボーナス数字２番");

    /** type. */
    private int id;
    private String name;

    private Loto7Type(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public Loto7Type valueOf(int id) {
    	for(Loto7Type type : Loto7Type.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public Loto7Type nameOf(String name) {
    	for(Loto7Type type : Loto7Type.values()) {
    		if(name.equals(type.getName())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
