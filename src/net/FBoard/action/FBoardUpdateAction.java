package net.FBoard.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.FBoard.db.FBoardBean;
import net.FBoard.db.FBoardDAO;

public class FBoardUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("FBoardUpdateAction execute");
		
		request.setCharacterEncoding("utf-8");
		String realpath=request.getRealPath("/upload");
		System.out.println(realpath);
		//올릴 파일 최대크기
		int maxSize=5*1024*1024; //5M

		MultipartRequest multi= new MultipartRequest(request,realpath,maxSize,"utf-8",new DefaultFileRenamePolicy());
		
		String pageNum=multi.getParameter("pageNum");
		
		FBoardBean fb=new FBoardBean();
		
		fb.setNum(Integer.parseInt(multi.getParameter("num")));
		fb.setName(multi.getParameter("name"));
		fb.setPass(multi.getParameter("pass"));
		fb.setSubject(multi.getParameter("subject"));
		fb.setContent(multi.getParameter("content"));
		FBoardDAO fdao=new FBoardDAO();
		
		int check=fdao.updateBoard(fb);
		
		if(check==0){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호틀림');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}else if(check==-1){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('num없음');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("alert('수정성공');");
		out.println("location.href='./FBoardList.fbo?pageNum="+pageNum+"';");
		out.println("</script>");
		out.close();
		return null;
	}

}
