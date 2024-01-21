package Models;

import java.util.Arrays;

public class MonthCronField extends CronField{

	public MonthCronField(String data) {
		super(data);
		 name = "minute";
		for(int i=1;i<=12;i++)
			this.validIntValues.add(i + "");
		validTextValues =   Arrays.asList("JAN",
	            "FEB",
	            "MAR",
	            "APR",
	            "MAY",
	            "JUN",
	            "JUL",
	            "AUG",
	            "SEP",
	            "OCT",
	            "NOV",
	            "DEC"
	            );
		
	
	}
	
	
}
