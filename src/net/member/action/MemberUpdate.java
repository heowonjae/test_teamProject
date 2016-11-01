package net.member.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdate implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberUpdate execute()");
		//세션값 가져오기
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		//세션값 없으면  ./MemberLogin.me
		if(id==null){
			response.sendRedirect("./MemberLogin.mc");
		}
		
		// 디비객체 생성 MemberDAO mdao
		MemberDAO mdao=new MemberDAO();
		// MemberBean mb =  메서드호출 getMember(id)
		MemberBean mb=mdao.getMember(id);
		
		// request 속성 저장  "mb",mb
		request.setAttribute("mb", mb);
		// 이동   ./member/updateForm.jsp
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./member/update.jsp");
		return forward;
	}
}
