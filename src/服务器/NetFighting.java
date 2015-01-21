package net.myServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


class NetFighting  implements Runnable
{
	
	public Socket MySocket=null;
	public Socket ArmySokcet =null;
	public InputStream  in= null;
	public OutputStream  out= null;
	public OutputStream  OutToArmy = null;
	
	public NetFighting(Socket mySocket, Socket armySocket)
	{
		this.MySocket=mySocket;
		this.ArmySokcet=armySocket;
		
		try {
			in = this.MySocket.getInputStream();
			this.out= this.MySocket.getOutputStream();
			this.OutToArmy=this.ArmySokcet.getOutputStream();
			
		} catch (IOException e) {
			
		}
		
		
	}
	public void run()
	{
		//String command =null;
		
		
		while (true)
		{
			try {
				byte buff[ ]=new byte[512];//缓冲数组
			    int num = this.in.read(buff);
			     // String str=new String(buff);//接受客户端发送的数据包
			      this.OutToArmy.write(buff,0,num);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		
	}
	
}