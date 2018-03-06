package sha.work.enums;

public enum PageOrderType {

	ASC(0, "asc", "昇順"),
	DESC(1, "desc", "降順");
	
    /** type. */
    private int id;
    private String key;
    private String name;

    private PageOrderType(int id, String key, String name) {
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
    

    
    public PageOrderType valueOf(int id) {
    	for(PageOrderType type : PageOrderType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public PageOrderType keyOf(String key) {
    	for(PageOrderType type : PageOrderType.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public PageOrderType nameOf(String name) {
    	for(PageOrderType type : PageOrderType.values()) {
    		if(name.equals(type.getName())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
