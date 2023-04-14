package uk.AntonSibgatulin.Battle.Tank;

import uk.AntonSibgatulin.Battle.Map.MapModel;

public class Rect {
public double x, y, w, h;

        public Rect() {
        }

        public Rect(double _x, double _y, double _w, double _h) {
        x = _x;
        y = _y;
        w = _w;
        h = _h;
        }

//пересечение квадратов
        
        /*
public static boolean isIntersect(Square a, Rect b) {
        return ((a.x < (b.x + b.w)) &&
                (b.x < (a.x + a.size)) &&
                (a.y < (b.y + b.h)) &&
                (b.y < (a.y + a.size)));
}
*/
        public static boolean isHave(Rect a,Rect b){
        	if(a.x == b.x && a.y == b.y ){
        		return true;
        	}
        	return false;
        	
        }
        
        
        public  boolean isHave(Rect a){
        	if(a.x == x && a.y == y ){
        		return true;
        	}
        	return false;
        	
        }
        public static boolean isIntersect(Rect a, Rect b) {
                return ((a.x < (b.x + b.w)) &&
                        (b.x < (a.x + a.w)) &&
                        (a.y < (b.y + b.h)) &&
                        (b.y < (a.y + a.h)));
        }

//пересечение квадратов с выталкиванием
        /*
public static boolean isLockIntersect(Square a, Rect b) {
        if (!isIntersect(a, b))
                return false;
        int x0 = b.x - (a.x - b.w);
        int y0 = b.y - (a.y - b.h);
        int x1 = (a.x + a.size) - b.x;
        int y1 = (a.y + a.size) - b.y;
        if (x1 < x0)
                x0 = -x1;
        if (y1 < y0)
                y0 = -y1;

        if (Math.abs(x0) < Math.abs(y0))
                a.x += x0;
        else if (Math.abs(x0) > Math.abs(y0))
                a.y += y0;
        else {
                a.x += x0;
                a.y += y0;
        }
        return true;
}

*/
public boolean  d=false,u=false,l=false,r=false ;
    	
public void move(TankModel tank){
	Pos pos = tank.pos;
	int v = tank.hull.speed;
	if(pos == pos.up){
		if(u && !d && !l&& !r){
		w = tank.hull.h;
		h = tank.hull.w;
		y-=v;
		}
	}
	
	if(pos == pos.down){
		if(!u && d && !l&& !r){
				y+=v;
		w = tank.hull.h;
		h = tank.hull.w;
	}
		}
	
	if(pos == pos.left){
	if(!u && !d && l&& !r){
			x-=v;
		h = tank.hull.h;
		w = tank.hull.w;
	}
	}
	
	if(pos == pos.right){
	if(!u && !d && !l&& r){
			
		x+=v;
		h = tank.hull.h;
		w = tank.hull.w;
		}
	}
	
}


public void move(Pos pos,double speed){
	double v = speed;
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

       
        public void update(TankModel tank){
        	Pos pos = tank.pos;
        	if(pos == pos.up){
    			w = tank.hull.h;
    			h = tank.hull.w;
    		
    		}
    		
    		if(pos == pos.down){
    			w = tank.hull.h;
    			h = tank.hull.w;
    		}
    		
    		if(pos == pos.left){
    			h = tank.hull.h;
    			w = tank.hull.w;
    		}
    		
    		if(pos == pos.right){
    			h = tank.hull.h;
    			w = tank.hull.w;
    		}
    	}
        @Override
        public String toString(){
        	return x+" "+y+" "+w+" "+h;
        }
        public static boolean isLockIntersect(Rect a, Rect b) {
            if (!isIntersect(a, b))
                    return false;
            double x0 = b.x - (a.x - b.w);
            double y0 = b.y - (a.y - b.h);
            double x1 = (a.x + a.w) - b.x;
            double y1 = (a.y + a.h) - b.y;
            if (x1 < x0)
                    x0 = -x1;
            if (y1 < y0)
                    y0 = -y1;

            if (Math.abs(x0) < Math.abs(y0))
                    a.x += x0;
            else if (Math.abs(x0) > Math.abs(y0))
                    a.y += y0;
            else {
                    a.x += x0;
                    a.y += y0;
            }
            return true;
    }

        
        
        
        
        public  boolean isLockIntersect( Rect b) {
            if (!isIntersect(this, b))
                    return false;
            double x0 = b.x - (this.x - b.w);
            double y0 = b.y - (this.y - b.h);
            double x1 = (this.x + this.w) - b.x;
            double y1 = (this.y + this.h) - b.y;
            if (x1 < x0)
                    x0 = -x1;
            if (y1 < y0)
                    y0 = -y1;

            if (Math.abs(x0) < Math.abs(y0))
                    this.x += x0;
            else if (Math.abs(x0) > Math.abs(y0))
                    this.y += y0;
            else {
                    this.x += x0;
                    this.y += y0;
            }
            return true;
    }

		public boolean isMove(MapModel map) {
			if(x<0 || y<0||x>map.w||y>map.h){
				return false;
			}
			return true;
		}


        };