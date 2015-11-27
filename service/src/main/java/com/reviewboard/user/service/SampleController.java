package com.reviewboard.user.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

	@RequestMapping("/")
	@ResponseBody
	public String helloWorld() {
		return "Hello, world";
	}

}
