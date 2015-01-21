package net.myServer;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.omg.CORBA.portable.OutputStream;

import myChessBoard.MainChessBoard.MainChessBoard;



public class AdvanceChat extends JFrame implements Runnable{

 private static final long serialVersionUID = -2397593626990759111L;

 private JScrollPane scrollPane = null; // 滚动

 private JTextPane text = null; // 不用说了，如果不认识的话就没必要往后看了

 private Box box = null; // 放输入组件的容器

 private JButton b_insert = null, b_remove = null, b_icon = null; // 插入按钮;清除按钮;插入图片按钮

 private JTextField addText = null; // 文字输入框

 private JComboBox fontName = null, fontSize = null, fontStyle = null, fontColor = null,
   fontBackColor = null; // 字体名称;字号大小;文字样式;文字颜色;文字背景颜色

 private StyledDocument doc = null; // 非常重要插入文字样式就靠它了

 //增加一个聊天的同信包
 public myClient MyAdvanceChat =  null;
 public int armyStyle =0;
 public Color armyColor= Color.red;
 public int armySize =12;
 public Color armyBackColor =Color.WHITE;
 public static myClient Control  =null;
 public static int colorNum =1;
 public static int backcolorNum=6;
 
 //Color.white;
public String armyName ="";
 public AdvanceChat() {
  super("好棋友，好基友");
  try { // 使用Windows的界面风格
   UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
  } catch (Exception e) {
   e.printStackTrace();
  }
  text = new JTextPane();
  text.setEditable(false); // 不可录入
  doc = text.getStyledDocument(); // 获得JTextPane的Document
  scrollPane = new JScrollPane(text);
  addText = new JTextField(18);
  String[] str_name = { "宋体", "黑体", "Dialog", "Gulim" };
  String[] str_Size = { "12", "14", "18", "22", "30", "40" };
  String[] str_Style = { "常规", "斜体", "粗体", "粗斜体" };
  String[] str_Color = { "黑色", "红色", "蓝色", "黄色", "绿色" };
  String[] str_BackColor = { "无色", "灰色", "淡红", "淡蓝", "淡黄", "淡绿" };
  fontName = new JComboBox(str_name); // 字体名称
  fontSize = new JComboBox(str_Size); // 字号
  fontStyle = new JComboBox(str_Style); // 样式
  fontColor = new JComboBox(str_Color); // 颜色
  fontBackColor = new JComboBox(str_BackColor); // 背景颜色
  b_insert = new JButton("插入"); // 插入
  b_remove = new JButton("清空"); // 清除
  b_icon = new JButton("图片"); // 插入图片
  b_insert.addActionListener(new ActionListener() { // 插入文字的事件
     public void actionPerformed(ActionEvent e) {
      insert(getFontAttrib());
      addText.setText("");
     }
    });
  b_remove.addActionListener(new ActionListener() { // 清除事件
     public void actionPerformed(ActionEvent e) {
      text.setText("");
     }
    });
  b_icon.addActionListener(new ActionListener() { // 插入图片事件
     public void actionPerformed(ActionEvent arg0) {
      JFileChooser f = new JFileChooser(); // 查找文件
      f.showOpenDialog(null);
      insertIcon(f.getSelectedFile()); // 插入图片
     }
    });
  box = Box.createVerticalBox(); // 竖结构
  Box box_1 = Box.createHorizontalBox(); // 横结构
  Box box_2 = Box.createHorizontalBox(); // 横结构
  box.add(box_1);
  box.add(Box.createVerticalStrut(8)); // 两行的间距
  box.add(box_2);
  box.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8)); // 8个的边距
  // 开始将所需组件加入容器
  box_1.add(new JLabel("字体：")); // 加入标签
  box_1.add(fontName); // 加入组件
  box_1.add(Box.createHorizontalStrut(8)); // 间距
  box_1.add(new JLabel("样式："));
  box_1.add(fontStyle);
  box_1.add(Box.createHorizontalStrut(8));
  box_1.add(new JLabel("字号："));
  box_1.add(fontSize);
  box_1.add(Box.createHorizontalStrut(8));
  box_1.add(new JLabel("颜色："));
  box_1.add(fontColor);
  box_1.add(Box.createHorizontalStrut(8));
  box_1.add(new JLabel("背景："));
  box_1.add(fontBackColor);
  box_1.add(Box.createHorizontalStrut(8));
  box_1.add(b_icon);
  box_2.add(addText);
  box_2.add(Box.createHorizontalStrut(8));
  box_2.add(b_insert);
  box_2.add(Box.createHorizontalStrut(8));
  box_2.add(b_remove);
  this.getRootPane().setDefaultButton(b_insert); // 默认回车按钮
  this.getContentPane().add(scrollPane);
  this.getContentPane().add(box, BorderLayout.SOUTH);
//  this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
  this.setSize(600, 400);
  this.setLocationRelativeTo(null);
  this.setVisible(false);
  addText.requestFocus();
//  this.addWindowListener(new WindowAdapter(){
//	  public void WindowClosing(WindowEvent e){
//	//	  JOptionPane.showMessageDialog(null, "正在关闭！");
//		  System.exit(0);
//	  }
//  });
 }

 /**
  * 插入图片
  * 
  * @param icon
  */
 private void insertIcon(File file) {
  text.setCaretPosition(doc.getLength()); // 设置插入位置
  text.insertIcon(new ImageIcon(file.getPath())); // 插入图片
  insert(new FontAttrib()); // 这样做可以换行
 }

 /**
  * 将文本插入JTextPane
  * 
  * @param attrib
  */
 private void insert(FontAttrib attrib) {
  try { // 插入文本
	  
	  String str=attrib.getText() ;
   doc.insertString(doc.getLength(), "me："+str + "\n", attrib.getAttrSet());
   scrollPane.getVerticalScrollBar().setValue( scrollPane.getVerticalScrollBar().getMaximum());
   if(MainChessBoard.NetOpen)
   this.MyAdvanceChat.sendTo(str);
  } catch (BadLocationException e) {
   e.printStackTrace();
  }
 }

 /**
  * 获取所需要的文字设置
  * 
  * @return FontAttrib
  */
 private FontAttrib getFontAttrib() {
  FontAttrib att = new FontAttrib();
  att.setText(addText.getText());
  att.setName((String) fontName.getSelectedItem());
  att.setSize(Integer.parseInt((String) fontSize.getSelectedItem()));
  String temp_style = (String) fontStyle.getSelectedItem();
  if (temp_style.equals("常规")) {
   att.setStyle(FontAttrib.GENERAL);
  } else if (temp_style.equals("粗体")) {
   att.setStyle(FontAttrib.BOLD);
  } else if (temp_style.equals("斜体")) {
   att.setStyle(FontAttrib.ITALIC);
  } else if (temp_style.equals("粗斜体")) {
   att.setStyle(FontAttrib.BOLD_ITALIC);
  }
  String temp_color = (String) fontColor.getSelectedItem();
  if (temp_color.equals("黑色")) {
   att.setColor(new Color(0, 0, 0));
   AdvanceChat.colorNum=1;
  } else if (temp_color.equals("红色")) {
   att.setColor(new Color(255, 0, 0));
   AdvanceChat.colorNum=2;
  } else if (temp_color.equals("蓝色")) {
   att.setColor(new Color(0, 0, 255));
   AdvanceChat.colorNum=3;
  } else if (temp_color.equals("黄色")) {
   att.setColor(new Color(255, 255, 0));
   AdvanceChat.colorNum=4;
  } else if (temp_color.equals("绿色")) {
   att.setColor(new Color(0, 255, 0));
   AdvanceChat.colorNum=5;
  }
  String temp_backColor = (String) fontBackColor.getSelectedItem();
  if (!temp_backColor.equals("无色")) {
	  AdvanceChat.backcolorNum=6;
   if (temp_backColor.equals("灰色")) {
	   AdvanceChat.backcolorNum=7;
    att.setBackColor(new Color(200, 200, 200));
   } else if (temp_backColor.equals("淡红")) {
    att.setBackColor(new Color(255, 200, 200));
    AdvanceChat.backcolorNum=8;
   } else if (temp_backColor.equals("淡蓝")) {
    att.setBackColor(new Color(200, 200, 255));
    AdvanceChat.backcolorNum=9;
   } else if (temp_backColor.equals("淡黄")) {
    att.setBackColor(new Color(255, 255, 200));
    AdvanceChat.backcolorNum=10;
   } else if (temp_backColor.equals("淡绿")) {
    att.setBackColor(new Color(200, 255, 200));
    AdvanceChat.backcolorNum=11;
   }
  }
  return att;
 }

// public static void main(String args[]) {
//  new ToolTip();
// }

 /**
  * 字体的属性类
  */
 private class FontAttrib{
  public static final int GENERAL = 0; // 常规

  public static final int BOLD = 1; // 粗体

  public static final int ITALIC = 2; // 斜体

  public static final int BOLD_ITALIC = 3; // 粗斜体

  private SimpleAttributeSet attrSet = null; // 属性集

  private String text = null, name = null; // 要输入的文本和字体名称

  private int style = 0, size = 0; // 样式和字号

  private Color color = null, backColor = null; // 文字颜色和背景颜色

  /**
   * 一个空的构造（可当做换行使用）
   */
  public FontAttrib() {
  }

  /**
   * 返回属性集
   * 
   * @return
   */
  public SimpleAttributeSet getAttrSet() {
   attrSet = new SimpleAttributeSet();
   if (name != null)
    StyleConstants.setFontFamily(attrSet, name);
   if (style == FontAttrib.GENERAL) {
    StyleConstants.setBold(attrSet, false);
    StyleConstants.setItalic(attrSet, false);
   } else if (style == FontAttrib.BOLD) {
    StyleConstants.setBold(attrSet, true);
    StyleConstants.setItalic(attrSet, false);
   } else if (style == FontAttrib.ITALIC) {
    StyleConstants.setBold(attrSet, false);
    StyleConstants.setItalic(attrSet, true);
   } else if (style == FontAttrib.BOLD_ITALIC) {
    StyleConstants.setBold(attrSet, true);
    StyleConstants.setItalic(attrSet, true);
   }
   StyleConstants.setFontSize(attrSet, size);
   if (color != null)
    StyleConstants.setForeground(attrSet, color);
   if (backColor != null)
    StyleConstants.setBackground(attrSet, backColor);
   if(MainChessBoard.NetOpen)
   {
	   AdvanceChat.Control.sendTo("T"+style+"Z"+size+"C"+AdvanceChat.colorNum+"B"+AdvanceChat.backcolorNum);
	   
//	   AdvanceChat.Control.sendTo("T"+style);
//	   
//	   AdvanceChat.Control.sendTo("Z"+size);
//	   
//	   AdvanceChat.Control.sendTo("C"+AdvanceChat.colorNum);
//	   //AdvanceChat.changColorToInt(color));
//	   
//	   
//	   AdvanceChat.Control.sendTo("B"+AdvanceChat.backcolorNum);
//			   //AdvanceChat.changColorToInt(backColor));
   }
   return attrSet;
  }

  /**
   * 设置属性集
   * 
   * @param attrSet
   */
  public void setAttrSet(SimpleAttributeSet attrSet) {
   this.attrSet = attrSet;
  }

  /* 后面的注释就不写了，一看就明白 */

  public String getText() {
   return text;
  }

  public void setText(String text) {
   this.text = text;
  }

  public Color getColor() {
   return color;
  }

  public void setColor(Color color) {
   this.color = color;
  }

  public Color getBackColor() {
   return backColor;
  }

  public void setBackColor(Color backColor) {
   this.backColor = backColor;
  }

  public String getName() {
   return name;
  }

  public void setName(String name) {
   this.name = name;
  }

  public int getSize() {
   return size;
  }

  public void setSize(int size) {
   this.size = size;
  }

  public int getStyle() {
   return style;
  }

  public void setStyle(int style) {
   this.style = style;
  }
 }

@Override
public void run() {
	// TODO Auto-generated method stub
	
	
		
		while(true)
		{
			
			if(MainChessBoard.NetOpen)
			{
			
			this.listenToFont();
			
			
			 
			 try { // 插入文本
				 
				 String str =  this.MyAdvanceChat.readCommand();
				 SimpleAttributeSet Set= this.getArmySet();
				
				   doc.insertString(doc.getLength(), "基佬："+str+ "\n", Set);
				   
				  
				  } catch (BadLocationException e) {
				   e.printStackTrace();
				  }
		}
			
			 scrollPane.getVerticalScrollBar().setValue( scrollPane.getVerticalScrollBar().getMaximum());
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
}

public SimpleAttributeSet getArmySet()
{
	SimpleAttributeSet army=new SimpleAttributeSet();
	if (armyName != null)
	    StyleConstants.setFontFamily(army,armyName);
	   if (armyStyle == FontAttrib.GENERAL) {
	    StyleConstants.setBold(army, false);
	    StyleConstants.setItalic(army, false);
	   } else if (armyStyle == FontAttrib.BOLD) {
	    StyleConstants.setBold(army, true);
	    StyleConstants.setItalic(army, false);
	   } else if (armyStyle== FontAttrib.ITALIC) {
	    StyleConstants.setBold(army, false);
	    StyleConstants.setItalic(army, true);
	   } else if (armyStyle == FontAttrib.BOLD_ITALIC) {
	    StyleConstants.setBold(army, true);
	    StyleConstants.setItalic(army, true);
	   }
	   StyleConstants.setFontSize(army, armySize);
	   if (armyColor != null)
	    StyleConstants.setForeground(army, armyColor );
	   if (armyBackColor != null)
	    StyleConstants.setBackground(army, armyBackColor);
	
	return army;
	}

public void listenToFont()
{
	String temp ="";
	////if(this.myColor!=this.PresentTurnColor)
	//{
	if(MainChessBoard.NetOpen)
	{
		temp=AdvanceChat.Control.readCommand();
		if(!temp.equals(" "))
		{
			this.decodeInstruction(temp);
		}
		
	}
	


}

public void decodeInstruction(String temp)
{
	//char In = myClient.getChar(temp);
	
	armyStyle=myClient.getStyle(temp);
	armySize=myClient.getSize(temp);
	armyColor=AdvanceChat.changeIntToColor(myClient.getColor(temp));
	armyBackColor=AdvanceChat.changeIntToColor(myClient.getBackColor(temp));
	
//	if(In=='N')
//	{
//		armyName = myClient.getName(temp);
//		
//	}
//	if(In=='T')
//	{
//		armyStyle = myClient.getNum(temp);
//	}
//	
//	if(In=='Z')
//	{
//		armySize = myClient.getNum(temp);
//	}
//	if(In=='C')
//	{
//		armyColor=  AdvanceChat.changeIntToColor(myClient.getNum(temp));
//	}
//	if(In=='B')
//	{
//		armyBackColor = AdvanceChat.changeIntToColor(myClient.getNum(temp));
//	}
}


public static int changColorToInt(Color color)
{
	
	
	int b=0;
	//黄色
	if(color==new Color(255, 255, 0))
	{
		b=4;
	}else{
		
      //黑色
		if(color==new Color(0, 0, 0))
			b=1;
		//红色
		if(color==new Color(255, 0, 0))
			b=2;
		//蓝色
		if(color==new Color(0, 0, 255))
			b=3;
		//绿色
		if(color==new Color(0, 255, 0))
			b=5;
		
		if(color==Color.WHITE)b=6;
		//灰色
		if(color==new Color(200, 200, 200))b=7;
		//淡红
		if(color==new Color(255, 200, 200))b=8;
		//蛋黄
		if(color==new Color(255, 255, 200))b=9;
		//淡蓝
		if(color==new Color(200, 200, 255))b=10;
		//淡绿
		if(color==new Color(200, 255, 200))b=11;
		
	}	
	
	
	return b;	
}

public static Color changeIntToColor(int b)
{
	Color color = Color.GREEN;
	
	///黑色
	if(b==1) color=new Color(0, 0, 0);
	//红色
	if(b==2) color=new Color(255, 0, 0);
	//蓝色
	if(b==3) color=new Color(0, 0, 255);
	//黄色
	if(b==4) color=new Color(255, 255, 0);
	//绿色
	if(b==5) color=new Color(0, 255, 0);
	//白色
	if(b==6) color=Color.WHITE;
	//灰色
	if(b==7) color=new Color(200, 200, 200);
	//淡红
	if(b==8) color=new Color(255, 200, 200);
	//淡黄
	if(b==10) color=new Color(255, 255, 200);
	//淡蓝
	if(b==9) color=new Color(200, 200, 255);
	//淡绿
	if(b==11) color=new Color(200, 255, 200);
	
	
	
	return color;
	
}
}