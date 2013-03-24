import java.util.Scanner;


public class BigInt
{
  private int arrayLength = 512;
  private int lengthUsed;
  char[] numberArray = new char[arrayLength];
  Scanner keyboard = new Scanner(System.in);

 
  public BigInt(String numberString)
  {
    lengthUsed = numberString.length();
    checkLength(numberString);
    numberArray = convertToCharArray(numberString);
  }


  public BigInt()
  {
    String uInput = getNumFromStdIn();
    lengthUsed = uInput.length();
    numberArray = convertToCharArray( uInput );
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
    }
    if (this.getArrayLength() > otherBigInt.getArrayLength())
    {
      otherBigInt.setArrayLength(this.getArrayLength() );
    }

  }


  public char[] makeZeroArray()
  {
    char[] newArray = new char[getArrayLength()];
    for ( char entry : newArray )
    {
      entry = '0';
    }
    return newArray;
  }


  public String getNumFromStdIn()
  {
    System.out.println("Please enter an integer:");
    String userInput = keyboard.next();
    return userInput;
  }


  public char[] convertToCharArray(String myString)
  {
    char[] numArray = makeZeroArray();
    int i,j;
    for (i=myString.length()-1, j=0; i>=0; i--, j++)
    {
      numArray[i] = myString.charAt(j);
    }
    return numArray;
  }


  public void setArrayLength(int newSize)
  {
    this.arrayLength = newSize;
  }


  public int getArrayLength()
  {
    return arrayLength;
  }


    @Override
  public String toString()
  {
//    boolean numberHasBegun = false;
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
    for (int m=0; m<getArrayLength(); m++)
    {
      column=carry +
             Character.getNumericValue(this.numberArray[m]) +
             Character.getNumericValue(otherBigInt.numberArray[m]);
      String ca=String.valueOf(column);
      carry=ca.charAt(1);
      sum[m]=ca.charAt(0);
    }
    return new BigInt(deleteLeadingZeros(sum.toString()));
  }

  public String deleteLeadingZeros(String numberStr)
  {
    char endChar = numberStr.charAt(numberStr.length()-1);
    if (endChar=='0')
    {
      return deleteLeadingZeros( numberStr.substring(0, numberStr.length()-1) );
    }
    else 
    {
        return numberStr;
    }
  }

  public void WTFInThisArray()
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
      if (this.getLengthUsed()==0 && otherBigInt.getLengthUsed()==0)
      {
        return 0;
      }
      else
      {
//        int thisLeading, otherLeading;
        int arrayIndex=this.getLengthUsed()-1;
        if (otherBigInt.getLengthUsed()-1 !=arrayIndex)
        {
          System.out.println("Fatal Error: Numbers of uneven length");
          System.exit(0);
        }
//        thisLeading = this.numberArray[arrayIndex];
//        otherLeading = otherBigInt.numberArray[arrayIndex];
        if (this.numberArray[arrayIndex] > otherBigInt.numberArray[arrayIndex])
        {
          return 1;
        }
        else if (this.numberArray[arrayIndex] < otherBigInt.numberArray[arrayIndex])
        {
          return -1;
        }
        else 
        {
          System.out.println("Comparison of array index "+arrayIndex+" inconclusive.");
          System.out.println(this.numberArray[arrayIndex]+" and "+otherBigInt.numberArray[arrayIndex]+" are equal.");

          BigInt nextSmallest, nextSmallestOther;
          nextSmallest = new BigInt( this.toString().substring(0, arrayIndex) );
          nextSmallestOther = new BigInt( otherBigInt.toString().substring(0, arrayIndex));

          System.out.println("Now comparing "+nextSmallest.toString()+" and "+nextSmallestOther.toString());

          return nextSmallest.compare(nextSmallestOther);
        }
      }
    }
  }

  public int getLengthUsed()
  {
    return lengthUsed;
  }

  public void setLengthUsed()
  {
    this.lengthUsed=deleteLeadingZeros(this.numberArray.toString()).length();
  }

}


