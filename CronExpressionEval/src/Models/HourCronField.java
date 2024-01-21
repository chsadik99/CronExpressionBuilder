package Models;

public class HourCronField extends CronField{

	public HourCronField(String data) {
		super(data);
		this.name = "hour";
		for(int i=0;i<24;i++)
			this.validIntValues.add(i +"");
	}
	
}
