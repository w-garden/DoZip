package com.dozip.service;

import com.dozip.dao.PartnersDAO;
import com.dozip.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PartnersServiceImpl implements PartnersService {
    @Autowired
    private PartnersDAO partnersDao;

    @Override
    public PartnersVO getPartnersInfo(String pId) {
        return partnersDao.getPartnersInfo(pId);
    }
    @Override
    public int addPortfolio(PortfolioVO pv) {
        partnersDao.addPortfolio(pv);
        return partnersDao.getPort_num(pv); //수정작업 필요 . 가끔2개가 조회됨
    }

    @Override
    public void insertPartners(PartnersVO pv) {
        partnersDao.insertPartners(pv);
    }
    @Override
    public PartnersVO getPartnersInfo2(String findid_business_num) {
        return partnersDao.getPartnersInfo2(findid_business_num);
    }
    @Override
    public void insertPort_Photos(PortfolioVO pv) {
        partnersDao.insertPort_Photos(pv);
    }
    @Override
    public int getListCount(QnaVO findQ) {
        return partnersDao.getListCount(findQ);
    }
    @Override
    public List<QnaVO> getQnaList(QnaVO findQ) {
        return partnersDao.getQnaList(findQ);
    }
    @Transactional
    @Override
    public int insertQna(QnaVO qdto) {
        partnersDao.updateLevel(qdto); //답변글 레벨증가
        partnersDao.updateState(qdto); //원본글 state 답변완료로 변경
        return partnersDao.insertQna(qdto);
    }//답변레벨증가+답변저장=>트랜잭션 적용 대상
    @Override
    public int deleteReply(QnaVO dv) {
        return partnersDao.deleteReply(dv);
    }
    @Override
    public int selqnaRef(QnaVO dv) {
        return partnersDao.selqnaRef(dv);
    }
    @Override
    public void returnState(QnaVO dv) {
        partnersDao.returnState(dv);
    }


    /* minwoo */
    @Override
    public PartnersVO getMember(String businessNum) {
        return this.partnersDao.getMember(businessNum);
    }
    @Override
    public Partners_subVO getPartnersSub(String businessNum) {
        return partnersDao.getPartnersSub(businessNum);
    }
    @Override
    public void updatePartnersSub(Partners_subVO ps) {
        partnersDao.updatePartnersSub(ps);
    }

    @Override
    public void updatePartners(PartnersVO p) { partnersDao.updatePartners(p); }

    @Override
    public void insertPartnersSub(Partners_subVO ps) {
        partnersDao.insertPartnersSub(ps);
    }

    @Override
    public List<EstimateVO> selectEstimateList() {
        return partnersDao.selectEstimateList();
    }


    @Override
    public int checkSub(String businessNum) {
        return partnersDao.checkSub(businessNum);
    }

//    @Override
//    public void checkremaindate(EstimateVO vo) {
//        partnersDao.checkremaindate(vo);
//    }


    @Override
    public int checkBusinessNum(PartnersVO pv) {
        return partnersDao.checkBusinessNum(pv);
    }

    @Override
    public List<EstimateVO> getAllEstList() {
        return partnersDao.getAllEstList();
    }

    @Override
    public List<BidVO> getBidList() {
        return partnersDao.getBidList();
    }


    @Override
    public EstimateVO write_contract(int est_num){
        return partnersDao.write_contract(est_num);
    }

    @Override
    public void insertContract(ContractVO cv) {
        partnersDao.insertContract(cv);
    }
    
    @Override
    public EstimateVO selectEstimate(String bid_no) {
        return partnersDao.selectEstimate(bid_no);
    }

    @Override
    public void insertbid(BidVO bid) {
        partnersDao.insertbid(bid);
    }
}

