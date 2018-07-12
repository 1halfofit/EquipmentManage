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
			// ��ѯ�豸������Ϣ
			findEquipmentType(request, response);
		} else if (parameter.equals("saveEquipment")) {
			// �����豸��Ϣ
			saveEquipment(request, response);
		} else if (parameter.equals("findEquipmentList")) {
			// ��ѯ�豸��Ϣ�б�
			findEquipmentList(request, response);
		} else if (parameter.equals("deleteEquipment")) {
			// ɾ���豸��Ϣ
			deleteEquipment(request, response);
		} else if (parameter.equals("findEquipmentById")) {
			// ��ѯ�豸��Ϣ���Ե��޸�ҳ��
			findEquipmentById(request, response);
		} else if (parameter.equals("updateEquipment")) {
			// �޸��豸��Ϣ
			updateEquipment(request, response);
		}
	}


	/**
	 * �޸��豸��Ϣ
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
		// ��װ����
		EquipmentType equipmentType = new EquipmentType(Integer.parseInt(tid.trim()));
		Equipment equipment = new Equipment(Integer.parseInt(eid.trim()), ipAddress, name, introduce, buyTime, factory,
				equipmentType);
		int i = EquipmentDao.updateEquipment(equipment);
		if (i > 0) {
			// �޸ĳɹ������б�ҳ��
			findEquipmentList(request, response);
		} else {
			response.sendRedirect("error.jsp");
		}
	}

	/**
	 * ͨ��id������Ϣ
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
	 * ɾ���豸��Ϣ
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
			// ɾ���ɹ������б�ҳ��
			findEquipmentList(request, response);
		} else {
			response.sendRedirect("error.jsp");
		}
	}

	/**
	 * ����豸��Ϣ
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
		// ��װ����
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
	 * ��ѯ�����豸��Ϣ
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
	 * �����豸����
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
