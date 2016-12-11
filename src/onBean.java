package db;
import java.io.*;
import java.sql.*;
import javax.sql.*;
import java.util.*;
public class onBean
{
	private Statement st=null;
	private Connection con=null;
	
	public onBean ()
	{
		try{
		System.out.println("This is constructor");
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		con = DriverManager.getConnection("jdbc:odbc:onlinedsn","oneva1","oneva1");
		st= con.createStatement();
		}catch(Exception e){
			System.out.println("Problem in connecting to DataBase :"+e);
		}
	}
	public void setCommit(boolean flag)
	{
		try
		{
			if(flag)
				con.commit();
			else
				con.rollback();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public int insertData(String query)
	{
		System.out.println(query);
		int returnvalue=0;
		try{
		returnvalue=st.executeUpdate(query);
		}catch(Exception e){
			System.out.println("Problem in insertData():"+e);
		}
		return returnvalue;
	}

	public void setCommitState(boolean flag)
	{
		try
		{
			con.setAutoCommit(flag);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public int getMaxId(String tablename,String field)
	{
		int returnvalue=0;
		try{
		ResultSet rs=st.executeQuery("select max("+field+") from "+tablename);
		if(rs.next())
			returnvalue= rs.getInt(1)+1;
		else
			returnvalue = 1;
		}catch(Exception e){
			System.out.println("Problem in GetMaxid():"+e);
		}
		return returnvalue;
	}

	public Vector getIds(String tablename,String fields)
					{
						System.out.println("in queryhhhhhhhhhhhh");
						Vector returnval=new Vector();
								try	{
									String query ="select  "+fields+" from "+tablename;
									System.out.println("ppppppppp"+query);
									ResultSet rs=st.executeQuery(query);
														while(rs.next())
									{
											
															returnval.addElement(rs.getString(1));
									} 
								
												}   catch(Exception e)  {
													System.out.println(e);
												}
												
								return returnval;
	}



public int pData(int bid,String bname,String descp,java.sql.Date sdts,java.sql.Date edts,int cid,int tid,int stre)
	{
		
		int returnvalue=0;
		try{

PreparedStatement inser = con.prepareStatement("insert into batch values(?,?,?,?,?,?,?,?)");

			inser.setInt(1,bid);
			inser.setString(2,bname);
			inser.setString(3,descp);
			inser.setDate(4,sdts);
			inser.setDate(5,edts);
			inser.setInt(6,cid);
			inser.setInt(7,tid);
			inser.setInt(8,stre);
			returnvalue=inser.executeUpdate();
		
		}catch(Exception e){
			System.out.println("Problem in insertData():"+e);
		}
		return returnvalue;
	}

public Hashtable getBatchDetails(String id)
	{
		Hashtable returnvalue=new Hashtable();
		System.out.println("lllllllllllids is "+id);
		//int pp=Integer.parseInt(id);
		//System.out.println("lllllllllllids is "+pp);
		String query="select b.NAME,b.BDESC,b.STARTDATE,b.ENDDATE,c.NAME,c.COST from COURSE c,batch b where b.COURSEID=c.COURSEID and BATCHID="+id;
		System.out.println(query);
		try{
		Statement st1=con.createStatement();
		ResultSet rs=st1.executeQuery(query);
		while(rs.next()){
		returnvalue.put("name",rs.getString(1));
		returnvalue.put("desc",rs.getString(2));
		returnvalue.put("sdt",rs.getDate(3));
		returnvalue.put("edt",rs.getDate(4));
		returnvalue.put("cname",rs.getString(5));
		returnvalue.put("cost",new Float(rs.getFloat(6)));
		}
		}catch(Exception e){
			System.out.println("Problem in retriveing data in getBatchDetails():"+e);
		}
		return returnvalue;
	}



public String getMaxcustId(String person)
	{
		Random r=new Random();
		int i=r.nextInt(5000);
		String returnvalue=null;
		if(person.equals("cust"))
			returnvalue="cust"+i;
		/*else if(person.equals("publ"))
			returnvalue="publ"+i;
		/*try{
		ResultSet rs=st.executeQuery("select max(pwd) from login where status='"+person+"'");
		while(rs.next()){
		String temp= rs.getString(1);
		String temp1=temp.substring(0,4);
		returnvalue=temp1+(Integer.parseInt(temp.substring(4))+1);}
		}catch(Exception e){
			System.out.println("Problem in getMaxcustId():"+e);
		}*/
		return returnvalue;
	}

public String getpass(String loginid)
	{
		String returnvalue=new String();
		try{
			String qr="select password  from login where loginid='"+loginid+"'";
		ResultSet rs=st.executeQuery(qr);
		if(rs.next())
			returnvalue= rs.getString(1);
		else
			returnvalue = "";
		}catch(Exception e){
			System.out.println("Problem in getpass():"+e);
		}
		return returnvalue;
	}


public float getcostst(String bid)
	{
		float returnvalue=0;
		try{
			String qr="select c.COST  from batch b,course c  where c.COURSEID=b.COURSEID and BATCHID="+bid;
		System.out.println("ttttttttt"+qr);
		ResultSet rs=st.executeQuery(qr);
		if(rs.next())
			returnvalue= rs.getFloat(1);
		else
			returnvalue = 0;
		}catch(Exception e){
			System.out.println("Problem in getpass():"+e);
		}
		return returnvalue;
	}
public int getMaxIId(String tablename,String field)
	{
		int returnvalue=5000;
		try{
		ResultSet rs=st.executeQuery("select max("+field+") from "+tablename);
		if(rs.next())
			returnvalue= rs.getInt(1)+1;
		else
			returnvalue = 5000;
		}catch(Exception e){
			System.out.println("Problem in getMaxIId():"+e);
		}
		return returnvalue;
	}

/*public Hashtable getCustomerDetails(String id)
	{
		Hashtable returnvalue=new Hashtable();
		String query="select * from customer where cust_id='"+id+"'";
		System.out.println(query);
		try{
		Statement st1=con.createStatement();
		ResultSet rs=st1.executeQuery(query);
		while(rs.next()){
	
		returnvalue.put("CUST_ID",rs.getString("CUST_ID"));
		returnvalue.put("CUST_NAME",rs.getString("CUST_NAME"));
		returnvalue.put("CUST_CITY",rs.getString("CUST_CITY"));
		returnvalue.put("CUST_STATE",rs.getString("CUST_STATE"));
		returnvalue.put("CUST_COUNTRY",rs.getString("CUST_COUNTRY"));
		returnvalue.put("CUST_FAX1",rs.getString("CUST_FAX1"));
		returnvalue.put("CUST_DOB",rs.getDate("CUST_DOB"));
		returnvalue.put("CUST_MAIL",rs.getString("CUST_MAIL"));
		returnvalue.put("SEX",rs.getString("SEX"));
		returnvalue.put("CUST_RESNO",rs.getString("CUST_RESNO"));
		returnvalue.put("CUST_OFFNO",rs.getString("CUST_OFFNO"));
		returnvalue.put("CUST_MOBILE",rs.getString("CUST_MOBILE"));
		returnvalue.put("CUST_ADD2",rs.getString("CUST_ADD2"));
		returnvalue.put("BATCHID",new Integer(rs.getInt("BATCHID")));


		 
		  
		  }
		}catch(Exception e){
			System.out.println("Problem in retrieving data in getCustomerDetails():"+e);
		}
		return returnvalue;
	}*/

public Hashtable getBatchls(String id)
	{
		Hashtable returnvalue=new Hashtable();
		String query="select * from customer where cust_id='"+id+"'";
		System.out.println(query);
		try{
		Statement st1=con.createStatement();
		ResultSet rs=st1.executeQuery(query);
		while(rs.next()){
	
		returnvalue.put("BATCHID",new Integer(rs.getInt("BATCHID")));
		returnvalue.put("NAME",rs.getString("NAME"));
		returnvalue.put("BDESC",rs.getString("BDESC"));
		returnvalue.put("STARTDATE",rs.getDate("STARTDATE"));
		returnvalue.put("ENDDATE",rs.getDate("ENDDATE"));
		returnvalue.put("COURSEID",new Integer(rs.getInt("COURSEID")));
		returnvalue.put("TUTORID",new Integer(rs.getInt("TUTORID")));
		returnvalue.put("STRENGTH",new Integer(rs.getInt("STRENGTH")));
		 
		  
		  }
		}catch(Exception e){
			System.out.println("Problem in retrieving data in getBatchls():"+e);
		}
		return returnvalue;
	}

public Hashtable getCoursedtls(String id)
	{
		Hashtable returnvalue=new Hashtable();
		String query="select * from customer where cust_id='"+id+"'";
		System.out.println(query);
		try{
		Statement st1=con.createStatement();
		ResultSet rs=st1.executeQuery(query);
		while(rs.next()){
	

		returnvalue.put("COURSEID",new Integer(rs.getInt("COURSEID")));
		returnvalue.put("NAME",rs.getString("NAME"));
		returnvalue.put("DURATION",rs.getString("DURATION"));
		returnvalue.put("COST",new Float(rs.getFloat("COST")));
		returnvalue.put("CDESC",rs.getString("CDESC"));
				 
		  
		  }
		}catch(Exception e){
			System.out.println("Problem in retrieving data in getCoursedtls():"+e);
		}
		return returnvalue;
	}

public boolean adminLogin(String name,String pwd)
	{
		if(name.equalsIgnoreCase("admin")&&pwd.equalsIgnoreCase("admin"))
			return true;
		else
			return false;
		
	}

	public String check(String name,String pwd,String type)
	{
		String returnValue="";
		try{
			System.out.println(";kkkkkkkkkkkkkk"+name+pwd+type);
			String qr="select PASSWORD from login where loginid='"+ name +"' and type='"+type+"'";
			System.out.println("llllllll"+qr);
			ResultSet rs=st.executeQuery(qr);
		if(rs.next()==false)
			returnValue="NO USER WITH THIS ID";
		else
			{
				if(pwd.equalsIgnoreCase((String)rs.getString(1).trim()))
					returnValue="true";
				else
					returnValue="Login Failed";
			}
					}catch(Exception e){
			System.out.println("Problem in retriveing from  DataBase check():"+e);
		}
		return returnValue;
	}
	

public Hashtable getcustfortut(String id)
	{
		Hashtable returnvalue=new Hashtable();
		String query="select a.CUST_ID,a.cust_name,a.cust_address,a.CUST_MOBILE,b.tutorid from customer a,batch b where a.BATCHID=b.BATCHID and b.BATCHID='"+id+"'";
		System.out.println(query);
		try{
		Statement st1=con.createStatement();
		ResultSet rs=st1.executeQuery(query);
		while(rs.next()){
	String rows = "";
				String rno = rs.getString(1);
System.out.println(rno+"cust ID");
String cname = rs.getString(2);
String caddr = rs.getString(3);
String cmob = rs.getString(4);

rows = cname+"!?"+caddr+"!?"+cmob; 
returnvalue.put(rno,rows);
		
		  }
		}catch(Exception e){
			System.out.println("Problem in retrieving data in getcustfortut():"+e);
		}
		return returnvalue;
	
		
	}

	public Vector getbattutIds(String tids)
	{
		System.out.println("in queryhhhhhhhhhhhh");
		Vector returnval=new Vector();
		try
		{
			String query ="select BATCHID from batch where tutorid='"+tids+"'";
			System.out.println("ppppppppp"+query);
			ResultSet rs=st.executeQuery(query);
			while(rs.next())
			{
				returnval.addElement(rs.getString(1));
			} 
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return returnval;
	}
	
	
	public Hashtable getPaymentDtl(String bid)
	{
		Hashtable pdtlht=new Hashtable();
		try{
			Statement st1=con.createStatement();
			String qry="select a.payid,a.payamount,to_char(a.paydate,'dd-mm-yyyy'),a.paymode,a.cust_id from paymentmaster a,customer b where a.cust_id=b.cust_id and b.batchid='"+bid+"'";
				System.out.println("datails for tutor batch qr"+qry);
				ResultSet rs = st1.executeQuery(qry);
				while(rs.next())
			{
				
				String rows = "";
				String rno = rs.getString(1);
				double payamount=rs.getDouble(2);
				String paydate = rs.getString(3);
				String paymode = rs.getString(4);
				String cust_id=rs.getString(5);
				rows=payamount+"&?"+paydate+"&?"+paymode+"&?"+cust_id;
				pdtlht.put(rno,rows);
			}
		   }catch(Exception e){
			System.out.println("Problem in getPaymentDtl:"+e);
		}
		return pdtlht;
	}
public Hashtable getbatdetailfortut(String tid)
	{
		Hashtable pdtlht=new Hashtable();
		try{
			Statement st1=con.createStatement();
			String qry="select batchId,name,to_char(startdate,'dd-mm-yyyy'),to_char(enddate,'dd-mm-yyyy'),courseid from batch where tutorid='"+tid+"'";
				System.out.println("datails for tutor batch qr"+qry);
				ResultSet rs = st1.executeQuery(qry);
				while(rs.next())
			{
				
				String rows = "";
				String rno = rs.getString(1);

				String name=rs.getString(2);
				String stdate = rs.getString(3);
				String enddate = rs.getString(4);
				String cid=rs.getString(5);
				rows=name+"&?"+stdate+"&?"+enddate+"&?"+cid;
				pdtlht.put(rno,rows);
			}
		   }catch(Exception e){
			System.out.println("Problem in getbatdetailfortut:"+e);
		}
		return pdtlht;
	}

public Hashtable getCustomerDetails(String id)
	{
		Hashtable returnvalue=new Hashtable();
		String query="select CUST_ID,CUST_NAME,CUST_ADDRESS,CUST_CITY,CUST_STATE,CUST_COUNTRY,CUST_FAX1,TO_CHAR(CUST_DOB,'DD-MM-YYYY') DOB,CUST_MAIL,SEX,CUST_RESNO,CUST_OFFNO,CUST_MOBILE,CUST_ADD2,BATCHID from customer where cust_id='"+id+"'";
		System.out.println(query);
		try{
		Statement st1=con.createStatement();
		ResultSet rs=st1.executeQuery(query);
		while(rs.next()){
	
		returnvalue.put("CUST_ID",rs.getString("CUST_ID"));
		returnvalue.put("CUST_NAME",rs.getString("CUST_NAME"));
		returnvalue.put("CUST_ADDRESS",rs.getString("CUST_ADDRESS"));
		returnvalue.put("CUST_CITY",rs.getString("CUST_CITY"));
		returnvalue.put("CUST_STATE",rs.getString("CUST_STATE"));
		returnvalue.put("CUST_COUNTRY",rs.getString("CUST_COUNTRY"));
		returnvalue.put("CUST_FAX1",rs.getString("CUST_FAX1"));
		returnvalue.put("CUST_DOB",rs.getString("DOB"));
		returnvalue.put("CUST_MAIL",rs.getString("CUST_MAIL"));
		returnvalue.put("SEX",rs.getString("SEX"));
		returnvalue.put("CUST_RESNO",rs.getString("CUST_RESNO"));
		returnvalue.put("CUST_OFFNO",rs.getString("CUST_OFFNO"));
		returnvalue.put("CUST_MOBILE",rs.getString("CUST_MOBILE"));
		returnvalue.put("CUST_ADD2",rs.getString("CUST_ADD2"));
		returnvalue.put("BATCHID",new Integer(rs.getInt("BATCHID")));
	 		  
	  }
	}
		catch(Exception e){
			System.out.println("Problem in retrieving data in getCustomerDetails():"+e);
		}
		return returnvalue;
	}

//new Method*************************
	public Hashtable getCustomers()
	{
		Hashtable returnvalue=new Hashtable();
		try{
		ResultSet rs=st.executeQuery("select * from customer");
		while(rs.next())
			{
				String data=new String();
				data+=rs.getString("CUST_NAME")+"?&";
				data+=rs.getString("CUST_CITY")+"?&";
				data+=rs.getString("CUST_MAIL")+"?&";
				data+=rs.getString("SEX")+"?&";
				data+=rs.getString("CUST_ADD2")+"?&";
								
				returnvalue.put(rs.getString("CUST_ID"),data);
			}
		}catch(Exception e){
			System.out.println("Problem in retriveing data in getCustomers():"+e);
		}
		return returnvalue;
	}

//new Method*************************

public Hashtable getCustomerperto(String id)
	{
		Hashtable returnvalue=new Hashtable();
		try{
		ResultSet rs=st.executeQuery("select * from customer where cust_id='"+id+"'");
		while(rs.next())
			{
				String data=new String();
				data+=rs.getString("CUST_NAME")+"?&";
				data+=rs.getString("CUST_CITY")+"?&";
				data+=rs.getString("CUST_MAIL")+"?&";
				data+=rs.getString("SEX")+"?&";
				data+=rs.getString("CUST_ADD2")+"?&";
								
				returnvalue.put(rs.getString("CUST_ID"),data);
			}
		}catch(Exception e){
			System.out.println("Problem in retriveing data in getCustomers():"+e);
		}
		return returnvalue;
	}
	
	public Hashtable getres(String id)	
	{
	   	Hashtable returnvalue=new Hashtable();
		try{
		int bid = 0;
		System.out.println("00000000000000");
		ResultSet rs = st.executeQuery("SELECT BATCHID FROM CUSTOMER WHERE CUST_ID='"+id+"'");
		System.out.println("11111111111111");
		if(rs.next())
		{
			bid = rs.getInt(1);
			System.out.println("BatchId : "+bid);
		}
		rs = null;
		rs=st.executeQuery("select MEG_ID,TO_CHAR(TITLE,'DD-MM-YYYY') from  RESRCS where BATCHID="+bid+" AND TITLE<=SYSDATE");
		while(rs.next()){
			String megid = rs.getInt(1)+"";
			System.out.println("MegId : "+megid);
			String val = rs.getString(2);
			System.out.println("Title : "+val);
			returnvalue.put(megid,val);
			}
		}catch(Exception e){
			System.out.println("Problem in retriveing data in getres():");
				e.printStackTrace();
		}
		return returnvalue;
	}
public Hashtable getTutors()
	{
		Hashtable returnvalue=new Hashtable();
		try{
		ResultSet rs=st.executeQuery("select * from tutor");
		while(rs.next())
			{
				String data=new String();
				data+=rs.getString("NAME")+"?&";
				data+=rs.getString("QUALI")+"?&";
				data+=rs.getString("EXP")+"?&";
				data+=rs.getString("ADDRESS")+"?&";
				data+=rs.getString("EMAIL")+"?&";
				data+=(new Integer(rs.getInt("PHONE")))+"?&";
								
				returnvalue.put(rs.getString("TUTORID"),data);
			}
		}catch(Exception e){
			System.out.println("Problem in retriveing data in getTutors():"+e);
		}
		return returnvalue;
	}
	
	public Hashtable getTutorsPer(String id)
	{
		Hashtable returnvalue=new Hashtable();
		try{
		ResultSet rs=st.executeQuery("select * from tutor where tutorid='"+id+"'");
		while(rs.next())
			{
				String data=new String();
				data+=rs.getString("NAME")+"?&";
				data+=rs.getString("QUALI")+"?&";
				data+=rs.getString("EXP")+"?&";
				data+=rs.getString("ADDRESS")+"?&";
				data+=rs.getString("EMAIL")+"?&";
				data+=(new String(rs.getString("PHONE")))+"?&";
								
				returnvalue.put(rs.getString("TUTORID"),data);
			}
		}catch(Exception e){
			System.out.println("Problem in retriveing data in getTutors():"+e);
		}
		return returnvalue;
	}

	public Hashtable getTutorDetails(int id)
	{
		Hashtable returnvalue=new Hashtable();
		String query="select * from tutor where TUTORID="+id+"";
		System.out.println(query);
		try{
		Statement st1=con.createStatement();
		ResultSet rs=st1.executeQuery(query);
		while(rs.next()){
	
		returnvalue.put("TUTORID",rs.getString("TUTORID"));
		returnvalue.put("NAME",rs.getString("NAME"));
		returnvalue.put("QUALI",rs.getString("QUALI"));
		returnvalue.put("EXP",rs.getString("EXP"));
		returnvalue.put("ADDRESS",rs.getString("ADDRESS"));
		returnvalue.put("EMAIL",rs.getString("EMAIL"));
		returnvalue.put("PHONE",new String(rs.getString("PHONE")));
	 		  
	  }
	}
		catch(Exception e){
			System.out.println("Problem in retrieving data in getTutorDetails():"+e);
		}
		return returnvalue;
	}
//new Method*************************

public Hashtable getBatchDetails(int id)
	{
		Hashtable returnvalue=new Hashtable();
		String query="select BATCHID,NAME,BDESC,TO_CHAR(STARTDATE,'DD-MM-YYYY') startdate,TO_CHAR(ENDDATE,'DD-MM-YYYY') enddate,COURSEID,TUTORID,STRENGTH from batch where BATCHID="+id+"";
		System.out.println(query);
		try{
		Statement st1=con.createStatement();
		ResultSet rs=st1.executeQuery(query);
		while(rs.next()){
	
		returnvalue.put("BATCHID",new Integer(rs.getInt("BATCHID")));
		returnvalue.put("NAME",rs.getString("NAME"));
		returnvalue.put("BDESC",rs.getString("BDESC"));
		returnvalue.put("startdate",rs.getString("startdate"));
		returnvalue.put("enddate",rs.getString("enddate"));
		returnvalue.put("COURSEID",new Integer(rs.getInt("COURSEID")));
		returnvalue.put("TUTORID",new Integer(rs.getInt("TUTORID")));
		returnvalue.put("STRENGTH",new Integer(rs.getInt("STRENGTH")));
		 
		  
		  }
		}catch(Exception e){
			System.out.println("Problem in retrieving data in getBatchls():"+e);
		}
		return returnvalue;
	}

//new Method*************************
public Hashtable getBatches()
	{
		Hashtable returnvalue=new Hashtable();
		try{
		ResultSet rs=st.executeQuery("select BATCHID,NAME,BDESC,TO_CHAR(STARTDATE,'DD-MM-YYYY') startdate,TO_CHAR(ENDDATE,'DD-MM-YYYY') enddate,COURSEID,TUTORID,STRENGTH from batch");
		while(rs.next())
			{


			
				String data=new String();
				
				data+=rs.getString("NAME")+"?&";
				data+=rs.getString("BDESC")+"?&";
				data+=rs.getString("startdate")+"?&";
				data+=rs.getString("enddate")+"?&";
				data+=(new Integer(rs.getInt("COURSEID")))+"?&";
				data+=(new Integer(rs.getInt("TUTORID")))+"?&";
				data+=(new Integer(rs.getInt("STRENGTH")))+"?&";
								
				returnvalue.put(rs.getString("BATCHID"),data);
			}
		}catch(Exception e){
			System.out.println("Problem in retriveing data in getTutors():"+e);
		}
		return returnvalue;
	}
	
public int getbatch(String cid)
	{
		int bid=0;
		try{
			
			String qr="select batchid from customer where cust_id='"+cid+"'";
			
			System.out.println("llllllll"+qr);
			ResultSet rs=st.executeQuery(qr);
		while(rs.next())
			
			{
				bid=rs.getInt(1);
			}
				
					}catch(Exception e){
			System.out.println("Problem in retriveing from  DataBase check():"+e);
		}
		return bid;
	}


public Hashtable getCourses()
	{
		Hashtable returnvalue=new Hashtable();
		try{
		ResultSet rs=st.executeQuery("select * from course");
		while(rs.next())
			{
				String data=new String();
				data+=rs.getString("NAME")+"?&";
				data+=rs.getString("DURATION")+"?&";
				data+=(new Integer(rs.getInt("COST")))+"?&";
				data+=rs.getString("CDESC")+"?&";
		
				
				returnvalue.put(new Integer(rs.getInt("COURSEID")),data);
				
			}
		}catch(Exception e){
			System.out.println("Problem in retriveing data in getCourses():"+e);
		}
		return returnvalue;
	}


	public Hashtable getCourseDetails(int id)
	{
		Hashtable returnvalue=new Hashtable();
		String query="select * from course where COURSEID="+id+"";
		System.out.println(query);
		try{
		Statement st1=con.createStatement();
		ResultSet rs=st1.executeQuery(query);
		while(rs.next()){
	
		returnvalue.put("COURSEID",new Integer(rs.getInt("COURSEID")));
		returnvalue.put("NAME",rs.getString("NAME"));
		returnvalue.put("DURATION",rs.getString("DURATION"));
		returnvalue.put("COST",new Integer(rs.getInt("COST")));
		returnvalue.put("CDESC",rs.getString("CDESC"));
			  
	  }
	}
		catch(Exception e){
			System.out.println("Problem in retrieving data in getCourseDetails():"+e);
		}
		return returnvalue;
	}





	
	public Hashtable getresdtl(String id)	
	{
	   	Hashtable returnvalue=new Hashtable();
		try{

		ResultSet rs=st.executeQuery("select to_char(TITLE,'dd-mm-yyyy'),RESRC_PATH from  RESRCS where MEG_ID="+id);
		while(rs.next()){
			returnvalue.put(rs.getString(1),rs.getString(2));
			}
		}catch(Exception e){
			System.out.println("Problem in retriveing data in getresdtl():");
				e.printStackTrace();
		}
		return returnvalue;
	}
	
	public void updatTbl(String qu)
	{
		try {
			System.out.println(qu);
		st.executeUpdate(qu);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

    public boolean seekLogin(String query)
	{
		boolean bb=false;
		try
		{
			System.out.println(query);
			ResultSet rs = st.executeQuery(query);
			if (rs.next())
			{
				System.out.println("In Method...");
				bb=true;
				System.out.println("bb : "+bb);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return bb;
    }

	public ResultSet seekLogin1(String query)
	{
		ResultSet rs = null;
		try
		{
			rs = st.executeQuery(query);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return rs;
	}
	
	public Hashtable gettest_qt(String qno3)
  	{
    	Hashtable ht = new Hashtable();
    	try {

      	ResultSet rs = st.executeQuery("select * from test_queries where ques_number="+qno3);
      	while (rs.next()) {
        ht.put("QUES_NUMBER",rs.getInt(1)+"");
        ht.put("QUES_NAME", rs.getString(2));
       	ht.put("QUES_OPT1", rs.getString(3));
        ht.put("QUES_OPT2", rs.getString(4));
        ht.put("QUES_OPT3", rs.getString(5));
        ht.put("QUES_OPT4",rs.getString(6));
        ht.put("QUES_ANS", rs.getString(7));
		ht.put("BATCHID",new Integer(rs.getInt(8)));
            }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return ht;
  }
  
	public Hashtable getBatCustIds(String bid)
  	{
    	Hashtable ht = new Hashtable();
    	try {

      	ResultSet rs = st.executeQuery("select cust_id from customer where batchid="+bid);
      	while (rs.next()) {
		String cus = rs.getString(1);
        ht.put(cus,cus);
            }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return ht;
  }
	public String getTestStatus(String id)
	{
		String bid = new String();
		try
		{
			
			Statement st2 = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT BATCHID FROM CUSTOMER WHERE CUST_ID='"+id+"'");
			if (rs.next())
			{
				bid = rs.getInt(1)+"";
				ResultSet rs2 = st2.executeQuery("SELECT BATCHID FROM ONLINE_TESTLOGIN WHERE EXMDATE LIKE SYSDATE AND BATCHID="+bid+" AND CUST_ID='"+id+"' AND STATUS='NOTTAKEN'");
				System.out.println("In Method...");
				if(!rs2.next())
				{
					bid = null;
				}
				System.out.println("bb : "+bid);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return bid;
    }  

	public Hashtable getBookDetails(String id)
	{
		Hashtable returnvalue=new Hashtable();
		String query="SELECT * FROM book WHERE book_ID='"+id+"'";
		System.out.println(query);
		try{
		ResultSet rs=st.executeQuery(query);
		while(rs.next()){
		returnvalue.put("BOOK_ID",rs.getString(1));
		returnvalue.put("BOOK_DESC",rs.getString(2));
		returnvalue.put("BOOK_PRICE",new Float(rs.getFloat(3)));
		returnvalue.put("BOOK_QTY",new Integer(rs.getInt(4)));
		returnvalue.put("TECHNOLOGY",rs.getString(5));
		returnvalue.put("BOOK_IMAGEURL",rs.getString(6));
		returnvalue.put("BOOK_DELIVERYTIME",new Integer(rs.getInt(7)));
		returnvalue.put("TITLE",rs.getString(8));
		returnvalue.put("AUTHOR",rs.getString(9));
		}
		}catch(Exception e){
			System.out.println("Problem in retriveing data in getBookDetails():"+e);
		}
		return returnvalue;
	}

	public Vector getCateg()
	{
		Vector v = new Vector();
		try
		{
			ResultSet rs=st.executeQuery("SELECT UNIQUE(technology) FROM book");
			while(rs.next())
			{
				v.addElement(rs.getString(1)+"");
			}
		}
		catch(SQLException sq)
		{
			sq.printStackTrace();
		}
		return v;
	}

	public Hashtable getCatBooksDetails(String cat)
	{
		Hashtable returnvalue=new Hashtable();
		String query="SELECT book_IMAGEURL,TITLE,book_PRICE,book_id FROM book  WHERE technology='"+cat+"' ORDER BY book_ID";
		System.out.println(query);
		try
		{
			ResultSet rs=st.executeQuery(query);
		    while(rs.next())
			{
				String values = rs.getString(1)+"**"+rs.getString(2)+"**"+rs.getFloat(3);
				String itemId = rs.getString(4);
				returnvalue.put(itemId,values);
			}
		}
		catch(Exception e)
		{
			System.out.println("Problem in retriveing data in getCatBooksDetails():"+e);
		}
		return returnvalue;
	}
public String creditCheck(String cardno,float amount)
	{
		String returnValue="";
		String query="SELECT CARDID  ,BAL FROM creditcard ";
		System.out.println(query);
		try
		{
			ResultSet rs=st.executeQuery(query);
			if(!rs.next())
				returnValue="NO CREDITCARD WITH THIS ID ";
			else
			{
				if(cardno.equalsIgnoreCase((String)rs.getString(1)) && amount<=rs.getFloat(2))
				{
					returnValue="true";
				}
				else
					returnValue="false";
			}
		}
		catch(Exception e)
		{
			System.out.println("Problem in retriveing from  DataBase creditCheck():"+e);
		}
		return returnValue;
	}

	public String creditCheck(String cardno,float amount,String cust)
	{
		String returnValue="";
		String query="SELECT CUST_ID,CREDIT_BALANCE FROM DCREDITCARD WHERE CREDIT_ID='"+cardno+"'";
		System.out.println(query);
		try
		{
			ResultSet rs=st.executeQuery(query);
			if(!rs.next())
				returnValue="NO CREDITCARD WITH THIS ID ";
			else
			{
				if(cust.equalsIgnoreCase((String)rs.getString(1)) && amount<=rs.getFloat(2))
				{
					returnValue="true";
				}
				else
					returnValue="false";
			}
		}
		catch(Exception e)
		{
			System.out.println("Problem in retriveing from  DataBase creditCheck():"+e);
		}
		return returnValue;
	}
	
	public Hashtable getexamdates(String batchid)
{
System.out.println("in queryhhhhhhhhhhhh");
System.out.println("Batch ID:"+batchid);
Hashtable returnval=new Hashtable();
try	{
String query ="select to_char(EXMDATE,'dd-mon-yy') from ONLINE_TESTLOGIN where BATCHID="+batchid;
System.out.println("ppppppppp"+query);
ResultSet rs=st.executeQuery(query);
        while(rs.next())
             {
                 returnval.put(rs.getString(1),batchid);
                //System.out.println(rs.getString(1));
             } 
        }
		catch(Exception e)  
			{
			    System.out.println(e);
            }
return returnval;
}

public Hashtable getexamresults(String batchid,String date)
{
	System.out.println("in queryhhhhhhhhhhhh");
	Hashtable returnval=new Hashtable();
	try	{
	String query ="select cust_id,STATUS,RESULT from ONLINE_TESTLOGIN where batchid="+batchid+" and EXMDATE='"+date+"' and status='TAKEN'";
	System.out.println("ppppppppp"+query);
	ResultSet rs=st.executeQuery(query);
	while(rs.next())
	{
		String data="";
				data+=rs.getString("STATUS")+"?&";
				data+=rs.getString("RESULT");
				returnval.put(rs.getString(1),data);
	} 
	}catch(Exception e)  {System.out.println(e);
	}
	return returnval;
	}
  
	public Hashtable getTransactions(String cust_id)
	{
		Hashtable returnvalue=new Hashtable();
		String query="select b.book_id,b.trans_id,b.pay_mode,to_char(b.item_deliverydate,'dd-mm-yyyy'),b.item_qty,b.item_total from transactiondetails b where b.trans_id in(select trans_id from transaction where cust_id='"+cust_id+"') order by trans_id";
		System.out.println(query);
		try{
		ResultSet rs=st.executeQuery(query);
		int key=1;
		while(rs.next())
			{
				int t_id=rs.getInt(2);
				String data=rs.getString(1)+"?&";
				data+=rs.getString(3)+"?&";
				data+=rs.getInt(5)+"?&";
				data+=rs.getString(4)+"?&";
				data+=getTransDate(t_id)+"?&";
				data+=rs.getString(6);
				returnvalue.put(new Integer(key),data);
				key=key+1;
			}
		}catch(Exception e){
			System.out.println("Problem in retriveing data in Gettransations():"+e);
		}
		return returnvalue;
	}

	public String getTransDate(int id)
	{
	String returnvalue="";
	try{
				Statement st1=con.createStatement();
				ResultSet rs1=st1.executeQuery("select to_char(trans_date,'dd-mm-yyyy') from transaction where trans_id="+id);
				rs1.next();
				returnvalue=rs1.getString(1);
		}catch(Exception e){
			System.out.println("Problem in retriveing data in getTransDate():"+e);
		}
		return returnvalue;
	}


}
