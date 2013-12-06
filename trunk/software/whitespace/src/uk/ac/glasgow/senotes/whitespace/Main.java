package uk.ac.glasgow.senotes.whitespace;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Simple front end entry point for the whitespace program interpreter, using
 * System.in and System.out for user interaction.
 * 
 * Example usage: <br/>
 * java uk.ac.glasgow.senotes.whitespace.Main DEFAULT testset/default/helloworld.ws
 * 
 * @author tws
 * 
 */
public class Main {
	
	protected static final Logger logger = Logger.getLogger(Main.class);
	
	/**
	 * Application entry point method.
	 * 
	 * @param args
	 *            args[0] selects the character set (DEFAULT or VISIBLE).
	 *            args[1] specifies the whitespace program source. <br/>
	 */
	public static void main (String[] args){
		
		PropertyConfigurator.configure("log4j.properties");
		
		CharacterSet characterSet = CharacterSet.valueOf(args[0]);
		
		String filePath = args[1];
		String source;
		try {
			source = readProgramSourceFile(args[1]);
			WhiteSpaceProgram program = new WhiteSpaceProgram(source);

			Interpreter interpreter =
				new Interpreter(program, characterSet, System.in, System.out);
				
			interpreter.run();
			
		} catch (FileNotFoundException e) {
			logger.fatal(
				"Couldn't load target program from file path ["+filePath+"].", e);
		} catch (IOException e) {
			logger.fatal(
				"Some sort of IO problem accessing the source at the specified path ["+filePath+"].",e);
		}
	}
	
	/**
	 * Reads a specified whitespace source file from the specified file
	 * location.
	 * 
	 * @param filePath
	 *            the local file path to the whitespace application.
	 * @return the file content.
	 * @throws FileNotFoundException
	 *             , IOException
	 */
	public static String readProgramSourceFile(String filePath)
		throws FileNotFoundException, IOException {

		FileInputStream fis = new FileInputStream(filePath);
			
		BufferedReader buf = 
			new BufferedReader(new InputStreamReader(fis));
			
		String program  ="";
				
		while (buf.ready())
			program+=(char)buf.read();
		
		try {
			buf.close();
		} catch (IOException e) {
			logger.error("Couldn't close buffer resource ["+buf+"]",e);
		}
				
		return program;				
	}
}
