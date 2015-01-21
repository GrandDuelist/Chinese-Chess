package myChess.ChessPiece;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import myChess.Enum.ChessColor;
import myChess.Enum.ChessType;
import myChessBoard.MainChessBoard.CPoint;
import myChessBoard.MainChessBoard.Player;

public class ChessJiang extends ChessPiece {

	
	  public static Icon blackjiang=new ImageIcon("blackjiang.png");
	  public static Icon redshuai   = new ImageIcon ("redshuai.png");
	@Override
	
	//行走函数
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

	@Override
	public boolean canWalk(CPoint target) {     //判断要去的地方是否合法，可以返回true，不能走返回false//子类继承方法
		CPoint temp=this.CurrentCPoint;
		if(position_judge(target)==true)
		{
			if(Math.abs(temp.Cx-target.Cx)+Math.abs(temp.Cy-target.Cy)==1)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	//将军能走的所有位置
	boolean position_judge(CPoint p)
	{
		CPoint start=this.InitCPoint;
		
		CPoint r_pos1=new CPoint(3,0);
		CPoint r_pos2=new CPoint(4,0);
		CPoint r_pos3=new CPoint(5,0);
		CPoint r_pos4=new CPoint(3,1);
		CPoint r_pos5=new CPoint(4,1);
		CPoint r_pos6=new CPoint(5,1);
		CPoint r_pos7=new CPoint(3,2);
		CPoint r_pos8=new CPoint(4,2);
		CPoint r_pos9=new CPoint(5,2);
		
		CPoint b_pos1=new CPoint(3,9);
		CPoint b_pos2=new CPoint(4,9);
		CPoint b_pos3=new CPoint(5,9);
		CPoint b_pos4=new CPoint(3,8);
		CPoint b_pos5=new CPoint(4,8);
		CPoint b_pos6=new CPoint(5,8);
		CPoint b_pos7=new CPoint(3,7);
		CPoint b_pos8=new CPoint(4,7);
		CPoint b_pos9=new CPoint(5,7);
		if(start.Cy==0)
		{
			if(p.equals(r_pos1)||p.equals(r_pos2)||p.equals(r_pos3)||p.equals(r_pos4)||p.equals(r_pos5)
					||p.equals(r_pos6)||p.equals(r_pos7)||p.equals(r_pos8)||p.equals(r_pos9))
				return true;
			else
				return false;
		}
		else
		{
			if(p.equals(b_pos1)||p.equals(b_pos2)||p.equals(b_pos3)||p.equals(b_pos4)||p.equals(b_pos5)
					||p.equals(b_pos6)||p.equals(b_pos7)||p.equals(b_pos8)||p.equals(b_pos9))
				return true;
			else
				return false;
		}
	}

	public ChessJiang(ChessColor color, CPoint initCpoint,int num){
	
			  super(blackjiang,num);
			  this.chessColor=color;
			 
			  if(color==ChessColor.RED)
			  {
				this.ChessMan=ChessType.REDshuai;
				this.setIcon(redshuai);
			  }
			  
			  if(color==ChessColor.BLACK)
			  {
				  this.ChessMan=ChessType.BLACKshuai;
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
			this.ChessMan=ChessType.REDshuai;
			this.setIcon(redshuai);
		}
		else
		{
			this.chessColor=ChessColor.BLACK;
			this.ChessMan=ChessType.BLACKshuai;
			this.setIcon(blackjiang);
		}

	
	}
}
