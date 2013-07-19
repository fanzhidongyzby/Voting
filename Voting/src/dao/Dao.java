package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Candidate;
import model.User;
import util.DBConnection;

public class Dao {
	
	public ArrayList<Candidate> getCandidateList(){
		String sql = "";
		ResultSet rs = null;
		ArrayList<Candidate>  canList = new ArrayList<Candidate>();
		Connection conn = DBConnection.getConnection();
		sql = "select * from candidate order by name desc ";
		PreparedStatement ps = DBConnection.prepare(conn, sql);
		try {
			rs = ps.executeQuery();
			while(rs.next()){
				Candidate can = new Candidate();
				can.setBirthday(rs.getString("birthday"));
				can.setEduBgd(rs.getString("eduBgd"));
				can.setId(rs.getInt("id"));
				can.setIdNo(rs.getString("idNo"));
				can.setNation(rs.getString("nation"));
				can.setName(rs.getString("name"));
				can.setOther(rs.getString("other"));
				can.setPassword(rs.getString("password"));
				can.setPhotoPath(rs.getString("photoPath"));
				can.setPoliStatus(rs.getString("poliStatus"));
				can.setPosition(rs.getString("position"));
				can.setResume(rs.getString("resume"));
				can.setSex(rs.getString("sex"));
				can.setTel(rs.getString("tel"));
				can.setVoteNum(rs.getInt("voteNum"));
				can.setWorkPlace(rs.getString("workPlace"));
				
				canList.add(can);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(conn);
		}
		return canList;
	}
	public int isVoter(String idNo){
		String sql = "";
		int result = 0;
		ResultSet rs = null;
		Connection conn = DBConnection.getConnection();
		sql = "select idNo,isVote from voter where idNo = '"+idNo+"'"; 
		PreparedStatement ps = DBConnection.prepare(conn, sql);
		try {
			rs = ps.executeQuery();
			if(rs.next()){
				 int i = rs.getInt("isVote");
				 if(i == 0&&rs.getString("idNo").equals(idNo)){//没有投票
					 result =  1;//合法用户
				 }else result = 2;//投过票
			}
			else result = 0;//无用户
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(conn);
		}
		return result;
	}
	public int vote(String idNo, ArrayList<String> cans){
		String sql = "";
		int rs=0;
		Connection conn = DBConnection.getConnection();
		sql = "update voter set isVote=1 where idNo='"+idNo+"'";
		Statement st=null;
		try {
			st=conn.createStatement();
			conn.setAutoCommit(false);//禁止事务自动提交
			st.addBatch(sql);//添加事务
			String coll="";
			for(int i=0;i<cans.size()-1;i++)
			{
				coll+=cans.get(i)+",";
			}
			coll+=cans.get(cans.size()-1);
			sql = "update candidate set voteNum=voteNum+1 where id in("+coll+")";
			st.addBatch(sql);//添加事务
			st.executeBatch();//执行事务
			conn.commit(); //提交
			conn.setAutoCommit(true);// 恢复默认    
			rs=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			try {
				conn.rollback();//回滚
				conn.setAutoCommit(true);//设置提交方式为默认方式
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//出现sql异常，事务回滚  
            
		}
		finally
		{
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBConnection.close(conn);
		}
		return rs;
	}
	
	public Candidate getCandidate(int canId){
		String sql = "";
		ResultSet rs = null;
		Candidate can = null;
		Connection conn = DBConnection.getConnection();
		sql = "select * from candidate where id = "+canId;
		PreparedStatement ps = DBConnection.prepare(conn, sql);
		try {
			rs = ps.executeQuery();
			if(rs.next()){
				can = new Candidate();
				can.setBirthday(rs.getString("birthday"));
				can.setEduBgd(rs.getString("eduBgd"));
				can.setId(rs.getInt("id"));
				can.setIdNo(rs.getString("idNo"));
				can.setNation(rs.getString("nation"));
				can.setName(rs.getString("name"));
				can.setOther(rs.getString("other"));
				can.setPassword(rs.getString("password"));
				can.setPhotoPath(rs.getString("photoPath"));
				can.setPoliStatus(rs.getString("poliStatus"));
				can.setPosition(rs.getString("position"));
				can.setResume(rs.getString("resume"));
				can.setSex(rs.getString("sex"));
				can.setTel(rs.getString("tel"));
				can.setVoteNum(rs.getInt("voteNum"));
				can.setWorkPlace(rs.getString("workPlace"));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(conn);
		}
		return can;
		
	}
	public boolean editCandidate(Candidate can){
		
		boolean result  = false;
		String sql = "";
		Connection conn = DBConnection.getConnection();
		
		sql = "update  candidate set password=?, birthday=?,eduBgd=?," +
		"nation=?,name=?,other=?,poliStatus=?,position=?," +
		" resume=?,tel=?,sex=?,workPlace=?,photoPath=? " +
		"where id= "+can.getId();
			
		
		PreparedStatement ps = DBConnection.prepare(conn, sql);
		
			try {
				ps.setString(1, can.getPassword());
				ps.setString(2, can.getBirthday());
				ps.setString(3, can.getEduBgd());
				ps.setString(4, can.getNaiton());
				ps.setString(5, can.getName());
				ps.setString(6,can.getOther() );
				ps.setString(7, can.getPoliStatus());
				ps.setString(8, can.getPosition());
				ps.setString(9,can.getResume() );
				ps.setString(10, can.getTel());
				ps.setString(11, can.getSex());
				
				ps.setString(12, can.getWorkPlace());
				
					ps.setString(13, can.getPhotoPath());
				
				if(ps.executeUpdate()>0)result =true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				DBConnection.close(ps);
				DBConnection.close(conn);
			}
		return result;
		
		
	}
public boolean editCandidateWithoutImg(Candidate can){
		
		boolean result  = false;
		String sql = "";
		Connection conn = DBConnection.getConnection();
		
			sql = "update  candidate set password=?, birthday=?,eduBgd=?," +
			"nation=?,name=?,other=?,poliStatus=?,position=?," +
			" resume=?,tel=?,sex=?,workPlace=? " +
			"where id= "+can.getId();
		
		
		PreparedStatement ps = DBConnection.prepare(conn, sql);
		
			try {
				ps.setString(1, can.getPassword());
				ps.setString(2, can.getBirthday());
				ps.setString(3, can.getEduBgd());
				ps.setString(4, can.getNaiton());
				ps.setString(5, can.getName());
				ps.setString(6,can.getOther() );
				ps.setString(7, can.getPoliStatus());
				ps.setString(8, can.getPosition());
				ps.setString(9,can.getResume() );
				ps.setString(10, can.getTel());
				ps.setString(11, can.getSex());
			
				ps.setString(12, can.getWorkPlace());
				if(ps.executeUpdate()>0)result =true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				DBConnection.close(ps);
				DBConnection.close(conn);
			}
		return result;
		
		
	}
	public boolean addCandidate(Candidate can){
		
		boolean result  = false;
		String sql = "";
		Connection conn = DBConnection.getConnection();
		
			//sql = "insert into  candidate(name,idNo,password)values('?','?','?')" ;
		sql = "insert into  candidate(name,idNo,password)values('"+can.getName()+"','"
		+can.getIdNo()+"','"+can.getPassword()+"')" ;
		
		PreparedStatement ps = DBConnection.prepare(conn, sql);
		
			try {
//				ps.setString(1, can.getName());
//				ps.setString(2, can.getIdNo());
//				ps.setString(3,can.getPassword());
				
				if(ps.executeUpdate()>0)result =true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				DBConnection.close(ps);
				DBConnection.close(conn);
			}
		return result;
		
	}
	
	public boolean delCandidate(int canId){
		boolean result  = false;
		String sql = "";
		Connection conn = DBConnection.getConnection();
		
			sql = "delete from  candidate where id=" +canId;
		
		PreparedStatement ps = DBConnection.prepare(conn, sql);
		
			try {
				if(ps.executeUpdate()>0)result =true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				DBConnection.close(ps);
				DBConnection.close(conn);
			}
		return result;
		
	}
	public User login(String uname,String psw)
	{
		String sql = "";
		ResultSet rs = null;
		User user = null;
		int result=3;
		Connection conn = DBConnection.getConnection();
		sql = "select * from manager where account = '"+uname+"'";
		PreparedStatement ps = DBConnection.prepare(conn, sql);
		try {
			rs = ps.executeQuery();
			if(rs.next()){
				
				if(rs.getString("password").equals(psw))
				{user = new User();
//					result=0;//管理员
					user.setId(rs.getInt("id"));
					user.setAccount(uname);
					user.setPassword(psw);
					user.setUserType("admin");
					
				}
			}
			else
			{
				sql = "select * from candidate where idNo = '"+uname+"'";
				DBConnection.close(rs);
				DBConnection.close(ps);
				ps = DBConnection.prepare(conn, sql);
				rs = ps.executeQuery();
				if(rs.next()){
					
					if(rs.getString("password").equals(psw))
					{user = new User();
//						result=1;//候选人
						user.setId(rs.getInt("id"));
						user.setAccount(uname);
						user.setPassword(psw);
						user.setUserType("candidate");
					}
				}else user = null;
				
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			DBConnection.close(rs);
			DBConnection.close(ps);
			DBConnection.close(conn);
		}
		return user;
	}
	
	public boolean uploadInfo(String path)
	{
		File file = new File(path);//获得物理路径
		String sql = "";
		boolean rs=false;
		Connection conn = DBConnection.getConnection();
		sql = "delete from voter;";//清除选民信息
		Statement st=null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			st=conn.createStatement();
			conn.setAutoCommit(false);//禁止事务自动提交
			st.addBatch(sql);//添加清除事务
			//读取文件
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line="";
			while(line!=null)
			{
				line=br.readLine();
				if(line==null)
					break;
				else
				{
					line=line.trim();
					if("".equals(line))
						continue;
				}
				sql = "insert into voter (idNo,isVote)values('"+line+"',0);";
				st.addBatch(sql);//添加插入事务
			}
			st.executeBatch();//执行事务
			conn.commit(); //提交
			conn.setAutoCommit(true);// 恢复默认    
			rs=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();//回滚
				conn.setAutoCommit(true);//设置提交方式为默认方式
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//出现sql异常，事务回滚
            
		}
		finally
		{
			try {
				st.close();
				br.close();
				fr.close();
			} catch (final Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBConnection.close(conn);
		}
		return rs;
	}
}
