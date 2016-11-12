package test.crm.mytest;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by santhosh.b on 19/09/15.
 */

class Node {

    Integer data;
    Node left;
    Node right;

    Node(Integer data, Node left, Node right) {

        this.data = data;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object node)    {

        Node temp = (Node) node;
        if(this.data.equals(temp.data))
            return true;
        return false;
    }
}

public class BinaryTree {


    Node root;

    public Node getRoot()   {return root;}
    public void add(Integer integer) {

        if (root == null) {

            root = new Node(integer, null, null);
            return;
        }
        Node addNode = new Node(integer, null, null);
        Node traverseNode = root;

        while (traverseNode != null) {

            if (integer < traverseNode.data) {
                if (traverseNode.left != null)
                    traverseNode = traverseNode.left;
                else    {
                    traverseNode.left = addNode;
                    return;
                }
            } else if (integer > traverseNode.data) {
                if (traverseNode.right != null)
                    traverseNode = traverseNode.right;
                else    {
                    traverseNode.right = addNode;
                    return;
                }

            }
        }

    }

    public void printMe()   {

       traverseTree(root);

    }

    private void traverseTree(Node root)    {

        if(root == null)    return;
        traverseTree(root.left);
        System.out.println(root.data);
        traverseTree(root.right);
    }

    public boolean isBinaryTree(Node root)   {

        if(root == null)
            return true;

        if((root.left != null && root.left.data > root.data) || (root.right != null && root.right.data < root.data))    {
            return false;
        }

        isBinaryTree(root.left);
        isBinaryTree(root.right);
        return true;
    }

    public void levelOrderTraversal(Node root)   {

        Deque<Node> deQue =new ArrayDeque<Node>();
        deQue.push(root);
        Node node = null;
        while((node = deQue.pop()) != null) {

            System.out.println(node.data);
            if(node.left != null)
            deQue.push(node.left);
            if(node.right != null)
            deQue.push(node.right);
        }
    }

    public void deleteNode(Node deleteNode)   {

        Node traversalNode = root;
        Node parentNode = root;
        if(root == null) return;
        if(traversalNode.equals(deleteNode))    {

            //Same as root
        }
        else    {

            Node parent = findAndGetParentNode(root, root , deleteNode);
            if(deleteNode.left == null && deleteNode.right == null) {

                if(parent.left == deleteNode)   {
                    parent.left = null;
                }
                else if (parent.right == deleteNode)    {
                    parent.right = null;
                }
            }
            /*
             * Single Node Child
             */
            if(deleteNode.left != null && deleteNode.right == null) {

                if(parent.left == deleteNode)   {
                    parent.left = deleteNode.left;
                }
                else if (parent.right == deleteNode)    {
                    parent.right = deleteNode.left;;
                }
            }
            if(deleteNode.right != null && deleteNode.left == null) {

                if(parent.left == deleteNode)   {
                    parent.left = deleteNode.left;
                }
                else if (parent.right == deleteNode)    {
                    parent.right = deleteNode.left;;
                }
            }
            /*
             * End Single Node Child
             */
            /*
             *Both left right exists
             */
            if(deleteNode.right != null && deleteNode.left != null) {

                //Look for smallest node in right most sub-tree
            }

        }
    }


    public Node findAndGetParentNode(Node parent , Node child, Node findNode) {

        if(child != null && child.data.equals(findNode.data))   {
            findNode = child;
            return parent;
        }

        findAndGetParentNode(root, root.left, findNode);
        findAndGetParentNode(root, root.right, findNode);

        return null;
    }
    public static void main(String args[]) {

        Integer data = 10;
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.add(10);
        binaryTree.add(20);
        binaryTree.add(5);
        binaryTree.add(1);
        binaryTree.add(30);
        binaryTree.printMe();
        System.out.println(binaryTree.isBinaryTree(binaryTree.getRoot()));
        binaryTree.levelOrderTraversal(binaryTree.getRoot());
    }
}
