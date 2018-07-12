package equipmentManage.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import equipmentManage.dao.EquipmentDao;
import equipmentManage.entity.Equipment;
import equipmentManage.entity.EquipmentType;

public class EquipmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EquipmentDao equipmentDao = new EquipmentDao();

	public EquipmentServlet() {
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String parameter = request.getParameter("method");
		if (parameter.equals("findEquipmentType")) {
			// 查询设备类型信息
			findEquipmentType(request, response);
		} else if (parameter.equals("saveEquipment")) {
			// 增加设备信息
			saveEquipment(request, response);
		} else if (parameter.equals("findEquipmentList")) {
			// 查询设备信息列表
			findEquipmentList(request, response);
		} else if (parameter.equals("deleteEquipment")) {
			// 删除设备信息
			deleteEquipment(request, response);
		} else if (parameter.equals("findEquipmentById")) {
			// 查询设备信息回显到修改页面
			findEquipmentById(request, response);
		} else if (parameter.equals("updateEquipment")) {
			// 修改设备信息
			updateEquipment(request, response);
		}
	}


	/**
	 * 修改设备信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void updateEquipment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String eid = request.getParameter("eid");
		String name = request.getParameter("name");
		String ipAddress = request.getParameter("ipAddress");
		String introduce = request.getParameter("introduce");
		String buyTime = request.getParameter("buyTime");
		String factory = request.getParameter("factory");
		String tid = request.getParameter("equipmentType.tid");
		// 封装操作
		EquipmentType equipmentType = new EquipmentType(Integer.parseInt(tid.trim()));
		Equipment equipment = new Equipment(Integer.parseInt(eid.trim()), ipAddress, name, introduce, buyTime, factory,
				equipmentType);
		int i = EquipmentDao.updateEquipment(equipment);
		if (i > 0) {
			// 修改成功返回列表页面
			findEquipmentList(request, response);
		} else {
			response.sendRedirect("error.jsp");
		}
	}

	/**
	 * 通过id查找信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void findEquipmentById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String eid = request.getParameter("eid");
		Equipment equipment = EquipmentDao.findEquipmentById(Integer.parseInt(eid.trim()));
		List<EquipmentType> list = equipmentDao.findEquipmentType();
		request.setAttribute("list", list);
		request.setAttribute("equipment", equipment);
		request.getRequestDispatcher("update_equipment.jsp").forward(request, response);
	}

	/**
	 * 删除设备信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void deleteEquipment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String eid = request.getParameter("eid");
		int i = EquipmentDao.deleteEquipment(eid);
		if (i > 0) {
			// 删除成功返回列表页面
			findEquipmentList(request, response);
		} else {
			response.sendRedirect("error.jsp");
		}
	}

	/**
	 * 添加设备信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void saveEquipment(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String name = request.getParameter("name");
		String ipAddress = request.getParameter("ipAddress");
		String introduce = request.getParameter("introduce");
		String buyTime = request.getParameter("buyTime");
		String factory = request.getParameter("factory");
		String tid = request.getParameter("equipmentType.tid");
		// 封装操作
		EquipmentType equipmentType = new EquipmentType(Integer.parseInt(tid.trim()));
		Equipment equipment = new Equipment(ipAddress, name, introduce, buyTime, factory, equipmentType);
		int i = EquipmentDao.saveEquipment(equipment);
		if (i > 0) {
			findEquipmentList(request, response);
		} else {
			response.sendRedirect("error.jsp");
		}
	}

	/**
	 * 查询所有设备信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void findEquipmentList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Equipment> list = equipmentDao.findEquipmentList();
		request.setAttribute("list", list);
		request.getRequestDispatcher("equipment_list.jsp").forward(request, response);
	}

	/**
	 * 回显设备类型
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void findEquipmentType(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<EquipmentType> list = equipmentDao.findEquipmentType();
		request.setAttribute("list", list);
		request.getRequestDispatcher("save_equipment.jsp").forward(request, response);
	}
}
