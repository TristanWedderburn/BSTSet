package lab3;

public class BSTSet {

    public final TNode root;

    public BSTSet() {//null constructor
        root = null;
    }

    public BSTSet(int[] input) {
        int temp;
        //sort unsorted input array using bubble sort algorithm
        for (int i = 0; i < input.length; i++) {
            for (int j = 1; j < (input.length - i); j++) {
                if (input[j - 1] > input[j]) {
                    //swap elements  
                    temp = input[j - 1];
                    input[j - 1] = input[j];
                    input[j] = temp;
                }
            }
        }

        int j = 0;
        int i = 1;
        int[] output = null;

        //removes duplicates from array
        if (input.length > 2) {//if root has children
            while (i < input.length) {
                if (input[i] == input[j]) {
                    i++;
                } else {
                    input[++j] = input[i++];
                }
            }
            output = new int[j + 1];
            for (int k = 0; k < output.length; k++) {
                output[k] = input[k];
            }
        }
//         if (output[0] == 0) {
//            int[] output2 = new int[output.length - 1];
//            for (int l = 0; l < output2.length; l++) {
//                output2[l] = output[l + 1];
//            }
//            output = output2;
//        }
        root = sortedListToBST(output, 0, output.length - 1);
    }

    public boolean isIn(int v) {
        TNode p = root;
        while (p != null) {
            if (v == p.element) {
                return true;
            } else if (v > p.element) {
                p = p.right;
            } else if (v < p.element) {
                p = p.left;
            }
        }
        return false;
    }

    public void add(int v) {
        //adds node as a leaf
        TNode p = root;//temporary node to traverse BST        
        if (isIn(v) == false) {//if duplicate
            while (p != null) {
                if (v > p.element) {
                    if (p.right == null) {
                        p.right = new TNode(v, null, null);
                        break;
                    } else {
                        p = p.right;
                    }
                }
                if (v < p.element) {

                    if (p.left == null) {
                        p.left = new TNode(v, null, null);
                        break;
                    } else {
                        p = p.left;
                    }
                }
            }
        }
    }

    public boolean remove(int v) {
        //removes nodes based on 3 cases in lecture slides
        TNode p = root; //node to traverse BST
        if (isIn(v) == true) {//must be in BST
            while (true) {
                if (v > p.element) {
                    if (p.right.element == v) {
                        if (p.right.left != null && p.right.right != null) {
                            //2 children
                            TNode q = p.right.right;
                            while (q.left != null) {
                                q = q.left;
                            }
                            if (q.left == null) {
                                q.left = p.right.left;
                                p.right = q;
                            } else {
                                while (q.left != null) {
                                    q = q.left;
                                }
                                p.right = q.left;
                                q.left = null;
                            }
                            return true;
                        }
                        if (p.right.left == null && p.right.right == null) {
                            //leaf node
                            p.right = null;
                            return true;
                        } else {
                            //1 child node
                            p.right = (p.right.left != null) ? p.right.left : p.right.right;
                            return true;
                        }
                    } else {
                        p = p.right;
                    }
                }
                if (v < p.element) {
                    if (p.left.element == v) {
                        if (p.left.left != null && p.left.right != null) {
                            //2 children
                            TNode q = p.right.right;
                            while (q.left != null) {
                                q = q.left;
                            }
                            p.right.element = q.element;
                            TNode r = p.right;
                            do {
                                r = r.left;
                            } while (r != null && r.element != p.right.element);
                            r = null;
                            return true;

                        }
                        if (p.left.left == null && p.right.right == null) {
                            //leaf node
                            p.left = null;
                            return true;
                        } else {
                            //1 child node
                            p.left = (p.right.left != null) ? p.right.left : p.right.right;
                            return true;
                        }
                    } else {
                        p = p.left;
                    }
                }
            }
        } else {
            return false; //if not in BST
        }
    }

    public BSTSet union(BSTSet s) {
        if (size() == 0) {
            return s;
        } else if (s.size() == 0) {
            return this;
        } else {
            int[] union = new int[size() + s.size()];
            //recursive alogrithm to turn BST to an array
            toArray(root, union, 0, s);
            toArray(s.root, union, size(), this);
            BSTSet unionSet = new BSTSet(union);//use constructor to initialize new object
            return unionSet;
        }
    }

    private static int toArray(TNode n, int[] results, int index, BSTSet s) {//for union

        if (n.left != null) {
            //if (s.isIn(n.left.element)!= true) {
            index = toArray(n.left, results, index, s);//returns index
            //}
        }

        if (n.right != null) {
            //if (s.isIn(n.right.element) != true) {//avoid duplicates
            index = toArray(n.right, results, index, s);//returns index
            //}
        }
        if (s.isIn(n.element) != true) {//avoid duplicates
            results[index] = n.element;//add element to array
        }
        return index + 1;
    }

    private static int toArray2(TNode n, int[] results, int index, BSTSet s) {//for intersection
        if (n.left != null) {
            if (s.isIn(n.left.element) == true) {//must be in both BSTSs
                index = toArray2(n.left, results, index, s);//returns index
            }
        }

        if (n.right != null) {
            if (s.isIn(n.right.element) == true) {//must be in both BSTSs
                index = toArray2(n.right, results, index, s);//returns index
            }
        }

        if (s.isIn(n.element) == true) {
            results[index] = n.element;//add element to array
        }
        return index + 1;
    }

    public BSTSet intersection(BSTSet s) {
        if (size() == 0) {
            return new BSTSet();
        } else if (s.size() == 0) {
            return new BSTSet();
        } else {
            int length = (size() <= s.size()) ? size() : s.size();
            int[] inter = new int[length];
            //recursive algorithm to turn BST into an array
            
            toArray2(root, inter, 0, s);
            
            int counter=0;
            for(int i =0;i<inter.length;i++){
                if(inter[i]==0){
                    counter++;
                }
            }
            if(counter==inter.length){
                return new BSTSet();
            }
            
            if(isIn(0)==true&&s.isIn(0)==true){
                counter--;
            }
            int[]output = new int[inter.length-counter];
            int j=0;
            int i=0;
            while(i<inter.length-counter){
                if(inter[i]!=0){
                    output[j]=inter[i];
                    j++;
                }
                i++;
            }
            inter=output;
            BSTSet interSet = new BSTSet(inter);
            return interSet;
        }
    }

    public int size() {
        if (root == null) {
            return 0;
        } else if (root.right == null && root.left == null) {
            return 1;
        } else {
            //recursive algorithm to find each element in ST
            return size(root);
        }
    }

    public int size(TNode p) {
        if (p == null) {
            return 0;
        } else {
            int size = size(p.left);
            size += size(p.right);
            return (size + 1);
        }
    }

    public int height() {
        if (root == null) {
            return -1;
        } else if (root.right == null && root.left == null) {
            return 0;
        } else {
            //recursive algorithm to find how many levels are in BST
            return height(root);
        }
    }

    public int height(TNode p) {
        if (p == null) {
            return 0;
        } else {
            int lheight = height(p.left);
            int rheight = height(p.right);

            if (lheight > rheight) {//finds longer path and continues with that path
                return (lheight + 1);
            } else {
                return (rheight + 1);
            }
        }
    }

    private TNode sortedListToBST(int[] input, int start, int end) {
        //recursively initializes the element and its left and right nodes
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TNode add = new TNode(input[mid], null, null);
        add.left = sortedListToBST(input, start, mid - 1);
        add.right = sortedListToBST(input, mid + 1, end);
        return add;
    }

    public void printBSTSet(){
        if (root == null) {
            System.out.println("The set is empty");
        } else {
            System.out.print("The set elements are: ");
            printBSTSet(root);//recursive algorithm for in-order traversal
            System.out.print("\n");
        }
    }

    private void printBSTSet(TNode t) {
        if (t != null) {
            printBSTSet(t.left);
            System.out.print(" " + t.element + ", ");
            printBSTSet(t.right);
        }
    }

    public void printNonRec()//in order traversal
    {
        if (root != null) {
            LLStack stack = new LLStack();
            TNode node = root;

            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            while (stack.IsEmpty() != true) {
                node = stack.pop();
                System.out.print(node.element + ", ");

                if (node.right != null) {
                    node = node.right;

                    while (node != null) {
                        stack.push(node);
                        node = node.left;
                    }
                }
            }
        } else {
            System.out.print("Set is empty");
        }
    }

}
