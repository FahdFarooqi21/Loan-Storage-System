public abstract class Loan {

    //Attributes of class Loan and all its subclasses.
    protected int recordId;
    protected String type;
    protected double intRate;
    protected int term;
    protected double amountLeft;


    //Default Constructor for Loan class.
    public Loan(int recordId){
        this.recordId = recordId;
        this.type = "Not given";
        this.intRate = 0;
        this.term = 0;
        this.amountLeft = 0;
    }

    // Custom Constructor which requires info for Loan.
    public Loan(int recordId, String type, double intRate, int term, double amountLeft){
        this.recordId = recordId;
        this.type = type;
        this.intRate = intRate;
        this.term = term;
        this.amountLeft = amountLeft;
    }


    //Setter methods for Loan information which are inherited by all subclasses.
    public void setRecordId(int recordId){this.recordId = recordId;}
    public void setType(String type){this.type=type;}
    public void setIntRate(double intRate){this.intRate=intRate;}
    public void setTerm(int term){this.term=term;}
    public void setAmountLeft(double amountLeft) {this.amountLeft = amountLeft;}


    //Getter methods for Loan information which are inherited by all subclasses.
    public int getRecordId(){return this.recordId;}
    public String getType(){return this.type;}
    public double getIntRate(){return this.intRate;}
    public int getTerm(){return this.term;}
    public double getAmountLeft() {return amountLeft;}


}
