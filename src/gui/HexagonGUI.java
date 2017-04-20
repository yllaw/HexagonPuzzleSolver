package gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

import hexagon.Hexagon;
import hexagon.HexagonPosition;
import hexagon.HexagonSolver;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HexagonGUI extends Application {
	// Pane for Hexagon flower
	Pane p;

	//HexagonShapes for flower
	HexagonShape h1;
	HexagonShape h2;
	HexagonShape h3;
	HexagonShape h4;
	HexagonShape h5;
	HexagonShape h6;
	HexagonShape h7;

	//Solution count & selected index
	Text selectedIndexOverListSize = new Text(25, 50, "1. Load your Hexagon List! \n2. Click Solve!");
	// Hexagon data
	ArrayList<Hexagon> sevenHexagons = new ArrayList<>();
	// allSolutions to sevenHexagons
	List<List<HexagonPosition>> allSolutions;
	// Index of current solution
	int selectedSolutionIndex = 0;
	// sevenHexagons solved?
	boolean solved = false;
	// TODO: clear allSolutions when choosing new file
	@Override
	public void start(Stage primaryStage) {
		BorderPane bp = new BorderPane();
		this.p = new Pane();

		// HexagonShapes
		this.h1 = new HexagonShape(300, 300);
		this.h2 = new HexagonShape(300, 161);
		this.h3 = new HexagonShape(420, 230);
		this.h4 = new HexagonShape(420, 369);
		this.h5 = new HexagonShape(300, 439);
		this.h6 = new HexagonShape(180, 369);
		this.h7 = new HexagonShape(180, 230);

		// File Menu
		Menu fileMenu = new Menu("_File");

		// File Menu Items
		MenuItem load = new MenuItem("Load Hexagons...");
		load.setOnAction(e -> {
			try {
				sevenHexagons = loadFromFile();// also colors HexagonShapes
				this.h1.loadHexagon(sevenHexagons.get(0).getColorList(), sevenHexagons.get(0).getID());
				this.h2.loadHexagon(sevenHexagons.get(1).getColorList(), sevenHexagons.get(1).getID());
				this.h3.loadHexagon(sevenHexagons.get(2).getColorList(), sevenHexagons.get(2).getID());
				this.h4.loadHexagon(sevenHexagons.get(3).getColorList(), sevenHexagons.get(3).getID());
				this.h5.loadHexagon(sevenHexagons.get(4).getColorList(), sevenHexagons.get(4).getID());
				this.h6.loadHexagon(sevenHexagons.get(5).getColorList(), sevenHexagons.get(5).getID());
				this.h7.loadHexagon(sevenHexagons.get(6).getColorList(), sevenHexagons.get(6).getID());

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		fileMenu.getItems().add(load);

		MenuItem save = new MenuItem("Save Solution...");
		// TODO: save.setOnAction(e -> saveSolutionToFile());
		fileMenu.getItems().add(save);
		fileMenu.getItems().add(new SeparatorMenuItem());

		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(e -> System.exit(0));
		fileMenu.getItems().add(exit);

		// Solve Menu
		Menu solveMenu = new Menu("_Solve");

		// Solve Menu Items
		MenuItem solve = new MenuItem("Solve!");
		solve.setOnAction(e -> solve());
		solveMenu.getItems().add(solve);
		MenuItem stats = new MenuItem("Stats...");
		// TODO stats.setOnAction(e -> showStats());
		solveMenu.getItems().add(stats);

		// Help Menu
		Menu helpMenu = new Menu("_Help");

		// Help Menu Items
		MenuItem about = new MenuItem("About...");
		// TODO about.setOnAction(e -> aboutDialog);
		helpMenu.getItems().add(about);

		// Menu Bar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, solveMenu, helpMenu);

		// Left Arrow Button
		Image leftArrow = new Image(getClass().getResourceAsStream("leftArrow.png"));
		ImageView l = new ImageView(leftArrow);
		l.setFitWidth(98);
		l.setFitHeight(59);
		Button leftBtn = new Button("", l);
		leftBtn.setStyle("-fx-background-color: #3a3a3a;");
		leftBtn.setOnAction(e -> {
			if (selectedSolutionIndex > 0 && sevenHexagons != null && solved == true) {
				selectedSolutionIndex--;
				paintSolution();
			}
		});
		leftBtn.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent k) {
				if (k.getCode().equals(KeyCode.LEFT)) {
					if (selectedSolutionIndex > 0 && sevenHexagons != null && solved == true) {
						selectedSolutionIndex--;
						paintSolution();
					}
					// previous solution
				}
			}
		});

		// Right Arrow Button;
		Image rightArrow = new Image(getClass().getResourceAsStream("rightArrow.png"));
		ImageView r = new ImageView(rightArrow);
		r.setFitWidth(98);
		r.setFitHeight(59);
		Button rightBtn = new Button("", r);
		rightBtn.setStyle("-fx-background-color: #3a3a3a;");
		rightBtn.setOnAction(e -> {
			if (selectedSolutionIndex < allSolutions.size() - 1 && sevenHexagons != null && solved == true) {
				selectedSolutionIndex++;
				paintSolution();
			}
		});
		rightBtn.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent k) {
				if (k.getCode().equals(KeyCode.RIGHT)) {
					if (selectedSolutionIndex < allSolutions.size() - 1 && sevenHexagons != null && solved == true) {
						selectedSolutionIndex++;
						paintSolution();
					}
					// next solution
				}
			}
		});

		// Text indexOverListSize
		this.selectedIndexOverListSize.setFill(Color.LIGHTGREY);
		this.selectedIndexOverListSize.setStroke(Color.BLUEVIOLET);
		this.selectedIndexOverListSize.setFont(Font.font("Arial", FontWeight.BOLD, 25));

		// Hbox
		HBox hbox = new HBox();
		hbox.getChildren().addAll(leftBtn, rightBtn);
		hbox.setPadding(new Insets(10, 10, 10, 10));
		hbox.setSpacing(366);

		// BorderPane
		bp.setTop(menuBar);
		bp.setCenter(this.p);
		bp.setBottom(hbox);
		bp.setStyle("-fx-background-color: #3a3a3a;");

		// hex flower pane
		this.p.getChildren().addAll(this.h1, this.h2, this.h3, this.h4, this.h5, this.h6, this.h7,
				this.selectedIndexOverListSize);

		// Scene
		Scene scene = new Scene(bp, 600, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Hexagon Puzzle Solver");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	// solves & paints first solution
	private void solve() throws NullPointerException {
		if (sevenHexagons == null || sevenHexagons.size() != 7) {
			throw new NullPointerException();
		}
		this.allSolutions = HexagonSolver.getAllResults(sevenHexagons);
		solved = true;
		paintSolution();
	}
	
	// Paints hex flower
	private void paintSolution() {
		paintHexagon(h1, 0);
		paintHexagon(h2, 1);
		paintHexagon(h3, 2);
		paintHexagon(h4, 3);
		paintHexagon(h5, 4);
		paintHexagon(h6, 5);
		paintHexagon(h7, 6);
	}
	// paints individual Hexagon at SelectedSolutionIndex
	private void paintHexagon(HexagonShape h, int hexagonIndex) {
		List<HexagonPosition> solution = this.allSolutions.get(selectedSolutionIndex);

		HexagonPosition hexPosition = solution.get(hexagonIndex);
		List<String> colors = this.sevenHexagons.get(hexPosition.getHexagonId() - 1).getRotatedColors(hexPosition);
		h.loadHexagon(colors, hexPosition.getHexagonId());
		showSelectedIndex();

	}

	// displays the selectedIndexOverListSize
	private void showSelectedIndex() {
		p.getChildren().remove(this.selectedIndexOverListSize);
		selectedIndexOverListSize.setText("Solution: " + (selectedSolutionIndex + 1) + "/" + allSolutions.size());
		p.getChildren().add(selectedIndexOverListSize);
	}
	
	//Initialize sevenHexagons
	private static ArrayList<Hexagon> initializeHexagons(ArrayList<String> hexValsIn) throws NullPointerException {
		if (hexValsIn == null) {
			throw new NullPointerException("null value list");
		}
		ArrayList<Hexagon> hexagons = new ArrayList<>();
		Hexagon hexagon1 = new Hexagon(Integer.parseInt(hexValsIn.get(0)), hexValsIn.get(1));
		hexagons.add(hexagon1);
		Hexagon hexagon2 = new Hexagon(Integer.parseInt(hexValsIn.get(2)), hexValsIn.get(3));
		hexagons.add(hexagon2);
		Hexagon hexagon3 = new Hexagon(Integer.parseInt(hexValsIn.get(4)), hexValsIn.get(5));
		hexagons.add(hexagon3);
		Hexagon hexagon4 = new Hexagon(Integer.parseInt(hexValsIn.get(6)), hexValsIn.get(7));
		hexagons.add(hexagon4);
		Hexagon hexagon5 = new Hexagon(Integer.parseInt(hexValsIn.get(8)), hexValsIn.get(9));
		hexagons.add(hexagon5);
		Hexagon hexagon6 = new Hexagon(Integer.parseInt(hexValsIn.get(10)), hexValsIn.get(11));
		hexagons.add(hexagon6);
		Hexagon hexagon7 = new Hexagon(Integer.parseInt(hexValsIn.get(12)), hexValsIn.get(13));
		hexagons.add(hexagon7);

		for (Hexagon h : hexagons) {
			System.out.println(h.toString());
		}
		return hexagons;
	}

	// Load Hexagon List
	private static ArrayList<Hexagon> loadFromFile() throws IOException {
		String userDir = System.getProperty("user.home");
		JFileChooser fc = new JFileChooser(userDir + "/Desktop");
		fc.setDialogTitle("Select Hexagon List");
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		ArrayList<String> hexVals = null;
		int retVal = fc.showOpenDialog(null);
		if (retVal == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fc.getSelectedFile();

			Scanner scanner = new Scanner(selectedFile);
			hexVals = new ArrayList<>();

			while (scanner.hasNext()) {
				String lines[] = scanner.nextLine().split("\\r?\\n");
				for (String line : lines) {
					hexVals.addAll(Arrays.asList(line.split(" ")));
				}
			}

			scanner.close();
		}

		return initializeHexagons(hexVals);

	}

	public static void main(String[] args) throws IOException {
		launch(args);
	}
}
