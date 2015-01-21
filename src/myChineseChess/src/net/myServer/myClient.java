package net.myServer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import myChessBoard.MainChessBoard.MainChessBoard;

public class myClient{
	
	public Socket mySocket = null;

    public InputStream in = null;
    public OutputStream out =null;
    
	public myClient()
	{
		super();
	
		try {
			//this.mySocket=new Socket("192.168.2.147",ChessServer.RECEIVE_PORT);
			 if(MainChessBoard.IPAddress==null||MainChessBoard.IPAddress.equals(""))
			 {
			this.mySocket=new Socket(InetAddress.getLocalHost( ),ChessServer.RECEIVE_PORT);
			 }
			 else
			 {
				 this.mySocket=new Socket(MainChessBoard.IPAddress,ChessServer.RECEIVE_PORT);
			 }
			this.in= this.mySocket.getInputStream();
			this.out=this.mySocket.getOutputStream();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		while(true)
//		{
//			
//			    byte buff[ ]=new byte[20];//��������
//			    try {
//			    	
//			    	   String aa = "�õ�";
//					   
//					   byte buff2[] = aa.getBytes();
//					   this.out.write(buff2);
//					    
//					    
//					    
//					in.read(buff);
//					
//					String str=new String(buff);//���ܿͻ��˷��͵����ݰ�
//				    this.myText.append("������˵:"+str+"\n");
//				    
//				  
//				    
//				    
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			    
//			   
//			
//		}
	}
		
	public void sendTo(String num)
	{
		String aa =num;
		   
	    byte buff2[] = aa.getBytes();
	    
	    
	   try {
		this.out.write(buff2);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	
	public String readCommand()
	{
		byte buff[]=new byte[512];//��������
		try {
			int num=in.read(buff);
			String str=new String(buff,0,num);//���ܿͻ��˷��͵����ݰ�
			//return Integer.parseInt(str);
			System.out.println(str);
			return str;
		    
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "N";
	    
	}
	
	//�õ���ǰ����
	public static int getNum(String command)
	{
		String sub = command.substring(1);
		return Integer.parseInt(sub);
		
	}
	
	//�õ�����
	public static char  getChar(String command)
	{
		char A = command.charAt(0);
		return A;
	}
	
	
	
	//�����ƶ����õ�������
	public static  int getCx(String command)
	{
		 return (8-(int)(command.charAt(1)-48));
		
	}
	//�����ƶ����õ�������
	public static int getCy(String command )
	{
		return (9-(int)(command.charAt(2)-48));
	}
	
	//�õ����ƣ�������������
	public static String getName(String command)
	{
		return command.substring(1,command.length());
	}
	//�õ�����
	public static int getStyle (String command)
	{
		return (Integer.parseInt(command.substring(command.indexOf("T")+1,command.indexOf("Z"))));
	}
	
	//�õ�����ɫ
	public static int getBackColor(String command)
	{
		return (Integer.parseInt(command.substring(command.indexOf("B")+1,command.length())));
	}
	
	//�õ���ɫ
	public static int getColor(String command)
	{
		return (Integer.parseInt(command.substring(command.indexOf("C")+1,command.indexOf("B"))));
		
	}
	
	//�õ���У 
	public static int getSize(String command)
	{
		return (Integer.parseInt(command.substring(command.indexOf("Z")+1,command.indexOf("C"))));
	}
	

}
