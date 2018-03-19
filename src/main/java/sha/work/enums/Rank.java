package sha.work.enums;

public enum Rank {

	A			(1, 10, "A", 	"Aランク"),
	B			(2, 9, "B", 	"Bランク"),
	C			(3, 8, "C", 	"Cランク"),
	D			(4, 7, "D", 	"Dランク"),
	E			(5, 6, "E", 	"Eランク"),
	F			(6, 5, "F", 	"Fランク"),
	G			(7, 4, "G", 	"Gランク"),
	H			(8, 3, "H", 	"Hランク"),
	I			(9, 2, "I", 	"Iランク"),
	J			(10,1, "J", 	"Jランク"),
	K			(11, 0, "K", 	"Kランク"),
	L			(12, 0, "L", 	"Lランク"),
	M			(13, 0, "M", 	"Mランク"),
	N			(14, 0, "N", 	"Nランク"),
	O			(15, 0, "O", 	"Oランク"),
	P			(16, 0, "P", 	"Pランク"),
	Q			(17, 0, "Q", 	"Qランク"),
	R			(18, 0, "R", 	"Rランク"),
	S			(19, 0, "S", 	"Sランク"),
	T			(20, 0, "T", 	"Tランク"),
	U			(21, 0, "U", 	"Uランク"),
	V			(22, 0, "V", 	"Vランク"),
	W			(23, 0, "W", 	"Wランク"),
	X			(24, 0, "X", 	"Xランク"),
	Y			(25, 0, "Y", 	"Yランク"),
	Z			(26, 0, "Z", 	"Zランク"),
	
	;

    /** type. */
    private int id;
    private int point;
    private String key;
    private String name;

    private Rank(int id, int point, String key, String name) {
        this.id = id;
        this.point = point;
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
    
    public int getPoint() {
        return point;
    }
    
    public Rank valueOf(int id) {
    	for(Rank type : Rank.values()) {
    		if(id == type.getId()) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public Rank keyOf(String key) {
    	for(Rank type : Rank.values()) {
    		if(key.equals(type.getKey())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
    
    public Rank nameOf(String name) {
    	for(Rank type : Rank.values()) {
    		if(name.equals(type.getName())) {
    			return type;
    		}
    	}
    	
    	return null;
    }
}
