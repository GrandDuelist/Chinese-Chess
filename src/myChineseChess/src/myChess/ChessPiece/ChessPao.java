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
	//�ڵĳ������⣬�ֿ�д
	public void eat(CPoint target)
	{
		if(canEat(target))
		{
			this.CanReachable=true;
			this.walk(target);
		}
		
		
		 this.myBoardPanel.SoundEat.playSound();
		
	}
	//�ж��ܹ�����
	public boolean canEat(CPoint armyPosition)
	{
		
		int between = 0;
		CPoint temp=this.CurrentCPoint;
		if(this.CurrentCPoint.Cx==armyPosition.Cx)//��ʾ���Ǻ����ƶ�
		{
			//����Ҫ�ж�ֻ���ƶ�����Ҫ�����ӣ��ƶ��Ļ��ͳ�һ������������ӵĻ��м���������ֻ��һ�����ӣ�����
			//Ҫ���������p��������һ������
			int i=1;
			if(temp.Cy<armyPosition.Cy)//��ʾ���������ƶ�
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
			else //���������ƶ�
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
		else if(this.CurrentCPoint.Cy==armyPosition.Cy)//��ʾ������ֱ�����ƶ�
		{
			int j=1;
			if(temp.Cx<armyPosition.Cx)//��ʾ���������ƶ�
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
			else //��ʾ���������ƶ�
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
		else  //�Ȳ�����Ҳ����ֱ������ô�������ڵ��ƶ����򣬷���false
			return false;
		
		
	}


	public boolean canWalk(CPoint target) {
		

		CPoint temp=this.CurrentCPoint;
		if(CurrentCPoint.getCx()==target.getCx())//��ʾ���Ǻ����ƶ�
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
		else if(CurrentCPoint.getCy()==target.getCy())//��ʾ������ֱ�����ƶ�
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
