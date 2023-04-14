package uk.AntonSibgatulin.Battle.Tank;

import uk.AntonSibgatulin.Battle.BattleField;

public interface ITank {
	
    public Object clone();
		public BattleField getField();
		
	void fire(String str);
	public TankModel getTank();
	
	public Hull getHull();
	public String getType();
	public long getTimeReload();
	
	public double getMaxShot();
	public double getMinShot();
	public double getSpeedShot();
	public double maxDistance();
	public double maxSplash();
	public double minSplash();

	public void setField(BattleField field);

	void setTank(TankModel tank);
	
	
}
