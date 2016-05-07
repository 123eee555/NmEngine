package me.nimnon.nmengine.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import sun.applet.AppletAudioClip;

public class SoundUtils {
	
	/**
	 * Loads and returns a Clip object, you can use this to play a sound or music, file must be
	 * a WAV.
	 * 
	 * MP3 and other sound formats are not yet supported
	 * @param path
	 * @return
	 */
	public static Clip playSound(String path) {
		
		AudioInputStream audioStream = null;
		try {
			audioStream = AudioSystem.getAudioInputStream(Class.class.getResource(path));
		} catch (UnsupportedAudioFileException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		AudioFormat format = audioStream.getFormat();
		
		DataLine.Info info = new DataLine.Info(Clip.class, format);
		
		Clip aClip = null;
		try {
			aClip = (Clip) AudioSystem.getLine(info);
			aClip.open(audioStream);
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aClip;
	}
	
	public static AppletAudioClip loadClip(String path) {
		AppletAudioClip clip = new AppletAudioClip(Class.class.getResource(path));
		return clip;
	}

}
