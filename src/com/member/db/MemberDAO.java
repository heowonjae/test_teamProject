package com.member.db;

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
	
		//��񿬰� �޼���
		private Connection getConnection() throws Exception{
		String dbUrl="jdbc:mysql://localhost:3306/testdb";
		String dbUser="tester";
		String dbPass="1234";
		Connection con=null;
//			// 1�ܰ� ����̹� �δ�
		Class.forName("com.mysql.jdbc.Driver");
//			// 2�ܰ� ��񿬰�
		con=DriverManager.getConnection(dbUrl, dbUser, dbPass);
			return con;
			
			
		}
		
		
		
		
		public void insertMember(MemberBean mb){
			Connection con=null;
			String sql="";
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				//1����̹� �δ�//2��񿬰�
				con=getConnection();
				//3sql insert
				sql="insert into member(id,pass,name,email,file) values(?,?,?,?,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, mb.getId());
				pstmt.setString(2, mb.getPass());
				pstmt.setString(3, mb.getName());
				pstmt.setString(4, mb.getEmail());
				pstmt.setString(5, mb.getFile());
				
				//4����
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				//��ü���� ����
				if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}} 
				if(con!=null){try{con.close();}catch(SQLException ex){}}
			}
		}//ȸ�����Ը޼���
		
		
		
		public int userCheck(String id,String pass){
			Connection con=null;
			String sql="";
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			int check=-1;
			try {
				//1 ����̹� �δ�			//2 ��񿬰�
				con=getConnection();
				//3 sql id���� �����ϴ� passwd ��������
			sql="select pass from member where id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				//4  ���� => rs ����
				rs=pstmt.executeQuery();
				//5 rs ������ ������ ���̵�����
				//    ��й�ȣ �� ������  check=1
				//              Ʋ���� check=0
				//  ������ ���̵����  check=-1
				if(rs.next()){
					//���̵�����
					if(pass.equals(rs.getString("pass"))){
						check=1;//��й�ȣ����
					}else{
						check=0;//��й�ȣƲ��
					}
				}else{
					check=-1;//���̵����
				}
			}catch(Exception e){
				 e.printStackTrace();
			}finally{
				if(rs!=null){try{rs.close();}catch(SQLException ex){}}
				if(pstmt!=null){try{pstmt.close();}catch(SQLException ex){}}
				if(con!=null){try{con.close();}catch(SQLException ex){}}
			}
			return check;
		}//����üũ�޼���
		
		//ȸ��������ȸ�޼���
		public MemberBean getMember(String id){
			Connection con=null;
			String sql="";
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			MemberBean mb=null;
			try {
				//1 ����̹� �δ�		//2 ��񿬰�
				con=getConnection();
				//3 sql id���ǿ� �����ϴ� member������������
				sql="select * from member where id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				//4 rs ���� ����
				rs=pstmt.executeQuery();
				//5 rs ù��° �������̵� ������ �ִ� ���
				// mb ��ü ���� 
				// rs id�� => mb id������� ����
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
		
		//ȸ�������޼���
		public int updateMember(MemberBean mb){
			Connection con=null;
			String sql="";
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			int check=-1;
			try {
				//1,2 ��񿬰� �޼���ȣ��
				con=getConnection();
				//3 sql ���� id�� �ش��ϴ� passwd ��������
				sql="select pass from member where id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, mb.getId());
				//4 rs ���� ��� ����
				rs=pstmt.executeQuery();
				//5 rs������ ������ ���̵�����
				//      ��й�ȣ �� ������  check=1
				//      //3 sql update 
		//  ���� id�ش��ϴ� name age gender email ����
				//       4 ����
				//       Ʋ���� "��й�ȣƲ��" check=0
				//  ������ "���̵����" check=-1
				if(rs.next()){
					//���̵����� //��й�ȣ��
					if(mb.getPass().equals(rs.getString("pass"))){
						check=1;//�´°��
						//3 update ����
						sql="update member set name=?,email=?, file=?  where id=?";
						pstmt=con.prepareStatement(sql);
						pstmt.setString(1, mb.getName());
						pstmt.setString(2, mb.getEmail());
						pstmt.setString(3, mb.getFile());
						pstmt.setString(4, mb.getId());
						//4 ����
						pstmt.executeUpdate();
					}else{
						check=0;//Ʋ�����
					}
				}else{
					check=-1;//���̵����
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
				//1,2 ��񿬰�
				con=getConnection();
				//3 id���ǿ� �ش��ϴ� passwd ��������
				sql="select pass from member where id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);;
				//4 ���� => rs����
				rs=pstmt.executeQuery();
				//5 rs ù������ �̵� ������ �ִ°��
				// ��й�ȣ�� ������ check=1  
				   //3 delete//4����
				//Ʋ���� check=0
				// ���°�� ���̵���� check=-1
				if(rs.next()){
					if(pass.equals(rs.getString("pass"))){
						check=1;
						//3 sql delete
						sql="delete from member where id=?";
						pstmt=con.prepareStatement(sql);
						pstmt.setString(1, id);
						//4 ����
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
