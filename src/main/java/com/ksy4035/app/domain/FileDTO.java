/**************************************************************
 * 작성자 : Ally
 * 작성일시 : 2016/09/20
 **************************************************************/

package com.ksy4035.app.domain;

public class FileDTO {
    private int file_idx;
    private String org_name;
    private String file_name;
    private long file_size;
    private String file_path;
    private int idx;
    
    public int getFile_idx() {
        return file_idx;
    }
    public void setFile_idx(int file_idx) {
        this.file_idx = file_idx;
    }
    public String getOrg_name() {
        return org_name;
    }
    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }
    public String getFile_name() {
        return file_name;
    }
    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
    public long getFile_size() {
        return file_size;
    }
    public void setFile_size(long file_size) {
        this.file_size = file_size;
    }
    public int getIdx() {
        return idx;
    }
    public void setIdx(int idx) {
        this.idx = idx;
    }
    public String getFile_path() {
        return file_path;
    }
    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
    
    
}
