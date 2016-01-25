package goEuro;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class CSVwriter {

	List<Airport> results;
	FileWriter fileWriter;
	
	public CSVwriter(List<Airport> results, String name) throws FileNotFoundException, IOException {
		this.results = results;
		this.fileWriter = new FileWriter(name);
	}

	public void writeCSV() throws IOException{
	    fileWriter.append("_id,name,type,latitude,longitude\n");
	    Iterator<Airport> itr = results.iterator();
	    while(itr.hasNext()) {
	    	Airport ap = itr.next();
	    	fileWriter.append(ap+"\n");
	    }
        fileWriter.flush();
        fileWriter.close();
	}
}
