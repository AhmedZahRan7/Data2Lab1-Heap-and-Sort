package eg.edu.alexu.csd.filestructure.sort;
import java.util.ArrayList;

public class Sort <T extends Comparable<T>> implements ISort {
    @Override
    public IHeap<T> heapSort(ArrayList arrayList) {
        if (arrayList==null) return new Heap();
        IHeap<T> heap = new Heap();
        heap.build(arrayList);
        for(int i=0;i<arrayList.size();i++) heap.extract();
        return ((Heap)heap).clone();
    }
    @Override
    public void sortFast(ArrayList arrayList) {
        if (arrayList==null) return;
        quickSortRecursion(arrayList,0,arrayList.size());
    }
    private < T extends Comparable<T> > void quickSortRecursion(ArrayList array , int first,int last){
        if (first < last) {
            int pivotIndex = partition(array, first, last);
            quickSortRecursion(array, first, pivotIndex);
            quickSortRecursion(array, pivotIndex+1,last);
        }
    }
    private < T extends Comparable<T> > int partition( ArrayList<T> array , int start , int last ){
        int pivotIndex = (int) ( Math.random()  * (last - start - 1) ) + start;
        swap(array , start , pivotIndex);
        int lastLeast = start;

        for(int i =start+1 ;i<last;i++){
            if(array.get(i).compareTo(array.get(start))<0){
                ++lastLeast;
                swap(array,i,lastLeast);
            }
        }

        swap(array,start,lastLeast);
        return lastLeast;
    }

    @Override
    public void sortSlow(ArrayList arrayList) {
        if (arrayList==null) return;
        selectionSort(arrayList,0);
    }
    private < T extends Comparable<T> > void selectionSort( ArrayList<T> array , int startIndex){
        for(int i=startIndex ; i<array.size()-1;++i){
            int min = getTheMinInd(array,i);
            swap(array,i,min);
        }
    }
    private < T extends Comparable<T> > int getTheMinInd(ArrayList<T> array , int startIndex){
        int minimum = startIndex;
        for (int i=startIndex+1;i<array.size();i++) {
            if(array.get(i).compareTo( array.get(minimum)) < 0) {
                minimum= i;
            }
        }
        return minimum;
    }

    private  < T extends Comparable<T> > void swap (ArrayList<T> array , int indexA , int indexB ){
        T temp = array.get(indexA);
        array.set(indexA,array.get(indexB));
        array.set(indexB,temp);
    }

}
