import java.io.*;
import java.util.*;

class Solution
{
    public static class Heading {
        protected int weight;
        protected String text;

        public Heading(int weight, String text) {
            this.weight = weight;
            this.text = text;
        }
    }
    public static class Node {
        protected Heading heading;
        protected List<Node> children;

        public Node(Heading heading) {
            this.heading = heading;
            this.children = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws java.lang.Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Heading> headings = new ArrayList<>();
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            headings.add(parse(line));
        }
        Node outline = toOutline(headings);
        String html = toHtml(outline);
        System.out.println(html);
    }


    /////////// BEGIN EDITABLE //////////////
    private static Node toOutline(List<Heading> headings) {
        // Implement this function. Sample code below builds an
        // outline of only the first heading.

        Node root = new Node(new Heading(0, ""));
        

        // If we have just one heading..
        if (headings.size() == 1){
            root.children.add(new Node(headings.get(0)));
        }
        else{
            for (int i=0; i<= headings.size()-1; i++){
                Heading heading = headings.get(i);
                int w = heading.weight;

                if (w == 1){
                    root.children.add(new Node(heading));
                }
                else if(w == 2){
                    List<Node> childList = root.children;

                    if (childList.size() < 1){
                        // Root has no child node yet, we will create one child and then attach this heading to it
                        root.children.add(new Node(new Heading(0, ""))); 
                    }
                    
                    // Now get the last child and a child to it..
                    int indexOfLastRootChild = childList.size()-1;
                    root.children.get(indexOfLastRootChild).children.add(new Node(heading));
                }
                else if(w == 3){
                    int indexOfRootlastRootCild = root.children.size()-1;
                    Node lastChildOfRoot = root.children.get(indexOfRootlastRootCild);

                    int indexOfLastChildOfLastChildOfRoot = lastChildOfRoot.children.size()-1;
                    root.children.get(indexOfRootlastRootCild).children.get(indexOfLastChildOfLastChildOfRoot)
                        .children.add(new Node(heading));

                }
                else if(w == 4){
                    int indexOfRootlastRootCild = root.children.size()-1;
                    Node lastChildOfRoot = root.children.get(indexOfRootlastRootCild);

                    int indexOfLastChildOfLastChildOfRoot = lastChildOfRoot.children.size()-1;
                    Node lastChildOfLastChildOfLastChild = lastChildOfRoot.children.get(indexOfLastChildOfLastChildOfRoot);

                    int index3 = lastChildOfLastChildOfLastChild.children.size()-1;
                    root.children.get(indexOfRootlastRootCild).children.get(indexOfLastChildOfLastChildOfRoot)
                        .children.get(index3).children.add(new Node(heading));
                }

                else if(w == 5) {
                    int indexOfRootlastRootCild = root.children.size()-1;
                    Node lastChildOfRoot = root.children.get(indexOfRootlastRootCild);

                    int indexOfLastChildOfLastChildOfRoot = lastChildOfRoot.children.size()-1;
                    Node lastChildOfLastChildOfLastChild = lastChildOfRoot.children.get(indexOfLastChildOfLastChildOfRoot);

                    int index3 = lastChildOfLastChildOfLastChild.children.size()-1;
                    Node child3 = lastChildOfLastChildOfLastChild.children.get(index3);

                    int index4 = child3.children.size()-1;
                    root.children.get(indexOfRootlastRootCild).children.get(indexOfLastChildOfLastChildOfRoot)
                        .children.get(index3).children.get(index4).children.add(new Node(heading));
                }
                else if(w==6){
                    int indexOfRootlastRootCild = root.children.size()-1;
                    Node lastChildOfRoot = root.children.get(indexOfRootlastRootCild);

                    int indexOfLastChildOfLastChildOfRoot = lastChildOfRoot.children.size()-1;
                    Node lastChildOfLastChildOfLastChild = lastChildOfRoot.children.get(indexOfLastChildOfLastChildOfRoot);

                    int index3 = lastChildOfLastChildOfLastChild.children.size()-1;
                    Node child3 = lastChildOfLastChildOfLastChild.children.get(index3);

                    int index4 = child3.children.size()-1;
                    Node child4 = child3.children.get(index4);

                    int index5 = child4.children.size()-1;
                    root.children.get(indexOfRootlastRootCild).children.get(indexOfLastChildOfLastChildOfRoot)
                        .children.get(index3).children.get(index4).children.get(index5).children.add(new Node(heading));
                }
            }
        }

        return root;
    }
    /////////// END EDITABLE //////////////

    /** Parses a line of input.
     This implementation is correct for all predefined test cases. */
    private static Heading parse(String record) {
        String[] parts = record.split(" ", 2);
        int weight = Integer.parseInt(parts[0].substring(1));
        Heading heading = new Heading(weight, parts[1].trim());
        return heading;
    }

    /** Converts a node to HTML.
     This implementation is correct for all predefined test cases. */
    private static String toHtml(Node node) {
        StringBuilder buf = new StringBuilder();
        if (!node.heading.text.isEmpty()) {
            buf.append(node.heading.text);
            buf.append("\n");
        }
        Iterator<Node> iter = node.children.iterator();
        if (iter.hasNext()) {
            buf.append("<ul>");

            while (iter.hasNext()) {
                Node child = iter.next();
                buf.append("<li>");
                buf.append(toHtml(child));
                buf.append("</li>");
                if (iter.hasNext()) {
                    buf.append("\n");
                }
            }
            buf.append("</ul>");
        }
        return buf.toString();
    }
}
