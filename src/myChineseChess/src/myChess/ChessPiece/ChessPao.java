package myChess.ChessPiece;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import myChess.Enum.ChessColor;
import myChess.Enum.ChessType;
import myChessBoard.MainChessBoard.CPoint;
import myChessBoard.MainChessBoard.ChessBoardPanel;
import myChessBoard.MainChessBoard.Player;

public class ChessPao extends ChessPiece{

	
	  public  static Icon blackpao=new ImageIcon("blackpao.png");
	  public  static Icon redpao   = new ImageIcon ("redpao.png");
	
	public void walkTo(CPoint target) {
	
		if(canWalk(target))
		{
			this.CanReachable=true;
			this.walk(target);
			 this.myBoardPanel.SoundMov.playSound();
			
		}
		else
		{
			this.CanReachable=false;
		}
		
	}
	//炮的吃子特殊，分开写
	public void eat(CPoint target)
	{
		if(canEat(target))
		{
			this.CanReachable=true;
			this.walk(target);
		}
		
		
		 this.myBoardPanel.SoundEat.playSound();
		
	}
	//判断能够吃子
	public boolean canEat(CPoint armyPosition)
	{
		
		int between = 0;
		CPoint temp=this.CurrentCPoint;
		if(this.CurrentCPoint.Cx==armyPosition.Cx)//表示炮是横向移动
		{
			//还需要判断只是移动还是要吃棋子，移动的话和车一样，如果吃棋子的话中间必须隔有且只有一个棋子，并且
			//要到达的坐标p处必须有一个棋子
			int i=1;
			if(temp.Cy<armyPosition.Cy)//表示炮是向右移动
			{
				
				for(;(temp.Cy+i)<armyPosition.Cy;i++)
				{
					if(ChessBoardPanel.nodes[temp.Cx][temp.Cy+i].isOccupied==true)
						between ++;
				}
				if(between == 1)
					return true;
				else 
					return false;
			}
			else //车是向左移动
			{
				for(;(temp.Cy-i)>armyPosition.Cy;i++)
				{
					if(ChessBoardPanel.nodes[temp.Cx][temp.Cy-i].isOccupied==true)
						between++;
				}
				if(between == 1)
					return true;
				else 
					return false;
			}
		}
		else if(this.CurrentCPoint.Cy==armyPosition.Cy)//表示炮是竖直方向移动
		{
			int j=1;
			if(temp.Cx<armyPosition.Cx)//表示炮是向下移动
			{
				for(;(temp.Cx+j)<armyPosition.Cx;j++)
				{
					if(ChessBoardPanel.nodes[temp.Cx+j][temp.Cy].isOccupied==true)
						between ++;
				}
				if(between == 1)
					return true;
				else 
					return false;
			}
			else //表示炮是向上移动
			{
				for(;(temp.Cx-j)>armyPosition.Cx;j++)
				{
					if(ChessBoardPanel.nodes[temp.Cx-j][temp.Cy].isOccupied==true)
						between ++;
				}
				if(between == 1)
					return true;
				else 
					return false;
			}
		}
		else  //既不横向也不竖直方向，那么不符合炮的移动规则，返回false
			return false;
		
		
	}


	public boolean canWalk(CPoint target) {
		

		CPoint temp=this.CurrentCPoint;
		if(CurrentCPoint.getCx()==target.getCx())//表示车是横向移动
		{
			//必须要判断中间是否还隔着棋子，如果隔着棋子那么移动不成功
			
			if(temp.Cy<target.Cy)//表示跑是向右移动
			{
				for(int i=1;(temp.Cy+i)<target.Cy;i++)
				{
					if(ChessBoardPanel.nodes[temp.Cx][temp.Cy+i].isOccupied==true)
						
						{
						return false;
						}
				}
				
				return true;
			}
			else //炮是向左移动
			{
				for(int i=1;(temp.Cy-i)>target.Cy;i++)
				{
					if(ChessBoardPanel.nodes[temp.Cx][temp.Cy-i].isOccupied==true)
					{
						
						return false;
						}
				}
			
				return true;
			}
		}
		else if(CurrentCPoint.getCy()==target.getCy())//表示车是竖直方向移动
		{
			
			if(temp.Cx<target.Cx)//表示车是向下移动
			{
				for(int j=1;(temp.Cx+j)<target.Cx;j++)
				{
					if(ChessBoardPanel.nodes[temp.Cx+j][temp.Cy].isOccupied==true)
					{
						
						return false;
						}
				}
			
				return true;
			}
			else //表示炮是向上移动
			{
				for(int j=1;(temp.Cx-j)>target.Cx;j++)
				{
					if(ChessBoardPanel.nodes[temp.Cx-j][temp.Cy].isOccupied==true)
					{
					
						return false;
						}
				}
				
				return true;
			}
		}
		else  //既不横向也不竖直方向，那么不符合车的移动规则，返回false
			
		
		return false;
	
	}
	
	public ChessPao(ChessColor color,CPoint initCpoint,int num)
	  {
		  super(blackpao,num);
		  this.chessColor=color;
		 
		  if(color==ChessColor.RED)
		  {
			this.ChessMan=ChessType.REDpao;
			this.setIcon(redpao);
		  }
		  
		  if(color==ChessColor.BLACK)
		  {
			  this.ChessMan=ChessType.BLACKpao;
		  }
		  
		  
		  this.CurrentCPoint=initCpoint;
		  this.InitCPoint =initCpoint;
		  this.setInitBounds(this.InitCPoint);  
		  this.setBackground(null);
		  
		
		  
	  }
	public void changeColor() {
		// TODO Auto-generated method stub
		
		if(this.chessColor==ChessColor.BLACK)
		{
			this.chessColor=ChessColor.RED;
			this.ChessMan=ChessType.REDpao;
			this.setIcon(redpao);
		}
		else
		{
			this.chessColor=ChessColor.BLACK;
			this.ChessMan=ChessType.BLACKpao;
			this.setIcon(blackpao);
		}
		
	}

	
	

}
