import java.util.Scanner;


public class BigInt
{
  private int arrayLength = 256;
  private int lengthUsed;
  char[] numberArray = new char[arrayLength];
  Scanner keyboard = new Scanner(System.in);

  public BigInt(String numberString)
  {
    checkLength(numberString);
    numberArray = convertToCharArray(numberString);
    lengthUsed = numberString.length();
  }


  public BigInt()
  {
    String uInput = getNumFromStdIn();
    numberArray = convertToCharArray( uInput );
    setLengthUsed();
  }

  /*
  public BigInt(String numberString, int newArrayLength)
  {
    lengthUsed = numberString.length();
    setArrayLength(newArrayLength);
    checkLength(numberString);
    numberArray = convertToCharArray(numberString);
  }
  */

  public void checkLength(String longString)
  {
    while (longString.length() >= this.getArrayLength() )
    {
      this.setArrayLength(this.getArrayLength()*2);
    }
  }


  public void checkOtherArrayLength(BigInt otherBigInt)
  {
    if (this.getArrayLength() < otherBigInt.getArrayLength())
    {
      this.setArrayLength(otherBigInt.getArrayLength() );
      this.setLengthUsed();

    }
    if (this.getArrayLength() > otherBigInt.getArrayLength())
    {
      otherBigInt.setArrayLength(this.getArrayLength() );
      otherBigInt.setLengthUsed();
    }

  }


  public char[] makeZeroArray()
  {
    char[] newArray = new char[getArrayLength()];
    for (int r=0; r<getArrayLength(); r++)
    { 
      newArray[r] = '0';
    }
    return newArray;
  }


  public String getNumFromStdIn()
  {
    System.out.println("Please enter an integer:");
    String userInput = keyboard.next();
    checkLength(userInput);
    return userInput;
  }


  public char[] convertToCharArray(String myString)
  {
    char[] newNumArray = makeZeroArray();
    int i,j;
    for (i=myString.length()-1, j=0; i>=0; i--, j++)
    {
      newNumArray[j] = myString.charAt(i);
    }
    return newNumArray;
  }


  public int getLengthUsed()
  {
    return lengthUsed;
  }


  public void setLengthUsed()
  {
    this.lengthUsed=deleteTrailingZeros( new String(this.numberArray) ).length();
//    System.out.println("lengthUsed = "+getLengthUsed());
  }


  public void setArrayLength(int newSize)
  {
    this.arrayLength = newSize;
  }


  public int getArrayLength()
  {
    return arrayLength;
  }

  
  public String toString()
  {
    setLengthUsed();
    String numberStringRemade = "";
    for(int k=getLengthUsed()-1; k>=0; k--)
    {
      numberStringRemade = numberStringRemade + numberArray[k];
    }
    return numberStringRemade;
  }


  public BigInt sum(BigInt otherBigInt)
  {
    checkOtherArrayLength(otherBigInt);
    int carry=0;
    int column;
    char[] sum=makeZeroArray();
    for (int m=0; m<this.getArrayLength(); m++)
    {
      column=Character.getNumericValue(this.numberArray[m]) +
             Character.getNumericValue(otherBigInt.numberArray[m]) +
             carry;
      if (column >= 10 )
      {
        sum[m]=Character.forDigit(column-10, 10);
        carry=1;
      }
      else
      {
        sum[m]=Character.forDigit(column, 10);
        carry=0;
      }
    }
    String sumString = "";
    for (int p=0; p<sum.length; p++)
    {
      sumString = sum[p] + sumString;
    }
    return new BigInt( deleteLeadingZeros(sumString) );
  }


  public String deleteLeadingZeros(String numberStr)
  {
    int firstNonZero=0;
    for (int r=0; r<numberStr.length(); r++)
    {
      if (numberStr.charAt(r)!='0' && numberStr.charAt(r)!='\0')
      {
        firstNonZero=r;
        break;
      }
    }
    return numberStr.substring( firstNonZero, numberStr.length() );
  }

  
  public String deleteTrailingZeros(String numberStr2)
  {
    int firstNonZeroFromEnd=numberStr2.length();
    for (int s=numberStr2.length()-1; s>=0; s--)
    {
      if (numberStr2.charAt(s)!='0' && numberStr2.charAt(s)!='\0')
      {
        firstNonZeroFromEnd=s;
        break;
      }
    }
    return numberStr2.substring( 0, firstNonZeroFromEnd+1 );
  }


  public void displayArrayContents()
  {
    
    for (char itemThis : this.numberArray)
    {
      System.out.print(itemThis+ " ");
    }
  }

  
  public int compare(BigInt otherBigInt)
  {
    checkOtherArrayLength(otherBigInt);
    if ( this.getLengthUsed() > otherBigInt.getLengthUsed() )
    {
      return 1;
    }
    else if (this.getLengthUsed() < otherBigInt.getLengthUsed() )
    {
      return -1;
    }
    else
    {
      for (int n=0; n<this.getLengthUsed(); n++)
      {
        if (this.numberArray[n] > otherBigInt.numberArray[n])
        {
          return 1;
        }
        if (this.numberArray[n] < otherBigInt.numberArray[n])
        {
          return -1;
        }
      }
      return 0;
    }
  }
}