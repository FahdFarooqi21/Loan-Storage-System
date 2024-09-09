import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
public class XYZBank {

    //List of all customers added.
    private static ArrayList<Customer> customers = new ArrayList<Customer>();

    //Number of records to be added.
    private static int number;

    //Class wide Scanner.
    public static Scanner input = new Scanner(System.in);



    //Method to validate inputs, so I don't have to repeat code.
    public static boolean validateInput(int recordID, String customerID, String type, double interestRate, double amountLeft, int years){
        boolean recordIDValid;
        boolean customerIDValid = false;
        boolean typeValid;
        boolean interestRateValid;
        boolean amountLeftValid;
        boolean yearsValid;

        //check recordID length.
        int recordLen = String.valueOf(recordID).length();
        if(recordLen == 6) {recordIDValid = true;}
        else{
            recordIDValid = false;
            System.out.println("Invalid Record ID length. Must be 6 numbers.");
        }

        //Check customerID length. If that is valid then check format of ID.
        boolean lettersValid = false;
        boolean uppercase;
        boolean letters=false;

        if(customerID.length() == 6) {

            //Check Customer ID (First 3 characters)

            //Check that the letters are capitals.
            if(customerID.substring(0,3).equals(customerID.substring(0,3).toUpperCase())){
                uppercase = true;
            }
            else {
                uppercase=false;
                System.out.println("Invalid Customer ID format. Should be in format AAAXXX.");
            }

            //Check if first 3 letters are characters.
            for(int i=0; i<3; i++ ){
                if (Character.isLetter(customerID.charAt(i))) {letters=true;}

                else{letters=false;
                    System.out.println("Invalid Customer ID format. Should be in format AAAXXX.");
                    break;}
            }

            //Confirm the letters are characters and uppercase.
            if(uppercase && letters) {lettersValid=true;}



            //check customer ID format (Last 3 numbers)
            boolean numbersValid = false;

            for(int i=3; i<6; i++ ){
                if (Character.isDigit(customerID.charAt(i))) {numbersValid=true;}

                else{numbersValid=false;
                    System.out.println("Invalid Customer ID format. Should be in format AAAXXX.");
                    break;}
            }
            if (lettersValid && numbersValid) {customerIDValid=true;}
        }



        else {
            System.out.println("Invalid Customer ID length. Must be 6 Characters");
        }


        //Check the loan type is valid.
        if (type.equalsIgnoreCase("Auto")||
                type.equalsIgnoreCase("Builder")||
                type.equalsIgnoreCase("Mortgage")||
                type.equalsIgnoreCase("Personal")||
                type.equalsIgnoreCase("Other")) {
            typeValid=true;}

        else{
            typeValid=false;
            System.out.println("Invalid Loan Type entered.");
        }


        //Check the interest rate entered is valid.
        if (interestRate>=0) {interestRateValid = true;}

        else {interestRateValid=false;
            System.out.println("Invalid Interest rate. Must be 0 or more");}

        //Check amount of money left that is entered is valid.
        if (amountLeft>=0) {amountLeftValid = true;}

        else {amountLeftValid=false;
            System.out.println("Invalid Amount left. Must be 0 or more");}

        //Check the number of years left on the loan is valid.
        if (years>=0) {yearsValid = true;}

        else {yearsValid=false;
            System.out.println("Invalid number of years left. Must be 0 or more");}


        //Check all information entered is valid and therefore allow the creation of an object with the entered attributes
        return recordIDValid && customerIDValid && typeValid && interestRateValid && amountLeftValid && yearsValid;
    }









    //Method to add a new customer.
    public static void addCustomer(){
        String customerID = null;
        double income = 0;
        boolean customerValid = false;
        boolean incomeValid = false;

        //While loop to keep asking customer ID until a valid one is inputted.
        //Must be in form AAAXXX and must be unique.
        while (!customerValid){
            System.out.println("Please make a Customer ID (AAAXXX)");
            customerID = input.next();
            if (!validateInput(111111, customerID, "Auto", 3, 100, 2)){
                System.out.println("Please try again");
            }
            else{
                //Checks if Customer ID already exists.
                if(!customers.isEmpty()) {
                    for (Customer customer : customers) {
                        if (customer.getCustomerId().equals(customerID)) {
                            System.out.println("Customer ID already exists please try again.");
                            break;
                        }
                        else {
                            System.out.println("Customer ID accepted");
                            customerValid = true;
                        }
                    }
                }
                //ID automatically accepted when customer array is empty.
                else {
                    System.out.println("Customer ID accepted");
                    customerValid = true;
                }

            }
        }

        //Loop to keep asking income until valid is inputted. Must be a number 0 or more.
        while(!incomeValid) {
            System.out.println("Enter your annual Income");
            try{income = input.nextDouble();}
            catch (InputMismatchException e){
                System.out.println("Please enter a number");
                System.out.println("Please try again.");
                input.next();
                continue;

            }
            if(income<0){System.out.println("Income can not be negative. Please try again.");}
            else {System.out.println("Income accepted");
                incomeValid=true;
            }
        }
        //If all inputs are valid, create a new Customer object with the inputs and append to customers arrayList.
        Customer customer1 = new Customer(customerID,income);
        customers.add(customer1);
        menu();
    }


    public static void editCustomer(){
        Customer toChange = null;
        boolean customerFound = false;
        //Ask for Customer ID and check if it matches.
        System.out.println("Enter Customer ID of Customer to edit");
        String oldID = input.next();
        if(!customers.isEmpty()){
            for (Customer customer : customers){
                if (customer.getCustomerId().equals(oldID)){
                    toChange = customer;
                    System.out.println("Customer Found.");
                    customerFound = true;
                    break;
                }
            }
            //If customer not found, return to main menu.
            if (!customerFound){System.out.println("Customer not found."); menu();}

            else {
                //If customer found then ask what to change.
                boolean optionValid = false;
                System.out.println("Would you like to change the Customer ID or Income?");
                System.out.println("(1) Customer ID");
                System.out.println("(2) Income");
                String choice = input.next();
                if (choice.equals("1") || choice.equals("2")) {
                    optionValid = true;
                }
                while (!optionValid) {
                    System.out.println("Invalid option. Pick 1 or 2.");
                    choice = input.next();
                    if (choice.equals("1") || choice.equals("2")) {
                        optionValid = true;
                    }
                }
                //If option 1 chosen, then ask for new ID and validate
                if (choice.equals("1")) {
                    System.out.println("Enter new Customer ID");
                    String newID = input.next();
                    while (!validateInput(111111, newID, "Other", 2, 2, 2)) {
                        System.out.println("Enter Customer ID again.");
                        newID = input.next();
                    }
                    //Use setter method to set new ID
                    toChange.setCustomerId(newID);
                    menu();

                } else {
                    //If option 2 chosen, then ask for new income and validate.
                    System.out.println("Enter new income");
                    double newIncome = input.nextDouble();
                    while (newIncome < 0) {
                        System.out.println("Income must be a positive number.");
                        System.out.println("Try again");
                        newIncome = input.nextDouble();
                    }
                    toChange.setCustomerIncome(newIncome);
                    menu();

                }
            }
        }
        //Message if no customers are added and an edit is attempted.
        else {System.out.println("No customers added"); menu();}
    }









    //Adds record to customer's creditRecord arrayList
    public static void addRecordMain(){
        //Calculate number of records added
        int total = 0;
        for (Customer customer : customers){
            total+= customer.creditRecords.size();
        }
        //If total number of records is less than number then run addRecord.
        if(total<number){
            System.out.println("Would you like to add a custom Record or default Record?");
            System.out.println("(1) Custom");
            System.out.println("(2) Default");
            String choice = input.next();
            Customer chosenCustomer = null;
            //Confirm option entered is valid
            if (choice.equals("1") || choice.equals("2")){
                boolean customerValid = false;
                //Ask for customer ID to locate customer to which record needs to be added to.
                while (!customerValid){
                    System.out.println("Please enter your Customer ID (AAAXXX)");
                    String customerId = input.next();
                    if (!validateInput(111111, customerId, "Auto", 3, 100, 2)){
                        System.out.println("Please try again");
                    }
                    else {
                        boolean customerFound = false;
                        //Check customer ID entered exists
                        if(!customers.isEmpty()) {
                            for (Customer customer : customers) {
                                if (customer.getCustomerId().equals(customerId)) {
                                    System.out.println("Customer found.");
                                    chosenCustomer = customer;
                                    customerValid = true;
                                    customerFound = true;
                                    break;
                                }

                            }
                            if (!customerFound){System.out.println("Customer ID not found");
                                System.out.println("Please enter ID again");
                            }
                        }
                        else {
                            System.out.println("No Customers are added");
                            customerValid = true;
                            menu();
                        }

                    }
                }
                //If choice 1 was chosen, run addRecord method in Customer class.
                if (choice.equals("1")){
                    assert chosenCustomer != null;
                    chosenCustomer.addRecord();
                    menu();
                }

                //If choice 2 chosen then run addDefault method in Customer class.
                else {
                    assert chosenCustomer != null;
                    chosenCustomer.addDefault();
                    menu();
                }


            }

            else{System.out.println("Please choose 1 or 2"); addRecordMain();}
        }
        //Message when too many records are added.
        else {System.out.println("Maximum number of records reached."); menu();}

    }







    //Method to call the printDetails method with the option to print one customer or all.
    public static void printRecord(){
        //Gives user an option to choose between all customer records or one customer's record.
        System.out.println("Would you like to print all customer's records?");
        System.out.println("(1) All customers");
        System.out.println("(2) Choose Customer");
        String choice = input.next();
        //Validation to make sure invalid option isn't chosen.
        while(!choice.equals("1") && !choice.equals("2")){
            System.out.println("Please choose either 1 or 2");
            System.out.println("Would you like to print all customer's records or a specific customer?");
            System.out.println("(1) All customers");
            System.out.println("(2) Choose Customer");
            choice = input.next();
        }
        //Prints all records of all customers.
        if (choice.equals("1")){
            System.out.println();
            System.out.println();
            System.out.println("Maximum number of Records: "+number);
            int total = 0;
            for (Customer customer : customers){
                total+= customer.creditRecords.size();
            }
            System.out.println("Number of records added: "+ total );
            System.out.println();
            System.out.println("==============================================================");
            System.out.println("==============================================================");
            for (Customer customer1 : customers){
                customer1.printDetails();
            }
            menu();
        }
        else {
            //If option 2 chosen then ask for customerID and validate.
            boolean customerIDValid=false;
            boolean customerMatch = false;
            Customer chosenCustomer = null;
            while(!customerIDValid){
                System.out.println("Enter Customer ID of customer: ");
                String customerId = input.next();
                if (!validateInput(111111, customerId, "Auto", 3, 100, 2)){
                    System.out.println("Please try again");
                }
                //Check customerID exists and choose correct customer.
                else {
                    for (Customer customer : customers){
                        if (customer.getCustomerId().equals(customerId)){
                            customerMatch = true;
                            chosenCustomer = customer;
                            break;
                        }
                    }
                    if(customerMatch){customerIDValid=true;}
                    else {System.out.println("Customer not found. Try again");}
                }
            }
            //Print details of chosen customer and return to menu.
            chosenCustomer.printDetails();
            menu();
        }

    }











    //Menu to allow users to enter choices.
    public static void menu(){
        System.out.println("Welcome to XYZBank");
        System.out.println("Choose one of the following options using their number.");
        System.out.println("(1) Add Customer");
        System.out.println("(2) Add Loan Record");
        System.out.println("(3) Edit Customer");
        System.out.println("(4) Print Records");
        System.out.println("(5) Exit");
        String choice = input.next();
        if (choice.equals("1")){addCustomer();}
        else if (choice.equals("2")){addRecordMain();}
        else if (choice.equals("3")){editCustomer();}
        else if (choice.equals("4")){printRecord();}
        else if (choice.equals("5")){System.out.println("Thank you for using XYZBank.");}
        else {System.out.println("Please choose an option between 1 and 5"); menu();}
    }


    public static void main(String[] args){
        //Set the maximum number of records to be added. Validating it is above 0.
        System.out.println("Please enter the number of records you would like to add:");
        boolean numberValid = false;
        while(!numberValid){
            try{number = input.nextInt();}
            catch (InputMismatchException e){System.out.println("Please enter a number"); input.next(); continue;}

            if (number<=0){System.out.println("Please enter a positive number more than 0.");}
            else {numberValid=true;}
        }

        menu();


    }
}