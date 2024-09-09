import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
public class Customer implements CheckerPrinter{

    public static Scanner input = new Scanner(System.in);
    private String customerId;
    private double customerIncome;
    private boolean eligibilityStat;

    //Array of Loan records belonging to each Customer
    ArrayList<Loan> creditRecords = new ArrayList<Loan>();





    //Method to validate inputs.
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
            System.out.println("Invalid Loan Type entered. Choose from: Auto, Builder, Personal, Mortgage or Other");
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






    //Constructor for Customer.
    public Customer(String customerId, double customerIncome){
        this.customerId = customerId;
        this.customerIncome = customerIncome;
    }




    //Getter methods for Customer ID, Income and eligibility.
    public String getCustomerId() {return customerId;}
    public double getCustomerIncome() {return customerIncome;}
    public boolean getEligibility(){return eligibilityStat;}



    //Setter methods for Customer ID and Income.
    public void setCustomerId(String customerId) {this.customerId = customerId;}
    public void setCustomerIncome(double customerIncome) {this.customerIncome = customerIncome;}




    //Inherited interface method to check for eligibility to add records.
    @Override
    public void checkEligibility() {
        double total = 0;
        for (Loan loan : creditRecords){
            total += loan.getAmountLeft();}
        eligibilityStat = !(total>4*customerIncome);
    }




    //Method to create and add a record to the Array of records for the Customer.
    public void addRecord() {
        //Check customer is eligible to add records.
        this.checkEligibility();
        if (eligibilityStat) {
            int recordID = 0;
            String type = null;
            double intRate = 0;
            int term = 0;
            double amountLeft = 0;
            double overpayment = 0;

            boolean recordValid = false;
            boolean typeValid = false;
            boolean intRateValid = false;
            boolean termValid = false;
            boolean amountLeftValid = false;
            boolean overpaymentValid = false;
            boolean sameRecord=false;

            //While loop that keeps asking for record ID until a valid one is inputted.
            while (!recordValid) {
                System.out.println("Enter a Record ID (XXXXXX)");
                //Makes sure record ID entered is a number and is unique.
                try {
                    recordID = input.nextInt();
                    if(!this.creditRecords.isEmpty()) {
                        for (Loan loans : this.creditRecords) {
                            if (recordID == loans.getRecordId()){
                                System.out.println("Record ID must be unique to all records" );
                                sameRecord = true;
                                break;
                            }
                        }
                        if (sameRecord){sameRecord=false; continue;}
                    }
                //Make sure record ID is a number entered.
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a 6 digit number");
                    System.out.println("Please try again.");
                    input.next();
                    continue;
                }
                if (!validateInput(recordID, "ABC123", "Auto", 2.0, 200.00, 2)) {
                    System.out.println("Please try again.");
                }

                else {
                    System.out.println("Record ID accepted");
                    recordValid = true;
                }
            }

            //While loop which keeps asking for loan type until valid one is entered.
            while (!typeValid) {
                System.out.println("Enter a Loan type (Auto, Builder, Personal, Mortgage, Other)");
                type = input.next();

                if (!validateInput(111111, "ABC123", type, 2, 200, 2)) {
                    System.out.println("Please try again.");
                } else {
                    System.out.println("Loan Type accepted");
                    typeValid = true;
                }
            }

            //While loop to ask and validate interest rate.
            while (!intRateValid) {
                System.out.println("Enter an Interest Rate");
                try {
                    intRate = input.nextDouble();
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a number");
                    System.out.println("Please try again.");
                    input.next();
                    continue;
                }
                if (!validateInput(111111, "ABC123", "Auto", intRate, 200, 2)) {
                    System.out.println("Please try again.");
                } else {
                    System.out.println("Interest Rate accepted");
                    intRateValid = true;
                }
            }

            //Loop that asks and validates loan term.
            while (!termValid) {
                System.out.println("Enter the loan term");
                try {
                    term = input.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a number");
                    System.out.println("Please try again.");
                    input.next();
                    continue;
                }
                if (!validateInput(111111, "ABC123", "Auto", 2, 200, term)) {
                    System.out.println("Please try again.");
                } else {
                    System.out.println("Loan term accepted");
                    termValid = true;
                }
            }

            //Loop to ask and validate amount left on loan.
            while (!amountLeftValid) {
                System.out.println("Enter amount left on Loan");
                try {
                    amountLeft = input.nextDouble();
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a number");
                    System.out.println("Please try again.");
                    input.next();
                    continue;
                }
                if (!validateInput(111111, "ABC123", "Auto", 2, amountLeft, 2)) {
                    System.out.println("Please try again.");
                } else {
                    System.out.println("Amount Left accepted");
                    amountLeftValid = true;
                }
            }

            //Checks if the loan type is Builder or Mortgage as these have an extra attribute to set (overpayment)
            if (type.equalsIgnoreCase("Builder") || type.equalsIgnoreCase("Mortgage")) {
                while (!overpaymentValid) {
                    System.out.println("Enter an overpayment amount");
                    try {
                        overpayment = input.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a number");
                        System.out.println("Please try again.");
                        input.next();
                        continue;
                    }
                    if (overpayment < 0 || overpayment > 2) {
                        System.out.println("Overpayment must be between 0 and 2.");
                        System.out.println("Please try again.");
                    } else {
                        System.out.println("Overpayment amount accepted");
                        overpaymentValid = true;
                    }
                }
            }

            //Once all inputs are entered it creates the corresponding loan record and adds to records array.
            switch (type.toUpperCase()) {
                case "AUTO":
                    AutoLoan auto = new AutoLoan(recordID, intRate, term, amountLeft);
                    this.creditRecords.add(auto);
                    System.out.println("Loan record added");
                    break;
                case "BUILDER":
                    BuilderLoan build = new BuilderLoan(recordID, intRate, term, overpayment, amountLeft);
                    this.creditRecords.add(build);
                    System.out.println("Loan record added");
                    break;
                case "MORTGAGE":
                    MortgageLoan mortgage = new MortgageLoan(recordID, intRate, term, overpayment, amountLeft);
                    this.creditRecords.add(mortgage);
                    System.out.println("Loan record added");
                    break;
                case "PERSONAL":
                    PersonalLoan personal = new PersonalLoan(recordID, intRate, term, amountLeft);
                    this.creditRecords.add(personal);
                    System.out.println("Loan record added");
                    break;
                case "OTHER":
                    OtherLoan other = new OtherLoan(recordID, intRate, term, amountLeft);
                    this.creditRecords.add(other);
                    System.out.println("Loan record added");
                    break;

            }
            //updates value of checkEligibility once a loan is entered.
            this.checkEligibility();
        }
        //Message if customer is not eligible.
        else {System.out.println("You are not eligible to add more records. Income must be more than 4x total loan amount.");}
    }


    //Method to add a default record.
    public void addDefault(){
        //Check eligibility and start method.
        this.checkEligibility();
        if(eligibilityStat){
            //Ask for record ID and validate.
            int recordID = 0;
            boolean recordValid = false;
            boolean sameRecord = false;
            while (!recordValid) {
                System.out.println("Enter a Record ID");
                //Makes sure record ID entered is a number and is unique.
                try {
                    recordID = input.nextInt();
                    //Check record ID is unique.
                    if(!this.creditRecords.isEmpty()) {
                        for (Loan loans : this.creditRecords) {
                            if (recordID == loans.getRecordId()){
                                System.out.println("Record ID must be unique to all records" );
                                sameRecord = true;
                                break;
                            }
                        }
                        if (sameRecord){continue;}
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a number");
                    System.out.println("Please try again.");
                    input.next();
                    continue;
                }
                if (!validateInput(recordID, "ABC123", "Auto", 2.0, 200.00, 2)) {
                    System.out.println("Please try again.");
                }

                else {
                    System.out.println("Record ID accepted");
                    recordValid = true;
                }
            }

            //Default loan creation and add to creditRecords.
            AutoLoan def = new AutoLoan(recordID);
            this.creditRecords.add(def);

        }

        else {System.out.println("You are not eligible to add more records. Income must be more than 4x total loan amount.");}
    }


    //Method to print details of loan.
    @Override
    public void printDetails() {
        //Print eligibility status (convert true to YES and false to NO.)
        String eligibilityPrint;
        if (this.eligibilityStat){eligibilityPrint="YES";}
        else {eligibilityPrint="NO";}
        System.out.println();
        //Printed table head
        System.out.println("Customer ID: "+this.customerId);
        System.out.println("Eligible to arrange new loans - "+eligibilityPrint);
        System.out.println();
        //Formatted table
        System.out.printf("%-10s %-10s %-9s %-12s %-10s\n", "RecordID", "LoanType", "IntRate", "AmountLeft", "YearsLeft");
        for(Loan i : creditRecords){
            System.out.printf("%-10s %-10s %-9s %-12s %-10s\n",i.getRecordId(),i.getType().toUpperCase(),i.getIntRate(),i.getAmountLeft(),i.getTerm());
        }
        //Aesthetic
        System.out.println();
        System.out.println("==============================================================");
        System.out.println("==============================================================");
        System.out.println();
        System.out.println();

    }
}

