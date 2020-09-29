package com.fetchRewards.UniqueEmailsAPI.emailChecker;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // This annotation makes sure that the response is in JSON/XML format from the method
@RequestMapping("/emails")
public class EmailChecker {
	
	@GetMapping("/list/")
	public int getUniqueNumEmails(@RequestBody EmailWrapper emails) {
		return checkEmails(emails.getEmails());
	}
	
	/*
	 * Every email is checked with all the characters.
	 * Time Complexity - O(m*n) where m - number of characters in each email string,
	 * n - number of emails in the list.
	 * Space Complexity - HashSet is used. Worst space complexity can be when all emails are 
	 * actually unique and are added to the set. So O(m*n) to store all emails with all characters.
	 */
	private int checkEmails(List<String> emails) {
		if(emails == null || emails.size() == 0) return 0; // no emails, return 0
		Set<String> set = new HashSet<>(); //set will eliminate duplicates
		emails.forEach(email -> processEmail(email, set)); //populate the set
		return set.size();
	}
	/*
	 * Every character of the email is visited which makes the time complexity - O(m)
	 * where m is the number of characters.
	 */
	private void processEmail(String email, Set<String> set) {
		StringBuilder sb = new StringBuilder(); 
		boolean foundAtSign = false; //'@' sign 
		boolean plusSign = false; // '+' sign
		char c;
		for(int i = 0; i < email.length(); i++) {
			c = email.charAt(i);
			if(c == '.' && !foundAtSign) continue; // ignore all '.' before @ symbol
			if(c == '@') { // After @ symbol, nothing is ignored 
				foundAtSign = true;
				plusSign = false;
			}
			if(c == '+') plusSign = true; 
			if(!plusSign) sb.append(Character.toLowerCase(c)); // append only if '+' hasn't been visited.
		}
		//Add newly created StringBuilder to the Set.
		set.add(sb.toString()); // only unique addresses will be added to the set.
	}
	
	//A wrapper class used to get list of emails as one object through postman.
	public static class EmailWrapper{
		List<String> emails;

		public List<String> getEmails() {
			return emails;
		}

		public void setEmails(List<String> emails) {
			this.emails = emails;
		}
	}

}
