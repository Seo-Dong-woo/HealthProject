package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class GymReserveDAO {
	@Autowired
	private GymReserveMapper mapper;

	public List<GymVO> gymReserveData(String address)
	{
		return mapper.gymReserveData(address);
	}
	
	public void gymReserveInsert(GymReserveVO vo)
	{
		mapper.gymReserveInsert(vo);
	}
	
	public List<GymReserveVO> gymReserveMypageData(String userId)
	{
		return mapper.gymReserveMypageData(userId);
	}

	public void GymreserveCancel(int rno)
	{
		mapper.GymreserveCancel(rno);
	}
	
	public List<GymReserveVO> gymReserveAdminpageData()
	{
		return mapper.gymReserveAdminpageData();
	}
	
	public void gymReserveOk(int rno)
	{
		mapper.gymReserveOk(rno);
	}
	
	public GymReserveVO gymReserveInfoData(int rno)
	{
		return mapper.gymReserveInfoData(rno);
	}
}
