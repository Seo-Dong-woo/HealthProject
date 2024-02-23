package com.sist.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.vo.*;
import com.sist.dao.*;

@Service
public class GymReserveServiceImpl implements GymReserveService {
	@Autowired
	private GymReserveDAO grDao;

	@Override
	public List<GymVO> gymReserveData(String address) {
		// TODO Auto-generated method stub
		return grDao.gymReserveData(address);
	}

	@Override
	public void gymReserveInsert(GymReserveVO vo) {
		// TODO Auto-generated method stub
		grDao.gymReserveInsert(vo);
	}

	@Override
	public List<GymReserveVO> gymReserveMypageData(String userId) {
		// TODO Auto-generated method stub
		return grDao.gymReserveMypageData(userId);
	}

	@Override
	public void GymreserveCancel(int rno) {
		// TODO Auto-generated method stub
		grDao.GymreserveCancel(rno);
	}

	@Override
	public List<GymReserveVO> gymReserveAdminpageData() {
		// TODO Auto-generated method stub
		return grDao.gymReserveAdminpageData();
	}

	@Override
	public void gymReserveOk(int rno) {
		// TODO Auto-generated method stub
		grDao.gymReserveOk(rno);
	}

	@Override
	public GymReserveVO gymReserveInfoData(int rno) {
		// TODO Auto-generated method stub
		return grDao.gymReserveInfoData(rno);
	}
}
