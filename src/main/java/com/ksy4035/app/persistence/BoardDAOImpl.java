/**************************************************************
 * 작성자 : Ally
 * 작성일시 : 2016/09/20
 **************************************************************/

package com.ksy4035.app.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ksy4035.app.domain.BoardDTO;
import com.ksy4035.app.domain.FileDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {
    
    @Autowired
    private SqlSession sqlSession;
    private static final String NAMESPACE = "com.ksy4035.mappers.boardMapper";
    
    @Override
    public List<BoardDTO> list(Map<String, Object> map) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".list", map);
    }

    @Override
    public BoardDTO insert(BoardDTO dto) {
        sqlSession.insert(NAMESPACE + ".insert", dto);
        return dto;
    }

    @Override
    public BoardDTO read(Integer idx) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".read", idx);
    }

    @Override
    public void updateCount(Integer idx) {
        sqlSession.update(NAMESPACE + ".updateCount", idx);
    }

    @Override
    public void update(BoardDTO dto) {
        sqlSession.update(NAMESPACE + ".update", dto);
    }

    @Override
    public void delete(Integer idx) {
        sqlSession.delete(NAMESPACE + ".delete", idx);
    }

    @Override
    public void fileUpload(FileDTO fileDto) {
        sqlSession.insert(NAMESPACE + ".fileUpload", fileDto);
    }

    @Override
    public int totalCount() throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".totalCount");
    }

    @Override
    public List<FileDTO> fileList(Integer idx) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".fileList", idx);
    }

    @Override
    public FileDTO fileInfo(Integer file_idx) throws Exception {
        return sqlSession.selectOne(NAMESPACE + ".fileInfo", file_idx);
    }

    @Override
    public void fileDelete(Integer file_idx) {
        sqlSession.delete(NAMESPACE + ".fileDelete", file_idx);
    }

    
}
