import sun.audio.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.*;

public class SoundClip {

	public static void main(String[] args)  {
		
		JFrame frame = new JFrame();
		frame.setSize(200,200);
		JButton button = new JButton("Play Bird");
		frame.add(button);
		button.addActionListener(new AL());
		frame.setVisible(true);
		
	}
	
	public static class AL implements ActionListener {
		public final void actionPerformed(ActionEvent e) {
			sound();
		}
	}

	public static void sound() {
		AudioPlayer player = AudioPlayer.player;
		AudioStream soundStream;
		AudioData data;
		ContinuousAudioDataStream loop = null;
		
		try {
			soundStream = new AudioStream(new FileInputStream("bird.wav"));
			data = soundStream.getData();
			loop = new ContinuousAudioDataStream(data);
		} catch (IOException error) {}
		
		player.start(loop);
	}
	
}
