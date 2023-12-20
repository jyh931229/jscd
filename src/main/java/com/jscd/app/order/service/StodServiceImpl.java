package com.jscd.app.order.service;

import com.jscd.app.order.dao.StodDao;
import com.jscd.app.order.dto.StodDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StodServiceImpl implements StodService {

    private final StodDao stodDao;

    public StodServiceImpl(StodDao stodDao) {
        this.stodDao = stodDao;
    }

    // 주문 내역 테이블에 데이터 저장
    @Override
    public int insertStod(StodDTO stodDTO) throws Exception {
        return stodDao.insertStod(stodDTO);
    }

    // 결재 내역 테이블에 데이터 저장
    @Override
    public int insertPayHty(StodDTO stodDTO) throws Exception {
        return stodDao.insertPayHty(stodDTO);
    }

    // 주문 내역 조회
    // StodDao의 selectOrderList() 호출, '주문 내역' 조회 및 결과 반환
    // OrderController.java에서 selectOrderList() 호출 시 '주문 내역' 조회 가능
    @Override
    public List<StodDTO> selectOrderList(String id, int page, int itemsPerPage) throws Exception {
        int start = (page - 1) * itemsPerPage;
        int end = itemsPerPage; // #{end} : 시작점으로부터 가져올 레코드의 개수 (OrderController.java - getOrderList()와 연결 됨)
        return stodDao.selectOrderList(id, start, end);
    }


    // 전체 아이템 수 조회
    @Override
    public int countOrderList(String id) throws Exception {
        return stodDao.countOrderList(id);
    }
    
    // 주문 상세 내역 조회
    @Override
    public List<StodDTO> selectOrderDetail(String id, String odNo) throws Exception {
        return stodDao.selectOrderDetail(id, odNo);
    }
}
