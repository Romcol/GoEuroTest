package goEuro;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Parser {
	
	InputStreamReader data;
	List<Airport> results;
	
	Parser(InputStreamReader data)
	{
		this.data = data;
		this.results = new ArrayList<Airport>();
	}
	
	public void doParse() throws NullPointerException
	{
	    JsonParser jp = new JsonParser();
	    JsonElement root = jp.parse(this.data);
	    JsonArray rootobj = root.getAsJsonArray();
	    
	    Iterator<JsonElement> itr = rootobj.iterator();
	    while(itr.hasNext())
	    {
	    	Airport ap = new Airport();
	    	JsonObject jobj = itr.next().getAsJsonObject();
	    	JsonObject pos = jobj.get("geo_position").getAsJsonObject();
	    	ap._id = jobj.get("_id").getAsInt();
	    	ap.name = jobj.get("name").getAsString();
	    	ap.type = jobj.get("type").getAsString();
	    	ap.latitude = pos.get("latitude").getAsDouble();
	    	ap.longitude = pos.get("longitude").getAsDouble();
	    	results.add(ap);
	    }
	}
	
	public List<Airport> getResults(){
		return this.results;
	}
	
}
