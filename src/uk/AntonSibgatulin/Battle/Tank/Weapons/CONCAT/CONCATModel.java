package uk.AntonSibgatulin.Battle.Tank.Weapons.CONCAT;

import java.util.HashMap;
import java.util.Random;

import uk.AntonSibgatulin.Battle.BattleField;
import uk.AntonSibgatulin.Battle.Tanks;
import uk.AntonSibgatulin.Battle.Tank.Hull;
import uk.AntonSibgatulin.Battle.Tank.ITank;
import uk.AntonSibgatulin.Battle.Tank.Pos;
import uk.AntonSibgatulin.Battle.Tank.Rect;
import uk.AntonSibgatulin.Battle.Tank.TankModel;

public class CONCATModel extends Tanks implements ITank{

	public CONCATModel(String name,int maxShot,int minShot,long reload,int maxsplash,int minsplash,int speed,long maxDistance,Hull hull) {
		super(name,maxShot,minShot,reload,maxsplash,minsplash,speed,maxDistance,hull);
		}
	public static final int reload = 20;


	@Override
	public BattleField getField() {
		// TODO Auto-generated method stub
		return field;
	}

	
	@Override
	public TankModel getTank() {
		// TODO Auto-generated method stub
		return tank;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public long getTimeReload() {
		// TODO Auto-generated method stub
		return timeReload;
	}

	@Override
	public double getMaxShot() {
		// TODO Auto-generated method stub
		return maxShot;
	}

	@Override
	public double getMinShot() {
		// TODO Auto-generated method stub
		return minShot;
	}

	@Override
	public double getSpeedShot() {
		// TODO Auto-generated method stub
		return speed;
	}

	@Override
	public double maxDistance() {
		// TODO Auto-generated method stub
		return maxDistance;
	}

	@Override
	public double maxSplash() {
		// TODO Auto-generated method stub
		return maxSplash;
	}

	@Override
	public double minSplash() {
		// TODO Auto-generated method stub
		return minSplash;
	}

	@Override
	public Hull getHull() {
		// TODO Auto-generated method stub
		return hull;
	}

	@Override
	public void setField(BattleField field) {
		this.field = field;
		
	}
	@Override
	public void setTank(TankModel tank) {
		this.tank = tank;
		
	}



	@Override
	public void fire(String str) {
		if(System.currentTimeMillis()-lastTime >=this.getTimeReload() && tank.init){
			lastTime = System.currentTimeMillis();
			
			Pos pos = this.tank.pos;
			double rnd = this.minSplash+new Random().nextInt((int) (maxSplash-minSplash));
		
			
			
			
			double recx = 0;
			 int distance = 0;
			double recy = 0;
			if(pos==Pos.up || pos == Pos.down){
				recy = tank.rect.y+tank.hull.h/3;
				recx = tank.rect.x+tank.hull.w/2-rnd/4/2;
			}
			
			if(pos==Pos.left || pos == Pos.right){
				recy = tank.rect.y+tank.hull.h/2-3;
				recx = tank.rect.x+tank.hull.w/2-rnd/4/2;
			}
			
			
			
			
			
			Rect vec = new Rect(recx,recy,rnd,rnd);
			Rect start = new Rect(tank.rect.x,tank.rect.y,tank.hull.h,tank.hull.h);
			
			int id = field.idShoot++;
			field.send_to_all("table;shot;create;"+id+";"+vec.x+";"+vec.y+";"+rnd, null);
			HashMap<TankModel ,String> runner = new HashMap<>();
			
			while(true){
				
				try {
					
					Thread.sleep(reload/3);
					vec.move(pos, this.speed);
					start.move(pos,this.speed);
		
					if(distance>this.maxDistance){
						field.send_to_all("table;shot;bomb;"+id+";"+(int)vec.x+";"+(int)vec.y, null);
						break;
					}
					distance+=this.speed;
					field.send_to_all("table;shot;move;"+id+";"+(int)vec.x+";"+(int)vec.y, null);
					
					boolean br = false;
					
					for(int j = 0;j<field.map.block.size();j++){
						if(Rect.isIntersect(vec, field.map.block.get(j)) && !br){
							br = true;
							
						}
					}
					if(br){
						field.send_to_all("table;shot;bomb;"+id+";"+(int)vec.x+";"+(int)vec.y, null);
						
						break;
					}
					
					for(int i = 0;i<field.tank_array.size();i++){
						TankModel model = field.tank_array.get(i);
						if(model!=tank){
							if(Rect.isIntersect(start, model.rect)&&model.init && runner.get(model)==null){
								if(!model.teams.equals(tank.teams)){
									field.shoot(tank, model, (float) (minShot+new Random().nextInt((int) (this.maxShot-this.minShot))));
								runner.put(model, "run");
								}
							//	field.send_to_all("table;shot;bomb;"+id+";"+(int)vec.x+";"+(int)vec.y, null);
							//br = true;
							//break;
							}
							if(Rect.isIntersect(vec, model.rect)&&model.init){
								if(!model.teams.equals(tank.teams)){
									field.shoot(tank, model, (float) (minShot+new Random().nextInt((int) (this.maxShot-this.minShot))));
								}
								field.send_to_all("table;shot;bomb;"+id+";"+(int)vec.x+";"+(int)vec.y, null);
							br = true;
							break;
							}
							
						}
					}
					if(br){
						break;
					}
					
					if(vec.x <=0 || vec.y<=0 || vec.x>field.map.w || vec.y>field.map.h){
						field.send_to_all("table;shot;bomb;"+id+";"+(int)vec.x+";"+(int)vec.y, null);
						
						break;
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	 @Override
	 public Object clone()
            
        {
          return (ITank)new CONCATModel( type, maxShot, minShot, reload, maxSplash, minSplash, (int)speed, (long)maxDistance, hull);
          
        }

}
