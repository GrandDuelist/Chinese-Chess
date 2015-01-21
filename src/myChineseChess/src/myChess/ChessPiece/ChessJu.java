package myChess.ChessPiece;



import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import myChess.Enum.*;
import myChessBoard.MainChessBoard.CPoint;
import myChessBoard.MainChessBoard.ChessBoardPanel;
import myChessBoard.MainChessBoard.Player;

public class ChessJu extends ChessPiece {
	
	  public static Icon blackju=new ImageIcon("blackju.png");
	  public static Icon redju   = new ImageIcon ("redzu.png");
	  

		@Override
		public void walkTo(CPoint target) {
			// TODO Auto-generated method stub
			this.CanReachable=this.canWalk(target);
			if(this.CanReachable)
			{
				
				this.walk(target);
				 this.myBoardPanel.SoundMov.playSound();
			}
			
		}
		
		
		//�ж��Ƿ�������
		public boolean canWalk(CPoint target)
		{
			

			CPoint temp=this.CurrentCPoint;
			if(CurrentCPoint.Cx==target.Cx)//��ʾ���Ǻ����ƶ�
			{
				//����Ҫ�ж��м��Ƿ񻹸������ӣ��������������ô�ƶ����ɹ�
				
				if(temp.Cy<target.Cy)//��ʾ���������ƶ�
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
				else //���������ƶ�
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
			else if(CurrentCPoint.Cy==target.Cy)//��ʾ������ֱ�����ƶ�
			{
				
				if(temp.Cx<target.Cx)//��ʾ���������ƶ�
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
				else //��ʾ���������ƶ�
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
			else  //�Ȳ�����Ҳ����ֱ������ô�����ϳ����ƶ����򣬷���false
				
				
			return false;
		}
	  
		
		
	  
	  public ChessJu(ChessColor color,CPoint initCpoint,int num)
	  {
		  super(blackju,num);
		  this.chessColor=color;
		 
		  if(color==ChessColor.RED)
		  {
			this.ChessMan=ChessType.REDzhu;
			this.setIcon(redju);
		  }
		  
		  if(color==ChessColor.BLACK)
		  {
			  this.ChessMan=ChessType.BLACKzhu;
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
				this.ChessMan=ChessType.REDzhu;
				this.setIcon(redju);
			}
			else
			{
				this.chessColor=ChessColor.BLACK;
				this.ChessMan=ChessType.BLACKzhu;
				this.setIcon(blackju);
			}
		}

	

}
