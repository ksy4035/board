package com.ksy4035.app.domain;

import org.springframework.web.multipart.MultipartFile;

public class ImageDTO {
    private String CKEditorFuncNum;
    private MultipartFile Upload;
    private String newFilename;
    private String imageUrl;
    
    public String getCKEditorFuncNum() {
        return CKEditorFuncNum;
    }
    public void setCKEditorFuncNum(String cKEditorFuncNum) {
        CKEditorFuncNum = cKEditorFuncNum;
    }
    public MultipartFile getUpload() {
        return Upload;
    }
    public void setUpload(MultipartFile upload) {
        Upload = upload;
    }
    public String getNewFilename() {
        return newFilename;
    }
    public void setNewFilename(String newFilename) {
        this.newFilename = newFilename;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    
}
