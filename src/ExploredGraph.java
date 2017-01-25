import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * 
 */

/**
 * @author your name(s) here.
 * Extra Credit Options Implemented, if any:  (mention them here.)
 * 
 * Solution to Assignment 5 in CSE 373, Autumn 2016
 * University of Washington.
 * 
 * (Based on starter code v1.3. By Steve Tanimoto.)
 *
 * Java version 8 or higher is recommended.
 *
 */

// Here is the main application class:
public class ExploredGraph {
	Set<Vertex> Ve; // collection of explored vertices
	Set<Edge> Ee;   // collection of explored edges
	Set<Vertex> open;
	HashMap<Vertex, Set<Vertex>> preceedings;
	Set<Vertex> temp;
	Stack<Vertex> stack;
	int nv;
	int ne;
	
	public ExploredGraph() {
		Ve = new LinkedHashSet<Vertex>();
		Ee = new LinkedHashSet<Edge>();
		open = new LinkedHashSet<Vertex>();
		temp = new LinkedHashSet<Vertex>();
		stack = new Stack();
		nv = Ve.size();
		ne = Ee.size();
	}

	public void initialize() {
		Ve = new LinkedHashSet<Vertex>();
		Ee = new LinkedHashSet<Edge>();
		nv = 0;
		ne = 0;
	}
	public int nvertices() {
		return nv;
	}
	public int nedges() {
		return ne;
	} 
	
	public void idfs(Vertex vi, Vertex vj) {
			int count = 0;
			preceedings = new HashMap<Vertex, Set<Vertex>>();
			Operator one = new Operator(0,1);
			Operator two = new Operator(0,2);
			Operator three = new Operator(1,2);
			Operator four = new Operator(1,0);
			Operator five = new Operator(2,0);
			Operator six = new Operator(2,1);
			preceedings.put(vi, null);
			stack.push(vi);
			open.add(vi);
			while (!Ve.contains(vj)) {
				Vertex first = (Vertex) stack.pop();
				Ve.add(first);
				Vertex insert = one.transition(first);
				if ((!open.contains(insert))&&(!Ve.contains(insert))&&(insert != null)){stack.push(insert);open.add(insert);}
				insert = two.transition(first);
				if ((!open.contains(insert))&&(!Ve.contains(insert))&&(insert != null)){stack.push(insert);open.add(insert);}
				insert = three.transition(first);
				if ((!open.contains(insert))&&(!Ve.contains(insert))&&(insert != null)){stack.push(insert);open.add(insert);}
				insert = four.transition(first);
				if ((!open.contains(insert))&&(!Ve.contains(insert))&&(insert != null)){stack.push(insert);open.add(insert);}
				insert = five.transition(first);
				if ((!open.contains(insert))&&(!Ve.contains(insert))&&(insert != null)){stack.push(insert);open.add(insert);}
				insert = six.transition(first);
				if ((!open.contains(insert))&&(!Ve.contains(insert))&&(insert != null)){stack.push(insert);open.add(insert);}	
				count+=1;
				System.out.println(count);
				System.out.println(stack);
				
				for (Vertex previous:stack) {
						if (preceedings.containsKey(previous)){
							preceedings.get(previous).add(first);
						} else {
							preceedings.put(previous, new LinkedHashSet<>());
							preceedings.get(previous).add(first);
						}
				}
			}
			//System.out.println(stack);
			
			Iterator<Vertex> itr = Ve.iterator();
			while(itr.hasNext()){
				System.out.println((itr.next().toString()));  
	        }
	} // Implement this. (Iterative Depth-First Search)

	public void bfs(Vertex vi, Vertex vj) {   // Implement this. (Breadth-First Search)
		System.out.println("Iterative Breadth-First Search .........................");
		int count = -1;
		Operator one = new Operator(0,1);
		Operator two = new Operator(0,2);
		Operator three = new Operator(1,2);
		Operator four = new Operator(1,0);
		Operator five = new Operator(2,0);
		Operator six = new Operator(2,1);
		
		preceedings = new HashMap<Vertex, Set<Vertex>>();
		preceedings.put(vi, null);
		Ve.clear();
		Ve.add(vi);
		open.clear();
		open.add(vi);
		temp.clear();
		Iterator<Vertex> iterO = Ve.iterator();// 1st item in helper
		Vertex first = (Vertex) iterO.next();
		//System.out.println("open = " + open);
		while (!open.isEmpty()) {
			count++;
			//System.out.println(first + " " + "count = " + count);
			Vertex tmp;
			tmp = (one.transition(first));
			if (tmp != null) {
				Ve.add(tmp);
			}
			tmp = two.transition(first);
			if (tmp != null) {
				Ve.add(tmp);
			}
			tmp = three.transition(first);
			if (tmp != null) {
				Ve.add(tmp);
			}
			tmp = four.transition(first);
			if (tmp != null) {
				Ve.add(tmp);
			}
			tmp = five.transition(first);
			if (tmp != null) {
				Ve.add(tmp);
			}
			tmp = six.transition(first);
			if (tmp != null) {
				Ve.add(tmp);
			}
			Iterator<Vertex> copy = Ve.iterator();
			while(copy.hasNext()) {
				open.add(copy.next());
			}
			
			Iterator<Vertex> del = Ve.iterator();
			for (int k = 0; k <= count; k++) {
				temp.add(del.next());
			}
			open.removeAll(temp);
			for (Vertex previous:open) {
				if (preceedings.containsKey(previous)){
					preceedings.get(previous).add(first);
				} else {
					preceedings.put(previous, new LinkedHashSet<>());
					preceedings.get(previous).add(first);
				}
			}
			//System.out.println("open = " + open);
			Iterator<Vertex> CurI = Ve.iterator();
			for (int j = 0; j <= count + 1; j++) {	 
				if (CurI.hasNext()) {
					first = CurI.next();
				}
			}
			if (first.equals(vj)) {
				return;
			}
		}
			
	} 

	
	public ArrayList<Vertex> retrievePath(Vertex vi) {
		ArrayList<Vertex> path = new ArrayList<>();		
		Iterator<Vertex> it = Ve.iterator();
		Vertex root = it.next();
		if (this.preceedings.get(vi) != null) {
			Vertex previous = this.preceedings.get(vi).iterator().next();
			path.add(vi);
			path.addAll(this.retrievePath(previous));
			if(previous == root){
					path.add(root);
					return path;
			}
		} 
		return path;
	} // Implement this.
	
	public ArrayList<Vertex> shortestPath(Vertex vi, Vertex vj) {
		this.bfs(vi, vj);
		return this.retrievePath(vj);
	} // Implement this.
	
	public Set<Vertex> getVertices() {
		return Ve;
	} 
	public Set<Edge> getEdges() {
		return Ee;
	} 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExploredGraph eg = new ExploredGraph();
		// Test the vertex constructor: 
		Vertex v0 = eg.new Vertex ("[[4,3,2,1],[],[]]");
		Vertex v1 = eg.new Vertex ("[[],[],[4,3,2,1]]");
		
		//eg.bfs(v0,v1);
		eg.idfs(v0, v1);
		/*for (Vertex v : eg.preceedings.keySet()) {
			System.out.print(v);
			System.out.print("\t\t\t");
			System.out.print(eg.preceedings.get(v));
			System.out.print("\n");
		}*/
		//ArrayList<Vertex> path = eg.shortestPath(v0, v1);
		ArrayList<Vertex> path = eg.retrievePath(v1);
		System.out.println(path);
		/*eg.idfs(v0, v1);
		for (Vertex v : eg.preceedings.keySet()) {
		System.out.print(v);
		System.out.print("\t\t\t");
		System.out.print(eg.preceedings.get(v));
		System.out.print("\n");*/
		// Add your own tests h1ere.
		// The autograder code will be used to test your basic functionality later.

	}
	
	class Vertex {
		final String Vertex = null;
		ArrayList<Stack<Integer>> pegs; // Each vertex will hold a Towers-of-Hanoi state.
		int n;
		// There will be 3 pegs in the standard version, but more if you do extra credit option A5E1.
		
		// Constructor that takes a string such as "[[4,3,2,1],[],[]]":
		public Vertex(String vString) {
			String[] parts = vString.split("\\],\\[");
			pegs = new ArrayList<Stack<Integer>>(3);
			for (int i=0; i<3;i++) {
				pegs.add(new Stack<Integer>());
				try {
					parts[i]=parts[i].replaceAll("\\[","");
					parts[i]=parts[i].replaceAll("\\]","");
					List<String> al = new ArrayList<String>(Arrays.asList(parts[i].split(",")));
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++		System.out.println("ArrayList al is: "+al);
					Iterator<String> it = al.iterator();
					while (it.hasNext()) {
						String item = it.next();
                        if (!item.equals("")) {
          //**************************************************************************                      System.out.println("item is: "+item);
                                pegs.get(i).push(Integer.parseInt(item));
                        }
					}
				}
				catch(NumberFormatException nfe) { nfe.printStackTrace(); }
			}		
		}
		
		public String toString() {
			String ans = "[";
			for (int i=0; i<3; i++) {
			    ans += pegs.get(i).toString().replace(" ", "");
				if (i<2) { ans += ","; }
			}
			ans += "]";
			return ans;
		}
		
		@Override
		 public boolean equals(Object o){
			if (((Vertex) o).toString().equals(this.toString())) {
				return true;
			} else {
				return false;
			}
		}
		
		@Override
		public int hashCode() {
			int newCode = 0;
			Stack one = this.pegs.get(0);
			Stack two = this.pegs.get(1);
			Stack three = this.pegs.get(2);
			for (int i = 0; i < one.size(); i++) {
				newCode += (int) one.peek() * 3;
				one.push(one.pop());
			}
			for (int i = 0; i < two.size(); i++) {
				newCode += (int) two.peek() * 13;
				two.push(two.pop());
			}
			for (int i = 0; i < three.size(); i++) {
				newCode += (int) three.peek() * 23;
				three.push(three.pop());
			}
			return newCode;
		}
		
	}
	
	class Edge {
		ArrayList<Vertex> edge;
		
		public Edge(Vertex vi, Vertex vj) {
			// Add whatever you need to here.
			edge = new ArrayList<Vertex>(2);
			edge.add(vi);
			edge.add(vj);
		}
		
		//"Edge from [[4,3,2,1],[],[]] to [[4,3,2],[1],[]]"
		public String toString() {
			return "Edge from " + edge.get(0).toString() + " to " + edge.get(1).toString();
		}
		
		public Vertex getEndPoint1(ArrayList<Vertex> edge){
			return edge.get(0);
		}
		
		public Vertex getEndPoint2(ArrayList<Vertex> edge){
			return edge.get(1);
		}
	}
	
	class Operator {
		private int i, j;

		public Operator(int i, int j) { // Constructor for operators.
			this.i = i;
			this.j = j;
		}
		
		// Check if the it can move from peg i to peg j
		public boolean precondition(Vertex v) {
			if (v.pegs.get(j).isEmpty()) {
				if (!v.pegs.get(i).isEmpty()){
					return true;
				} else {
					return false;
				}
			} else {
				if (!v.pegs.get(i).isEmpty()) {
					if (v.pegs.get(i).peek() < v.pegs.get(j).peek()) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		}
		
		
		public Vertex transition(Vertex v) {
			Vertex temp = null;
			if (precondition(v) == true) {
				String s = v.toString();
				temp = new Vertex(s);
				int topN = temp.pegs.get(i).pop();
				temp.pegs.get(j).push(topN);
			}
			return temp;
		}

		public String toString() {
			return "[Operator that tries to move a disk from peg " + i + " to peg + " + j + "]";
		}
	}
}
