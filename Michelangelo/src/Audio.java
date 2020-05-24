import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class Audio {
    private FloatControl gainControl;
    private Clip clip;
    public Audio() {
        try {
            // Open an audio input stream.
            //URL url = this.getClass().getClassLoader().getResource("Cigarette Daydreams - Cage the Elephant.wav");
            URL url = this.getClass().getResource("SoundSystem/Cigarette Daydreams - Cage the Elephant.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            //clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public float getVolume() {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public void setVolume(float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }
}