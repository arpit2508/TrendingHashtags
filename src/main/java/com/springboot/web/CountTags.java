package com.springboot.web;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.Map;

public class CountTags {

	public Map<String, Integer> trending(String input) {
		HashMap<String, Integer> CountMap = new HashMap<String, Integer>();

		String regexPattern = "(#\\w+)"; // Regex for extracting hashtags
		String textbody = input; // getting the input text via POJO

		Pattern pattern = Pattern.compile(regexPattern); // Compiling the Regex
		Matcher matcher = pattern.matcher(textbody); // matching the text with Regex
		while (matcher.find()) {
			String hashtag = matcher.group(1);

			if (CountMap.containsKey(hashtag)) {

				int count = CountMap.get(hashtag);
				CountMap.put(hashtag, count + 1); // if tag already exist, increase the count by 1
			} else {

				CountMap.put(hashtag, 1); // if tag does not exist, initialize the count as 1
			}

		}

		Map<String, Integer> sortedMap = sortByValue(CountMap);

		return sortedMap;
	}

	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> inputmap) {
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(inputmap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> compare1, Map.Entry<String, Integer> compare2) {
				return (compare2.getValue()).compareTo(compare1.getValue());
			}
		});

		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}

}
