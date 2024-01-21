package Models;

public class DayCronField extends CronField{

	public DayCronField(String data) {
		super(data);
		name = "day";
		for(int i=1;i<=31;i++)
				this.validIntValues.add(i+"");
	}
	
}
