/*
 * @(#)DiGraph.java
 */

import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.*;

import ds.util.*;

class Edge
{
	// index of the destination vertex in the ArrayList
	// vInfo of vertex properties
	public int dest;
	// weight of this edge
	public int weight;

	public Edge(int dest, int weight)
	{
		this.dest = dest;
		this.weight = weight;
	}

	public boolean equals(Object obj)
	{
		if (!(obj instanceof Edge))
			return false;

		return ((Edge)obj).dest == this.dest;
	}
}

// maintains vertex properties, including its set of Edges
class VertexInfo<T>
{
	// vertex reference back to the vertex in the map
	public T vertex;

	// list of Edge objects (adjacent vertices) for the
	// current vertex
	public LinkedList<Edge> edgeList;

	// maintains the in-degree of the vertex
	public int inDegree;

	// indicates whether the object currently represents a vertex
	public boolean occupied;

	// indicates vertex color for use in algorithms
	// that traverse the vertices of a Graph
	public VertexColor color;

	// available to algorithms for storing relevant
	// data values
	public int dataValue;

	// available to Graph algorithms; holds parent which is
	// a vertex that has an edge terminating in the current
	// vertex
	public T parent;

	// constructor creates an object with initial values for
	// the vertex, edgeList, inDegree, and occupied fields.
	public VertexInfo(T v)
	{
		vertex = v;
		edgeList = new LinkedList<Edge>();
		inDegree = 0;
		occupied = true;
	}
}

/**
 * An instance of this class is a directed weight graph that implements
 * the <tt>Graph</tt> interface.
 */

public class DiGraph<T> implements Graph<T>
{
	// store vertex in a map with its name as the key and the index
	// of the corresponding VertexInfo object in the vInfo
	// ArrayList as the value
	private HashMap<T, Integer> vtxMap;
	private ArrayList<VertexInfo<T>> vInfo;

	// availability stack
	private ALStack<Integer> availStack;

	// number of edges in the Graph
	private int numEdges;

	// takes vertex v in the map and returns the index of the
	// corresponding vInfo element or -1 if v is not a vertex
	private int getVInfoIndex(Object v)
	{
		// get the Integer value field of the vtxMap entry
		Integer indexObj = vtxMap.get(v);

		// if value is null, there is not entry in the map; return
		// -1; otherwise, convert object to an int
		if (indexObj == null)
			return -1;
		else
			return indexObj;
	}

	// search a LinkedList of Edge objects for an edge
	// having index dest in vInfo
	private Edge findEdge(LinkedList<Edge> edgeList, int dest)
	{
		Iterator<Edge> iter = edgeList.iterator();

		while (iter.hasNext())
		{
			Edge e = iter.next();
			if (e.dest == dest)
				return e;
		}

		return null;
	}

	// call when deleting a vertex v. remove all
	// edges that terminate at v, and update the
	// in-degree of each neighbor of v
	private void removeFixup(int index)
	{
		// iterator used to scan Edge objects in adjacency lists
		Iterator<Edge> iter = null;
		Edge e = null;
		VertexInfo<T> vtxInfo = vInfo.get(index), edgeVtxInfo;

		vtxInfo.occupied = false;
		availStack.push(index);

		// remove all the edges that terminate at the vertex being
		// removed. use a loop to check all of the VertexInfo
		// elements in vInfo for which occupied is true; these
		// correspond to actual vertices in the map
		for (int i = 0; i < vInfo.size(); i++)
		{
			// get the VertexInfo object for index i
			edgeVtxInfo = vInfo.get(i);

			// check if vertex is valid
			if (edgeVtxInfo.occupied)
			{
				// obtain an iterator to scan the adjacency list
				iter = edgeVtxInfo.edgeList.iterator();

				while (iter.hasNext())
				{
					// get the Edge object and check if the dest field
					// has value index which identifies vertex v; if so
					// remove it and decrement numEdges
					e = iter.next();
					if (e.dest == index)
					{
						iter.remove();
						numEdges--;
						break;
					}
				}
			}
		}

		// reduce numEdges by number of elements in adjacency list
		numEdges -= vtxInfo.edgeList.size();

		// scan the adjacency list for vertex v and decrement the in-degree
		// for each adjacent vertex
		iter = vtxInfo.edgeList.iterator();
		while (iter.hasNext())
		{
			e = iter.next();
			edgeVtxInfo = vInfo.get(e.dest);
			iter.remove();
			edgeVtxInfo.inDegree--;
		}
	}

	// create an empty graph.
	public DiGraph()
	{
		vtxMap = new HashMap<T, Integer>();
		availStack = new ALStack<Integer>();
		vInfo = new ArrayList<VertexInfo<T>>();
		numEdges = 0;
	}

	// returns the number of vertices in the graph
	public int numberOfVertices()
	{ return vtxMap.size(); }

	// returns the number of edges in the graph
	public int numberOfEdges()
	{ return numEdges; }

	// returns true if the graph has no vertices or edges and
	// false otherwise
	public boolean isEmpty()
	{ return vtxMap.size() == 0; }

	// returns the weight of the edge connecting vertex v1 to v2.
	// if the edge (v1,v2) does not exist, return -1. if v1 or v2
	// is not a vertex in the graph, throws
	// IllegalArgumentException
	public int getWeight(T v1, T v2)
	{
		// find the indices for the VertexInfo objects
		int vInfoIndex1 = getVInfoIndex(v1),
		    vInfoIndex2 = getVInfoIndex(v2);

		// check for an error and throw exception
		if (vInfoIndex1 == -1 || vInfoIndex2 == -1)
			throw new IllegalArgumentException(
				"DiGraph getWeight(): vertex not in graph");

		// find the edge corresponding to the destination index
		Edge e = findEdge(vInfo.get(vInfoIndex1).edgeList,
								vInfoIndex2);
		if (e == null)
			return -1;
		else
			return e.weight;
	}

	// if edge (v1, v2) is in the graph, update the weight of
	// the edge and return the previous weight; otherwise, return
	// -1. if v1 or v2 is not a vertex in the graph, throws
	// IllegalArgumentException
	public int setWeight(T v1, T v2, int w)
	{
		// find the indices for the VertexInfo objects
		int vInfoIndex1 = getVInfoIndex(v1),
		    vInfoIndex2 = getVInfoIndex(v2);

		// check for an error and throw exception
		if (vInfoIndex1 == -1 || vInfoIndex2 == -1)
			throw new IllegalArgumentException(
				"DiGraph setWeight(): vertex not in graph");

		// find the edge corresponding to the destination index
		Edge e = findEdge(vInfo.get(vInfoIndex1).edgeList, vInfoIndex2);

		int returnVal = -1;

		if (e != null)
		{
			returnVal = e.weight;
			e.weight = w;
		}

		return returnVal;
	}

	// return the in-degree of vertex v. if v is not a graph
	// vertex, throws IllegalArgumentException
	public int inDegree(T v)
	{
		// find the VertexInfo object for index v
		int vInfoIndex = getVInfoIndex(v);

		// check for an error and throw exception if vertices not in graph
		if (vInfoIndex == -1)
			throw new IllegalArgumentException(
				"DiGraph inDegree(): vertex not in graph");

		// in-degree is stored in vInfo
		return vInfo.get(vInfoIndex).inDegree;
	}

	// return the out-degree of vertex v. if v is not a graph
	// vertex, throws IllegalArgumentException
	public int outDegree(T v)
	{
		// find the VertexInfo object for index v
		int vInfoIndex = getVInfoIndex(v);

		// check for an error and throw exception if vertices not in graph
		if (vInfoIndex == -1)
			throw new IllegalArgumentException(
				"DiGraph outDegree(): vertex not in graph");

		// out-degree is the number of edges
		return vInfo.get(vInfoIndex).edgeList.size();
	}

	// returns the vertices that are adjacent to vertex v in a
	// Set object. if v is not a graph vertex,
	// throws IllegalArgumentException
	public Set<T> getNeighbors(T v)
	{
		// find the VertexInfo object for index v
		int index = getVInfoIndex(v);

		// check for an error and throw exception if vertices not in graph
		if (index == -1)
			throw new IllegalArgumentException(
					"DiGraph getNeighbors(): vertex not in graph");

		// create HashSet object to hold vertices, obtain the VertexInfo
		// object, and an initialize an iterator to scan the adjacency
		// list of the VertexInfo object
		HashSet<T> edgeSet = new HashSet<T>();
		VertexInfo<T> vtxInfo = vInfo.get(index);
		Iterator<Edge> iter = vtxInfo.edgeList.iterator();
		Edge e = null;

		while (iter.hasNext())
		{
			e = iter.next();
			edgeSet.add(vInfo.get(e.dest).vertex);
		}

		return edgeSet;
	}

	// if the edge (v1, v2) is not in the graph, adds the edge
	// with weight w and returns true. returns false if the edge
	// is already in the graph. if v1 or v2 is not a vertex in
	// the graph, throws IllegalArgumentException
	public boolean addEdge(T v1, T v2, int w)
	{
		// find the indices for the VertexInfo objects
		int pos1 = getVInfoIndex(v1),
		    pos2 = getVInfoIndex(v2);

		// check for an error and throw exception if vertices not in graph
		if (pos1 == -1 || pos2 == -1)
			throw new IllegalArgumentException(
				"DiGraph addEdge(): vertex not in graph");

		// throw exception if a self-edge
		if (pos1 == pos2)
			throw new IllegalArgumentException(
				"DiGraph addEdge(): self-edges not allowed");

		// get VertexInfo objects for vertices v1 and v2
		VertexInfo<T> vtxInfo1 = vInfo.get(pos1),
						  vtxInfo2 = vInfo.get(pos2);

		Edge e = new Edge(pos2, w);

		boolean returnValue = true;

		// try to add an Edge reference v1-v2.
		// if it already exists, just return
		if (!vtxInfo1.edgeList.contains(e))
		{
			vtxInfo1.edgeList.add(e);
			// increment inDegree for vertex v2 and number of edges
			vtxInfo2.inDegree++;
			numEdges++;
		}
		else
			returnValue = false;

		return returnValue;
	}

	// if v is not in the graph, adds it to the graph and returns
	// true; otherwise, returns false
	public boolean addVertex(T v)
	{
		int index;

		if (vtxMap.containsKey(v))
			return false;

		// see if there is an entry in vInfo freed by an earlier
		// call to removeVertex()
		if (!availStack.isEmpty())
		{
			// yes. get its index
			index = availStack.pop();
			// update the vInfo element in the ArrayList
			VertexInfo<T> vtxInfo = vInfo.get(index);
			vtxInfo.vertex = v;
			vtxInfo.edgeList.clear();
			vtxInfo.occupied = true;
			vtxInfo.inDegree = 0;
		}
		else
		{
			// no. we'll have to increase the size of vInfo
			index = vInfo.size();
			vInfo.add(new VertexInfo<T>(v));
		}

		vtxMap.put(v, index);

		return true;
	}

	// if (v1,v2) is an edge, removes the edge and returns true;
	// otherwise, returns false. if v1 or v2 is not a vertex in
	// the graph, throws IllegalArgumentException
	public boolean removeEdge(T v1, T v2)
	{
		// find the indices for the VertexInfo objects
		int vInfoIndex1 = getVInfoIndex(v1),
		    vInfoIndex2 = getVInfoIndex(v2);

		// check for an error and throw exception if vertices not in graph
		if (vInfoIndex1 == -1 || vInfoIndex2 == -1)
			throw new IllegalArgumentException(
				"DiGraph removeEdge(): vertex not in graph");

		Iterator<Edge> iter =
				vInfo.get(vInfoIndex1).edgeList.iterator();
		Edge e;
		boolean removedTheEdge = false;
		while (iter.hasNext())
		{
			e = iter.next();
			if (e.dest == vInfoIndex2)
			{
				iter.remove();
				removedTheEdge = true;
				break;
			}
		}

		if (!removedTheEdge)
			return false;
		else
		{
			vInfo.get(vInfoIndex2).inDegree--;
			numEdges--;
		}

		return true;
	}

	// if v is a vertex in the graph, removes it from the graph
	// and returns true; otherwise, returns false
   public boolean removeVertex(Object v)
	{
		// find the index for the VertexInfo object in vInfo
		int index = getVInfoIndex(v);

		if (index == -1)
			return false;

		vtxMap.remove(v);

		// fixup vInfo corresponding to the removal of vertex v
		removeFixup(index);

		return true;
	}

	// removes all of the vertices and edges from the graph
	public void clear()
	{
		vtxMap.clear();
		availStack.clear();
		vInfo.clear();

		// set the number of vertices and edges to 0
		numEdges = 0;
	}

	// builds a graph whose vertices are strings by reading the
	// vertices and edges from the textfile filename. the format
	// of the file is
	//     nVertices
	//     vertex_1 vertex_2 ...
	//           ...
	//     ... vertex_nVertices
	//     nEdges
	//     vertex vertex weight_1
	//             ...
	//     vertex vertex weight_nEdges
	public static DiGraph<String> readGraph(String filename)
		throws FileNotFoundException
	{
		// nVertices is number of vertices to read
		int i, nVertices, nEdges;
		// use for input of vertices (v1) and edges ( {v1, v2} )
		String v1, v2;
		// edge weight
		int weight;
		DiGraph<String> g = new DiGraph<String>();

		Scanner graphIn =
			new Scanner(new FileReader(filename));

		// input the number of vertices
		nVertices = graphIn.nextInt();

		// input the vertices and add each into the graph
		for (i = 0; i < nVertices; i++)
		{
			v1 = graphIn.next();
			g.addVertex(v1);
		}

		// input the number of edges
		nEdges = graphIn.nextInt();

		// input the vertices and weight for each edge, and
		// add it into the graph
		for (i = 0; i < nEdges; i++)
		{
			v1 = graphIn.next();
			v2 = graphIn.next();
			weight = graphIn.nextInt();
			g.addEdge(v1,v2,weight);
		}

		return g;
	}

	private Set<T> graphVertexSet = null;

	// returns a set-view of the vertices in the graph
	public Set<T> vertexSet()
	{
		if (graphVertexSet == null)
			graphVertexSet = new Set<T>()
			{
				public int size()
				{
					return vtxMap.size();
				}

				public boolean isEmpty()
				{
					return vtxMap.isEmpty();
				}

				public boolean contains(Object item)
				{
					return vtxMap.containsKey(item);
				}

				public Iterator<T> iterator()
				{
					return new IteratorImpl();
				}

				public boolean add(T item)
				{
      			throw new UnsupportedOperationException();
				}

				public boolean remove(Object item)
				{
					boolean retValue = false;

					if (vtxMap.containsKey(item))
					{
						removeVertex(item);
						retValue = true;
					}

					return retValue;
				}

				public void clear()
				{
					DiGraph.this.clear();
				}

				public Object[] toArray()
				{
					Object[] result = new Object[size()];
					int i = 0;
					Iterator<T> iter = iterator();

					while (iter.hasNext())
					{
						result[i] = iter.next();
						i++;
					}

					return result;
    			}

			};

			return graphVertexSet;
	}

	// returns true if v is a vertex in the graph
	public boolean containsVertex(Object v)
	{
		return vtxMap.containsKey(v);
	}

	// returns true if there is an edge from v1 to v2 and returns
	// false otherwise. if v1 or v2 is not a vertex in the graph,
	// throws IllegalArgumentException
	public boolean containsEdge(T v1, T v2)
	{
		// find the indices for the VertexInfo objects
		int vInfoIndex1 = getVInfoIndex(v1),
		    vInfoIndex2 = getVInfoIndex(v2);

		// check for an error and throw exception
		if (vInfoIndex1 == -1 || vInfoIndex2 == -1)
			throw new IllegalArgumentException(
				"DiGraph containsEdge(): vertex not in graph");

		VertexInfo<T> vtxInfo = vInfo.get(vInfoIndex1);

		Iterator<Edge> iter = vtxInfo.edgeList.iterator();
		Edge e;

		while (iter.hasNext())
		{
			e = iter.next();
			if (e.dest == vInfoIndex2)
				return true;
		}
		return false;
	}

	// returns the color of vertex v. if v is not a graph vertex,
	// throws IllegalArgumentException. for use by graph
	// algorithms
	public VertexColor getColor(T v)
	{
		// find the vInfo index for v
		int pos = getVInfoIndex(v);

		if (pos != -1)
			return vInfo.get(pos).color;
		else
			// throw an exception
			throw new IllegalArgumentException(
				"DiGraph getColor(): vertex not in graph");
	}

	// sets the color of vertex v and returns the previous color.
	// if v is not a graph vertex,
	// throws IllegalArgumentException. for use by graph
	// algorithms
	public VertexColor setColor(T v, VertexColor c)
	{
		// find the vInfo index for v
		int pos = getVInfoIndex(v);
		VertexColor oldColor = null;
		VertexInfo<T> vtxInfo;

		if (pos != -1)
		{
			vtxInfo = vInfo.get(pos);
			oldColor = vtxInfo.color;
			vtxInfo.color = c;
		}
		else
			// throw an exception
			throw new IllegalArgumentException(
				"DiGraph setColor(): vertex not in graph");

		return oldColor;
	}

	// sets the color of each vertex to VertexColor.WHITE. for use
	// by graph algorithms
	public void colorWhite()
	{
		VertexInfo<T> vtxInfo;

		for (int i = 0; i < vInfo.size(); i++)
		{
			vtxInfo = vInfo.get(i);

			if (vtxInfo.occupied)
				vtxInfo.color = VertexColor.WHITE;
		}
	}

	// returns the parent of vertex v. if v is not a graph vertex,
	// throws IllegalArgumentException. for use by graph
	// algorithms
	public T getParent(T v)
	{
		// find the vInfo index for v
		int pos = getVInfoIndex(v);

		if (pos != -1)
			return vInfo.get(pos).parent;
		else
			// throw an exception
			throw new IllegalArgumentException(
				"DiGraph getParent(): vertex not in graph");
	}

	// assigns the parent of vertex v to be p and returns the
	// previous parent. if v or p is not a graph vertex, throws
	// IllegalArgumentException. for use by graph
	// algorithms
	public T setParent(T v, T p)
	{
		// find the vInfo index for v
		int pos1 = getVInfoIndex(v), pos2 = getVInfoIndex(p);
		VertexInfo<T> vtxInfo;
		T oldParent = null;

		if (pos1 != -1 && pos2 != -1)
		{
			vtxInfo = vInfo.get(pos1);
			oldParent = vtxInfo.parent;
			vtxInfo.parent = p;
		}
		else
			// throw an exception
			throw new IllegalArgumentException(
				"DiGraph setParent(): vertex not in graph");

		return oldParent;
	}

	// returns the integer data value associated with vertex v.
	// if v is not a graph vertex, throws
	// IllegalArgumentException. for use by graph
	// algorithms
	public int getData(T v)
	{
		// find the vInfo index for v
		int pos = getVInfoIndex(v);

		if (pos != -1)
			return vInfo.get(pos).dataValue;
		else
			// throw an exception
			throw new IllegalArgumentException(
				"DiGraph getData(): vertex not in graph");
	}

	// sets the integer data value associated with vertex v and
	// returns the previous value. if v is not a graph vertex,
	// throws IllegalArgumentException. for use by graph
	// algorithms
	public int setData(T v, int value)
	{
		// find the vInfo index for v
		int pos = getVInfoIndex(v), oldData = -1;
		VertexInfo<T> vtxInfo;

		if (pos != -1)
		{
			vtxInfo = vInfo.get(pos);
			oldData = vtxInfo.dataValue;
			vtxInfo.dataValue = value;
		}
		else
			// throw an exception
			throw new IllegalArgumentException(
				"DiGraph setData(): vertex not in graph");

		return oldData;
	}


	// sets the data value of each vertex to INFINITY. for use by
	// graph algorithms
	public void initData()
	{
		VertexInfo<T> vtxInfo;

		for (int i = 0; i < vInfo.size(); i++)
		{
			vtxInfo = vInfo.get(i);

			if (vtxInfo.occupied)
				vtxInfo.dataValue = DiGraphs.INFINITY;
		}
	}

	// returns a string representation of the graph
	public String toString()
	{
		Object[] mapEntry = vtxMap.entrySet().toArray();
		String returnStr = "";
		Map.Entry<T,Integer> entry = null;
		VertexInfo<T> vtxInfo = null;
		Iterator<Edge> iter = null;
		Edge e = null;

		Arrays.sort(mapEntry, new SortEntry());

		for (int i=0;i < mapEntry.length;i++)
		{
			entry = (Map.Entry<T,Integer>)mapEntry[i];
			vtxInfo = vInfo.get(entry.getValue());
			returnStr += entry.getKey() + ":  ";
			returnStr += "in-degree " + vtxInfo.inDegree +
			             "  out-degree " + vtxInfo.edgeList.size() +
			             "\n";
			returnStr += "    Edges: ";

			iter = vtxInfo.edgeList.iterator();

			while(iter.hasNext())
			{
				e = iter.next();
				returnStr += vInfo.get(e.dest).vertex
				          + "(" + e.weight + ")  ";
			}
			returnStr += "\n";
		}

		return returnStr;
	}

	// implements graph iterators
	private class IteratorImpl implements Iterator<T>
	{
		Iterator<T> iter;
		T lastValue = null;

		public IteratorImpl()
		{
			// iter traverses the map vertices
			iter = vtxMap.keySet().iterator();
		}

		public boolean hasNext()
		{
			return iter.hasNext();
		}

		public T next()
		{
			lastValue = iter.next();
			return lastValue;
		}

		public void remove()
		{
			if (lastValue == null)
				throw new IllegalStateException(
						"Graph vertex set iterator call to next() " +
						"required before calling remove()");

			// find the index of lastValue in vInfo
			int index = getVInfoIndex(lastValue);

			// remove the current vertex from the map
			iter.remove();

			// remove all edges that terminate at lastValue, and
			// update the in-degree of each neighbor of lastValue
			removeFixup(index);
		}
	}
}

class SortEntry implements Comparator
{
	public int compare(Object x, Object y)
	{
		Map.Entry obj1 = (Map.Entry)x, obj2 = (Map.Entry)y;

		return ((Comparable)obj1.getKey()).compareTo(obj2.getKey());
	}
}
