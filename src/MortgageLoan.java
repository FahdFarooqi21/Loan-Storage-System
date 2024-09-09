public class MortgageLoan extends Loan{
    //Unique attribute to MortgageLoan class.
    private double overpayment;


    //Default Constructor for Mortgage Loan.
    public MortgageLoan(int recordId){
        super(recordId);
    }


    //Custom constructor for Mortgage Loan.
    public MortgageLoan(int recordId, double intRate, int term, double overpayment, double amountLeft){
        super(recordId,"Mortgage",intRate,term,amountLeft);
        this.overpayment = overpayment;
    }

    //Getter method for overpayment as this is not inherited from Loan as not all loans have overpayment.
    public double getOverpayment() {return overpayment;}

    //Setter method for overpayment as it is not inherited.
    public void setOverpayment(double overpayment) {this.overpayment = overpayment;}
}
