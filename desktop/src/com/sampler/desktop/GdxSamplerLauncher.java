package com.sampler.desktop;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.utils.reflect.ClassReflection;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class GdxSamplerLauncher extends JFrame {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    //AWT = Abstract Window Toolkit
    private LwjglAWTCanvas lwjglAWTCanvas;

    public GdxSamplerLauncher() throws HeadlessException {
        setTitle(GdxSamplerLauncher.class.getSimpleName());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // tell window (jframe) to resize and layout our components
        pack();
        setVisible(true);

        launchSample("com.sampler.InputListeningSample");
    }

    //main
    public static void main(String[] args) {
        // must be used to run JFrame properly
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GdxSamplerLauncher();
            }
        });
    }

    private void launchSample(String name) {
        System.out.println("launching sample name = " + name);

        Container container = getContentPane();

        if (lwjglAWTCanvas != null) {
            lwjglAWTCanvas.stop();
            container.remove(lwjglAWTCanvas.getCanvas());
        }

        ApplicationListener sample;
        try {
            //get class object by name
            Class<ApplicationListener> clazz = ClassReflection.forName(name);

            //Create new instance of our sample class
            sample = ClassReflection.newInstance(clazz);

        } catch (Exception e){
            throw new RuntimeException("Cannot create sample with name = "+ name, e);
        }

        lwjglAWTCanvas = new LwjglAWTCanvas(sample);
        lwjglAWTCanvas.getCanvas().setSize(WIDTH,HEIGHT);
        container.add(lwjglAWTCanvas.getCanvas(), BorderLayout.CENTER);

        pack();
    }
}
