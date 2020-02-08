import java.util.Random;


public class RSA {
	
	
	boolean ch = false;
	public int[] extended_Euclid(int p, int q){
		
			      if (q == 0)
			         return new int[] { p, 1, 0 };

			      int[] gcdArr = extended_Euclid(q, p % q);
			      
			      int r = gcdArr[0];
			      int x = gcdArr[2];
			      int y = gcdArr[1] - (p / q) * gcdArr[2];
			      return new int[] { r, x, y };
			 
	}
	
	
	long find_inverse(long a, long m) {
		if(m>=2) {
			
		int [] gcdAraay = extended_Euclid((int)a,(int)m);
		if(gcdAraay[0]==1)	{
			return gcdAraay[1] % m;
		}
		else 
			System.out.print("ther is no inverse ");
		return -1;		
					
		}
		return -1;
	}

	public  int random_prime() {
		
		Random random = new Random();
		
		int k =random.nextInt(10000);
		
		while(true) {
			if(k>1) {
				break;			 
			}
			 k =random.nextInt(10000);
		}
		
		if(!isPrime(k)) {
		while(true){
			if(isPrime(k)&&k>1) {
				break;
			}
		k=random.nextInt(10000);
		}
		}
		
		return k;
	
	}
	
	public  boolean  isPrime(int p) {
		
		if(p==2)
			return true;
		 if (p <= 1) 
	            return false; 
		  for (int i = 2; i < p; i++) 
	            if (p % i == 0) 
	                return false; 
	       
	        return true;
		
		
	}
	
	public long[] generate_keys() {
		
		long p=random_prime();
		long q=random_prime();

		
		while(p==q)
			q=random_prime();
		
		long e =random_prime();

		long n = p*q;
		long z = (p-1)*(q-1);
		
		int[]ll = extended_Euclid((int)e,(int)z);
		
		while(true) {
			if(e>1&&e<z&&ll[0]==1) {
				break;
			}
						e=random_prime();
			ll = extended_Euclid((int)e,(int)z);
		}
		
		long d = find_inverse(e,z);
		while(d<0) {
			 p=random_prime();
			 q=random_prime();		
			while(p==q)
				q=random_prime();
			
			 e =random_prime();

			 n = p*q;
			 z = (p-1)*(q-1);
			
			ll = extended_Euclid((int)e,(int)z);
			while(true) {
				if(e>1&&e<z&&ll[0]==1) {
					break;
				}
							e=random_prime();
				ll = extended_Euclid((int)e,(int)z);
			}	
			d = find_inverse(e,z);
		}
			
		long x[] = {e,n,d};
		
		
		return x;
	}
	
	
	long modular_exponentiation(long b, long n, long m) {
		
		
		long x =1;
		long power = b%m;
		
		String bin = ""+Long.toBinaryString(n);
	
		
		int len = bin.length();
		
		
		for(int i=len-1;i>=0;i--) {
			
			if(bin.charAt(i) == '1' ) {
			
				x= (x*power)%m;}
			
	
				power=(power*power)%m;
							
		}
	
		return x;
		
	}
	
	
	long string_to_int(String text) {
		//System.out.println(text);
	text = text.toLowerCase();
		char x;
		String con="";
		long k=0;
		//System.out.println(text.length());
		for(int i=0;i<text.length();i++) {
			x=text.charAt(i);
		for(char S = 'a' ; S<x;++S) {
			k++;
		}
		if(k<10) {
			con += "0"+k;
			
		}
		else 
			con += ""+k;
		//System.out.println(con);
			
		k=0;
		}
//	System.out.println(con);
	
		int h = Integer.parseInt(con);
		//System.out.println(h);
	//	System.out.println(h);
		
		return h;
	}
	
	
	String int_to_String(long inttext) {
		
		String j ;
		
		if(inttext<10) {
			j= "000"+inttext;
		}
		else if(inttext<100) {
			j= "00"+inttext;
		}
		else if(inttext<1000) {
			j= "0"+inttext;
		}
		else
		j= ""+inttext;
		
		String [] arra = new String [j.length()];
		int c =0,s;
		String con="";
		for(int i=0 ,h=2,k=0;k<arra.length/2;) {
			c=0;
			s=Integer.parseInt(j.substring(i,h));
			h=h+2;
			i=i+2;
			k++;
		for(char x='a'; x<'z';++x) {
			if(c == s) {
				con +=""+x;
				break;
			}
			
			c++;
			
		}
		}
		
		return con;
	}
	

	long[] encrypt(String plaintext, long e, long n) {
		
		int strlength = plaintext.length();
		char s =' ';
       long k,val2;
       String l;
       long msg[];
       
       if(plaintext.length()%2!=0) {
    	   //System.out.println("jj");
    	   ch=true;
		msg = new long [(strlength/2)+1];}
       else
    	   msg = new long [strlength/2];
		int b =2;
		int ss=0;
		
		for ( int i =0;i<plaintext.length()/2;i++) {
			l=plaintext.substring(ss,b);
			ss=b;
			b=b+2;	
		
			k= string_to_int(""+l);		
			val2=modular_exponentiation(k,e,n);
			msg[i]=val2;
		}
		
		if(plaintext.length()%2!=0) {
		
			l=plaintext.substring(plaintext.length()-1,plaintext.length());
	 	//System.out.println(l);
			k= string_to_int(""+l);
			val2=modular_exponentiation(k,e,n);
			//System.out.println(val2);
			msg[msg.length-1]=val2;
			//System.out.println(msg.length);
		}
		
		
		return msg;
	}
	
	
	
    String decrypt(long[] ciphertext, long d, long n) {
    	
long val2;
String testing ;
String k ="";

	for(int i=0;i<ciphertext.length;i++)	{
		k+=int_to_String(modular_exponentiation(ciphertext[i],d,n));	
	}
		if(ch) {
			String j=k;
			k=k.substring(0,k.length()-2);
			k+=j.substring(j.length()-1,j.length());
		}
    	
    	
		return k;
	}
}
