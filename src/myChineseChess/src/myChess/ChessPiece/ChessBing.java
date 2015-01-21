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
	//�ж��Ƿ����
	public void crossRiver()
	{
		if(this.InitCPoint.Cy==3)        //�����Ǻ�ɫ
		{
			if(this.CurrentCPoint.Cy>4)
				this.isCrossRiver=true;
		}
		else//��ɫ����
		{
			if(this.CurrentCPoint.Cy<5)
				this.isCrossRiver=true;
		}	
		
	}

	@Override
	public boolean canWalk(CPoint target) {

		//��Ҫ�ֳ������������������˿��Ժ����ߣ����û�й��ӵĻ��Ͳ��ܡ�
				//������������������ڱ���˵��ֻ��ǰ�������ܺ���
				CPoint temp=this.CurrentCPoint;
				
				if(this.isCrossRiver==false)//��ʾ��û�й���,ֻ����ǰ��
				{
					if(temp.Cx==target.Cx)  //ֻ����ֱ������
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
				else//��ʾ�����ˣ����������ߣ�����Ҳֻ����ǰ
				{
					if(this.InitCPoint.Cy==3)
					{
						if(temp.Cy==target.Cy)//��ʾ������
						{
							if(Math.abs(temp.Cx-target.Cx)==1) //һ��ֻ���ƶ�һ����λ
								return true;
							else
								return false;
						}
						else if(temp.Cx==target.Cx)//��ʾ������
						{
							if(target.Cy-temp.Cy==1)  //��ֱ����ֻ���ƶ�һ����λ������p.xһ��Ҫ��temp.x��
								return true;
							else
								return false;
						}
						else
							return false;
					}
					else         //this.current_Pos.x==5
					{
						if(temp.Cy==target.Cy)//��ʾ������
						{
							if(Math.abs(temp.Cx-target.Cx)==1)
								return true;
							else
								return false;
						}
						else if(temp.Cx==target.Cx)//��ʾ������
						{
							if(temp.Cy-target.Cy==1)  //��ֱ����ֻ���ƶ�һ����λ,����temp.xһ��Ҫ��p.x��
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
