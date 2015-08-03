package com.fogthecatman.main;

/*

 * This class will deal with playing sounds from chat commands
 * 
 * 
 */

import java.io.File;
import java.io.IOException;
 


import java.lang.reflect.InvocationTargetException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
 
public class Sounds implements LineListener {
     
        private Clip clip;
       
        public void playSong(String song) throws IOException, InvocationTargetException, InterruptedException 
        {
            try {
                    File f = new File("res/songs/" + song);
                    Line.Info linfo = new Line.Info(Clip.class);
                    Line line = AudioSystem.getLine(linfo);
                    clip = (Clip) line;
                    clip.addLineListener(this);
                    AudioInputStream ais = AudioSystem.getAudioInputStream(f);
                    clip.open(ais);
                    clip.start();
            } catch(Throwable t) {
                    t.printStackTrace();
            }
        }
       
        @Override
        public void update(LineEvent event) {
                LineEvent.Type type = event.getType();
                if(type == LineEvent.Type.OPEN) {
                        //System.out.println("OPEN");
                } else if(type == LineEvent.Type.CLOSE) {
                       // System.out.println("CLOSE");
                } else if(type == LineEvent.Type.START) {
                       // System.out.println("START");
                } else if(type == LineEvent.Type.STOP) {
                        System.out.println("CLIP STOPPED");
                        clip.close();
                }
        }
}