package agh.cs.lab8.visualisation;

import agh.cs.lab8.maps.Jungle;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserPanel extends JPanel implements ActionListener {

    public Jungle jungle;
    public Simulator simulator;
    JButton button;

    public UserPanel(Jungle jungle, Simulator simulator) {
        setLayout(null);
        this.jungle = jungle;
        this.simulator = simulator;
        button = new JButton("Stop");
        button.addActionListener(this);
        Rectangle r = new Rectangle(0, 0, 300, 60);
        button.setBounds(r);
        add(button,BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize((int) (simulator.frame.getWidth() * 0.25), (int)(simulator.frame.getHeight()*0.5));
        this.setLocation(0, (int)(simulator.frame.getHeight()*0.5));
        int width = this.getWidth();
        int height = this.getHeight(); //38 is toolbar size
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, width, height);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!simulator.timer.isRunning()) {
            button.setText("Stop");
            simulator.timer.start();
        } else {
            simulator.timer.stop();
            button.setText("Start");
        }
    }
}
