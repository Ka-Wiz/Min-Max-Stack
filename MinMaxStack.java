import common.EmptyCollectionException;
import common.CollectionADT;

interface StackADT<T> extends CollectionADT {
	public T pop();
	public T peek();
	public void push(T newItem);
	public boolean isEmpty();
}

public class MinMaxStack <T extends Comparable<? super T>> implements StackADT<T>
{
	private class StackNode
	{
		T item;
		StackNode next, nextExtreme;
		boolean extremeIsMin;
		
		StackNode(T t) { item = t; }
	}
	
	StackNode top, min, max;
	int size = 0;
	
	public T getMin()
	{
		if(top == null)
			throw new EmptyCollectionException();
		
		return min.item;
	}
	
	public T getMax()
	{
		if(top == null)
			throw new EmptyCollectionException();
		
		return max.item;
	}
	
	@Override
	public T pop()
	{
		if(top == null)
			throw new EmptyCollectionException();
		
		if(top.nextExtreme != null)
			if(top.extremeIsMin)
				min = top.nextExtreme;
			else
				max = top.nextExtreme;
		
		T t = top.item;
		top = top.next;
		--size;
		
		return t;
	}

	@Override
	public T peek()
	{
		if(top == null)
			throw new EmptyCollectionException();
		
		return top.item;
	}

	@Override
	public void push(T newItem)
	{
		StackNode n = new StackNode(newItem);
		
		if(top != null)
		{
			n.next = top;
			
			if(newItem.compareTo(min.item) <= 0)
			{
				n.nextExtreme = min;
				n.extremeIsMin = true;
				min = n;
			}
			else if(newItem.compareTo(max.item) >= 0)
			{
				n.nextExtreme = max;
				n.extremeIsMin = false;
				max = n;
			}
				
		}
		else
			min = max = n;
		
		top = n;
		++size;
	}

	@Override
	public int getSize() { return size; }

	@Override
	public void clear() { top = null; }

	@Override
	public boolean isEmpty() { return top == null; }
}



