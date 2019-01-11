/**
 *
 * @author Esad Softic
 */
 package umsl3806;



import java.io.*;
import java.util.Scanner;


public class ATM 
{
    private Account[] acctArray = new Account[3];
    boolean accountsLoaded = false;
        
    public void loadAcct()
    {
        try  
        {
            FileInputStream inStream= new FileInputStream("C:/file1.out");
            ObjectInputStream is = new ObjectInputStream(inStream);
            acctArray = (Account[]) is.readObject();
            is.close();
        }
        catch (Exception ioe)
        {
            System.out.println(ioe.getMessage());
                //acctArray=new Account[3]; 
                //populateaccts();
        }
       accountsLoaded = true;
    }
    
    public void populateAcct() throws IOException
    {
        if(accountsLoaded)
        {
            System.out.println("You've already loaded account");
        }
        else
        {
            int i = 0;
            do
            {

                acctArray[i] = new Account(String.valueOf(i), 100);
//                accountsLoaded=true;
//                  Scanner sc = new Scanner(System.in);
//                double begin_balance = sc.nextDouble();
//                String tempName = sc.nextLine();
                i++;
            }
            while(i<acctArray.length);
            System.out.println("You have created 3 accounts ");
            accountsLoaded=true;
        }    
////                acctArray[i] = new Account (100.00, tempname);  
    } 

    public void saveAccount()  //AGAIN, MAKE SURE YOU ARE ADMINISTRATOR, SO IT SAVES
        {
           try
           {
                FileOutputStream outStream = new FileOutputStream("C:/file1.out");
                ObjectOutputStream os = new ObjectOutputStream(outStream);
                os.writeObject(acctArray);
                os.flush();
                os.close();
           }
            catch (IOException ioe)
           {
                System.err.println(ioe);        
           }
        }

    public static void main(String[]args) throws IOException
    {
        ATM atm=new ATM(); 
        int input = -99;  
        do
        {
            System.out.println("1 - Load Accounts");
            System.out.println("2 - Populate Accounts");
            System.out.println("3 - Access Accounts");
            System.out.println("5 - Exit");
                  
            Scanner sc = new Scanner(System.in);
            input = sc.nextInt();
                  
            if(input == 1)
            {
                atm.loadAcct();
                atm.saveAccount();   
            }
            else if(input == 2)
            {
                atm.populateAcct();
                atm.saveAccount();
            }
            else if(input == 3)
            {
                atm.accessAccounts();
                atm.saveAccount();
            }

            }while(input != 4);
   
        }

        public void accessAccounts() throws IOException
        {
           int input;
           Scanner sc = new Scanner(System.in);
      
          do
          {
            System.out.println("0 - Account0: ");
            System.out.println("1 - Account1: ");
            System.out.println("2 - Account2: ");
            System.out.println("5 - Exit ");
            
            input = sc.nextInt();
////             if(input == 0 ||(input == 1) || (input == 2)){
//                 acctArray[input] = new Menu();
//             }
            if(input == 0)
            {
//                acctArray[0] = new Account("Account zero", input);
                acctArray[0].menu();
            }
            else if(input == 1)
            {
//                acctArray[1] = new Account();
                acctArray[1].menu();
            }
            else if(input == 2)
            {
//                acctArray[2] = new Account();
                acctArray[2].menu();
            }
     
        }while (input != 5);

    }
}

