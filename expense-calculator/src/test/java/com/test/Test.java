package com.test;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.npeetha.expensetracker.bo.Account;
import com.npeetha.expensetracker.managers.IAccountManager;

public class Test {

	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				"file:///Users/nimilpeethambaran/MaProjects/ip/ip/ip-web/src/main/webapp/WEB-INF/Spring.xml");
		IAccountManager userManager = (IAccountManager) ctx.getBean("accountManager");

		Account a = new Account();
		String id;
//		a.setBudget(100d).setDescription("t").setName("bla").setId(UUID.randomUUID().toString()).setLastUpdatedBy("npeetha").setUpdateDate(new Date());
//		String id = userManager.createAccount(a);
//		
//
//		System.out.println("User inserted!"+ id);
		List<Account> users = userManager.getAccounts();
//		for(Account user : users){
//			System.out.println("User :"+user.toString());
//			
//		}
		
		 a = users.get(0);
		 a.setLastUpdatedBy("krishna");
		 a.setName("Change");
		 System.out.println(a.toString());
		 
		 id = userManager.updateAccount(a);
		 System.out.println("User updated!"+ id);
		 
		 a = userManager.getAccount(id);
		 System.out.println("User retried"+a.toString());
		 
		 userManager.deleteAccount(id);
		 System.out.println("User deleted!"+ id);
		 users = userManager.getAccounts();
		System.out.println("\nUser list fetched!" + "\nUser count: " + users.size());
	}
	
}
