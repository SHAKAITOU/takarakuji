package sha.work.dto.loto.def;

public enum BinGo5Table {

	TURN		(0, "TURN"),  
	OPEN_DT	(1, "OPEN_DT"),
	L1			(2, "L1"),  
	L2			(3, "L2"),
	L3			(4, "L3"),
	L4			(5, "L4"),
	L5			(6, "L5"),
	L6			(7, "L6"),
	L7			(8, "L7"),
	L8			(9, "L8");

	/** type. */
	private int id;
	private String name;

	private BinGo5Table(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public BinGo5Table valueOf(int id) {
		for(BinGo5Table type : BinGo5Table.values()) {
			if(id == type.getId()) {
				return type;
			}
		}

		return null;
	}

	public BinGo5Table nameOf(String name) {
		for(BinGo5Table type : BinGo5Table.values()) {
			if(name.equals(type.getName())) {
				return type;
			}
		}

		return null;
	}
}
