package Models;

import java.util.*;

public class CronField {
	protected String name;
	protected List<String> validIntValues;
	protected List<String> validTextValues;
	private String data;
	
	public CronField(String data) {
		this.data = data;
		this.validIntValues = new ArrayList<>();
		this.validTextValues = new ArrayList<>();
	}
	
	
	
	
	public String getName() {
		return name;
	}
	
	public List<String> getValidIntValues() {
		return validIntValues;
	}
	
	public  List<String> getValidTextValues() {
		return validTextValues;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getData() {
		return data;
	}
}
