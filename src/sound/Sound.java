package sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Created by E.Batuhan Kaynak on 24.9.2016.
 */
public class Sound {

    public Sound(){

    }

    public void playNumbers(String numbers){
        for (int i = 0; i < numbers.length(); i++){
            char a = (char) (numbers.charAt(i) - '0');
            playSound(findNumberSound(a));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String findNumberSound(int i) {
        String fileName = "";
        if (i == 0){
            fileName = "sound/0.wav";
        } else if (i == 1){
            fileName = "sound/1.wav";
        } else if (i == 2){
            fileName = "sound/2.wav";
        } else if (i == 3){
            fileName = "sound/3.wav";
        } else if (i == 4){
            fileName = "sound/4.wav";
        } else if (i == 5){
            fileName = "sound/5.wav";
        } else if (i == 6){
            fileName = "sound/6.wav";
        } else if (i == 7){
            fileName = "sound/7.wav";
        } else if (i == 8){
            fileName = "sound/8.wav";
        } else if (i == 9){
            fileName = "sound/9.wav";
        }

        return fileName;
    }

    public void playAlo() {
        playSound("sound/alo.wav");
    }

    private void playSound(String sound){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("/" + sound));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Error playing sound.");
        }
    }
}
