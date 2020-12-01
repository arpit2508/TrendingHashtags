package com.springboot.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.springboot.web.CountTags;
import com.springboot.web.model.TextModel;

@Controller
public class RequestController {
	@RequestMapping(value = { "/index" }, method = RequestMethod.GET) // mapping for http request for /index
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView(); // creating ModelAndView object
		modelAndView.setViewName("index"); // resources/template/index.html
		return modelAndView;
	}

	@RequestMapping(value = { "/check" }, method = RequestMethod.GET) // get http request for /check
	@ResponseBody
	public String process(TextModel model) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		CountTags sortedMap = new CountTags(); // Initializing CountTags class
		map = (HashMap<String, Integer>) sortedMap.trending(model.getInput()); // calling trending function from the
																				// CountTags class
		System.out.print(map); // Printing the output over the console, please note that here we are printing the whole sorted array.

		int MAXWIDTH = 10; // Setting up the with of column
		String output = null;
		List out = new ArrayList();

		Set<String> keys = map.keySet();
		String[] keysArray = keys.toArray(new String[keys.size()]);
		for (int i = 0; i < keysArray.length && i < 10; i++)      //Limiting top 10 hashtags
		{

			out.add(StringUtils.rightPad(keysArray[i].toString(), MAXWIDTH) + "|" // creating output List
					+ StringUtils.rightPad((map.get(keysArray[i]).toString()), MAXWIDTH));
		}
		output = out.toString(); // TypeCast List to String

		return output; // Returning the output to html page

	}
}