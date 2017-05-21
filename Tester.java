import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Complete your details...
 * Name and Surname: Reece Volker
 * Student/staff Number: u16312572
 * Created on 2017/05/21
 */

public class Tester {


    /** ASSUMPTIONS!!!
     * Every function should be able to be called with vertices that don't exist, with the following output
     * numEdges -- return 0;
     * getDegree -- return 0;
     * changeLabel -- return false
     * depthFirstTraversal -- return "";
     * getPath -- return "";
     * getChinesePostmanRoute -- return "";
    */
    public static void main(String[] args) throws Exception {

        String[] graphFiles = null;
        int j = -1;
        try {
            graphFiles = new String[] {"graph.txt", "graph2.txt", "moh.txt", "m3h.txt", "m3h1.txt", "nikolaus.txt", "pg409DFS.txt", "small.txt", "suffolk.txt", "traversableSimple.txt", "nonTraversableSimple.txt", "large.txt"};
            String[][] vertices = new String[graphFiles.length][];
            for (int i = 0; i < vertices.length; ++i)
                vertices[i] = getVertices(graphFiles[i]);
            String[] u = new String[graphFiles.length];
            String[] v = new String[graphFiles.length];

            for(int i = 0; i < u.length; ++i) {
                if (vertices[i].length > 2) {
                    u[i] = vertices[i][2];
                } else {
                    u[i] = vertices[i][0];
                }
            }
            for(int i = 0; i < v.length; ++i) {
                if (vertices[i].length > 4) {
                    v[i] = vertices[i][4];
                } else {
                    v[i] = vertices[i][0];
                }
            }

            for(j = 0; j < graphFiles.length; ++j) {
                testGraph(graphFiles[j], u[j], v[j], vertices[j]);
            }



        } catch (FileNotFoundException e) {
            String graph;
            if (j != -1)
                graph = graphFiles[j];
            else
                graph = "";

            System.out.println(graph + " not Found!" + e.getLocalizedMessage());

            throw e;
        } catch (Exception e) {

            String graph;
            if (j != -1)
                graph = graphFiles[j];
            else
                graph = "";
            System.out.println(graph + " + Exception e: " + e.getLocalizedMessage());

            throw e;
        }
    }

    public static String[] getVertices(String fileName) throws FileNotFoundException
    {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            List<String> verts = new ArrayList<>();

            scanner.nextLine();
            while (scanner.hasNext()) {
                String cur = scanner.nextLine();

                Scanner s = new Scanner(cur).useDelimiter(",");

                for(int i = 0; i < 2; ++i) {
                    String vert = s.next();
                    if (!verts.contains(vert))
                        verts.add(vert);
                }
            }

            return verts.toArray(new String[verts.size()]);

        } catch (FileNotFoundException e) {
            throw e;
        }


    }

    public static void testGraph(String fileName, String u, String v, String[] vertices) throws Exception
    {
        Graph testReconstruct = new Graph(fileName);
        if (!testReconstruct.reconstructGraph(fileName))
            throw new Exception("Cannot find file possible");

        Graph graph = new Graph(fileName);

        System.out.println("TESTING " + fileName + "!!!!!");
        System.out.println("====================================================================");
        System.out.println("====================================================================");
        System.out.println("====================================================================");

        System.out.println("numEdges(" + u + "," + "v" + graph.numEdges(u, v));



        String one = "Horses";
        String two = "Bananas";
        System.out.println("changeLabel(" + one + "," + two + ") :" + graph.changeLabel(one, two));
        moreTests(graph, u, v, vertices, false);
        System.out.println("Reconstruct graph to same: " + graph.reconstructGraph(fileName));
        System.out.println("changeLabel(" + v + "," + one + ") :" + graph.changeLabel(v, one));
        moreTests(graph, u, v, vertices, false);
        System.out.println("Reconstruct graph to same: " + graph.reconstructGraph(fileName));
        System.out.println("changeLabel(" + two + "," + v + ") :" + graph.changeLabel(two, v));
        moreTests(graph, u, v, vertices, false);
        System.out.println("Reconstruct graph to same: " + graph.reconstructGraph(fileName));
        System.out.println("changeLabel(" + v + "," + u + ") :" + graph.changeLabel(v, u));
        moreTests(graph, u, v, vertices, false);

        System.out.println("clone() and test:");
        Graph clone = graph.clone();
        moreTests(clone, u, v, vertices, false);
        System.out.println();

        System.out.println("clone.changeLabel(" + v + "," + u + ") :" + clone.changeLabel(v, u));
        moreTests(clone, u, v, vertices, false);
        moreTests(graph, u, v, vertices, false);


        System.out.println("====================================================================");
        System.out.println("====================================================================");
        System.out.println("====================================================================");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public static void moreTests(Graph graph, String u, String v, String[] vertices, boolean isPostmanGraph)
    {
       for(int i = 1; i < vertices.length; ++i) {
           System.out.println("numEdges(" + vertices[i] + "," + vertices[i - 1] + ") :" + graph.numEdges(vertices[i], vertices[i - 1]));
           System.out.println("numEdges(" + vertices[i - 1] + "," + vertices[i] + ") :" + graph.numEdges(vertices[i - 1], vertices[i]));
           System.out.println();
       }

       for(int i = 1; i < vertices.length; ++i) {
           System.out.println("getPath(" + vertices[i] + "," + vertices[i-1] + ") :" + graph.getPath(vertices[i], vertices[i-1]));
           System.out.println("getPath(" + vertices[i-1] + "," + vertices[i] + ") :" + graph.getPath(vertices[i-1], vertices[i]));
           System.out.println();
       }

       for(int i = 0; i < vertices.length; ++i) {
           System.out.println("getDegree(" + vertices[i] + ") :" + graph.getDegree(vertices[i]));
       }
       System.out.println();

       for(int i = 0; i < vertices.length; ++i) {
           System.out.println("depthFirstTraversal(" + vertices[i] + ") :" + graph.depthFirstTraversal(vertices[i]));
       }
       System.out.println();

       System.out.println("getOdd() " + graph.getOdd());
       System.out.println();

       System.out.println("getChinesePostmanCost() " + graph.getChinesePostmanCost());
       System.out.println();

       if (isPostmanGraph == false) {
           System.out.println("This isn't chinese postman graph, generate one and test it");
           System.out.println("getChinesePostmanGraph() == moreTests");
           moreTests(graph.getChinesePostmanGraph(), u, v, vertices, true);

       }
       else {
           System.out.println("This already is chinese postman - don't generate again. ");
       }

        for(int i = 0; i < vertices.length; ++i) {
            System.out.println("getChinesePostmanRoute(" + vertices[i] + ") :" + graph.getChinesePostmanRoute(vertices[i]));
        }
        System.out.println();
    }
}
