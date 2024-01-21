package MainClass;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class  CalculateTest {

	@Test
	public void NoCronExprssion() {
		Calculate obj = new Calculate();
		String []arr = new String[0];
		String ret = obj.buildCronExp(arr);
		assertEquals("No cron expression provided to parse",ret);
		
	}

	@Test
	public void IncompleteCronExpr() {
		Calculate obj = new Calculate();
		String []arr = new String[] {"* *"};
		String ret = obj.buildCronExp(arr);
		assertEquals("Insufficient numbers of parameters passed.",ret);
		
	}

}
