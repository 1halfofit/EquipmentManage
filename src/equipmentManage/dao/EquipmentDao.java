package equipmentManage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import equipmentManage.entity.Equipment;
import equipmentManage.entity.EquipmentType;
import equipmentManage.tools.C3p0Util;

public class EquipmentDao {

	public EquipmentDao() {
	}

	/**
	 * 查询设备类型
	 * @return List<EquipmentType>
	 */
	public List<EquipmentType> findEquipmentType() {
		List<EquipmentType> list = new ArrayList<EquipmentType>();
		Connection connection = C3p0Util.getConn();
		try {
			PreparedStatement ps = connection.prepareStatement("select * from equipment_type");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int tid = rs.getInt("tid");
				String typeName = rs.getString("typename");
				EquipmentType equipmentType = new EquipmentType(tid, typeName);
				list.add(equipmentType);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3p0Util.closeConn(connection);
		}

		return list;
	}

	/**
	 * 保存数据
	 * @param equipment
	 * @return
	 */
	public static int saveEquipment(Equipment equipment) {

		int i = 0;
		Connection connection = C3p0Util.getConn();
		try {
			PreparedStatement ps = connection.prepareStatement("insert into equipment values(?,?,?,?,?,?,?)");
			ps.setInt(1, 0);
			ps.setString(2, equipment.getIpAddress());
			ps.setString(3, equipment.getName());
			ps.setString(4, equipment.getIntroduce());
			ps.setString(5, equipment.getBuyTime());
			ps.setString(6, equipment.getFactory());
			ps.setInt(7, equipment.getEquipmentType().getTid());

			i = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3p0Util.closeConn(connection);
		}

		return i;
	}

	/**
     *查询功能
	 * @return List<Equipment>
	 */
	public List<Equipment> findEquipmentList() {

		List<Equipment> list = new ArrayList<Equipment>();

		Connection connection = C3p0Util.getConn();
		String sql = "SELECT * FROM equipment e LEFT JOIN equipment_type t ON e.tid = t.tid";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int eid = rs.getInt("eid");
				String name = rs.getString("name");
				String ipAddress = rs.getString("ipaddress");
				String introduce = rs.getString("introduce");
				String buyTime = rs.getString("buytime");
				String factory = rs.getString("factory");
				int tid = rs.getInt("tid");
				String typeName = rs.getString("typename");
				Equipment equipment = new Equipment(eid, ipAddress, name, introduce, buyTime, factory,
						new EquipmentType(tid, typeName));
				list.add(equipment);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3p0Util.closeConn(connection);
		}
		return list;
	}

	public static int deleteEquipment(String eid) {
		int i = 0;
		Connection connection = C3p0Util.getConn();
		String sql = "delete from equipment where eid = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(eid.trim()));
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3p0Util.closeConn(connection);
		}
		return i;
	}

	public static Equipment findEquipmentById(int eid_parm) {
		Connection connection = C3p0Util.getConn();
		Equipment equipment = null;
		String sql = "SELECT * FROM equipment where eid =?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, eid_parm);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int eid = rs.getInt("eid");
			String name = rs.getString("name");
			String ipAddress = rs.getString("ipaddress");
			String introduce = rs.getString("introduce");
			String buyTime = rs.getString("buytime");
			String factory = rs.getString("factory");
			int tid = rs.getInt("tid");
			equipment = new Equipment(eid, ipAddress, name, introduce, buyTime, factory, new EquipmentType(tid));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3p0Util.closeConn(connection);
		}
		return equipment;
	}

	public static int updateEquipment(Equipment equipment) {
		int i = 0;
		Connection connection = C3p0Util.getConn();
		try {
			String sql = "UPDATE equipment SET ipaddress=?,NAME=?,introduce=?,buytime=?,factory=?,tid=? WHERE eid=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, equipment.getIpAddress());
			ps.setString(2, equipment.getName());
			ps.setString(3, equipment.getIntroduce());
			ps.setString(4, equipment.getBuyTime());
			ps.setString(5, equipment.getFactory());
			ps.setInt(6, equipment.getEquipmentType().getTid());
			ps.setInt(7, equipment.getEid());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3p0Util.closeConn(connection);
		}

		return i;
	}


}
