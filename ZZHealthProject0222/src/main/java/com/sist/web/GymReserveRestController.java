package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.sist.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.manager.MailManager;
import com.sist.service.*;
@RestController
public class GymReserveRestController {
   @Autowired
   private GymReserveService grService;
   
   @Autowired
   private MailManager mm;//MailManager mm=new MailManager()
   
   @GetMapping(value="reserve/gym_list_vue.do",produces = "text/plain;charset=UTF-8")
   public String gym_list(String address) throws Exception
   {
	   List<GymVO> list=grService.gymReserveData(address);
	   ObjectMapper mapper=new ObjectMapper();
	   String json=mapper.writeValueAsString(list);
	   return json;
   }
   @PostMapping(value="reserve/reserve_ok.do",produces = "text/plain;charset=UTF-8")
   public String reserve_ok(GymReserveVO vo,HttpSession session)
   {
	   String result="no";
	   try {
		   vo.setUserId((String)session.getAttribute("userId"));
	       System.out.println("gno:"+vo.getGno());
	       System.out.println("date:"+vo.getRDate());
	       System.out.println("time:"+vo.getRTime());
	       System.out.println("userId:"+vo.getUserId());
		   grService.gymReserveInsert(vo);
	       result="yes";
	   }catch(Exception ex)
	   {
		   result="no";
	   }
	   
	   return result;
   }
   @GetMapping(value="reserve/mypage_list_vue.do",produces = "text/plain;charset=UTF-8")
   public String mypage_list(HttpSession session) throws Exception
   {
	   String userId=(String)session.getAttribute("userId");
	   List<GymReserveVO> list=grService.gymReserveMypageData(userId);
	   ObjectMapper mapper=new ObjectMapper();
	   String json=mapper.writeValueAsString(list);
	   return json;
   }
   @GetMapping(value="reserve/reserve_cancel_vue.do",produces = "text/plain;charset=UTF-8")
   public String reserve_cancel(int rno)
   {
	   String result="";
	   try
	   {
		   result="yes";
		   grService.GymreserveCancel(rno);
	   }catch(Exception ex) 
	   {
		   result="no";   
	   }
	   return result;
   }
   @GetMapping(value="reserve/reserve_admin_vue.do",produces = "text/plain;charset=UTF-8")
   public String reserve_admin() throws Exception
   {
	   List<GymReserveVO> list=grService.gymReserveAdminpageData();
	   ObjectMapper mapper=new ObjectMapper();
	   String json=mapper.writeValueAsString(list);
	   return json;
   }
   @GetMapping(value="reserve/reserve_ok_vue.do",produces = "text/plain;charset=UTF-8")
   public String reserve_ok(int rno)
   {
	   System.out.println("rno="+rno);
	   String result="";
	   try
	   {
		   result="yes";
		   grService.gymReserveOk(rno);
		   GymReserveVO vo=grService.gymReserveInfoData(rno);
		   //mm.mailReserveSender(vo);
	   }catch(Exception ex)
	   {
		   result=ex.getMessage();
	   }
	   return result;
   }
}