package uk.AntonSibgatulin.Battle;

import uk.AntonSibgatulin.Battle.Tank.Hull;
import uk.AntonSibgatulin.Battle.Tank.Pos;
import uk.AntonSibgatulin.Battle.Tank.TankModel;

public class Tanks {
	public int maxSplash =  0;
	public int minSplash = 0;
	public long timeReload = 0;
	public TankModel tank = null;
	public Pos pos = null;
	public long lastTime = 0;
	public String type = null;
	public double maxDistance = 0;
	public double speed = 0;
	public BattleField field = null;
	public int maxShot = 0;
	public int minShot = 0;
	
	public int width = 0;
	public int height = 0;
	public int price = 0;
	public Hull hull = null;
	public Tanks(String name,int maxShot,int minShot,long timereload,int maxSplash,int minSplash,int speed,long maxDistance,Hull hull){
		type = name;
		this.maxShot = maxShot;
		this.minShot = minShot;
		this.timeReload = timereload;
		this.maxSplash = maxSplash;
		this.hull = hull;
		this.minSplash = minSplash;
		this.speed = speed;
		this.maxDistance = maxDistance;
		
	}

}
