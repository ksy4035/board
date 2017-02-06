/**************************************************************
 * 작성자 : Ally
 * 작성일시 : 2016/09/20
 **************************************************************/

package com.ksy4035.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksy4035.app.domain.BoardDTO;
import com.ksy4035.app.domain.FileDTO;
import com.ksy4035.app.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
    
    @Autowired
    private BoardDAO dao;
    
    @Override
    public List<BoardDTO> list(Map<String, Object> map) throws Exception {
        return dao.list(map);
    }

    @Override
    public BoardDTO insert(BoardDTO dto) {
        return dao.insert(dto);
    }

    @Override
    public BoardDTO read(Integer idx) throws Exception {
        return dao.read(idx);
    }

    @Override
    public void updateCount(Integer idx) {
        dao.updateCount(idx);
    }

    @Override
    public void update(BoardDTO dto) {
        dao.update(dto);
    }

    @Override
    public void delete(Integer idx) {
        dao.delete(idx);
    }

    @Override
    public void fileUpload(FileDTO fileDto) {
        dao.fileUpload(fileDto);
    }

    @Override
    public int totalCount() throws Exception {
        return dao.totalCount();
    }

    @Override
    public List<FileDTO> fileList(Integer idx) throws Exception {
        return dao.fileList(idx);
    }

    @Override
    public FileDTO fileInfo(Integer file_idx) throws Exception {
        return dao.fileInfo(file_idx);
    }

    @Override
    public void fileDelete(Integer file_idx) {
        dao.fileDelete(file_idx);
    }
    
    
}
