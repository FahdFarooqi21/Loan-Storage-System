public class PersonalLoan extends Loan{

    //Default constructor for Personal Loan.
    public PersonalLoan(int recordId){
        super(recordId);
    }

    //Custom constructor for Personal Loan.
    public PersonalLoan(int recordId, double intRate, int term, double amountLeft){
        super(recordId,"Personal",intRate,term,amountLeft);
    }
}
