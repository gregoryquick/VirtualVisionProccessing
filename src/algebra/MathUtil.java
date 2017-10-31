package algebra;

public class MathUtil {
	
	//Simple looped factorial implementation
	public static int factorial(int in) throws Exception {
		if(in < 0)
			throw new Exception("Factorial not defined for negatives");
		int out = 1;
		for(int i = 2; i <= in;i++) {
			out *= i;
		}
		return out;
	}
	
	//Simple n choose k function
	public static int choose(int numberFrom,int numberChosen) throws Exception {
		if(numberFrom < 0 || numberChosen < 0 || numberChosen > numberFrom)
			throw new Exception("Invalid choose inputs");
		int out = factorial(numberFrom);
		out /= factorial(numberFrom - numberChosen);
		out /= factorial(numberChosen);
		return out;
	}

}
