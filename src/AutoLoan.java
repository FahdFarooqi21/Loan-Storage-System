public class AutoLoan extends Loan {

    //default auto Loan constructor.
    public AutoLoan(int recordId){
        super(recordId);
    }

    //Customer auto Loan constructor.
    public AutoLoan(int recordId, double intRate, int term, double amountLeft){
        super(recordId,"Auto",intRate,term,amountLeft);
    }
}
