package myChessBoard.MainChessBoard;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import myChess.ChessPiece.ChessPiece;
import myChess.Enum.ChessColor;
import myChess.Enum.ChessType;
import myChessBoard.MainChessBoard.*;

public class CPoint {//implements  MouseListener{
	
	public int Cx=0;
	public int Cy=0;    //C打头区别普通坐标
	
	public boolean isOccupied= false;
	public ChessColor occupiedColor = null;
	public ChessType OccupeidType = null;
	public ChessPiece OccupiedChess =null;
	
	public static final double CPWidth = 63.25;  //每一格的宽度
	public static final double CPHeight= 58.25;  //每一格的高度
	public static final double  initCx =42.0;    //起始横坐标
	public static final double  initCy =5.0;   //起始纵坐标
	
	//棋盘的边界线位置
	public static final double BorderMinCx = 72.0;
	public static final double BorderMaxCx = 578.0;
	public static final double BorderMinCy = 37.0;
	public static final double BorederMaxCy =561.0;
	
	public static final double midum= 399.0;
	
	
	public int getCx() {
		return Cx;
	}
	public void setCx(int cx) {
		Cx = cx;
	}
	public int getCy() {
		return Cy;
	}
	
	public void setCy(int cy) {
		Cy = cy;
	}
	
	
	
	public CPoint()
	{
		
	}
	public CPoint(int x ,int y)
	{
		this.setCx(x);
		this.setCy(y);
		
	}
	
	//根据鼠标点建立棋盘点
	public CPoint(Point MousePoint)      
	{ 
		CPoint temp = new CPoint();
		CPoint.changeToCPoint(MousePoint);
		this.Cx=temp.getCx();
		this.Cy=temp.getCy();
	 
	}
	
	
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		// TODO Auto-generated method stub
//		System.out.println("("+e.getX()+","+e.getY()+")");
//		System.out.println("("+CPoint.changeToCPoint(e.getPoint()).getCx()+","+CPoint.changeToCPoint(e.getPoint()).getCy()+")");
//	    
//		System.out.println("("+CPoint.changeToPoint(CPoint.changeToCPoint(e.getPoint())).x+","+CPoint.changeToPoint(CPoint.changeToCPoint(e.getPoint())).y+")");
//	}
//	
//	@Override
//	public void mousePressed(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mouseExited(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
	
	public static CPoint changeToCPoint(Point a)
	{
		CPoint temp =new CPoint ();
		temp.Cx =(int)(((double)a.x-initCx)/CPWidth);
		temp.Cy =(int)(((double)a.y-initCy)/CPHeight);
		return temp; 
	}
	
	//转换为坐标
	public static Point changeToPoint(CPoint a)
	{
		Point temp = new Point();
		temp.x = (int)((double)a.getCx()*(CPoint.CPWidth)+CPoint.initCx);
		temp.y =(int)((double)a.getCy()*(CPoint.CPHeight)+CPoint.initCy);
		return temp;
		
	}
	//判断坐标是否相等
	public boolean equals(CPoint temp)
	{
		return (this.Cx==temp.Cx&&this.Cy==temp.Cy);
	}
	
	//转换为棋盘上的节点
	public static CPoint changeToNode(Point a)
	{
		
		return  ChessBoardPanel.nodes[changeToCPoint(a).Cx] [changeToCPoint(a).Cy];
	}
	
	public boolean noChessHere()
	{
		return this.OccupeidType==null;
	}
	public void setChess(ChessType chess)
	{
		this.OccupeidType=chess;
	
	}
	
	//网络对战时，我的左边转换为对方敌人的左边
	public static CPoint changeToArmyBoard(CPoint a )
	{
		CPoint temp = new CPoint(8-a.Cx,9-a.Cy);
		return temp;
	}

}
