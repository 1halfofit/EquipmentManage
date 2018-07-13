package equipmentManager.entity;
/**
* @author  瀚博
* @version 创建时间：2018年7月12日
* @version v1.0
*/
public class EquipmentType {

	public EquipmentType() {
		super();
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
