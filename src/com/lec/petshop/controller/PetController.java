package com.lec.petshop.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.petshop.service.AdminDeleteService;
import com.lec.petshop.service.AdminDeleteViewService;
import com.lec.petshop.service.AdminJoinService;
import com.lec.petshop.service.AdminListService;
import com.lec.petshop.service.AdminLoginService;
import com.lec.petshop.service.CatAllService;
import com.lec.petshop.service.CatBreedService;
import com.lec.petshop.service.CatContentService;
import com.lec.petshop.service.CatDeleteService;
import com.lec.petshop.service.CatDeleteViewService;
import com.lec.petshop.service.CatInsertService;
import com.lec.petshop.service.CatModifyContentSerivce;
import com.lec.petshop.service.CatModifyService;
import com.lec.petshop.service.CatReplyDeleteSerivice;
import com.lec.petshop.service.CatReplyDeleteViewService;
import com.lec.petshop.service.CatReplyService;
import com.lec.petshop.service.DogAllService;
import com.lec.petshop.service.DogBreedService;
import com.lec.petshop.service.DogContentService;
import com.lec.petshop.service.DogDeleteService;
import com.lec.petshop.service.DogDeleteViewService;
import com.lec.petshop.service.DogInsertService;
import com.lec.petshop.service.DogModifyContentService;
import com.lec.petshop.service.DogModifyService;
import com.lec.petshop.service.DogReplyDeleteService;
import com.lec.petshop.service.DogReplyDeleteViewService;
import com.lec.petshop.service.DogReplyService;
import com.lec.petshop.service.FreeBoardContentService;
import com.lec.petshop.service.FreeBoardDeleteService;
import com.lec.petshop.service.FreeBoardDeleteViewService;
import com.lec.petshop.service.FreeBoardListService;
import com.lec.petshop.service.FreeBoardModifyService;
import com.lec.petshop.service.FreeBoardModifyViewService;
import com.lec.petshop.service.FreeBoardReplyService;
import com.lec.petshop.service.FreeBoardReplyViewService;
import com.lec.petshop.service.FreeBoardWriteService;
import com.lec.petshop.service.MIdConfirmService;
import com.lec.petshop.service.MJoinService;
import com.lec.petshop.service.MLoginService;
import com.lec.petshop.service.MLogoutService;
import com.lec.petshop.service.MModifyAllService;
import com.lec.petshop.service.MModifyAllViewService;
import com.lec.petshop.service.MSearchIdService;
import com.lec.petshop.service.MSearchPwService;
import com.lec.petshop.service.MSearchPwViewService;
import com.lec.petshop.service.MainService;
import com.lec.petshop.service.ReviewContentService;
import com.lec.petshop.service.ReviewDeleteService;
import com.lec.petshop.service.ReviewDeleteViewService;
import com.lec.petshop.service.ReviewListService;
import com.lec.petshop.service.ReviewModifyService;
import com.lec.petshop.service.ReviewModifyViewService;
import com.lec.petshop.service.ReviewReplyWriteService;
import com.lec.petshop.service.ReviewReplyWriteViewService;
import com.lec.petshop.service.ReviewWriteService;
import com.lec.petshop.service.Service;
import com.lec.petshop.service.deleteCatZimService;
import com.lec.petshop.service.deleteZimservice;
import com.lec.petshop.service.insertCatZimService;
import com.lec.petshop.service.insertZimService;

/**
 * Servlet implementation class PetController
 */
@WebServlet("*.do")
public class PetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean loginForm = false;
	private boolean joinForm = false;
	private boolean adminloginForm = false;
	private boolean adminJoinForm = false;
	private boolean dogInsertForm = false;
	private boolean modifyForm = false;
	private boolean modifyFreeBoard = false;
	private boolean writeFreeBoard = false;
	private boolean replyFreeBoard = false;
	private boolean dogModifyForm = false;
	private boolean dogDeleteForm = false;
	private boolean catInsertForm = false;
	private boolean catModifyForm = false;
	private boolean catDeleteForm = false;
	private boolean dogReplyDeleteForm = false;
	private boolean catReplyDeleteForm = false;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		String viewPage = null;
		Service service = null;

		if (command.equals("/main.do")) { // ??????
			service = new MainService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		} else if (command.equals("/loginView.do")) { // ????????? ????????? View???
			viewPage = "member/loginView.jsp";
			loginForm = true;
		} else if (command.equals("/login.do")) { // ????????? ?????? ???
			if (loginForm) {
				service = new MLoginService();
				service.execute(request, response);
			}
			viewPage = "main.do";
			loginForm = false;
		} else if (command.equals("/joinAgree.do")) { // ???????????? ?????? ?????? View???
			viewPage = "member/joinAgree.jsp";
		} else if (command.equals("/joinView.do")) { // ???????????? ???????????? View???
			viewPage = "member/joinView.jsp";
			joinForm = true;
		} else if (command.equals("/idConfirm.do")) { // id ???????????? ?????? ???
			service = new MIdConfirmService();
			service.execute(request, response);
			viewPage = "member/idConfirm.jsp";
		} else if (command.equals("/join.do")) { // ???????????? ?????? ???
			if (joinForm) {
				service = new MJoinService();
				service.execute(request, response);
			}
			viewPage = "member/joinResult.jsp";
			joinForm = false;
		} else if (command.equals("/logout.do")) { // ???????????? ?????? ???
			service = new MLogoutService();
			service.execute(request, response);
			viewPage = "main.do";
		} else if (command.equals("/searchIdView.do")) { // ????????? ?????? View ???
			viewPage = "member/searchIdView.jsp";
		} else if (command.equals("/searchId.do")) { // ????????? ?????? ?????? ???
			service = new MSearchIdService();
			service.execute(request, response);
			viewPage = "member/searchIdResult.jsp";
		} else if (command.equals("/searchPwView.do")) { // ???????????? ?????? View ???
			service = new MSearchPwViewService();
			service.execute(request, response);
			viewPage = "member/searchPwView.jsp";
		} else if (command.equals("/searchPw.do")) { // ???????????? ?????? ?????? ???
			service = new MSearchPwService();
			service.execute(request, response);
			viewPage = "member/searchPwResult.jsp";
		} else if (command.equals("/mypageView.do")) { // ?????? ?????? ?????????
			viewPage = "member/MyPage.jsp";
		} else if (command.equals("/modifyView.do")) { // ?????? ??????????????? ???????????? ???????????? View???
			viewPage = "member/modify.jsp";
		} else if (command.equals("/modifyAllView.do")) { // ?????? ?????? ?????? View???
			service = new MModifyAllViewService();
			service.execute(request, response);
			viewPage = "member/modifyAll.jsp";
			modifyForm = true;
		} else if (command.equals("/modify.do")) { // ?????? ?????? ?????? ???
			if (modifyForm) {
				service = new MModifyAllService();
				service.execute(request, response);
			}
			viewPage = "member/MyPage.jsp";
			modifyForm = false;
		}
		/*************************************************************************************************?????? ************************************************************/
		/*************************************************************************************************?????? ************************************************************/
		/*************************************************************************************************??????************************************************************/
		else if (command.equals("/adminLoginView.do")) { // ????????? ????????? View ???
			viewPage = "admin/adminLoginView.jsp";
			adminloginForm = true;
		} else if (command.equals("/adminLogin.do")) { // ????????? ????????? ?????? ???
			if (adminloginForm) {
				service = new AdminLoginService();
				service.execute(request, response);
			}
			viewPage = "main.do";
			adminloginForm = false;
		} else if (command.equals("/adminJoinView.do")) { // ????????? ?????? View ???
			viewPage = "admin/adminJoinView.jsp";
			adminJoinForm = true;
		} else if (command.equals("/adminJoin.do")) { // ????????? ?????? ?????? ???
			if (adminJoinForm) {
				service = new AdminJoinService();
				service.execute(request, response);
			}
			viewPage = "main.do";
			adminJoinForm = false;
		} else if (command.equals("/adminDeleteView.do")) { // ????????? ?????? View???
			service = new AdminDeleteViewService();
			service.execute(request, response);
			viewPage = "admin/adminDeleteView.jsp";	
		} else if (command.equals("/adminDelete.do")) { // ????????? ?????? ?????? ???
			service = new AdminDeleteService();
			service.execute(request, response);
			viewPage = "admin/adminDeleteView.jsp";
		} else if (command.equals("/adminPageView.do")) { // ????????? ??????????????? ??????
			viewPage = "admin/adminPage.jsp";
		} else if (command.equals("/adminPageDogListView.do")) { // ????????? ??????????????? ????????? ????????? ??????
			service = new DogAllService();
			service.execute(request, response);
			viewPage = "admin/adminDogListView.jsp";
		} else if (command.equals("/adminPageCatListView.do")) { // ????????? ??????????????? ????????? ????????? ??????
			service = new CatAllService();
			service.execute(request, response);
			viewPage = "admin/adminCatListView.jsp";
		}
		/*************************************************************************************************????????? ************************************************************/
		/*************************************************************************************************?????????************************************************************/
		/*************************************************************************************************?????????************************************************************/
		else if (command.equals("/DogAllView.do")) { // ????????? ????????????
			service = new DogAllService();
			service.execute(request, response);
			viewPage = "pet/DogAllView.jsp";
		} else if (command.equals("/DogContentView.do")) { // ????????? Content
			service = new DogContentService();
			service.execute(request, response);
			viewPage = "pet/DogContent.jsp";
		} else if (command.equals("/DogInsertView.do")) { // ????????? ?????? View ???
			service = new DogBreedService();
			service.execute(request, response);
			viewPage = "admin/adminDogInsert.jsp";
			dogInsertForm = true;
		} else if (command.equals("/DogInsert.do")) { // ????????? ?????? ?????? ???
			if (dogInsertForm) {
				service = new DogInsertService();
				service.execute(request, response);
			}
			viewPage = "admin/adminDogListView.jsp";
			dogInsertForm = false;
		} else if (command.equals("/DogModifyView.do")) { // ????????? ?????? View???
			service = new DogModifyContentService();
			service.execute(request, response);
			viewPage = "admin/adminDogModifyView.jsp";
			dogModifyForm = true;
		} else if (command.equals("/DogModify.do")) { // ????????? ?????? ?????? ???
			if (dogModifyForm) {
				service = new DogModifyService();
				service.execute(request, response);
			}
			viewPage = "adminPageDogListView.do";
			dogModifyForm = false;
		} else if (command.equals("/DogDeleteView.do")) { // ????????? ?????? View???
			service = new DogDeleteViewService();
			service.execute(request, response);
			viewPage = "admin/adminDogDeleteView.jsp";
			dogDeleteForm = true;
		} else if (command.equals("/DogDelete.do")) { // ????????? ?????? ?????? ???
			if (dogDeleteForm) {
				service = new DogDeleteService();
				service.execute(request, response);
			}
			viewPage = "adminPageDogListView.do";
			dogDeleteForm = false;
		} else if (command.equals("/dogReplyInsert.do")) { // ????????? ?????? ?????? ???
			service = new DogReplyService();
			service.execute(request, response);
			viewPage = "DogContentView.do";
		} else if (command.equals("/deleteDogReplyView.do")) { // ????????? ?????? ?????? ????????? View???
			service = new DogReplyDeleteViewService();
			service.execute(request, response);
			viewPage = "pet/DogDeleteReplyView.jsp";
			dogReplyDeleteForm = true;
		} else if (command.equals("/DogDeleteReply.do")) { // ????????? ?????? ?????? ?????? ???
			if (dogReplyDeleteForm) {
				service = new DogReplyDeleteService();
				service.execute(request, response);
			}
			viewPage = "DogContentView.do";
			dogReplyDeleteForm = false;
		} else if (command.equals("/insertZim.do")) { // ????????? ??? ?????? ?????? ???
			service = new insertZimService();
			service.execute(request, response);
			viewPage = "DogContentView.do";
		} else if (command.equals("/deleteZim.do")) { // ????????? ??? ?????? ?????? ???
			service = new deleteZimservice();
			service.execute(request, response);
			viewPage = "DogContentView.do";
		} else if (command.equals("/DogBreedView.do")) { // ????????? ???????????? ?????????
			service = new DogBreedService();
			service.execute(request, response);
			viewPage = "pet/DogBreedView.jsp";
		}
		/*************************************************************************************************????????? ************************************************************/
		/*************************************************************************************************????????? ************************************************************/
		/*************************************************************************************************?????????************************************************************/
		else if (command.equals("/CatAllView.do")) { // ????????? ????????????
			service = new CatAllService();
			service.execute(request, response);
			viewPage = "pet/CatAllView.jsp";
		} else if (command.equals("/CatContentView.do")) { // ????????? ????????????
			service = new CatContentService();
			service.execute(request, response);
			viewPage = "pet/CatContent.jsp";
		} else if (command.equals("/CatInsertView.do")) { // ????????? ????????? ??????
			service = new CatBreedService();
			service.execute(request, response);
			viewPage = "admin/adminCatInsert.jsp";
			catInsertForm = true;
		} else if (command.equals("/CatInsert.do")) { // ????????? ?????? ??????
			if (catInsertForm) {
				service = new CatInsertService();
				service.execute(request, response);
			}
			viewPage = "admin/adminCatListView.jsp";
			catInsertForm = false;
		} else if (command.equals("/CatModifyView.do")) { // ????????? ?????? View???
			service = new CatModifyContentSerivce();
			service.execute(request, response);
			viewPage = "admin/adminCatModifyView.jsp";
			catModifyForm = true;
		} else if (command.equals("/CatModify.do")) { // ????????? ?????? ?????? ???
			if (catModifyForm) {
				service = new CatModifyService();
				service.execute(request, response);
			}
			viewPage = "adminPageCatListView.do";
			catModifyForm = false;
		} else if (command.equals("/CatDeleteView.do")) { // ????????? ?????? ????????? View ???
			service = new CatDeleteViewService();
			service.execute(request, response);
			viewPage = "admin/adminCatDeleteView.jsp";
			catDeleteForm = true;
		} else if (command.equals("/CatDelete.do")) { // ????????? ?????? ?????? ???
			if (catDeleteForm) {
				service = new CatDeleteService();
				service.execute(request, response);
			}
			viewPage = "adminPageCatListView.do";
			catDeleteForm = false;
		} else if (command.equals("/catReplyInsert.do")) { // ????????? ?????? ?????? ??????
			service = new CatReplyService();
			service.execute(request, response);
			viewPage = "CatContentView.do";
		} else if (command.equals("/deleteCatReplyView.do")) { // ????????? ?????? ?????? ????????? View???
			service = new CatReplyDeleteViewService();
			service.execute(request, response);
			viewPage = "pet/CatDeleteReplyView.jsp";
			catReplyDeleteForm = true;
		} else if (command.equals("/CatDeleteReply.do")) { // ????????? ?????? ?????? ?????? ???
			if (catReplyDeleteForm) {
				service = new CatReplyDeleteSerivice();
				service.execute(request, response);
			}
			viewPage = "CatContentView.do";
			catReplyDeleteForm = false;
		} else if (command.equals("/insertCatZim.do")) { // ????????? ??? ?????? ?????? ???
			service = new insertCatZimService();
			service.execute(request, response);
			viewPage = "CatContentView.do";
		} else if (command.equals("/deleteCatZim.do")) { // ????????? ??? ?????? ?????? ???
			service = new deleteCatZimService();
			service.execute(request, response);
			viewPage = "CatContentView.do";
		}
		/*************************************************************************************************????????? ************************************************************/
		/*************************************************************************************************????????? ************************************************************/
		/*************************************************************************************************?????????************************************************************/
		else if (command.equals("/freeBoardListView.do")) { // ?????? ????????? ????????? ?????? (Communication)
			service = new FreeBoardListService();
			service.execute(request, response);
			viewPage = "FreeBoard/FreeBoardList.jsp";
		} else if (command.equals("/freeBoardContentView.do")) { // ??????????????? ?????? ??? content
			service = new FreeBoardContentService();
			service.execute(request, response);
			viewPage = "FreeBoard/FreeBoardContent.jsp";
		} else if (command.equals("/freeBoardModifyView.do")) { // ??????????????? ?????? ??? ?????? View ???
			service = new FreeBoardModifyViewService();
			service.execute(request, response);
			viewPage = "FreeBoard/FreeBoardModify.jsp";
			modifyFreeBoard = true;
		} else if (command.equals("/freeBoardModify.do")) { // // ??????????????? ?????? ??? ?????? ??????
			if (modifyFreeBoard) {
				service = new FreeBoardModifyService();
				service.execute(request, response);
			}
			viewPage = "freeBoardContentView.do";
			modifyFreeBoard = false;
		} else if (command.equals("/freeBoardWriteView.do")) { // ??????????????? ??? ?????? View ???
			viewPage = "FreeBoard/FreeBoardWrite.jsp";
			writeFreeBoard = true;
		} else if (command.equals("/freeBoardWrite.do")) { // ??????????????? ??? ?????? ??????
			if (writeFreeBoard) {
				service = new FreeBoardWriteService();
				service.execute(request, response);
			}
			viewPage = "freeBoardListView.do";
			writeFreeBoard = false;
		} else if (command.equals("/freeBoardDeleteView.do")) { // ??????????????? ??? ?????? ????????? View ???
			service = new FreeBoardDeleteViewService();
			service.execute(request, response);
			viewPage = "FreeBoard/FreeBoardDeleteView.jsp";
		} else if (command.equals("/freeBoardDelete.do")) { // ??????????????? ??? ?????? ??????
			service = new FreeBoardDeleteService();
			service.execute(request, response);
			viewPage = "freeBoardListView.do";
		} else if (command.equals("/freeBoardReplyView.do")) { // ??????????????? ??????View??? ??????
			service = new FreeBoardReplyViewService();
			service.execute(request, response);
			viewPage = "FreeBoard/FreeBoardWrite.jsp";
			replyFreeBoard = true;
		} else if (command.equals("/freeBoardReply.do")) { // ??????????????? ?????? ??????
			if (replyFreeBoard) {
				service = new FreeBoardReplyService();
				service.execute(request, response);
			}
			viewPage = "freeBoardListView.do";
			replyFreeBoard = false;
		}
		/*************************************************************************************************Communication************************************************************/
		/*************************************************************************************************Communication************************************************************/
		/*************************************************************************************************Communication************************************************************/
		  else if(command.equals("/ReviewListView.do")) {  // ?????? ????????? ????????? ?????? ???
			  service = new ReviewListService();
			  service.execute(request, response); 
			  viewPage = "Review/ReviewList.jsp";
		  } else if(command.equals("/ReviewWriteView.do")) {  // ?????? ????????? ?????? ?????? View???
			  service = new AdminListService();
			  service.execute(request, response);
			  viewPage = "Review/ReviewWrite.jsp";
		  } else if(command.equals("/ReviewWrite.do")) {   // ?????? ????????? ?????? ?????? ?????????
			  service = new ReviewWriteService();
			  service.execute(request, response);
			  viewPage = "ReviewListView.do";			  
		  } else if(command.equals("/ReviewContentView.do")) { // ??????????????? ???????????? ?????????
			  service = new ReviewContentService();
			  service.execute(request, response);
			  viewPage = "Review/ReviewContent.jsp";
		  } else if(command.equals("/ReviewReplyWriteView.do")) {  // ?????? ????????? ????????? ?????? View???
			  service = new ReviewReplyWriteViewService();
			  service.execute(request, response);  
			  viewPage = "Review/ReviewWrite.jsp";
		  } else if(command.equals("/ReviewReplyWrite.do")) { // ?????? ????????? ????????? ?????? ???
			  service = new ReviewReplyWriteService();
			  service.execute(request, response);
			  viewPage = "ReviewListView.do";
		  } else if(command.equals("/ReviewModifyView.do")) {  // ?????? ????????? ?????? View ???
			  service = new ReviewModifyViewService();
			  service.execute(request, response);
			  viewPage = "Review/ReviewModifyView.jsp";
		  } else if(command.equals("/ReviewModify.do")) {   // ?????? ????????? ?????? ?????? ???
			  service = new ReviewModifyService();
			  service.execute(request, response);
			  viewPage = "ReviewContentView.do";
		  } else if(command.equals("/ReviewDeleteView.do")) {  // ?????? ????????? ?????? ????????? View???
			  service = new ReviewDeleteViewService();
			  service.execute(request, response);
			  viewPage = "Review/ReviewDeleteView.jsp";
		  } else if(command.equals("/ReviewDelete.do")){  // ?????? ????????? ?????? ?????? ???
			  service = new ReviewDeleteService();
			  service.execute(request, response);
			  viewPage = "ReviewListView.do";
		  }
		/*************************************************************************************************Review************************************************************/
		/*************************************************************************************************Review************************************************************/
		/*************************************************************************************************Review************************************************************/
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);

	}

}
