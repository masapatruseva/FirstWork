import java.util.*;
import java.util.ListIterator;



public class DIYarrayList <T> implements List <T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] data;


    public DIYarrayList(int initialCapacity) {
        if(initialCapacity > 0) {
            this.data = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
        this.data = new Object[]{};
        }else {
            throw new IllegalArgumentException("Illegal capacity: " + initialCapacity);
        }
    }

    public DIYarrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size(); i++) {
            T element = get(i);
            if (element != null) {
                sb.append(element);
            }
            if (i < size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        return new Itr();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }


    @Override
    public boolean add(T e) {
        if ((size + 1) - data.length > 0) {
            grow();
        }
        data[size++] = e;
        return true;
    }


    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() { throw new UnsupportedOperationException(); }

    @Override
    public T get(int index) {
        if (index < 0 || index >= data.length)
            throw new IndexOutOfBoundsException();
        return (T) data[index];
    }

    @Override
    public T set(int index, T element) {
        Objects.checkIndex(index, size);
        T oldValue = (T) data[index];
        data[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, T element) {
        if (size == data.length) {
            grow();
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    private void grow() {
        int oldCapacity = data.length;
        int newCapacity = oldCapacity + (oldCapacity / 2);
        data = Arrays.copyOf(data, newCapacity);
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        final Object[] es = data;
        T oldValue = (T) data[index];

        final int newSize;
        if ((newSize = size - 1) > index)
            System.arraycopy(es, index + 1, es, index, newSize - index);
        es[size = newSize] = null;

        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    private class Itr implements Iterator<T> {
        int cursor;
        int last = -1;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            if (cursor >= size) {
                throw new NoSuchElementException();
            }
            last = cursor;
            return (T) data[cursor++];
        }

        @Override
        public void remove() {
            if (last < 0)
                throw new IllegalStateException();

            try {
                DIYarrayList.this.remove(last);
                cursor = last;
                last = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }
    
    private class ListItr extends Itr implements ListIterator<T> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public T previous() {
            if (cursor <= 0) {
                throw new NoSuchElementException();
            }
            last = --cursor;
            return (T) data[last];
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void set(T t) {
            if (last < 0) {
                throw new IllegalStateException();
            }
            try {
                DIYarrayList.this.set(last, t);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void add(T t) {
            try {
                DIYarrayList.this.add(cursor, t);
                cursor += 1;
                last = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }


    @Override
    public ListIterator listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator listIterator(int index) {
        return new ListItr(index);
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException();
    }



}

