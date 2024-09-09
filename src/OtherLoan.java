public class OtherLoan extends Loan{

    //Default constructor for Other Loan.
    public OtherLoan(int recordId){
        super(recordId);
    }

    //Custom constructor for Other Loan.
    public OtherLoan(int recordId, double intRate, int term, double amountLeft){
        super(recordId,"Other",intRate,term,amountLeft);
    }
}
