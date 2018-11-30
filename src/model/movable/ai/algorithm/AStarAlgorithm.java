package model.movable.ai.algorithm;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class AStarAlgorithm{
	
	public List<Node> getPath(Node target){
		List<Node> path = new ArrayList<Node>();

		for(Node node = target; node!=null; node = node.parent){
			path.add(node);
		}

		Collections.reverse(path);

		return path;
	}
	
	public void search(Node source, Node goal) {
		Set<Node> explored = new HashSet<Node>();

		PriorityQueue<Node> queue = new PriorityQueue<Node>(20, 
				new Comparator<Node>(){
			//override compare method
			public int compare(Node i, Node j){
				if(i.f_scores > j.f_scores){
					return 1;
				}

				else if (i.f_scores < j.f_scores){
					return -1;
				}

				else{
					return 0;
				}
			}

		}
				);

		//cost from start
		source.g_scores = 0;

		queue.add(source);

		boolean found = false;

		while((!queue.isEmpty())&&(!found)){

			//the node in having the lowest f_score value
			Node current = queue.poll();

			explored.add(current);

			//goal found
			if(current.equalsTo(goal)){
				found = true;
			}

			//check every child of current node
			for(Edge e : current.adjacencies){
				Node child = e.target;
				double cost = e.cost;
				double temp_g_scores = current.g_scores + cost;
				double temp_f_scores = temp_g_scores;


				/*if child node has been evaluated and 
                                the newer f_score is higher, skip*/

				if((explored.contains(child)) && 
						(temp_f_scores >= child.f_scores)){
					continue;
				}

				/*else if child node is not in queue or 
                                newer f_score is lower*/

				else if((!queue.contains(child)) || 
						(temp_f_scores < child.f_scores)){
					child.parent = current;
					child.g_scores = temp_g_scores;
					child.f_scores = temp_f_scores;

					if(queue.contains(child)){
						queue.remove(child);
					}

					queue.add(child);

				}

			}

		}
	}

	public static class Node{
		public final int x;
		public final int y;
		public double g_scores;
		public double f_scores = 0;
		public ArrayList<Edge> adjacencies;
		public Node parent;

		public Node(int x, int y){
			this.x = x;
			this.y = y;
			adjacencies = new ArrayList<Edge>();
		}

		public int getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		
		public String toString(){
			return x+","+y;
		}
		
		public void addAdjacent(Node n, int cost) {
			if(n != null)
			adjacencies.add(new Edge(n, cost));
		}

		public boolean equalsTo(Node n) {
			return x == n.x && y == n.y;
		}
	}

	public static class Edge{
		private final double cost;
		private final Node target;

		public Edge(Node targetNode, double costVal){
			target = targetNode;
			cost = costVal;
		}
	}
}