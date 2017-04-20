package hexagon;

import java.util.ArrayList;
import java.util.List;

public class HexagonSolver {

	public static List<List<HexagonPosition>> getAllResults(List<Hexagon> sevenHexagons)
			throws IllegalArgumentException {
		if (sevenHexagons.size() != 7) {
			throw new IllegalArgumentException("Hexagon List != 7");
		}

		List<List<HexagonPosition>> allSolutions = new ArrayList<List<HexagonPosition>>();

		for (Hexagon centerHexagon : sevenHexagons) {// try each Hexagon
			List<HexagonPosition> hexagonPositions = new ArrayList<>();
			hexagonPositions.add(new HexagonPosition(centerHexagon.getID(), 0));
			List<List<HexagonPosition>> centerHexagonSolutions = getResultsRecursively(sevenHexagons, hexagonPositions);

			for (List<HexagonPosition> solution : centerHexagonSolutions) {
				allSolutions.add(solution);
			}
		}
		return allSolutions;
	}

	public static List<List<HexagonPosition>> getResultsRecursively(List<Hexagon> sevenHexagons,
			List<HexagonPosition> hexagonPositions) {
		// Hexagon position index 0 = center, 1 = 12am, 2 = 2am, 3 = 4am, 4 =
		// 6am, 5 = 8am, 6 = 10am;
		Hexagon centerHexagon = getHexagonByID(sevenHexagons, hexagonPositions.get(0).getHexagonId());

		List<List<HexagonPosition>> allSolutions = new ArrayList<List<HexagonPosition>>();

		for (Hexagon nextHexagon : sevenHexagons) {
			if (containsHexagonId(nextHexagon, hexagonPositions)) {
				continue;
			}

			for (int nextHexagonColorSegment = 0; nextHexagonColorSegment < 6; nextHexagonColorSegment++) {
				int nextHexagonRotation = getNextHexagonRotation(sevenHexagons, hexagonPositions, centerHexagon,
						nextHexagon, nextHexagonColorSegment);
				if (nextHexagonRotation > -1) {

					List<HexagonPosition> nextHexagonPositions = new ArrayList<HexagonPosition>();
					nextHexagonPositions.addAll(hexagonPositions);
					nextHexagonPositions.add(new HexagonPosition(nextHexagon.getID(), nextHexagonRotation));

					if (nextHexagonPositions.size() == 7) {
						allSolutions.add(nextHexagonPositions);
					} else {
						List<List<HexagonPosition>> nestedSolutions = getResultsRecursively(sevenHexagons,
								nextHexagonPositions);
						for (List<HexagonPosition> nestedSolution : nestedSolutions) {
							allSolutions.add(nestedSolution);
						}
					}

				}

			}

		}

		return allSolutions;

	}

	public static int getNextHexagonRotation(List<Hexagon> sevenHexagons, List<HexagonPosition> hexagonPositions,
			Hexagon centerHexagon, Hexagon nextHexagon, int nextHexagonColorSegment) {
		String nextHexagonColorSegmentVal = nextHexagon.getColorBySegmentIndex(nextHexagonColorSegment);
		String nextHexagonColorSegmentPlus1 = nextHexagon.getColorBySegmentIndex(nextHexagonColorSegment + 1);

		if (hexagonPositions.size() > 1) {
			HexagonPosition previousHexagonPosition = hexagonPositions.get(hexagonPositions.size() - 1);

			Hexagon previousHexagon = getHexagonByID(sevenHexagons, previousHexagonPosition.getHexagonId());

			switch (hexagonPositions.size()) {

			case 2:// nextHexagon is @ 2 o'clock
				String previousHexagonSegmentColorToMatch = previousHexagon
						.getColorBySegmentIndex(2 - previousHexagonPosition.getClockwiseRotation());
				if (centerHexagon.B.equals(nextHexagonColorSegmentVal)
						&& previousHexagonSegmentColorToMatch.equals(nextHexagonColorSegmentPlus1)) {
					return (4 - nextHexagonColorSegment) % 6;
				}
				break;
			case 3:// nextHexagon is @4 o'clock
				previousHexagonSegmentColorToMatch = previousHexagon
						.getColorBySegmentIndex(3 - previousHexagonPosition.getClockwiseRotation());
				if (centerHexagon.C.equals(nextHexagonColorSegmentVal)
						&& previousHexagonSegmentColorToMatch.equals(nextHexagonColorSegmentPlus1)) {
					return (5 - nextHexagonColorSegment) % 6;
				}
				break;

			case 4:// nextHexagon is @6 o'clock
				previousHexagonSegmentColorToMatch = previousHexagon
						.getColorBySegmentIndex(4 - previousHexagonPosition.getClockwiseRotation());
				if (centerHexagon.D.equals(nextHexagonColorSegmentVal)
						&& previousHexagonSegmentColorToMatch.equals(nextHexagonColorSegmentPlus1)) {
					return (6 - nextHexagonColorSegment) % 6;
				}
				break;
			case 5:// nextHexagon is @8 o'clock
				previousHexagonSegmentColorToMatch = previousHexagon
						.getColorBySegmentIndex(5 - previousHexagonPosition.getClockwiseRotation());
				if (centerHexagon.E.equals(nextHexagonColorSegmentVal)
						&& previousHexagonSegmentColorToMatch.equals(nextHexagonColorSegmentPlus1)) {
					return (7 - nextHexagonColorSegment) % 6;
				}
				break;
			case 6:// nextHexagon is @10 o'clock
				HexagonPosition firstHexagonPosition = hexagonPositions.get(1);
				Hexagon firstHexagon = getHexagonByID(sevenHexagons, firstHexagonPosition.getHexagonId());
				String firstHexagonSegmentColorToMatch = firstHexagon
						.getColorBySegmentIndex(4 - firstHexagonPosition.getClockwiseRotation());

				previousHexagonSegmentColorToMatch = previousHexagon
						.getColorBySegmentIndex(6 - previousHexagonPosition.getClockwiseRotation());

				if (centerHexagon.F.equals(nextHexagonColorSegmentVal)
						&& previousHexagonSegmentColorToMatch.equals(nextHexagonColorSegmentPlus1)
						&& firstHexagonSegmentColorToMatch
								.equals(nextHexagon.getColorBySegmentIndex(nextHexagonColorSegment - 1))) {
					return (8 - nextHexagonColorSegment) % 6;
				}
				break;
			}
		} else {
			// nextHexagon is @12 o'clock
			if (centerHexagon.A.equals(nextHexagonColorSegmentVal)) {
				return 3 - nextHexagonColorSegment;
			}
		}
		return -1; // no solution found
	}

	public static boolean containsHexagonId(Hexagon hex, List<HexagonPosition> hexagonPositions) {
		for (HexagonPosition hexagonPosition : hexagonPositions) {
			if (hexagonPosition.getHexagonId() == hex.getID()) {
				return true;
			}
		}
		return false;
	}

	public static Hexagon getHexagonByID(List<Hexagon> sevenHexagons, int id) throws IndexOutOfBoundsException {
		for (Hexagon hexagon : sevenHexagons) {
			if (hexagon.getID() == id) {
				return hexagon;
			}
		}
		throw new IndexOutOfBoundsException("Out Of Bounds: Choose id between [1-7]");
	}

}
