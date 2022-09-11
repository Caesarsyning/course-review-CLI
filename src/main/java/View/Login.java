package View;

import Data_Layer.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Login extends CommandLineUI{

    public Login() throws SQLException, IOException {
    }

        protected static void loginMenu() throws SQLException {
        printLoginOptions();
        int choice = getSelection();
        processLoginChoice(choice);

    }
    protected static void printLoginOptions(){
        System.out.println();
        System.out.println("Please select from the following: ");
        System.out.println("    1. Login (existing user) ");
        System.out.println("    2. Sign up (create an account) ");
        System.out.println("    3. Quit the system");
    }

   protected static void processLoginChoice(int choice) throws SQLException {
        switch(choice) {
            case 1:
                printLogin();
                break;
            case 2:
                printSignUP();
                break;
            case 3:
                returnToLogin=false;
                break;
            default:
                System.out.println("Selection is not valid, try again!");
        }
    }
    protected static int getSelection() {
        boolean validSelection = false;
        int choice =-1;
        do {
            try {
                input = new Scanner(System.in);
                String arg = input.nextLine().trim();
                choice = Integer.parseInt(arg);
                if (arg.equals("1")||arg.equals("2")) {
                    validSelection = true;
                }
                else if (choice==3){
                    return choice;
                }else {
                    System.out.println("User Error: Must enter a number 1 or 2 or 3.");
                }
            } catch(NumberFormatException e){
                System.out.println("User Error: Must enter a number 1 or 2 or 3.");
            }
            catch (RuntimeException e) {
                System.out.println("User Error: Must enter a number 1 or 2 or 3.");
            }
        } while (!validSelection);
        return choice;
    }
    public static void printLogin() throws SQLException {
        System.out.println("Name(computing ID): ");
            input = new Scanner(System.in);
            String name = input.nextLine().trim();
            if (mainController.computingIDNotExist(name)){
                System.out.println("Computing ID does not exist. Returning back to Login...");
                return;
            }
        System.out.println("Enter Password: ");
            input = new Scanner(System.in);
            String password = input.nextLine().trim();
            if (mainController.passwordExist(name,password)){
              theUser = mainController.getStudent(name);
                System.out.println("Login Successfully! Loading to Main Menu...");
                returnToMainMenu=true;
            }
            else{
                System.out.println("Your password is incorrect. Returning back to Login...");
                return;
            }


    }
    public static void printSignUP() throws SQLException {
        boolean validPassword = false;
        String name = "";
        String password = "";
        System.out.println("1. Enter your new computing ID: ");
            input = new Scanner(System.in);
            name = input.nextLine().trim();
            if (!mainController.computingIDNotExist(name)){
                System.out.println("Computing ID already exists. Try another one. Returning back to Login...");
                return;
            }
            else{
                System.out.println("Computing ID is valid.");
            }
        System.out.println("2. Enter Password: ");
        do {
            input = new Scanner(System.in);
            password = input.nextLine().trim();
            if (password.length() >= 6) {
                validPassword = true;
            } else {
                System.out.println("Password must have at least 6 characters. Try again!");
            }
        }while(!validPassword);
            System.out.println("3. Confirm Password: ");
                input = new Scanner(System.in);
                String confirm = input.nextLine().trim();
                if (confirm.length() >= 6 && confirm.equals(password)) {
                    mainController.addNewUser(name,password);
                    theUser =  mainController.getStudent(name);
                    System.out.println("Signed Up Successfully. Loading to Main Menu...");
                    returnToMainMenu=true;

                }else{
                    System.out.println("Confirm Password do not match.Returning back to Login...");
                    returnToLogin=true;
                }
    }


}
