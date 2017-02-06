/**************************************************************
 * 작성자 : Ally
 * 작성일시 : 2016/09/23
 **************************************************************/

package com.ksy4035.app.util;

public class Paging {
    // 한 페이지당 보여지는 게시글 수
    private int numPerPage = 4;
    // 게시물의 시작 rownum
    private int startNum;
    // 게시물의 마지막 rownum
    private int endNum;
    // 페이지 블럭 사이즈(한 화면에 보여지는 페이지 수)
    private int pagePerBlock = 2;
    // 총 페이지 수
    private int totalPage;
    // 시작 페이지
    private int startPage;
    // 마지막 페이지
    private int endPage;
    // 현재 블럭
    private int curBlock;
    // 현재 페이지
    private int curPage;
    // 페이징 HTML div의 스트링 값
    private String pagingDiv;
    
    public Paging(){}

    public Paging(int curPage, int totalCount){
        // 현재 페이지 값의 최소값을 1로 설정
        if(curPage <= 0){
            this.curPage = 1;
        }
        this.curPage = curPage;
        
        // 전체 게시글 수의 최소값을 1로 설정
        if(totalCount <= 0){
            totalCount = 1;
        }
        
        // 전체 페이지  = 전체 게시글 수를 페이지 당 게시글 수로 나누었을 때 나머지가 0이면 그 나눈 몫, 0이 아니면 그 나눈 몫에 +1 해준 값
        this.totalPage = totalCount % numPerPage == 0 ? totalCount / numPerPage : totalCount / numPerPage + 1;
        
        // 시작 게시글의 rownum = (현재 페이지 - 1) * 페이지당 게시글 수 + 1
        this.startNum = (curPage - 1) * numPerPage + 1; 
        
        // 마지막 게시글의 rownum = 현재 페이지 * 페이지당 게시글 수
        this.endNum = curPage * numPerPage;
        
        // 현재 블럭 = 현재 페이지를 블럭 사이즈로 나누었을 때 나머지가 0이면 그 나눈 몫, 0이 아니면 그 나눈 몫에 +1 해준 값
        this.curBlock = curPage % pagePerBlock == 0 ? curPage / pagePerBlock : curPage / pagePerBlock + 1;
        
        // 마지막 페이지 = 현재 블럭 * 블럭사이즈
        this.endPage = curBlock * pagePerBlock;
        
        // 만약 '마지막 페이지'가 '전체 페이지 수'보다 크다면 '마지막 페이지'를 '전체 페이지 수'로 설정, 작거나 같다면 '마지막 페이지'로 설정 
        endPage = endPage > totalPage ? totalPage : endPage;
        
        // 시작 페이지  = (현재 블럭 - 1) * 페이지 블럭 사이즈 + 1  
        if(startPage <= 0){
            this.startPage = 1;
        }
        this.startPage = (curBlock - 1) * pagePerBlock + 1;
        
        // 페이징 div 값을 생성해주는 setter 호출
        setPagingDiv();
    }
    
    /**
     * 페이징 HTML div 생성 메소드 
     */
    public void setPagingDiv() {
        pagingDiv = "<div class='pagination'><ul id='pagingul' class='pagination'>" +
                "<li><a href='/boardMain?curPage=1'>&laquo;</a></li>" +
                "<li><a href='/boardMain?curPage=" + (startPage-1) + "'>&lt;</a></li>";
        
        for(int i=startPage; i<=endPage; i++){
            pagingDiv += "<li><a href='/boardMain?curPage=" + i + "'>";
            if(curPage==i){
                pagingDiv +=                        "<strong>" + i + "</strong>";
            }else{
                pagingDiv += i;               
            }
            pagingDiv += "</a></li>";
        }
        
        if(endPage+1 > totalPage){
            pagingDiv += "<li><a href='/boardMain?curPage=" + (totalPage) + "'>&gt;</a></li>";
        }else{
            pagingDiv += "<li><a href='/boardMain?curPage=" + (endPage+1) + "'>&gt;</a></li>";             
        }
        pagingDiv += "<li><a href='/boardMain?curPage=" + (totalPage) + "'>&raquo;</a></li></ul></div>";
    }
    
    public void setCurPage(int curPage){
        this.curPage = curPage;
    }
    
    public int getStartNum(){
        return startNum; 
    }
    
    public int getEndNum(){
        return endNum; 
    }
    
    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }
    
    public int getTotalPage(){
        return totalPage;
    }
    
    public int getCurPage(){
        return curPage;
    }

    public String getPagingDiv() {
        return pagingDiv;
    }

    
}
