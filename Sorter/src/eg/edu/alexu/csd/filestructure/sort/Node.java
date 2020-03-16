package eg.edu.alexu.csd.filestructure.sort;

public class Node <T extends Comparable<T> >  implements INode<T> {
    private int index;
    private T value;
    private IHeap<T> myHeap;

    Node(){}

    public Node(int index, T value, IHeap<T> myHeap) {
        this.index = index;
        this.value = value;
        this.myHeap = myHeap;
    }
    public void setMyHeap(IHeap<T> myHeap) {
        this.myHeap = myHeap;
    }
    @Override
    public INode<T> getLeftChild() {
        if (index*2 > myHeap.size()) return null;
        return ((Heap)myHeap).getNodeI(index*2);
    }
    @Override
    public INode<T> getRightChild() {
        if (index*2 +1 > myHeap.size()) return null;
        return ((Heap)myHeap).getNodeI(index*2 +1);
    }
    @Override
    public INode<T> getParent() {
        if (index/2 < 1) return null;
        return ((Heap)myHeap).getNodeI(index/2);
    }
    @Override
    public T getValue() {
        return this.value;
    }
    @Override
    public void setValue(T value) {
        this.value = value;
    }

    public INode<T> clone(){
        return new Node<T>(index,value,myHeap);
    }
}
