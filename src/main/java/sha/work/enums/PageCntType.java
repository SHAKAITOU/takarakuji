package sha.work.enums;

public enum PageCntType {

	MAX_10(10, "max10", "10件"),
	MAX_25(25, "max25", "25件"),
	MAX_50(50, "max50", "50件"),
	MAX_75(75, "max75", "75件"),
	MAX_100(100, "max100", "100件");
	
    /** type. */
    private int id;
    private String key;
    private String name;

    private PageCntType(int id, String key, String name) {
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
    

    
    public PageCntType valueOf(int id) {
    	for(PageCntType type : PageCntType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public PageCntType keyOf(String key) {
    	for(PageCntType type : PageCntType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public PageCntType nameOf(String name) {
    	for(PageCntType type : PageCntType.values()) {
    		if(name.equals(type.getName())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
