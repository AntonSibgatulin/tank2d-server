package uk.AntonSibgatulin.Battle.Map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MapCreator extends JPanel	implements ActionListener{
	public JSONObject json = new JSONObject();
	public int w = 0;
	public int h = 0;

	public JSONArray array_block = new JSONArray();
	

	public JSONArray array_spawn_blj = new JSONArray();

	public JSONArray array_spawn_rdj = new JSONArray();
	
	public ArrayList<Rectangle> array_spawn_bl = new ArrayList<>();
	public ArrayList<Rectangle> array_spawn_rd = new ArrayList<>();
	
	public String name = "Card #?";
	public int type = 0;
	public int mx , my;
	//0 block
	//1 spawn blue
	//2 spawn red
	//3 spawn fr
	//4 spawn fb
	//5 boor
public Rectangle rect = null;
public ArrayList<Rectangle> rect_list = new ArrayList<>();
public ArrayList<Rectangle> rect_list_back = new ArrayList<>();
boolean touch = false;

public Timer t =new Timer(20,this);
	public MapCreator() {
		t.start();
		super.setFocusable(true);
		super.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {

					int k = arg0.getKeyCode();
					if(k == arg0.VK_ENTER){
						String g = JOptionPane.showInputDialog("x,y,x,size");
						String str[] = g.split(" ");
						for(int i = 0;i<Integer.valueOf(str[2]);i++){
Rectangle rect = new Rectangle(Integer.valueOf(str[0])+i*Integer.valueOf(str[3]),Integer.valueOf(str[1]),Integer.valueOf(str[3]),Integer.valueOf(str[3]));
JSONArray ar = new JSONArray();
ar.put(rect.x);
ar.put(rect.y);
ar.put(rect.width);
ar.put(rect.height);
array_block.put(ar);

rect_list.add(rect);
						}
						
					}else if(k == arg0.VK_N){
						name = JOptionPane.showInputDialog("Card name");
						
					}
					else if(k == arg0.VK_S){
						String st[] = JOptionPane.showInputDialog("Size card").split(" ");
						w = Integer.valueOf(st[0]);
						h = Integer.valueOf(st[1]);
					}
					
					else if(k == arg0.VK_SHIFT){
						String g = JOptionPane.showInputDialog("x,y,y,size");
						String str[] = g.split(" ");
						for(int i = 0;i<Integer.valueOf(str[2]);i++){
Rectangle rect = new Rectangle(Integer.valueOf(str[0]),Integer.valueOf(str[1])+i*Integer.valueOf(str[3]),Integer.valueOf(str[3]),Integer.valueOf(str[3]));
JSONArray ar = new JSONArray();
ar.put(rect.x);
ar.put(rect.y);
ar.put(rect.width);
ar.put(rect.height);
array_block.put(ar);					
rect_list.add(rect);
						}
						
					}
					
					else{
					type = k-48;
					System.out.println(k-48);
					}
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode()==arg0.VK_Z){
					if(rect_list.size()>0){
						rect_list_back.add(rect_list.get(rect_list.size()-1));
						
					rect_list.remove(rect_list.size()-1);
					}
					
				}
				if(arg0.getKeyCode()==arg0.VK_A){
					if(rect_list_back.size()>0 ){
						rect_list.add(rect_list_back.get(rect_list_back.size()-1));
						
						rect_list_back.remove(rect_list_back.size()-1);
					}
				}
				
				if(arg0.getKeyCode()==arg0.VK_ALT){
					JSONObject json = new JSONObject();
					try {
						json.put("name", name);
						json.put("block", array_block);
						json.put("width", w);
						json.put("height", h);
						
						json.put("blue_spawn", array_spawn_blj);
						json.put("red_spawn", array_spawn_rdj);
						
						System.out.println(json);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		super.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
			//	System.out.println("down "+e.getX()+" "+e.getY());
				if(type==0){
					rect = new Rectangle(e.getX(),e.getY(),1,1);
					touch = true;
					
				}
				if(type == 9){
					rect = new Rectangle(e.getX(),e.getY(),64,64);
					//touch = true;
				}
				if(type == 8){
					rect = new Rectangle(e.getX(),e.getY(),32,32);
					//touch = true;
				}
				if(type==5){
					array_spawn_bl.add(new Rectangle(e.getX(),e.getY(),64,64));
					JSONArray list = new JSONArray();
					list.put(e.getX());
					list.put(e.getY());
					list.put(64);
					list.put(64);
					array_spawn_blj.put(list);
				}
				
				
				if(type==4){
					array_spawn_rd.add(new Rectangle(e.getX(),e.getY(),64,64));
					JSONArray list = new JSONArray();
					list.put(e.getX());
					list.put(e.getY());
					list.put(64);
					list.put(64);
					array_spawn_rdj.put(list);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if(rect!=null){
					if(type == 0){
						JSONArray ar = new JSONArray();
						ar.put(rect.x);
						ar.put(rect.y);
						ar.put(rect.width);
						ar.put(rect.height);
						array_block.put(ar);
					}
			rect_list.add((Rectangle) rect.clone());
				}
			touch = false;
			}
			
		});
		
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
	public static void main(String[]args){
		JFrame j = new JFrame("map creator");
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setExtendedState(JFrame.MAXIMIZED_BOTH);
		j.add(new MapCreator());
		j.setVisible(true);
		
		
	}

@Override
public void paint(Graphics g){
	g = (Graphics2D)g;
	g.setColor(Color.black);
g.fillRect(0, 0, getWidth(), getHeight());
if(touch){
	//rect = new Rectangle(rect.x,rect.y,MouseInfo.getPointerInfo().getLocation().x-rect.x,MouseInfo.getPointerInfo().getLocation().y-rect.y-20);
	rect.width =MouseInfo.getPointerInfo().getLocation().x-rect.x;
rect.height =MouseInfo.getPointerInfo().getLocation().y-rect.y-20;
}
//array_spawn_bl
g.setColor(Color.gray);
for(int i = 0;i<rect_list.size();i++){
	((Graphics2D) g).fill(rect_list.get(i));
}



g.setColor(Color.red);
for(int i = 0;i<array_spawn_rd.size();i++){
	((Graphics2D) g).fill(array_spawn_rd.get(i));
}
g.setColor(Color.blue);
for(int i = 0;i<array_spawn_bl.size();i++){
	((Graphics2D) g).fill(array_spawn_bl.get(i));
}
if(rect!=null){
((Graphics2D) g).fill(rect);
}

}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		
	}
}

