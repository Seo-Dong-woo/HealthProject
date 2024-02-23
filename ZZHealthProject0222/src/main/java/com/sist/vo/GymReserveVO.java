package com.sist.vo;

import java.util.*;

import lombok.Data;
/*
 *  RNO        NOT NULL NUMBER        
	FNO                 NUMBER        
	USERID              VARCHAR2(20)  
	RDATE      NOT NULL VARCHAR2(100) 
	RTIME      NOT NULL VARCHAR2(20)  
	RINWON     NOT NULL VARCHAR2(20)  
	REGDATE             DATE          
	RESERVE_OK          NUMBER     
 */
@Data
public class GymReserveVO {
	private int rno, gno, reserve_ok;
	private String userId, rDate, rTime, dbday;
	private Date regdate;
	private GymVO gvo=new GymVO(); // 조인
}
