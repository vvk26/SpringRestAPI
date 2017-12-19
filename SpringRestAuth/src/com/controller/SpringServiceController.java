package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DAO.UserDAO;
@RestController
@RequestMapping("/RestAPI")
public class SpringServiceController {

	@Autowired UserDAO userDetails;
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String getIntrest(@RequestParam String userName, @RequestParam String password, @RequestParam String prAmt,@RequestParam String fromDate, @RequestParam String toDate ) {

		boolean status = userDetails.checkLogin(userName, password);

		if(!status)
			return "<html><b>Please chcek Username/Password</b></html>";

		String getIntrest = userDetails.getIntrest(userName, password);

		if (getIntrest != null && !"".equalsIgnoreCase(getIntrest)) {
			return "<html><b> Hi..."+userName+" your Interest is"+getIntrest+"</b></html>";
		}

		final double interestRate =  0.03875;
		double year = 0.00;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		   try{
		   Date  d1 = format.parse(fromDate);
		   Date  d2 = format.parse(toDate);

			//in milliseconds
		   double diffInDays =  ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
		    year = diffInDays/365;

		   }catch(Exception e){
			   e.printStackTrace();
		   }

		double interest = Double.parseDouble(prAmt)*(1 + interestRate*year);

		int count = userDetails.insertIntrest(userName, String.valueOf(interest));

		if(count != 1)
			return "<html><b>Error Occured while Updating Interest</b></html>";

		return "<html><b> Hi..."+userName+" your Interest is updated successfuly</b></html>";

	}

	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
	public String insertUsers(@RequestParam String userName, @RequestParam String password) {

		int status = userDetails.insertUser(userName, password);

		if(status == 11)
			return "<html><b>username already exists!! please change and try</b></html>";
		else
			return "<html><b>user inserted Successfully..</b></html>";
	}
}