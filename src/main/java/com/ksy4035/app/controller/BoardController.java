/**************************************************************
 * 작성자 : Ally
 * 작성일시 : 2016/09/20
 **************************************************************/

package com.ksy4035.app.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ksy4035.app.domain.BoardDTO;
import com.ksy4035.app.domain.FileDTO;
import com.ksy4035.app.domain.ImageDTO;
import com.ksy4035.app.service.BoardService;
import com.ksy4035.app.util.Paging;

@Controller
public class BoardController {
	
    @Autowired
    private BoardService service;
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	/**
	 * 인덱스 페이지
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
	    
	    logger.info("Welcome home!");
		return "index";
		
	}
	
	/**
     * 게시판 목록
     */
    @RequestMapping(value = "/boardMain", method = RequestMethod.GET)
    public void boardMain(Model model, Integer curPage) throws Exception {

        if(curPage == null || curPage <= 0){
            curPage = 1;
        }
        
        // 현재 페이지와 게시글의 총 개수를 넘겨 페이징 처리
        Paging paging = new Paging(curPage, service.totalCount());

        // 게시글의 시작과 마지막 rownum 번호를 map에 담아 넘겨 게시글의 리스트를 뽑아줌
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startNum", paging.getStartNum());
        map.put("endNum", paging.getEndNum());
        List<BoardDTO> list = service.list(map);

        // 게시글 리스트와 페이징 처리된 값들을 모델에 담아 뷰로 보내준다.
        model.addAttribute("list", list);
        model.addAttribute("paging", paging);

    }
    
    /**
     * 게시글 상세 페이지
     */
    @RequestMapping(value = "/boardRead", method = RequestMethod.GET)
    public void boardRead(Integer idx, Model model, Integer curPage) throws Exception {
        
        // 조회수 증가
        service.updateCount(idx);
        
        // 해당 게시글에 등록된 파일 리스트를 뽑아 모델에 담아 뷰로 보냄. 
        List<FileDTO> fileList = service.fileList(idx);
        if(fileList != null){
            model.addAttribute("fileList", fileList);
        }
        model.addAttribute("curPage", curPage);
        model.addAttribute("dto", service.read(idx));
        
    }
    
    /**
     * 게시글 등록 폼
     */
    @RequestMapping(value = "/boardRegister", method = RequestMethod.GET)
    public void boardRegister(Model model) {
    }
    
    /**
     * 게시글 등록 완료
     */
    @RequestMapping(value = "/boardRegister", method = RequestMethod.POST)
    public String registerComplete(BoardDTO dto, RedirectAttributes rttr, MultipartHttpServletRequest request) throws IllegalStateException, IOException {
                
        BoardDTO boardDto = service.insert(dto);
        
        // 파일을 저장할 폴더 생성
        String realFolder = "D:\\Workspace\\Board\\src\\main\\webapp\\resources\\upload\\attachment\\";
        
        File dir = new File(realFolder);
        if(!dir.isDirectory()){
            dir.mkdirs();
        }
        
        List<MultipartFile> image = request.getFiles("upload");
        System.out.println("image.size() : " + image.size());
        // 첨부한 파일을 리스트로 저장
        List<MultipartFile> mf = request.getFiles("uploadFile");
        System.out.println("mf : " + mf.size());
        if(mf != null){
            for(int i=0; i<mf.size(); i++){
                if (!mf.get(i).getOriginalFilename().equals("")) {
                    // 실제 파일명
                    String org_name = mf.get(i).getOriginalFilename();
                    System.out.println("fileName : " + org_name);
                    // 저장되는 파일명(파일명 중복방지 처리)
                    String file_name = System.currentTimeMillis() + "_" + org_name;
                    // 저장될 파일 경로
                    String savePath = realFolder + file_name;
                    // 파일 사이즈
                    long file_size = mf.get(i).getSize();
                    // 파일 저장
                    mf.get(i).transferTo(new File(savePath));
                    
                    FileDTO fileDto = new FileDTO();
                    fileDto.setOrg_name(org_name);
                    fileDto.setFile_name(file_name);
                    fileDto.setFile_size(file_size);
                    fileDto.setFile_path(realFolder);
                    fileDto.setIdx(boardDto.getIdx());
                    
                    service.fileUpload(fileDto);
                }
            }
        }
        rttr.addFlashAttribute("msg", "등록되었습니다.");
        
        return "redirect:/boardMain";
        
    }
    
    /**
     * 게시글 수정 폼
     */
    @RequestMapping(value = "/boardUpdate", method = RequestMethod.GET)
    public void boardUpdate(Integer idx, Model model, Integer curPage) throws Exception {
        
        // 해당 게시글에 등록된 파일 리스트를 뽑아 모델에 담아 뷰로 보냄. 
        List<FileDTO> fileList = service.fileList(idx);
        if(fileList != null){
            model.addAttribute("fileList", fileList);
        }
        
        model.addAttribute("curPage", curPage);
        model.addAttribute("dto", service.read(idx));
        
    }   
    
    /**
     * 게시글 수정 완료
     */
    @RequestMapping(value = "/boardUpdate", method = RequestMethod.POST)
    public String updateComplete(BoardDTO dto, RedirectAttributes rttr, Integer curPage, MultipartHttpServletRequest request) throws IllegalStateException, IOException {
        
        service.update(dto);
        
        // 파일을 저장할 폴더 생성
        String realFolder = "D:\\Workspace\\Board\\src\\main\\webapp\\resources\\upload\\attachment\\";
        
        File dir = new File(realFolder);
        if(!dir.isDirectory()){
            dir.mkdirs();
        }
        
        // 첨부한 파일을 리스트로 저장
        List<MultipartFile> mf = request.getFiles("uploadFile");
        if(mf != null){
            for(int i=0; i<mf.size(); i++){
                if (!mf.get(i).getOriginalFilename().equals("")) {
                    // 실제 파일명
                    String org_name = mf.get(i).getOriginalFilename();
                    System.out.println("fileName : " + org_name);
                    // 저장되는 파일명(파일명 중복방지 처리)
                    String file_name = System.currentTimeMillis() + "_" + org_name;
                    // 저장될 파일 경로
                    String savePath = realFolder + file_name;
                    // 파일 사이즈
                    long file_size = mf.get(i).getSize();
                    // 파일 저장
                    mf.get(i).transferTo(new File(savePath));
                    
                    FileDTO fileDto = new FileDTO();
                    fileDto.setOrg_name(org_name);
                    fileDto.setFile_name(file_name);
                    fileDto.setFile_size(file_size);
                    fileDto.setFile_path(realFolder);
                    fileDto.setIdx(dto.getIdx());
                    
                    service.fileUpload(fileDto);
                }
            }
        }
        rttr.addAttribute("curPage", curPage);
        rttr.addFlashAttribute("msg", "수정되었습니다.");
        
        return "redirect:/boardMain";
        
    }    
    
    /**
     * 게시글 삭제
     */
    @RequestMapping(value = "/boardDelete", method = RequestMethod.GET)
    public String boardDelete(Integer idx, RedirectAttributes rttr) {
        
        service.delete(idx);
        rttr.addFlashAttribute("msg", "삭제되었습니다.");
        
        return "redirect:/boardMain";
        
    } 
    
    /**
     * 파일 다운로드 
     */
    @RequestMapping(value="/fileDownload", method = RequestMethod.GET)
    public String fileDownload(Integer idx, Integer file_idx, HttpServletResponse response, Model model, Integer curPage, RedirectAttributes rttr) throws Exception{
        System.out.println("idx : " + idx);
        System.out.println("curPage : " + curPage);
        
        // 다운로드 할 파일의 정보를 가져온다.
        FileDTO fileDto = service.fileInfo(file_idx);
        
        String file_name = fileDto.getFile_name();
        String org_name = fileDto.getOrg_name();
        String file_path = fileDto.getFile_path();
        
        
        if(file_path==null){
            String msg = "파일이 존재하지 않습니다.";
            rttr.addFlashAttribute("msg", msg);
            
            return "redirect:/boardRead?idx=" + idx + "&curPage=" + curPage;
        }
        else{
            // 해당 파일을 바이트 배열 형식으로 읽어온다.
            byte fileByte[] = FileUtils.readFileToByteArray(new File(file_path + file_name));
            
            response.setContentType("application/octet-stream");
            response.setContentLength(fileByte.length);
            response.setHeader("Content-Disposition",
                    "attachment; fileName=\"" + URLEncoder.encode(org_name, "UTF-8") + "\";");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.getOutputStream().write(fileByte);
            
            response.getOutputStream().flush();
            response.getOutputStream().close();
            
            return null;
        }
        
                
    }
    
    /**
     * 첨부파일 삭제 
     */
    @RequestMapping(value="/fileDelete", method = RequestMethod.POST)
    public @ResponseBody String fileDelete(Integer file_idx){
        // DB 삭제
        service.fileDelete(file_idx);
        // 파일 삭제
        
        return "success";
        
    }
    
    /**
     * ckeditor 이미지 업로드(임시폴더 저장) 
     */
    @RequestMapping(value="/imageUpload")
    public String imageUpload(HttpServletRequest request, ImageDTO dto, Model model) throws IOException{
        
        String realFolder = "D:\\Workspace\\Board\\src\\main\\webapp\\resources\\upload\\temp\\";
        
        File dir = new File(realFolder);
        if(!dir.isDirectory()){
            dir.mkdirs();
        }
        
        MultipartFile file = dto.getUpload();
        
        if(file != null){
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            System.out.println("fileName : " + fileName);
            
            if(!"".equals(fileName)){
                File destD = new File(realFolder + fileName);
                if(!destD.exists()){
                    destD.mkdirs();
                }
                
                file.transferTo(destD);
                
                dto.setNewFilename(fileName);
                dto.setImageUrl("/resources/upload/temp/" + fileName);
                System.out.println(dto.getCKEditorFuncNum());
                System.out.println(dto.getImageUrl());
                
                model.addAttribute("dto", dto);
            }
        }
        return "ckeditorUpload";
    }
	
}
