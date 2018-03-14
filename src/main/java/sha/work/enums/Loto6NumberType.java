package sha.work.enums;

public enum Loto6NumberType {

	NOMAL_NUMBER(1, "numberValue", "本数字", "#2780E3"),
	BONUS_NUMBER1(2, "bonusNumber1Value", "ボーナス数字", "#9954BB");

    /** type. */
    private int id;
    private String key;
    private String name;
    private String barColor;

    private Loto6NumberType(int id, String key, String name, String barColor) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.barColor = barColor;
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
    
    public String getBarColor() {
        return barColor;
    }
    
    public Loto6NumberType valueOf(int id) {
    	for(Loto6NumberType type : Loto6NumberType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public Loto6NumberType keyOf(String key) {
    	for(Loto6NumberType type : Loto6NumberType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public Loto6NumberType nameOf(String name) {
    	for(Loto6NumberType type : Loto6NumberType.values()) {
    		if(name.equals(type.getName())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
