package org.example.tankGame;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AePlayWave {

    private String filename;  // .wav 文件路径
    private Position curPosition;

    enum Position { LEFT, RIGHT, NORMAL }

    public AePlayWave(String wavFile) {
        this.filename = wavFile;
        this.curPosition = Position.NORMAL;  // 默认播放位置为正常
    }

    public AePlayWave(String wavFile, Position p) {
        this.filename = wavFile;
        this.curPosition = p;
    }

    public void play() {
        File soundFile = new File(filename);
        if (!soundFile.exists()) {
            System.err.println("音频文件未找到: " + filename);
            return;
        }

        // 设置音频格式
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
            return;
        }

        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // 调整音量控制
        if (auline.isControlSupported(FloatControl.Type.PAN)) {
            FloatControl pan = (FloatControl) auline.getControl(FloatControl.Type.PAN);
            if (curPosition == Position.RIGHT)
                pan.setValue(1.0f);  // 声音在右声道
            else if (curPosition == Position.LEFT)
                pan.setValue(-1.0f);  // 声音在左声道
        }

        // 播放音频
        auline.start();
        int nBytesRead = 0;
        byte[] abData = new byte[512];

        try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0)
                    auline.write(abData, 0, nBytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            auline.drain();
            auline.close();
        }
    }

    public static void main(String[] args) {
        String filePath = "path_to_your_wav_file.wav";  // 将此路径替换为你的 .wav 文件路径
        AePlayWave player = new AePlayWave(filePath);
        player.play();
    }
}

