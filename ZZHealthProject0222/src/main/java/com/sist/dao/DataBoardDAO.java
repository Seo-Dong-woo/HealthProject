package com.sist.dao;

import java.util.*;

import com.sist.mapper.*;
import com.sist.vo.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
/*
 *   Spring MVC
 *     1. web.xml => WebServlet
 *        DispatcherServlet 등록 => 서블릿은 URL에 따라서 톰캣에 의해 호출
 *        =================
 *          | Servlet => init() => service() => destroy()
 *                         |
 *                       WebApplicationContext => 클래스 정보를 넘겨줌
 *                       =====================
 *                       <init-param>
 *                           <param-name>contextConfigLocation</param-name>
 *                           <param-value>/WEB-INF/config/application-*.xml</param-value>
 *                       </init-param>
 */
@Repository
public class DataBoardDAO {
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private DataBoardMapper mapper;
	
	public List<DataBoardVO> databoardListData(int start, int end)
	{
		return mapper.databoardListData(start, end);
	}
	
	public int databoardTotalpage()
	{
		return mapper.databoardTotalpage();
	}
	
	public void databoardInsert(DataBoardVO vo)
	{
		mapper.databoardInsert(vo);
	}
	
	public DataBoardVO databoardDetailData(int no)
	{
		mapper.hitIncrement(no);
		return mapper.databoardDetailData(no);
	}
	
	public DataBoardVO databoardFileInfoData(int no)
	{
		return mapper.databoardFileInfoData(no);
	}
	
	public boolean databoardDelete(int no, String pwd)
	{
		boolean bCheck=false;
		String db_pwd=mapper.databoardGetPassword(no);
		if(encoder.matches(pwd, db_pwd))
		{
			bCheck=true;
			mapper.databoardDelete(no);
		}
		
		return bCheck;
	}
	
	public DataBoardVO databoardUpdateData(int no)
	{
		mapper.hitIncrement(no);
		return mapper.databoardDetailData(no);
	}
	
	public boolean databoardUpdate(DataBoardVO vo)
	{
		boolean bCheck=false;
		String db_pwd=mapper.databoardGetPassword(vo.getNo());
		if(encoder.matches(vo.getPwd(), db_pwd))
		{
			bCheck=true;
			mapper.databoardUpdate(vo);
		}
		return bCheck;
	}
}
