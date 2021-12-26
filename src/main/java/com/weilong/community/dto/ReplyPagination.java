package com.weilong.community.dto;

import java.util.ArrayList;
import java.util.List;

public class ReplyPagination {
    private boolean showPrevious; //是否有上一页的图标
    private boolean showFirstPage; //是否有首页的图标
    private boolean shownext;  //是否有下一页的图标
    private boolean showEndPage;  //是否展示尾页图标
    private Integer page;  //当前页面数
    private List<Integer> pages =new ArrayList<Integer>();  //页面数量
    private Integer totalpage;  //总分页数

    public boolean isShowPrevious() {
        return showPrevious;
    }

    public void setShowPrevious(boolean showPrevious) {
        this.showPrevious = showPrevious;
    }

    public boolean isShowFirstPage() {
        return showFirstPage;
    }

    public void setShowFirstPage(boolean showFirstPage) {
        this.showFirstPage = showFirstPage;
    }

    public boolean isShownext() {
        return shownext;
    }

    public void setShownext(boolean shownext) {
        this.shownext = shownext;
    }

    public boolean isShowEndPage() {
        return showEndPage;
    }

    public void setShowEndPage(boolean showEndPage) {
        this.showEndPage = showEndPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public Integer getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(Integer totalpage) {
        this.totalpage = totalpage;
    }
    public void setPagination(Integer totalpage, Integer page) {
        this.totalpage=totalpage;
        this.page=page;
        //忘pages里面加数字
        pages.add(page);
        for (int i = 1; i <=3 ; i++) {
            if(page-i>0){
                pages.add(0,page-i);
            }
            if(page +i<=totalpage){
                pages.add(page+i);
            }
        }
        //是否展示上一页图标
        if(page==1){
            showPrevious=false;
        }else{
            showPrevious=true;
        }
        //是否展示下一页图标
        if(page==totalpage){
            shownext=false;
        }else{
            shownext=true;
        }
        //是否展示首页图标
        if(pages.contains(1)){
            showFirstPage=false;
        }else{
            showFirstPage=true;
        }
        //是否展示末页图标
        if(pages.contains(totalpage)){
            showEndPage=false;
        }else{
            showEndPage=true;
        }
    }
}
