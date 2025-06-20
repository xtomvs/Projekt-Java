package org.example;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer {

    public static void playSound(String path) {
        try {
            URL soundURL = SoundPlayer.class.getResource(path);
            if (soundURL == null) return;

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Sound error: " + e.getMessage());
        }
    }

    public static void loopSound(String path) {
        try {
            URL soundURL = SoundPlayer.class.getResource(path);
            if (soundURL == null) return;

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Loop sound error: " + e.getMessage());
        }
    }
}
