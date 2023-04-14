package uk.AntonSibgatulin.Battle.Map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

public class MapLoader {
public HashMap<String ,MapModel> init(String folder){
	HashMap<String,MapModel> maps = new HashMap<>();
	File file = new File("Maps");
	File[] files = file.listFiles();
	for(int i = 0;i<files.length;i++){
		if(files[i].isFile())
		try {
			BufferedReader buf = new BufferedReader(new FileReader(files[i]));
			String str = "";
			String string = "";
			while((str = buf.readLine())!=null){
				string = string+str;
				
			}
			JSONObject json = new JSONObject(string);
			MapModel map = LoadMap(json);
			maps.put(json.getString("name"),map);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	return maps;
	
}
	public MapLoader() {
			
	}

	public MapModel LoadMap(JSONObject json){
		MapModel map = new MapModel(json);
		return map;
	}
}
