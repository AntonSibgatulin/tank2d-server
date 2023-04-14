package uk.AntonSibgatulin.Battle.Tank;

public class Hull {
	public int health = 0;
	
public int w,h;
public String name = null;
public int speed = 0;

	public Hull(int w,int h,int speed,int health) {
		this.w = w;
		this.h = h;
		this.speed = speed;
		this.health = health;
	}

}
