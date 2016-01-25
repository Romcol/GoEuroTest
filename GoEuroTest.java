package goEuro;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class GoEuroTest {
	
	public static void launchError(String errorMsg){
		System.out.println(errorMsg+"\n");
		System.exit(1);
	}
	
	public static void main(String[] args){
		
		if(args.length != 1)
		{
			launchError("Error: Incorrect number of arguments.\njava -jar GoEuroTest.jar \"CITY_NAME\"");
		}
		
		HTTPConnection c;
		Parser parser = null;
		InputStreamReader rawJSON = null;
		
		try{
			c = new HTTPConnection("http://api.goeuro.com/api/v2/position/suggest/en/"+args[0]);
			rawJSON = c.initiateConnection();
		}
		catch(FileNotFoundException e)
		{
			launchError("Error while accessing file : it may be used in an other program.");
		}
		catch(IOException e)
		{
			launchError("Error: Can't connect to API URL.");
		}
		
		try{
		parser = new Parser(rawJSON);
		parser.doParse();
		}
		catch(NullPointerException e)
		{
			launchError("Error while parsing JSON.");
		}
		
		if(!parser.getResults().isEmpty())
		{
			try{
				CSVwriter writer = new CSVwriter(parser.getResults(),"output.csv");
				writer.writeCSV();
				System.out.println("Success: "+parser.getResults().size()+" row(s) written for city \""+args[0]+"\".");
			}
			catch(FileNotFoundException e)
			{
				launchError("Error while accessing file: it may be used in an other program.");
			}
			catch(IOException e)
			{
				launchError("Error while writing in file.");
			}
		}
		else{
			launchError("No matches found for given parameter.");
		}
	}
}
