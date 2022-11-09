package com.dozip.dao.partners.index;

import com.dozip.vo.InfoVO;
import com.dozip.vo.PayVO;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IndexDAOImpl implements  IndexDAO{
    @Autowired
    private SqlSession sqlSession;

    @Override//이번달 매출내역
    public PayVO montly_sales(String businessNum) {
        return sqlSession.selectOne("montly_sales", businessNum);
    }

    @Override
    public int portfolioCount(String bNum) {
        return sqlSession.selectOne("portfolioCount",bNum);
    }

    @Override
    public InfoVO partnersInfoCheck(String bNum) { //파트너스 정보 등록 확인용
        return sqlSession.selectOne("partnersInfoCheck", bNum);
    }

    @Override
    public int newQnaCount(String bNum) { //미답변 문의 개수
        return sqlSession.selectOne("newQnaCount", bNum);
    }
}