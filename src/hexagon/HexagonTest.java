package hexagon;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class HexagonTest {

	public static List<Hexagon> instantiateHexagonList() {
		List<Hexagon> sevenHexagons = new ArrayList<>();

		sevenHexagons.add(new Hexagon(1, "YBGROG"));
		sevenHexagons.add(new Hexagon(2, "BRPRYO"));
		sevenHexagons.add(new Hexagon(3, "ROPGPG"));
		sevenHexagons.add(new Hexagon(4, "BBGBPG"));
		sevenHexagons.add(new Hexagon(5, "YPGORP"));
		sevenHexagons.add(new Hexagon(6, "GRBYOY"));
		sevenHexagons.add(new Hexagon(7, "YGOBRR"));

		return sevenHexagons;
	}

	@Test
	public void Hexagon_instantiationTest() {

		// Arrange
		int id = 1;
		String color = "YBGOBR";

		// Act
		Hexagon h = new Hexagon(id, color);

		// Assert
		assertEquals(id, h.getID());
		assertEquals("Y", h.A);
		assertEquals("B", h.B);
		assertEquals("G", h.C);
		assertEquals("O", h.D);
		assertEquals("B", h.E);
		assertEquals("R", h.F);

	}

	@Test
	public void Hexagon_constructor_illegalArgumentTest() {

		// Arrange
		int id = 1;
		String color = "YTBGOBR";

		// Act
		try {
			@SuppressWarnings("unused")
			Hexagon h = new Hexagon(id, color);
			fail("Expected exception");
		} // Assert
		catch (IllegalArgumentException e) {
			assertEquals("Colors != 6", e.getMessage());
		}

	}

	@Test
	public void Hexagon_constructor_illegalColorTest() {

		// Arrange
		int id = 1;
		String color = "YBVOBR";

		// Act
		try {
			@SuppressWarnings("unused")
			Hexagon h = new Hexagon(id, color);
			fail("Expected exception");
		} // Assert
		catch (IllegalArgumentException e) {
			assertEquals("Color is not [R, B, Y, G, O, P]", e.getMessage());

		}

	}

	@Test
	public void Hexagon_getColorBySegmentIndexTest() {
		// Arrange
		int id = 1;
		String color = "YBPOBR";

		// Act
		Hexagon h = new Hexagon(id, color);

		// Assert
		assertEquals("Y", h.getColorBySegmentIndex(0));
		assertEquals("B", h.getColorBySegmentIndex(1));
		assertEquals("P", h.getColorBySegmentIndex(2));
		assertEquals("O", h.getColorBySegmentIndex(3));
		assertEquals("B", h.getColorBySegmentIndex(4));
		assertEquals("R", h.getColorBySegmentIndex(5));
		
		assertEquals("R", h.getColorBySegmentIndex(-1));
		assertEquals("B", h.getColorBySegmentIndex(-2));
		assertEquals("O", h.getColorBySegmentIndex(-3));
		assertEquals("P", h.getColorBySegmentIndex(-4));
		assertEquals("B", h.getColorBySegmentIndex(-5));

		assertEquals("Y", h.getColorBySegmentIndex(6));
		assertEquals("B", h.getColorBySegmentIndex(7));
		assertEquals("P", h.getColorBySegmentIndex(8));
		assertEquals("O", h.getColorBySegmentIndex(9));
		assertEquals("B", h.getColorBySegmentIndex(10));
		assertEquals("R", h.getColorBySegmentIndex(11));

		assertEquals("Y", h.getColorBySegmentIndex(-6));
		assertEquals("R", h.getColorBySegmentIndex(-7));
		assertEquals("B", h.getColorBySegmentIndex(-8));
		assertEquals("O", h.getColorBySegmentIndex(-9));
		assertEquals("P", h.getColorBySegmentIndex(-10));
		assertEquals("B", h.getColorBySegmentIndex(-11));
	}

	@Test
	public void HexagonPosition_constructorTest() {
		// Arrange
		int id = 1;
		int offset = 3;

		// Act
		HexagonPosition hPos = new HexagonPosition(id, offset);

		// Assert
		assertEquals(hPos.getHexagonId(), id);
		assertEquals(hPos.getClockwiseRotation(), offset);

	}

	@Test
	public void HexagonSolver_getHexagonByID() {
		// Arrange
		int id = 4;
		List<Hexagon> sevenHexagons = instantiateHexagonList();

		// Act
		Hexagon testVal = HexagonSolver.getHexagonByID(sevenHexagons, id);

		// Assert
		assertEquals(testVal, sevenHexagons.get(3));

	}

	@Test
	public void HexagonSolver_getNextHexagonRotation12am() {
		List<Hexagon> sevenHexagons = new ArrayList<>();

		sevenHexagons.add(new Hexagon(1, "YBGROG"));
		sevenHexagons.add(new Hexagon(2, "BRPRYO"));
		sevenHexagons.add(new Hexagon(3, "ROPGPG"));
		sevenHexagons.add(new Hexagon(4, "BBGBPG"));
		sevenHexagons.add(new Hexagon(5, "YPGORP"));
		sevenHexagons.add(new Hexagon(6, "GRBYOY"));
		sevenHexagons.add(new Hexagon(7, "YGOBRR"));

		List<HexagonPosition> hexagonPositions = new ArrayList<>();
		Hexagon centerHexagon = sevenHexagons.get(1);
		Hexagon nextHexagon = sevenHexagons.get(0);

		hexagonPositions.add(new HexagonPosition(centerHexagon.getID(), 0));

		// Act
		int nextHexagonColorSegment = 0;

		int nextHexagonRotation = HexagonSolver.getNextHexagonRotation(sevenHexagons, hexagonPositions, centerHexagon,
				nextHexagon, nextHexagonColorSegment);

		// Assert
		assertEquals(-1, nextHexagonRotation);

		// Act
		nextHexagonColorSegment = 1;

		nextHexagonRotation = HexagonSolver.getNextHexagonRotation(sevenHexagons, hexagonPositions, centerHexagon,
				nextHexagon, nextHexagonColorSegment);

		// Assert
		assertEquals(2, nextHexagonRotation);

	}

	@Test
	public void HexagonSolver_getNextHexagonRotation_AllCasesTest() {
		List<Hexagon> sevenHexagons = new ArrayList<>();

		sevenHexagons.add(new Hexagon(1, "YBGROG"));
		sevenHexagons.add(new Hexagon(2, "BRPRYO"));
		sevenHexagons.add(new Hexagon(3, "ROPGPG"));
		sevenHexagons.add(new Hexagon(4, "BBGBPG"));
		sevenHexagons.add(new Hexagon(5, "YPGORP"));
		sevenHexagons.add(new Hexagon(6, "GRBYOY"));
		sevenHexagons.add(new Hexagon(7, "YGOBRR"));

		List<HexagonPosition> hexagonPositions = new ArrayList<>();
		Hexagon centerHexagon = sevenHexagons.get(1);
		Hexagon nextHexagon = sevenHexagons.get(3);

		hexagonPositions.add(new HexagonPosition(centerHexagon.getID(), 0));

		// Act
		int nextHexagonColorSegment = 1;

		int nextHexagonRotation = HexagonSolver.getNextHexagonRotation(sevenHexagons, hexagonPositions, centerHexagon,
				nextHexagon, nextHexagonColorSegment);

		// Assert
		assertEquals(2, nextHexagonRotation);
		hexagonPositions.add(new HexagonPosition(nextHexagon.getID(), 2));

		// Act
		nextHexagon = sevenHexagons.get(5); // tests case 2 for
											// getNextHexagonRotation()
		nextHexagonColorSegment = 1;

		nextHexagonRotation = HexagonSolver.getNextHexagonRotation(sevenHexagons, hexagonPositions, centerHexagon,
				nextHexagon, nextHexagonColorSegment);

		// Assert
		assertEquals(3, nextHexagonRotation);
		hexagonPositions.add(new HexagonPosition(nextHexagon.getID(), 3));

		// Act
		nextHexagon = sevenHexagons.get(2); // tests case 3 for
											// getNextHexagonRotation()
		nextHexagonColorSegment = 2;

		nextHexagonRotation = HexagonSolver.getNextHexagonRotation(sevenHexagons, hexagonPositions, centerHexagon,
				nextHexagon, nextHexagonColorSegment);

		// Assert
		assertEquals(3, nextHexagonRotation);
		hexagonPositions.add(new HexagonPosition(nextHexagon.getID(), 3));

		// Act
		nextHexagon = sevenHexagons.get(0); // tests case 4 for
											// getNextHexagonRotation()
		nextHexagonColorSegment = 3;

		nextHexagonRotation = HexagonSolver.getNextHexagonRotation(sevenHexagons, hexagonPositions, centerHexagon,
				nextHexagon, nextHexagonColorSegment);

		// Assert
		assertEquals(3, nextHexagonRotation);
		hexagonPositions.add(new HexagonPosition(nextHexagon.getID(), 3));

		// Act
		nextHexagon = sevenHexagons.get(6); // tests case 5 for
											// getNextHexagonRotation()
		nextHexagonColorSegment = 0;

		nextHexagonRotation = HexagonSolver.getNextHexagonRotation(sevenHexagons, hexagonPositions, centerHexagon,
				nextHexagon, nextHexagonColorSegment);

		// Assert
		assertEquals(1, nextHexagonRotation);
		hexagonPositions.add(new HexagonPosition(nextHexagon.getID(), 1));

		// Act
		nextHexagon = sevenHexagons.get(4); // tests case 5 for
											// getNextHexagonRotation()
		nextHexagonColorSegment = 3;

		nextHexagonRotation = HexagonSolver.getNextHexagonRotation(sevenHexagons, hexagonPositions, centerHexagon,
				nextHexagon, nextHexagonColorSegment);

		// Assert
		assertEquals(5, nextHexagonRotation);

	}

	@Test
	public void HexagonSolver_getResultsRecursivelyTest() {
		// Arrange
		List<HexagonPosition> expectedList = new ArrayList<>();
		List<List<HexagonPosition>> expectedSolution = new ArrayList<>();

		expectedList.add(new HexagonPosition(2, 0));
		expectedList.add(new HexagonPosition(4, 2));
		expectedList.add(new HexagonPosition(6, 3));
		expectedList.add(new HexagonPosition(3, 3));
		expectedList.add(new HexagonPosition(1, 3));
		expectedList.add(new HexagonPosition(7, 1));
		expectedList.add(new HexagonPosition(5, 5));

		expectedSolution.add(expectedList); // creates expected Solution

		List<Hexagon> sevenHexagons = instantiateHexagonList();
		List<HexagonPosition> hexagonPositions = new ArrayList<>();

		Hexagon centerHexagon = sevenHexagons.get(1);

		hexagonPositions.add(new HexagonPosition(centerHexagon.getID(), 0));

		List<List<HexagonPosition>> solutionSet = new ArrayList<List<HexagonPosition>>();
		// Act
		solutionSet = HexagonSolver.getResultsRecursively(sevenHexagons, hexagonPositions);

		// Assert
		assertEquals(expectedSolution.toString(), solutionSet.toString());

	}

	@Test
	public void HexagonSolver_getAllResultsTest() {
		// Arrange
	
		List<Hexagon> sevenHexagons = new ArrayList<>();

		sevenHexagons.add(new Hexagon(1, "BGBPPP"));
		sevenHexagons.add(new Hexagon(2, "BRBPRP"));
		sevenHexagons.add(new Hexagon(3, "BOBPPP"));
		sevenHexagons.add(new Hexagon(4, "BGRPOP"));
		sevenHexagons.add(new Hexagon(5, "BBBPPP"));
		sevenHexagons.add(new Hexagon(6, "BBOPRP"));
		sevenHexagons.add(new Hexagon(7, "BBPROP"));

		// Act
		List<List<HexagonPosition>> solutionSet = new ArrayList<List<HexagonPosition>>();

		solutionSet = HexagonSolver.getAllResults(sevenHexagons);
		int solutionNumber = 1;
		for (List<HexagonPosition> solution : solutionSet) {
			System.out.println(solution.toString());
			solutionNumber++;
		}
		
		System.out.println("");
		System.out.println("Solutions found: " + solutionNumber);

	}
}
// assert that allSolutions has solution of size = 7
