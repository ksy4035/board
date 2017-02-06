/**************************************************************
 * 작성자 : Ally
 * 작성일시 : 2016/09/20
 **************************************************************/

package com.ksy4035.app.domain;

public class BoardDTO {
    /**
     * 게시판 일련번호 
     */
    private int     idx;
    /**
     * 제목 
     */
    private String  title;
    /**
     * 작성자 
     */
    private String  writer;
    /**
     * 조회수 
     */
    private int     count;
    /**
     * 등록일시 
     */
    private String  regdate;
    /**
     * 내용 
     */
    private String  content;
    
    public int getIdx() {
        return idx;
    }
    public void setIdx(int idx) {
        this.idx = idx;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public String getRegdate() {
        return regdate;
    }
    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
    
}
