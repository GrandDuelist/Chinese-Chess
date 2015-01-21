package myChessBoard.MainChessBoard;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class Player extends Thread {
	
	String Name;
	int num;
	AudioClip clip = null;
	public Player(String name, int num)
	{
		Name =name;
		this.num=num;
	}
	
	public void run(){
	
	URL cb = null;
	
	File f= new File(Name);
	try {
		cb=f.toURL();
	
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	clip=Applet.newAudioClip(cb);
	if(this.num==1)
	{
	clip.loop();
	}
//	if(this.num==0&&MainChessBoard.isSoundOn)
//	{
//		clip.play();
//		
//	}
	
//	while(this.num==2&&MainChessBoard.isSoundOn)
//	{
//		clip.play();
//		
//		try {
//			Thread.sleep(400);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	}
	
	public void setLoop()
	{
		this.num=1;
	}
	public void setPlay()
	{
		this.num=0;
	}
	public void setStop()
	{
		this.num=-1;
		clip.stop();
		
	}
	public void setSelect()
	{
		this.num=2;
	}
	
	public void playMusic()
	{
		clip.loop();
	}
	public void playSound()
	{
		if(MainChessBoard.isSoundOn)
	    clip.play();
	}
	
	
}
