package map;

import java.io.File;

import blocks.Block;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class labyrint extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Map map = MapInterpreter.interpretMap(new File("map.txt"));

		Scene scene = new Scene(map, map.getWidth(), map.getHeight());
		primaryStage.setScene(scene);
		primaryStage.show();
		
		MapSolver.solveMap(map);
		AnimationTimer at = new AnimationTimer() {
		
			long before = 0;
			int times = 0;
			
			@Override
			public void handle(long now) {
				if (now-before >= 200_000_000) {
					MapSolver.draw(map, times);
					times++;
					before = now;
				}
				// TODO Auto-generated method stub
				
			}
		};
		at.start();

	}

	public static void main(String[] args) {
		launch();
	}

}
