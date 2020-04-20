import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Random;
import java.math.BigInteger;

public class RabinKarp1 
{
	static void rabinfindPattern(String text,String pattern){
		/*
	    q a prime number
	    p hash value for pattern
	    t hash value for text
	    d is the number of unique characters in input alphabet
		 */
		int d=128;
		int q=107;
		int end=text.length();
		int m=pattern.length();
		int t=0,p=0;
		int h=1;
		int i,j;
		int start = 0;
		//hash value calculating function
		for (i=0;i<m-1;i++)
			h = (h*d)%q;
		for (i=0;i<m;i++){
			p = (d*p + pattern.charAt(i))%q;
			t = (d*t + text.charAt(i))%q;
		}
		//search for the pattern 
		for(i=0;i<end-m;i++){
			if(p==t){
				//if the hash value matches match them character by character
				for(j=0;j<m;j++)
					if(text.charAt(j+i)!=pattern.charAt(j))
						break;
				if(j==m && i>=start)
					System.out.println("Pattern match found at index "+i);            
			}
			if(i<end-m){
				t =(d*(t - text.charAt(i)*h) + text.charAt(i+m))%q;
				if(t<0)
					t=t+q;
			}    
		}                                
	}

	public static void main(String[] args) {    
		rabinfindPattern("I love yoe ve move. Plovse, love me.", "love");       
	}
}