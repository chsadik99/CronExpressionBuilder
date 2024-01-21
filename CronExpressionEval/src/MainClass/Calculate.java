package MainClass;

import java.util.Arrays;

import Models.DayCronField;
import Models.MinuteCronField;
import Models.MonthCronField;
import Models.CronField;
import Models.WeekDayCronField;
import Models.HourCronField;

public class Calculate {

	String intRegex = "-?\\d+(\\.\\d+)?";
	
	public Calculate() {}
	
	public String buildCronExp(String []args) 
	{
		if(args.length < 1)
			return "No cron expression provided to parse";
		try {
			String []cronFieldValues =  args[0].split(" ");
			if(cronFieldValues.length < 6)
				return "Insufficient numbers of parameters passed.";
		CronField []cronField = new CronField[] {new MinuteCronField(cronFieldValues[0]),
				new HourCronField(cronFieldValues[1]),
				new DayCronField(cronFieldValues[2]),
				new MonthCronField(cronFieldValues[3]),
				new WeekDayCronField(cronFieldValues[4])};
		
		StringBuilder finalCronExpression = new StringBuilder();
		
		for(int i=0;i<5;i++)
		{
			finalCronExpression.append(cronField[i].getName() );
			
			for(int j=cronField[i].getName().length();j<20;j++)
				{
				finalCronExpression.append(" ");
				}
			
			finalCronExpression.append(  expand(cronField[i]) + "\n");
			
		}
		return finalCronExpression.toString();
		
		}
		catch(Exception ex)
		{
			return ex.getMessage();
		}
		
	}
	
	String expand(CronField cronField) throws Exception
	{
		if( cronField.getData().equals("*"))
			return   String.join(" ", cronField.getValidIntValues());
				
		if(cronField.getData().contains(","))
			return expandList(cronField);
		
		 if(cronField.getData().contains("-"))
			return expandInterval(cronField);
		 
		if(cronField.getData().contains("/"))
			return expandData(cronField);
		
		if(cronField.getValidIntValues().contains(cronField.getData()) ||  cronField.getValidTextValues().contains(cronField.getData()) )
			return cronField.getData();
		
		throw new Exception("Not Valid Data");
		
	}
	
	private String expandData(CronField cronField) throws Exception{
		String []interv = cronField.getData().split("/");
		StringBuilder cronExp = new StringBuilder();
		if(interv.length!=2)
			{
			throw new Exception("Invalid format for " + cronField.getName() +" only 2 fileds allowed");
			}
		if(!interv[1].matches(intRegex) )
			{
			throw new Exception("Invalid format for " + cronField.getName() +" interval. "+ interv[1] +" should be an integer ");
			}

		int multiple =  Integer.parseInt(interv[1]); 
		
		if(interv[0].equals("*") )
		{
			for(String ch1: cronField.getValidIntValues())
			{
				if( Integer.parseInt(ch1) %multiple==0 )
					{
					cronExp.append( ch1 + " ");
					}
				
			}
		}
		
		if(!interv[1].matches(intRegex) )
			{
			throw new Exception("Invalid format for " + cronField.getName() +" interval. "+ interv[1] +" should be an integer or '*'");
			}

	
		int start = Integer.parseInt(interv[0]); 
		for(String ch1: cronField.getValidIntValues())
		{
			if( Integer.parseInt(ch1) > start && Integer.parseInt(ch1) %multiple==0 )
				{
				cronExp.append( ch1 + " ");
				}
				
		}
	
	
		return cronExp.toString();
	}

	private String expandInterval(CronField cronField) throws Exception {
		String []interv = cronField.getData().split("-");
		
		if(interv.length!=2)
			{
			throw new Exception("Invalid format for " + cronField.getName() +" only 2 fields allowed.");
			}
	
		
		if(interv[0].matches(intRegex) != interv[1].matches(intRegex))
			{
			throw new Exception("Invalid format for " + cronField.getName() +" range. Both should be of same type");
			}
		
		int start=0,end=0;
		if(interv[0].matches(intRegex))
		{
			start = cronField.getValidIntValues().indexOf(interv[0]);
			end = cronField.getValidIntValues().indexOf(interv[1]);
		}
		else {
			start = cronField.getValidTextValues().indexOf(interv[0]);
			end = cronField.getValidTextValues().indexOf(interv[1]);
		}
		
		if(start==-1 || end==-1)
			{
				throw new Exception("Invalid format for " + cronField.getName() +" range. Invalid start/end value for range expr.");
			}
		
		if(start>end)
			{
			throw new Exception("Invalid format for " + cronField.getName() +" range. "+start +"  should be <= " +end);
			}
		
		return String.join(" ",  cronField.getValidIntValues().subList(start, end+1) );
				
	}

	private String expandList(CronField cronField) throws Exception{
		String []list = cronField.getData().split(",");
		boolean isIntType = list[0].matches(intRegex);
		StringBuilder cronExp = new StringBuilder();
		
		for(String val:list)
		{
			if(val.matches(intRegex)!=isIntType)
			{
				throw new Exception("Invalid format for "+ cronField.getName() +" list. All values of list should have the same type.");
			}
			else if(isIntType)
			{
				if(cronField.getValidIntValues().contains(val)  )
					{
					cronExp.append(val + " ");
					}
				else
					{
					throw new Exception("Invalid format for "+ cronField.getName() +" list."+val +" not in allowed integer values for "+ cronField.getName());
					}

			}
			else {
				if(cronField.getValidTextValues().contains(val)  )
					{
					cronExp.append(val + " ");
					}
				else
					{
					throw new Exception("Invalid format for "+ cronField.getName() +" list."+val +" not in allowed text values for "+ cronField.getName());
					}

			}
		}
		return cronExp.toString() ;
	}

	
	
	
	
	
}
