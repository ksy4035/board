
/**************************************************************
 * 작성자 : Ally
 * 작성일시 : 2016/09/20
 **************************************************************/

package com.ksy4035.app.persistence;

import java.util.List;
import java.util.Map;

import com.ksy4035.app.domain.BoardDTO;
import com.ksy4035.app.domain.FileDTO;

public interface BoardDAO {

    public List<BoardDTO> list(Map<String, Object> map) throws Exception;
    public BoardDTO insert(BoardDTO dto);
    public BoardDTO read(Integer idx) throws Exception;
    public void updateCount(Integer idx);
    public void update(BoardDTO dto);
    public void delete(Integer idx);
    public int totalCount() throws Exception;
    public void fileUpload(FileDTO fileDto);
    public List<FileDTO> fileList(Integer idx) throws Exception;
    public FileDTO fileInfo(Integer file_idx) throws Exception;
    public void fileDelete(Integer file_idx);
    
}
