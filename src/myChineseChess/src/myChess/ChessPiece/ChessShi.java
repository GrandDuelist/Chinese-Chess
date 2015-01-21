package myChess.ChessPiece;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import myChess.Enum.ChessColor;
import myChess.Enum.ChessType;
import myChessBoard.MainChessBoard.CPoint;
import myChessBoard.MainChessBoard.Player;

public class ChessShi extends ChessPiece{
	
	  public static Icon blackshi=new ImageIcon("blackshi.png");
	  public static Icon redshi   = new ImageIcon ("redshi.png");
	
	 public ChessShi(ChessColor color,CPoint initCpoint,int num)
	  {
		  super(blackshi,num);
		  this.chessColor=color;
		 
		  if(color==ChessColor.RED)
		  {
			this.ChessMan=ChessType.REDshi;
			this.setIcon(redshi);
		  }
		  
		  if(color==ChessColor.BLACK)
		  {
			  this.ChessMan=ChessType.BLACKshi;
		  }
		  
		  
		  this.InitCPoint =initCpoint;
		  this.CurrentCPoint=initCpoint;
		  this.setInitBounds(this.InitCPoint);  
		  this.setBackground(null);
		  
	  }

	@Override
	public void walkTo(CPoint target) {
		// TODO Auto-generated method stub
		
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
	public boolean canWalk(CPoint target) {
		
		CPoint temp=this.CurrentCPoint;
		if(position_judge(target))
		{
			if(Math.abs(temp.Cx-target.Cx)==1&&Math.abs(temp.Cy-target.Cy)==1)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	boolean position_judge(CPoint p)
	{
		CPoint start=this.InitCPoint;
		CPoint r_pos1=new CPoint(3,0);
		CPoint r_pos2=new CPoint(5,0);
		CPoint r_pos3=new CPoint(4,1);
		CPoint r_pos4=new CPoint(3,2);
		CPoint r_pos5=new CPoint(5,2);
		
		CPoint b_pos1=new CPoint(3,9);
		CPoint b_pos2=new CPoint(5,9);
		CPoint b_pos3=new CPoint(4,8);
		CPoint b_pos4=new CPoint(3,7);
		CPoint b_pos5=new CPoint(5,7);
		if(start.Cy==0)
		{
			if(p.equals(r_pos1)||p.equals(r_pos2)||p.equals(r_pos3)||p.equals(r_pos4)||p.equals(r_pos5))
				return true;
			else
				return false;
		}
		else
		{
			if(p.equals(b_pos1)||p.equals(b_pos2)||p.equals(b_pos3)||p.equals(b_pos4)||p.equals(b_pos5))
				return true;
			else
				return false;
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
			this.ChessMan=ChessType.REDshi;
			this.setIcon(redshi);
		}
		else
		{
			this.chessColor=ChessColor.BLACK;
			this.ChessMan=ChessType.BLACKshi;
			this.setIcon(blackshi);
		}
		
	}


}
