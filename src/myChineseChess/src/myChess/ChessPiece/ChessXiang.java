package myChess.ChessPiece;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import myChess.Enum.ChessColor;
import myChess.Enum.ChessType;
import myChessBoard.MainChessBoard.CPoint;
import myChessBoard.MainChessBoard.ChessBoardPanel;
import myChessBoard.MainChessBoard.Player;

public class ChessXiang extends ChessPiece{

	

	  public static Icon blackxiang=new ImageIcon("blackxiang.png");
	  public static Icon redxiang   = new ImageIcon ("redxiang.png");
	  
	

	@Override
	public boolean canWalk(CPoint target) {
		
		CPoint temp=this.CurrentCPoint;
		if(this.InitCPoint.Cy==0)
		{
			if(target.Cy<=4)  //象不能过河
			{
		if(Math.abs(temp.Cx-target.Cx)==2&&Math.abs(temp.Cy-target.Cy)==2)//判断是否是“田”
		{
			//还需要判断象眼是否堵上，如果堵上的话就无法移动
			if(ChessBoardPanel.nodes[(temp.Cx+target.Cx)/2][(temp.Cy+target.Cy)/2].isOccupied==false)
				return true;
			else
				return false;
		}
		else
			return false;
			}
			else
				return false;
	  }
		else
		{
			if(target.Cy>=5)
			{
				if(Math.abs(temp.Cx-target.Cx)==2&&Math.abs(temp.Cy-target.Cy)==2)//判断是否是“田”
				{
					if(ChessBoardPanel.nodes[(temp.Cx+target.Cx)/2][(temp.Cy+target.Cy)/2].isOccupied==false)
						return true;
					else
						return false;
				}
				else
					return false;
			}
			else
				return false;
		}
	
	}
	
	
	  public ChessXiang(ChessColor color,CPoint initCpoint, int num)
	  {
		  super(blackxiang,num);
		  this.chessColor=color;
		 
		  if(color==ChessColor.RED)
		  {
			this.ChessMan=ChessType.REDxiang;
			this.setIcon(redxiang);
		  }
		  
		  if(color==ChessColor.BLACK)
		  {
			  this.ChessMan=ChessType.BLACKxiang;
		  }
		  
		  
		  this.InitCPoint =initCpoint;
		  this.CurrentCPoint=initCpoint;
		  this.setInitBounds(this.InitCPoint);  
		  this.setBackground(null);
		  
	  }


	@Override
	public void walkTo(CPoint target) {
		// TODO Auto-generated method stub
		
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
			this.ChessMan=ChessType.REDxiang;
			this.setIcon(redxiang);
		}
		else
		{
			this.chessColor=ChessColor.BLACK;
			this.ChessMan=ChessType.BLACKxiang;
			this.setIcon(blackxiang);
		}
		
	}


}
