package swing;

import sun.awt.X11.Screen;

import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.*;
import java.util.ArrayList;

public class Interface {
    private JPanel mainPanel;
    private JButton homeButton;
    private JLabel searchIcon;
    private JButton searchButton;
    private JTextField searchTextField;
    private JButton addButton;
    private JLabel allGameNoti;
    private JList gameSmallList;

    private void createUIComponents() {
        searchIcon = new JLabel(new ImageIcon("image/searchIcon.png"));
        searchButton = new JButton(new ImageIcon("image/filter.png"));
        homeButton = new JButton(new ImageIcon("image/homeIcon.png"));
        addButton = new JButton(new ImageIcon("image/addIcon.png"));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GAME MANAGEMENT");
        frame.setContentPane(new Interface().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
