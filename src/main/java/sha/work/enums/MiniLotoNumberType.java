package sha.work.enums;

public enum MiniLotoNumberType {

	NOMAL_NUMBER(1, "numberValue", "本数字", "#2780E3"),
	BONUS_NUMBER1(2, "bonusNumber1Value", "ボーナス数字１番", "#9954BB");

    /** type. */
    private int id;
    private String key;
    private String name;
    private String barColor;

    private MiniLotoNumberType(int id, String key, String name, String barColor) {
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
    
    public MiniLotoNumberType valueOf(int id) {
    	for(MiniLotoNumberType type : MiniLotoNumberType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public MiniLotoNumberType keyOf(String key) {
    	for(MiniLotoNumberType type : MiniLotoNumberType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public MiniLotoNumberType nameOf(String name) {
    	for(MiniLotoNumberType type : MiniLotoNumberType.values()) {
    		if(name.equals(type.getName())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
