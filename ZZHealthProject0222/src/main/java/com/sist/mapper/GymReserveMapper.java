package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

import lombok.EqualsAndHashCode;

public interface GymReserveMapper {
	@Select("SELECT no, poster, title "
			+ "FROM gym "
			+ "WHERE address LIKE '%'||#{address}||'%'")
	public List<GymVO> gymReserveData(String address); // 한식 중식 양식 일식
	
	@Insert("INSERT INTO gymReserve VALUES("
			+ "gr_rno_seq.nextval, #{gno}, #{userId}, #{rDate}, #{rTime}, SYSDATE, 0)")
	public void gymReserveInsert(GymReserveVO vo);
	
	@Results({
		@Result(column = "title", property = "gvo.title"),
		@Result(column = "poster", property = "gvo.poster")
	})
	@Select("SELECT rno, gr.gno, title, poster, rDate, rTime, "
			+ "reserve_ok, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') as dbday "
			+ "FROM gymReserve gr, gym g "
			+ "WHERE gr.gno=g.no "
			+ "AND userId=#{userId} "
			+ "ORDER BY rno DESC")
	public List<GymReserveVO> gymReserveMypageData(String userId);
	
	@Delete("DELETE FROM reserve "
			+ "WHERE rno=#{rno}")
	public void GymreserveCancel(int rno);
	
	@Results({
		@Result(column = "title", property = "gvo.title"),
		@Result(column = "poster", property = "gvo.poster")
	})
	@Select("SELECT rno, gr.gno, title, poster, rDate, rTime, "
			+ "reserve_ok, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') as dbday "
			+ "FROM gymReserve gr, gym g "
			+ "WHERE gr.gno=g.no "
			+ "ORDER BY rno DESC")
	public List<GymReserveVO> gymReserveAdminpageData();
	
	@Update("UPDATE gymReserve SET "
			+ "reserve_ok=1 "
			+ "WHERE rno=#{rno}")
	public void gymReserveOk(int rno);
	
	@Results({
		@Result(column = "title", property = "gvo.title"),
		@Result(column = "poster", property = "gvo.poster")
	})
	@Select("SELECT rno, gr.gno, userId, title, poster, rDate, rTime, "
			+ "reserve_ok, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') as dbday "
			+ "FROM gymReserve gr, gym g "
			+ "WHERE gr.gno=g.no "
			+ "AND rno=#{rno}")
	public GymReserveVO gymReserveInfoData(int rno);
}
