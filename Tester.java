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

        int option = 0;

        if (args.length == 0) {
            System.out.println("Usage: java Tester [tiny|small|full]");
            System.out.println("tiny - only tests chinesePostmanRoute()");
            System.out.println("small - only tests some things");
            System.out.println("full - tests many combinations of everything - WARNING: produces a lot of output");

            System.out.println("\n\nE.g. java Tester tiny > out.txt");

            return;
        }
        else {
            if (args[0].compareTo("small") == 0)
                option = 1;
            else if (args[0].compareTo("full") == 0)
                option = 2;
            else
                option = 0;
        }
        String[] graphFiles = null;
        int j = -1;
        try {
            graphFiles = new String[] {"graph.txt", "graph2.txt", "moh.txt", "m3h.txt", "m3h1.txt", "nikolaus.txt", "pg409DFS.txt", "small.txt", "suffolk.txt", "traversableSimple.txt", "nonTraversableSimple.txt", "large.txt", "tiny.txt"};

            for(int i = 0; i < graphFiles.length; ++i) {
                graphFiles[i] = "./files/" + graphFiles[i];
            }

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
                testGraph(graphFiles[j], u[j], v[j], vertices[j], option);
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

    public static void testGraph(String fileName, String u, String v, String[] vertices, int thoroughness) throws Exception
    {
        Graph testReconstruct = new Graph(fileName);
        if (!testReconstruct.reconstructGraph(fileName))
            throw new Exception("Cannot find file possible");

        Graph graph = new Graph(fileName);

        System.out.println("TESTING " + fileName + "!!!!!");
        System.out.println("====================================================================");
        System.out.println("====================================================================");
        System.out.println("====================================================================");

        if (thoroughness == 1 || thoroughness == 2) {
            System.out.println("numEdges(" + u + "," + v + "):\t" + graph.numEdges(u, v));


            boolean full = true;
            if (thoroughness == 1)
                full = false;


            String one = "Horses";
            String two = "Bananas";
            System.out.println("changeLabel(" + one + "," + two + ") :" + graph.changeLabel(one, two));
            moreTests(graph, u, v, vertices, false, full);
            System.out.println("Reconstruct graph to same: " + graph.reconstructGraph(fileName));
            System.out.println("changeLabel(" + v + "," + one + ") :" + graph.changeLabel(v, one));

            moreTests(graph, u, v, vertices, false, full);

            if (thoroughness == 2) {
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

            }
        }
        else {
            for(int i = 0; i < vertices.length; ++i) {
                System.out.println("chinesePostmanRoute(" + vertices[i] + "):\t" + graph.getChinesePostmanGraph().getChinesePostmanRoute(vertices[i]));
            }
        }

        System.out.println("====================================================================");
        System.out.println("====================================================================");
        System.out.println("====================================================================");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public static void moreTests(Graph graph, String u, String v, String[] vertices, boolean isPostmanGraph)
    {
        moreTests(graph, u, v, vertices, isPostmanGraph, true);
    }

    public static void moreTests(Graph graph, String u, String v, String[] vertices, boolean isPostmanGraph, boolean full)
    {
        if (isPostmanGraph) {
            System.out.println("POSTMAN!!!");
            System.out.println("====================================================================");
            System.out.println("====================================================================");
        }

       for(int i = 1; i < vertices.length; ++i) {
           System.out.println("numEdges(" + vertices[i] + "," + vertices[i - 1] + ") :\t" + graph.numEdges(vertices[i], vertices[i - 1]));
           System.out.println("numEdges(" + vertices[i - 1] + "," + vertices[i] + ") :\t" + graph.numEdges(vertices[i - 1], vertices[i]));
           System.out.println();
       }

       for(int i = 1; i < vertices.length; ++i) {
           System.out.println("getPath(" + vertices[i] + "," + vertices[i-1] + ") :\t" + graph.getPath(vertices[i], vertices[i-1]));
           System.out.println("getPath(" + vertices[i-1] + "," + vertices[i] + ") :\t" + graph.getPath(vertices[i-1], vertices[i]));
           System.out.println();
       }

       if (full == true) {
           for (int i = 0; i < vertices.length; ++i) {
               for (int j = 0; j < vertices.length; ++j) {
                   System.out.println("numEdges(" + vertices[i] + "," + vertices[j] + ") :\t" + graph.numEdges(vertices[i], vertices[j]));
               }
           }

           for (int i = 0; i < vertices.length; ++i) {
               for (int j = 0; j < vertices.length; ++j) {
                   System.out.println("getPath(" + vertices[i] + "," + vertices[j] + ") :\t" + graph.getPath(vertices[i], vertices[j]));
               }
           }
       }
       for(int i = 0; i < vertices.length; ++i) {
           System.out.println("getDegree(" + vertices[i] + ") :\t" + graph.getDegree(vertices[i]));
       }
       System.out.println();

       for(int i = 0; i < vertices.length; ++i) {
           System.out.println("depthFirstTraversal(" + vertices[i] + ") :\t" + graph.depthFirstTraversal(vertices[i]));
       }
       System.out.println();

       System.out.println("getOdd() " + graph.getOdd());
       System.out.println();

       System.out.println("getChinesePostmanCost() " + graph.getChinesePostmanCost());
       System.out.println();

       if (full == true) {
           if (isPostmanGraph == false) {
               System.out.println("This isn't chinese postman graph, generate one and test it");
               System.out.println("getChinesePostmanGraph() == moreTests");
               moreTests(graph.getChinesePostmanGraph(), u, v, vertices, true);

           } else {
               System.out.println("This already is chinese postman - don't generate again. ");
           }
       }
        for(int i = 0; i < vertices.length; ++i) {
            System.out.println("getChinesePostmanRoute(" + vertices[i] + ") :\t" + graph.getChinesePostmanRoute(vertices[i]));
        }
        System.out.println();

       if (isPostmanGraph) {
           System.out.println("END POSTMAN!!!");
           System.out.println("====================================================================");
           System.out.println("====================================================================");
           System.out.println();
       }
    }
}
