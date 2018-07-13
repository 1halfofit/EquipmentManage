package equipmentManager.service;

import java.util.List;

import equipmentManager.dao.EquipmentDao;
import equipmentManager.entity.Equipment;
import equipmentManager.entity.EquipmentType;
import equipmentManager.tools.Page;

/**
 * @author 瀚博
 * @version 创建时间：2018年7月12日
 * @version v1.0
 */
public class EquipentService {

	public EquipentService() {
	}

	private static EquipmentDao equipmentDao = new EquipmentDao();

	/**
	 * @param page
	 * @param equipment
	 * @return equipmentDao.findList(page,equipment)
	 */
	public static List<Equipment> findList(Page page, Equipment equipment) {
		return equipmentDao.findList(page, equipment);
	}

	/**
	 * @param equipment
	 * @return equipmentDao.findEquipmentCount();
	 */
	public static int findEquipmentCount(Equipment equipment) {
		return equipmentDao.findEquipmentCount(equipment);
	}

	/**
	 * @param equipment
	 * @return equipmentDao.updateEquipment(equipment);
	 */
	public static int updateEquipment(Equipment equipment) {
		return equipmentDao.updateEquipment(equipment);
	}

	/**
	 * @param parseInt
	 * @return equipmentDao.findEquipmentById(parseInt);
	 */
	public static Equipment findEquipmentById(int parseInt) {
		return equipmentDao.findEquipmentById(parseInt);
	}

	/**
	 * @return equipmentDao.findEquipmentType();
	 */
	public List<EquipmentType> findEquipmentType() {
		return equipmentDao.findEquipmentType();
	}

	/**
	 * @param eid
	 * @return equipmentDao.deleteEquipment(eid);
	 */
	public int deleteEquipment(String eid) {
		return equipmentDao.deleteEquipment(eid);
	}

	/**
	 * @param equipment
	 * @return equipmentDao.saveEquipment(equipment);
	 */
	public int saveEquipment(Equipment equipment) {
		return equipmentDao.saveEquipment(equipment);
	}
}
