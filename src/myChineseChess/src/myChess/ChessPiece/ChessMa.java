package myChess.ChessPiece;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import myChess.Enum.ChessColor;
import myChess.Enum.ChessType;
import myChessBoard.MainChessBoard.CPoint;
import myChessBoard.MainChessBoard.ChessBoardPanel;
import myChessBoard.MainChessBoard.Player;

public class ChessMa extends ChessPiece{

	  public static Icon blackhorse=new ImageIcon("blackhorse.png");
	  public static Icon redhorse   = new ImageIcon ("redma.png");
	  
	
		public void walkTo(CPoint target) {
			// TODO Auto-generated method stub
			
			if(canWalk(target))
			{
				this.walk(target);
				 this.myBoardPanel.SoundMov.playSound();
			}
			
		}
		
		
		//�ж��Ƿ�������
		public boolean canWalk(CPoint target)
		{
			CPoint temp=this.CurrentCPoint;
			if(Math.pow(target.Cx-this.CurrentCPoint.Cx, 2)+Math.pow(target.Cy-this.CurrentCPoint.Cy, 
					2)==5)//��Ҫ�ж��Ƿ�  ������
			{
				if(Math.abs(temp.Cx-target.Cx)==1)   //��ʾ����������ѡ�������
				{
					if(target.Cy>temp.Cy) //��ʾ������
					{
						if(ChessBoardPanel.nodes[temp.Cx][temp.Cy+1].isOccupied)
						{
							this.CanReachable=false;
							return false;
						}
						else
						{
							this.CanReachable=false;
							return true;
						}
					}
					else//��ʾ������
					{
						if(ChessBoardPanel.nodes[temp.Cx][temp.Cy-1].isOccupied)
							return false;
						else
							return true;
					}
				}
				else           //��ʾ��Ҫ�����ߣ��ж��Ƿ� ������
				{
					if(target.Cx>temp.Cx)//��ʾҪ������
					{
						if(ChessBoardPanel.nodes[temp.Cx+1][temp.Cy].isOccupied)
							return false;
						else
							return true;
					}
					else  //��ʾҪ������
					{
						if(ChessBoardPanel.nodes[temp.Cx-1][temp.Cy].isOccupied)
							return false;
						else
							return true;
					}
				}
			}
			else
				return false;
			
			
	    }
	
	public ChessMa(ChessColor color,CPoint initCpoint,int num)
	{
		 
			  super(blackhorse,num);
			  this.chessColor=color;
			 
			  if(color==ChessColor.RED)
			  {
				this.ChessMan=ChessType.REDma;
				this.setIcon(redhorse);
			  }
			  
			  if(color==ChessColor.BLACK)
			  {
				  this.ChessMan=ChessType.BLACKma;
			  }
			  
			  
			  this.CurrentCPoint=initCpoint;
			  this.InitCPoint =initCpoint;
			  this.setInitBounds(this.InitCPoint);  
			  this.setBackground(null);	  

	}
	@Override
	public boolean canEat(CPoint armyPosition) {
		// TODO Auto-generated method stub
		return this.canWalk(armyPosition);
	}

	@Override
	public void eat(CPoint target) {
	
		if(canEat(target))
	{
		
		this.CanReachable=true;
		this.walk(target);
		
		
	}
	else
	{
		this.CanReachable=false;
	}
		
		
		 this.myBoardPanel.SoundEat.playSound();
		
	}
	
	public void changeColor() {
		// TODO Auto-generated method stub
		
		if(this.chessColor==ChessColor.BLACK)
		{
			this.chessColor=ChessColor.RED;
			this.ChessMan=ChessType.REDma;
			this.setIcon(redhorse);
		}
		else
		{
			this.chessColor=ChessColor.BLACK;
			this.ChessMan=ChessType.BLACKma;
			this.setIcon(blackhorse);
		}
		
	}





}
