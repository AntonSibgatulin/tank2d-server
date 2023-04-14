package uk.AntonSibgatulin.Battle.Tank;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import uk.AntonSibgatulin.Battle.Tank.Weapons.BTR.BTRModel;
import uk.AntonSibgatulin.Battle.Tank.Weapons.CONCAT.CONCATModel;
import uk.AntonSibgatulin.Battle.Tank.Weapons.T15.T15Model;
import uk.AntonSibgatulin.Battle.Tank.Weapons.T34.T34Model;

public class TankLoader {

	public TankLoader() {
		// TODO Auto-generated constructor stub
	}
	
	public HashMap<String,ITank> init(String folder){
		HashMap <String ,ITank> list = new HashMap<String,ITank>();
		
		File file = new File(folder);
		File[] files = file.listFiles();
		for(int i = 0;i<files.length;i++){
			if(files[i].isFile()){
			try {
				BufferedReader buf = new BufferedReader(new FileReader(files[i]));
				String str = "";
				String string = "";
				while((str = buf.readLine())!=null){
					string = string +str;
				}
				JSONObject json = new JSONObject(string);
				if(json.has("name")){
					String name= json.getString("name");
					for(int j = 0;j<=3;j++){
						JSONObject op = json.getJSONObject("m"+j);
						
						Hull hull = new Hull(op.getInt("width"), op.getInt("height"), op.getInt("hull_speed"), op.getInt("health"));
						
					if(name.equals("BTR")){
							
						ITank tanks = (ITank) new BTRModel(name,op.getInt("maxShoot"),op.getInt("minShoot")
								,op.getLong("timeReload"),op.getInt("maxSplash"),op.getInt("minSplash"),op.getInt("speed"),op.getLong("maxDistance"),hull);
						list.put(name+"_m"+j, tanks);
						
					}else if(name.equals("T34")){
						ITank tanks = (ITank) new T34Model(name,op.getInt("maxShoot"),op.getInt("minShoot")
								,op.getLong("timeReload"),op.getInt("maxSplash"),op.getInt("minSplash"),op.getInt("speed"),op.getLong("maxDistance"),hull);
						list.put(name+"_m"+j, tanks);
						
					}else if(name.equals("T15")){
						ITank tanks = (ITank) new T15Model(name,op.getInt("maxShoot"),op.getInt("minShoot")
								,op.getLong("timeReload"),op.getInt("maxSplash"),op.getInt("minSplash"),op.getInt("speed"),op.getLong("maxDistance"),hull);
						list.put(name+"_m"+j, tanks);
						
					}
					else if(name.equals("CONCAT")){
						ITank tanks = (ITank) new CONCATModel(name,op.getInt("maxShoot"),op.getInt("minShoot")
								,op.getLong("timeReload"),op.getInt("maxSplash"),op.getInt("minSplash"),op.getInt("speed"),op.getLong("maxDistance"),hull);
						list.put(name+"_m"+j, tanks);
						
					}
					
					
						
					}
				}
				buf.close();
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
			
		}
		System.out.println("Size list ..."+list.size()+"elements");
		return list;
	}

}
