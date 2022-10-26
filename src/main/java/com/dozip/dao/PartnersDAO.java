package com.dozip.dao;

import com.dozip.vo.*;

import java.util.List;

public interface PartnersDAO {
    PartnersVO getPartnersInfo(String pId);
    void addPortfolio(PortfolioVO pv);
    int getPort_num(PortfolioVO pv);
    void insertPartners(PartnersVO pv);
    PartnersVO getPartnersInfo2(String findid_business_num);
    void insertPort_Photos(PortfolioVO pv);
    int getListCount(QnaVO findQ);
    List<QnaVO> getQnaList(QnaVO findQ);
    void updateLevel(QnaVO qdto);
    void updateState(QnaVO qdto);
    int insertQna(QnaVO qdto);
    int deleteReply(QnaVO dv);
    int selqnaRef(QnaVO dv);
    void returnState(QnaVO dv);
    int checkBusinessNum(PartnersVO pv);

    /* minwoo */
    /* My page */
    PartnersVO getMember(String businessNum); //data_manage 회원정보
    Partners_subVO getPartnersSub(String businessNum);
    void insertPartnersSub(Partners_subVO ps);
    void updatePartnersSub(Partners_subVO ps);
    void updatePartners(PartnersVO p);
    int checkSub(String businessNum);
    /* bid */
    List<EstimateVO> selectEstimateList(); //estimate 테이블에 있는 db 전부 가져오기
    /*bid_detail */
    EstimateVO selectEstimate(String bid_no);
    int countBid(String est_num);
    int checkBid(BidVO b);
    /* bid_detail_ok */
    void insertbid(BidVO bid);
    /* my_bid */
    List<BidVO> selectJoinList(EstimateVO e);
    /* construct_request */
    List<EstimateVO> selectEstimateListBnum(String businessNum);
    int getListCount2(String businessNum);
    List<EstimateVO> getAllEstList();
    List<BidVO> getBidList();
    EstimateVO write_contract(int est_num);
    int insertContract(ContractVO cv);
    BidVO getOneBid(String est_num);
    EstimateVO getOneEst(String est_num);
    void updateEstimate(ContractVO cv);
    List<ContractVO> getContractList();
}

