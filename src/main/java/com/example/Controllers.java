package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Controllers {

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String get(Model model) {
		HashMap<String, List<String>> plugin2ScriptList = geneatePlugin2ScriptList();
		model.addAttribute("plugin2ScriptList", plugin2ScriptList);

		HashMap<String, String> selectedScripts = new HashMap<>();

		plugin2ScriptList.entrySet().stream().forEach(e -> {
			selectedScripts.put(e.getKey(), "");
		});

		model.addAttribute("selectedScripts", new SelectedScripts(selectedScripts));

		return "home";
	}

	@RequestMapping(path = "/", method = RequestMethod.POST)
	@ResponseBody
	public String post(@ModelAttribute SelectedScripts selectedScripts) {
		List<String> collect = selectedScripts.getSelectedScripts().entrySet().stream()
				.map(e -> "Using script " + e.getValue() + " for plugin " + e.getKey()).collect(Collectors.toList());
		return String.join("<br/>", collect);
	}

	private HashMap<String, List<String>> geneatePlugin2ScriptList() {
		HashMap<String, List<String>> plugin2ScriptList = new HashMap<>();
		IntStream.range(1, randomInt(4)).forEach(e -> {
			String pluginName = "plugin-" + Integer.toString(e);
			plugin2ScriptList.put(pluginName, IntStream.range(1, randomInt(e))
					.mapToObj(s -> pluginName + "_scipt-" + Integer.toString(s)).collect(Collectors.toList()));
		});
		return plugin2ScriptList;
	}

	private int randomInt(int seed) {
		int Min = 3;
		int Max = 6;
		Random generator = new Random();
		if (seed != 0) {
			generator.setSeed(seed);
		}
		return generator.nextInt(Max - Min) + Min;
	}

}
