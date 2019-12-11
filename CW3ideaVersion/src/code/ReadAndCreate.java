/*
Jiyao Zhang
ID: 1822277
 */
package code;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadAndCreate extends JFrame implements Readable {

    private int key;  // "key" defines how many animals an icon represents
    private String animalName;
    private ImageIcon full;  // full icon of the animal
    private ImageIcon half;  // half icon of the animal, represents half key number
    private Map data;  // a Map of animal data; key is country name, value is how much icon wll be added
    private ArrayList mapKeyArray;  // an ArrayList which stores keys of data Map
    private int maxGridWidth;  // I'm going to use GridBadLayout, it defines the width(relative) of this JFrame

    // none parameter constructor
    public ReadAndCreate() {
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public void getImage(String fullPath, String halfPath) {
        this.full = new ImageIcon(fullPath);
        this.half = new ImageIcon(halfPath);
    }

    // Override read() from Readable, enable the class to read file.
    @Override
    public void read(String path) {
        File file = new File(path);
        Map<String, Double> data = new HashMap<>();
        ArrayList<String> keyArray = new ArrayList<>();
        int maxNumber = 0;  // the largest number of icon will a country draws (no matter full or half)
        try {
            // Create a BufferedReader point to the file
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String lineTxt = null;
            int lineNo = 0;  // Current number of line
            // read the .txt file by lines
            while ((lineTxt = br.readLine()) != null) {
                lineNo++;
                // read from the second line (the first line is title)
                if (lineNo > 1) {
                    String[] oneLineData = lineTxt.split(",");
                    keyArray.add(oneLineData[0]);  // store keys of data(Map)
                    // store animal data into HashMap; double value means how much icon will be drawn
                    double number = (double) Integer.parseInt(oneLineData[1]) / this.key;
                    int intNumber = (int) Math.floor(number);
                    if (number - intNumber >= 0.5) {
                        number = intNumber + 0.5;
                    } else {
                        number = intNumber;
                    }
                    data.put(oneLineData[0], (double) number);
                    if (number > maxNumber) {
                        maxNumber = (int) Math.ceil(number);
                    }
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.data = data;
        this.mapKeyArray = keyArray;
        this.maxGridWidth = maxNumber + 2;
    }

    // main method to create frame
    public void create() {
        this.setSize(950, 700);  // set default frame size
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // use GridBagLayout
        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        Font font = new Font("arial", Font.BOLD, 30);
        createHead();
        createBody(font);
        createBottom(font);
        this.setVisible(true);
    }

    // create a JLabel that will be added to this frame
    private JLabel createJLabel(Color background) {
        JLabel jLabel = new JLabel();
        jLabel.setOpaque(true);
        jLabel.setBackground(background);
        return jLabel;
    }

    private int currentLine = 0; // current line number in GridBagLayout to add components
    private void createHead() {
        JLabel head = new JLabel("Numbers of " + this.animalName + "s in the world", JLabel.CENTER);
        head.setFont(new Font("arial", Font.BOLD, 40));
        head.setOpaque(true); // let background color can be seen
        head.setBackground(Color.green);
        // add the component to this JFrame
        this.add(head, new GridBagConstraints(0, this.currentLine, this.maxGridWidth, 1,
                1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        this.currentLine++;

        // draw a white line between countries
        JPanel line = drawLine(this);
        this.add(line, new GridBagConstraints(0, this.currentLine, 0, 1,
                1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        this.currentLine++;
    }

    private void createBody(Font font) {
        int fullNumber; // how much full icon will be added
        // traversal countries
        for (int i = 0; i < this.mapKeyArray.size(); i++) {
            String countryName = (String) this.mapKeyArray.get(i);
            // add names of countries to be the first JLabels of each line
            JLabel country = new JLabel(countryName, JLabel.CENTER);
            country.setFont(font);
            country.setOpaque(true);
            country.setBackground(Color.cyan);
            this.add(country, new GridBagConstraints(0, this.currentLine, 2, 1,
                    1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));

            // draw every icon followed the country name
            double number = (double) this.data.get(countryName);
            // if this country have a half icon to add
            if (number != (double) Math.floor(number)) {
                fullNumber = (int) Math.floor(number);
                for (int j = 0; j < this.maxGridWidth - 2; j++) {
                    if (j < fullNumber) {  // add full icons
                        JLabel fullImg = createJLabel(Color.yellow);
                        fullImg.setSize(new Dimension(80, 80));
                        adaptiveSizeOfImageInJLabel(fullImg, this.full);
                        this.add(fullImg, new GridBagConstraints(j + 2, this.currentLine, 1, 1,
                                1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));

                    } else if (j == fullNumber) {  //  add a half icon
                        JLabel halfImg = createJLabel(Color.yellow);
                        halfImg.setSize(new Dimension(80, 80));
                        adaptiveSizeOfImageInJLabel(halfImg, this.half);
                        this.add(halfImg, new GridBagConstraints(j + 2, this.currentLine, 1, 1,
                                1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));

                    } else {  // add blank JLabel
                        JLabel blank = createJLabel(Color.yellow);
                        this.add(blank, new GridBagConstraints(j + 2, this.currentLine, 0, 1,
                                1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));
                        break;
                    }
                }
            // if this country have no half icons
            } else {
                fullNumber = (int) number;
                for (int j = 0; j < this.maxGridWidth - 2; j++) {
                    if (j < fullNumber) { // add full icons
                        JLabel fullImg = createJLabel(Color.yellow);
                        fullImg.setSize(new Dimension(80, 80));
                        adaptiveSizeOfImageInJLabel(fullImg, this.full);
                        this.add(fullImg, new GridBagConstraints(j + 2, this.currentLine, 1, 1,
                                1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));

                    } else {  // add blank JLabels
                        JLabel blank = createJLabel(Color.yellow);
                        this.add(blank, new GridBagConstraints(j + 2, this.currentLine, 0, 1,
                                1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                                new Insets(0, 0, 0, 0), 0, 0));
                        break;
                    }
                }
            }
            this.currentLine++;
            // draw a line after finishing a country
            JPanel line = drawLine(this);
            this.add(line, new GridBagConstraints(0, this.currentLine, 0, 1, 1, 1,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            this.currentLine++;
        }
    }

    // create bottom part of the frame
    private void createBottom(Font font) {
        // draw a full icon at middle index to show the key
        int middleIndex = (int) Math.floor((double) this.maxGridWidth / 2) - 1;
        for (int i = 0; i < this.maxGridWidth; i++) {
            if (i == middleIndex) {  // add a full icon at the center of bottom
                JLabel fullImg = createJLabel(Color.pink);
                fullImg.setSize(new Dimension(80, 80));
                adaptiveSizeOfImageInJLabel(fullImg, this.full);
                this.add(fullImg, new GridBagConstraints(i, this.currentLine, 1, 1,
                        1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

            } else if (i == middleIndex - 1) {  // write "key" before the full icon
                JLabel text = new JLabel("Key", JLabel.CENTER);
                text.setFont(font);
                text.setOpaque(true);
                text.setBackground(Color.pink);
                this.add(text, new GridBagConstraints(i, this.currentLine, 1, 1,
                        1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

            } else if (i == middleIndex + 1) { // write "=  (this.key) (animalName)s" after the full icon
                JLabel text = new JLabel("=     " + this.key + "    " + this.animalName + "s", JLabel.LEFT);
                text.setFont(font);
                text.setOpaque(true);
                text.setBackground(Color.pink);
                this.add(text, new GridBagConstraints(i, this.currentLine, 0, 1,
                        1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
                break;
            } else { // add blank JLabels
                JLabel blank = createJLabel(Color.pink);
                this.add(blank, new GridBagConstraints(i, this.currentLine, 1, 1,
                        1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));
            }
        }
    }

    // to add a icon into a JLabel, and automatically adapt the size of JLabel
    private void adaptiveSizeOfImageInJLabel(JLabel jLabel, ImageIcon imageIcon) {
        Image img = imageIcon.getImage();
        int width = jLabel.getWidth();
        int height = jLabel.getHeight();
        img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        imageIcon.setImage(img);
        jLabel.setIcon(imageIcon);
    }

    // to create a JPanel to be a white line
    private JPanel drawLine(JFrame jFrame) {
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.white);
        return jPanel;
    }

}
