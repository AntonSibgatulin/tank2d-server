package uk.AntonSibgatulin.Server;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import uk.AntonSibgatulin.Battle.BattleField;
import uk.AntonSibgatulin.Battle.Map.MapLoader;
import uk.AntonSibgatulin.Battle.Map.MapModel;
import uk.AntonSibgatulin.Battle.Tank.ITank;
import uk.AntonSibgatulin.Battle.Tank.TankLoader;
import uk.AntonSibgatulin.Database.DatabaseModel;
import uk.AntonSibgatulin.User.User;

public class Server extends WebSocketServer{
	public Server serv = this;
public MapLoader Maploader = new MapLoader();
public HashMap<String ,MapModel> maps = null;
public HashSet<WebSocket> conn = null;

public int idbattle = 0;
public ArrayList<BattleField> battles = new ArrayList<>();

public HashMap<User,BattleField> gameit = new HashMap<>();


public HashMap<Integer,BattleField> gameid = new HashMap<>();


public TankLoader Tankloader = new TankLoader();
public HashMap<Long,User > online = new HashMap<>();

public HashMap<String,ITank> tanks_weapons = new HashMap<>();

public HashMap<WebSocket,User> users= new HashMap<>();
public ArrayList<User> usersarray = new ArrayList<>();


public DatabaseModel base = new DatabaseModel();





	public Server(String string,int port) throws UnknownHostException {
		super(new InetSocketAddress(InetAddress.getByName(string),port));
		
		conn = new HashSet<WebSocket>();
		maps = Maploader.init("Maps");
		tanks_weapons = Tankloader.init("Weapons");
		
	}

	@Override
	public void onClose(WebSocket arg0, int arg1, String arg2, boolean arg3) {
		User user = users.get(arg0);
		if(user!=null){
		online.remove(user.id);
		usersarray.remove(user);
		BattleField field = gameit.get(user);
		if(field!=null){
			field.removeUser(user);
		}
		gameit.remove(user);
		
		users.remove(arg0);
		}
		conn.remove(arg0);
		
	}

	@Override
	public void onError(WebSocket arg0, Exception arg1) {
		// TODO Auto-generated method stub
		
	}

	public void send_to_table(User user){
		for(int i = 0;i<this.battles.size();i++){
			BattleField game = battles.get(i);
			user.send("tables;all;"+game.id+";"+1+";"+game.time+";"+game.map.w+"x"+game.map.h);
	
		}
	}
	
	public void send_to_all(BattleField game){
		for(int i = 0;i<this.usersarray.size();i++){
			User user = usersarray.get(i);
			user.send("tables;all;"+game.id+";"+1+";"+game.time+";"+game.map.w+"x"+game.map.h);
	
		}
	}
	@Override
	public void onMessage(WebSocket arg0, String arg1) {
		new Thread(new Runnable(){

			@Override
			public void run() {
				String str[] =arg1.split(";");
			//	System.out.println(arg1);
				if(str[0].equals("auth") && str.length>=3){
					String login =str[1];
					String password = str[2];
					User user = base.getUser(login, password);
					if(user!=null){
						user.socket = arg0;
						user.send("auth;true");
						usersarray.add(user);
						users.put(user.socket, user);
						online.put(user.id, user);
						send_to_table(user);
					}else{
						arg0.send("auth;false");
					}
				}else if (false == true){
					
				}else{
					User user = users.get(arg0);
					if(user ==null){return;}
					else{
						
						
						
						if(str[0].equals("tables")){
							if(str[1].equals("create")){
					BattleField field = new BattleField(str[2],"DM",maps.get("Sandbox"),idbattle++);			
				battles.add(field);
				gameid.put(field.id, field);
				System.out.println(field.id);
				
					send_to_all(field);
							}else if(str[1].equals("join")){
								Integer id = Integer.valueOf(str[2]);
								BattleField field = gameid.get(id);
								if(field!=null){
								boolean bool = field.addUser(user, "",serv);

								if(bool){
								
									gameit.put(user, field);
							}
								}
							}else if (str[1].equals("move")){
								BattleField field = gameit.get(user);
								if(field!=null){
									field.move(user, str[2]);
								}
							}else if (str[1].equals("unmove")){
								BattleField field = gameit.get(user);
								if(field!=null){
									field.unmove(user, str[2]);
								}
							}else if (str[1].equals("fire")){
								BattleField field = gameit.get(user);
								if(field!=null){
									field.fire(user.tank, str[2]);
								}
							}
							
							
						}
						
						
					}
				}
				
				
				
				
				
			}
			
		}).start();
	}

	@Override
	public void onOpen(WebSocket arg0, ClientHandshake arg1) {
		conn.add(arg0);
		
	}

	public void initTankModel(User user,BattleField field) {
		user.initTankModel(tanks_weapons,field);
	}

}
