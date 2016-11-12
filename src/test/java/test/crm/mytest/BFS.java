package test.crm.mytest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by santhosh.b on 20/09/15.
 */

class GraphNode    {

    Integer data;
    boolean isVisted = false;
    public GraphNode(Integer data)  {

        this.data = data;
    }


}

class VertexG    {

    GraphNode source;
    GraphNode dest;

    public VertexG(GraphNode source, GraphNode dest) {

        this.source = source;
        this.dest = dest;
    }



}
public class BFS {

    List<GraphNode> graphNodeList = new ArrayList<GraphNode>();
    List<VertexG> vertextList = new ArrayList<VertexG>();

    public void addGraphNode(GraphNode node) {

        graphNodeList.add(node);
    }
    public void addVertex(GraphNode source, GraphNode dest) {
        VertexG vertex = new VertexG(source, dest);
        vertextList.add(vertex);
    }

    public void traverse(GraphNode node)    {


        Deque<GraphNode> traverseQue = new ArrayDeque<GraphNode>();
        traverseQue.offer(node);
        GraphNode temp = null;
        node.isVisted = true;
        while((node = traverseQue.poll()) != null)  {

            //Find vertex adjacent to node and offer the same.

        }
     }


}
