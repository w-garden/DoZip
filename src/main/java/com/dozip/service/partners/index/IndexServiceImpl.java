package com.dozip.service.partners.index;

import com.dozip.dao.partners.index.IndexDAO;
import com.dozip.vo.InfoVO;
import com.dozip.vo.PayVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService{
    @Autowired
    IndexDAO indexDAO;

    @Override
    public PayVO montly_sales(String businessNum) {
        return indexDAO.montly_sales(businessNum);
    }
    @Override
    public int portfolioCount(String bNum) {
        return indexDAO.portfolioCount(bNum);
    }

    @Override
    public InfoVO partnersInfoCheck(String bNum) {
        return indexDAO.partnersInfoCheck(bNum);
    }

    @Override //미답변 문의 개수
    public int newQnaCount(String bNum) { 
        return indexDAO.newQnaCount(bNum);
    }
}