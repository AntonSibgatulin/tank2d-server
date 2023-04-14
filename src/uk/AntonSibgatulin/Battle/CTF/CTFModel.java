package uk.AntonSibgatulin.Battle.CTF;

import java.util.HashMap;

import uk.AntonSibgatulin.Battle.BattleField;
import uk.AntonSibgatulin.Battle.Tank.Rect;
import uk.AntonSibgatulin.Battle.Tank.TankModel;
import uk.AntonSibgatulin.Battle.Tank.Math.Vector2d;

public class CTFModel extends Thread{
	public static final double flag_w = 32;
	public static final double flag_h = 32;
	
public FlagModel blue = null;
public FlagModel red = null;
public BattleField field = null;
	public CTFModel(HashMap<String,String> flags,BattleField field) {
		String[] bl = flags.get("blue").split(";");
		String[] rd = flags.get("red").split(";");
		blue = new FlagModel(new Rect(Double.valueOf(bl[0]),Double.valueOf(bl[1]),flag_w,flag_h),"blue");
		red = new FlagModel(new Rect(Double.valueOf(rd[0]),Double.valueOf(rd[1]),flag_w,flag_h),"red");
		this.field = field;
		
		
	}
	public void flag_up(TankModel tank,FlagModel flag){
		flag.have.add(tank.getTankId());
		
	}
	@Override 
	public void run(){
		while(true){
			try {
				Thread.sleep(20);
				for(int i = 0;i<field.tank_array.size();i++){
					TankModel tank = field.tank_array.get(i);
						if(tank.getTeams().equals("blue")){
							if(Rect.isIntersect(tank.getRect(), red.position)){
								red.position.x = tank.getRect().x;
								red.position.y = tank.getRect().y;
							}
							if(Rect.isIntersect(tank.getRect(), blue.position) && !blue.position.isHave(blue.start_position)){
									blue.return_flag();
							}
						}
						if(tank.getTeams().equals("red")){
							
							if(Rect.isIntersect(tank.getRect(), blue.position)){
								blue.position.x = tank.getRect().x;
								blue.position.y = tank.getRect().y;
							}
							if(Rect.isIntersect(tank.getRect(), red.position) && !red.position.isHave(red.start_position)){
									red.return_flag();
							}
						}
					
					
					
					
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
