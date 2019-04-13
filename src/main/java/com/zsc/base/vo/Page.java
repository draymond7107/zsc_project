package com.zsc.base.vo;
import com.zsc.base.utils.NumberUtils;

import java.util.List;
import java.util.Map;

/**
 * 工具包
 * @author icelove
 */
public class Page{
	private int pageNo = 1;// 当前的页面
	private int pageSize = 20;// 每页大小
	private int total;// 总记录数
	private int sinceId = -1;
	private int maxId = -1;
	private List<?> resultList;
	public Page (){}
	
	public Page (Map<?,?> map){
		int pageSize = NumberUtils.str2Int((String)map.get("pageSize"),20);
		if(pageSize <= 0)
			this.pageSize = 20;
		else
			this.pageSize = pageSize;
		this.total = NumberUtils.str2Int((String)map.get("total"),1000);
		this.sinceId = NumberUtils.str2Int((String)map.get("sinceId"),0);
		this.maxId = NumberUtils.str2Int((String)map.get("maxId"),0);
		this.pageNo = NumberUtils.str2Int((String)map.get("pageNo"),1);
		doPage(this.pageNo);
	}
	public Page (int pageSize, int recordCount, int currentPage){
		if(pageSize <= 0)
			this.pageSize = 20;
		else
			this.pageSize = pageSize;
		this.total = recordCount;
		doPage(currentPage);
	}
	/**
	 * 获取前端分页开始记录
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public static int getFirst(Integer pageNo,Integer pageSize){
		pageNo = pageNo==null?1:pageNo;
		pageSize = pageSize==null?20:pageSize;
		return (pageNo - 1) * pageSize;
	}
	public static int checkPageSize(Integer pageNo,Integer pageSize){
		pageNo = pageNo==null?1:pageNo;
		pageSize = pageSize==null?20:pageSize;
		return (pageNo - 1) * pageSize;
	}
	/**
	 * 总页数
	 * @return
	 */
	public int getPageCount(){
		int count = total / pageSize;// 得到刚好是整页的 页数
		int mod = total % pageSize;// 判断是否还有残余的记录
		if(mod != 0)
			count++;
		return total == 0?1:count;// 判断没有记录的时候
	}
	/**
	 * 验证当前页码(检验当前参数是否合法。不合法给他赋合法值)
	 * @param currentPage
	 */
	public void doPage(int currentPage){
		int tempCurrentPage = currentPage <= 0?1:currentPage;// 检查是否小于0
		tempCurrentPage = tempCurrentPage > getPageCount()?getPageCount():tempCurrentPage;// 是否超出了总的页数
		this.pageNo = tempCurrentPage;
	}
	/**
	 * 取第一个记录数
	 * @return
	 */
	public int getIndexOf(){
		int num = (pageNo - 1) * pageSize;
		return num;
	}
	/**
	 * 获取当前记录 sql 的
	 * @return
	 */
	public int getLastIndex(){
		int num = (pageNo * pageSize);
		return Math.min(num,total);
	}
	public void setPageSize(Integer pageSize){
		this.pageSize = (pageSize==null?20:pageSize.intValue());
	}
	public void setPageNo(Integer pageNo){
		this.pageNo = (pageNo==null?1:pageNo.intValue());
	}
	public int getPageNo(){
		return pageNo;
	}
	public int getNextPage(){
		if(this.pageNo < getPageCount()){
			return this.pageNo + 1;
		}else{
			return 1;
		}
	}
	public int getPageSize(){
		return pageSize;
	}
	public void setTotal(int total){
		this.total = total;
	}
	public int getTotal(){
		return total;
	}
	public String getPageStr(String url){
		if(url.indexOf("?") == -1){
			url += "?";
		}else
			url += "&";
		int pageCount = getPageCount();
		pageNo = getPageNo();
		StringBuffer sb = new StringBuffer("");
		sb.append("<div id='page_nav'>");
		sb.append("<li>共 <strong>" + getTotal() + "</strong> 条记录</li>\n");
		if(pageNo > 1){
			sb.append("<li><a href='" + url + "pageSize=" + pageSize + "&page=1" + "'>首页</a></li>\n");
			sb.append("<li><a href='" + url + "pageSize=" + pageSize + "&page=" + (pageNo - 1) + "'>上一页</a></li>\n");
		}else{
			sb.append("<li><font color=#6D6D6D>首页</font></li>\n");
			sb.append("<li><font color=#6D6D6D>上一页</font></li>\n");
		}
		if(pageNo < pageCount){
			sb.append("<li><a href='" + url + "pageSize=" + pageSize + "&page=" + (pageNo + 1) + "'>下一页</a></li>\n");
			sb.append("<li><a href='" + url + "pageSize=" + pageSize + "&page=" + pageCount + "'>尾页</a></li>\n");
		}else{
			sb.append("<li><font color=#6D6D6D>下一页</font></li>\n");
			sb.append("<li><font color=#6D6D6D>尾页</font></li>");
		}
		sb.append("<li>页次：<strong><font color=red>" + pageNo + "</font>/" + pageCount + "</strong>页</li>\n");
		sb.append("<li>每页显示：<input class='inputText' maxLength=4 size=3 value=" + pageSize + " onkeypress=\"javascript:if(event.keyCode==13){window.location='" + url + "page=1&pageSize='+this.value;return false}\"/>条</li>\n");
		sb.append("</div>\n");
		return sb.toString();
	}
	public int getSinceId(){
		return sinceId;
	}
	public void setSinceId(int sinceId){
		this.sinceId = sinceId;
	}
	public int getMaxId(){
		return maxId;
	}
	public void setMaxId(int maxId){
		this.maxId = maxId;
	}
	@SuppressWarnings("unchecked")
	public <T>T getResult(Class<T> t){
		return (T)resultList;
	}
	public List<?> getResultList(){
		return resultList;
	}
	public void setResultList(List<?> resultList){
		this.resultList = resultList;
	}
}
