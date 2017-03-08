package com.example;

import java.util.HashMap;

public class SelectedScripts {
	HashMap<String, String> selectedScripts;

	public SelectedScripts(HashMap<String, String> selectedScripts) {
		super();
		this.selectedScripts = selectedScripts;
	}

	public HashMap<String, String> getSelectedScripts() {
		return selectedScripts;
	}

	public SelectedScripts() {
		super();
	}

	public void setSelectedScripts(HashMap<String, String> selectedScripts) {
		this.selectedScripts = selectedScripts;
	}

}
