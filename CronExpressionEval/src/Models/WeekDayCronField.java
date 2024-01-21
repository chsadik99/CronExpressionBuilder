package Models;

import java.util.*;

public class WeekDayCronField extends CronField{

	public WeekDayCronField(String data) {
		super(data);
		name = "weekday";
		for(int i=0;i<7;i++)
				validIntValues.add(i+"" );
		
		validTextValues = Arrays.asList(  "SUN",
	            "MON",
	            "TUE",
	            "WED",
	            "THU",
	            "FRI",
	            "SAT"
	            );
	}
	
}
