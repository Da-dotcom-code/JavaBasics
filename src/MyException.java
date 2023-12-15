
public class MyException extends Exception  {
	
	public MyException (String s) {
		super(s);
	}
	
	static int test (int toTest) throws Exception {
		if (toTest % 2 == 0) return toTest;
		else if (toTest % 3 == 0) throw new ArithmeticException ();
		else if (toTest % 5 == 0) throw new IllegalArgumentException("Exception Type IAE");
		else throw new MyException("Zahl " + toTest + " nicht erlaubt!"); 
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 21; i++) {
			try {
				int a = test(i);
				System.out.println(a);
			} catch (Exception e) {
				if (e instanceof ArithmeticException ) System.out.println("Exception Type AE");
				if (e instanceof IllegalArgumentException) System.out.println(e.getMessage());
				if (e instanceof MyException) System.out.println(e.getMessage());
			}
		}
		
	}

}