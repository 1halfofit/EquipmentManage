package equipmentManager.entity;
/**
* @author  瀚博
* @version 创建时间：2018年7月12日
* @version v1.0
*/
public class Equipment {
	
		public Equipment() {
		super();
	}
		private int eid;
		private String ipAddress;
		private String name;
		private String introduce;
		private String buyTime;
		private String factory;
		private EquipmentType equipmentType;
		public int getEid() {
			return eid;
		}
		public void setEid(int eid) {
			this.eid = eid;
		}
		public String getIpAddress() {
			return ipAddress;
		}
		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getIntroduce() {
			return introduce;
		}
		public void setIntroduce(String introduce) {
			this.introduce = introduce;
		}
		public String getBuyTime() {
			return buyTime;
		}
		public void setBuyTime(String buyTime) {
			this.buyTime = buyTime;
		}
		public String getFactory() {
			return factory;
		}
		public void setFactory(String factory) {
			this.factory = factory;
		}
		public EquipmentType getEquipmentType() {
			return equipmentType;
		}
		public void setEquipmentType(EquipmentType equipmentType) {
			this.equipmentType = equipmentType;
		}
		public Equipment(int eid, String ipAddress, String name, String introduce, String buyTime, String factory,
				EquipmentType equipmentType) {
			super();
			this.eid = eid;
			this.ipAddress = ipAddress;
			this.name = name;
			this.introduce = introduce;
			this.buyTime = buyTime;
			this.factory = factory;
			this.equipmentType = equipmentType;
		}

		public Equipment(String ipAddress, String name, String introduce, String buyTime, String factory,
				EquipmentType equipmentType) {
			super();
			this.ipAddress = ipAddress;
			this.name = name;
			this.introduce = introduce;
			this.buyTime = buyTime;
			this.factory = factory;
			this.equipmentType = equipmentType;
		}
		public Equipment(String name, String factory) {
			super();
			this.name = name;
			this.factory = factory;
		}
		
}
