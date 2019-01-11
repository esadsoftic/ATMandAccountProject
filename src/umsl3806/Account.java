/**
 *
 * @author Esad Softic
 */

package umsl3806;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;


public class Account implements Serializable
{
    double balance;
    String acctName;
    static Scanner sc = new Scanner(System.in);
    protected int firstdate;
    protected int seconddate;
    protected Calendar cal1 = new GregorianCalendar();
    protected Calendar cal2 = new GregorianCalendar();
    protected boolean dateflag = false;
    protected double rate;
    
   public Account(String argAcctName, double begin_balance) throws IOException
    {
//        balance = 100;
        balance =  begin_balance;
//        acctName = argAcctName;
//        System.out.println("You are opening " + acctName + "'s account");
        getDate1();
//        getDate2();
    }
    
    public Account()
    {
        balance = 100;
        
    }
    
    public void deposit()
    {
        System.out.println("Enter deposit amt:");
        double tempDeposit = sc.nextDouble();
        
        balance = tempDeposit + balance;
               
    }
    
    public void withdraw()
    {
         System.out.println("Enter withdraw amt:");
         double tempDeposit = sc.nextDouble();
         if (balance > tempDeposit)
         {
             balance =  balance - tempDeposit;
         }
    }
    
    public void checkBalance()
    {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        String moneyString = formatter.format(balance);
        System.out.println(moneyString);
    }
    
    public void calcInterest() throws IOException
    {
//       System.out.println("balance is:" + balance);
       int datediff = seconddate - firstdate;
//       System.out.println("firstdate is:" + firstdate);
//       System.out.println("seconddate is:" + seconddate);
//       System.out.println("datediff is:" + datediff);
       rate = .10/365;
       double ratetime = Math.pow(1+rate,datediff);
       double interestedEarned = (balance * ratetime) - balance;
       System.out.println("Interest you would earn for this period: " + interestedEarned );    
    }
    
    private void getDate1() throws IOException 
    {
       System.out.println("Enter first date(mm/dd/yyyy): ");
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       String inputText = br.readLine();
       SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
       ParsePosition pos = new ParsePosition(0);
       Date date = formatter.parse(inputText, pos);
       cal1.setTime(date);
       
       firstdate = cal1.get(Calendar.DAY_OF_YEAR) + (cal1.get(Calendar.YEAR) * 365);
       dateflag = true; 
//       System.out.println("firstdate in GD1 =" + firstdate);
       getDate2();
    }
    
    private void getDate2() throws IOException
    {
       System.out.println("Enter second date(mm/dd/yyyy): ");
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       String inputText = br.readLine();
       SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
       ParsePosition pos = new ParsePosition(0);
       Date date = formatter.parse(inputText, pos);
       cal2.setTime(date);
       seconddate = cal2.get(Calendar.DAY_OF_YEAR) + (cal2.get(Calendar.YEAR) * 365);
       dateflag = true; 
//       System.out.println("2nddate in GD2 =" + seconddate);
       
       if(firstdate > seconddate)
       {
           System.out.println("Cant Backdate)");
           getDate2();
       }
       
    }
    
    public void menu() throws IOException
    {
        int input = 0;
        Scanner sc = new Scanner(System.in);
        
        do
        {
            System.out.println("1) Deposit");
            System.out.println("2) Withdraw");
            System.out.println("3) Check Balance");
            System.out.println("4) Calculate Interest");
            System.out.println("5) Exit");
            
            input = sc.nextInt();
            
            if(input == 1)
            {
                deposit();
            }   
            else if(input ==2)
            {
                withdraw();
            }
            else if(input == 3)
            {
                checkBalance();
            }
            else if(input ==4)
            {
                calcInterest();
            }
           
        }while(input !=5);   
            
        }
 }
            

