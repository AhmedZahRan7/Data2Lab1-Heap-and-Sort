package eg.edu.alexu.csd.filestructure.sort;
import java.util.ArrayList;
import java.util.Collection;

public class Heap  <T extends Comparable<T> & Cloneable> implements IHeap<T>    {
    private ArrayList<INode<T>> array = new ArrayList<>();
    private int size;

    Heap(){
        array.add(new Node<T>());
        size=0;
    }

    public INode<T> getNodeI(int i){
        return array.get(i);
    }

    @Override
    public INode<T> getRoot() {
        if(size==0) return null;
        return array.get(1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void heapify(INode<T> iNode) {
        if (iNode == null)return;
        INode<T> largest = iNode;
        INode<T> left = iNode.getLeftChild();
        INode<T> right =iNode.getRightChild();

        if(left!= null && left.getValue().compareTo(largest.getValue())>0 ) largest = left;
        if(right!=null && right.getValue().compareTo(largest.getValue())>0) largest = right;

        if(largest != iNode){
            T temp = largest.getValue();
            largest.setValue(iNode.getValue());
            iNode.setValue(temp);
            heapify(largest);
        }
    }

    @Override
    public T extract() {
        if (size == 0) return null;
        T max = getRoot().getValue();
        swapNodes(array.get(1),array.get(size));
        size--;
        heapify(getRoot());
        return max;
    }

    @Override
    public void insert(T element) {
        if (element == null) return;
        size++;
        INode<T> newOne = new Node<>(size,element,this);
        if (size < array.size()) array.set(size,newOne);
        else array.add(newOne);
        heapifyThisBranch(newOne);
    }

    @Override
    public void build(Collection<T> unsorted) {
        if(unsorted == null) return;
        this.array = new ArrayList<>();
        array.add(new Node<T>());
        size=0;
        for (T value : unsorted) {
            size++;
            array.add(new Node<>(size,value,this));
        }
        heapifyBottomToUp();
    }

    private void swapNodes(INode<T> node1 , INode<T> node2){
        T temp1 = node1.getValue();
        node1.setValue(node2.getValue());
        node2.setValue(temp1);
    }
    private void heapifyBottomToUp(){
        for(int i=size/2 ; i>= 1 ;i--) heapify(array.get(i));
    }

    private void heapifyThisBranch(INode<T> iNode){
        if(iNode == null || iNode.getParent() == null) return;
        if (iNode.getValue().compareTo(iNode.getParent().getValue())>0) {
            swapNodes(iNode,iNode.getParent());
            heapifyThisBranch(iNode.getParent());
        }
    }

    private void setArray(ArrayList<INode<T>> array) {
        this.array = array;
    }

    private void setSize(int size){
        this.size=size;
    }
    public IHeap<T> clone(){
        Heap<T> newHeap = new Heap();
        ArrayList<INode<T>> newArray = new ArrayList<>();
        for(int i=0;i<array.size();i++) {
            INode<T> clonedNode = ((Node<T>)(array.get(i))).clone();
            ((Node<T>)clonedNode).setMyHeap(newHeap);
            newArray.add(clonedNode);
        }
        newHeap.setArray(newArray);
        newHeap.setSize(newArray.size()-1);
        return newHeap;
    }
}
