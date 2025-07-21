package pub2504.gui;

import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

// jlayer 외부라이브러리 사용해서 MP3재생
// https://sourceforge.net/projects/javalayer/
// jl1.0.1.jar

public class MP3Player extends Thread {
	
	public static void main(String[] args) {
		new MP3Player().start();
	}
	
	@Override
	public void run() {
		
		File file = new File("C:\\pub2504\\assets\\sound.mp3");
		
		try {
			
			// 사운드 파일에서 바이트 입력 스트림
			FileInputStream fis = new FileInputStream(file);
			
			// JLarer Player 생성
			Player player = new Player(fis);
			
			// 재생
			player.play();
			
			while (true) {
				System.out.println(player.getPosition());
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

} // class














