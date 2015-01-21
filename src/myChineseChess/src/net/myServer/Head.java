package net.myServer;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.border.Border;

public class Head  extends JPanel{
	
	
	
	public static  final int WIDTH  = 80;
	public static  final  int HEIGHT =95;
	
	public String Name  ;
	public Icon ImgIcon= null;
	
	public JLabel myLabel;
	public JLabel myName;
	
	//放置头像和姓名的
	JPanel  head  = new JPanel ();
	
	//设置个人信息的
	JLabel AgeLabel = new JLabel();
	JLabel GenderLabel= new JLabel();
	JLabel leftChessNumLabel =  new JLabel();
	
	public int age ; 
	public  String gender ;
	public int myCredit;
	
	public Head(String name, String iconpath, int age , String genter ,int  leftChessNum)
	{
		super();
		
		this.Name=name;
		this.ImgIcon= new ImageIcon (iconpath);
		this.myCredit= leftChessNum;
		this.gender= genter;
		this.age = age;
		this.setLayout(null);
		this.setOpaque(false);
        this.sethead();
	
		this.setMessage();
	}
	
	
    //设置头像区
	public void sethead()
	{

		this.myLabel =  new JLabel(this.ImgIcon);
		this.myName = new JLabel("  "+this.Name);
		
		this.head.setLayout(new BorderLayout());
		 Border b = BorderFactory.createEtchedBorder();//边框
         this.head.setBorder(b); //为这个显示区加上边框
         
		this.head.add(this.myLabel, BorderLayout.CENTER);
		this.head.add(this.myName,BorderLayout.SOUTH);
		this.head.setBounds(0,0,WIDTH,HEIGHT);
		this.add(this.head);
		
	}
	public void  setMyBounds(int x, int y )
	{
		this.setBounds(x, y, 200, HEIGHT);
		
	}
	
	
	//设置信息栏
	public void setMessage()
	{
		
		
		this.AgeLabel.setText("年龄："+this.age);
		this.GenderLabel.setText("性别："+this.gender);
		this.leftChessNumLabel.setText("当前积分："+this.myCredit);
		
		
		this.GenderLabel.setBounds(WIDTH+20,0,200-WIDTH,HEIGHT/3);
		this.AgeLabel.setBounds(WIDTH+20,HEIGHT/3,200-WIDTH,HEIGHT/3);
		this.leftChessNumLabel.setBounds(WIDTH+20,HEIGHT/3*2,200-WIDTH,HEIGHT/3);
		
		
		this.add(this.GenderLabel);
		this.add( this.AgeLabel);
		this.add(this.leftChessNumLabel);
		
		
	}

}
