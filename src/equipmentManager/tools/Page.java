package equipmentManager.tools;

/**
 * 描述：分页工具类
 * @author 瀚博
 * @version 创建时间：2018年7月9日
 * @version v1.0
 */
public class Page {
	// 总条数
	private int countRows;
	// 当前页数
	private int currentPage;
	// 每页查多少条
	private int pageSize;
	// 索引位置，分页的第一个参数
	private int index;
	// 上一页
	private int prevPage;
	// 下一也
	private int nextPage;
	// 总页数
	private int countPage;

	/**
	 * @param pageSize
	 * @param currentPage
	 */
	public Page(String pageSize, String currentPage, int countRows) {
		// 初始化总条数信息
		this.countRows = countRows;
		// 处理当前页
		initCurrentPage(currentPage);
		// 处理一下每页多少条数据
		initPageSize(pageSize);
		// 总页数
		initCountPage();
		// 初始化索引开始的位置
		initIndex();
		// 上一页
		initPrevPage();
		// 下一页
		initNextPage();
	}

	/**
	 * 总页数
	 */
	private void initCountPage() {
		this.countPage = (countRows / pageSize) + (countRows % pageSize == 0 ? 0 : 1);

		// 处理当前页数信息过大的问题，之所以放在这里是因为之前处理没有总页数信息
		if (this.currentPage > this.countPage) {
			this.currentPage = countPage;
		}
	}

	/**
	 * 下一页
	 */
	private void initNextPage() {
		if (this.currentPage > this.countPage) {
			this.nextPage = countPage;
		} else {
			this.nextPage = this.currentPage + 1;
		}
	}

	/**
	 * 上一页
	 */
	private void initPrevPage() {
		if (this.currentPage <= 1) {
			this.prevPage = 1;
		} else {
			this.prevPage = this.currentPage - 1;
		}
	}

	/**
	 * 处理每页多少条数据
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
	 * 处理当前页信息
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
	 * 初始化查询索引开始的位
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
