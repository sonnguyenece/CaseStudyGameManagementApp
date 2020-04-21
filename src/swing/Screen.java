package swing;

import code.CRUDList;
import code.Game;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class Screen extends JFrame {
    public static final int PLAYBUTTON_WIDTH = 180;
    public static final int PLAYBUTTON_HEIGHT = 50;
    private JPanel mainPanel;
    private JButton homeButton;
    private JLabel searchIcon;
    private JButton searchButton;
    private JTextField searchTextField;
    private JButton addButton;
    private JLabel quantityGames;
    private JList gameSmallList;
    private JPanel headerImg;
    private JLabel header;
    private JLabel description;
    private JButton playButton;
    private JPanel specifyJpanel;
    private JLabel lastPlayed;
    private JLabel playTime;
    private JLabel gameScore;
    private JLabel developer;
    private JLabel homepage;
    private DefaultListModel defaultListGameModel;
    public CRUDList crudList;

    public Screen() {
        super("GAME MANAGEMENT");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        specifyJpanel.setVisible(false);
//        specifyJpanel.setPreferredSize(new Dimension(1000, 50));

        defaultListGameModel = new DefaultListModel<>();
        gameSmallList.setModel(defaultListGameModel);
        gameSmallList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                int gameIndex = gameSmallList.getSelectedIndex();
                if (gameIndex >= 0) {
                    specifyJpanel.setVisible(true);
                    Game selectedGame = crudList.getGameList().get(gameIndex);

                    header.setIcon(new ImageIcon(selectedGame.getHeaderImage()));

                    playButton.setPreferredSize(new Dimension(PLAYBUTTON_WIDTH, PLAYBUTTON_HEIGHT));
                    playButton.setIcon(new ImageIcon("image/playIcon.png"));
                    lastPlayed.setText("Last Played : " + " dd/mm/yyyy          ");
                    lastPlayed.setFont(new Font("Arial", Font.ITALIC, 20));
                    playTime.setText("Play Time :" + " Hours            ");
                    playTime.setFont(new Font("Arial", Font.ITALIC, 20));
                    gameScore.setText("Game Score : "+selectedGame.getGameScore());
                    gameScore.setFont(new Font("Arial", Font.PLAIN, 20));
                    developer.setText("Developer: "+selectedGame.getDeveloper()+"       ");
                    developer.setFont(new Font("Arial", Font.PLAIN, 20));
                    homepage.setText("Homepage: "+selectedGame.getGameHomepage());
                    homepage.setFont(new Font("Arial", Font.PLAIN, 20));


                    description.setText(selectedGame.getDescription());
                    description.setFont(new Font("Consolas", Font.ITALIC, 20));
                    description.setForeground(Color.WHITE);

                }
            }
        });
    }

    private void createUIComponents() {
        searchIcon = new JLabel(new ImageIcon("image/searchIcon.png"));
        searchButton = new JButton(new ImageIcon("image/filter.png"));
        homeButton = new JButton(new ImageIcon("image/homeIcon.png"));
        addButton = new JButton(new ImageIcon("image/addIcon.png"));
//        header= new JLabel(new ImageIcon("image/Game Datas/HalfLife2/image/header.resized.png"));
//        description = new JLabel();
//    screenShot1 =new JLabel(new ImageIcon("image/Game Datas/HalfLife2/image/screenshot1.jpg"));
//    screenShot2 =new JLabel(new ImageIcon("image/Game Datas/HalfLife2/image/screenshot2.jpg"));
    }

    public CRUDList getCrudList() {
        return crudList;
    }

    public void setCrudList(CRUDList crudList) {
        this.crudList = crudList;
    }

    public void refreshGameList() {
        defaultListGameModel.removeAllElements();
        defaultListGameModel.clear();
        for (Game g : crudList.getGameList()) {
            defaultListGameModel.addElement(new ImgsNText(g.getName(), new ImageIcon(g.getIconPath())));
        }
        gameSmallList.setCellRenderer(new Renderer());
        gameSmallList.setModel(defaultListGameModel);

        quantityGames.setText("GAMES (" + crudList.getGameList().size() + ")");
    }

}
