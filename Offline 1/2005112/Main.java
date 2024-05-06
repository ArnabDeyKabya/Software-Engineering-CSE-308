import java.util.*;
import java.util.stream.Collectors;


public class Main {
    private static Account createAccountOfType(String acType) {
        switch (acType) {
            case "Student":
                return new Student();
            case "Savings":
                return new Savings();
            case "Fixed_Deposit":
                return new Fixed_Deposit();
            default:
                System.out.println("Invalid account type: " + acType);
                return null;
        }
    }
    public static void main(String[] args) {
        boolean employee_loggedin = false;
        Bank bank = new Bank();
       Employee current_employee = null;
       Account current_account = null;
       while(true)
       {
           Scanner sc = new Scanner(System.in);
           String cmd = sc.nextLine();

           List<String> commands = Arrays.stream(cmd.split("\\s+"))
                   .collect(Collectors.toList());

           if(commands.get(0).equalsIgnoreCase("Create"))
           {
               String name = commands.get(1);
               String ac_type = commands.get(2);
               String init_balance_s = commands.get(3);

               double init_balance;
               try {
                   init_balance = Double.parseDouble(init_balance_s);
               } catch (NumberFormatException e) {
                   System.out.println("Invalid initial balance format.");
                   continue;
               }
               if (!bank.Customer_Ensure(name)) {
                   System.out.println("A user exists who has the same name");
                   continue;
               }
               Account a= createAccountOfType(ac_type);
               if (a != null && a.create_account(init_balance, name)) {
                   bank.accounts.add(a);
                   System.out.println(ac_type + " account for " + name + " created; initial balance " + init_balance + "$");

                   current_account = a;
                   employee_loggedin = false;
               } else {
                   System.out.println("Cannot create account");
               }

           }
           if(commands.get(0).equalsIgnoreCase("Open"))
           {
               String name = commands.get(1);
               boolean flag = false;
               flag = bank.isEmployee(name);
                   if(flag)
                   {
                       current_employee = (Employee)bank.find_people(name);
                       employee_loggedin = true;
                       System.out.print(name + " Active,");
                       if(bank.isLoan_pending()) System.out.println("There are loans pending");
                       else System.out.println("There are no loans pending");
                   }
                   else
                   {
                       current_account = (Account)bank.find_people(name);
                       employee_loggedin = false;
                       String n = current_account.getOwner_name();
                       System.out.println("Welcome back," + n);
                   }
           }
           if(commands.get(0).equalsIgnoreCase("Withdraw"))
           {
               double am = Double.parseDouble(commands.get(1));
               boolean flag = false;
               flag = employee_loggedin;
               if(flag == false)
               {
                   boolean f = false;
                   f = current_account.withdraw(am);
                   if(f == true)
                   {
                       System.out.println(am+"$ Withdrawn ; current balance "+current_account.getBalance()+"$");
                   }
                   else
                   {
                       System.out.println("Invalid Transaction; current balance "+current_account.getBalance()+"$");
                   }
               }
               else
               {
                   System.out.println("You are in employee account , login from a customer account");
               }

           }
           if(commands.get(0).equalsIgnoreCase("Deposit"))
           {
               String am = commands.get(1);

               try {
                   double amount = Double.parseDouble(am);
                   boolean flag = employee_loggedin;

                   if (flag == false) {
                       boolean f = current_account.deposite(amount);
                       if (f == true) {
                           System.out.println(amount + "$ deposited; current balance " + current_account.getBalance() + "$");
                       } else {
                           System.out.println("Failed to Deposit");
                       }
                   } else {
                       System.out.println("You are in an employee account, log in from the customer account");
                   }
               } catch (NumberFormatException e) {
                   System.out.println("Invalid amount format");
               }
           }
           if(commands.get(0).equalsIgnoreCase("Request"))
           {
               String am = commands.get(1);

               try {
                   double amount = Double.parseDouble(am);
                   boolean flag = employee_loggedin;

                   if (flag == false) {
                       boolean f = current_account.request_loan(amount);
                       if(f == true) {
                           System.out.println("Loan request successful, sent for approval");
                       } else {
                           System.out.println("Failed to grant loan request");
                       }
                   } else {
                       System.out.println("You are in an employee account, log in from the customer account");
                   }
               } catch (NumberFormatException e) {
                   // Handle invalid amount input
                   System.out.println("Invalid amount format");
               }
           }
           if(commands.get(0).equalsIgnoreCase("Query"))
           {
               boolean flag = false;
               flag = employee_loggedin;
               if(flag == false)
               {
                   System.out.println("Current balance "+current_account.getBalance()+"$ , Loan "+current_account.getLoan());
               }
               else
               {
                   System.out.println("You are in employee account , login from customer account");
               }
           }
           if(commands.get(0).equalsIgnoreCase("Lookup")) {
               String name = commands.get(1);
               Account account;
               account = (Account) bank.find_people(name);
               boolean flag = employee_loggedin;

               if (flag == false) {
                   System.out.println("You don't have permission for this operation");
               } else {
                   if (account != null) {
                       System.out.println(name + "'s current account balance " + current_employee.Lookup(account) + "$");
                   } else {
                       System.out.println("Account not found for " + name);
                   }
               }
           }
           if(commands.get(0).equalsIgnoreCase("Approve")) {
               boolean flag = employee_loggedin;
               if (flag == false) {
                   System.out.println("You don't have permission for this operation");
                   continue;
               }

               if (current_employee.Approve_loan()) {
                   if (bank.isLoan_pending()) {
                       bank.approve_Loan();
                   } else {
                       System.out.println("No loans pending to approve");
                   }
               } else {
                   System.out.println("You don't have permission for this operation");
               }
           }
           if(commands.get(0).equalsIgnoreCase("Change"))  {
               if (commands.size() >= 3) {
                   String type = commands.get(1);
                   String new_rate= commands.get(2);

                   try {
                       double rate = Double.parseDouble(new_rate);
                       boolean flag = false;
                       flag = employee_loggedin;
                       if(flag == true)
                       {
                           boolean success = current_employee.change_interest_rate(type, rate);

                           if (success) {
                               System.out.println("Interest rate for " + type + " has been changed to " + new_rate);
                           } else {
                               System.out.println("You don't have permission for this operation");
                           }
                       }
                       else
                       {
                           System.out.println("You don't have permission for this operation");
                       }
                   } catch (NumberFormatException e) {
                       System.out.println("Invalid interest rate format");
                   }
               } else {
                   System.out.println("Invalid number of arguments for 'Change' command");
               }
           }
           if(commands.get(0).equalsIgnoreCase("See"))
           {
               if(employee_loggedin)
               {
                   boolean flag = current_employee.see_internal_fund();
                   if(flag == true)
                   {
                       System.out.println("Bank Internal Fund "+bank.getFund());
                   }
                   else
                   {
                       System.out.println("You don't have any permission for this operation");
                   }
               }
               else
               {
                   System.out.println("You don't have any permission for this operation");
               }
           }
           if(commands.get(0).equalsIgnoreCase("INC"))
           {
               bank.increase_one_year();
               System.out.println("One year passed By");
           }
           if(commands.get(0).equalsIgnoreCase("Close")) {
               boolean flag = employee_loggedin;
               if (flag == false) {
                   System.out.println("Transaction Closed for " + (current_account != null ? current_account.getOwner_name() : "No account selected"));
               } else {
                   System.out.println("Operations for " + (current_employee != null ? current_employee.getEmployee_name() : "No employee logged in") + " closed");
               }
               current_account = null;
               employee_loggedin = false;
               current_employee = null;
           }


       }

    }
}
