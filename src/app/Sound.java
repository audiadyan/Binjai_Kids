package app;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
    private Clip clip[] = new Clip[2];
    private URL soundURL[] = new URL[11];
    private FloatControl fc;
    private int volume = -4, tmpvolume;

    Sound() {
        soundURL[0] = getClass().getResource("/sounds/bs1.wav");
        soundURL[1] = getClass().getResource("/sounds/bs2.wav");
        soundURL[2] = getClass().getResource("/sounds/bs3.wav");
        soundURL[3] = getClass().getResource("/sounds/bs4.wav");
        soundURL[4] = getClass().getResource("/sounds/punch.wav");
        soundURL[5] = getClass().getResource("/sounds/button.wav");
        soundURL[6] = getClass().getResource("/sounds/keyboard.wav");
        soundURL[7] = getClass().getResource("/sounds/jump.wav");
        soundURL[8] = getClass().getResource("/sounds/error.wav");
        soundURL[9] = getClass().getResource("/sounds/gameover.wav");
        soundURL[10] = getClass().getResource("/sounds/opening.wav");
    }

    public void setFile(int x, int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip[x] = AudioSystem.getClip();
            clip[x].open(ais);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void playBS() {
        fc = (FloatControl)clip[0].getControl(FloatControl.Type.MASTER_GAIN);
        clip[0].start();
        fc.setValue(volume);
    }

    public void play() {
        clip[1].start();
    }

    public void loop() {
        clip[0].loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip[0].stop();
    }

    public void mute() {
        tmpvolume = volume;
        fc.setValue(-14);
    }

    public void unMute() {
        fc.setValue(tmpvolume);
    }

    public void upVolume() {
        volume += 2;
        if(volume > 6) {
            volume = 6;
        }
        fc.setValue(volume);
    }

    public void downVolume() {
        volume -= 2;
        if(volume < -14) {
            volume = -14;
        }
        fc.setValue(volume);
    }
}
