package com.sist.service;

import java.util.*;

import com.sist.vo.*;

public interface GymReserveService {
	public List<GymVO> gymReserveData(String address);
	public void gymReserveInsert(GymReserveVO vo);
	public List<GymReserveVO> gymReserveMypageData(String userId);
	public void GymreserveCancel(int rno);
	public List<GymReserveVO> gymReserveAdminpageData();
	public void gymReserveOk(int rno);
	public GymReserveVO gymReserveInfoData(int rno);
}
