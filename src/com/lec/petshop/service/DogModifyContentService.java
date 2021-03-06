package com.lec.petshop.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.dao.AdminDao;
import com.lec.petshop.dao.DogDao;
import com.lec.petshop.dto.DogDto;

public class DogModifyContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pageNum = request.getParameter("pageNum");
		int dnum = Integer.parseInt(request.getParameter("dnum"));
		DogDao dao = DogDao.getInstance();
		DogDto dog = dao.dogModifyContent(dnum);		
		request.setAttribute("breedlist", dao.breedList());
		request.setAttribute("dogModifyContent", dog);

	}

}
