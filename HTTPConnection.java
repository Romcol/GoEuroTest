package goEuro;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPConnection {

	String url;
	
	HTTPConnection(String url)
	{
		this.url = url;
	}
	
	public InputStreamReader initiateConnection() throws IOException
	{
	    URL url = new URL(this.url);
	    HttpURLConnection request = (HttpURLConnection) url.openConnection();
	    request.connect();
	    return new InputStreamReader((InputStream) request.getContent());
	}
}
