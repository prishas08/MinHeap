public class MinHeap {
	
	private Integer[] heap;
	private int size;
	private static final int DEFAULT_CAPACITY = 8;
	
	public MinHeap()
	{
		this.size = 0;
		heap = new Integer[DEFAULT_CAPACITY];
	}
	
	public MinHeap(int size)
	{
		this.size = size;
		heap = new Integer[size];

	}
	
	public MinHeap(Integer... nums)
	{
		for(int i = 0; i <nums.length; i++)
		{
			insert(nums[i]);
		}
	}
	
	public int getSize()
	{
		return this.size;
	}
	
	public boolean isEmpty()
	{
		if(this.getSize()==0)
		{
			return true;
		}
		return false;
	}
	
	public int peekMinimum()
	{
		return heap[1];
	}
	
	public int getLeftChildIndex(int index)
	{
		if((index*2)>getSize())
		{
			return -1;
		}
		return 2*index;
	}
	
	public int getRightChildIndex(int index)
	{
		if((index*2+1)>getSize())
		{
			return -1;
		}
		return 2*index+1;
	}
	
	public int getParentIndex(int index)
	{
		if(index==getSize())
		{
			return index;
		}
		return index/2;
	}
	
	private void doubleCapacity()
	{
		this.size = this.size*2;
	}
	
	public void insert(int value)
	{
		if(size >= heap.length-1)
		{
			doubleCapacity();
		}
		size++;
		this.heap[0] = value;
		bubbleUp(getSize()-1);
		
	}

	private void bubbleUp(int index)
	{
		if(this.heap[getParentIndex(index)]<this.heap[index] || index ==1)
		{
			return;
		}
		else
		{
			if(heap[getParentIndex(index)]>heap[index])
			{
				int parent = heap[getParentIndex(index)];
				heap[getParentIndex(index)] = heap[index];
				heap[index] = parent;
				bubbleUp(getParentIndex(index));
			}
		}
	}
	
	public int popMinimum()
	{
		int min = peekMinimum();
		heap[1] = heap[size-1];
		siftDown(1);
		return min;
	}
	
	private void siftDown(int index)
	{
		if(getLeftChildIndex(index)==-1 && getRightChildIndex(index)==-1)
		{
			return;
		}
		else
		{
			int min = -1;
			if(getRightChildIndex(index)>getSize())
			{
				min = getLeftChildIndex(index);
			}
			else
			{
				if((heap[getLeftChildIndex(index)]) > (heap[getRightChildIndex(index)]))
				{
					min = getRightChildIndex(index);
					
				}
				
			}
			if(heap[index]>heap[min])
			{
				int temp = heap[index];
				heap[index] = heap[min];
				heap[min] = temp;
			}
			siftDown(min);
		}
	}
	
	@Override
	public String toString()
	{
		String output = "";
		for(int i = 1; i<=getSize(); i++)
		{
			output+= heap[i] + ", ";
		}
		return output.substring(0, output.lastIndexOf(","));
	}
	
	public void display()
	{
		int nBlanks = 32, itemsPerRow = 1, column = 0, j = 1;
		String dots = "...............................";
		System.out.println(dots + dots);
		while(j <= this.getSize())
		{
			if (column == 0)
			{
				for (int k = 0; k < nBlanks; k++)
				{
					System.out.print(' ');
				}
			}
				
			System.out.print((heap[j] == null)? "" : heap[j]);
				
			if (++column == itemsPerRow) 
			{
				nBlanks /= 2;
				itemsPerRow *= 2;
				column = 0;
				System.out.println();
			}
				
			else
			{
				for (int k = 0; k < nBlanks * 2-2; k++)
				{
					System.out.print(' ');
				}
			}
			j++;
		}
		System.out.println("\n" + dots + dots);
	}
}
