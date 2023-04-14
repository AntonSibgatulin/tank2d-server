package uk.AntonSibgatulin.Battle.SDM;

import java.util.ArrayList;

import uk.AntonSibgatulin.Battle.BattleField;

public class SDMModel {
	public static final int times = 30000; 
public BattleField field = null;
public  long starTime = System.currentTimeMillis();

public ArrayList<Star> stars = new ArrayList<>();

	public SDMModel(BattleField field) {
		
		this.field = field;
		
	}

}
