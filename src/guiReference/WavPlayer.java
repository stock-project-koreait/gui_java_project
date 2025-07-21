package pub2504.gui;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// wav 사운드 파일 재생

public class WavPlayer extends Thread {
	
	public static void main(String[] args) {
		new WavPlayer().start();
	} // main
	
	@Override
	public void run() {
		File file = new File("C:\\pub2504\\assets\\sound.wav");
		AudioInputStream ais = null; // 오디오 파일 바이트 입력 스트림
		Clip clip = null; // 오디오 파일
		try {
			// 오디오 바이트 입력스트림 생성
			ais = AudioSystem.getAudioInputStream(file);
			// 클립 생성
			clip = AudioSystem.getClip();
			// 클립 오픈
			clip.open(ais);
			// 재생 시작
			clip.start();
			// 재생 완료때까지 대기
			Thread.sleep(clip.getMicrosecondLength() / 1000);
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
	}

} // class



























