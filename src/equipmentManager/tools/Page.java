package equipmentManager.tools;

/**
 * ��������ҳ������
 * @author 嫲�
 * @version ����ʱ�䣺2018��7��9��
 * @version v1.0
 */
public class Page {
	// ������
	private int countRows;
	// ��ǰҳ��
	private int currentPage;
	// ÿҳ�������
	private int pageSize;
	// ����λ�ã���ҳ�ĵ�һ������
	private int index;
	// ��һҳ
	private int prevPage;
	// ��һҲ
	private int nextPage;
	// ��ҳ��
	private int countPage;

	/**
	 * @param pageSize
	 * @param currentPage
	 */
	public Page(String pageSize, String currentPage, int countRows) {
		// ��ʼ����������Ϣ
		this.countRows = countRows;
		// ����ǰҳ
		initCurrentPage(currentPage);
		// ����һ��ÿҳ����������
		initPageSize(pageSize);
		// ��ҳ��
		initCountPage();
		// ��ʼ��������ʼ��λ��
		initIndex();
		// ��һҳ
		initPrevPage();
		// ��һҳ
		initNextPage();
	}

	/**
	 * ��ҳ��
	 */
	private void initCountPage() {
		this.countPage = (countRows / pageSize) + (countRows % pageSize == 0 ? 0 : 1);

		// ����ǰҳ����Ϣ��������⣬֮���Է�����������Ϊ֮ǰ����û����ҳ����Ϣ
		if (this.currentPage > this.countPage) {
			this.currentPage = countPage;
		}
	}

	/**
	 * ��һҳ
	 */
	private void initNextPage() {
		if (this.currentPage > this.countPage) {
			this.nextPage = countPage;
		} else {
			this.nextPage = this.currentPage + 1;
		}
	}

	/**
	 * ��һҳ
	 */
	private void initPrevPage() {
		if (this.currentPage <= 1) {
			this.prevPage = 1;
		} else {
			this.prevPage = this.currentPage - 1;
		}
	}

	/**
	 * ����ÿҳ����������
	 * @param ps
	 */
	private void initPageSize(String ps) {
		if (null == ps || "".equals(ps.trim())) {
			this.pageSize = 5;
		} else {
			this.pageSize = Integer.parseInt(ps.trim());
		}
	}

	/**
	 * ����ǰҳ��Ϣ
	 */
	private void initCurrentPage(String cp) {
		if (null == cp || "".equals(cp.trim())) {
			this.currentPage = 1;
		}else {
			this.currentPage = Integer.parseInt(cp.trim());

			if (this.currentPage < 1) {
				this.currentPage = 1;
			}
		}

	}

	/**
	 * ��ʼ����ѯ������ʼ��λ
	 * @param pageSize2
	 * @param currentPage2
	 */
	private void initIndex() {
		this.index = (currentPage - 1) * pageSize;
	}

	/**
	 * @return the countRows
	 */
	public int getCountRows() {
		return countRows;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @return the prevPage
	 */
	public int getPrevPage() {
		return prevPage;
	}

	/**
	 * @return the nextPage
	 */
	public int getNextPage() {
		return nextPage;
	}

	/**
	 * @return the countPage
	 */
	public int getCountPage() {
		return countPage;
	}
}
