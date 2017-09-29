package farmer;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import java.util.Arrays;

/**
 * This class constructs an undirected graph of vertices representing
 * states of the FWGC problem.
 * The structure of the graph is as shown at:
 * 
 * http://www.d.umn.edu/~tcolburn/cs2511/javalabs/graphs/images/FarmerGraph1.png
 * 
 * The FWGC states corresponding to the vertices is as shown at:
 * 
 * http://www.d.umn.edu/~tcolburn/cs2511/javalabs/graphs/images/FarmerGraph2.png
 * @author Peter Braband
 */
public class FarmerGraph extends Graph {
    
    private Vertex v1, v2, v3, v4, v5, v6, v7, v8, v9, v10;
    
    public FarmerGraph(){
    v1 = new FarmerState("west", "west", "west", "west");
    v2 = new FarmerState("west", "east", "west", "east"); 
    v3 = new FarmerState("west", "east", "west", "west");
    v4 = new FarmerState("west", "west", "east", "west");
    v5 = new FarmerState("west", "west", "west", "east");
    v6 = new FarmerState("east", "east", "east", "east");
    v7 = new FarmerState("east", "west", "east", "west");
    v8 = new FarmerState("east", "west", "east", "east");
    v9 = new FarmerState("east", "east", "west", "east");
    v10 = new FarmerState("east", "east", "east", "west");
    
    addVertices(Arrays.asList(new Vertex[] {v1,v2,v3,v4,v5,v6,v7,v8,v9,v10}));
    addEdges(Arrays.asList(new Edge[]{  new Edge(v1, v7),
                                        new Edge(v7, v4),
                                        new Edge(v4, v10),
                                        new Edge(v4, v8),
                                        new Edge(v10, v3),
                                        new Edge(v8, v5),
                                        new Edge(v3, v9),
                                        new Edge(v5, v9),
                                        new Edge(v9, v2),
                                        new Edge(v2, v6)}));
    setDirected(false);
    
    v1.setName("1");
    v2.setName("2");
    v3.setName("3");
    v4.setName("4");
    v5.setName("5");
    v6.setName("6");
    v7.setName("7");
    v8.setName("8");
    v9.setName("9");
    v10.setName("10");
    }
    
    public Vertex getV1(){
        return this.v1;
    }
    
    public Vertex getV2(){
        return this.v2;
    }
    
    public Vertex getV3(){
        return this.v3;
    }
    
    public Vertex getV4(){
        return this.v4;
    }
    
    public Vertex getV5(){
        return this.v5;
    }
    
    public Vertex getV6(){
        return this.v6;
    }
    
    public Vertex getV7(){
        return this.v7;
    }
    public Vertex getV8(){
        return this.v8;
    }
    
    public Vertex getV9(){
        return this.v9;
    }
    
    public Vertex getV10(){
        return this.v10;
    }
}
