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
	
	//����ͷ���������
	JPanel  head  = new JPanel ();
	
	//���ø�����Ϣ��
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
	
	
    //����ͷ����
	public void sethead()
	{

		this.myLabel =  new JLabel(this.ImgIcon);
		this.myName = new JLabel("  "+this.Name);
		
		this.head.setLayout(new BorderLayout());
		 Border b = BorderFactory.createEtchedBorder();//�߿�
         this.head.setBorder(b); //Ϊ�����ʾ�����ϱ߿�
         
		this.head.add(this.myLabel, BorderLayout.CENTER);
		this.head.add(this.myName,BorderLayout.SOUTH);
		this.head.setBounds(0,0,WIDTH,HEIGHT);
		this.add(this.head);
		
	}
	public void  setMyBounds(int x, int y )
	{
		this.setBounds(x, y, 200, HEIGHT);
		
	}
	
	
	//������Ϣ��
	public void setMessage()
	{
		
		
		this.AgeLabel.setText("���䣺"+this.age);
		this.GenderLabel.setText("�Ա�"+this.gender);
		this.leftChessNumLabel.setText("��ǰ���֣�"+this.myCredit);
		
		
		this.GenderLabel.setBounds(WIDTH+20,0,200-WIDTH,HEIGHT/3);
		this.AgeLabel.setBounds(WIDTH+20,HEIGHT/3,200-WIDTH,HEIGHT/3);
		this.leftChessNumLabel.setBounds(WIDTH+20,HEIGHT/3*2,200-WIDTH,HEIGHT/3);
		
		
		this.add(this.GenderLabel);
		this.add( this.AgeLabel);
		this.add(this.leftChessNumLabel);
		
		
	}

}
