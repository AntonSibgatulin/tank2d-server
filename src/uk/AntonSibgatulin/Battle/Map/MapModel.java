package uk.AntonSibgatulin.Battle.Map;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uk.AntonSibgatulin.Battle.Tank.Rect;
import uk.AntonSibgatulin.Battle.Tank.Math.Vector2d;

public class MapModel {
public int w = 0;
public int h = 0;
public ArrayList<Rect> block = new ArrayList<>();
public String name = null;

public ArrayList<Vector2d> spawn_blue = new ArrayList<>();
public ArrayList<Vector2d> spawn_red = new ArrayList<>();
public JSONObject json = null;

public void init() throws JSONException{
	w = json.getInt("width");
	h = json.getInt("height");
	for(int i = 0;i<json.getJSONArray("block").length();i++){
		JSONArray ar = json.getJSONArray("block").getJSONArray(i);
		Rect rect = new Rect(ar.getInt(0),ar.getInt(1),ar.getInt(2),ar.getInt(3));
		block.add(rect);
		
	}

	for(int i = 0;i<json.getJSONArray("blue_spawn").length();i++){
		JSONArray ar = json.getJSONArray("blue_spawn").getJSONArray(i);
		Vector2d rect = new Vector2d(ar.getInt(0),ar.getInt(1));
		spawn_blue.add(rect);
		
	}
	
	

	for(int i = 0;i<json.getJSONArray("red_spawn").length();i++){
		JSONArray ar = json.getJSONArray("red_spawn").getJSONArray(i);
		Vector2d rect = new Vector2d(ar.getInt(0),ar.getInt(1));
		spawn_red.add(rect);
		
	}
	
}
	public MapModel(JSONObject json) {
			this.json = json;
			try {
				init();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		// TODO Auto-generated constructor stub
	}

}
