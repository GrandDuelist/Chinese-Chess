package myChessBoard.MainChessBoard;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;


import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



import myChess.Enum.ChessColor;
import net.myServer.AdvanceChat;
import net.myServer.ChattingPanel;
import net.myServer.myClient;

public class MainChessBoard extends JFrame implements ActionListener, Runnable{
	
	//����ļ�¼
	public static int lastArmyChess = -1;
//	public static  int lastArmyCx = -1;
//	public static  int lastArmyCy = -1;
//	public static  int lastMyCx = -1;
//	public static  int lastMyCy = -1;
	public static  int lastMyChess = -1;
	public static CPoint lastArmyCPoint = null;
	public static CPoint lastMyCPoint = null;
	public static int myEteChess =-1;
	public static int myChessEat =-1; 
	public static int myarmyEteChess =-1;
	public static int armyChessEat=-1;
	
	public static String IPAddress = "";
	
	 public   AdvanceChat   myChatting =  new AdvanceChat();
	public ChessBoardPanel ChessBoard = null;
	public Thread ChessThread1 =null;
	public static myClient fightClient = null;
	public JMenuItem openNet = new JMenuItem("��������");
	public JMenuItem setIP = new JMenuItem("IP����");
	public static Player  backMusic=null;
	
	public JMenu Setting = new JMenu("����");
	
	//�������ֵĿ���
	public JMenuItem OpenBackMusic = new JMenuItem("��");
	public JMenuItem CloseBackMusic = new JMenuItem("��");
	public JMenu BackMusic = new JMenu("��������");
	public static boolean backIsOn = true;
	
	//��Ч����
	public  static boolean isSoundOn = true;
	public JMenuItem CloseSound =  new JMenuItem("��");
	public JMenuItem OpenSound= new JMenuItem("��");
	public JMenu Sound = new JMenu("��Ч");
	
	
	public static ChattingPanel  myChat=  new ChattingPanel();
    public Thread ChatThread  = null;
	
	
   public  int seq = 1;
    
   public  int BoardNum =0;
   
	public static boolean NetOpen= false;
	
	//�߼����촰��
	public JMenu  ChatSet= new JMenu ("����");
	public JMenuItem  ChatOpen = new JMenuItem ("�߼�����");
	
  //���ܰ�ť
	//���
	public Icon peace = new ImageIcon("peaceable.png");
	public JButton PeaceRequest =  new JButton(peace);
	public static boolean  isPeace = false;
	public static boolean  armyPeace =false;  //�ط��Ƿ�ͬ��

	//��ʼ
    Icon begin = new ImageIcon("beginready.png");
	public JButton BeginGame =  new JButton(begin);
	public static boolean  isReady =false;
	public static boolean  armyReady =false;
	public static boolean StartGame=false;
	
	//����
	public Icon regret = new ImageIcon("regretready.png");
	public JButton Regret =  new JButton(regret);
	public static boolean isRegret = false;
	public static boolean  armyRegret =false;
	
	//Ͷ��
	public Icon surrender = new ImageIcon("giveinready.png");
	public JButton Surrender =  new JButton(surrender);
	public  static boolean isSurrender = false;
	public static  boolean  armySurrender =false;
	
	public MainChessBoard() {
		
		super("�й�����");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize( 900, 660);
		//ChatArea chat = new ChatArea();
		this.ChessBoard=new ChessBoardPanel();
		//this.add(chat);
		this.setLayout(null);
		this.addBackgroudMusic();
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);
		this.addChessBoard();
		
		this.addFunction();
		this.setMenuBar();
		
		
		
		this.add(this.myChat);
		this.ChatThread= new Thread(this.myChat);
		this.ChatThread.start();
		
		this.ChessThread1 = new Thread(ChessBoard);
		ChessThread1.start();
		
		Thread AdvanceChatting = new Thread(this.myChatting);
		AdvanceChatting.start();
		
		this.setVisible(true);
//		contentPane = new JPanel();
//		contentPane.setToolTipText("\u4E2D\u56FD\u8C61\u68CB");
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(new BorderLayout(0, 0));
	}
	
	public void addChessBoard()
	{
		
		this.add(ChessBoard,BorderLayout.CENTER);
		this.ChessBoard.myChessBoard=this;
		
	}
	
	//��ӹ��ܰ�ť
	public void addFunction()
	{
		
		JPanel function = new JPanel();
		function.setBounds(650, 550,250, 50);
		this.add(function);
		function.setLayout(new GridLayout(1,4));
		//this.PeaceRequest.setBounds(0, , 40,20);
		//this.BeginGame.setForeground(Color.RED);
		//this.BeginGame.setOpaque(false);
		function.add(this.BeginGame);
	    function.add(this.Regret);
		function.add(this.PeaceRequest);
		function.add(this.Surrender);
		
		this.BeginGame.addActionListener(this);
		this.Regret.addActionListener(this);
		this.PeaceRequest.addActionListener(this);
		this.Surrender.addActionListener(this);
	}
	
	//��ӱ�������
	public void addBackgroudMusic()
	{
		backMusic= new Player("background.wav",1);
		
		backMusic.start();
		
	}
	
	public void setMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menu = new JMenu("�˵�");
		menuBar.add(menu);
		
		menu.add(this.setIP);
		this.setIP.addActionListener(this);
		menu.add(this.openNet);
		this.openNet.addActionListener(this);
		
		
		//����������Ч����
		
		this.Setting.add(this.BackMusic);
		this.BackMusic.add(this.OpenBackMusic);
		this.BackMusic.add(this.CloseBackMusic);
		
		this.Setting.add(this.Sound);
		this.Sound.add(this.OpenSound);
		this.Sound.add(this.CloseSound);
		
		
        menuBar.add(this.ChatSet);
        this.ChatSet.add(this.ChatOpen);
       
        menuBar.add(this.Setting);
		this.OpenBackMusic.addActionListener(this);
		this.CloseBackMusic.addActionListener(this);
		this.OpenSound.addActionListener(this);
		this.CloseSound.addActionListener(this);
		this.ChatOpen.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==openNet)
		{
	try{
          fightClient = new myClient();
			
          this.myChat.chatClient = new myClient();
          this.myChatting.MyAdvanceChat = new myClient();
          AdvanceChat.Control= new myClient();
			if(fightClient.readCommand().equals("2"))
			{
		        changeColor();
		        changeMessage(); 
		        this.seq=2;
		        
		        
			}
		    
		    
			NetOpen=true;
	}
	catch (Exception ew) {
		
		 JOptionPane.showMessageDialog(null, "��������ʧ��", "δ����", JOptionPane.ERROR_MESSAGE);
		
	}
			
		}
		
		//��������ֹͣ
		if(e.getSource()==this.CloseBackMusic)
		{
			if(backIsOn)
			{
			backMusic.setStop();
			backIsOn=false;
			}
		}
		
		//�������ֿ�
		if(e.getSource()==this.OpenBackMusic)
		{
			if(!backIsOn)
			{
		    backMusic.playMusic();
		    backIsOn=true;
			}
		}
		
		//��Ч��
		
		if(e.getSource()==this.OpenSound)
		{
			isSoundOn = true;
		}
		//��Ч��
		if(e.getSource()==this.CloseSound)
		{
			isSoundOn = false;
			
		}
		
		//�߼�����
		if(e.getSource()==this.ChatOpen)
		{
			this.myChatting.setVisible(true);
		}
		//��ʼ
		if(e.getSource()==this.BeginGame)
		{
		if(NetOpen&&isReady ==false)
		{
			isReady =true;
			this.ChessBoard.isGameOver=false;
			MainChessBoard.fightClient.sendTo("F1");
			if(armyReady&&!MainChessBoard.StartGame)
				{
				StartGame=true;
				JOptionPane.showMessageDialog(null, "" +
						"˫����׼������Ϸ��ʼ", "��ʼ��Ϸ", JOptionPane.INFORMATION_MESSAGE);
				BoardNum++;
				if(BoardNum>1)
				this.ChessBoard.restartGame();
				}
		}
		if(!NetOpen)
		{
			int   option=   JOptionPane.showConfirmDialog( 
	                  null, "�Ƿ����¿��֣�", "���¿��� ",JOptionPane.YES_NO_CANCEL_OPTION); 
	      if(option==JOptionPane.YES_OPTION)
	      {
	    	  BoardNum++;
			if(BoardNum>1)
	    	  this.ChessBoard.restartGame();
	      }
	      
	      else{
	    	  
	      }
		}
		}
		//Ͷ��
		if(e.getSource()==this.Surrender&&NetOpen)
		{
			MainChessBoard.fightClient.sendTo("F2");
			ChessBoardPanel.lost();
			
		}
		//����
		if(e.getSource()==this.Regret&&NetOpen)
		{
			MainChessBoard.fightClient.sendTo("F3");
		}
		
		//���
		if(e.getSource()==this.PeaceRequest&&NetOpen)
		{
			MainChessBoard.fightClient.sendTo("F4");
			
		}
		if(e.getSource()==this.setIP)
		{
			 this.IPAddress  = JOptionPane.showInputDialog("�����������IP��");
			 
		}
		
		

		
		
		
		
	}
	
	

	

	public  void changeColor() {
		// TODO Auto-generated method stub
		ChessColor temp = this.ChessBoard.myColor;
		this.ChessBoard.myColor=this.ChessBoard.ArmyColor;
		this.ChessBoard.ArmyColor= temp;
		
		for(int i=0;i<this.ChessBoard.AllChessPiece.length;i++)
		{
			this.ChessBoard.AllChessPiece[i].changeColor();
		}
		for(int i=0;i<this.ChessBoard.ArmyChessPiece.length;i++)
		{
			this.ChessBoard.ArmyChessPiece[i].changeColor();
		}
	}
	
	public void changeMessage()
	{
		this.myChat.changeMessage();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(true)
		{
			
			
			
			
			
		
			this.ChessBoard.listenToSelect();
			
			
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	

}
