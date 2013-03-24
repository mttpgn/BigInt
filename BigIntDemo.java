public class BigIntDemo
{
  public static void main(String args[])
  {
    BigInt userDefinedValue001=new BigInt();
    BigInt userDefinedValue002=new BigInt();
    System.out.println();
    System.out.println(userDefinedValue001);
    System.out.println(userDefinedValue002);
    System.out.println();
	  
    switch (userDefinedValue001.compare(userDefinedValue002))
    {
      case -1:
        System.out.println("Second number is larger.");
        break;
      case 0:
          System.out.println("Two numbers are equal");
          break;
      case 1:
          System.out.println("First number is larger");
          break;
    }
          
    System.out.println();       
    System.out.println("The sum of the two numbers is below.");
    System.out.println(userDefinedValue001);
    System.out.println(userDefinedValue002);
    System.out.println();
    System.out.println(userDefinedValue002.sum(userDefinedValue001));
  }
}
