package arun;
import java.util.ArrayList;
public class SampleJava 
{
	public static void main(String x[]) 
	{
		System.out.println("Arun Balaji");
		ArrayList<String>a=new ArrayList<String>(); 
		a.add("arun");
		a.add("balaji");
		a.add("hiii");
		a.remove(0);
		if(a.isEmpty())
		{
			System.out.println("My commit to git");
		}
		else
		{
			System.out.println(a);	
		}
	}
}
