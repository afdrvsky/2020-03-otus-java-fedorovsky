import java.util.*;

public class DIYarrayList<E> implements List<E> {

    private int defaultCapacity = 10;
    private int stepToIncCapacity;
    private int size;
    private Object[] elementData;

    public DIYarrayList() {
        this.elementData = new Object[defaultCapacity];
    }

    public DIYarrayList(int initialCapacity) {
        this.elementData = new Object[initialCapacity];
    }

    public DIYarrayList(int initialCapacity, int defaultCapacity) {
        this.elementData = new Object[initialCapacity];
        this.stepToIncCapacity = defaultCapacity + 10;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length < size)
            return (E[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(E t) {
        checkAdequacyCapacity(size + 1);
        elementData[size++] = t;
        return true;
    }

    private void checkAdequacyCapacity(int requiredCapacity) {
        if (requiredCapacity > elementData.length) {
            elementData = Arrays.copyOf(elementData, requiredCapacity + stepToIncCapacity);
        }
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAllExtended(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkRange(index);

        return addAllExtended(index, c);
    }

    private void checkRange(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public boolean addAllExtended(int index, Collection<? extends E> c) {
        if (c.size() > 0) {
            int oldSize = elementData.length;
            Object[] src = c.toArray();
            size = oldSize + src.length;
            checkAdequacyCapacity(size);
            if (index < oldSize) {
                System.arraycopy(elementData, index, elementData, index + src.length, oldSize - index);
            }
            System.arraycopy(src, 0, elementData, index, src.length);

            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E get(int index) {
        checkRange(index);
        return (E) elementData[index];
    }

    @Override
    public E set(int index, E element) {
        checkRange(index);
        elementData[index] = element;
        return element;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        checkRange(index);

        return new ListIterator<E>() {
            int position = index;
            int lastElementReturned = -1;

            @Override
            public boolean hasNext() {
                return position < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lastElementReturned = position++;
                return (E) elementData[lastElementReturned];
            }

            @Override
            public boolean hasPrevious() {
                return position > 0;
            }

            @Override
            public E previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                lastElementReturned = --position;
                return (E) elementData[lastElementReturned];
            }

            @Override
            public int nextIndex() {
                return position;
            }

            @Override
            public int previousIndex() {
                return position - 1;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(E e) {
                if (lastElementReturned < 0) {
                    throw new IllegalStateException();
                }
                DIYarrayList.this.set(lastElementReturned, e);
            }

            @Override
            public void add(E e) {
                DIYarrayList.this.add(e);
            }
        };
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
