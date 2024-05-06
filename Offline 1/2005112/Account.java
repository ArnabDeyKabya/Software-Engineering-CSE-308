public abstract class Account {
    protected String type;
    protected double loan_pending=0;
    abstract public boolean deposite(double am);
    protected double balance;
    protected double loan=0;
    abstract public boolean create_account(double balance, String name);
    protected String owner_name;
    public static double loan_interest = 10;
    private int age=0;

    abstract public boolean withdraw(double am);
    abstract public boolean request_loan(double am);

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getLoan() {
        return loan;
    }

    public void setLoan(double loan) {
        this.loan = loan;
    }

    public double getLoan_pending() {
        return loan_pending;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLoan_pending(double loan_pending) {
        this.loan_pending = loan_pending;
    }
    public static double getLoan_interest()
    {
        return loan_interest;
    }
    public void add_balance(double am)
    {
        this.balance += am;
    }
    public void remove_balance(double am)
    {
        this.balance -= am;
    }
    public void add_loan(double am)
    {
        this.loan += am;
    }
}
class Fixed_Deposit extends Account
{
    private static double max_loan = 100000;

    public static double dep_interest_rate = 15;
    public static double service_charge = 500;

    public Fixed_Deposit()
    {
        setType("Fixed Deposite");
    }
    public static double getDep_interest_rate()
    {
        return dep_interest_rate;
    }

    public static double init_deposit = 100000;

    @Override
    public boolean create_account(double b, String name) {
       if(balance>= init_deposit)
       {
           super.balance = b;
           super.owner_name =name;
           return true;
       }
       return false;
    }
    public static double min_deposit = 50000;
    @Override

    public boolean deposite(double am) {
        if(am >= min_deposit)
        {
            super.balance +=am;
            return true;
        }
        return false;
    }

    @Override
    public boolean withdraw(double am) {
        double bl = getBalance();
        if(am >bl)
        {
            return false;
        }
        int year = getAge();
        if(year>=1)
        {
            super.balance -=am;
            return true;
        }
        return false;
    }

    @Override
    public boolean request_loan(double am) {
        double l = getLoan()+getLoan_pending()+am;
        if(l <=100000)
        {
            double s = getLoan_pending()+am;
            setLoan_pending(s);
            return true;
        }
        return false;
    }
}

class Savings extends Account
{
    private static double max_loan = 10000;
    public static double dep_interest_rate = 10;
    public static double service_charge = 500;

    public Savings()
    {
        setType("Savings");
    }
    public static double getDep_interest_rate()
    {
        return dep_interest_rate;
    }

    @Override
    public boolean create_account(double bl, String name) {
        super.balance=bl;
        super.owner_name=name;
        return true;
    }

    @Override
    public boolean deposite(double am) {
        super.balance +=am;
        return true;
    }
    private static double min_balance = 1000;

    @Override
    public boolean withdraw(double am) {
        double l= getBalance();
        if(am> l)
        {
            return false;
        }
        double remain = l - am;
        if(remain >= min_balance)
        {
            remove_balance(am);
            return true;
        }
        return false;
    }

    @Override
    public boolean request_loan(double am) {
        double l =getLoan()+getLoan_pending()+am;
        if(l<=max_loan)
        {
            double l2=getLoan_pending()+am;
            setLoan_pending(l2);
            return true;
        }
        return false;
    }
}

class Student extends Account
{
    private static double max_loan = 1000;
    public static double dep_interest_rate = 5;
    public static double service_charge = 0;
    public Student()
    {
        setType("Student");
    }
    public static double getDep_interest_rate()
    {
        return dep_interest_rate;
    }

    @Override
    public boolean create_account(double bl, String name) {
        super.balance=bl;
        super.owner_name= name;
        return true;
    }

    @Override
    public boolean deposite(double am) {
        super.balance +=am;
       return true;
    }
    public static double withdraw_limit = 10000;

    @Override
    public boolean withdraw(double am) {
        double bl = getBalance();
        if(bl<am)
        {
            return false;
        }
        if(am<=10000)
        {
            remove_balance(am);
            return true;
        }
        return false;
    }

    @Override
    public boolean request_loan(double am) {
        double l = getLoan()+getLoan_pending()+am;
        if( l<=max_loan)
        {
            double l1=getLoan_pending()+am;
            setLoan_pending(l1);
            return true;
        }
        return false;
    }
}


