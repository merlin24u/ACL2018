package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.AStarAlgorithm.Node;

public class FollowMovableArtificialIntelligence extends
MovableArtificialIntelligence {
	private Random random;
	private GroundCollisionHandler groundCollisionHandler;
	private ArrayList<AStarAlgorithm.Node> nodes;
	private ArrayList<Point> path;

	public void createNodes() {
		Map map = groundCollisionHandler.getMap();
		nodes.clear();
		AStarAlgorithm.Node[][] nodesArray = new AStarAlgorithm.Node[map.getHeigh()][map.getWidth()];
		for(int y =0; y<map.getHeigh(); y++) {
			for(int x = 0; x<map.getWidth(); x++) {
				ArrayList<AStarAlgorithm.Node> nodes = new ArrayList<AStarAlgorithm.Node>();
				if(groundCollisionHandler.canMoveOn(x, y)) {
					nodesArray[y][x] = new AStarAlgorithm.Node(x, y);
				}
			}
		}
		for(int y =0; y<map.getHeigh(); y++) {
			for(int x = 0; x<map.getWidth(); x++) {
				Node n = nodesArray[y][x];
				if(n != null) {
					if(y-1 >= 0)
						n.addAdjacent(nodesArray[y-1][x], 1);
					if(x-1 >= 0)
						n.addAdjacent(nodesArray[y][x-1], 1);
					if(y+1 < nodesArray.length)
						n.addAdjacent(nodesArray[y+1][x], 1);
					if(x+1 < nodesArray[0].length)
						n.addAdjacent(nodesArray[y][x+1], 1);
					nodes.add(n);
				}
			}
		}
	}

	public Node getNode(int x, int y) {
		for(Node n: nodes) {
			if(n.getX() == x && n.getY() == y)
				return n;
		}
		return null;
	}

	public FollowMovableArtificialIntelligence(GroundCollisionHandler groundCollisionHandler) {
		this.groundCollisionHandler = groundCollisionHandler;
		this.nodes = new ArrayList<AStarAlgorithm.Node>();
		// TODO: Temporaire, à changer d'endroit
		this.createNodes();
	}

	public void execute(Movable movable) {
		Node target = getNode(1,1);
		Node current = getNode(movable.getPosition().x, movable.getPosition().y);

		for(Node n: nodes) {
			n.parent = null;
		}
		AStarAlgorithm searchAlgo = new AStarAlgorithm();
		if(target != null && current != null) {
			searchAlgo.search(current, target);
			List<AStarAlgorithm.Node> path = searchAlgo.getPath(target);
			if(path.size() > 1) {
				AStarAlgorithm.Node nextPosition = path.get(1);
				movable.move(nextPosition.x, nextPosition.y);
			}
		}
	}
}
