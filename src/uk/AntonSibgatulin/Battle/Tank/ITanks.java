package uk.AntonSibgatulin.Battle.Tank;

import uk.AntonSibgatulin.Battle.BattleField;
import uk.AntonSibgatulin.User.User;

public interface ITanks {
	public int getTankId();
	public User getUser();
	public ITank getWeapons();
	public String getTeams();
	public int getHealth();
	public void damage(float damage,TankModel from);
	public BattleField getField();
	
	public Rect getRect();
}
