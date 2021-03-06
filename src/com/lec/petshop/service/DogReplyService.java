package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.petshop.dao.Dog_ReplyDao;
import com.lec.petshop.dto.AdminDto;
import com.lec.petshop.dto.Dog_ReplyDto;
import com.lec.petshop.dto.MemberDto;

public class DogReplyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String replyPageNum = request.getParameter("replyPageNum");
		int dnum = Integer.parseInt(request.getParameter("dnum"));
		HttpSession session = request.getSession();
		String mid = ((MemberDto)session.getAttribute("member")).getMid();
		if(mid == null || mid.equals("")) {
			mid = ((AdminDto)session.getAttribute("admin")).getAid();
		}
		String rip = request.getRemoteAddr();
		String reply_content = request.getParameter("reply_content");
		Dog_ReplyDao dao = Dog_ReplyDao.getInstance();
		int result = dao.replyDog(new Dog_ReplyDto(0, 0, dnum, mid, reply_content, null, rip));
		if(result == Dog_ReplyDao.SUCCESS) {
			request.setAttribute("replyResult", result);
		} else {
			request.setAttribute("replyResult", result);
		}
		request.setAttribute("replyPageNum", replyPageNum);
		request.setAttribute("dnum", dnum);  
		
		

	}

}
