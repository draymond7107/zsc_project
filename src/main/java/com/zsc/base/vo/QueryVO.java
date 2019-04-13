package com.zsc.base.vo;


import com.zsc.base.abs._bean;
import com.zsc.constants.Constants;

/**
 * 后台查询VO
 * 功能说明：<br>
 * 模块名称：Base<br>
 * 功能描述：QueryVO<br>
 * 文件名称: QueryVO.java<br>
 * 系统名称：ICELOVE<br>
 * 软件著作权：ICELOVE 版权所有<br>
 * 开发人员：lujun <br>
 * 开发时间：2016-9-3 下午10:20:59<br>
 * 系统版本：1.0.0<br>
 */
public class QueryVO extends _bean {
    private static final long serialVersionUID = 1L;  //序列化使用
    private Integer pageNo = 1;        // 当前的页面
    private Integer pageSize = 20;    // 每页大小
    //定位ID分页(前端可以用)
    private Integer sinceId;
    private Integer maxId;
    private Integer count;
    private String orderByStr;
    //是否是更多查询
    private Integer moreQuery = 0;
    //是否隐藏查询表单
    private Integer hideQuery = 0;

    public boolean isMore() {
        return this.moreQuery != null && this.moreQuery.intValue() == Constants.YES;
    }

    public boolean isHideQuery() {
        return this.hideQuery != null && this.hideQuery.intValue() == Constants.YES;
    }

    public Integer getPageNo() {
        return pageNo == null ? 1 : pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        this.count = pageSize;
    }

    public Integer getSinceId() {
        return sinceId;
    }

    public void setSinceId(Integer sinceId) {
        this.sinceId = sinceId;
    }

    public Integer getMaxId() {
        return maxId;
    }

    public void setMaxId(Integer maxId) {
        this.maxId = maxId;
    }

    public void setCount(Integer count) {
        this.count = count;
        this.pageSize = count;
    }

    public void setOrderByStr(String orderByStr) {
        this.orderByStr = orderByStr;
    }

    public String getOrderByStr() {
        return orderByStr;
    }

    public void setMoreQuery(Integer moreQuery) {
        this.moreQuery = moreQuery;
    }

    public Integer getMoreQuery() {
        return moreQuery;
    }

    public Integer getHideQuery() {
        return hideQuery;
    }

    public void setHideQuery(Integer hideQuery) {
        this.hideQuery = hideQuery;
    }

    public int getCount() {
        return checkCountOrSize();
    }

    public Integer getPageSize() {
        return checkCountOrSize();
    }

    private int checkCountOrSize() {
        if(pageSize != null) {
            count = pageSize;
            return count.intValue();
        }
        if(count != null) {
            pageSize = count;
            return count.intValue();
        }
        return pageSize = count = 20;
    }

    public int getFirst() {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 20 : pageSize;
        return (pageNo - 1) * pageSize;
    }
}
