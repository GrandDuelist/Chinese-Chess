package myChess.ChessPiece;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JPanel;

import javax.swing.JLabel;

import myChess.Enum.*;
import myChessBoard.MainChessBoard.CPoint;
import myChessBoard.MainChessBoard.ChessBoardPanel;
import myChessBoard.MainChessBoard.MainChessBoard;
import myChessBoard.MainChessBoard.Player;



@SuppressWarnings("serial")
public abstract class ChessPiece extends JLabel implements  MouseListener {

	 //�����õ�ͼƬ
     public Icon img =null;
     public CPoint CurrentCPoint=null;  //��¼���ӵĵ�ǰ����
     public CPoint LastNode =null;  //��һ��������
     public boolean IsSelect =false;   //��¼�����Ƿ�ѡ��
     public boolean isWalking =false;   //��¼�����Ƿ���ִ���ߵĶ���
     public ChessType ChessMan = null;  //��¼���ӵ����� /���� ��������
     public ChessColor chessColor =null; //��¼���ӵ���ɫ
     public CPoint InitCPoint =null;
     public boolean CanReachable =false;
     public boolean isDead = false;
     public boolean isCrossRiver =false;
     public int Num;
     
     public ChessBoardPanel myBoardPanel = null;
     
     
     
   //  public JLabel chessLabel =null;
     
     //���ߺ������Ӿ������Ӷ����߷�
    public  abstract void walkTo(CPoint target);
    //�ж����ߺ���
    public abstract boolean canWalk(CPoint target);
    
    
    public abstract void changeColor();
    //public abstract void crossRiver();
    
    
    
    
    
     public ChessPiece(Icon img,int num)
     {
    	 super(img);
    	 //this.chessLabel = new JLabel(img);
    	 //this.add(chessLabel);
    	 this.setOpaque(false);
    	 this.img=img;
    	 this.addMouseListener(this);
    	 this.Num=num;
    	 
     }
      
     //���ñ�ѡ��
     public void setIsSelect(boolean isSelect)
     {
    	 this.IsSelect=isSelect;
    	 
     }
      //�Ƿ�ѡ��
     public boolean isSelect()
     {
    	 return IsSelect;
     }
     //������ʼ������
     public void setInitBounds(CPoint Cpoint)
     {
    	 this.CurrentCPoint = Cpoint;
    	 this.InitCPoint=Cpoint;
    	 ChessBoardPanel.nodes[Cpoint.Cx][Cpoint.Cy].isOccupied=true;
    	 ChessBoardPanel.nodes[Cpoint.Cx][Cpoint.Cy].OccupeidType=this.ChessMan;
    	 ChessBoardPanel.nodes[Cpoint.Cx][Cpoint.Cy].occupiedColor=this.chessColor;
    	 ChessBoardPanel.nodes[Cpoint.Cx][Cpoint.Cy].OccupiedChess=this;
    	 Point temp =CPoint.changeToPoint(Cpoint);
    	 this.setBounds(temp.x,temp.y,(int)CPoint.CPWidth,(int)CPoint.CPHeight);
     }
     
     //��������ߺ���
     public void walk (CPoint target)
     {
    	 if(target.Cx>=0&&target.Cy<ChessBoardPanel.HEIGHT&&target.Cy>=0&&target.Cx<ChessBoardPanel.WIDTH)
    	 {
         this.LastNode=this.CurrentCPoint;
         ChessBoardPanel.nodes[this.LastNode.Cx][this.LastNode.Cy].isOccupied=false;
         ChessBoardPanel.nodes[this.LastNode.Cx][this.LastNode.Cy].OccupeidType=null;
         
         
         //������һ�������Ӻ����ӵ�
        if(this.myBoardPanel.myColor==this.chessColor) 
        {
        MainChessBoard.lastMyChess=this.Num;
        MainChessBoard.lastMyCPoint=this.CurrentCPoint;
        }
        if(this.myBoardPanel.ArmyColor==this.chessColor)
        {
        MainChessBoard.lastArmyChess=this.Num;
        MainChessBoard.lastArmyCPoint= this.CurrentCPoint;
        }
        
        this.myBoardPanel.step++;
    	 this.setLocation(target);
    	 
    	 
    	 this.CurrentCPoint=target;
    	 this.isWalking=false;
    	 
    	 ChessBoardPanel.nodes[target.Cx][target.Cy].isOccupied=true;
         ChessBoardPanel.nodes[target.Cx][target.Cy].OccupeidType=this.ChessMan;
         ChessBoardPanel.nodes[target.Cx][target.Cy].occupiedColor=this.chessColor;
         ChessBoardPanel.nodes[target.Cx][target.Cy].OccupiedChess=this;
         
         this.IsSelect=false;
         
        
         
        
         
         //�������ӷ�
         this.changeCharactor();
    	 }
     }
     public void walk2 (CPoint target)
     {
    	 if(target.Cx>=0&&target.Cy<ChessBoardPanel.HEIGHT&&target.Cy>=0&&target.Cx<ChessBoardPanel.WIDTH)
    	 {
         this.LastNode=this.CurrentCPoint;
         ChessBoardPanel.nodes[this.LastNode.Cx][this.LastNode.Cy].isOccupied=false;
         ChessBoardPanel.nodes[this.LastNode.Cx][this.LastNode.Cy].OccupeidType=null;
         
         
        
        this.myBoardPanel.step++;
    	 this.setLocation(target);
    	 
    	 
    	 this.CurrentCPoint=target;
    	 this.isWalking=false;
    	 
    	 ChessBoardPanel.nodes[target.Cx][target.Cy].isOccupied=true;
         ChessBoardPanel.nodes[target.Cx][target.Cy].OccupeidType=this.ChessMan;
         ChessBoardPanel.nodes[target.Cx][target.Cy].occupiedColor=this.chessColor;
         ChessBoardPanel.nodes[target.Cx][target.Cy].OccupiedChess=this;
         
         this.IsSelect=false;
    	 }
     }
     
     //�������ӵ�����
     public void setLocation(CPoint Cpoint)
     {
    	 Point temp =CPoint.changeToPoint(Cpoint);
    	 this.setLocation(temp);
    	 this.CurrentCPoint=Cpoint;
     }

 	//���¿�ʼ��ԭ������
 	public void restartGame()
 	{
 		this.CanReachable=false;
 		//isDead = false;
 		isWalking =false;
 		IsSelect =false;
 		this.isCrossRiver=false;
// 		CurrentCPoint.Cx=this.InitCPoint.Cx;
// 		CurrentCPoint.Cy=this.InitCPoint.Cy;
 		
// 		if(this.isDead)
// 		{
// 		
// 			this.myBoardPanel.add(this);
// 			//this.setLocation(InitCPoint);
// 			this.isDead=false;
// 			
// 		}
 		if(!this.isDead)
		this.walk2(InitCPoint);
 	}
     
     //���õ������
     public void selectAction()
     {
    	 
    	 //this.setIcon(null);
    	 this.setVisible(false);

     }
     //�����ٴε������
     public void notSelectAction()
     {
    	 
    	
    	     //this.setIcon(img);
    		 this.setVisible(true);
    	 
    
    	 
     }
     
     abstract public boolean canEat(CPoint armyPosition);
     abstract public void eat(CPoint target);

	@Override
	 public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
   if(!MainChessBoard.NetOpen&&!ChessBoardPanel.isGameOver)
   {
	   this.playWhenNoNetwork();
   }
   
   else if(MainChessBoard.StartGame&&!ChessBoardPanel.isGameOver){
	   this.playWhenNetwork();
	   
   }
		
	
		
	}

	//����ģʽ�Ķ�ս
	public void playWhenNoNetwork()
	{

		if(!this.isDead&&this.chessColor==ChessBoardPanel.PresentTurnColor)
		{	
//	    if(this.IsSelect)
//	    this.myBoardPanel.Sound.setStop();
			
		this.IsSelect=!this.IsSelect;
		//this.myBoardPanel.Sound= null;
//		if(this.IsSelect)
//		{
//		this.myBoardPanel.Sound = new Player("select.wav",2);
//		this.myBoardPanel.Sound.start();
//		}
		
		//��������ȡ��ѡ��
				for(int i=0;i<this.myBoardPanel.AllChessPiece.length;i++)
				{
					if(this.myBoardPanel.AllChessPiece[i]!=this)
					{
						this.myBoardPanel.AllChessPiece[i].IsSelect=false;
					}
//					//��ѡ���״̬��������
//					if(this.myBoardPanel.AllChessPiece[i]==this)
//					{
//						this.myBoardPanel.fightClient.sendTo(i);
//					}
				}
				
				
				for(int i=0;i<this.myBoardPanel.ArmyChessPiece.length;i++)
				{
					if(this.myBoardPanel.ArmyChessPiece[i]!=this)
					{
						this.myBoardPanel.ArmyChessPiece[i].IsSelect=false;
					}
				}
				
		
		}
		
		//��������
		else {
			
			 
			int PresentArmy   = this.getArmySelectChess();
			
			//���Է��Ƿ���ѡ��
			if(PresentArmy!=100&&ChessBoardPanel.PresentTurnColor==ChessBoardPanel.myColor)
			{
				if(this.myBoardPanel.AllChessPiece[PresentArmy].canEat(this.CurrentCPoint))
				{
					this.isDead=true;
					this.myBoardPanel.remove(this);
					this.myBoardPanel.repaint();
					this.myBoardPanel.AllChessPiece[PresentArmy].eat(this.CurrentCPoint);
					this.myBoardPanel.AllChessPiece[PresentArmy].IsSelect=false;
					
				}
				
			         
				
			}
			
			else if(PresentArmy!=100&&ChessBoardPanel.PresentTurnColor==ChessBoardPanel.ArmyColor)
			{
				if(this.myBoardPanel.ArmyChessPiece[PresentArmy].canEat(this.CurrentCPoint))
				{
					this.isDead=true;
					this.myBoardPanel.remove(this);
					this.myBoardPanel.repaint();
					this.myBoardPanel.ArmyChessPiece[PresentArmy].eat(this.CurrentCPoint);
					this.myBoardPanel.ArmyChessPiece[PresentArmy].IsSelect=false;
					
				}
			         
				
			}
			if(this.ChessMan==ChessType.BLACKshuai&&this.isDead)
			{
				if(ChessBoardPanel.myColor==chessColor.BLACK)
					ChessBoardPanel.lost();
				else
					ChessBoardPanel.win();
				
			}
			
			if(this.ChessMan==ChessType.REDshuai&&this.isDead)
			{
				
				
				if(ChessBoardPanel.myColor==chessColor.RED)
					ChessBoardPanel.lost();
				else
					ChessBoardPanel.win();
			}
			
			
			
		}
   }
    
	
	//����ģʽ�Ķ�ս
	public void playWhenNetwork()
	{
		if(ChessBoardPanel.PresentTurnColor==ChessBoardPanel.myColor)
		{
		if(this.chessColor==ChessBoardPanel.myColor)
		{
						
					this.IsSelect=!this.IsSelect;
					
				
					
				
				  
		
		//��������ȡ��ѡ��
		for(int i=0;i<this.myBoardPanel.AllChessPiece.length;i++)
		{
			if(this.myBoardPanel.AllChessPiece[i]!=this)
			{
				this.myBoardPanel.AllChessPiece[i].IsSelect=false;
				
			}
			
			if(this.myBoardPanel.AllChessPiece[i]==this)
			{
			
			    MainChessBoard.fightClient.sendTo("S"+i);
			}
		}
		}
		//�з����ӣ���
		else {
			
			 
			int PresentArmy   = this.getArmySelectChess();
			
			//���Է��Ƿ���ѡ��
			if(PresentArmy!=100&&ChessBoardPanel.PresentTurnColor==ChessBoardPanel.myColor)
			{
				if(this.myBoardPanel.AllChessPiece[PresentArmy].canEat(this.CurrentCPoint))
				{
					this.isDead=true; 
					this.myBoardPanel.remove(this);
					MainChessBoard.myarmyEteChess=this.Num;
					this.myBoardPanel.repaint();
					this.myBoardPanel.AllChessPiece[PresentArmy].eat(this.CurrentCPoint);
					MainChessBoard.armyChessEat=PresentArmy;
					this.myBoardPanel.AllChessPiece[PresentArmy].IsSelect=false;
					
					//�������磬���ӱ�����
					MainChessBoard.fightClient.sendTo("E"+this.Num);
					
					
				}
				
			         
				
			}
			
			
			if(this.ChessMan==ChessType.BLACKshuai&&this.isDead)
			{
				if(ChessBoardPanel.myColor==ChessColor.BLACK)
					ChessBoardPanel.lost();
				else
					ChessBoardPanel.win();
				
			}
			
			if(this.ChessMan==ChessType.REDshuai&&this.isDead)
			{
				
				
				if(ChessBoardPanel.myColor==ChessColor.RED)
					ChessBoardPanel.lost();
				else
					ChessBoardPanel.win();
			}
			
		}
			
		}	
	}
	
	//�任���ӷ� 
	public void changeCharactor()
	{
		if(ChessBoardPanel.PresentTurnColor==ChessBoardPanel.myColor)
			ChessBoardPanel.PresentTurnColor=ChessBoardPanel.ArmyColor;
		else 
			ChessBoardPanel.PresentTurnColor=ChessBoardPanel.myColor;
	}
	

	
	//�õ��ط��Ѿ�ѡ������ӣ��ж��ܲ��ܱ���
	public int getArmySelectChess()
	{
		if(ChessBoardPanel.PresentTurnColor==ChessBoardPanel.myColor)
		{
		        for(int i=0;i<this.myBoardPanel.AllChessPiece.length;i++)
		        {
		        	if(this.myBoardPanel.AllChessPiece[i].IsSelect)
		        		return i;
		        }
		        	
		}
		
		else
		{
			
			 for(int i=0;i<this.myBoardPanel.ArmyChessPiece.length;i++)
		        {
		        	if(this.myBoardPanel.ArmyChessPiece[i].IsSelect)
		        		return i;
		        }
			
		}
		
		return 100;
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