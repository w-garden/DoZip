package com.dozip.service.partners.mypage;

import com.dozip.vo.PartnersVO;
import com.dozip.vo.Partners_subVO;

public interface InfoService {
    PartnersVO getMember(String businessNum); //data_manage 회원정보 가져오기
    Partners_subVO getPartnersSub(String businessNum);
    void updatePartners(PartnersVO p);//data_manage 회원정보 추가/수정(address)

    void insertPartnersSub(Partners_subVO ps);
    void updatePartnersSub(Partners_subVO ps);//data_manage sub회원정보 추가/수정
    int checkSub(String businessNum);
}