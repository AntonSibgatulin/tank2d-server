package uk.AntonSibgatulin.Battle.Tank.Math;

import uk.AntonSibgatulin.Battle.Tank.Pos;
import uk.AntonSibgatulin.Battle.Tank.Rect;

public class Vector2d {
public double x,y;
public Rect rect = null;

public Vector2d(double x,double y) {
	this.x = x;
	this.y = y;
	
}

public double w=0,h=0;
public Vector2d(double x,double y,double w,double h) {
	this.x = x;
	this.y = y;
	this.w = w;
	this.h = h;
	rect = new Rect(x,y,w,h);
}

	public void move(Pos pos,double v){
		if(pos == pos.up){
			y-=v;
		}
		
		if(pos == pos.down){
			y+=v;
		}
		
		if(pos == pos.left){
			x-=v;
		}
		
		if(pos == pos.right){
			x+=v;
		}
	}

}
