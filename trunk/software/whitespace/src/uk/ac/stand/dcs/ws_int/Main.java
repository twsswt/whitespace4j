package uk.ac.stand.dcs.ws_int;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Main {
	
	protected static final Logger logger = Logger.getLogger(Main.class);
	{
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.INFO);
	}
	
	public static void main (String[] args){
		
		try {
			//char[] chars = new char[]{'\t',' ','\n'};
			System.out.println("here");
			char[] chars = new char[]{'T', 'W', 'N'};
			
			BufferedReader buf = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(args[0])));
			String program  ="";
			while (buf.ready()){
				program+=(char)buf.read();
			}
			new Interpreter(program, chars);
			
		} catch (FileNotFoundException e) {
			logger.fatal("Couldn't load target program", e);
		} catch (IOException e) {
			logger.fatal("Some sort of IO problem accessing the target program",e);
		}
	}
}
