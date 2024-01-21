package MainClass;

import MainClass.Calculate;

public class MainC {
	
	public static void main(String[] args) {
		//Calculate obj = new Calculate();
		System.out.println("jata : " +  args.length );	
		Calculate obj = new Calculate();
		System.out.println(obj.buildCronExp(args));
//		return args.length;
	}

}
