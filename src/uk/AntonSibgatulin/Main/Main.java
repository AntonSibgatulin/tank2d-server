package uk.AntonSibgatulin.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import uk.AntonSibgatulin.Server.Server;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException, JSONException {
		BufferedReader buf = new BufferedReader(new FileReader(new File("configure/sock.cfg")));
		String str ="";
		String jsonString ="";
		while((str = buf.readLine())!=null){
			jsonString = jsonString+str;
		}
		JSONObject json = new JSONObject(jsonString);
		
		Server server = new Server(json.getString("IP"),json.getInt("PORT"));
		server.start();
		
		
	}

}
