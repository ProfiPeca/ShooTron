package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class GameSound {

    Clip soundClip;
    URL audioResourceArray[] = new URL[30];

    public GameSound() {
        audioResourceArray[0] = getClass().getResource("/sound/cold_emptiness.wav");
        audioResourceArray[1] = getClass().getResource("/sound/shoot.wav");
        audioResourceArray[2] = getClass().getResource("/sound/hurt.wav");
        audioResourceArray[3] = getClass().getResource("/sound/pickUp.wav");
        audioResourceArray[4] = getClass().getResource("/sound/labDoorOpen.wav");
    }

    public void soundGetter(int i) {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioResourceArray[i]);
            soundClip = AudioSystem.getClip();
            soundClip.open(audioInputStream);
        } catch (Exception e) {

        }
    }

    public void soundPlay() {
        soundClip.start();
    }

    public void soundStop() {
        soundClip.stop();
    }

    public void soundLoop() {
        soundClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
