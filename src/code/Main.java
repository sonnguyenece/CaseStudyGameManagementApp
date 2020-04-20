package code;

import javax.swing.*;

import swing.Screen;

public class Main {
    public static void main(String[] args) throws Exception {
        CRUDList crudList = new CRUDList();
        Screen screen = new Screen();

        screen.setVisible(true);

        Game supertux2 = new Game("Supertux 2", false, "supertux2", "image/Game Datas/Supertux2/image/icon.jpg", 3f);
//        supertux2.playGame();
//        System.out.println(supertux2.isRunning());
        Game halfLife = new Game("Half Life 2", true, "220", "image/Game Datas/HalfLife2/image/icon.png", 10f);
        Game left4Dead = new Game("Left4Dead 2", true, "550", "image/Game Datas/Left4Dead 2/image/icon.jpg", 5);
        Game dontStarve = new Game("Don't Starve ", true, "322330", "image/Game Datas/Don't Starve Together/image/icon.jpg", 7);

        crudList.addGame(supertux2);
        crudList.addGame(halfLife);
        crudList.addGame(left4Dead);
        crudList.addGame(dontStarve);


        crudList.sortByName();
        screen.setGameList(crudList.getGameList());
        screen.refreshGameList();


    }
}
