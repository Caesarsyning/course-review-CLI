import Controller.MainController;
import Data_Layer.Student;
import Data_Layer.StudentDriver;
import View.CommandLineUI;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * This class is included just to show where your source files go.
 * UVA courses are preloaded, so some code is commentted only for future use or debug
 * @author pm8fc
 *
 */

public class Main {
	public static void main(String[] args) throws SQLException {
		try {
			CommandLineUI commandLineUI = new CommandLineUI();
			commandLineUI.start();
		} catch (SQLException e) {
			System.out.println("SQL is not connected correctly");
		} catch (IOException e) {
			System.out.println("UVA course data is not loaded correctly");
		}


	}
}








