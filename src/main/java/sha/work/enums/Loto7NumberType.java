package sha.work.enums;

public enum Loto7NumberType {

	NOMAL_NUMBER(1, "numberValue", "本数字", "#2780E3"),
	BONUS_NUMBER1(2, "bonusNumber1Value", "ボーナス数字１", "#9954BB"),
	BONUS_NUMBER2(3, "bonusNumber2Value", "ボーナス数字２", "#FF7518");

    /** type. */
    private int id;
    private String key;
    private String name;
    private String barColor;

    private Loto7NumberType(int id, String key, String name, String barColor) {
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
    
    public Loto7NumberType valueOf(int id) {
    	for(Loto7NumberType type : Loto7NumberType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public Loto7NumberType keyOf(String key) {
    	for(Loto7NumberType type : Loto7NumberType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public Loto7NumberType nameOf(String name) {
    	for(Loto7NumberType type : Loto7NumberType.values()) {
    		if(name.equals(type.getName())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
