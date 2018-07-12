package equipmentManage.entity;

public class EquipmentType {

	public EquipmentType() {
	}

	private int tid;
	private String typeName;
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public EquipmentType(int tid, String typeName) {
		super();
		this.tid = tid;
		this.typeName = typeName;
	}
	public EquipmentType(int tid) {
		super();
		this.tid = tid;
	}

}
