package net.member.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
		//디비연결 메서드
		private Connection getConnection() throws Exception{
		String dbUrl="jdbc:mysql://localhost:3306/testdb";
		String dbUser="tester";
		String dbPass="1234";
		Connection con=null;
//			// 1단계 드라이버 로더
		Class.forName("com.mysql.jdbc.Driver");
//			// 2단계 디비연결
		con=DriverManager.getConnection(dbUrl, dbUser, dbPass);
			return con;
			
			
		}
		
		
		
		
		public void insertMember(MemberBean mb){
			Connection con=null;
			String sql="";
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				//1드라이버 로더//2디비연결
				con=getConnection();
				//3sql insert
				sql="insert into member(id,pass,name,email,file) values(?,?,?,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, mb.getId());
				pstmt.setString(2, mb.getPass());
				pstmt.setString(3, mb.getName());
				pstmt.setString(4, mb.getEmail());
				pstmt.setString(5, mb.getFile());
				
				//4실행
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				//객체생성 종료
				if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}} 
				if(con!=null){try{con.close();}catch(SQLException ex){}}
			}
		}//회원가입메서드
		
		
		
		public int userCheck(String id,String pass){
			Connection con=null;
			String sql="";
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			int check=-1;
			try {
				//1 드라이버 로더			//2 디비연결
				con=getConnection();
				//3 sql id조건 만족하는 passwd 가져오기
			sql="select pass from member where id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				//4  실행 => rs 저장
				rs=pstmt.executeQuery();
				//5 rs 데이터 있으면 아이디있음
				//    비밀번호 비교 맞으면  check=1
				//              틀리면 check=0
				//  없으면 아이디없음  check=-1
				if(rs.next()){
					//아이디있음
					if(pass.equals(rs.getString("pass"))){
						check=1;//비밀번호맞음
					}else{
						check=0;//비밀번호틀림
					}
				}else{
					check=-1;//아이디없음
				}
			}catch(Exception e){
				 e.printStackTrace();
			}finally{
				if(rs!=null){try{rs.close();}catch(SQLException ex){}}
				if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}}
				if(con!=null){try{con.close();}catch(SQLException ex){}}
			}
			return check;
		}//유저체크메서드
		
		//회원정보조회메서드
		public MemberBean getMember(String id){
			Connection con=null;
			String sql="";
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			MemberBean mb=null;
			try {
				//1 드라이버 로더		//2 디비연결
				con=getConnection();
				//3 sql id조건에 만족하는 member정보가져오기
				sql="select * from member where id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				//4 rs 실행 저장
				rs=pstmt.executeQuery();
				//5 rs 첫번째 행으로이동 데이터 있는 경우
				// mb 객체 생성 
				// rs id열 => mb id멤버변수 저장
				if(rs.next()){
					mb=new MemberBean();
					mb.setId(rs.getString("id"));
					mb.setPass(rs.getString("pass"));
					mb.setName(rs.getString("name"));
					mb.setFile(rs.getString("file"));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(rs!=null){try{rs.close();}catch(SQLException ex){}}
				if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}}
				if(con!=null){try{con.close();}catch(SQLException ex){}}
			}
			return mb;
		}
		
		//회원수정메서드
		public int updateMember(MemberBean mb){
			Connection con=null;
			String sql="";
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			int check=-1;
			try {
				//1,2 디비연결 메서드호출
				con=getConnection();
				//3 sql 조건 id에 해당하는 passwd 가져오기
				sql="select pass from member where id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, mb.getId());
				//4 rs 실행 결과 저장
				rs=pstmt.executeQuery();
				//5 rs데이터 있으면 아이디있음
				//      비밀번호 비교 맞으면  check=1
				//      //3 sql update 
		//  조건 id해당하는 name age gender email 수정
				//       4 실행
				//       틀리면 "비밀번호틀림" check=0
				//  없으면 "아이디없음" check=-1
				if(rs.next()){
					//아이디있음 //비밀번호비교
					if(mb.getPass().equals(rs.getString("pass"))){
						check=1;//맞는경우
						//3 update 수정
						sql="update member set name=?,email=?, file=?  where id=?";
						pstmt=con.prepareStatement(sql);
						pstmt.setString(1, mb.getName());
						pstmt.setString(2, mb.getEmail());
						pstmt.setString(3, mb.getFile());
						pstmt.setString(4, mb.getId());
						//4 실행
						pstmt.executeUpdate();
					}else{
						check=0;//틀린경우
					}
				}else{
					check=-1;//아이디없음
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(rs!=null){try{rs.close();}catch(SQLException ex){}}
				if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}}
				if(con!=null){try{con.close();}catch(SQLException ex){}}
			}
			return check;
		}
		// deleteMember(id,passwd)
		public int deleteMember(String id,String pass){
			Connection con=null;
			String sql="";
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			int check=-1;
			try {
				//1,2 디비연결
				con=getConnection();
				//3 id조건에 해당하는 passwd 가져오기
				sql="select pass from member where id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);;
				//4 실행 => rs저장
				rs=pstmt.executeQuery();
				//5 rs 첫행으로 이동 데이터 있는경우
				// 비밀번호비교 맞으면 check=1  
				   //3 delete//4실행
				//틀리면 check=0
				// 없는경우 아이디없음 check=-1
				if(rs.next()){
					if(pass.equals(rs.getString("pass"))){
						check=1;
						//3 sql delete
						sql="delete from member where id=?";
						pstmt=con.prepareStatement(sql);
						pstmt.setString(1, id);
						//4 실행
						pstmt.executeUpdate();
					}else{
						check=0;
					}
				}else{
					check=-1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(rs!=null){try{rs.close();}catch(SQLException ex){}}
				if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}}
				if(con!=null){try{con.close();}catch(SQLException ex){}}
			}
			return check;
		}
		
}
