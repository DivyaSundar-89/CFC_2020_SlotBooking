package com.cfc.cfcrest;

import org.springframework.stereotype.Controller;
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
	
	
}