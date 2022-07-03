package com.lec.petshop.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.petshop.dao.CatDao;
import com.lec.petshop.dto.AdminDto;
import com.lec.petshop.dto.CatDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class CatModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRealPath("DogImageUpFolder");
		int maxSize = 1024 * 1024 * 10;
		String[] cimage = { "", "", "", "", "" };
		String[] originalCimage = { "", "", "", "", "" };
		try {
			MultipartRequest mRequest = new MultipartRequest(request, path, maxSize, "utf-8",
															new DefaultFileRenamePolicy());
			Enumeration<String> paramNames = mRequest.getFileNames();
			int idx = 0;
			while (paramNames.hasMoreElements()) {
				String param = paramNames.nextElement(); // 파라미터 이름 받아오기
				cimage[idx] = mRequest.getFilesystemName(param); // 그 파라미터 이름으로 저장된 파일을 가지고 옴
				originalCimage[idx] = mRequest.getOriginalFileName(param);// 해당 파라미터 이름으로 첨부된 오리지널 파일 이름 가지고 옴
				idx++;
			}
			CatDao dao = CatDao.getInstance();
			HttpSession session = request.getSession();
			int cnum = Integer.parseInt(mRequest.getParameter("cnum"));
			CatDto oldCat = dao.catModifyContent(cnum);
			if(cimage[0] == null ) {
				cimage[0] = oldCat.getCimage5();
			}
			if(cimage[1] == null) {
				cimage[1] = oldCat.getCimage4();
			}
			if(cimage[2] == null) {
				cimage[2] = oldCat.getCimage3();
			} 
			if(cimage[3] == null) {
				cimage[3] = oldCat.getCimage2();
			} 
			if(cimage[4] == null) {
				cimage[4] = oldCat.getCimage1();
			}
//			for(int i = 0; i < dimage.length; i++) {
//				if(dimage[i] == null) {
//					dimage[i] = oldDog.getDimage+(i+1)();
//				}
//			}		
			String cname = mRequest.getParameter("cname");
			String cgender = mRequest.getParameter("cgender");
			String cbirthStr = mRequest.getParameter("cbirth");
			Date cbirth = null;
			if (!cbirthStr.equals("")) {
				cbirth = Date.valueOf(cbirthStr);
			}
			int cbreedno = Integer.parseInt(mRequest.getParameter("cbreedno"));
			int cprice = Integer.parseInt(mRequest.getParameter("cprice"));
			String ccontent = mRequest.getParameter("ccontent");
			
			String aid =  ((AdminDto) session.getAttribute("admin")).getAid();
			String cip = request.getRemoteAddr();
			CatDto cat = new CatDto(cnum, cname, cgender, cbirth, cprice, cbreedno, aid, ccontent, cimage[4], cimage[3], cimage[2], cimage[1], cimage[0], cip, 0, 1, null, null );
			int result = dao.updateCat(cat);
			if(result == CatDao.SUCCESS) {
				System.out.println(aid+" 관리자가 고양이 수정 성공");
				request.setAttribute("CatModifyResult", result);
			} else {
				System.out.println("수정 실패"+cat);
				request.setAttribute("CatModifyResult", result );
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		for (String f : cimage) {
			if (f != null) { // f가 null이 아닐때만 카피할 것
				// 파일복사
				InputStream is = null;
				OutputStream os = null;
				try {
					File serverfile = new File(path + "/" + f); // 첨부하지 않으면 filename에는 null이 들어감
					if (serverfile.exists()) { // 첨부한 파일이 있는지
						is = new FileInputStream(serverfile); // 원본 파일
						os = new FileOutputStream( "C:\\choijinyoung\\source\\petshop_project\\petshop\\WebContent\\DogImageUpFolder/" + f);// 복사될 파일
						byte[] bs = new byte[(int) serverfile.length()]; // 서버파일의 크기만큼 바이트로 읽어내겠따는 뜻, 형변환으로도int로 해주기
						while (true) {
							int nReadCnt = is.read(bs);
							if (nReadCnt == -1) {
								break;
							}
							os.write(bs, 0, nReadCnt);
						} // while
						System.out.println("서버 첨부 & 복사 완료");
					} // if
				} catch (Exception e) {
					System.out.println(e.getMessage());
				} finally {

					try {
						if (os != null) {
							os.close();
						}
						if (is != null) {
							is.close();
						}
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}

				}

			}
		}

	}

}
