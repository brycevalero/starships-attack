package com.brycevalero.starships.sound;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundFX {

	public static void play(String resource) {
		try {
			// This can also be used for system wide resources
			// InputStream soundStream =
			// getClass().getResourceAsStream(resource);

			// Use an stream instead of a file so we can read resources in jar.
			InputStream audioInputStream = Music.class.getResourceAsStream(resource);
			// add buffer for mark/reset support
			InputStream audioBuffered = new BufferedInputStream(audioInputStream);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(audioBuffered);
			// Get a sound clip resource.
			Clip clip = AudioSystem.getClip();
			// Open audio clip and load samples from the audio input stream.
			clip.open(audioIn);
			clip.setFramePosition(0);
			clip.start();

		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

}
