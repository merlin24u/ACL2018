package model.movable.ai;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.movable.ai.algorithm.AStarAlgorithm.Node;
import model.movable.character.Character;
import model.movable.character.Pacman;
import model.movable.collision.GroundCollisionHandler;
import model.Map;
import model.movable.Movable;
import model.movable.ai.algorithm.AStarAlgorithm;
import model.movable.collision.CollisionUtility;

public class FollowMovableArtificialIntelligence extends MovableArtificialIntelligence {
	private Random random;
	private GroundCollisionHandler groundCollisionHandler;
	private ArrayList<AStarAlgorithm.Node> nodes;
	private ArrayList<Point> path;

	public void createNodes() {
		nodes.clear();
		AStarAlgorithm.Node[][] nodesArray = new AStarAlgorithm.Node[map.getHeigh()][map.getWidth()];
		for (int y = 0; y < map.getHeigh(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				ArrayList<AStarAlgorithm.Node> nodes = new ArrayList<AStarAlgorithm.Node>();
				if (groundCollisionHandler.canMoveOn(x, y)) {
					nodesArray[y][x] = new AStarAlgorithm.Node(x, y);
				}
			}
		}
		for (int y = 0; y < map.getHeigh(); y++) {
			for (int x = 0; x < map.getWidth(); x++) {
				Node n = nodesArray[y][x];
				if (n != null) {
					if (y - 1 >= 0)
						n.addAdjacent(nodesArray[y - 1][x], 1);
					if (x - 1 >= 0)
						n.addAdjacent(nodesArray[y][x - 1], 1);
					if (y + 1 < nodesArray.length)
						n.addAdjacent(nodesArray[y + 1][x], 1);
					if (x + 1 < nodesArray[0].length)
						n.addAdjacent(nodesArray[y][x + 1], 1);
					nodes.add(n);
				}
			}
		}
	}

	public Node getNode(int x, int y) {
		for (Node n : nodes) {
			if (n.getX() == x && n.getY() == y)
				return n;
		}
		return null;
	}

	public FollowMovableArtificialIntelligence(Map map, GroundCollisionHandler groundCollisionHandler) {
		super(map);
		this.groundCollisionHandler = groundCollisionHandler;
		this.nodes = new ArrayList<AStarAlgorithm.Node>();
		// TODO: Temporaire, � changer d'endroit
		this.createNodes();
	}

	private Character getNearestCharacter(Movable movable, String type) {
		ArrayList<Character> characters = map.getCharactersOfType(type);
		int nearestDistance = Integer.MAX_VALUE;
		Character nearestCharacter = null;
		for (Character c : characters) {
			int distance = CollisionUtility.getManhattanDistance(movable.getPosition(), c.getPosition());
			if (distance < nearestDistance) {
				nearestDistance = distance;
				nearestCharacter = c;
			}
		}
		return nearestCharacter;
	}

	public void execute(Movable movable) {
		Pacman player = (Pacman) getNearestCharacter(movable, "Player");
		if (player != null) {
			Node target = getNode(player.getPosition().x, player.getPosition().y);
			Node current = getNode(movable.getPosition().x, movable.getPosition().y);

			for (Node n : nodes) {
				n.parent = null;
			}
			AStarAlgorithm searchAlgo = new AStarAlgorithm();
			if (target != null && current != null) {
				searchAlgo.search(current, target);
				List<AStarAlgorithm.Node> path = searchAlgo.getPath(target);
				if (path.size() > 1) {
					AStarAlgorithm.Node nextPosition = path.get(1);
					movable.move(nextPosition.x, nextPosition.y);
				}
			}
		}
	}
}
