package uk.AntonSibgatulin.Battle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.Timer;

import org.java_websocket.WebSocket;

import uk.AntonSibgatulin.Battle.Inventar.Health.Health;
import uk.AntonSibgatulin.Battle.Map.MapModel;
import uk.AntonSibgatulin.Battle.Tank.Pos;
import uk.AntonSibgatulin.Battle.Tank.Rect;
import uk.AntonSibgatulin.Battle.Tank.TankModel;
import uk.AntonSibgatulin.Battle.Tank.Math.Vector2d;
import uk.AntonSibgatulin.Server.Server;
import uk.AntonSibgatulin.User.User;

public class BattleField extends Thread implements ActionListener{
	Timer t = new Timer (40,this);
	public static final long repeat  = 20;
	public static final int  newHealth = 2500;
	public static final int colhealth = 5;
	public long lastTimeHealth = 0L;
	
	public int idShoot = 0;
	public int id = 0;
public String name = null;
public BattleType type = null;
public String types = null;
public long time = 0;


public ArrayList<Health>  health = new ArrayList<Health>();




public long timeCreate = System.currentTimeMillis();
public static final long reinit = 3000;
public int maxPlayer = 5;
public HashMap<WebSocket,User> users = new HashMap<>();
public HashMap<User,TankModel> tanks = new HashMap<>();
public ArrayList<TankModel> tank_array = new ArrayList<>();
public ArrayList<User> socket = new ArrayList<>();

public HashMap<TankModel,BattleChart> chart = new HashMap<>();


public void init(User user){
	TankModel tank = tanks.get(user);
	System.out.println("Rock star everybody wants you baby get down down "+tank.init);
	
	if(tank!=null && tank.init==false){
		if(type==BattleType.DM){
			System.out.println("Init DM");
			int x = new Random().nextInt(map.spawn_blue.size());
			int y = new Random().nextInt(map.spawn_red.size());
			
			if(new Random().nextInt(2)==1){
				reinit(tank,map.spawn_blue.get(x),Pos.left);
			}else{
				reinit(tank,map.spawn_red.get(y),Pos.right);
			}
			
			
		}
	}
}
public void move(User user,String pos){
	TankModel tank = tanks.get(user);
	if(tank!=null){
		
		if(pos.equals("right")){
			tank.rect.u = false;
			tank.rect.d = false;
			tank.rect.r = true;
			tank.rect.l = false;
			tank.pos = Pos.right;
			
		}
		
		if(pos.equals("left")){
			tank.rect.u = false;
			tank.rect.d = false;
			tank.rect.r = false;
			tank.rect.l = true;
			tank.pos = Pos.left;
		}
		
		if(pos.equals("up")){
			tank.rect.u = true;
			tank.rect.d = false;
			tank.rect.r = false;
			tank.rect.l = false;
			tank.pos = Pos.up;
		}
		if(pos.equals("down")){
			tank.pos = Pos.down;
			tank.rect.u = false;
			tank.rect.d = true;
			tank.rect.r = false;
			tank.rect.l = false;
		}
		//tank.rect.move( tank);
		
	
	}
}



public void unmove(User user,String pos){
	TankModel tank = tanks.get(user);
	if(tank!=null){
		
		if(pos.equals("right")){
			tank.rect.r = false;
			
			
		}
		
		if(pos.equals("left")){
			tank.rect.l = false;
			
		}
		
		if(pos.equals("up")){
			tank.rect.u = false;
	
		}
		if(pos.equals("down")){
			//tank.pos = Pos.down;
			tank.rect.d = false;
		
		}
		//tank.rect.move( tank);
		
	
	}
}
public void reinit(TankModel tank,Vector2d rect,Pos pos){
	try {
		Thread.sleep(reinit);
		
		tank.rect.x = rect.x;
		
		tank.rect.y = rect.y;
		tank.health = tank.hull.health;
	
		tank.rect.update(tank);
		tank.pos = pos;
		send_to_all("tables;init;"+tank.getTankId()+";"+tank.rect.x+";"+tank.rect.y+";"+pos.toString(),null);
		tank.init = true;
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

private void initHealth(User user) {
	user.send("table;health;init;"+user.tank.health);
	
}
private void updateHealth(User user) {
	if(user!=null)
	user.send("table;health;update;"+user.tank.health);
	
}
public void kill(TankModel from ,TankModel to){
	to.init = false;
	new Thread(new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
	
	send_to_all("table;kill;"+to.getTankId()+";"+from.getTankId(),null);
	
		}
		
		}).start();
	BattleChart fromc = this.chart.get(from);
	BattleChart toc = this.chart.get(to);
	fromc.killme++;
	toc.kill++;
	toc.score+=10;
	sendChangeChart(from,fromc);
	sendChangeChart(to,toc);
	to.health = 0;
	updateHealth(to.user);
	init(to.user);
	
}




public void kill(TankModel to){
	to.init = false;
	new Thread(new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
	
	send_to_all("table;kill;"+to.getTankId()+";4",null);
	
		}
		
		}).start();
	BattleChart toc = this.chart.get(to);

	toc.kill++;
	toc.score+=10;
	sendChangeChart(to,toc);
	to.health = 0;
	updateHealth(to.user);
	init(to.user);
	
}

private void sendChangeChart(TankModel from, BattleChart fromc) {
	from.user.send("table;battleChart;change;"+fromc.kill+";"+fromc.killme);
	
}
public void fire(TankModel tank,String string){
	if(tank.init){
		tank.getWeapons().fire(string);
	}
}

public void shoot(TankModel from ,TankModel to,float damage){
	System.out.println("damage: = "+damage);
	to.damage(damage, from);
	updateHealth(to.user);
	
	
}
public void send_to_tank(String str ,TankModel tank){
	
	tank.getUser().send(str);
	
}
public void send_to_all(String str,WebSocket arg0){
	for(User user :socket){
		if(user.socket!=arg0){
			user.send(str);
		}
	}
}
public MapModel map = null;


	public BattleField(String name,String types,MapModel map,int id ) {
		if(name.length()<4){
			name = map.name;
		}
		this.types = types;
		if(types.equals("DM")){
			type = BattleType.DM;
		}
		this.map = map;
		this.id = id;
		
		
		
		if(types.equals("CTF")){
			type = BattleType.CTF;
		}
		this.name = name;
		
		start();
		t.start();
	}
	
	
	public boolean addUser(User user,String team,Server server){
		if(users.get(user.socket)!=null)return false;
		
		if(type == BattleType.DM){
			if(users.size()<maxPlayer){
				
				users.put(user.socket, user);
				server.initTankModel(user,this);
				user.tank.color="green";
				user.tank.teams = "green"+user.id;
				tanks.put(user, user.tank);
				user.tank.pos = Pos.right;
				tank_array.add(user.tank);
				
				socket.add(user);
				user.send("game;join;"+id+";"+map.w+"x"+map.h+";"+user.tank.color+";"+user.id+";"+user.jsonObjecttank);
				new Thread(new Runnable(){
					@Override
					public void run() {
				send_to_all("tables;enemy;init;"+user.id+";"+user.jsonObjecttank,user.socket);
					}}).start();;
				
				chart.put(user.tank, new BattleChart());
				
				initMap(user);
					
				init(user);
				
				sendenemy(user);
				initBattleChart(user);
				initHealth(user);
				return true;
		}
		}
		return false;
		
	}
	
	
	private void initBattleChart(User user) {
		user.send("table;battleChart;init");
	}
	
	
	
	
	private void initMap(User user) {
		user.send("game;init;"+map.json.toString());
	}
@Override
public void run(){
	while(true){
		try {
			Thread.sleep(20);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
public int idhealth = 0;
public void isColision(Rect rect){
	for(int i =0;i<map.block.size();i++){
		if(Rect.isLockIntersect(rect, map.block.get(i))){
		//	isColision(rect);
		}
		
	}
}
public Rect getRectRandom(){
	int x = new Random().nextInt(map.w-64);
	int y = new Random().nextInt(map.h-64);
	Rect rect = new Rect(x,y,32,32);
	isColision(rect);
	return rect;
}
@Override
public void actionPerformed(ActionEvent arg0) {
	if(health.size()>colhealth){
		
		this.send_to_all("table;health;remove;"+health.get(0).id,null);
		health.remove(0);
	}
	if(System.currentTimeMillis()-lastTimeHealth>=newHealth){
		
		Health health= new Health(getRectRandom(),idhealth++);
		this.health.add(health);
		lastTimeHealth = System.currentTimeMillis();
		this.send_to_all("table;health;add;"+health.id+";"+health.rect.x+";"+health.rect.y,null);
		
	}
	for(int i = 0;i<tank_array.size();i++){
		//if(i>=tank_array.size())return;
		TankModel tank = tank_array.get(i);
		
		for(int j = 0;j<map.block.size();j++){
			if(j>=map.block.size())return;
			Rect rect = map.block.get(j);
			if(tanks.get(tank.user)!=null && rect!=null && tanks.get(tank.user).init){
			if(Rect.isLockIntersect( tank.rect,rect)){
			//System.out.println(Rect.isIntersect(rect, tanks.get(tank.user).rect)+" "+rect+" "+tanks.get(tank.user).rect);
			
			}
			
			}
			
			
			
			}
		
		
		for(int j = 0;j<tank_array.size();j++){
			TankModel l = tank_array.get(j);
			if(l!=tank&&l.init && tank.init){
				Rect.isLockIntersect(l.rect, tank.rect);
				Rect.isLockIntersect(tank.rect, l.rect);
			}
		}
		if(tank.init){
			
		this.send_to_all("tables;move;"+tank.getTankId()+";"+tank.rect.x+";"+tank.rect.y+";"+tank.pos.toString(),null);
		tank.rect.update(tank);
		tank.rect.move(tank);
		for(int g =0;g<health.size();g++){
			if(g<health.size())
			if(Rect.isIntersect(tank.rect, health.get(g).rect)){
				this.send_to_all("table;health;remove;"+health.get(g).id,null);
				System.out.println("health");
				health.remove(g);
				g--;
				tank.health = tank.hull.health;
				this.updateHealth(tank.user);
				
			}
		}
		if(!tank.rect.isMove(map)){
		tank.field.kill(tank);
		
		}
		}
		
		
	}
	
}

public void removeUser(User user) {
users.remove(user);
tanks.remove(user);
socket.remove(user);
tank_array.remove(user);
	
}

public void sendenemy(User user){
	for(int i =0;i<tank_array.size();i++){
		
		if(user.tank.id!=tank_array.get(i).id){
		user.send("tables;enemy;init;"+tank_array.get(i).getTankId()+";"+tank_array.get(i).user.jsonObjecttank);
		}
	}
}


}
