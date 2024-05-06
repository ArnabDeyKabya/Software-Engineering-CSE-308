import java.util.ArrayList;
import java.util.LinkedList;

public class Bank {
    private double fund;
    private double init_fund = 1000000;
    LinkedList<Employee> employees;
    LinkedList<Account> accounts;
    public Bank()
    {
        Managing_Director MD = new Managing_Director("MD");
        Officer O1 = new Officer("O1");
        Officer O2 = new Officer("O2");
        Cashier C1 = new Cashier("C1");
        Cashier C2 = new Cashier("C2");
        Cashier C3 = new Cashier("C3");
        Cashier C4 = new Cashier("C4");
        Cashier C5 = new Cashier("C5");
        accounts = new LinkedList<>();
        employees = new LinkedList<>();
        employees.add(MD);
        employees.add(O1);
        employees.add(O2);
        employees.add(C1);
        employees.add(C2);
        employees.add(C3);
        employees.add(C4);
        employees.add(C5);
        fund = init_fund;
        System.out.println("Bank Created; MD, O1, O2, C1, C2, C3, C4, C5 created");
    }
    public double getFund(){return fund;}
    public void setFund(double f){fund=f;}
    public boolean Customer_Ensure(String name)
    {
        for(Account a: accounts)
        {
            String n = a.getOwner_name();
            if(n.equalsIgnoreCase(name))
            {
                return false;
            }
        }
        return true;
    }
    public boolean isEmployee(String name)
    {
        for(Employee e: employees)
        {
            String n = e.getEmployee_name();
            if(n.equalsIgnoreCase(name))
            {
                return true;
            }
        }
        return false;
    }
   public Object find_people(String name)
    {
        for(Account a: accounts)
        {
            String n = a.getOwner_name();
            if(n.equals(name))
            {
                return (Account)a;
            }
        }
        for(Employee e: employees)
        {
            String n = e.getEmployee_name();
            if(n.equals(name))
            {
                return (Employee)e;
            }
        }
        return null;
    }
    public boolean isLoan_pending()
    {
        for(Account a: accounts)
        {
            double l = a.getLoan_pending();
            if(l>0)
            {
                return true;
            }
        }
        return false;
    }
    public void approve_Loan()
    {
        for(Account a:accounts)
        {
            if(a.getLoan_pending()>0 && (fund -a.getLoan_pending())>0)
            {
                a.add_balance(a.getLoan_pending());
                a.add_loan(a.getLoan_pending());
                a.setLoan_pending(0);
                System.out.println("Loan approved for "+a.getOwner_name());
                double f = getFund()-a.getLoan_pending();
                setFund(f);
            }
        }
    }
    public void increase_one_year()
    {
        for(Account a: accounts)
        {
            int year = a.getAge();
            year = year + 1;
            a.setAge(year);
            double service_charge=0;
            String t = a.getType();
            double dep_int=0;
            if(t.equalsIgnoreCase("Student"))
            {
                dep_int= a.getBalance()*Student.getDep_interest_rate()*0.01;
                service_charge = Student.service_charge;
                double deduce =((a.getLoan()*Account.getLoan_interest()*0.01)+service_charge);
                a.add_balance(dep_int);
                a.remove_balance(deduce);
            }
            if(t.equalsIgnoreCase("Savings"))
            {
                dep_int= a.getBalance()*Savings.getDep_interest_rate()*0.01;
                service_charge = Savings.service_charge;
                double deduce =((a.getLoan()*Account.getLoan_interest()*0.01)+service_charge);
                a.add_balance(dep_int);
                a.remove_balance(deduce);
            }
            if(t.equalsIgnoreCase("Fixed Deposit"))
            {
                dep_int= a.getBalance()*Fixed_Deposit.getDep_interest_rate()*0.01;
                service_charge = Fixed_Deposit.service_charge;
                double deduce =((a.getLoan()*Account.getLoan_interest()*0.01)+service_charge);
                a.add_balance(dep_int);
                a.remove_balance(deduce);
            }
            fund = fund - dep_int;
            double loan_int = a.getLoan()*Account.getLoan_interest()*0.01;
            fund = fund + loan_int;
            fund = fund + service_charge;
        }
    }

}
