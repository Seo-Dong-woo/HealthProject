package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

public interface DataBoardMapper {
	@Select("SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+ "FROM (SELECT /* + INDEX_DESC(springDataBoard sdb_no_pk) */no, subject, name, regdate, hit "
			+ "FROM springDataBoard)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<DataBoardVO> databoardListData(@Param("start") int start, @Param("end") int end);
	
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM springDataBoard")
	public int databoardTotalpage();
	
	@Insert("INSERT INTO springDataBoard VALUES("
			+ "sdb_no_seq.nextval, #{name}, #{subject},"
			+ "#{content}, #{pwd}, SYSDATE, 0, #{filename}, #{filesize}, #{filecount})")
	public void databoardInsert(DataBoardVO vo);
	
	@Update("UPDATE springDataBoard SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no, name, subject, content,"
			+ "TO_CHAR(regdate, 'YYYY-MM-DD') as dbday,"
			+ "hit, filename, filesize, filecount "
			+ "FROM springDataBoard "
			+ "WHERE no=#{no}")
	public DataBoardVO databoardDetailData(int no);
	
	// 삭제
	@Select("SELECT filename, filecount "
			+ "FROM springDataBoard "
			+ "WHERE no=#{no}")
	public DataBoardVO databoardFileInfoData(int no);
	
	@Select("SELECT pwd FROM springDataBoard "
			+ "WHERE no=#{no}")
	public String databoardGetPassword(int no);
	
	@Delete("DELETE FROM springDataBoard "
			+ "WHERE no=#{no}")
	public void databoardDelete(int no);
	
	// 수정
	@Update("UPDATE springDataBoard SET "
			+ "name=#{name}, subject=#{subject}, content=#{content}, "
			+ "filename=#{filename}, filesize=#{filesize}, filecount=#{filecount} "
			+ "WHERE no=#{no}")
	public void databoardUpdate(DataBoardVO vo);
}