package View;

import Data_Layer.Review;
import Data_Layer.myReview;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainMenu extends CommandLineUI {
    public MainMenu() throws SQLException, IOException{
    }

    protected static void loginMenu() throws SQLException {
        printMenuOptions();
        int choice = getSelection();
        processMenuChoice(choice);

    }

    private static void printMenuOptions() {
        System.out.println();
        System.out.println("Please select from the following: ");
        System.out.println("    1. Submit review for a course");
        System.out.println("    2. See reviews for a course");
        System.out.println("    3. See all of my reviews");
        System.out.println("    4. Download data to a json file");
        System.out.println("    5. Log-out");
    }

    protected static int getSelection() {
        boolean validSelection = false;
        int choice = 0;

        do {
            try {
                input = new Scanner(System.in);
                String arg = input.nextLine().trim();
                choice = Integer.parseInt(arg);
                if (choice >= 1 && choice <= 5) {
                    validSelection = true;
                } else {
                    System.out.println("User Error: Must enter a number 1 to 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("User Error: Must enter a number 1 to 5");
            }
        } while (!validSelection);
        return choice;
    }


    protected static void printSubmitReview() {
        System.out.println("Enter the course you want to review (subject and catalog number,e.g. CS 2100 ): ");
        try {
            input = new Scanner(System.in);
            String course = input.nextLine().trim();
            if (!mainController.courseExist(course) || course.length() < 1) {
                System.out.println("User Error: Enter a correct course name. Return back to Main Menu");
                return;
            }
            else if (mainController.hasTwoReview(theUser, course)) {
                System.out.println("You have already reviewed this course before. Return back to Main Menu");
                return;
            }
            System.out.println("Enter your review comment:  ");
            input = new Scanner(System.in);
            String text = input.nextLine().trim();
            String r;
            do {
                System.out.println("Please enter your rating from 1 to 5:  ");
                r = input.nextLine().trim();
            } while ((!r.equals("1")) && (!r.equals("2")) && (!r.equals("3")) && (!r.equals("4")) && (!r.equals("5")));
            int rating = Integer.parseInt(r);
            mainController.addTextReview(theUser, course, text, rating);

        } catch (RuntimeException e) {
            System.out.println("Input is incorrect. Return back to Main Menu");
        } catch (SQLException e) {
            System.out.println("Input is incorrect. Return back to Main Menu");
        }


    }

    private static void processMenuChoice(int choice) throws SQLException {
        switch (choice) {
            case 1:
                printSubmitReview();
                break;
            case 2:
                printAllofOnecourseReview();
                break;
            case 3:
                printAllmyReviews();
                break;
            case 4:
                DumpJson();
                break;
            case 5:
                returnToMainMenu = false;
                returnToLogin = true;
                break;
            default:
                System.out.println("Selection is not valid, try again!");
        }
    }

    protected static void printAllmyReviews() throws SQLException {
        List<myReview> myReviews = mainController.getAllMyReviews(theUser);
        System.out.println("Here are all your reviews:");
        if (myReviews.isEmpty())System.out.println("You have no reviews yet");
        myReviews.stream()
                .map(mr -> {
                    String out = mr.getSubject() + " " + mr.getCatalogNumber() + ": " + mr.getRating() + "/" + ratingMax
                            + " - " + mr.getText();
                    return out;
                })
                .forEach(System.out::println);


    }


    protected static void printAllofOnecourseReview() throws SQLException {
        System.out.println("Enter the course you want to see all its reviews (subject and catalog number,e.g. CS 2100 ): ");
        try {
            input = new Scanner(System.in);

            String course = input.nextLine().trim();
            if (!mainController.courseExist(course)) {
                System.out.println("User Error: Not a correct course name. Return back to Main Menu");
                return;
            }
            List<String> re = mainController.getOneCourseAllReviews(course);
            System.out.println();
            System.out.println("Here are all the comments of this course:");
            System.out.println();
            if (re.isEmpty()) {
                System.out.println("Oops. This course has no comments yet.");
                return;
            }
            for (String s : re) System.out.println("\""+s+"\"");
            System.out.println("Course Average " + mainController.getAverageRating(course) + "/" + ratingMax);
            System.out.println();
        } catch (RuntimeException e) {
            System.out.println("User Error: Not a correct course name. Return back to Main Menu");
        }
    }

    protected static void DumpJson() throws SQLException{
        System.out.println("Enter your Json file pathname here, e.g. example.json");
        try {
            input = new Scanner(System.in);
            String filename = input.nextLine().trim();
            if (!filename.endsWith(".json")){
                System.out.println("User Error: Not a correct file name. Return back to Main Menu");
                return;
            }
            mainController.addToJson(filename);
        }
        catch(Exception e){
            System.out.println("User Error: Not a correct file name. Return back to Main Menu");
            return;
        }
        System.out.println("Download to json file Successfully!");
        System.out.println("The data should be in the json file you given.");
        System.out.println("If file not found, it creates a json file on the directory you currently at.");
    }
}


