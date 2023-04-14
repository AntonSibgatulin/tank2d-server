package uk.AntonSibgatulin.Battle.CTF;

import java.util.ArrayList;

import uk.AntonSibgatulin.Battle.Tank.Rect;
import uk.AntonSibgatulin.Battle.Tank.Math.Vector2d;

public class FlagModel {
public ArrayList<Integer> have = new ArrayList<>();

public Rect start_position = null;
public Rect position = null;
public String color = null;

	public FlagModel(Rect start_position,String color) {
		
		have = new ArrayList<>();
		this.start_position = start_position;
		this.position.x = start_position.x;
		this.position.y = start_position.y;
		this.position.w = start_position.w;
		this.position.h = start_position.h;
		
		this.color = color;
		
		
	}
public void restart(){
	have = new ArrayList<>();
}
public void return_flag(){
	position.x = start_position.x;
	position.y = start_position.y;
}
}
