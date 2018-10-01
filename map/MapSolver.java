package map;

import java.util.ArrayList;

import blocks.Block;
import blocks.ClosedBlock;
import blocks.GoalBlock;
import blocks.OpenBlock;
import blocks.StartBlock;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Circle;

public class MapSolver {
	
	static Map map;
	static int steps = 0;
	private static boolean solution = false;
	public static ArrayList<Integer> beenX = new ArrayList<Integer>();
	public static ArrayList<Integer> beenY = new ArrayList<Integer>();
	
	public static void solveMap(Map map) {
		MapSolver.map = map;
		int startX = map.getStartX();
		int startY = map.getStartY();
		
		beenX.add(startX);
		beenY.add(startY);
		
		solve(startX, startY, 1); //up
		solve(startX, startY, 2); //höger
		solve(startX, startY, 3); //ner
		solve(startX, startY, 4); //vänster
	}
	
	public static void solve(int x, int y, int dir) {
		 if (steps > 1000 || solution) {
			 return;
		 }
		 if (dir==1) {
			 y--;
		 }
		 if (dir==2) {
			 x++;
		 }
		 if (dir==3) {
			 y++;
		 }
		 if (dir==4) {
			 x--;
		 }
		 Block b = map.getBlock(x, y);
		 if (b instanceof ClosedBlock) {
			 return;
		 }
		 if (b == null) {
			 return;
		 }
		 if (beenhere(x,y)) {
			 return;
		 }
		 steps++;
		 beenX.add(x);
		 beenY.add(y);
		 if (b instanceof GoalBlock) {
			 System.out.println("Steps: " + steps);
			 solution = true;
			 return;
		 }
		 if (dir == 1) {
			 solve(x,y,1);
			 solve(x,y,2);
			 solve(x,y,4);
		 }
		 if (dir == 2) {
			 solve(x,y,1);
			 solve(x,y,3);
			 solve(x,y,2);
		 }
		 if (dir == 3) {
			 solve(x,y,3);
			 solve(x,y,2);
			 solve(x,y,4);
		 }
		 if (dir == 4) {
			 solve(x,y,1);
			 solve(x,y,3);
			 solve(x,y,4);
		 }
		 
		 
	
		
		
	} 
	
	public static boolean beenhere(int x, int y) {
		for (int i = 0 ; i < beenX.size(); i++) {
			if (x == beenX.get(i) && y == beenY.get(i)) {
				 return true;
			}
		}
		return false;
	}
	
	public static void draw(Group root, int times) {
		if (times >= beenX.size()) {
			return;
		} else {
			Circle cir = new Circle(beenX.get(times)*Block.SIZE+Block.SIZE/2,beenY.get(times)*Block.SIZE+Block.SIZE/2,10);
			cir.setFill(Color.YELLOW);

			if (times >= 900) {cir.setFill(Color.BLUE);}
			root.getChildren().add(cir);
		}
	}
	
}
