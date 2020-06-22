import java.util.Random;

public class RAStest {
	public static void main(String[] ars) {
  
		RSA x = new RSA();
		//-----------------------------------(random_prime)------------------------------------------------------------------
		System.out.println("Random_prime |" + x.random_prime() + "|");
		System.out.println("---------------------------------------------------");
		//-----------------------------------(extended_Euclid)------------------------------------------------------------------
		int gcdArray[] = x.extended_Euclid(252, 198);
		System.out.println(	"the Gcd for (252,195) = " + gcdArray[0] + " |x = " + gcdArray[1] + "| |y = " + gcdArray[2] + "|");
		System.out.println("---------------------------------------------------");
		//-----------------------------------(find_inverse)------------------------------------------------------------------
		System.out.println("the inverse for (101,4620) |" + x.find_inverse(101, 4620) + "|");
		System.out.println("---------------------------------------------------");
		//-----------------------------------(modular_exponentiation)------------------------------------------------------------------
		System.out.println("Modular_exponentiation (3,94,17) is |" + x.modular_exponentiation(3, 1011110, 17) + "|");
		System.out.println("---------------------------------------------------");
		//-----------------------------------(string_to_int)------------------------------------------------------------------
		System.out.println("String to int (kbl) = |" + x.string_to_int("abc") + "|");
		System.out.println("---------------------------------------------------");
		//-----------------------------------(int_to_String)------------------------------------------------------------------
		System.out.println("int to String (100111) : |" + x.int_to_String(0001) + "|");
		System.out.println("---------------------------------------------------");
		//-----------------------------------(generate_keys)------------------------------------------------------------------
		long[] k = x.generate_keys();
		long e = k[0];
		long n = k[1];
		long d = k[2];
		System.out.println("n = " + n + " e = " + e + " d =" + d);
		System.out.println("---------------------------------------------------");
		//-----------------------------------(encrypt)------------------------------------------------------------------
		System.out.println("encrypt \"stop \" :");
		System.out.println();
		long[] ar = x.encrypt("stop", e, n);
		for (int i = 0; i < ar.length; i++) {
		System.out.print(ar[i] + " ");}	
		System.out.println();	
		System.out.println("---------------------------------------------------");
		//-----------------------------------(decrypt)------------------------------------------------------------------
		System.out.println("decrypt |" + x.decrypt(ar, d, n) + "|");
		Random random = new Random();
		int aa=random.nextInt(27);
		System.out.println(aa);
	}
}
