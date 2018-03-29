package sha.work.enums;

public enum ExecuteReturnType {

	OK(1),
	NG(2),
	DEFAULT(3);

    /** type. */
    private int id;

    private ExecuteReturnType(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public ExecuteReturnType valueOf(int id) {
    	for(ExecuteReturnType type : ExecuteReturnType.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
