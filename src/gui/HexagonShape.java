package gui;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HexagonShape extends Region {
	// color of each segment
	public String A;
	public String B;
	public String C;
	public String D;
	public String E;
	public String F;
	// Hexagon id
	public int id;
	private Text idText;
	// Segments of hexagon
	private Polygon triangle1;
	private Polygon triangle2;
	private Polygon triangle3;
	private Polygon triangle4;
	private Polygon triangle5;
	private Polygon triangle6;

	private final double angle30degree = Math.PI / 6;
	
	//public double centerX, centerY;

	public HexagonShape(double centerX, double centerY) {
		
		
		// Node dimensions
		final double WIDTH = 200, HEIGHT = 200;
		double radius = Math.min(WIDTH, HEIGHT) * 0.4;

		// Create a pane, and place polygons to pane
		Pane pane = new Pane();

		// Create hexagon
		Polygon hexagon = new Polygon();
		hexagon.setFill(Color.WHITE);
		hexagon.setStroke(Color.BLACK);
		ObservableList<Double> list = hexagon.getPoints();

		// Add points to the hexagon list
		for (int i = 0; i < 6; i++) {
			list.add(centerX + radius * Math.cos(2 * i * angle30degree));
			list.add(centerY - radius * Math.sin(2 * i * angle30degree));
		}

		// Create triangles
		this.triangle1 = new Polygon();
		ObservableList<Double> tri1List = this.triangle1.getPoints();
		this.triangle2 = new Polygon();
		ObservableList<Double> tri2List = this.triangle2.getPoints();
		this.triangle3 = new Polygon();
		ObservableList<Double> tri3List = this.triangle3.getPoints();
		this.triangle4 = new Polygon();
		ObservableList<Double> tri4List = this.triangle4.getPoints();
		this.triangle5 = new Polygon();
		ObservableList<Double> tri5List = this.triangle5.getPoints();
		this.triangle6 = new Polygon();
		ObservableList<Double> tri6List = this.triangle6.getPoints();

		// color null data triangles
		triangle1.setFill(Color.LIGHTGRAY);
		triangle1.setStroke(Color.BLUEVIOLET);
		triangle2.setFill(Color.LIGHTGRAY);
		triangle2.setStroke(Color.BLUEVIOLET);
		triangle3.setFill(Color.LIGHTGRAY);
		triangle3.setStroke(Color.BLUEVIOLET);
		triangle4.setFill(Color.LIGHTGRAY);
		triangle4.setStroke(Color.BLUEVIOLET);
		triangle5.setFill(Color.LIGHTGRAY);
		triangle5.setStroke(Color.BLUEVIOLET);
		triangle6.setFill(Color.LIGHTGRAY);
		triangle6.setStroke(Color.BLUEVIOLET);

		// Add points to each triangle list
		createTriangle(tri2List, radius, centerX, centerY, 0);
		createTriangle(tri1List, radius, centerX, centerY, 2);
		createTriangle(tri6List, radius, centerX, centerY, 4);
		createTriangle(tri5List, radius, centerX, centerY, 6);
		createTriangle(tri4List, radius, centerX, centerY, 8);
		createTriangle(tri3List, radius, centerX, centerY, 10);

		// Text id
		idText = new Text(centerX - 10, centerY + 10, "");
		idText.setFill(Color.LIGHTGREY);
		idText.setStroke(Color.BLUEVIOLET);
		idText.setFont(Font.font("Arial", FontWeight.BOLD, 35));
		
		
		
		
		// Add all to pane and pane to region
		pane.getChildren().addAll(hexagon, this.triangle1, this.triangle2, this.triangle3, this.triangle4,
				this.triangle5, this.triangle6, idText);
		getChildren().add(pane);

	}

	// Adds triangle polygon points
	private void createTriangle(ObservableList<Double> vectors, double radius, double centerX, double centerY,
			int radian) {
		vectors.add(centerX);
		vectors.add(centerY);
		vectors.add(centerX + radius * Math.cos(radian * angle30degree));
		vectors.add(centerY - radius * Math.sin(radian * angle30degree));
		vectors.add(centerX + radius * Math.cos((radian + 2) * angle30degree));
		vectors.add(centerY - radius * Math.sin((radian + 2) * angle30degree));

	}

	// Load Hexagon and colors
	public void loadHexagon(List<String> colors, int id) {
		this.A = colors.get(0);
		colorTriangle(triangle1, this.A);
		this.B = colors.get(1);
		colorTriangle(triangle2, this.B);
		this.C = colors.get(2);
		colorTriangle(triangle3, this.C);
		this.D = colors.get(3);
		colorTriangle(triangle4, this.D);
		this.E = colors.get(4);
		colorTriangle(triangle5, this.E);
		this.F = colors.get(5);
		colorTriangle(triangle6, this.F);

		this.id = id;
		idText.setText(Integer.toString(id));
	}

	// Helper method to color a Triangle
	private void colorTriangle(Polygon triangle, String segment) {
		if (segment.equals("R")) {
			triangle.setFill(Color.RED);
			triangle.setStroke(Color.DARKGRAY);
		} else if (segment.equals("B")) {
			triangle.setFill(Color.BLUE);
			triangle.setStroke(Color.DARKGRAY);
		} else if (segment.equals("Y")) {
			triangle.setFill(Color.YELLOW);
			triangle.setStroke(Color.DARKGRAY);
		} else if (segment.equals("G")) {
			triangle.setFill(Color.GREEN);
			triangle.setStroke(Color.DARKGRAY);
		} else if (segment.equals("O")) {
			triangle.setFill(Color.ORANGE);
			triangle.setStroke(Color.DARKGRAY);
		} else if (segment.equals("P")) {
			triangle.setFill(Color.PURPLE);
			triangle.setStroke(Color.DARKGRAY);
		}
		triangle.setStrokeWidth(.5);
	}
}
