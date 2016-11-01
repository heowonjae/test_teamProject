package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdateAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberUpdateAction execute()");
		// 세션값 가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		// 세션값 없으면 ./MemberLogin.me
		if (id == null) {
			response.sendRedirect("./MemberLogin.mc");
		}
		
		// 한글처리
		request.setCharacterEncoding("utf-8");
		// 자바빈 객체 생성 MemberBean mb

		String realPath = request.getRealPath("/upload");
		int maxSize = 5 * 1024 * 1024;
		MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "utf-8",
				new DefaultFileRenamePolicy());

		MemberBean mb = new MemberBean();
		// 메서드호출 set메서드 <= 폼파라미터
		// id passwd name age gender email
		mb.setId(multi.getParameter("id"));
		mb.setPass(multi.getParameter("pass"));
		mb.setName(multi.getParameter("name"));
		mb.setEmail(multi.getParameter("email"));
		mb.setFile(multi.getParameter("file"));
		
		if(multi.getFilesystemName("file")!=null){

			mb.setFile(multi.getFilesystemName("file"));	
		}else{
			mb.setFile(multi.getParameter("file2")); //기존 파일 이름
		}
		
		// 디비객체 생성 MemberDAO mdao
		MemberDAO mdao = new MemberDAO();
		// int check= 메서드호출 updateMember(mb)
		int check = mdao.updateMember(mb);
		// check==0 "비밀번호틀림" 뒤로이동
		// check==-1 "아이디없음" 뒤로이동
		if (check == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		} else if (check == -1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디없음');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		// "수정성공" ./MemberMain.me 이동
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('수정성공');");
		out.println("location.href='./MemberMain.mc'");
		out.println("</script>");
		out.close();
		return null;
	}
}
