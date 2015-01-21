package net.myServer;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.Border;

import myChessBoard.MainChessBoard.MainChessBoard;

public class ChattingPanel extends  JPanel implements  ActionListener,KeyListener, Runnable{
	public JTextArea  myText = new JTextArea();
	public JPanel  myMessagePanel =  new JPanel();
	public JPanel armyMessagePanel = new JPanel();
	
	public JTextArea  armyText = new JTextArea();
	public JTextField   myInputText = new JTextField();
	
	
	public myClient   chatClient  = null;
	
    public String MyCharacter ;
	//总头像
	public Head  armyHead  = new Head ("chengran","chengran.jpg", 19, "男", 0);
	public Head  myHead  = new  Head("fangzhihan","fangzhihan.jpg", 19, "男", 0);
	
	//加一个发送的按钮
	public JButton  Send  = new JButton ("send");
	
	
	public  ChattingPanel()
	{
		super();
		
		//this.setBackground(Color.RED);
		this.setBounds(650, 0, 250, 550);
		MyCharacter = "fangzhihan";
		
		this.setMyPanel();
		this.setArmyPanel();
		
		
		this.setLayout(new GridLayout(2,1));
		
		
		
	    this.add(this.armyMessagePanel);
	    this.add(this.myMessagePanel);
		
		
	}
	
	public void setMyPanel()
	{
		this.myMessagePanel.setBackground(Color.pink);
		 Border b = BorderFactory.createEtchedBorder();; //边框
         this.myMessagePanel.setBorder(b); //为这个显示区加上边框
        
        //设置对话框
         this.myText.setEnabled(false);
         this.myMessagePanel.setLayout(null);
       // this.myText.setBounds(0, 100,250, 140);
        
         this.myText.setBorder(b);
        
         this.myText.setDisabledTextColor(Color.black);
         JScrollPane scroll = new JScrollPane(this.myText); 
         scroll.setBounds(0, 100,250, 140);
         scroll.setBorder(b);
          scroll.getViewport().setBackground(Color.PINK);
       
        // scroll.setBackground(Color.pink);
       //  scroll.setForeground(Color.pink);
         this.myMessagePanel.add(scroll);
         
         //设置头像
          this.myHead.setMyBounds(0,0);
          this.myMessagePanel.add(this.myHead);
         
         this.myText.setOpaque(false);
          
         //设置输入框
          this.myInputText.setBounds(10, 245, 160,25);
          this.myMessagePanel.add(this.myInputText);
          this.myInputText.addKeyListener(this);
          
          this.myInputText.setBorder(b);
          //加入发送按钮
          this.Send.setBounds(180, 240, 60, 30);
          this.myMessagePanel.add(this.Send);
          this.Send.addActionListener(this);
          
          
         
         
         
		
		
	}
	
	public void setArmyPanel()
	{
		this.armyMessagePanel.setBackground(Color.yellow);
		Border b = BorderFactory.createEtchedBorder();; //边框
        this.armyMessagePanel.setBorder(b); //为这个显示区加上边框
        
        this.armyText.setEnabled(false);
        this.armyMessagePanel.setLayout(null);
        //this.armyText.setBounds(0, 100,250, 180);
        
        JScrollPane scroll = new JScrollPane(this.armyText); 
        scroll.setBounds(0, 100,250, 180);
        scroll.setBorder(b);
         scroll.getViewport().setBackground(Color.YELLOW);
        
        this.armyMessagePanel.add(scroll);
        this.armyText.setBorder(b);
         this.armyHead.setMyBounds(0,0);
         this.armyMessagePanel.add(this.armyHead);
         this.armyText.setDisabledTextColor(Color.BLACK);
         
         this.armyText.setOpaque(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()== this.Send&&!this.myInputText.getText().equals(""))
		{
			String str  = this.myInputText.getText();
			this.myText.append("我说："+str+"\n");
			this.myInputText.setText("");
			if(MainChessBoard.NetOpen)
			this.chatClient.sendTo(str);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyCode()==KeyEvent.VK_ENTER&&!this.myInputText.getText().equals(""))
		{
			String str  = this.myInputText.getText();
			this.myText.append("我说："+str+"\n");
			this.myInputText.setText("");
			if(MainChessBoard.NetOpen)
			this.chatClient.sendTo(str);
		}
	}
	
	//联网时交换角色
	public void  changeMessage()
	{
		this.armyMessagePanel.remove(armyHead);
		this.myMessagePanel.remove(this.myHead);
		this.armyMessagePanel.repaint();
		this.myMessagePanel.repaint();
		
		Head temp = myHead;
		myHead  = armyHead;
		armyHead= temp;
		
		MyCharacter= myHead.Name;
		
		//this.armyHead.setMyBounds(x, y)
		
		this.armyMessagePanel.add(this.armyHead);
		this.myMessagePanel.add(this.myHead);
		
		
		
	}
	
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true)
		{
		if(MainChessBoard.NetOpen)
		{
		    String str =  this.chatClient.readCommand();
		    this.armyText.append("对家发话了："+str+"\n");
		}
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	}


}
