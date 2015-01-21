package myChess.ChessPiece;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import myChess.Enum.ChessColor;
import myChess.Enum.ChessType;
import myChessBoard.MainChessBoard.CPoint;
import myChessBoard.MainChessBoard.Player;

public class ChessBing extends ChessPiece{

	

	  public static Icon blackzu=new ImageIcon("blackzu.png");
	  public static Icon redzu   = new ImageIcon ("redbing.png");
	  
	@Override
	public void walkTo(CPoint target) {
		this.crossRiver();
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
	//判断是否过河
	public void crossRiver()
	{
		if(this.InitCPoint.Cy==3)        //代表是红色
		{
			if(this.CurrentCPoint.Cy>4)
				this.isCrossRiver=true;
		}
		else//黑色棋子
		{
			if(this.CurrentCPoint.Cy<5)
				this.isCrossRiver=true;
		}	
		
	}

	@Override
	public boolean canWalk(CPoint target) {

		//需要分成两种情况，如果过河了可以横向走，如果没有过河的话就不能。
				//不管是哪种情况，对于兵来说都只能前进，不能后退
				CPoint temp=this.CurrentCPoint;
				
				if(this.isCrossRiver==false)//表示还没有过河,只能向前走
				{
					if(temp.Cx==target.Cx)  //只能竖直方向走
					{
						if(this.InitCPoint.Cy==3)
						{
							if(temp.Cy<target.Cy&&target.Cy-temp.Cy==1)
								return true;
							else
								return false;
						}
						else
						{
							if(temp.Cy>target.Cy&&temp.Cy-target.Cy==1)
								return true;
							else
								return false;
						}
					}
					else
						return false;
				}
				else//表示过河了，可以左右走，但是也只能向前
				{
					if(this.InitCPoint.Cy==3)
					{
						if(temp.Cy==target.Cy)//表示横向走
						{
							if(Math.abs(temp.Cx-target.Cx)==1) //一次只能移动一个单位
								return true;
							else
								return false;
						}
						else if(temp.Cx==target.Cx)//表示纵向走
						{
							if(target.Cy-temp.Cy==1)  //竖直方向只能移动一个单位，并且p.x一定要比temp.x大
								return true;
							else
								return false;
						}
						else
							return false;
					}
					else         //this.current_Pos.x==5
					{
						if(temp.Cy==target.Cy)//表示横向走
						{
							if(Math.abs(temp.Cx-target.Cx)==1)
								return true;
							else
								return false;
						}
						else if(temp.Cx==target.Cx)//表示纵向走
						{
							if(temp.Cy-target.Cy==1)  //竖直方向只能移动一个单位,并且temp.x一定要比p.x大
								return true;
							else 
								return false;
						}
						else
							return false;
					}
				}
	}
	
	
	

	public ChessBing(ChessColor color, CPoint initCpoint,int num){
		
		  super(blackzu,num);
		  this.chessColor=color;
		 
		  if(color==ChessColor.RED)
		  {
			this.ChessMan=ChessType.REDbin;
			this.setIcon(redzu);
		  }
		  
		  if(color==ChessColor.BLACK)
		  {
			  this.ChessMan=ChessType.BLACKbin;
		  }
		  
		  
		  this.CurrentCPoint=initCpoint;
		  this.InitCPoint =initCpoint;
		  this.setInitBounds(this.InitCPoint);  
		  this.setBackground(null);
		  
		
		  
	  
}
	@Override
	public boolean canEat(CPoint armyPosition) {
		return this.canWalk(armyPosition);
	}
	@Override
	public void eat(CPoint target) {
		this.crossRiver();
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
	@Override
	public void changeColor() {
		// TODO Auto-generated method stub
		
		if(this.chessColor==ChessColor.BLACK)
		{
			this.chessColor=ChessColor.RED;
			this.ChessMan=ChessType.REDbin;
			this.setIcon(redzu);
		}
		else
		{
			this.chessColor=ChessColor.BLACK;
			this.ChessMan=ChessType.BLACKbin;
			this.setIcon(blackzu);
		}
		
	}

	
	
	

}
