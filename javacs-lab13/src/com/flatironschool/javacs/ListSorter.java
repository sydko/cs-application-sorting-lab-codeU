/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
        if (list.size() == 1){
        	return list;
        } else {
        	Integer mid = list.size() / 2; 

        	List<T> first = mergeSort(list.subList(0, mid), comparator);
        	List<T> second = mergeSort(list.subList(mid, list.size()), comparator);


        	Collections.sort(first, comparator);
        	Collections.sort(second, comparator);

        	return merge(first, second, comparator);
        }
	}

	private List<T> merge(List<T> first, List<T> second, Comparator<T> comparator){
		Integer i = 0, j = 0;
		List<T> newList = new ArrayList<T>();
		while(i < first.size() && j < second.size()){
			if (comparator.compare(first.get(i), second.get(j)) <= 0){
				newList.add(first.get(i));
				i ++;
			} else {
				newList.add(second.get(j));
				j ++;
			}
		}

		while( i < first.size()){
			newList.add(first.get(i));
			i ++;
		}
		while( j < second.size()){
			newList.add(second.get(j));
			j ++;
		}
	
		return newList;
	}


	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
        PriorityQueue heap = new PriorityQueue<T>();
		for (T item : list){
			heap.offer(item);
		}
		list.clear();
		while (heap.size() != 0){
			T item = (T)heap.poll();
			list.add(item);
		}
	}

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
        // FILL THIS IN!
        PriorityQueue heap = new PriorityQueue<T>();
        
        for (T x : list){
	        if (heap.size() < k){
				//Branch 1: If the heap is not full, add x to the heap.
	        	heap.offer(x);
	        } else {
	        	T root = (T) heap.peek();
	        	if (comparator.compare(x, root) >= 0){
	        		//Branch 3: If the heap is full and x is greater than the smallest element in the heap,
	        		//remove the smallest element from the heap and add x.
	        		heap.poll();
	        		heap.offer(x);

	        	}
	        		//Branch 2: If the heap is full, compare x to the smallest element in the heap.
		        	//If x is smaller, it cannot be one of the largest k elements, so you can discard it.
	        }

        }

		list.clear();
		while (heap.size() != 0){
			T item = (T)heap.poll();
			list.add(item);
		}
        return list;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
