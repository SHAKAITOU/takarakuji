package sha.work.common;

public enum HanyoType {

	LOTO7(1),
	LOTO6(2),
	MINILOTO(3),
	NUMBERS3(4),
	NUMBERS4(5),
	BINGO5(6);

    /** type. */
    private int id;

    private HanyoType(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public HanyoType valueOf(int id) {
    	for(HanyoType type : HanyoType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
