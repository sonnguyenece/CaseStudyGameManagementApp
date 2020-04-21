package code;

import javax.swing.*;

import swing.Screen;

public class Main {
    public static void main(String[] args) throws Exception {
        Screen screen = new Screen();
        screen.setVisible(true);

        Game supertux2 = new Game("Supertux 2", false, "supertux2", "image/Game Datas/Supertux2/image/icon.jpg", 3f);
        Game halfLife = new Game("Half Life 2", true, "220", "image/Game Datas/HalfLife2/image/icon.png", 10f);
        Game left4Dead = new Game("Left4Dead 2", true, "550", "image/Game Datas/Left4Dead 2/image/icon.jpg", 5);
        Game dontStarve = new Game("Don't Starve ", true, "322330", "image/Game Datas/Don't Starve Together/image/icon.jpg", 7);

//        supertux2.playGame();

        halfLife.setDescription("<html>Description: Half-Life 2 is a 2004 first-person shooter game developed and published by Valve." +
                "Like the original Half-Life, it combines shooting, puzzles, and storytelling, and adds features" +
                "such as vehicles and physics-based gameplay ... The game was created using Valve's Source engine," +
                "developed alongside."
                + "<br/> Play this game on Steam</html>");

        halfLife.setHeaderImage("image/Game Datas/HalfLife2/image/header.resized.png");
        halfLife.setDeveloper("Valve");
        halfLife.setGameScore(Float.parseFloat("9.6"));
        halfLife.setGameHomepage("Valve.com");

        screen.crudList = new CRUDList();
        screen.getCrudList().addGame(supertux2);
        screen.getCrudList().addGame(halfLife);
        screen.getCrudList().addGame(left4Dead);
        screen.getCrudList().addGame(dontStarve);
        screen.getCrudList().sortByName();
        screen.refreshGameList();
    }
}
