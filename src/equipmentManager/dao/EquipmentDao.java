package equipmentManager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import equipmentManager.tools.C3p0Util;
import equipmentManager.entity.Equipment;
import equipmentManager.entity.EquipmentType;
import equipmentManager.tools.Page;

/**
 * @author 瀚博
 * @version 创建时间：2018年7月12日
 * @version v1.0
 */
public class EquipmentDao {
	/**
	 * 修改设备信息
	 * @param parseInt
	 * @return int
	 */
	public int updateEquipment(Equipment equipment) {
		int i = 0;
		Connection connection = C3p0Util.getConn();
		try {
			String sql = "UPDATE equipment SET ipaddress = ?,NAME=?,introduce=?,buytime=?,factory=?,tid=? WHERE eid = ?";
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

	/**
	 * 根据Id查询设备信息
	 * @param eid_parm
	 * @return Equipment
	 */
	public Equipment findEquipmentById(int eid_parm) {
		Equipment equipment = null;
		Connection connection = C3p0Util.getConn();
		String sql = "SELECT * FROM equipment where eid =?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, eid_parm);
			ResultSet rs = ps.executeQuery();
			// 移动指针
			rs.next();
			int eid = rs.getInt("eid");
			String name = rs.getString("name");
			String ipAddress = rs.getString("ipaddress");
			String content = rs.getString("introduce");
			String buyTime = rs.getString("buytime");
			String factory = rs.getString("factory");
			// 设备类型的外键tid
			int tid = rs.getInt("tid");
			equipment = new Equipment(eid, ipAddress, name, content, buyTime, factory, new EquipmentType(tid));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3p0Util.closeConn(connection);
		}
		return equipment;
	}

	/**
	 * 查找设备类型
	 * @param 
	 * @return list
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
	 * 删除设备信息
	 * 
	 * @param eid
	 * @return int
	 */
	public int deleteEquipment(String eid) {
		int i = 0;
		Connection connection = C3p0Util.getConn();
		try {
			String sql = "delete from equipment where eid =?";
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

	/**
	 * 增加设备信息
	 * @param equipment
	 * @return int
	 */
	public int saveEquipment(Equipment equipment) {
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
	 * 查询列表
	 * @param page
	 * @param equipment
	 * @return List<Equipment>
	 */
	public List<Equipment> findList(Page page, Equipment equipment) {
		List<Equipment> list = new ArrayList<Equipment>();
		Connection connection = C3p0Util.getConn();
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM equipment e LEFT JOIN equipment_type t ON e.tid = t.tid where 1=1 ");
		if (null != equipment && null != equipment.getName() && !"".equals(equipment.getName().trim())) {
			sql.append(" and name like '%" + equipment.getName() + "%'");
		}
		if (null != equipment && null != equipment.getFactory() && !"".equals(equipment.getFactory().trim())) {
			sql.append(" and factory like '%" + equipment.getFactory() + "%'");
		}
		sql.append(" limit ?,?");
		try {
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, page.getIndex());
			ps.setInt(2, page.getPageSize());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int eid = rs.getInt("eid");
				String ipAddress = rs.getString("ipaddress");
				String name = rs.getString("name");
				String introduce = rs.getString("introduce");
				String buyTime = rs.getString("buytime");
				String factory = rs.getString("factory");
				// 这俩是设备类型的数据
				int tid = rs.getInt("tid");
				String typeName = rs.getString("typename");
				Equipment equipment1 = new Equipment(eid, ipAddress, name, introduce, buyTime, factory,
						new EquipmentType(tid, typeName));
				list.add(equipment1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3p0Util.closeConn(connection);
		}
		return list;
	}

	/**
	 * 查询总条数
	 * @param equipment
	 * @return int
	 */
	public int findEquipmentCount(Equipment equipment) {
		int count = 0;
		Connection connection = C3p0Util.getConn();
		StringBuffer sql = new StringBuffer("select count(*) from equipment where 1=1 ");
		if (null != equipment && null != equipment.getName() && !"".equals(equipment.getName().trim())) {
			sql.append(" and name like '%" + equipment.getName() + "%'");
		}
		if (null != equipment && null != equipment.getFactory() && !"".equals(equipment.getFactory().trim())) {
			sql.append(" and factory like '%" + equipment.getFactory() + "%'");
		}
		try {
			PreparedStatement ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			rs.next();
			// 指针移动后直接接收第一行的唯一的一个字段
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3p0Util.closeConn(connection);
		}
		return count;
	}

}
