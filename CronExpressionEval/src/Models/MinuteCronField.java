package Models;

public class MinuteCronField extends CronField{

	public MinuteCronField(String val) {
		super(val);
		this.name = "minute";
		for(int i=0;i<60;i++)
			this.validIntValues.add(i+"");
	}
	
	
	
}
