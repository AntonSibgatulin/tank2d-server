package uk.AntonSibgatulin.Battle.Tank;

import uk.AntonSibgatulin.Battle.BattleField;
import uk.AntonSibgatulin.User.User;

public class TankModel implements ITanks{
public Pos pos = Pos.up;
public boolean init = false;

public Rect rect = null;
public long id = 0;
public User user = null;

public int health = 0;
public BattleField field = null;
public ITank tank = null;


public String teams = null;
public Hull hull = null;
public  String color=null;
	public TankModel(ITank tank,User user,BattleField battle) {
		this.tank = tank;
		field = battle;
		this.hull  = tank.getHull();
		this.id = user.id;
		rect = new Rect(0,0,hull.w,hull.h);
		this.user = user;
		
		
		
	}

	@Override
	public int getTankId() {
		// TODO Auto-generated method stub
		return (int)id;
	}

	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return user;
	}

	@Override
	public ITank getWeapons() {
		// TODO Auto-generated method stub
		return tank;
	}

	@Override
	public Rect getRect() {
		// TODO Auto-generated method stub
		return rect;
	}

	@Override
	public String getTeams() {
		// TODO Auto-generated method stub
		return teams;
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

	@Override
	public void damage(float damage, TankModel from) {
		System.out.println(health);
		if(health-damage>0){
			health-= damage;
			field.send_to_tank("table;health;"+getHealth(), this);
			
		}else {
			
			this.init = false;
			field.kill(from, this);
			
			
		}
		
	}

	@Override
	public BattleField getField() {
		// TODO Auto-generated method stub
		return field;
	}

}
