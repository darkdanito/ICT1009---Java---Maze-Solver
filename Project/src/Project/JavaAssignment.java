package Project;

import java.io.*;

// main file of the Assignment.
// Contain the main()
// Contain the method to read input from users

import Project.JavaAssignment;

public class JavaAssignment 
{
	public static final char WALL = '0'; 						// 0 = Wall
	public static final char FREE = '1'; 						// 1 = Passage
	public static final char TARGET = 'T'; 						// Location of the TARGET
	public static final char AGENT = 'A'; 						// Location of the AGENT
	
	public static void main(String[] args) throws IOException 
	{
		try
		{
			BufferedReader reader = IO.ReadFile();					// call IO to read Maze File
			
			MazeGenerator newMaze = new MazeGenerator();			// call create empty Maze
			
			newMaze.sizeMaze(reader);								// Check Maze size of text file
			
			BufferedReader readerD = IO.ReadFile();					// call IO to read Maze File

			newMaze.generateMaze(readerD);							// initialize the Maze
			
			inputAgent(newMaze);									// Update Agent X Y into Maze
			inputTarget(newMaze);									// Update Target X Y into Maze
			
			long startTime2 = System.currentTimeMillis();			// Get Start time 
			
			Agent AgentOne = new Agent();							// Create Agent Object
			AgentOne.setName('B');
			AgentOne.setNamecode('A');
			
			Target TargetGoal = new Target();						// Create Target Object
			TargetGoal.setName('C');
			TargetGoal.setNamecode('T');
			
			MazeSolver.catchTarget(newMaze, AgentOne, TargetGoal);
			MazeSolver.solve(newMaze, AgentOne, TargetGoal);

			long endTime2 = System.currentTimeMillis();				// Get End Time

			System.out.println("Program runtime : " + (endTime2 - startTime2) + " ms");
			
			reader.close();
			readerD.close();
		} catch(FileNotFoundException e) 
		{
			System.out.println("Maze File cannot be found. Please check File Name / Directory.");
		}
	}
	
	public static void inputAgent(MazeGenerator newMaze) throws IOException
	{														// User Input of AGENT X Y Position
		int agentX = 0;
		int agentY = 0;
		
		String inputAgentX = null;
		String inputAgentY = null;

		BufferedReader readerDD = IO.ReadInput();
		
		do 
		{
			newMaze.displayMaze();								
			System.out.print("\nPlease enter the starting X position of Agent: ");
			
			inputAgentX = readerDD.readLine();	
			if (!inputAgentX.isEmpty() && inputAgentX.matches("[0-9]+")) 
			{													
				agentX = Integer.parseInt(inputAgentX);
				if (agentX >= 0 && agentX < newMaze.getRow()) 	// Bigger than 0 and less than
				{												// array size
					System.out.print("Please enter the starting Y position of Agent: ");
					
					inputAgentY = readerDD.readLine();
					if (!inputAgentY.isEmpty() && inputAgentY.matches("[0-9]+")) 
					{
						agentY = Integer.parseInt(inputAgentY);
						if (agentY >= 0 && agentY < newMaze.getColumn()) 
						{						
							if (newMaze.getMazeArray()[agentX][agentY] != WALL) 
							{									// Update to Array Agent Location
								newMaze.getMazeArray()[agentX][agentY] = AGENT;
								newMaze.displayMaze();
							} else 
							{
								System.out.println("Cannot place on Wall!!!");
							}
						} else 
						{
							System.out.println("Enter only positive numbers of Y coordinate and within range!!!");
						}
					} else 
					{
						System.out.println("Only numbers are allowed for Y coordinate!!! ");
					}
				} else 
				{
					System.out.println("Enter only positive numbers of X coordinate and within range!!!");
				}
			} else 
			{
				System.out.println("Only numbers are allowed for X coordinate!!!");
			}
		} while (inputAgentX.isEmpty() || !inputAgentX.matches("[0-9]+") ||
				agentX < 0 || agentX >= newMaze.getRow()|| 
				inputAgentY.isEmpty() || !inputAgentY.matches("[0-9]+") ||
				agentY < 0 || agentY >= newMaze.getColumn() ||
				newMaze.getMazeArray()[agentX][agentY] == WALL);
	}
	
	public static void inputTarget(MazeGenerator newMaze) throws IOException
	{														// User Input of Target X Y Position
		int targetX = 0;
		int targetY = 0;

		String inputTargetX = null;
		String inputTargetY = null;
		
		BufferedReader readerDD = IO.ReadInput();
		
		do 
		{
			System.out.print("\nPlease enter the starting X position of Target: ");
			inputTargetX = readerDD.readLine();

			if (!inputTargetX.isEmpty() && inputTargetX.matches("[0-9]+")) 
			{
				targetX = Integer.parseInt(inputTargetX);	
				if (targetX >= 0 && targetX < newMaze.getRow()) 
				{
					System.out.print("Please enter the starting Y position of Target: ");
					inputTargetY = readerDD.readLine();
					if (!inputTargetY.isEmpty() && inputTargetY.matches("[0-9]+")) 
					{
						targetY = Integer.parseInt(inputTargetY);
						if (targetY >= 0 && targetY < newMaze.getColumn()) 
						{
							if (newMaze.getMazeArray()[targetX][targetY] != WALL && 
								newMaze.getMazeArray()[targetX][targetY] != AGENT ) 
							{								// Update to Array Target Location
								newMaze.getMazeArray()[targetX][targetY] = TARGET;
								newMaze.displayMaze();
							} else 
							{
								System.out.println("Cannot place on Wall or Agent!!!");
							}
						} else 
						{
							System.out.println("Enter only positive numbers of Y coordinate and within range!!!");
						}
					} else 
					{
						System.out.println("Only numbers are allowed for Y coordinate!!! ");
					}
				} else 
				{
					System.out.println("Enter only positive numbers of X coordinate and within range"
							+ "!!!");
				}
			} else 
			{
				System.out.println("Only numbers are allowed for X coordinate!!!");
			}
		} while (inputTargetX.isEmpty() || !inputTargetX.matches("[0-9]+") || 
				targetX < 0 || targetX >= newMaze.getRow()||
				inputTargetY.isEmpty() || !inputTargetY.matches("[0-9]+") || 
				targetY < 0 || targetY >= newMaze.getColumn() ||
				newMaze.getMazeArray()[targetX][targetY] == WALL || 
				newMaze.getMazeArray()[targetX][targetY] == AGENT);			
	}
}
