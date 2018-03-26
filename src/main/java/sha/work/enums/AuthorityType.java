package sha.work.enums;

public enum AuthorityType {

	ROLE_USER(0, "USER", "ROLE_USER"),
	ROLE_ADMIN(1, "ADMIN", "ROLE_ADMIN");
    
	/** type. */
    private int id;
    /** type. */
    private String name;

    private String role;
    
    private AuthorityType(int id, String name, String role) {
    	this.id = id;
        this.name = name;
        this.role = role;

    }
    
    public int getId() {
    	return id;
    }
    
    public String getName() {
    	return name;
    }
    
    public String getRole() {
    	return role;
    }

    
    public AuthorityType idOf(int id) {
    	for(AuthorityType type : AuthorityType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public AuthorityType nameOf(String name) {
    	for(AuthorityType type : AuthorityType.values()) {
    		if(name.equals(type.toString())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
