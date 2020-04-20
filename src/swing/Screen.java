package swing;

import code.Game;

import javax.swing.*;
import java.util.ArrayList;

public class Screen extends JFrame {
    private JPanel mainPanel;
    private JButton homeButton;
    private JLabel searchIcon;
    private JButton searchButton;
    private JTextField searchTextField;
    private JButton addButton;
    private JLabel quantityGames;
    private JList gameSmallList;
    private DefaultListModel defaultListGameModel;
    private ArrayList<Game> gameList;

    public ArrayList<Game> getGameList() {
        return gameList;
    }

    public void setGameList(ArrayList<Game> gameList) {
        this.gameList = gameList;
//        refreshGameList();
    }

    private void createUIComponents() {
        searchIcon = new JLabel(new ImageIcon("image/searchIcon.png"));
        searchButton = new JButton(new ImageIcon("image/filter.png"));
        homeButton = new JButton(new ImageIcon("image/homeIcon.png"));
        addButton = new JButton(new ImageIcon("image/addIcon.png"));

    }

    public Screen() {
        super("GAME MANAGEMENT");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        gameList = new ArrayList<>();
        defaultListGameModel = new DefaultListModel<>();
        gameSmallList.setModel(defaultListGameModel);
    }

    public void refreshGameList() {
        defaultListGameModel.removeAllElements();
        defaultListGameModel.clear();
        for (Game g : gameList) {
            defaultListGameModel.addElement(new ImgsNText(g.getName(), new ImageIcon(g.getIconPath())));
        }
        gameSmallList.setCellRenderer(new Renderer());
        gameSmallList.setModel(defaultListGameModel);

        quantityGames.setText("GAMES ("+gameList.size()+")");
    }

}
