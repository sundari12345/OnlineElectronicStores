import java.nio.file.*;
import java.io.*;  
import java.util.*;
import java.util.regex.*;
import java.io.Console;
import org.mindrot.jbcrypt.*;
 
class StockManagementSystem {
    public static void main(String[] args) {
    
        
        
         String welcome = "🅉 🄾 🄷 🄾  🄾 🄽 🄻 🄸 🄽 🄴  🅂 🅃 🄾 🅁 🄴";
         System.out.print("\033[H\033[2J");
         System.out.println("*********************************************************");
         System.out.printf("%-12s %-10s %12s\n","* ",welcome,"*");
         System.out.println("*********************************************************");
        
		Scanner getData = new Scanner(System.in);
	        int loginNum = PersonEntry.confirmEntry();
	        
		System.out.println("Enter your user Name");
		String userName = getData.nextLine();
		
		
		if(userName.length()<=1){
			do{
				System.out.println("Maruka adii username!");
				userName = getData.nextLine();	
			}while(userName.length()<=1);
		}
		
		
		String password = maskPassword();
		System.out.println();
		
		
		
		
		
		if(password.length()<=1){
			do{
				System.out.println("Maruka adii passwd!");
				password = maskPassword();	
			}while(password.length()<=1);
		}
		
		
		
		
		System.out.print("\033[H\033[2J");
		customer userPerson = new customer(loginNum, userName, password);
		
       	
        try{
        
		if(userPerson.currentUser != null){
		  userPerson.userFile(userName);
		  System.out.println("***********************************************************");
		  System.out.printf("%-40s %-20s\n","Choices: ","type");
		  System.out.print("***********************************************************");
		  System.out.printf("\n%-40s %-20s","to view the categories: ","yes");
		  System.out.printf("\n%-40s %-20s","to exit the shop:","exit");
		  System.out.print("\n***********************************************************\n");
		  String Choice = getData.nextLine();
		  //getData.close();
		  System.out.print("\033[H\033[2J");
		  StockManagementSystem.callFuntion(Choice, userPerson, loginNum);
		}  
          }	catch (Exception e) {
	         e.printStackTrace();
	      } 
	      	
    }
    
    static String maskPassword(){
       Console console = System.console();
        //if (console == null) {
            //System.out.println("Please Enter the password");
            //System.exit(0);
        //}

        char[] passwordArray = console.readPassword("Enter your  password: ");
        System.out.println(Arrays.toString(passwordArray));
        
        
        	console.printf("Password entered was: \n", new String(passwordArray));
         	return new String(passwordArray);
        
    }
    
    
    static void callFuntion(String categoryChoice, customer userPerson, int loginNum){
       String yes = "yes";
       Scanner getData = new Scanner(System.in);
        if(categoryChoice.equals(yes)){
              userPerson.callShowCategories();
              if(loginNum == 1||loginNum ==2){
		      System.out.println("***********************************************************");
		      System.out.printf("%-40s %-20s\n","Choices: ","type");
		      System.out.print("***********************************************************");
		      System.out.printf("\n%-40s %-20s","to buy the product: ","category Name");
		      System.out.printf("\n%-40s %-20s","to see purchase your info: ","info");
		      System.out.printf("\n%-40s %-20s","go back","back");
		      System.out.print("\n***********************************************************\n");
	      }
	      else{
	             System.out.println("***********************************************************");
		      System.out.printf("%-40s %-20s\n","Choices: ","type");
		      System.out.print("***********************************************************");
		      System.out.printf("\n%-40s %-20s","to view product: ","category Name");
		      System.out.printf("\n%-40s %-20s", "to view low stocks: ", "lowstock");
		      System.out.printf("\n%-40s %-20s","to view  sale report: ","salereport");
		      System.out.printf("\n%-40s %-20s","to view total revenue: ","revenue");
		      System.out.printf("\n%-40s %-20s","to modify the stocks: ","modify");
		      System.out.printf("\n%-40s %-20s","go back:","back");
		      System.out.print("\n***********************************************************\n");
	      }	      
              
            
            String category = getData.nextLine();
            String no = "back";
            String info = "info";  
            String modify = "modify";
            String salereport = "salereport";
            String revenue = "revenue";
            String lowstock = "lowstock";
            System.out.print("\033[H\033[2J");
            if(!category.equals(no)&&!category.equals(info)&&!category.equals(modify)&&!category.equals(modify)&&!category.equals(revenue)&&!category.equals(lowstock)&&!category.equals(salereport)){
              userPerson.callshowIteam(category, loginNum);
            } 
            else if(category.equals(info)){
                 userPerson.customerInfo();
            }  
            else if(category.equals(modify)){
                 userPerson.callModify(loginNum);
            }
            else if(category.equals(salereport)){
                 userPerson.salesReport();
            }
            else if(category.equals(revenue)){
                    userPerson.seeTotalRevenue();
            }
            else if(category.equals(lowstock)){
                  userPerson.callshowLowStock();
            }
          System.out.println("***********************************************************");
          System.out.printf("%-40s %-20s\n","Choices: ","type");
          System.out.print("***********************************************************");
          System.out.printf("\n%-40s %-20s","to view the categories: ","yes");
          System.out.printf("\n%-40s %-20s","to exit the shop:","exit");
          System.out.print("\n***********************************************************\n");
          String Choice = getData.nextLine();
          getData.close();
          System.out.print("\033[H\033[2J");
            if(Choice.equals("yes")){
            callFuntion(Choice, userPerson, loginNum);
            }
            else{
                System.out.println("Thank you for visiting our shop!!!");
            }
            }
            else{
                  System.out.println("Thank you for visiting our shop!!!");
            }
    }
    
    
}

 class PersonEntry{
 
       
 
       static int confirmEntry(){
         
                  int  loginNum = 0;
		  System.out.println("\n");
		  System.out.println("***********************************************************");
		  System.out.printf("%-45s %-20s\n","Choices: ","type");
		  System.out.print("***********************************************************");
		  System.out.printf("\n%-45s %-20s","to sign up the customer account: ","1");
		  System.out.printf("\n%-45s %-20s","to sign in the customer account:","2");
		  System.out.printf("\n%-45s %-20s","to sign in the manager account: ","3");
		  System.out.print("\n***********************************************************\n");
		  
		try{
		
			Scanner getData = new Scanner(System.in);
			loginNum = getData.nextInt();
			//getData.close();
			if(loginNum != 1 && loginNum != 2 && loginNum !=3){
			     System.out.println("Invalid Input");
			     confirmEntry();
			}
			
	       }
	        catch(InputMismatchException e){
	              System.out.println("Please Enter the Number");
	              confirmEntry();
	       }
	       return loginNum;
       
       }
 } 

class person extends PersonEntry{

   String userName;
   String password;
   String currentUser;
   static String managerCode  = "manager@sundari001";
   
   static ArrayList<String> userNameList = new ArrayList<String>();
   static ArrayList<String> userPasswordList = new ArrayList<String>();
   
   void personSign(int loginNum, String userName, String password){
   
     String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
      if(loginNum == 1){
          try{
              String fileName = "UserAccount.txt";
              File userFile = new File(fileName);
              
              
              if (userFile.createNewFile()){
                 currentUser = userName;
                    this.userName = userName;
                    this.password = password;
                    this.managerCode = "manager@"+userName+"001";
                 FileWriter userFileInput = new FileWriter(fileName, true); 
                 
                 
                 userNameList.add(userName);
                 userPasswordList.add(hashedPassword);
                 
                 userFileInput.write(userName+"~"+hashedPassword+"\n");
                 userFileInput.close();
                 System.out.println("Account is created Successfully!");
                 System.out.println("***********************************************************");
                 System.out.printf("%-17s %-10s %17s\n","","welcome "+currentUser,"");
                 System.out.println("***********************************************************");
                 System.out.println("\nwelcome "+currentUser); 
              }
              else{
                ArrayList<String> fileContents = new ArrayList<String>(); 
                fileContents.addAll(Files.readAllLines(Paths.get(fileName)));
                
                for(String line: fileContents){
                   String[] tempArr = line.split("~");
                   userNameList.add(tempArr[0]);
                   userPasswordList.add(tempArr[1]);
                }
                if(userNameList.contains(userName)){
                   System.out.println(userName+" is already exit, try again!");
                }
                else{
                   userNameList.add(userName);
                   userPasswordList.add(hashedPassword);
                   currentUser = userName;
                      this.userName = userName;
                      this.password = password;
                      
                      
                   FileWriter userFileInput = new FileWriter(fileName, true); 

                   userFileInput.write(userName+"~"+hashedPassword+"\n");
                   userFileInput.close();
                   System.out.println("*************************************************");
                   System.out.println("          Account is created Successfully!");
                   System.out.println("*************************************************\n");
                 System.out.println("***********************************************************");
                 System.out.printf("%-17s %-10s\n","","welcome "+currentUser);
                 System.out.println("***********************************************************\n");
                }
              }
              
            } 
           catch (IOException e) {
               e.printStackTrace();
           }     
               
      }
      else if(loginNum == 2){
       try{
         String fileName = "UserAccount.txt";
         File userFile = new File(fileName);
     
         if (!userFile.createNewFile()){
             ArrayList<String> fileContents = new ArrayList<String>(); 
             fileContents.addAll(Files.readAllLines(Paths.get(fileName)));
                
             for(String line: fileContents){
                String[] tempArr = line.split("~");
                userNameList.add(tempArr[0]);
                userPasswordList.add(tempArr[1]);
             }
             if(userNameList.contains(userName)){
             
               if(BCrypt.checkpw(password, userPasswordList.get(userNameList.indexOf(userName)))){
                 currentUser = userName;
                     this.userName = userName;
                     this.password = password;
                  System.out.println("***********************************************************");
                  System.out.printf("%-17s %-10s %17s\n","","welcome "+currentUser,"");
                  System.out.println("***********************************************************");
               }
               else{
                  System.out.println("Sorry wrong password!");
               }   
                
             }
             else{
                System.out.println("Your name is not exit please try again!");
             }
         }
         else{
                System.out.println("Your name is not exit please try again!");
             }
         
        }
        catch (IOException e) {
            e.printStackTrace();
        } 
      }
      else if(loginNum == 3){
        try{
             if(userName.equals("sundari")){
                 if(password.equals("123456789")){
                 String fileName = "UserAccount.txt";
                 File userFile = new File(fileName);
                 ArrayList<String> fileContents = new ArrayList<String>(); 
                 fileContents.addAll(Files.readAllLines(Paths.get(fileName)));
                
		    for(String line: fileContents){
		           String[] tempArr = line.split("~");
		           userNameList.add(tempArr[0]);
		           userPasswordList.add(tempArr[1]);
		        }
                 currentUser = userName;
                     this.userName = userName;
                     this.password = password;
                  System.out.println("***********************************************************");
                  System.out.printf("%-17s %-10s %17s\n","","welcome "+currentUser,"");
                  System.out.println("***********************************************************");
		      if((currentUser.length() != 0)&&(loginNum == 3)){
	      	 System.out.println("\n");
		     }
                  
               }   
               else{
                    System.out.println("Sorry worng password");
               }
           }  
           else{
                   System.out.println("Only manager can access.");
           }
           }catch (IOException e) {
                 e.printStackTrace();
            } 
      }
      
      
      else{
        System.out.println("Invalid input");
      }
      		
   }
   
 
}

class customer extends person{

	   iteam customerStock = new iteam();
	   static manager Manager = new manager();
	   
	   HashMap<String, ArrayList<String>> customerStockList = new HashMap<>();
	   HashMap<String, ArrayList<String>> salesCustomerStockList = new HashMap<>();
	   ArrayList<String> revenueName = new ArrayList <String>();
	   ArrayList <Integer> revenueList = new ArrayList <Integer>();
	  
	  
	  customer(int loginNum, String userName, String password){
	     super.personSign(loginNum, userName, password);
	  }
	  
	  
	  void callShowCategories(){
	    customerStock.showCategories();
	  }
	  
	  void callshowLowStock(){
	      customerStock.showLowStock();
	  }
	  
	  void callshowIteam(String category, int loginNum){
	    Scanner getData = new Scanner(System.in);
	    customerStock.showIteam(category);
	    if(loginNum == 1||loginNum == 2){
		   System.out.println("***********************************************************");
		   System.out.printf("%-40s %-20s\n","Choices: ","type");
		   System.out.print("***********************************************************");
		   System.out.printf("\n%-40s %-20s","to view your purchase info: ","info");
		   System.out.printf("\n%-40s %-20s","to buy the products: ","buy");
		   System.out.printf("\n%-40s %-20s","go back:","back");
		   System.out.print("\n***********************************************************\n");
		    String categories = getData.nextLine();
		    String no = "back";
		    String buy = "buy";  
		    String info = "info";
		    if(!categories.equals(no) && !categories.equals(buy)&&!categories.equals(info)){
		       callShowCategories();
		       callshowIteam(categories, loginNum);
		    }
		    else if(categories.equals(buy)){
		       System.out.println("\n\nEnter the product Index");
			int iteamIndex = getData.nextInt();
		       System.out.println("Enter the quantity of the device");
			int  quantity = getData.nextInt();
		       buyIteams(category, iteamIndex, quantity);
		    }
		    else if(categories.equals(info)){
			this.customerInfo();
		    }
		}    
		else{
			   System.out.println("***********************************************************");
			   System.out.printf("%-40s %-20s\n","Choices: ","type");
			   System.out.print("***********************************************************");
			   //System.out.printf("\n%-40s %-20s","you want to see the products: ","Category Name");
			   System.out.printf("\n%-40s %-20s", "to view low stocks: ", "lowstocks");
			   System.out.printf("\n%-40s %-20s","to view sale products: ","salereport");
			   System.out.printf("\n%-40s %-20s","to view total revenue: ","revenue");
			   System.out.printf("\n%-40s %-20s","to modify the products: ","modify");
			   System.out.printf("\n%-40s %-20s","go back:","back");
			   System.out.print("\n***********************************************************\n");
			   String categories = getData.nextLine();
			   //getData.close();
			   String no = "back";
		          String modify = "modify";
		          String salereport = "salereport";
		          String revenue = "revenue";
		          String lowstock = "lowstock";
			    if(!categories.equals(no) && !categories.equals(modify)&&!categories.equals(salereport)&&!categories.equals(revenue)&&!categories.equals(lowstock)){
			       callShowCategories();
			       callshowIteam(categories, loginNum);
			    }
			    else if(categories.equals(modify)){
			         this.callModify(loginNum);
			    }
			    else if(categories.equals(salereport)){
			         this.salesReport();    
			    }
			    else if(categories.equals(revenue)){
			         this.seeTotalRevenue();    
			    }
			    else if(categories.equals(lowstock)){
			         this.callshowLowStock();    
			    }
			    
			   
		}
	  }
	  
	  void userFile(String fileName){
		   try{
		    fileName = fileName+"Stock.txt";
		    File userFile = new File(fileName);
		     if (userFile.createNewFile()){
			 FileWriter userFileInput = new FileWriter(fileName); 
		     }
		     else{ 
			  ArrayList<String> fileContents =  new ArrayList<String>();
		         fileContents.addAll(Files.readAllLines(Paths.get(fileName)));
		          if(fileContents.size() != 0){
				 String stockstring = fileContents.get(0).split("\\{|\\}")[1];
				 String[] categoryList = stockstring.split("=");
				 String category =   categoryList[0]; 
				                  
				     for(int i = 1; i < categoryList.length; i++){
				      String[] contentList = categoryList[i].split(Pattern.quote("[") + "|" + Pattern.quote("]"));
				      String iteamString = categoryList[0];
				          if(i != categoryList.length-1){
					      String[] iteamStringl = contentList[2].split(", ");
					      iteamString = iteamStringl[1];
				          }
				      ArrayList<String> iteamList = new ArrayList<>();   
				      String[] iteamStringList = contentList[1].split(", ");
				      
				          for(String line : iteamStringList){
				          iteamList.add(line);
				          }
				      customerStockList.put(category, iteamList);
				      category = iteamString;
				    }
				 }
		            }		 
		      } catch (IOException e) {
			    e.printStackTrace();
		     } 

	  } 
	  
	  void customerInfo(){
		       System.out.println("**************************************");
		       System.out.println("	       "+currentUser+"            ");
		       System.out.println("**************************************");
		       
		       for (Map.Entry<String, ArrayList<String>> entry : customerStockList.entrySet()) {
			    System.out.println("\n\n"+entry.getKey());
			    ArrayList<String> list = new ArrayList<String>();
			    list.addAll(entry.getValue());
			    for(String line : list){
			      String[] iteamInfo = line.split("-");
			      System.out.printf("\n%-20s %-20s","Product Name: ",iteamInfo[0]);
			      System.out.printf("\n%-20s %-20s","Product Price: ",iteamInfo[1]);
			      System.out.printf("\n%-20s %-20s","Product Quantity: ",iteamInfo[2]);
			      System.out.printf("\n%-20s %-20s","Product Quality: ",iteamInfo[3]);
			      System.out.printf("\n%-20s %-20s","Product Grade: ",iteamInfo[4]);
			      System.out.println("\n");
			      
			   }
			}
		     }
	  
	  void buyIteams(String category, int iteamIndex, int quantity){
	  
	               String[] choosedIteamList = customerStock.stockLevel.get(category).get(iteamIndex-1).split("-");
                      String productName = choosedIteamList[0];
                          int productPrice = Integer.parseInt(choosedIteamList[1]);
                          int productCount = Integer.parseInt(choosedIteamList[2]);
                          if(productCount >= quantity && quantity > 0){
		              productCount-=quantity;
		              String replaceString = productName+"-"+productPrice+"-"+productCount+"-"+choosedIteamList[3]+"-"+choosedIteamList[4];
		              customerStock.stockLevel.get(category).set(iteamIndex-1, replaceString);
		                 if(productCount == 0){
		                 customerStock.stockLevel.get(category).remove(iteamIndex-1);
		                 }
		              
		                  if(customerStockList.containsKey(category)){
		                 
		                 ArrayList<String> chooseIteam = new ArrayList<String>();
		                 chooseIteam.addAll(customerStockList.get(category));
		                     boolean isCategoryThere = false;
		                     
		                     for(int i = 0; i<chooseIteam.size(); i++){
		                         String[] wishProduct =  chooseIteam.get(i).split("-");
		                         String name = wishProduct[0];
		                              int count = Integer.parseInt(wishProduct[2]); 
		                              
		                              if(name.equals(productName)){
		                              count+=quantity;
		                              String replaceStr = name+"-"+wishProduct[1]+"-"+count+"-"+wishProduct[3]+"-"+wishProduct[4];
		                              customerStockList.get(category).set(i, replaceStr);
		                              isCategoryThere = true;
		                                    break;
		                              }
		                     }
		                     if(!isCategoryThere){
		                     String replaceStr = productName+"-"+productPrice+"-"+quantity+"-"+choosedIteamList[3]+"-"+choosedIteamList[4];
		                     customerStockList.get(category).add(replaceStr);
		                     }
		                     
		                 }
		                 else{
		                 String replaceStr = productName+"-"+productPrice+"-"+quantity+"-"+choosedIteamList[3]+"-"+choosedIteamList[4];
		                 ArrayList<String> productIteamList = new ArrayList<String>();
		                 productIteamList.add(replaceStr);
		                 customerStockList.put(category, productIteamList);
		                 }
		                 if(repayment(productPrice, quantity)){
		                    customerStock.addStockFile();
		                         this.addUserStockFile();
		                    System.out.println("Your order is delivered! \nThank you");
		               }
		               else{
		                     System.out.println("Please check the amount...");
		               }  
		         }
		         else{
		                System.out.println("Sorry!!! out of stock");
		         }
            }
            
            
            
            void addUserStockFile(){
                  try{
		    String fileName = currentUser+"Stock.txt";
		    File userFile = new File(fileName);
		    FileWriter userFileInput = new FileWriter(fileName); 
		    String stocks = customerStockList.toString(); 
		    userFileInput.write(stocks);
		    userFileInput.close();
		  }catch (IOException e) {
			    e.printStackTrace();
		  } 
           }
           
           boolean repayment(int amount, int quantity){
		   System.out.println("Your order is verified!\npay the amount Rs "+amount*quantity+".");
		   Scanner getData = new Scanner(System.in);
		   int Amount = getData.nextInt();
		   //getData.close();
		    return Amount == amount*quantity;
           }
           
           void callModify(int loginNum){
                    Scanner getData = new Scanner(System.in);
                    if(loginNum == 3){
                    System.out.println("Enter the manager id/password");
                    String managerPassword = getData.nextLine();
                    //getData.close();
                        if(managerPassword.equals(managerCode)){
                        Manager.modify();
                    }
                    else{
                    System.out.println("Sorry worng password..");
                    }
                  }  
                    
           }
           
           void salesReport(){
                for(String name: super.userNameList){
                      salesCustomerUserFile(name);
                      salesCustomerInfo(name);
                }
           
           }
           void seeTotalRevenue(){
                for(String name: super.userNameList){
                      salesCustomerUserFile(name);
                      salesCustomerRevenue(name);
                } 
                callRevenue();
           
           }
           
           void salesCustomerUserFile(String fileName){
		   try{
		    fileName = fileName+"Stock.txt";
		    File userFile = new File(fileName);
		     if (userFile.createNewFile()){
			 FileWriter userFileInput = new FileWriter(fileName); 
		     }
		     else{ 
			  ArrayList<String> fileContents =  new ArrayList<String>();
		         fileContents.addAll(Files.readAllLines(Paths.get(fileName)));
		          if(fileContents.size() != 0){
				 String stockstring = fileContents.get(0).split("\\{|\\}")[1];
				 String[] categoryList = stockstring.split("=");
				 String category =   categoryList[0]; 
				                  
				     for(int i = 1; i < categoryList.length; i++){
				      String[] contentList = categoryList[i].split(Pattern.quote("[") + "|" + Pattern.quote("]"));
				      String iteamString = categoryList[0];
				          if(i != categoryList.length-1){
					      String[] iteamStringl = contentList[2].split(", ");
					      iteamString = iteamStringl[1];
				          }
				      ArrayList<String> iteamList = new ArrayList<>();   
				      String[] iteamStringList = contentList[1].split(", ");
				      
				          for(String line : iteamStringList){
				          iteamList.add(line);
				          }
				      salesCustomerStockList.put(category, iteamList);
				      category = iteamString;
				    }
				 }
		            }		 
		      } catch (IOException e) {
			    e.printStackTrace();
		     } 

	  } 
	  
	  void salesCustomerInfo(String name){
		       System.out.println("**************************************");
		       System.out.println("	       "+name+"            ");
		       System.out.println("**************************************");
		       
		       for (Map.Entry<String, ArrayList<String>> entry : salesCustomerStockList.entrySet()) {
			    System.out.println("\n"+entry.getKey());
			    ArrayList<String> list = new ArrayList<String>();
			    list.addAll(entry.getValue());
			    for(String line : list){
			      String[] iteamInfo = line.split("-");
			      System.out.printf("\n%-20s %-20s","Product Name: ",iteamInfo[0]);
			      System.out.printf("\n%-20s %-20s","Product Price: ",iteamInfo[1]);
			      System.out.printf("\n%-20s %-20s","Product Quantity: ",iteamInfo[2]);
			      System.out.printf("\n%-20s %-20s","Product Quality: ",iteamInfo[3]);
			      System.out.printf("\n%-20s %-20s","Product Grade: ",iteamInfo[4]);
			      System.out.println("\n");
			      
			   }
			}
		     }
		     
		     void salesCustomerRevenue(String name){
		       
		       for (Map.Entry<String, ArrayList<String>> entry : salesCustomerStockList.entrySet()) {
			    
			    ArrayList<String> list = new ArrayList<String>();
			    list.addAll(entry.getValue());
			    for(String line : list){
			      String[] iteamInfo = line.split("-");
			      
			       if(revenueName.contains(iteamInfo[0])){
			          
			            int indexOfItem = revenueName.indexOf(iteamInfo[0]);
				    int currentRevenue = revenueList.get(indexOfItem);
				    int newRevenue = currentRevenue + (Integer.parseInt(iteamInfo[1]) * Integer.parseInt(iteamInfo[2]));
				    revenueList.set(indexOfItem, newRevenue);
			       }
			       else{
			            revenueName.add(iteamInfo[0]);
			            revenueList.add(Integer.parseInt(iteamInfo[1])*Integer.parseInt(iteamInfo[2]));
			       }
			   }   
			   
			}
		     }
		     
		     void callRevenue(){
		          int totalAmount = 0;
		         System.out.println("\n**********************************************");
		         System.out.printf("%30s", "Total revenue: ");
		         System.out.println("\n**********************************************");
		          for(int i = 0; i<revenueName.size(); i++){
			       totalAmount+=revenueList.get(i);
			       System.out.printf("\n%-30s %10s", revenueName.get(i), revenueList.get(i));
			   }
			   
			  System.out.println("\n**********************************************");
		         System.out.printf("%-30s %10s", "Total revenue: ", totalAmount);
		         System.out.println("\n**********************************************\n");
		     }
	  
}

class manager extends person{
     
     stock managerStock = new stock();
     
     void modify(){
     
          System.out.println("***********************************************************");
          System.out.printf("%-50s %-20s\n","Choices: ","type");
          System.out.print("***********************************************************");
          System.out.printf("\n%-50s %-20s","to add the new category: ","1");
          System.out.printf("\n%-50s %-20s","to add the product in the category: ","2");
          System.out.printf("\n%-50s %-20s","to remove the product in the category: ","3");
          System.out.printf("\n%-50s %-20s","to add the quantity of the product: ","4");
          System.out.printf("\n%-50s %-20s","to reduce the quantity of the product:","5");
          System.out.printf("\n%-50s %-20s","go back:","0");
          System.out.print("\n***********************************************************\n");
          Scanner getData = new Scanner(System.in);
            
            try{
                    int Numchoice = getData.nextInt();
		    if(Numchoice==1||Numchoice==2||Numchoice==3||Numchoice==4||Numchoice==5){
			  System.out.println("Enter the category");
			  getData.nextLine();
			  String category = getData.nextLine();
			    
			    if(managerStock.stockLevel.containsKey(category)){
				  System.out.println("Enter the product name");
				  String iteamName = getData.nextLine();
				  ArrayList<String> categoryList = new ArrayList<String>();
				  categoryList.addAll(managerStock.stockLevel.get(category));
				     boolean isCategoryThere = false;
				             
				             for(int i = 0; i<categoryList.size(); i++){
				                String[] changeProductList =  categoryList.get(i).split("-");
				                String changeProductname = changeProductList[0];
				                      int changeProductCount = Integer.parseInt(changeProductList[2]); 
				                      
				                      if(iteamName.equals(changeProductname)){
				                         
				                      String add = "add";
				                      String modify = "modify";
				                      String no = "exit";
				                      String choice = null;
				                            if(Numchoice == 4){
				                         choice = "add";
				                        }
				                            else if(Numchoice == 3||Numchoice == 5){
				                            choice = "modify";
				                          }
				                      
				                            if(add.equals(choice)){
				                           System.out.println("Enter the quantity to add");
				                                   int quantity = getData.nextInt();
				                                   if(quantity > 0){
								      changeProductCount+=quantity;
								      String replaceStr = changeProductname+"-"+changeProductList[1]+"-"+changeProductCount+"-"+changeProductList[3]+"-"+ changeProductList[4];
								      managerStock.stockLevel.get(category).set(i, replaceStr);
								      isCategoryThere = true;
								      System.out.println("**************************************");
								       System.out.println("	       "+category+"            ");
							      	System.out.println("**************************************");
								      System.out.printf("\n%-20s %-20s","Product Name: ",changeProductname);
								       System.out.printf("\n%-20s %-20s","Product Price: ",changeProductList[1]);
								       System.out.printf("\n%-20s %-20s","Product Quantity: ",changeProductCount);
								       System.out.printf("\n%-20s %-20s","Product Quality: ",changeProductList[3]);
								       System.out.printf("\n%-20s %-20s","Product Grade: ",changeProductList[4]);
								       System.out.println("\n");
								      System.out.println("\nYou successfully modify it!!!");
								         break;
								    }else{
								         isCategoryThere = true;
								         System.out.println("Invalid input");
								    }
								            
						           } 
						           else if(modify.equals(choice)){
						                   int quantity = 0;
						                   if(Numchoice == 5){
						                      System.out.println("Enter the quantity to remove the product: ");
						                      quantity = getData.nextInt();
						               }
						                   
						              
				                                    
				                                    if(quantity > 0){
				                                         if(changeProductCount >= quantity){
									      changeProductCount-=quantity;
									      String replaceStr = changeProductname+"-"+changeProductList[1]+"-"+changeProductCount+"-"+changeProductList[3]+"-"+ changeProductList[4];
									      managerStock.stockLevel.get(category).set(i, replaceStr);
									      isCategoryThere = true;
										if(changeProductCount <= 0){
										     managerStock.stockLevel.get(category).remove(i);
										}
									       System.out.println("**************************************");
									       System.out.println("	       "+category+"            ");
								      	System.out.println("**************************************");
									       System.out.printf("\n%-20s %-20s","Product Name: ",changeProductname);
									       System.out.printf("\n%-20s %-20s","Product Price: ",changeProductList[1]);
									       System.out.printf("\n%-20s %-20s","Product Quantity: ",changeProductCount);
									       System.out.printf("\n%-20s %-20s","Product Quality: ",changeProductList[3]);
									       System.out.printf("\n%-20s %-20s","Product Grade: ",changeProductList[4]);
									       System.out.println("\n");
									       System.out.println("\nYou successfully modify it!!!");
									       break;
									    }
									    else{
										isCategoryThere = true;
										System.out.println("Out of stock!!!");
									    }
								    }
								    else if(quantity == 0){
								      isCategoryThere = true;
								      managerStock.stockLevel.get(category).remove(i);
								      System.out.println("\nYou successfully modify it!!!");
								         break;
								    }
								    else{
								    isCategoryThere = true;
								    System.out.println("Sorry wrong input!");
								    }
						           }    
				                  }
				             }
				       if(!isCategoryThere){
						System.out.println("Enter the price of the product");
						  int price = getData.nextInt(); 
						  if(price > 50){
						      
						      System.out.println("Enter the quantity of the product");
							 int quantity = getData.nextInt();
							 if(quantity > 0){
							       System.out.println("Enter the quality of the product");
							       getData.nextLine();
							       String quality = getData.nextLine();
							       System.out.println("Enter the grade of the product");
							       String grade = getData.nextLine();
							       String  replaceStr = iteamName+"-"+price+"-"+quantity+"-"+quality+"-"+grade;
							       managerStock.stockLevel.get(category).add(replaceStr);
							       System.out.println("**************************************");
								System.out.println("	       "+category+"            ");
							       System.out.println("**************************************");
							       System.out.printf("\n%-20s %-20s","Product Name: ",iteamName);
								System.out.printf("\n%-20s %-20s","Product Price: ",price);
								System.out.printf("\n%-20s %-20s","Product Quantity: ",quantity);
								System.out.printf("\n%-20s %-20s","Product Quality: ",quality);
								System.out.printf("\n%-20s %-20s","Product Grade: ",grade);
								System.out.println("\n");
							       System.out.println("\nYou successfully modify it!!!");
							  }     
							  else{
								System.out.println("Invalid Input!!!");
							  }
						  }
						  else{
						    System.out.println("price must be greater than 50");
						  }
					       
				       }      
				             
			    }
			    else{          
				            System.out.println("Enter the name of the product");
				            String iteamName = getData.nextLine();
				            System.out.println("Enter the price of the product");
						  int price = getData.nextInt(); 
						  if(price > 50){
						      
						      System.out.println("Enter the quantity of the product");
							 int quantity = getData.nextInt();
							 if(quantity > 0){
							       System.out.println("Enter the quality of the product");
							       getData.nextLine();
							       String quality = getData.nextLine();
							       System.out.println("Enter the grade of the product");
							       String grade = getData.nextLine();
							       //getData.close();
							       String  replaceStr = iteamName+"-"+price+"-"+quantity+"-"+quality+"-"+grade;
							       ArrayList<String> productDetailsList = new ArrayList<String>(); 
							       productDetailsList.add(replaceStr);
							       managerStock.stockLevel.put(category,  productDetailsList);
							       System.out.println("**************************************");
								System.out.println("	       "+category+"            ");
							       System.out.println("**************************************");
							       System.out.printf("\n%-20s %-20s","Product Name: ",iteamName);
								System.out.printf("\n%-20s %-20s","Product Price: ",price);
								System.out.printf("\n%-20s %-20s","Product Quantity: ",quantity);
								System.out.printf("\n%-20s %-20s","Product Quality: ",quality);
								System.out.printf("\n%-20s %-20s","Product Grade: ",grade);
								System.out.println("\n");
							       System.out.println("\nYou successfully modify it!!!");
							       
							  }     
							  else{
								System.out.println("Invalid Input!!!");
							  }
						  }
						  else{
						    System.out.println("price must be greater than 50");
						  }
				             
			    }
			    
			    managerStock.addStockFile();
			    
		} 
		else if(Numchoice != 0){
		      System.out.print("\033[H\033[2J");
		      System.out.println("Invalid input");
		}
	  }
	  catch (InputMismatchException  e) {
                System.out.println("Please Enter the number!");
                   this.modify();
         } 
  
   }
}



class stock{ 
    ArrayList<String> fileContents =  new ArrayList<String>();
    static HashMap<String, ArrayList<String>> stockLevel = new HashMap<>();
    
    
    
    
    
    void addproductsToFile(){
         try{
            String fileName = "stocksLevel.txt";
            File stocksFile = new File(fileName);
     
             if (stocksFile.createNewFile()){
		          ArrayList<String> Smartphones = new ArrayList<>();
		          Smartphones.add("iPhone X-70000-100-New-A");
		          Smartphones.add("Samsung Galaxy S20-25000-150-New-A");
		          Smartphones.add("Google Pixel 5-15000-10-New-A");
		          Smartphones.add("OnePlus 9 Pro-17000-10-New-A");
		          Smartphones.add("Xiaomi Mi 11-12000-10-New-A");
		          stockLevel.put("Smartphones", Smartphones);

		          ArrayList<String> Laptops = new ArrayList<>();
		          Laptops.add("Dell XPS 13-100000-50-New-A");
		          Laptops.add("HP Spectre x360-150000-80-Refurbished-B");
		          Laptops.add("Lenovo ThinkPad X1 Carbon-200000-30-New-A");
		          Laptops.add("MacBook Pro 13-inch-250000-25-New-A");
		          Laptops.add("Microsoft Surface Laptop 4-40000-20-New-A");
		          stockLevel.put("Laptops", Laptops);

		          ArrayList<String> Tablets= new ArrayList<>();
		          Tablets.add("iPad Air-10000-70-New-A");
		          Tablets.add("Samsung Galaxy Tab S7-12000-60-Open Box-A");
		          Tablets.add("Microsoft Surface Pro 7-50000-40-New-A");
		          Tablets.add("Amazon Fire HD 10-15000-25-New-A");
		          Tablets.add("Lenovo Tab P11-60000-15-New-A");
		          stockLevel.put("Tablets", Tablets);

		          ArrayList<String> Smartwatches = new ArrayList<>();
		          Smartwatches.add("Apple Watch Series 6-1000-30-New-A");
		          Smartwatches.add("Garmin Venu-5000-40-Open Box-A");
		          Smartwatches.add("Samsung Galaxy Watch 4-2500-20-New-A");
		          Smartwatches.add("Fitbit Sense-7000-15-New-A");
		          Smartwatches.add("Huawei Watch GT 3-10000-10-New-A");
		          stockLevel.put("Smartwatches", Smartwatches);

		          ArrayList<String> Cameras = new ArrayList<>();
		          Cameras.add("Canon EOS R5-70000-20-New-A");
		          Cameras.add("Sony Alpha A7 III-120000-25-Refurbished-B");
		          Cameras.add("Nikon Z6-30000-15-New-A");
		          Cameras.add("Fujifilm X T4-50000-10-New-A");
		          Cameras.add("Panasonic Lumix GH5-200000-12-New-A");
		          stockLevel.put("Digital Cameras", Cameras);

			ArrayList<String> Headphones = new ArrayList<>();
			Headphones.add("Sony WH 1000XM4-2000-100-New-A");
			Headphones.add("AirPods Pro-1000-120-Open Box-A");
			Headphones.add("Bose QuietComfort 35 II-7000-50-Refurbished-B");
			Headphones.add("Sennheiser HD 660 S-2500-30-New-A");
			Headphones.add("JBL Free X-1500-20-New-A");
			stockLevel.put("Headphones", Headphones);

			ArrayList<String> GamingConsoles = new ArrayList<>();
			GamingConsoles.add("PlayStation 5-20000-50-New-A");
			GamingConsoles.add("Xbox Series X-25000-40-New-A");
			GamingConsoles.add("Nintendo Switch OLED-35000-15-New-A");
			GamingConsoles.add("Sony PS4 Pro-40000-25-Refurbished-B");
			GamingConsoles.add("Xbox One S-15000-20-Refurbished-B");
			stockLevel.put("Gaming Consoles", GamingConsoles);

			ArrayList<String> Televisions = new ArrayList<>();
			Televisions.add("Samsung QLED Q90T-12000-30-New-A");
			Televisions.add("LG OLED CX-25-20000-Open Box-A");
			Televisions.add("Sony Bravia X900H-25000-20-New-A");
			Televisions.add("TCL 6 Series-15000-15-New-A");
			Televisions.add("Vizio M Series-17000-10-New-A");
			stockLevel.put("Televisions", Televisions);

			ArrayList<String> Printers = new ArrayList<>();
			Printers.add("HP LaserJet Pro M404dn-10000-60-New-A");
			Printers.add("Epson EcoTank ET-2760-20000-45-Refurbished-B");
			Printers.add("Canon PIXMA TS9120-25000-30-New-A");
			Printers.add("Brother HL L2380DW-15000-20-New-A");
			Printers.add("Dymo LabelWriter 4XL-27000-15-New-A");
			stockLevel.put("Printers", Printers);

			ArrayList<String> Accessories = new ArrayList<>();
			Accessories.add("Logitech MX Master 3 Mouse-500-150-New-A");
			Accessories.add("WD Elements 2TB External Hard Drive-1000-80-New-A");
			Accessories.add("Samsung T5 Portable SSD-1200-40-New-A");
			Accessories.add("Logitech G Pro Mechanical Keyboard-5000-25-New-A");
			Accessories.add("Corsair HS70 Wireless Gaming Headset-2500-20-New-A");
			stockLevel.put("Accessories", Accessories);


                    FileWriter userFileInput = new FileWriter(fileName); 
                    String stocks = stockLevel.toString(); 
                    userFileInput.write(stocks);
                    userFileInput.close();
             }
             else{
                 ArrayList<String> fileContents =  new ArrayList<String>();
                 
                 fileContents.addAll(Files.readAllLines(Paths.get(fileName)));
                 String stockstring = fileContents.get(0).split("\\{|\\}")[1];
                 String[] categoryList = stockstring.split("=");
                 String category =   categoryList[0]; 
                                  
                     for(int i = 1; i < categoryList.length; i++){
                      String[] contentList = categoryList[i].split(Pattern.quote("[") + "|" + Pattern.quote("]"));
                      String iteamString = categoryList[0];
                          if(i != categoryList.length-1){
		              String[] iteamStringl = contentList[2].split(", ");
		              iteamString = iteamStringl[1];
                          }
                      ArrayList<String> iteamList = new ArrayList<>();
                          if(contentList.length != 0){
		              String[] iteamStringList = contentList[1].split(", ");
		              
		                  for(String line : iteamStringList){
		                  iteamList.add(line);
		                  }
		              stockLevel.put(category, iteamList);
		              category = iteamString;
		             } 
                     }
             }
             }
             catch (IOException e) {
                e.printStackTrace();
             } 
    }
    
    
    void showCategories(){
      System.out.println("These represent the various stock categories in our inventory.");
      int index = 1;
      for (String key : stockLevel.keySet()) {
         System.out.println(index+". "+key);
         index++;
      }
    }
    
    void addStockFile(){
		    try{
			   String fileName = "stocksLevel.txt";
			   File stocksFile = new File(fileName);
		          FileWriter userFileInput = new FileWriter(fileName); 
		          String stocks = stockLevel.toString(); 
		          userFileInput.write(stocks);
		          userFileInput.close();
		       } catch (IOException e) {
			    e.printStackTrace();
		        } 
           }
    
    
    void showProducts() {
        try {
            fileContents.addAll(Files.readAllLines(Paths.get("Stocks.txt")));
            for (String line : fileContents) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    void showLowStock(){
                  System.out.print("**************************************");
                  System.out.printf("\n%20s","Low stocks");
                  System.out.println("\n**************************************\n");
                     for (Map.Entry<String, ArrayList<String>> entry : stockLevel.entrySet()) {
			    
			    ArrayList<String> list = new ArrayList<String>();
			    list.addAll(entry.getValue());
			    for(String line : list){
			      String[] iteamInfo = line.split("-");
			        if(Integer.parseInt(iteamInfo[2]) < 5){
			             
			              System.out.print("-------------------------------------------");
					System.out.printf("\n%25s",entry.getKey());
					System.out.println("\n-------------------------------------------");
				      System.out.printf("\n%-20s %-20s","Product Name: ",iteamInfo[0]);
				      System.out.printf("\n%-20s %-20s","Product Quantity: ",iteamInfo[2]);
				      System.out.println("\n");
			       }	      
			      
			   }
			}
    }
}



class iteam extends stock{

                  iteam(){
                         super.addproductsToFile();
                  }
     
		   void showIteam(String category){
		   try{
		       System.out.print("\033[H\033[2J");
		       if(super.stockLevel.containsKey(category)){
			       System.out.println("**************************************");
			       System.out.println("	       "+category+"            ");
		      	System.out.println("**************************************");
	      	}
	      	        int count = 0;
		        for(String line : stockLevel.get(category)){
		             count++;
			      String[] iteamInfo = line.split("-");
			      System.out.printf("\n%-20s %-20s","Product Index: ", count);
				System.out.printf("\n%-20s %-20s","Product Name: ",iteamInfo[0]);
				System.out.printf("\n%-20s %-20s","Product Price: ",iteamInfo[1]);
				System.out.printf("\n%-20s %-20s","Product Quantity: ",iteamInfo[2]);
			       System.out.printf("\n%-20s %-20s","Product Quality: ",iteamInfo[3]);
				System.out.printf("\n%-20s %-20s","Product Grade: ",iteamInfo[4]);
				System.out.println("\n");
			}	
		      }catch (Exception e) {
                            System.out.println("No category found..");
                    }
		   }
}

