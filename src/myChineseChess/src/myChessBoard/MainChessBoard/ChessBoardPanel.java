package myChessBoard.MainChessBoard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import net.myServer.myClient;

import myChess.ChessPiece.*;
import myChess.Enum.ChessColor;
import myChess.Enum.ChessType;



public class ChessBoardPanel extends JPanel implements Runnable
, MouseListener{
	
	public static final int ChessNum= 16;
	public static final int WIDTH = 9;
	public static final int HEIGHT= 10;
	
	
	public static ChessColor myColor= ChessColor.RED;
	public static ChessColor ArmyColor = ChessColor.BLACK;
	public static ChessColor PresentTurnColor = ChessColor.RED;
	
	public ChessPiece AllChessPiece[] =new ChessPiece [ChessNum];
	public ChessPiece ArmyChessPiece[] = new ChessPiece[ChessNum];
	
	final Image img=Toolkit.getDefaultToolkit().createImage("chessboard.png");
	public static CPoint nodes[][] = new CPoint[WIDTH][HEIGHT];
	
	public Player Sound = new Player("select.wav",3);
	public Player SoundEat=new Player("capture.wav",0);
	public Player SoundMov=new Player("go.wav",0);
	
	
	public MainChessBoard myChessBoard = null;
	public int step = 0;
	
	public static boolean isGameOver =false;
	/**
	 * Create the panel.
	 */
	public ChessBoardPanel() {
		
		super();
		this.setCPoint();
		
		
		
		
		this.setChessPiece(myColor);
		this.setArmyPiece(ArmyColor);
		
		
		this.setBounds(0, 0, 650, 600);
		this.addMouseListener(this);
		this.Sound.start();
		this.SoundEat.start();
		this.SoundMov.start();
         this.setLayout(null);
         Border b = BorderFactory.createLineBorder(Color.red); //边框
		this.setBorder(b); //为这个显示区加上边框
	
		
	}
	
	//初始化坐标
	public void setCPoint()
	{
		for(int i=0;i<WIDTH;i++)
		{
			for(int j=0;j<HEIGHT;j++)
			{
				nodes[i][j]=new CPoint(i,j);
			}
		}
		
	}
	
	
	//加入我方棋子
	public void setChessPiece(ChessColor myChess)
	{
		  this.AllChessPiece[0]=new ChessJu(myChess,nodes[0][9],0);
		  this.AllChessPiece[1]=new ChessJu(myChess,nodes[8][9],1);
		  this.AllChessPiece[2]=new ChessMa(myChess,nodes[1][9],2);
		  this.AllChessPiece[3]=new ChessMa(myChess,nodes[7][9],3);
		  this.AllChessPiece[4]=new ChessXiang(myChess,nodes[6][9],4);
		  this.AllChessPiece[5]=new ChessXiang(myChess,nodes[2][9],5);
		  this.AllChessPiece[6]=new ChessShi(myChess,nodes[5][9],6);
		  this.AllChessPiece[7]=new ChessShi(myChess,nodes[3][9],7);
		  this.AllChessPiece[8]=new ChessJiang(myChess,nodes[4][9],8);
		  this.AllChessPiece[9]=new ChessPao(myChess,nodes[1][7],9);
		  this.AllChessPiece[10]=new ChessPao(myChess,nodes[7][7],10);
		  this.AllChessPiece[11]=new ChessBing(myChess,nodes[0][6],11);
		  this.AllChessPiece[12]=new ChessBing(myChess,nodes[2][6],12);
		  this.AllChessPiece[13]=new ChessBing(myChess,nodes[4][6],13);
		  this.AllChessPiece[14]=new ChessBing(myChess,nodes[6][6],14);
		  this.AllChessPiece[15]=new ChessBing(myChess,nodes[8][6],15);
		  
		for(int i=0;i<this.AllChessPiece.length;i++)
		{
			this.AllChessPiece[i].myBoardPanel=this;
			this.add(this.AllChessPiece[i]);
			
		}	
	}
	//加入敌方棋子
	public void setArmyPiece(ChessColor armyChess)
	{
		  this.ArmyChessPiece[0]=new ChessJu(armyChess,nodes[8][0],0);
		  this.ArmyChessPiece[1]=new ChessJu(armyChess,nodes[0][0],1);
		  this.ArmyChessPiece[2]=new ChessMa(armyChess,nodes[7][0],2);
		  this.ArmyChessPiece[3]=new ChessMa(armyChess,nodes[1][0],3);
		  this.ArmyChessPiece[4]=new ChessXiang(armyChess,nodes[2][0],4);
		  this.ArmyChessPiece[5]=new ChessXiang(armyChess,nodes[6][0],5);
		  this.ArmyChessPiece[6]=new ChessShi(armyChess,nodes[3][0],6);
		  this.ArmyChessPiece[7]=new ChessShi(armyChess,nodes[5][0],7);
		  this.ArmyChessPiece[8]=new ChessJiang(armyChess,nodes[4][0],8);
		  this.ArmyChessPiece[9]=new ChessPao(armyChess,nodes[7][2],9);
		  this.ArmyChessPiece[10]=new ChessPao(armyChess,nodes[1][2],10);
		  
		  this.ArmyChessPiece[11]=new ChessBing(armyChess,nodes[8][3],11);
		  this.ArmyChessPiece[12]=new ChessBing(armyChess,nodes[6][3],12);
		  this.ArmyChessPiece[13]=new ChessBing(armyChess,nodes[4][3],13);
		  this.ArmyChessPiece[14]=new ChessBing(armyChess,nodes[2][3],14);
		  this.ArmyChessPiece[15]=new ChessBing(armyChess,nodes[0][3],15);
		  
		  
		  
		for(int i=0;i<this.AllChessPiece.length;i++)
		{
			this.ArmyChessPiece[i].myBoardPanel=this;
			this.add(this.ArmyChessPiece[i]);
			
		}	
		
	}
	
	
	
	 protected  void paintChildren(Graphics g) {

         g.drawImage(img,0,0,this);

         super.paintChildren(g);

     }



	 //事件监听的线程
	
	public void run() {
		// TODO Auto-generated method stub
		//this.AllChessPiece[0].selectAction();
		//this.chessSelectAction(0);
		while(true)
		{
			
			
			this.listenToNet();
			
			
		
			//this.listenToSelect();
			
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	};
	
	
	//棋子选择的动画
	public void MySelectAction(int i)
	{
	    //this.AllChessPiece[i].selectAction();
		this.Sound.playSound();
		this.AllChessPiece[i].selectAction();
	    try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    this.AllChessPiece[i].notSelectAction();
	    
	    try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	//对方棋子的动画
	
	//棋子选择的动画
		public void ArmySelectAction(int i)
		{
		    //this.AllChessPiece[i].selectAction();
			this.Sound.playSound();
			this.ArmyChessPiece[i].selectAction();
		    try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    this.ArmyChessPiece[i].notSelectAction();
		    
		    try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		    
		}
	
	//对棋子选择进行监听
	public void listenToSelect()
	{
		for(int i=0;i<this.AllChessPiece.length;i++)
		{
			if(this.AllChessPiece[i].IsSelect)
			{
				this.MySelectAction(i);
				
			}
		}
		
		for(int i=0;i<this.ArmyChessPiece.length;i++)
		{
			if(this.ArmyChessPiece[i].IsSelect)
			{
				this.ArmySelectAction(i);
			}
		}
	}
	//监听网络选择
	public void listenToNet()
	{
		String temp ="";
		////if(this.myColor!=this.PresentTurnColor)
		//{
		if(MainChessBoard.NetOpen)
		{
			temp=MainChessBoard.fightClient.readCommand();
			if(!temp.equals(" "))
			{
				this.decodeInstruction(temp);
			}
			
		}
		//}
	}
	
	//解析从网络发来的命令
	public void decodeInstruction(String temp)
	{
		char In = myClient.getChar(temp);
		int  tempCx=0;
		int tempCy =0;
		int tempChess =0;
		
		//如果是选择命令
		if(In=='S')
		{
			tempChess = myClient.getNum(temp);
			//this.ArmyChessPiece[tempChess].IsSelect=!this.ArmyChessPiece[tempChess].IsSelect;
			this.ArmyChessPiece[tempChess].IsSelect=!this.ArmyChessPiece[tempChess].IsSelect;
			System.out.println(this.ArmyChessPiece[tempChess].IsSelect);
			for(int i=0; i<this.ArmyChessPiece.length;i++)
			{
				if(i!=tempChess)
				this.ArmyChessPiece[i].IsSelect=false;
			}
				
		}
		else{
			//如果是吃子命令
			if(In=='E')
			{
				tempChess=myClient.getNum(temp);
				
				this.remove(this.AllChessPiece[tempChess]);
				this.AllChessPiece[tempChess].isDead=true;
				MainChessBoard.myEteChess=tempChess;
				this.nodes[this.AllChessPiece[tempChess].CurrentCPoint.Cx][this.AllChessPiece[tempChess].CurrentCPoint.Cy].isOccupied=false;
				this.repaint();
				
				for(int i=0;i<this.ArmyChessPiece.length;i++)
				{
					
					if(this.ArmyChessPiece[i].IsSelect)
					{
						this.ArmyChessPiece[i].eat(this.AllChessPiece[tempChess].CurrentCPoint);
						
						MainChessBoard.myChessEat=i;
					}
				}
				
				
				if(this.AllChessPiece[tempChess].ChessMan==ChessType.BLACKshuai&&this.AllChessPiece[tempChess].isDead)
				{
					if(ChessBoardPanel.myColor==ChessColor.BLACK)
						ChessBoardPanel.lost();
					else
						ChessBoardPanel.win();
					
				}
				
				if(this.AllChessPiece[tempChess].ChessMan==ChessType.REDshuai&&this.AllChessPiece[tempChess].isDead)
				{
					
					
					if(ChessBoardPanel.myColor==ChessColor.RED)
						ChessBoardPanel.lost();
					else
						ChessBoardPanel.win();
				}
				
			}
			
			else{
				//如果是移动命令
				if(In=='M')
				{
					tempCx=myClient.getCx(temp);
					tempCy=myClient.getCy(temp);
					for(int i=0;i<this.ArmyChessPiece.length;i++)
					{
						if(this.ArmyChessPiece[i].IsSelect)
						{
							this.ArmyChessPiece[i].walkTo(new CPoint(tempCx,tempCy));
						}
					}
					
				}
				else if(In=='F')  //功能命令
				{
					int num = myClient.getNum(temp);
					
					if(num==1&&!MainChessBoard.StartGame)
						{
						if(!MainChessBoard.isReady)
						{
							MainChessBoard.armyReady=true;
						JOptionPane.showMessageDialog(null, "" +
								"对方已准备游戏，请尽快点击开始", "开始游戏", JOptionPane.INFORMATION_MESSAGE);
						}
						else{
							MainChessBoard.StartGame = true;
							JOptionPane.showMessageDialog(null, "" +
									"双方已准备，游戏开始", "开始游戏", JOptionPane.INFORMATION_MESSAGE);
							this.myChessBoard.BoardNum++;
							if(this.myChessBoard.BoardNum>1)
							this.restartGame();
						}
						}
					
					if(num==2&&!isGameOver&& MainChessBoard.StartGame)
					{
						JOptionPane.showMessageDialog(null, "" +
								
								"你把敌人吓破胆了！", "对方投降", JOptionPane.INFORMATION_MESSAGE);
						 isGameOver= true;
						   MainChessBoard.isReady=false;
							MainChessBoard.StartGame=false;
							MainChessBoard.armyReady=false;
							MainChessBoard.myChat.myHead.myCredit++;
							MainChessBoard.myChat.myHead.setMessage();
							MainChessBoard.myChat.armyHead.myCredit--;
							MainChessBoard.myChat.armyHead.setMessage();
						
					}
					
					if(num==3&&!isGameOver&& MainChessBoard.StartGame)
					{
						int   option=   JOptionPane.showConfirmDialog( 
				                  null, "该死的敌人想悔棋，是否同意？", "悔棋 ",JOptionPane.YES_NO_CANCEL_OPTION); 
				      if(option==JOptionPane.YES_OPTION) 
				      {
				    	 
				    	  MainChessBoard.fightClient.sendTo("J3");
				    	  if(this.PresentTurnColor==this.ArmyColor) //该敌人下推两步
							{
				    		  if(MainChessBoard.lastMyChess!=-1)
								{
				    			  
				    			    
									this.AllChessPiece[MainChessBoard.lastMyChess].walk2(MainChessBoard.lastMyCPoint);
									
									
									if(MainChessBoard.lastMyChess==MainChessBoard.armyChessEat)
										{
										
										this.add(this.ArmyChessPiece[MainChessBoard.myarmyEteChess]);
										this.ArmyChessPiece[MainChessBoard.myarmyEteChess].walk2(this.ArmyChessPiece[MainChessBoard.myarmyEteChess].CurrentCPoint);
										this.ArmyChessPiece[MainChessBoard.myarmyEteChess].isDead=false;
										}
								}
					    		  if(MainChessBoard.lastArmyChess!=-1)
					    		  {
									this.ArmyChessPiece[MainChessBoard.lastArmyChess].walk2(MainChessBoard.lastArmyCPoint);
									if(MainChessBoard.lastArmyChess==MainChessBoard.myChessEat)
									{
									
									this.add(this.AllChessPiece[MainChessBoard.myEteChess]);
									this.AllChessPiece[MainChessBoard.myEteChess].walk2(this.AllChessPiece[MainChessBoard.myEteChess].CurrentCPoint);
									this.AllChessPiece[MainChessBoard.myEteChess].isDead=false;
									}
								
					
							}
					
							}
							else {
								if(this.PresentTurnColor==this.myColor) //该我下 推一步
								{
									if(MainChessBoard.lastArmyChess!=-1)
									{
										this.ArmyChessPiece[MainChessBoard.lastArmyChess].walk2(MainChessBoard.lastArmyCPoint);
										
										if(MainChessBoard.lastArmyChess==MainChessBoard.myChessEat)
											{
											
											this.add(this.AllChessPiece[MainChessBoard.myEteChess]);
											this.AllChessPiece[MainChessBoard.myEteChess].walk2(this.AllChessPiece[MainChessBoard.myEteChess].CurrentCPoint);
											this.AllChessPiece[MainChessBoard.myEteChess].isDead=false;
											}
									}
									this.PresentTurnColor=this.ArmyColor;
						
								}
							}
				    	  
				    	  
				    	  
				    	  
				      }
				      if(option==JOptionPane.NO_OPTION)
				      {
				    	  MainChessBoard.fightClient.sendTo("J4");
				      }
						
					}
					
					if(num==4&&!isGameOver&& MainChessBoard.StartGame)
					{
						int   option=   JOptionPane.showConfirmDialog( 
				                  null, "对方请求求和，是否同意？", "求和 ",JOptionPane.YES_NO_CANCEL_OPTION); 
				      if(option==JOptionPane.YES_OPTION) 
				      {
				    	  ChessBoardPanel.peace();
				    	  MainChessBoard.fightClient.sendTo("J1");
				    
				      }
				      if(option==JOptionPane.NO_OPTION)
				      {
				    	  MainChessBoard.fightClient.sendTo("J2");
				      }
					}
					
				}
				if(In=='J'&& MainChessBoard.StartGame)
				{
					int num = myClient.getNum(temp);
					if(num==1)
					{
					isGameOver= true;
					
					MainChessBoard.isReady=false;
					MainChessBoard.StartGame=false;
					MainChessBoard.armyReady=false;
					JOptionPane.showMessageDialog(null, "" +
							
							"对方同意和棋！", "游戏结束", JOptionPane.INFORMATION_MESSAGE);
					}
					if(num==2)
					{
						
						JOptionPane.showMessageDialog(null, "" +
								
								"对方不同意和棋！", "请求失败", JOptionPane.INFORMATION_MESSAGE);
					}
					if(num==3)
					{
						JOptionPane.showMessageDialog(null, "" +
								
								"对方同意让你悔棋！", "好基佬", JOptionPane.INFORMATION_MESSAGE);
						if(this.PresentTurnColor==this.myColor) //该我下 推两步
						{
							if(MainChessBoard.lastMyChess!=-1)
							{
								this.AllChessPiece[MainChessBoard.lastMyChess].walk2(MainChessBoard.lastMyCPoint);
								
								if(MainChessBoard.lastMyChess==MainChessBoard.armyChessEat)
									{
									
									this.add(this.ArmyChessPiece[MainChessBoard.myarmyEteChess]);
									this.ArmyChessPiece[MainChessBoard.myarmyEteChess].walk2(this.ArmyChessPiece[MainChessBoard.myarmyEteChess].CurrentCPoint);
									this.ArmyChessPiece[MainChessBoard.myarmyEteChess].isDead=false;
									}
							}
				    		  if(MainChessBoard.lastArmyChess!=-1)
				    		  {this.ArmyChessPiece[MainChessBoard.lastArmyChess].walk2(MainChessBoard.lastArmyCPoint);
								if(MainChessBoard.lastArmyChess==MainChessBoard.myChessEat)
								
								{
								
								this.add(this.AllChessPiece[MainChessBoard.myEteChess]);
								this.AllChessPiece[MainChessBoard.myEteChess].walk2(this.AllChessPiece[MainChessBoard.myEteChess].CurrentCPoint);
								this.AllChessPiece[MainChessBoard.myEteChess].isDead=false;
								}
							
				
						}
						}
						else {
							if(this.PresentTurnColor==this.ArmyColor) //不该我下 推一步
							{
								if(MainChessBoard.lastMyChess!=-1)
								{
                                 this.AllChessPiece[MainChessBoard.lastMyChess].walk2(MainChessBoard.lastMyCPoint);
								
								if(MainChessBoard.lastMyChess==MainChessBoard.armyChessEat)
									{
									
									this.add(this.ArmyChessPiece[MainChessBoard.myarmyEteChess]);
									this.ArmyChessPiece[MainChessBoard.myarmyEteChess].walk2(this.ArmyChessPiece[MainChessBoard.myarmyEteChess].CurrentCPoint);
									this.ArmyChessPiece[MainChessBoard.myarmyEteChess].isDead=false;
									}
							}
								this.PresentTurnColor=this.myColor;
							}
					
							}
						}
						
					
					
					if(num==4)
					{
						JOptionPane.showMessageDialog(null, "" +
								
								"对方不同意悔棋！", "该死的", JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
				
			}
		}
			
			
		
		
	}
	
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(!MainChessBoard.NetOpen){
		
		for(int i=0;i<this.AllChessPiece.length;i++)
		{	
			if(this.AllChessPiece[i].IsSelect)
			{
				this.AllChessPiece[i].walkTo(CPoint.changeToCPoint(e.getPoint()));
				this.AllChessPiece[i].IsSelect=false;
				//CPoint.changeToNode(e.getPoint()).isOccupied=true;
			}
		
		
		}
		
		for(int i=0;i<this.ArmyChessPiece.length;i++)
		{	
			if(this.ArmyChessPiece[i].IsSelect)
			{
				this.ArmyChessPiece[i].walkTo(CPoint.changeToCPoint(e.getPoint()));
				this.ArmyChessPiece[i].IsSelect=false;
				//CPoint.changeToNode(e.getPoint()).isOccupied=true;
			}
		
		
		}
		}
		else if(MainChessBoard.StartGame&&!isGameOver)
		{
			if(ChessBoardPanel.myColor==ChessBoardPanel.PresentTurnColor)
			{
				
				for(int i=0;i<this.AllChessPiece.length;i++)
				{	
					if(this.AllChessPiece[i].IsSelect)
					{
						CPoint temp = CPoint.changeToCPoint(e.getPoint());
						this.AllChessPiece[i].walkTo(temp);
						this.AllChessPiece[i].IsSelect=false;
						
						MainChessBoard.fightClient.sendTo("M"+temp.Cx+temp.Cy);
						//CPoint.changeToNode(e.getPoint()).isOccupied=true;
					}
				
				
				}
			}
			
		}
	
	}
	
	//游戏结束
	public static void win()
	{
		isGameOver= true;
		MainChessBoard.isReady=false;
		MainChessBoard.StartGame=false;
		MainChessBoard.armyReady=false;
		JOptionPane.showMessageDialog(null, "" +
				"你赢了！ 再接再厉", "游戏结束", JOptionPane.INFORMATION_MESSAGE);
		
		MainChessBoard.myChat.myHead.myCredit++;
		MainChessBoard.myChat.myHead.setMessage();
		
		MainChessBoard.myChat.armyHead.myCredit--;
		MainChessBoard.myChat.armyHead.setMessage();
	}
	public static void lost()
	{
	   isGameOver= true;
	   MainChessBoard.isReady=false;
		MainChessBoard.StartGame=false;
		MainChessBoard.armyReady=false;
		JOptionPane.showMessageDialog(null, "" +
				"你输了！ 大侠重新来过", "游戏结束", JOptionPane.INFORMATION_MESSAGE);
		MainChessBoard.myChat.myHead.myCredit--;
		MainChessBoard.myChat.myHead.setMessage();
		MainChessBoard.myChat.armyHead.myCredit++;
		MainChessBoard.myChat.armyHead.setMessage();
		
	}
	public static void peace()
	{
	   isGameOver= true;
	   MainChessBoard.isReady=false;
		MainChessBoard.StartGame=false;
		MainChessBoard.armyReady=false;
		JOptionPane.showMessageDialog(null, "" +
				"本局和棋，再来一盘！", "游戏结束", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	//重新开始游戏
	public  void restartGame()
	{
		
		
		for(int i =0 ;i<WIDTH;i++)
		{
			for(int j =0; j<HEIGHT;j++)
			{
				nodes[i][j].isOccupied=false;
				nodes[i][j].OccupeidType=null;
				nodes[i][j].occupiedColor=null;
				nodes[i][j].OccupiedChess=null;
			}
		}
		
		
		
		
		MainChessBoard.lastArmyChess = -1;
//		public static  int lastArmyCx = -1;
//		public static  int lastArmyCy = -1;
//		public static  int lastMyCx = -1;
//		public static  int lastMyCy = -1;
		MainChessBoard.lastMyChess = -1;
		MainChessBoard.lastArmyCPoint = null;
		MainChessBoard.lastMyCPoint = null;
		MainChessBoard.myEteChess =-1;
		MainChessBoard.myChessEat =-1; 
		MainChessBoard.myarmyEteChess =-1;
		MainChessBoard.armyChessEat=-1;
		
		
		this.repaint();
		for(int i =0;i<this.AllChessPiece.length;i++)
		{
			this.AllChessPiece[i].restartGame();
			
		}
		
		for(int i =0;i<this.ArmyChessPiece.length;i++)
		{
			this.ArmyChessPiece[i].restartGame();
		}
		
		
		for(int i =0;i<this.AllChessPiece.length;i++)
		{
			
		
		if(this.AllChessPiece[i].isDead)
 		{
 		
			//this.AllChessPiece[i].setInitBounds(this.AllChessPiece[i].CurrentCPoint);
			this.add(this.AllChessPiece[i]);
 			//this.setLocation(InitCPoint);
			this.AllChessPiece[i].isDead=false;
 			
			this.AllChessPiece[i].setLocation(this.AllChessPiece[i].InitCPoint);
		//this.AllChessPiece[i].walk2(this.AllChessPiece[i].InitCPoint);
 		}
		}
		
		
		for(int i =0;i<this.ArmyChessPiece.length;i++)
		{
			
		
		if(this.ArmyChessPiece[i].isDead)
 		{
 		
			//this.ArmyChessPiece[i].setInitBounds(this.ArmyChessPiece[i].CurrentCPoint);
			this.add(this.ArmyChessPiece[i]);
 			//this.setLocation(InitCPoint);
			this.ArmyChessPiece[i].isDead=false;
 			
			this.ArmyChessPiece[i].setLocation(this.ArmyChessPiece[i].InitCPoint);
		//this.ArmyChessPiece[i].walk2(this.ArmyChessPiece[i].InitCPoint);
 		}
		}
		
		this.repaint();
		
		
		ChessBoardPanel.isGameOver=false;
		ChessBoardPanel.PresentTurnColor=ChessColor.RED;
	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
