import java.util.*;
import java.util.ListIterator;



public class DIYarrayList <T> implements List <T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] data;


    public DIYarrayList(int initialCapacity) {
        if(initialCapacity > 0) {
            this.data = (T[]) new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.data = (T[]) new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal capacity: " + initialCapacity);
        }
    }

    public DIYarrayList() {
        this.data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public String toString() {
        if (data == null)
            return "null";

        int iMax = data.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) {
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
    public Iterator<T> iterator() {
        return new Itr();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }


    @Override
    public boolean add(T e) {
        ensureCapacity(size + 1);
        data[size++] = e;
        return true;
    }
    private void ensureCapacity(int minCapacity) {
        if (minCapacity - data.length > 0) grow();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection <? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection <? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() { throw new UnsupportedOperationException(); }

    @Override
    public T get(int index) {
        checkIndex(index);
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return data[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        T oldValue = data[index];
        data[index] = element;
        return oldValue;
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index);
        ensureCapacity(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void grow() {
        int oldCapacity = data.length;
        int newCapacity = oldCapacity + (oldCapacity / 2);
        data = Arrays.copyOf(data, newCapacity);
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final Object[] es = data;
        T oldValue = data[index];

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
            return data[cursor++];
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
            return data[last];
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
    public ListIterator<T> listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        checkIndex(index);
        return new ListItr(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
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

