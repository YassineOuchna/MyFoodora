package core;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.NoSuchElementException ;
import java.util.StringTokenizer ;

import core.exceptions.*;
import core.food.*;
import core.orders.*;
import core.policies.*;
import core.users.*;
import core.fidelityCards.*;

import java.util.ArrayList ;
import java.util.Calendar;
import java.util.Date;

public class MyFoodoraClient{


	private static Scanner sc ;
	private static String input ;
	private static StringTokenizer st ;
	private static MyFoodora myFoodora ;
	private static User currentUser ;
	private static Order currentOrder = null ;

	/**
	 * Checks the count in the arguments 
	 * stored in the string tokenizer.  
	 * @param st
	 * @param expectedCount
	 * @return
	 */
	private static boolean checkArgumentsCount(StringTokenizer st, int expectedCount) {
	    return st.countTokens() == expectedCount;
	}

	public static void main(String[] args) throws ItemNotInMenuException {
		myFoodora = MyFoodora.getInstance();

		myFoodora.displayUsers();

		sc = new Scanner(System.in);

		System.out.println("Welcome on the MyFoodora application \n");

		String commande = "" ;
		closeLoop :
			while (!commande.equals("close")){	
				System.out.println("Type \"help <>\" to have a list of all available commands \n");
				input = sc.nextLine();
				st = new StringTokenizer(input) ;
				try{
					commande = st.nextToken() ;
				}catch(NoSuchElementException e){
					commande = "";
				}
				boolean error = false ;
				switch (commande){
				case("registerManager"):
					if (!checkArgumentsCount(st,3)) {
						System.out.println("Not enough arguments!****");
						System.err.println("The command "+ commande +" <name> <username> <password> has only 3 parameters.");
						break;
					}
					String managerName = st.nextToken();
					String managerUserName = st.nextToken();
					String managerPassword = st.nextToken();
					if(st.hasMoreTokens()){	
						System.err.println("The command "+ commande +" <name> <username> <password> has only 3 parameters.");
						error = true ;
					}
					if(!error){
						Manager newManager = new Manager(managerUserName, managerPassword);
						newManager.setName(managerName);
                        myFoodora.addUser(newManager);
						try{
							Manager manager = (Manager) myFoodora.findUserByName(managerName);
							System.out.println("You have been registered. Here is your accounts informations : ") ;
							System.out.println(myFoodora.findUserByName(managerName));
						}catch(UserNotFoundException e){
							System.out.println("Error while creating the " + managerName);
						}
					}
					break ;
				case("registerCourier"):
					if (!checkArgumentsCount(st,4)) {
						System.out.println("Not enough arguments!");
						System.err.println("The command "+ commande +" <name> <username> <password> <address> has only 4 parameters.");
						break;
					}
					String courierName = st.nextToken();
					String courierUserName = st.nextToken();
					String courierPassword = st.nextToken();
					String courierXString = st.nextToken(" ,");
					double courierX = 0;
					String courierYString = st.nextToken(",");
					double courierY = 0;
					try{
						courierX = Double.parseDouble(courierXString) ;
						courierY = Double.parseDouble(courierYString) ;
					}catch(NumberFormatException e){
						System.err.println("The address parameter is invalid you must enter two coordinates (ex : \"1.25,1.45\").");
						error = true ;
					}
					if(st.hasMoreTokens()){	
						System.err.println("The command \"registerCourier <username> <password> <address>\" has only 4 parameters.");
						error = true ;
					}
					if(!error){
						Courier newCourier = new Courier(courierUserName, courierPassword);
                        newCourier.setPosition(new double[]{courierX, courierY});
                        newCourier.setName(courierName);
                        myFoodora.addUser(newCourier);
						try{
							Courier courier = (Courier) MyFoodora.getInstance().findUserByName(courierName);
							System.out.println("You have been registered. Here is your accounts informations : ") ;
							System.out.println(myFoodora.findUserByName(courierName));
						}catch(UserNotFoundException e){
							System.out.println("Error while creating the " + courierName);
						}
					}
					break ;
				case("registerRestaurant"):
					if (!checkArgumentsCount(st,4)) {
						System.out.println("Not enough arguments!");
						System.err.println("The command "+ commande +" <name> <username> <password> <address> has only 4 parameters.");
						break;
					} 
					String restaurantName = st.nextToken();
					String restaurantUserName = st.nextToken();
					String restaurantPassword = st.nextToken();
					String restaurantXString = st.nextToken(" ,");
					double restaurantX = 0;
					String restaurantYString = st.nextToken(",");
					double restaurantY = 0;
					try{
						restaurantX = Double.parseDouble(restaurantXString) ;
						restaurantY = Double.parseDouble(restaurantYString) ;
					}catch(NumberFormatException e){
						System.err.println("The address parameter is invalid you must enter two coordinates (ex : \"1.25,1.45\").");
						error = true ;
					}
					if(st.hasMoreTokens()){	
						System.err.println("The command \"registerRestaurant <name> <username> <password> <address>\" has only 3 parameters.");
						error = true ;
					}
					if(!error){
						Restaurant newRestaurant = new Restaurant(restaurantUserName, restaurantPassword);
                        newRestaurant.setLocation(new double[]{restaurantX, restaurantY});
                        newRestaurant.setName(restaurantName);
                        myFoodora.addUser(newRestaurant);
						try{
							Restaurant restaurant = (Restaurant) MyFoodora.getInstance().findUserByName(restaurantName);
							System.out.println("You have been registered. Here is your accounts informations : ") ;
							System.out.println(myFoodora.findUserByName(restaurantName));
						}catch(UserNotFoundException e){
							System.out.println("Error while creating the " + restaurantName);
						}
					}
					break ;
				case("registerCustomer"):
					if (!checkArgumentsCount(st,7)) {
						System.out.println("Not enough arguments!");
						System.err.println("The command \"registerCustomer <firstName> <lastName> <username> <password> <email> <phone> <address> \" has only 7 parameters.");
						break;
					}
					String customerName = st.nextToken();
					String customerSurname = st.nextToken();
					String customerUserName = st.nextToken();
					String customerPassword = st.nextToken();
					String customerEmail= st.nextToken();
					String customerPhone = st.nextToken();
					String customerXString = st.nextToken(" ,");
					double customerX = 0;
					String customerYString = st.nextToken(",");
					double customerY = 0;
					try{
						customerX = Double.parseDouble(customerXString) ;
						customerY = Double.parseDouble(customerYString) ;
					}catch(NumberFormatException e){
						System.err.println("The address parameter is invalid you must enter two coordinates (ex : \"1.25,1.45\").");
						error = true ;
					}
					if(st.hasMoreTokens()){	
						System.err.println("The command \"registerCustomer <firstName> <lastName> <username> <password> <email> <phone> <address> \" has only 7 parameters.");
						error = true ;
					}
					if(!error){
						Customer newCustomer = new Customer(customerUserName, customerPassword, customerName, customerSurname);
                        newCustomer.setAddress(new double[]{customerX, customerY});
                        newCustomer.setEmail(customerEmail);
                        newCustomer.setPhoneNumber(customerPhone);
                        myFoodora.addUser(newCustomer);
						try{
							((Customer)myFoodora.findUserByName(customerName)).setAddress(new double [] {customerX,customerY});
							System.out.println("You have been registered. Here is your accounts informations : ") ;
							System.out.println(myFoodora.findUserByName(customerName));
						}catch(UserNotFoundException e){
							System.out.println("Error while creating the user.");
						}
						System.out.println("Would you like to be notified of special Offers ? y/n");
						for (int i =0; i<1; i++) {
							input = sc.nextLine();
							st = new StringTokenizer(input) ;
							String answer= "";
							try{
							answer = st.nextToken() ;
							}catch(NoSuchElementException e){
								System.out.println("Asnwer with either y or n");
							}
							if (answer == "y") {
								System.out.println("Notification On");
								newCustomer.setNotificationsOn(true);
								break;
							}
								
						}

					}
					break ;
				case("login"):
					String userType = login() ;
					String workReturn = "next" ;
					if(userType!=null){
						while(workReturn.equals("next")){
							workReturn = work(userType);
						}
						if (workReturn.equals("logout")){
							System.out.println("You have logged out.");
							break ;
						}
					}
					break;
				case ("runTest"):
					st = new StringTokenizer(input);
				    st.nextToken();
					String fileName = "" ;
					try{
						fileName = st.nextToken() ;
						File testScenarioFile = new File(fileName);
						//to read the testScenario file
						sc = new Scanner (testScenarioFile);
						//to write the testScenario file
						OutputStream out = new FileOutputStream(fileName+"Output.txt");
						System.setOut(new PrintStream(out));
					}catch(NoSuchElementException e){
						System.err.println("Invalid number of arguments or syntax error.");
					}catch (FileNotFoundException e){
						System.err.println("File of name \"" + fileName + "\" has not been found");
					}
					break;					
				case("help"):
					if (!checkArgumentsCount(st,0)) {
						System.err.println("Invalid number of arguments or syntax error.");
						
					} else {
					System.out.println("\"registerManager <name> <username> <password>\" : register as manager into the system\n"
							+ "\"login <username> <password>\" : log into the system\n"
							+ "\"runTest <testScenarioFile>\" : execute the list of CLUI commands contained in the testScenario file passed as argument\n"
							+ "\"close<>\" : close MyFoodora");}
					break ;
				case("close"):
					break closeLoop ;
				default:
					System.err.println("The command "+commande+" does not exist.");
					break;
				}
			}
		System.out.println("Thank you for your visit ! \nGoodbye !\n");
		sc.close();
	}

	/**
	 * Method which let the user log in the system
	 * @return the userType of the user.
	 */
	private static String login(){
		String userType = null ;
		try{
			String username = st.nextToken();
			String password = st.nextToken() ;
			currentUser = myFoodora.login(username,password);
			userType = currentUser.getUserType();
			System.out.println("You have successfully logged in the system !\n");
			System.out.println("Welcome "+currentUser.getName());			
			if (currentUser.getClass() == Customer.class) {
					System.out.println("Here are the activated restaurants of the platform :");
					for (Restaurant r : myFoodora.getRestaurants()) {
						if(r.isActive()) {
							System.out.println(r);
						}
						
					}
			}
		}catch(IncorrectIdentificationException e){
			System.out.println("Sorry " + e.getMessage() + "\n"
					+ "Please try again \n");
		}catch(AccountDesactivatedException e){
			System.out.println("Sorry " + e.getMessage() + "\n"
					+ "Your account has been deactivated : Please call a manager : +33745495730 \n");
		}catch(NoSuchElementException e){
			System.err.println("Invalid number of parameters or syntax error.");
		}
		return userType ;
	}

	/**
	 * Let the user use the platform with the commands he can use.
	 * @param userType
	 * @throws ItemNotInMenuException 
	 */
	private static String work(String userType) throws ItemNotInMenuException{		
		System.out.println("Type \"help\" to have a list of all available commands.");
		input = sc.nextLine();
		st = new StringTokenizer(input) ;
		switch (userType){
		case("customer"):
			return workCustomer() ;
		case("courier"):
			return workCourier() ;
		case("restaurant"):
			return workRestaurant() ;
		case("manager"):
			return workManager() ;
		default :
			return null ;
		}		
	}
	
	/**
	 * Works with all the possible commands that a customer can use
	 * @return "next" to go to the next command or "logout" if the customer wanted to logout.
	 * @throws ItemNotInMenuException 
	 */
	private static String workCustomer() throws ItemNotInMenuException{
		Customer currentCustomer = (Customer)currentUser ;
		String commande ;
		boolean error = false ;
		try{
				System.out.println("\"createOrder <restaurantName>\" : create a new order\n"
						+ "\"addItem2Order <itemName>\" : add a dish or a meal to the menu\n"
						+ "\"endOrder <applyReduction> : submit the order to today's date and applies the order depending on the applyReduction value \"yes\" or \"no\"\n"
						+ "\"registerFidelityCard <cardType>\" : associate a new fidelity card (\"basic\", \"point\" or \"lottery\")\n"
						+ "\"displayFidelityInfo <>\" : displays the fidelity info\n"
						+ "\"historyOfOrders <>\" : displays the history of orders\n"
						+"\"logout\" : logout\n") ;
			commande = st.nextToken() ;
			switch (commande){
			case("help"):
				System.out.println("\"createOrder <restaurantName>\" : create a new order\n"
						+ "\"addItem2Order <itemName>\" : add a dish or a meal to the menu\n"
						+ "\"endOrder <applyReduction> : submit the order to today's date and applies the order depending on the applyReduction value \"yes\" or \"no\"\n"
						+ "\"registerFidelityCard <cardType>\" : associate a new fidelity card (\"basic\", \"point\" or \"lottery\")\n"
						+ "\"displayFidelityInfo <>\" : displays the fidelity info\n"
						+ "\"historyOfOrders <>\" : displays the history of orders\n"
						+"\"logout\" : logout\n") ;
				return "next" ;
			case("createOrder"):
				String restaurantName = st.nextToken();
				Restaurant orderedRestaurant = null ;
				try{
					User orderedUser = myFoodora.findUserByName(restaurantName) ;
					if(orderedUser.getUserType().equals("restaurant")){
						orderedRestaurant = (Restaurant)orderedUser ;
					}else{
						System.err.println("The restaurant \""+restaurantName+"\" does not exist.");
						error = true ;
					}
				}catch(UserNotFoundException e){
					System.err.println("The restaurant \""+restaurantName+"\" does not exist.");
					error = true ;
				}
				if(st.hasMoreTokens()){
					System.err.println("Invalid number of parameters or syntax error.");
					error = true ;
				}
				if(!error){
					String orderName=currentCustomer.getName();
					currentOrder= new Order(orderedRestaurant,orderName,currentCustomer);
					System.out.println("A new order has been created. Here is the restaurant's menu :");
					currentOrder.getRestaurant().displayMenu();
				}
				return "next" ;
			case("addItem2Order"):
				String itemName = st.nextToken();
				if(st.hasMoreTokens()){
					System.err.println("Invalid number of parameters or syntax error.");
					error = true ;
				}
				if(currentOrder==null){
					System.err.println("You have not created any order.");
					error = true ;
					return "next" ;
				}
				boolean itemNotExisting = true ;
				String itemType = null ;
				MenuItem orderedDish = null ;
				MenuItem orderedMeal = null ;
				try{
					orderedDish = currentOrder.getRestaurant().getMenu().getItem(itemName) ;
					itemType = "dish" ;
					itemNotExisting = false ;
				}catch(ItemNotInMenuException e){
					
				}
				try{
					orderedMeal = currentOrder.getRestaurant().getMenu().getItem(itemName) ;
					itemType = "meal" ;
					itemNotExisting = false ;
				}catch(ItemNotInMenuException e){
					
				}
				if(itemNotExisting){
					System.err.println("The item \""+itemName+"\" does not exist.");
					error = true ;
				}
				if(!error){
					switch(itemType){
					case("dish"):
						currentOrder.addItem2Order(orderedDish);
						System.out.println("The dish \""+itemName+"\" has been added to your order.");
						break ;
					case("meal"):
						currentOrder.addItem2Order(orderedMeal);
						System.out.println("The meal \""+itemName+"\" has been added to your order.");
						break ;
					}
					System.out.println("The actual price of your order is "+currentOrder.getPrice());
				}
				return "next" ;
			case("endOrder"):
					if(st.hasMoreTokens()){	
						System.err.println("The command \"endOrder\" has 0 parameters.");
						error = true ;
				}
				if(currentOrder==null){
					System.err.println("You have not created any order.");
					error = true ;
					return "next" ;
				}
				if(currentOrder.getPrice()==0){
					System.err.println("You have not ordered anything.");
					error = true ;
				}
				if(!error){
					System.out.println("Here is your order :");
					System.out.println(currentOrder);
						if(currentCustomer.getFidelityCard().getFidelityDiscount()>0){
							System.out.println("You have won "+currentCustomer.getFidelityCard().getFidelityDiscount()+" with your fidelity card.");
						}
					try {
						currentOrder.endOrder();
					} catch (CourierNotFoundException e) {
						System.out.println("All couriers are unavailable at the moment. Try again after a few minutes");
					}
					currentOrder = null ;
				}
				return "next" ;
			case("registerFidelityCard"):
				String cardType = st.nextToken();
				switch(cardType){
				case("basic"):case("point"):case("lottery"):
					break ;
				default :
					System.err.println("The <cardType> parameter must be \"basic\", \"point\" or \"lottery\".");
					error = true ;
				}
				if(st.hasMoreTokens()){	
					System.err.println("The command \"registerFidelityCard <cardType>\" has only 1 parameter.");
					error = true ;
				}
				if(!error && (cardType.equals("basic") || cardType.equals("point") || cardType.equals("lottery"))  ){
					FidelityCard card= null;
					if (cardType.equals("basic")){
						card= new BasicFidelityCard();
					}
					else if (cardType.equals("point")){
						card= new PointFidelityCard();
					}
					else if (cardType.equals("lottery")){
						card= new LotteryFidelityCard();
					}
							
					currentCustomer.registerFidelityCard(card);
					System.out.println("You have now a "+cardType+" fidelity card.");
				}
				return "next" ;
			case("displayFidelityInfo"):
				if(st.hasMoreTokens()){	
					System.err.println("The command \"displayFidelityInfo <>\" cannot have parameters.");
					error = true ;
				}
				if(!error){
					currentCustomer.displayFidelityInfo();
				}
				return "next" ;
			case("historyOfOrders"):
				if(st.hasMoreTokens()){	
					System.err.println("The command \"historyOfOrders <>\" cannot have parameters.");
					error = true ;
				}
				if(!error){
					System.out.println(currentCustomer.getHistoryOfOrders(myFoodora));
				}
				return "next" ;
			case("logout"):
				if(st.hasMoreTokens()){	
					System.err.println("The command \"logout <>\" cannot have parameters.");
					return "next" ;
				}
				return "logout" ;				
			default :
				System.err.println("The command "+commande+" does not exist.");	
			}
			return "next" ;
		}catch(NoSuchElementException e){
			System.err.println("Invalid number of parameters or syntax error.");
			return "next" ;
		}  
	}
	
	/**
	 * Works with all the possible commands that a courier can use
	 * @return "next" to go to the next command or "logout" if the courier wanted to logout.
	 */
	private static String workCourier(){
		Courier currentCourier = (Courier)currentUser ;
		boolean error = false ;
		String commande ;
		try{
				System.out.println("\"onDuty <>\" : set state to on duty\n"
						+ "\"offDuty <>\" : set state to off duty\n"
						+ "\"acceptDeliveryCall <orderID> <answer> : accept (<answer> = \"yes\") or refuse (<answer> = \"no\") the delivery call for the orderID\n"
						+ "\"logout\" : log out\n");
			commande = st.nextToken() ;
			switch (commande){
			case("help"):
				System.out.println("\"onDuty <>\" : set state to on duty\n"
						+ "\"offDuty <>\" : set state to off duty\n"
						+ "\"acceptDeliveryCall <orderID> <answer> : accept (<answer> = \"yes\") or refuse (<answer> = \"no\") the delivery call for the orderID\n"
						+ "\"logout\" : log out\n");
				break;
			case("onDuty"):
				if(st.hasMoreTokens()){	
					System.err.println("The command \"onDuty <>\" cannot have parameters.");
					error = true ;
				}
				if(!error){
					System.out.println("You were "+(currentCourier.isOnDuty()?"on duty.":"off duty."));
					currentCourier.setOnDuty(true);
					System.out.println("You are now "+(currentCourier.isOnDuty()?"on duty.":"off duty."));
				}
				return "next" ;
			case("offDuty"):
				if(st.hasMoreTokens()){	
					System.err.println("The command \"offDuty <>\" cannot have parameters.");
					error = true ;
				}
				if(!error){
					System.out.println("You were "+(currentCourier.isOnDuty()?"on duty.":"off duty."));
					currentCourier.setOnDuty(false);
					System.out.println("You are now "+(currentCourier.isOnDuty()?"on duty.":"off duty."));
				}
				return "next" ;			
			case("logout"):
				if(st.hasMoreTokens()){	
					System.err.println("The command \"offDuty <>\" cannot have parameters.");
					error = true ;
				}
				if(!error){
					return "logout" ;
				}
				return "next" ;	
			default:
				System.out.println("This command is not available, please try again \n");
				break;
			}
			return "next" ;
		}catch(NoSuchElementException e){
			commande = "";
			return "next" ;
		}
	}
	
	/**
	 * Works with all the possible commands that a manager can use
	 * @return "next" to go to the next command or "logout" if the manager wanted to logout.
	 */
	private static String workManager(){
		Manager currentManager = (Manager)currentUser ;
		boolean error = false ;
		String commande ;
		try{
			commande = st.nextToken() ;
			switch (commande){
			case("help"):
				System.out.println("\"registerRestaurant <name> <username> <password> : register a new restaurant\n"
						+ "\"registerCustomer <firstName> <lastName> <username> <password> <address> : register a new customer\n"
						+ "\"registerCourier <firstName> <lastName> <username> <password> <address> : register a new courier\n"
						+ "\"setDeliveryPolicy <delPolicyName>\" : set the delivery policy to \"fastest\" or \"fairOccupation\"\n"
						+ "\"meetTargetProfit <profitInfoName> <targetProfit>\" : show how to set the value of the profit info (\"deliveryCost\", \"serviceFee\" or \"markup\") to meet the target profit.\n"
						+ "\"setProfitInfoValue <profitInfoName> <value>\" : sets the profitInfo (\"deliveryCost\", \"serviceFee\" or \"markup\") to the value\n"
						+ "\"associateCard <userName> <cardType>\" : associate a new fidelity card (\"basic\", \"point\" or \"lottery\" to the user\n"
						+ "\"showCourierDeliveries <>\" : displays all the Courier ordered by the number of delivered orders\n"
						+ "\"showRestaurantTop <>\" : displays all the Restaurants ordered by the number of orders sold\n"
						+ "\"showCustomers <>\" : displays all the Customers\n"
						+ "\"showMenuItem <restaurantName>\" : displays the menu of the restaurant\n"
						+ "\"showTotalProfit <>\" : displays the total profit of MyFoodora.\n"
						+ "\"showTotalProfit <startDate> <endDate>\" : displays the profit between statDate and endDate (format DD/MM/YYYY)\n"
						+ "\"activateUser <username>\" : activates the user\n"
						+ "\"deactivateUser <username>\" : deactivates the user\n"
						+ "\"logout\" : log out"
						);
				break;
				case("registerManager"):
					if (!checkArgumentsCount(st,3)) {
						System.out.println("Not enough arguments!****");
						System.err.println("The command "+ commande +" <name> <username> <password> has only 3 parameters.");
						break;
					}
					String managerName = st.nextToken();
					String managerUserName = st.nextToken();
					String managerPassword = st.nextToken();
					if(st.hasMoreTokens()){	
						System.err.println("The command "+ commande +" <name> <username> <password> has only 3 parameters.");
						error = true ;
					}
					if(!error){
						Manager newManager = new Manager(managerUserName, managerPassword);
						newManager.setName(managerName);
                        myFoodora.addUser(newManager);
						try{
							Manager manager = (Manager) myFoodora.findUserByName(managerName);
							System.out.println("You have been registered. Here is your accounts informations : ") ;
							System.out.println(myFoodora.findUserByName(managerName));
						}catch(UserNotFoundException e){
							System.out.println("Error while creating the " + managerName);
						}
					}
					return "next";
				case("registerCourier"):
					if (!checkArgumentsCount(st,4)) {
						System.out.println("Not enough arguments!");
						System.err.println("The command "+ commande +" <name> <username> <password> <address> has only 3 parameters.");
						break;
					}
					String courierName = st.nextToken();
					String courierUserName = st.nextToken();
					String courierPassword = st.nextToken();
					String courierXString = st.nextToken(" ,");
					double courierX = 0;
					String courierYString = st.nextToken(",");
					double courierY = 0;
					try{
						courierX = Double.parseDouble(courierXString) ;
						courierY = Double.parseDouble(courierYString) ;
					}catch(NumberFormatException e){
						System.err.println("The address parameter is invalid you must enter two coordinates (ex : \"1.25,1.45\").");
						error = true ;
					}
					if(st.hasMoreTokens()){	
						System.err.println("The command \"registerCourier <username> <password> <address>\" has only 3 parameters.");
						error = true ;
					}
					if(!error){
						Courier newCourier = new Courier(courierUserName, courierPassword);
                        newCourier.setPosition(new double[]{courierX, courierY});
                        newCourier.setName(courierName);
                        myFoodora.addUser(newCourier);
						try{
							Courier courier = (Courier) MyFoodora.getInstance().findUserByName(courierName);
							System.out.println("You have been registered. Here is your accounts informations : ") ;
							System.out.println(myFoodora.findUserByName(courierName));
						}catch(UserNotFoundException e){
							System.out.println("Error while creating the " + courierName);
						}
					}
					return "next";
				case("registerRestaurant"):
					if (!checkArgumentsCount(st,3)) {
						System.out.println("Not enough arguments!");
						System.err.println("The command "+ commande +" <name> <username> <password> has only 3 parameters.");
						break;
					} 
					String restaurantName = st.nextToken();
					String restaurantUserName = st.nextToken();
					String restaurantPassword = st.nextToken();
					String restaurantXString = st.nextToken();
					double restaurantX = 0;
					String restaurantYString = st.nextToken();
					double restaurantY = 0;
					try{
						restaurantX = Double.parseDouble(restaurantXString) ;
						restaurantY = Double.parseDouble(restaurantYString) ;
					}catch(NumberFormatException e){
						System.err.println("The address parameter is invalid you must enter two coordinates (ex : \"1.25,1.45\").");
						error = true ;
					}
					if(st.hasMoreTokens()){	
						System.err.println("The command \"registerRestaurant <name> <username> <password> <address>\" has only 3 parameters.");
						error = true ;
					}
					if(!error){
						Restaurant newRestaurant = new Restaurant(restaurantUserName, restaurantPassword);
                        newRestaurant.setLocation(new double[]{restaurantX, restaurantY});
                        newRestaurant.setName(restaurantName);
                        myFoodora.addUser(newRestaurant);
						try{
							Restaurant restaurant = (Restaurant) MyFoodora.getInstance().findUserByName(restaurantName);
							System.out.println("You have been registered. Here is your accounts informations : ") ;
							System.out.println(myFoodora.findUserByName(restaurantName));
						}catch(UserNotFoundException e){
							System.out.println("Error while creating the " + restaurantName);
						}
					}
				return "next" ;
				case("registerCustomer"):
					if (!checkArgumentsCount(st,7)) {
						System.out.println("Not enough arguments!");
						System.err.println("The command \"registerCustomer <firstName> <lastName> <username> <password> <address> <email> <phone>\" has only 7 parameters.");
						break;
					}
					String customerName = st.nextToken();
					String customerSurname = st.nextToken();
					String customerUserName = st.nextToken();
					String customerPassword = st.nextToken();
					String customerXString = st.nextToken(" ,");
					double customerX = 0;
					String customerYString = st.nextToken(",");
					double customerY = 0;
					String customerEmail= st.nextToken();
					String customerPhone = st.nextToken();
					try{
						customerX = Double.parseDouble(customerXString) ;
						customerY = Double.parseDouble(customerYString) ;
					}catch(NumberFormatException e){
						System.err.println("The address parameter is invalid you must enter two coordinates (ex : \"1.25,1.45\").");
						error = true ;
					}
					if(st.hasMoreTokens()){	
						System.err.println("The command \"registerCustomer <firstName> <lastName> <username> <password> <address> <email> <phone>\" has only 7 parameters.");
						error = true ;
					}
					if(!error){
						Customer newCustomer = new Customer(customerUserName, customerPassword, customerName, customerSurname);
                        newCustomer.setAddress(new double[]{customerX, customerY});
                        newCustomer.setEmail(customerEmail);
                        newCustomer.setPhoneNumber(customerPhone);
                        myFoodora.addUser(newCustomer);
						try{
							((Customer)myFoodora.findUserByName(customerName)).setAddress(new double [] {customerX,customerY});
							System.out.println("You have been registered. Here is your accounts informations : ") ;
							System.out.println(myFoodora.findUserByName(customerName));
						}catch(UserNotFoundException e){
							System.out.println("Error while creating the user.");
						}
						System.out.println("Would you like to be notified of special Offers ? y/n");
						for (int i =0; i<1; i++) {
							input = sc.nextLine();
							st = new StringTokenizer(input) ;
							String answer= "";
							try{
							answer = st.nextToken() ;
							}catch(NoSuchElementException e){
								System.out.println("Asnwer with either y or n");
							}
							if (answer == "y") {
								newCustomer.setNotificationsOn(true);
								System.out.println("Notification done");
								break;
							}
								
						}

					}
				return "next" ;
			case("setDeliveryPolicy"):
				String delPolicyName = st.nextToken();
				switch(delPolicyName){
				case("fairOccupation"):case("fastest"):
					break ;
				default :
					System.err.println("The <delPolicyName> parameter must be \"fairOccupation\" or \"fastest\".");
					error = true ;
				}
				if(st.hasMoreTokens()){	
					System.err.println("The command \"setDeliveryPolicy <delPolicyName>\" has only 1 parameter.");
					error = true ;
				}
				if(!error){
					DeliveryPolicy policy=null;
					if (delPolicyName.equals("fairOccupation")) {
						policy= new FairOcuppationDelivery();
					}
					else if (delPolicyName.equals("fastets")) {
						policy= new FastestDelivery();
					}
					
					
					currentManager.setDeliveryPolicy(policy);
					System.out.println("Delivery Policy has been set to : \n" + policy);				}
				return "next" ;
			case("meetTargetProfit"):
				String profitPolicyName = st.nextToken();
				switch(profitPolicyName){
				case("serviceFee"):case("markup"):case("deliveryCost"):
					break ;
				default :
					System.err.println("The <profitInfoName> parameter must be \"deliveryCost\", \"markup\" or \"serviceFee\".");
					error = true ;
				}
				String targetProfitString = st.nextToken();
				double targetProfit = 0 ;
				try{
					targetProfit = Double.parseDouble(targetProfitString) ;
				}catch(NumberFormatException e){
					System.err.println("The <targetProfit> parameter is invalid : you must enter a profit value (ex : \"2142.24\").");
					error = true ;
				}
				if(st.hasMoreTokens()){	
					System.err.println("The command \"meetTargetProfit <profitInfoName> <targetProfit> \" has only 2 parameters.");
					error = true ;
				}
				if(!error){
					try{
						currentManager.meetTargetProfit(targetProfit);
						double value=0;
						if (profitPolicyName.equals("serviceFee")) {
							value=MyFoodora.getInstance().getServiceFee();
						}
						else if (profitPolicyName.equals("markup")) {
							value=MyFoodora.getInstance().getMarkupPercentage();
						}
						else if (profitPolicyName.equals("deliveryCost")) {
							value=MyFoodora.getInstance().getDeliveryCost();
						}
						System.out.println("You can reach a profit of " + targetProfit +" by changing the "+ profitPolicyName +" to "+ value +".");
					}catch(ProfitUnreachableException e){
						System.err.println("It is impossible to reach the profit value "+targetProfit+".");
					}
				}
				return "next" ;
			
			case("associateCard"):
				String customerUsername = st.nextToken();
				String cardType = st.nextToken();
				switch(cardType){
				case("basic"):case("point"):case("lottery"):
					break ;
				default :
					System.err.println("The <cardType> parameter must be \"basic\", \"point\" or \"lottery\".");
					error = true ;
				}
				if(st.hasMoreTokens()){	
					System.err.println("The command \"associateCard <userName> <cardType>\" has only 2 parameters.");
					error = true ;
				}
				if(!error){
					try{
						User fidelUser = myFoodora.findUserByUserName(customerUsername);
						if(fidelUser.getUserType().equals("customer")){
							Customer fidelCustomer = (Customer)fidelUser ;
							fidelCustomer.registerFidelityCard(cardType);
							System.out.println("The user of username \""+customerUsername+"\" has now a "+cardType+" fidelity card.");
						}else{
							System.err.println("The user of username \""+customerUsername+"\" is not a customer.");
						}
					}catch(UserNotFoundException e){
						System.err.println("The user of username \""+customerUsername+"\" does not exist.");
					}
				}
				return "next" ;
			case("showCourierDeliveries"):
				if(st.hasMoreTokens()){	
					System.err.println("The command \"showCourierDeliveries <>\" cannot have parameters.");
					error = true ;
				}
				if(!error){
					System.out.println("Here are the activated couriers of the platform :");
					Courier mostActiveCourier = new Courier("","") ;
					ArrayList<Courier> activatedCourier = new ArrayList<Courier>() ;
					while (mostActiveCourier!=null){
						mostActiveCourier = currentManager.mostActiveCourier();
						if(mostActiveCourier!=null){
						activatedCourier.add(mostActiveCourier);
							currentManager.desactivateUser(mostActiveCourier);						
							System.out.println(mostActiveCourier);
						}else{
							for(Courier i:activatedCourier){
								currentManager.activateUser(i);	
							}
						}
					}
					
				}
				return "next" ;
			case("showRestaurantTop"):
				if(st.hasMoreTokens()){	
					System.err.println("The command \"showRestaurantTop <>\" cannot have parameters.");
					error = true ;
				}
				if(!error){
					System.out.println("Here are the activated restaurants of the platform :");
					Restaurant mostSellingRestaurant = new Restaurant("","") ;
					ArrayList<Restaurant> activatedRestaurants = new ArrayList<Restaurant>() ;
					while (mostSellingRestaurant!=null){
						mostSellingRestaurant = currentManager.mostSellingRestaurant();
						if(mostSellingRestaurant!=null){
						activatedRestaurants.add(mostSellingRestaurant);
							currentManager.desactivateUser(mostSellingRestaurant);						
							System.out.println(mostSellingRestaurant);
						}else{
							for(Restaurant i:activatedRestaurants){
								currentManager.activateUser(i);	
							}
						}
					}
					
				}
				return "next" ;
			case("showCustomers"):
				if(st.hasMoreTokens()){	
					System.err.println("The command \"showCustomers <>\" cannot have parameters.");
					error = true ;
				}
				if(!error){
					System.out.println("Here are the activated customers of the platform :");
					for (User user : myFoodora.getUsers()){
						if((user.getUserType().equals("customer")&&(user.isActive()))){
							System.out.println(user);							
						}
					}
					
				}
				return "next" ;
			case("showMenuItem"):
				restaurantName = st.nextToken();
				if(st.hasMoreTokens()){	
					System.err.println("The command \"showMenuItem <restaurantName>\" has only 1 parameter.");
					error = true ;
				}
				try{
					if(!myFoodora.findUserByName(restaurantName).getUserType().equals("restaurant")){
						System.err.println("The user named \""+restaurantName+"\" is not a restaurant.");
						error = true ;
					}
				}catch(UserNotFoundException e){
					System.err.println("The restaurant named \""+restaurantName+"\" does not exist.");
					error = true ;
				}
				if(!error){
					System.out.println("Here is the menu of the restaurant \""+restaurantName+"\" :");
					try{
						((Restaurant)myFoodora.findUserByName(restaurantName)).displayMenu();
					}catch(UserNotFoundException e){
						System.err.println("The restaurant named \""+restaurantName+"\" does not exist.");
					}
					
				}
				return "next" ;
			case("showTotalProfit"):
				if(st.hasMoreTokens()){	
					int day1 = 0 ; int month1 = 0 ; int year1 = 0 ;
					int day2 = 0 ; int month2 = 0 ; int year2 = 0 ;
					String stringDay1 = st.nextToken("\"/");
					String stringMonth1 = st.nextToken("/");
					String stringYear1 = st.nextToken("\"/");
					String stringDay2 = st.nextToken("\"/");
					String stringMonth2 = st.nextToken("/");
					String stringYear2 = st.nextToken("\"/");
					if(st.hasMoreTokens()){
						System.err.println("The command \"showTotalProfit <startDate> <endDate>\" has only 2 parameters.");
						error = true ;
					}
					try {
					    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

					    System.out.println("Enter start date (DD/MM/YYYY):");
					    String stringDate1 = sc.nextLine();
					    System.out.println("Enter end date (DD/MM/YYYY):");
					    String stringDate2 = sc.nextLine();

					    // Parsing the dates
					    Date date1 = sdf.parse(stringDate1);
					    Date date2 = sdf.parse(stringDate2);

					    Calendar start = Calendar.getInstance();
					    start.setTime(date1);
					    Calendar end = Calendar.getInstance();
					    end.setTime(date2);

					    // Check for invalid date ranges
					    if (start.after(end)) {
					        System.err.println("The start date must be before the end date.");
					        error = true;
					    }

					    if (!error) {
					        System.out.println("The total profit during this period is:");
					        System.out.println(currentManager.getMyFoodora().computeTotalProfit(start.getTime(), end.getTime()));
					    }

					} catch (ParseException e) {
					    System.err.println("Each date parameter must be in format \"DD/MM/YYYY\" (ex: \"28/01/2016\").");
					    error = true;
					}

					if (!error) {
					    Calendar start = Calendar.getInstance();
					    start.set(0, 0, 0);
					    Calendar end = Calendar.getInstance();

					    System.out.println("The total profit since the creation is:");
					    System.out.println(currentManager.getMyFoodora().computeTotalProfit(start.getTime(), end.getTime()));
					}
				return "next" ;}
			case("activateUser"):
				String username = st.nextToken();
				if(st.hasMoreTokens()){	
					System.err.println("The command \"activateUser <userName>\" has only 1 parameter.");
					error = true ;
				}
				if(!error){
					try{
						User user = myFoodora.findUserByUserName(username);
						if(user.isActive()){
							System.err.println("The user \""+username+"\" is already activated.");
						}else{
							System.out.println("The user \""+username+"\" has been activated.");
							user.setActive(true);
						}
					}catch(UserNotFoundException e){
						System.err.println("The user of username \""+username+"\" does not exist.");
					}
				}
				return "next" ;
			case("deactivateUser"):
				username = st.nextToken();
				if(st.hasMoreTokens()){	
					System.err.println("The command \"deactivateUser <userName>\" has only 1 parameter.");
					error = true ;
				}
				if(!error){
					try{
						User user = myFoodora.findUserByUserName(username);
						if(!user.isActive()){
							System.err.println("The user \""+username+"\" is already deactivated.");
						}else{
							System.out.println("The user \""+username+"\" has been deactivated.");
							user.setActive(false);
						}
					}catch(UserNotFoundException e){
						System.err.println("The user of username \""+username+"\" does not exist.");
					}
				}
				return "next" ;
			case("logout"):
				if(st.hasMoreTokens()){	
					System.err.println("The command \"logout <>\" cannot have parameters.");
					error = true ;
				}
				if(!error){
					return "logout" ;
				}
				return "next" ;	
			default :
				System.err.println("The command "+commande+" does not exist.");	
			}
			return "next" ;
		}catch(NoSuchElementException e){
			System.err.println("Invalid number of parameters or syntax error.");
			return "next" ;
		}
	}
	
	/**
	 * Works with all the possible commands that a restaurant can use
	 * @return "next" to go to the next command, "disconnect" or "close".
	 */
	private static String workRestaurant(){
		Restaurant currentRestaurant = (Restaurant)currentUser ;
		// global array with meal description when composing a meal
		ArrayList<String> mealDescription = new ArrayList<String>();
		String commande ;
		boolean error = false ;
		try{
				System.out.println("\"showMenuItem <>\" : display your menu\n"
						+ "\"addDishRestaurantMenu <dishName> <dishCategory> <foodCategory> <containsGluten 0 or 1> <unitPrice>\" : add a new dish to your menu\n"
						+ "\"createMeal <mealName> <mealCategory>\" : creates a new meal with mealname and mealCategory (\"full\" or \"half\")\n"
						+ "\"addDish2Meal <dishName> <mealName>\" : adds a dish to a meal\n"
						+ "\"showMeal <mealName>\" : displays the indicated meal\n"
						+ "\"setSpecialOffer <mealName>\" : sets the meal of the week\n"
						+ "\"setSpecialDiscountFactor <value>\" : sets the special discount factor\n"
						+ "\"setSpecialDiscountFactor <value>\" : sets the generic discount factor\n"
						+ "\"showSortedMeals <> : displays all the meals w.r.t. the number of times they have been picked\n"
						+ "\"showSortedDishes <> : displays all the dishes w.r.t. the number of times they have been picked\n"
						+ "\"logout\" : log out\n");
			commande = st.nextToken() ;
			switch (commande){
			case("help"):
				System.out.println("\"showMenuItem <>\" : display your menu\n"
						+ "\"addDishRestaurantMenu <dishName> <dishCategory> <foodCategory> <containsGluten 0 or 1> <unitPrice>\" : add a new dish to your menu\n"
						+ "\"createMeal <mealName> <mealCategory>\" : creates a new meal with mealname and mealCategory (\"full\" or \"half\")\n"
						+ "\"addDish2Meal <dishName> <mealName>\" : adds a dish to a meal\n"
						+ "\"showMeal <mealName>\" : displays the indicated meal\n"
						+ "\"setSpecialOffer <mealName>\" : sets the meal of the week\n"
						+ "\"setSpecialDiscountFactor <value>\" : sets the special discount factor\n"
						+ "\"setSpecialDiscountFactor <value>\" : sets the generic discount factor\n"
						+ "\"showSortedMeals <> : displays all the meals w.r.t. the number of times they have been picked\n"
						+ "\"showSortedDishes <> : displays all the dishes w.r.t. the number of times they have been picked\n"
						+ "\"logout\" : log out\n");
				return "next" ;
			case("showMenuItem"):
				if(st.hasMoreTokens()){
					if(st.hasMoreTokens()){	
						System.err.println("The command \"showMenuItem <>\" cannot have parameters.");
						error = true ;
					}
				}
				if(!error){
					System.out.println("Here is your menu :");
					currentRestaurant.displayMenu();
				}
				return "next" ;
			case("addDishRestaurantMenu"):
				String dishName = st.nextToken();
				String dishCategory = st.nextToken();
				switch(dishCategory){
				case("starter"):case("mainDish"):case("dessert"):
					break ;
				default :
					System.err.println("The <dishCategory> parameter is wrong. The possible values are \"starter\", \"mainDish\" and \"dessert\".");
					error = true ;
				}
				String foodCategory = st.nextToken();
				switch(foodCategory){
				case("standard"):case("vegetarian"):case("glutenFree"):
					break ;
				default :
					System.err.println("The <foodCategory> parameter is wrong. The possible values are \"standard\", \"vegetarian\".");
					error = true ;			
				}
				String containsGluten = st.nextToken(); 
				int gluten= Integer.valueOf(containsGluten);
				
				switch (gluten) {
				case(0):case(1):
					break;
				default :
					System.err.println("The <contains> parameter is wrong. The possible values are 0 and 1");
				}
					error = true ;			
				
				
				String priceString = st.nextToken();
				double price = 0;
				try{
					price = Double.parseDouble(priceString) ;
				}catch(NumberFormatException e){
					System.err.println("The price parameter is invalid.");
					error = true ;
				}
				if(price<=0){
					System.err.println("The price parameter must be positive.");
					error = true ;
				}
				if(st.hasMoreTokens()){
					System.err.println("Invalid number of parameters or syntax error.");
					error = true ;
				}
				if(!error){
					String[] dishDesc = new String[] {dishName,dishCategory,foodCategory,containsGluten,priceString};
					//String[] mealDesc= new String[] {dishName,dishCategory,foodCategory,containsGluten,priceString};
					try {
						currentRestaurant.addMenuItem("dish", dishDesc);
					} catch (InvalidItemDescription e) {
						System.out.println(e.getMessage());
					}
					System.out.println("The "+foodCategory+" "+dishCategory+" "+dishName+" has been added to your menu for "+price+" euros.");
				}
				return "next" ;
			case("createMeal"):
				String mealName = st.nextToken();
				String mealCategory = st.nextToken();
				switch(mealCategory){
				case("full"):case("half"):
					break ;
				default :
					System.err.println("The <mealCategory> parameter is wrong. The possible values are \"full\" and \"half\" ");
					error = true ;
				}
				if(st.hasMoreTokens()){
					System.err.println("The command \"createMeal <mealName> <mealCategory>\" has only 2 parameters.");
					error = true ;
				}
				if(!error){
					mealDescription.add(mealName);
					mealDescription.add(mealCategory);
					System.out.println("The "+mealCategory+" "+mealName+" has been created and needs "
							+ ((mealCategory == "full") ? ("3 dishes.") : ("2 dishes.")));
					System.out.println("To do so, use \"addDish2Meal <dishName> <mealName> \"");
				}
				return "next" ;
			case("addDish2Meal"):
				String dName = st.nextToken();
				String mName = st.nextToken();
				if(st.hasMoreTokens()){
					System.err.println("Invalid number of parameters or syntax error.");
					error = true ;
				}
				if(!error){
					try{
						// Check if we are creating a new meal 
						// throws index exception
						mealName = mealDescription.get(0);
						
						// Checking if the dish exists 
						currentRestaurant.getMenu().getItem(dName);
						mealDescription.add(dName);
						System.out.println("Here is the updated meal");
						System.out.println("Meal name : " + mealDescription.get(0));
						System.out.println("Size : " + mealDescription.get(1));
						System.out.println("Dishes : " + mealDescription.subList(2, -1));
						if ((mealDescription.get(0) == "full" && mealDescription.subList(2, -1).size() == 3) 
						|| (mealDescription.get(0) == "half" && mealDescription.subList(2, -1).size() == 2)) {
							System.out.println("Meal creation completed !");
							mealDescription.remove(1);
							String[] mealDescReal = new String[mealDescription.size()];
							mealDescReal = (String[]) mealDescription.toArray();
							currentRestaurant.addMenuItem("meal", mealDescReal);
							System.out.println(currentRestaurant.getMenu().getItem(mName));
							mealDescription.clear();
						} else {
							System.out.println("Meal is yet to be complete, add another dish!");
						}
					}catch (InvalidItemDescription e) {
						System.out.println(e.getMessage());
					}
					catch(ItemNotInMenuException e){
						System.err.println("The dish "+dName+" or the meal"+mName+" do not exist in the menu.");
					}
					catch (IndexOutOfBoundsException e) {
						System.out.println("Can't add dish to meal, create the meal first using \"createMeal\"");
					}
				}
				return "next" ;
			case("showMeal"):
				mealName = st.nextToken();
				if(st.hasMoreTokens()){
					System.err.println("Invalid number of parameters or syntax error.");
					error = true ;
				}
				if(!error){
					try{
						System.out.println("Here is the "+mealName+" :");
						System.out.println(currentRestaurant.getMenu().getItem(mealName));
					}
					catch(ItemNotInMenuException e){
						System.err.println("The meal"+mealName+" does not exist.");
					}
				}
				return "next" ;
			case("setSpecialDiscountFactor"):
				String stringDiscountFactor = st.nextToken();
				double discountFactor = 0;
				try{
					discountFactor = Double.parseDouble(stringDiscountFactor) ;
				}catch(NumberFormatException e){
					System.err.println("The <value> parameter is invalid. You must enter a double value.");
					error = true ;
				}
				if(st.hasMoreTokens()){
					System.err.println("The command \"setSpecialDiscountFactor <value>\" has only 1 parameter.");
					error = true  ;
				}
				if(!error){
					currentRestaurant.setSpecialDiscount(discountFactor);
					System.out.println("The discount factor is : "+discountFactor);
				
				}
				return "next" ;
			case("setGenericDiscountFactor"):
				String stringGenericFactor = st.nextToken();
				double genericFactor = 0;
				try{
					genericFactor = Double.parseDouble(stringGenericFactor) ;
				}catch(NumberFormatException e){
					System.err.println("The <value> parameter is invalid. You must enter a double value.");
					error = true ;
				}
				if(st.hasMoreTokens()){
					System.err.println("The command \"setGenericDiscountFactor <value>\" has only 1 parameter.");
					error = true  ;
				}
				if(!error){
					currentRestaurant.setGenericDiscount(genericFactor);
					System.out.println("The generic discount factor is : "+genericFactor);
				
				}
				return "next" ;
			case("setSpecialOffer"):
				mealName = st.nextToken();
				if(st.hasMoreTokens()){
					System.err.println("Invalid number of parameters or syntax error.");
					error = true ;
				}
				if(!error){
					try{
						Meal m = (Meal) currentRestaurant.getMenu().getItem(mealName);
						currentRestaurant.setSpecialOffer(m);
						System.out.println("Here is the new meal of the week :");
						System.out.println(currentRestaurant.getMenu().getSpecialOffer());
					}
					catch(ItemNotInMenuException e){
						System.err.println(e.getMessage());
					}
				}
				return "next" ;
			case("showSortedMenuItems"):
				if(st.hasMoreTokens()){
					System.err.println("The command \"showSortedMeals\" has no parameter.");
					error = true ;
				}
				if(!error){
					
					currentRestaurant.shippedItemSort();
				}
				return "next" ;
			case("logout"):
				if(st.hasMoreTokens()){
					if(st.hasMoreTokens()){	
						System.err.println("The command \"logout <>\" cannot have parameters.");
						return "next" ;
					}
				}
				return "logout" ;				
			default :
				System.err.println("The command "+commande+" does not exist.");	
			}
			return "next" ;
		}catch(NoSuchElementException e){
			System.err.println("Invalid number of parameters or syntax error.");
			return "next" ;
		}
	}
}
