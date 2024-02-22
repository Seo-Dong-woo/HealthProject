package com.sist.service;

import java.util.List;

import com.sist.vo.GymReplyVO;
import com.sist.vo.MemberVO;

public interface GymReplyService {
	public List<GymReplyVO> gymReplyListData(int gno);
	public void gymReplyInsert(GymReplyVO vo);
	public void gymReplyUpdate(GymReplyVO vo);
	public void gymReplyDelete(int no);
	public MemberVO memberInfoData(String userId);

}
