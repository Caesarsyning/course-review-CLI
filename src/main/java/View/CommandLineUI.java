package View;

import Controller.MainController;
import Data_Layer.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class CommandLineUI {
    protected static final int ratingMax = 5;
    protected static MainController mainController;

    protected static boolean returnToMainMenu = false;
    protected static boolean returnToLogin = true;
     static Scanner input;
    public static Student theUser;

    public CommandLineUI() throws SQLException, IOException {
        this.mainController = new MainController();
        this.theUser = new Student();

    }

    public void start() throws SQLException {
        System.out.println("Welcome to the Course Review!");
        System.out.println("Now loading...");
        System.out.println();
        mainController.getTables();
        while (returnToLogin){
            Login.loginMenu();
            while(returnToMainMenu)
                MainMenu.loginMenu();
        }
        input.close();

    }


}
