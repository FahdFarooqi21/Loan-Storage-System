public class BuilderLoan extends Loan{

    //Unique attribute to BuilderLoan class.
    private double overpayment;

    //Default Constructor for builder Loan.
    public BuilderLoan(int recordId){
        super(recordId);
        this.overpayment=0;
    }

    //Customer Constructor for builder Loan.
    public BuilderLoan(int recordId, double intRate, int term, double overpayment, double amountLeft){
        super(recordId,"Builder",intRate,term,amountLeft);
        this.overpayment = overpayment;
    }

    //Getter method for overpayment as this is not inherited from Loan as not all loans have overpayment.
    public double getOverpayment() {return overpayment;}


    //Setter method for overpayment as it is not inherited.
    public void setOverpayment(double overpayment) {this.overpayment = overpayment;}
}
