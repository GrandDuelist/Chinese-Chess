package net.myServer;

import java.awt.TextArea;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;




public class ChessServer extends JFrame{
	
	   public static final int RECEIVE_PORT =7000;
	   public int player =0;
	   public Socket firstGamePlayer     = null;
	   public Socket firstChatting     = null;
	   public Socket firstAdvanceChatting= null;
	   public Socket secondChatting     = null;
	   public Socket firstControl= null;
	   public Socket secondControl = null;
	   
	   public TextArea showMessage  = new TextArea();
       public Thread Thread1 =null;
       public Thread Thread2 =null;
       public NetFighting Player1=null;
       public NetFighting Player2=null;
       public NetFighting Chat1=null;
       public NetFighting Chat2=null;
       public NetFighting AdvanceChat1=null;
       public NetFighting AdvanceChat2=null;
       public NetFighting Control1=null;
       public NetFighting Control2=null;
       public Socket SecondGamePlayer  =null;
       public Socket secondAdvanceChatting= null;
       public  OutputStream  out  =null;
       
       
        public ChessServer(){
        	
        	super();
         this.setSize(200,200);
         this.add(this.showMessage);
         this.setVisible(true);
          this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    try {
	     	ServerSocket ChessServer = new ServerSocket(RECEIVE_PORT);
	     	firstGamePlayer  = ChessServer.accept();
	     	firstChatting = ChessServer.accept();
	     	this.firstAdvanceChatting=ChessServer.accept();
	     	this.firstControl=ChessServer.accept();
	     	this.tellPlay1();
	     	
	     	showMessage.append("玩家1加入！\n");
	     	showMessage.append("等待玩家2.......\n\n");
	     	SecondGamePlayer = ChessServer.accept();
	     	secondChatting  =  ChessServer.accept();
	     	this.secondAdvanceChatting=ChessServer.accept();
	     	this.secondControl=ChessServer.accept();
	     	this.tellPlayer2ChangeColor();
	     	showMessage.append("玩家2加入\n");
	     	
	     	
	     
	     	
	     	
	     	
	     	this.Player1=new NetFighting(this.firstGamePlayer,this.SecondGamePlayer);
	        Thread1 = new Thread( this.Player1);
	        Thread1.start();
	        this.Player2= new NetFighting(this.SecondGamePlayer,this.firstGamePlayer);
	        Thread2= new Thread( this.Player2);
	        Thread2.start();
	        this.Chat1 = new NetFighting(this.firstChatting,this.secondChatting);
	        Thread Thread3 = new Thread(this.Chat1);
	        Thread3.start();
	        this.Chat2= new  NetFighting(this.secondChatting,this.firstChatting);;
	        Thread Thread4 = new Thread (this.Chat2);
	        Thread4.start();
	        this.AdvanceChat1= new NetFighting(this.firstAdvanceChatting,this.secondAdvanceChatting);
	        Thread Thread5 = new Thread(this.AdvanceChat1);
	        Thread5.start();
	        this.AdvanceChat2= new  NetFighting(this.secondAdvanceChatting,this.firstAdvanceChatting);;
	        Thread Thread6 = new Thread (this.AdvanceChat2);
	        Thread6.start();
	        
	        this.Control1= new NetFighting(this.firstControl,this.secondControl);
	        Thread Thread7= new Thread(this.Control1);
	        Thread7.start();
	        this.Control2= new  NetFighting(this.secondControl,this.firstControl);;
	        Thread Thread8= new Thread (this.Control2);
	        Thread8.start();

	        
		
	       } catch (IOException e) {
	     	// TODO Auto-generated catch block
	      	showMessage.append("服务器初始化失败\n");
	}
	  
	    
        }
        
        public void tellPlay1()
        {
        	try {
				this.out = this.firstGamePlayer.getOutputStream();
				 String str =""+(++this.player);
				 
				 out.write(str.getBytes());
				 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        
        public void tellPlayer2ChangeColor()
        {
        	
        		try {
					this.out = this.SecondGamePlayer.getOutputStream();
					 String str =""+(++this.player);
					 
					 out.write(str.getBytes());
					 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        	
        	
        }
        
}

