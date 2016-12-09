package ColorPicker;

import Algo.MyVector;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Mike on 12/9/2016.
 */
public class PaletteGUI {

    private JFrame mainFrame;

    private String filePath;
    private List<Color> colors = new ArrayList<>();

    public PaletteGUI(List<MyVector> colors,String path){

        filePath = path;

        for (MyVector vector:colors){
            int r = vector.get(0).intValue();
            int g = vector.get(1).intValue();
            int b = vector.get(2).intValue();
            this.colors.add(new Color(r,g,b));
        }

        prepareGUI();
    }

    private void prepareGUI(){

        int frameWidth = 1000;
        int frameHeight = 300 + 750;

        mainFrame = new JFrame("Palette");
        mainFrame.setSize(frameWidth,frameHeight);

        Container contentPane = mainFrame.getContentPane();

        int numberOfColor = colors.size();
        int panelWidth = frameWidth/numberOfColor;

        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(0,0,frameWidth,750);
        ImageIcon icon = new ImageIcon(filePath);
        imageLabel.setIcon(icon);
        contentPane.add(imageLabel);

        for (int i=0;i<colors.size();i++){
            JPanel panel = new JPanel();
            panel.setBounds(i*panelWidth,300,panelWidth,frameHeight);
            panel.setBackground(colors.get(i));
            contentPane.add(panel);
        }

        mainFrame.setVisible(true);

    }


}
