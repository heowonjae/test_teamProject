package net.ToDo.action;

public class ActionForward {
    //이동방식 저장
	private boolean isRedirect=false;   //false- forward()
	                                    //true- sendRedirect()
	//이동경로 저장
	private String path=null;
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
