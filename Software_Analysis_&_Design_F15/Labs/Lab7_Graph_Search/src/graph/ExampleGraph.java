package graph;

import java.util.Arrays;
import java.util.List;

/**
 * Creates the undirected graph shown at:
 * 
 * http://www.d.umn.edu/~tcolburn/cs2511/javalabs/graphs/images/ExampleGraph.png
 * 
 * @author tcolburn
 */
public class ExampleGraph extends Graph {
    
    private Vertex r, s, t, u, v, w, x, y;

    public ExampleGraph() {
        
        r = new SimpleVertex("r");
        s = new SimpleVertex("s");
        t = new SimpleVertex("t");
        u = new SimpleVertex("u");
        v = new SimpleVertex("v");
        w = new SimpleVertex("w");
        x = new SimpleVertex("x");
        y = new SimpleVertex("y");
    
        addVertices(Arrays.asList(new Vertex[] {r, s, t, u, v, w, x, y}));
        
        addEdges(Arrays.asList(new Edge[] {new Edge(r, s),
                                           new Edge(r, v),
                                           new Edge(s, w),
                                           new Edge(w, x),
                                           new Edge(w, t),
                                           new Edge(t, x),
                                           new Edge(t, u),
                                           new Edge(y, x),
                                           new Edge(u, y)}));
        setDirected(false);
    }

    public Vertex getR() {
        return r;
    }

    public Vertex getS() {
        return s;
    }

    public Vertex getT() {
        return t;
    }

    public Vertex getU() {
        return u;
    }

    public Vertex getV() {
        return v;
    }

    public Vertex getW() {
        return w;
    }

    public Vertex getX() {
        return x;
    }

    public Vertex getY() {
        return y;
    }
}
