package Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

// IO Class
// Contain the method to read the maze file.
// Update the location of the Maze File below.

public class IO 
{
	public static BufferedReader ReadFile() throws FileNotFoundException
	{														// Read maze from text file
		// Update the location of the Maze File below: 
		String fileName = "C:\\Users\\darkdanito\\Desktop\\Project\\src\\maze3.txt";
		FileReader inputFile = new FileReader(fileName);
		BufferedReader reader = new BufferedReader(inputFile);
		
		return reader;
	}
	
	public static BufferedReader ReadInput()
	{														// ReadInput	
		InputStreamReader inputValue = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(inputValue);
		
		return reader;
	}
}
