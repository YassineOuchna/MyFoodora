package core;
import java.util.Scanner;
import java.util.NoSuchElementException;
import core.users.User;
import core.users.Customer;
import core.users.Courrier;
import core.users.Manager;
import core.users.Restaurant;

public class MyFoodoraChat {

    private static Scanner sc;
    private static MyFoodora myFoodora;
    private static User currentUser;

    public static void main(String[] args) {
        myFoodora = MyFoodora.getInstance();
        sc = new Scanner(System.in);

        System.out.println("Welcome to MyFoodora application\n");

        String command = "";
        while (!command.equals("close")) {
            System.out.println("To register, enter your type of user in lowercase (restaurant, customer, courier, manager)");
            System.out.println("To login, type 'login'");
            System.out.println("To exit, type 'close'");
            System.out.print("Enter command: ");
            command = sc.nextLine();

            switch (command) {
                case "restaurant":
                case "customer":
                case "courier":
                case "manager":
                    handleRegistration(command);
                    break;
                case "login":
                    handleLogin();
                    break;
                case "close":
                    break;
                default:
                    System.err.println("Unknown command: " + command);
                    break;
            }
        }

        System.out.println("Thank you for using MyFoodora! Goodbye!");
        sc.close();
    }

    private static void handleRegistration(String userType) {
        try {
            System.out.println("Enter userName, password, and address (x,y), separated by commas:");
            String[] data = sc.nextLine().split(",");
            String userName = data[0];
            String password = data[1];
            double[] location = { Double.parseDouble(data[2]), Double.parseDouble(data[3]) };

            switch (userType) {
                case "restaurant":
                    registerRestaurant(userName, password, location);
                    break;
                case "customer":
                    registerCustomer(userName, password, location);
                    break;
                case "courier":
                    registerCourier(userName, password, location);
                    break;
                case "manager":
                    registerManager(userName, password);
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error registering user: " + e.getMessage());
        }
    }

    private static void registerRestaurant(String userName, String password, double[] location) {
        try {
            System.out.println("Enter phone number and email, separated by a comma:");
            String[] phonenmail = sc.nextLine().split(",");
            String phone = phonenmail[0];
            String mail = phonenmail[1];
            Restaurant resto = new Restaurant(userName, password, location);
            resto.setPhoneNumber(phone);
            resto.setEmail(mail);
            myFoodora.addUser(resto);
            System.out.println("Restaurant registered successfully.");
        } catch (Exception e) {
            System.err.println("Error registering restaurant: " + e.getMessage());
        }
    }

    private static void registerCustomer(String userName, String password, double[] location) {
        try {
            System.out.println("Enter name, surname, phone number, and email, separated by commas:");
            String[] customerData = sc.nextLine().split(",");
            String name = customerData[0];
            String surname = customerData[1];
            String phone = customerData[2];
            String mail = customerData[3];
            Customer customer = new Customer(userName, password, name, surname);
            customer.setAddress(location);
            customer.setPhoneNumber(phone);
            customer.setEmail(mail);
            myFoodora.addUser(customer);
            System.out.println("Customer registered successfully.");
        } catch (Exception e) {
            System.err.println("Error registering customer: " + e.getMessage());
        }
    }

    private static void registerCourier(String userName, String password, double[] location) {
        try {
            System.out.println("Enter phone number and email, separated by a comma:");
            String[] phonenmail = sc.nextLine().split(",");
            String phone = phonenmail[0];
            String mail = phonenmail[1];
            Courrier courier = new Courrier(userName, password);
            courier.setPosition(location);
            courier.setEmail(mail);
            courier.setPhoneNumber(phone);
            myFoodora.addUser(courier);
            System.out.println("Courier registered successfully.");
        } catch (Exception e) {
            System.err.println("Error registering courier: " + e.getMessage());
        }
    }

    private static void registerManager(String userName, String password) {
        try {
            System.out.println("Enter phone number and email, separated by a comma:");
            String[] phonenmail = sc.nextLine().split(",");
            String phone = phonenmail[0];
            String mail = phonenmail[1];
            Manager manager = new Manager(userName, password);
            manager.setEmail(mail);
            manager.setPhoneNumber(phone);
            myFoodora.addUser(manager);
            System.out.println("Manager registered successfully.");
        } catch (Exception e) {
            System.err.println("Error registering manager: " + e.getMessage());
        }
    }

    private static void handleLogin() {
        try {
            System.out.println("Enter username and password separated by a comma:");
            String[] data = sc.nextLine().split(",");
            String username = data[0];
            String password = data[1];
            currentUser = myFoodora.login(username, password);
            System.out.println("Logged in successfully as " + currentUser.getUsername());
        } catch (NoSuchElementException e) {
            System.err.println("Invalid login format. Please use: login <username> <password>");
        } catch (Exception e) {
            System.err.println("Login failed: " + e.getMessage());
        }
    }
}
