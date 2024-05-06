abstract public class Employee {
    protected String employee_type;
    protected String employee_name;

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_type()
    {
        return employee_type;
    }
    abstract public boolean see_internal_fund();

    public String getEmployee_name() {
        return employee_name;
    }
    abstract public boolean Approve_loan();

    public void setEmployee_type(String employee_type) {
        this.employee_type = employee_type;
    }
    public double Lookup(Account a)
    {
        double b;
        b=a.getBalance();
        return b;
    }

    abstract public boolean change_interest_rate(String ac_type, double rate);

}

class Cashier extends Employee
{
    public Cashier(String name)
    {
        super.employee_name=name;
        super.employee_type = "Cashier";
    }

    @Override
    public boolean Approve_loan() {
        return false;
    }

    @Override
    public boolean change_interest_rate(String ac_type, double rate) {
        return false;
    }

    @Override
    public boolean see_internal_fund() {
        return false;
    }
}

class Managing_Director extends Employee
{
    public Managing_Director(String name)
    {
        super.employee_name=name;
        super.employee_type = "Managing Director";
    }

    @Override
    public boolean Approve_loan() {
        return true;
    }

    @Override
    public boolean change_interest_rate(String account_type, double rate) {
       if(account_type.equalsIgnoreCase("Savings"))
       {
           Savings.dep_interest_rate=rate;
       }
        if(account_type.equalsIgnoreCase("Student"))
        {
            Student.dep_interest_rate=rate;
        }
        if(account_type.equalsIgnoreCase("Fixed Deposit"))
        {
            Fixed_Deposit.dep_interest_rate=rate;
        }
        return true;
    }

    @Override
    public boolean see_internal_fund() {
        return true;
    }
}

class Officer extends Employee
{
    public Officer(String name)
    {
        super.employee_name=name;
        super.employee_type = "Officer";
    }

    @Override
    public boolean Approve_loan() {
        return true;
    }

    @Override
    public boolean change_interest_rate(String ac_type, double rate) {
        return false;
    }

    @Override
    public boolean see_internal_fund() {
        return false;
    }
}
