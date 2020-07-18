package com.cfc.cfcrest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	@RequestMapping(method = RequestMethod.GET, value = "/getslotdetail/{customerId}")
	@ResponseBody
	public Vendor getAllStudents(@PathVariable("customerId") String customerId) {

		return VendorSlotDetails.getInstance().getSlot(customerId);
	}

	@RequestMapping(value = "/storeDonorDetail", method = RequestMethod.POST)
	@ResponseBody
	public DonorPOJO persistPerson(@ModelAttribute DonorPOJO person) {
		System.out.println("donorpojo" + person + person.getName() + person.getAddress() + person.getCategory()
				+ person.getDonornotes() + person.getEmail() + person.getPhone() + person.getRecommend()
				+ person.getTime());
		person.set_id("donor_" + person.getName());
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

		JdbcCustomerDAO customerDAO = (JdbcCustomerDAO) context.getBean("customerDAO");
		customerDAO.storeDonorIntoCludant(person);
		return person;
	}

	@RequestMapping(value = "/storeRecipientDetail", method = RequestMethod.POST)
	@ResponseBody
	public RecipientPOJO persistPerson(@ModelAttribute RecipientPOJO recipient) {
		System.out.println("recipientpojo" + recipient.getName() + recipient.getAddress() + recipient.getCategory()
				+ recipient.getPhone() + recipient.getRecommend());
		recipient.set_id("recipient_" + recipient.getName());
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

		JdbcCustomerDAO customerDAO = (JdbcCustomerDAO) context.getBean("customerDAO");
		customerDAO.storeRecipientIntoCludant(recipient);

		return recipient;
	}

}