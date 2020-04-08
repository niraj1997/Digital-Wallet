package src;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class DigitalWallet {

	List<User> users;
	Map<String,User> usernameToUser;
	Map<String,Double> usernameToBalance;
	
	
	public static void main(String[] args) {
		System.out.println("********");
		Scanner sc = new Scanner(System.in);
		
//		Comparator<User> comp = (User d1, User d2) ->  new Integer(d1.getTxn_statemaent().size()).compareTo(new Integer(d2.getTxn_statemaent().size()));
		
 		List<User> users = new ArrayList<User>();
		@SuppressWarnings("unchecked")
		Map<String,User> usernameToUser = new TreeMap<String, User>((Map<? extends String, ? extends User>) new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				if(o1.getTxn_statemaent().size() > o2.getTxn_statemaent().size())
					return 1;
				return 0;
			};
		});

		while(true) {
			String input = sc.nextLine();
			String[] splitedInput = input.split(" ");

			if(splitedInput[0].equalsIgnoreCase("CreateWallet")) {
				if(!isUserPresent(splitedInput[1],usernameToUser)) {
					
					createuser(splitedInput,users,usernameToUser);	
					
				} else {
					
					printError("User Already Present ---- ");
	
				}
			} else if(splitedInput[0].equalsIgnoreCase("Overview")) {
				
				printOverview(users);
			
			} else if(splitedInput[0].equalsIgnoreCase("TransferMoney")) {
				
				transfer(splitedInput,usernameToUser);
				
				checkOffer1(splitedInput,usernameToUser);
				
			} else if(splitedInput[0].equalsIgnoreCase("Offer2")) {
			
				applyOffer2(usernameToUser);
				
			} else if(splitedInput[0].equalsIgnoreCase("Statement")) {
				
				printStatement(splitedInput,usernameToUser);
			
			} else if(splitedInput[0].equalsIgnoreCase("FixedDeposit")) {
				
			} else {
				break;
			}
		}
	}


	private static void applyOffer2(Map<String, User> usernameToUser2) {
		// TODO Auto-generated method stub
		if (usernameToUser2.size()>3) {
			Set<Map.Entry<String,User> > user1 = usernameToUser2.entrySet();
			int cn = 0;
			for(Map.Entry<String,User> entry : user1) {
				if(cn==3)
					break;
				cn++;
				User u1 = entry.getValue();
				if(cn==0) {
					creditBalance(u1, (double)10);
					addTxnStatement(u1, "offer2", (double) 10);
				} else if(cn==1) {
					creditBalance(u1, (double)5);
					addTxnStatement(u1, "offer2", (double) 5);
				} else if(cn==2) {
					creditBalance(u1, (double)2);
					addTxnStatement(u1, "offer2", (double) 2);
				}
			}
			
		}
	}


	private static void printStatement(String[] splitedInput, Map<String, User> usernameToUser) {
		// TODO Auto-generated method stub
		
		if(!isUserPresent(splitedInput[1], usernameToUser))
			printError("User not Present");
		
		User user = usernameToUser.get(splitedInput[1]);
		List<String> statement = user.getTxn_statemaent();
		
		for(String st : statement) 
			System.out.println(st);
		
	}


	private static void checkOffer1(String[] splitedInput, Map<String, User> usernameToUser) {
		// TODO Auto-generated method stub
		User user1 = usernameToUser.get(splitedInput[1]);
		User user2 = usernameToUser.get(splitedInput[2]);
		
		if(user1.getWallet().getBalance() == user2.getWallet().getBalance()) {
			creditBalance(user1, (double) 10);
			creditBalance(user2, (double) 10);
			
			addTxnStatement(user1, "offer1", (double) 10);

			addTxnStatement(user2, "offer1", (double) 10);
		}
		
		
	}


	private static void transfer(String[] splitedInput, Map<String, User> usernameToUser) {
		// TODO Auto-generated method stub
		if(!isUserPresent(splitedInput[1], usernameToUser) || !isUserPresent(splitedInput[2], usernameToUser)) {
			printError("User is not Present");
		} else {
			
			User user1 = usernameToUser.get(splitedInput[1]);
			User user2 = usernameToUser.get(splitedInput[2]);
			Double amount  = Double.parseDouble(splitedInput[3]);

			deductBalance(user1,amount);
			creditBalance(user2,amount);

			addTxnStatement(user2,"debit",amount);

			addTxnStatement(user1,"credit",amount);
		}
	}


	private static void addTxnStatement(User user1, String tx, Double amount) {
		// TODO Auto-generated method stub
		List<String> txn_user1 = user1.getTxn_statemaent();
		
		switch (tx) {
		
		case "credit":
			txn_user1.add(user1.getName() + "credit" + amount);
			break;
		case "debit":
			txn_user1.add(user1.getName() + "debit" + amount);
			break;
		case "offer1":
			txn_user1.add("offer1 credit" + amount);
			break;
		case "offer2":
			txn_user1.add("offer2 credit" + amount);
			break;
		}
		
		user1.setTxn_statemaent(txn_user1);
		
	}


	private static void creditBalance(User user1, Double amount) {
		// TODO Auto-generated method stub
		Double balance_user1 = user1.getWallet().getBalance();
		Wallet wallet_user1  = user1.getWallet();
		wallet_user1.setBalance(balance_user1+amount);	
		user1.setWallet(wallet_user1);
		
	}


	private static void deductBalance(User user1, Double amount) {
		// TODO Auto-generated method stub
		if(user1.getWallet().getBalance() < amount || amount < 0.0001) {
			printError("Amount can not be deductable");
		}
		
		Double balance_user1 = user1.getWallet().getBalance();
		Wallet wallet_user1  = user1.getWallet();
		wallet_user1.setBalance(balance_user1-amount);	
		user1.setWallet(wallet_user1);
		
	}


	private static void printError(String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
	}


	private static void printOverview(List<User> users) {
		// TODO Auto-generated method stub
		for(User user : users) {
			System.out.println(user.getName() + " " + user.getWallet().getBalance());
		}
	}


	private static void createuser(String[] splitedInput, List<User> users, Map<String, User> usernameToUser) {
		// TODO Auto-generated method stub
		List<String> txn_statemaent = new ArrayList<String>();
		List<String> offer_statement = new ArrayList<String>();
		Wallet wallet = new Wallet(LocalDateTime.now(), Double.parseDouble(splitedInput[2]), 0);
		User newUser = new User(splitedInput[1],wallet,txn_statemaent,offer_statement);

		users.add(newUser);
		usernameToUser.put(newUser.getName(), newUser);
	}


	private static boolean isUserPresent(String string, Map<String, User> usernameToUser) {
		return usernameToUser.containsKey(string);
	}
	
}
