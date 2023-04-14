package uk.AntonSibgatulin.User;

import java.util.HashMap;

import org.java_websocket.WebSocket;
import org.json.JSONException;
import org.json.JSONObject;

import uk.AntonSibgatulin.Battle.BattleField;
import uk.AntonSibgatulin.Battle.Tank.ITank;
import uk.AntonSibgatulin.Battle.Tank.TankModel;

public class User {
public String login = null;
public String password = null;
public JSONObject jsonWeapons = null;
public WebSocket socket = null;

public void send(String str){
	WebSocket con=socket;
	   if (con!=null && con.isFlushAndClose() == false && con.isConnecting() == false && con.isClosing()==false){

	con.send(str);
	   }
}
public TankModel tank = null;
public User(String login,String password) {
	this.login  = login;
	this.password = password;
	
}
public long id = 0;
public double money = 0;
public int score = 0;
public UserType type = null;
public JSONObject tanks=null;

public User(String login,String password,double money,int rang ,long id ,UserType type, JSONObject tanks, JSONObject t1, JSONObject inventar) {
	this.login  = login;
	this.id = id;
this.money = money;	
score = rang;
this.type = type;
this.tanks = tanks;
	this.password = password;
	
	// TODO Auto-generated constructor stub
}
public JSONObject jsonObjecttank = new JSONObject();

public void initTankModel(HashMap<String, ITank> tanks_weapons,BattleField field) {
	ITank tank=null;
	try {
		tank = (ITank) tanks_weapons.get(tanks.getJSONArray("hulls").get(tanks.getInt("hull")));
		
		tank.setField(field);
		jsonObjecttank.put("name", tank.getType());
		jsonObjecttank.put("timeReload", tank.getTimeReload());
		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	this.tank = new TankModel(tank,this,field);
	tank.setTank(this.tank);
	tank.setField(field);
}

}
