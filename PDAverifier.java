import java.util.*;
import java.io.*;

public class Prog2 {
	static boolean bool = true;
	static int x;
	static String S;
	static Stack<Character> stack = new Stack<Character>();
	public static void main(String[] args)
	{
		
		Scanner Scan = new Scanner(System.in);
		
		while (bool)
		{
			
			System.out.println("Do you want to enter a string? y or n");
			String answer = Scan.next();
			if(answer.equals("y"))
			{
				System.out.println("Please enter the string for the PDA: ");	//Allow the use to input the string to be processed.
				S = Scan.next();
				x = 0;															//Index for incoming string
				System.out.println(S);											//Print input
				Q0(); 															//Send a call to the start state,
				
				bool = true;													//In order to keep asking user for input
			}
			else if(answer.equals("n"))
			{
				bool = false;
				System.exit(0);
			}
		}
	}
	
	//Start state that reads '$', Pops Nothing and Pushes '$'
	static void Q0()
	{
		if(S.charAt(x) == '$')
		{
			System.out.println("(State q0) Reads $, Pops Nothing, Pushes $");
			stack.push('$');
			x++;
			Q1();
		}
		else
		{
			System.out.println("String rejected in state Q0");
			System.out.println();
		}
	}
	static void Q1()
	{
		try
		{
			if((S.charAt(x) >= 'a' && S.charAt(x) <= 'z'))		//If next input is char
			{
				System.out.println("(State q1) Reads " + S.charAt(x) + " Pops Nothing, Pushes Nothing");
				x++;
				Q2();
			}
			else if(S.charAt(x) == '(')							//If next input is '('
			{
				System.out.println("(State q1) Reads ( Pops Nothing, Pushes (");
				stack.push(S.charAt(x));
				x++;
				Q1();
			}
			else												//Otherwise, we've run into an illegitimate language
			{
				System.out.println("String rejected in state Q1");
				System.out.println();
			}
			
		}
		catch(Exception e)										//Error control
		{
			System.out.println("Q1 String index out of bounds");
		}
	}
	
	static void Q2()
	{
		try
		{
			if((S.charAt(x) >= 'a' && S.charAt(x) <= 'z') ||S.charAt(x) == '0' || S.charAt(x) == '1' || S.charAt(x) == '2' || S.charAt(x) == '3'
					|| S.charAt(x) == '4' || S.charAt(x) == '5' || S.charAt(x) == '6' || S.charAt(x) == '7' || S.charAt(x) == '8'
					|| S.charAt(x) == '9')						//If input is either char or digit
			{
				System.out.println("(State q2) Reads " + S.charAt(x) + " Pops Nothing, Pushes Nothing");
				x++;
				Q3();
			}
			else												//Otherwise, we've reached illegitimate language
			{
				System.out.println("String rejected in state Q2");
				System.out.println();
			}
		}catch(Exception e)										//Error control
		{
			System.out.println("Q2 String index out of bounds");
		}
	}
	static void Q3()
	{
		try
		{
			if(S.charAt(x) == ')')								//If input is ')'
			{
				if(stack.peek() == '(')							//And if the top of the stack containes '(', POP IT
				{
					System.out.println("(State q3) Reads ) Pops ( Pushes Nothing");
					stack.pop();
					x++;
					Q6();
				}
				else											//Otherwise, we've run into an issue and the program crashes
				{
					System.out.println("String rejected in state Q3");
					System.out.println();
					Trap();
				}

			}

			else if(S.charAt(x) == '+' || S.charAt(x) == '-' || S.charAt(x) == '/' || S.charAt(x) == '*') //if input is operator, continue
			{
				System.out.println("(State q3) Reads " + S.charAt(x) + " Pops Nothing, Pushes Nothing");
				x++;
				Q4();
			}
			else if(S.charAt(x) == '$')						//If we've reached that end of a legit string
			{
				if(stack.peek() == '$')						//and the last thing in the stack is $
				{
					System.out.println("(State q3) Reads $, Pops $, Pushes Nothing");	//Acceptable state
					Q5();
					stack.pop();
				}
				else
				{Trap();}									//if the $ is not the last thing on stack, we have a problem
			}
			else if((S.charAt(x) >= 'a' && S.charAt(x) <= 'z') ||S.charAt(x) == '0' || S.charAt(x) == '1' || S.charAt(x) == '2' || S.charAt(x) == '3'
					|| S.charAt(x) == '4' || S.charAt(x) == '5' || S.charAt(x) == '6' || S.charAt(x) == '7' || S.charAt(x) == '8'
					|| S.charAt(x) == '9')					//if input is char or digit
			{
				System.out.println("(State q3) Reads " + S.charAt(x) + " Pops Nothing, Pushes Nothing");	//proceed
				x++;
				Q3();			
			}
			else
			{
				System.out.println("String rejected in state Q3");
				System.out.println();
			}

		}
		catch(Exception e)			//error control
		{
			System.out.println("Q3 String Index out of bounds");
		}
	}
	
	static void Q4()
	{
		try
		{
			if((S.charAt(x) >= 'a' && S.charAt(x) <= 'z'))
			{
				System.out.println("(State q4) Reads " + S.charAt(x) + " Pops Nothing, Pushes Nothing");
				x++;
				Q3();
			}
			else if(S.charAt(x) == '(')
			{
				System.out.println("(State q4) Reads ( Pops Nothing, Pushes (");
				stack.push(S.charAt(x));
				x++;
				Q4();
			}
			else
			{
				System.out.println("(State q4) String rejected in state Q4");
				System.out.println();
			}
			
		}catch(Exception e)
		{
			System.out.println(" Q1 String index out of bounds");
		}
	}

	
	static void Q5()
	{
		System.out.println("String has been accepted in state Q5.");
		System.out.println();
	}
	
	static void Q6() //same protocol as q3
	{
		try
		{
			if(S.charAt(x) == ')')
			{
				if(stack.peek() == '(')
				{
					System.out.println("(State q3) Reads ) Pops ( Pushes Nothing");
					stack.pop();
					x++;
					Q6();
				}
				else
				{
					System.out.println("String rejected in state Q3");
					System.out.println();
					Trap();
				}

			}

			else if(S.charAt(x) == '+' || S.charAt(x) == '-' || S.charAt(x) == '/' || S.charAt(x) == '*')
			{
				System.out.println("(State q3) Reads " + S.charAt(x) + " Pops Nothing, Pushes Nothing");
				x++;
				Q4();
			}
			else if(S.charAt(x) == '$')
			{
				if(stack.peek() == '$')
				{
					System.out.println("(State q6) Reads $, Pops $, Pushes Nothing");
					Q5();
					stack.pop();
				}
				else
				{Trap();}
			}
			else
			{
				System.out.println("String rejected in state Q6");
				System.out.println();
			}

		}
		catch(Exception e)
		{
			System.out.println("Q3 String Index out of bounds");
		}
	}
	static void Trap()
	{
		System.out.println(S+" has crashed in the Trap State!");
		System.out.println();
	}
	
}
