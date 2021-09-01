import java.util.ArrayDeque;
import java.util.NoSuchElementException;

/**
 * Двунаправленная очередь, реализует интерфейс CustomArrayDeque<T>,
 * может хранить объекты любого типа.
 */
public class CustomArrayDequeImpl<T> implements CustomArrayDeque<T> {

    private static final int DEFAULT_CAPACITY = 8;
    private Object[] data = new Object[DEFAULT_CAPACITY];
    int head;
    int tail;
    int size;

    /**
     * Конструктор пустого объекта.
     */
    CustomArrayDequeImpl(){}

    /**
     * Конструктор объекта с заданной capacity.
     */
    CustomArrayDequeImpl(int capacity) {
        data = new Object[capacity + 1];
        head = 0;
        tail = 0;
        size = 0;
    }

    /**
     * Возвращает размер очереди
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Проверяет очередь на наличие объектов
     */
    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * Добавляет объект в конец очереди.
     */
    @Override
    public boolean add(T item) {
        if (isFull()) {
            throw new IllegalStateException("Очередь заполнена!");
        }
        addLast(item);

        return true;
    }

    /**
     * Добавляет объект в начало очереди.
     */
    @Override
    public void addFirst(T item) {
        if (isFull()) {
            throw new IllegalStateException("Очередь заполнена!");
        }
        if (head - 1 > 0) {
            head--;
        } else {
            head = data.length - 1;
        }
        data[head] = item;
        size++;
    }

    /**
     * Добавляет объект в конец очереди.
     */
    @Override
    public void addLast(T item) {
        if (isFull()) {
            throw new IllegalStateException("Очередь заполнена!");
        }
        data[tail] = item;
        size++;
        tail++;
        if (tail >= data.length) {
            tail = 0;
        }
    }

    /**
     * Добавляет объект в начало очереди.
     */
    @Override
    public boolean offerFirst(T item) {
        if (isFull()) {
            return false;
        }
        addFirst(item);

        return true;
    }

    /**
     * Добавляет объект в конец очереди.
     */
    @Override
    public boolean offerLast(T item) {
        if (isFull()) {
            return false;
        }
        addLast(item);

        return true;
    }

    /**
     * Возвращает объект из начала очереди.
     */
    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Очередь пустая!");
        }

        return (T) data[head];
    }

    /**
     * Возвращает объект из конца очереди.
     */
    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Очередь пустая!");
        }
        int tmp = tail -1;
        if (tmp < 0) {
            tmp = data.length - 1;
        }

        return (T) data[tmp];
    }

    /**
     * Возвращает объект из начала очереди.
     */
    @Override
    public T peekFirst() {
        if (isEmpty()) {
            return null;
        }

        return getFirst();
    }

    /**
     * Возвращает объект из конца очереди.
     */
    @Override
    public T peekLast() {
        if (isEmpty()) {
            return null;
        }

        return getLast();
    }

    /**
     * Удаляет объект из начала очереди.
     */
    @Override
    public T pollFirst() {
        if (isEmpty()) {
            return null;
        }
        T tmp = (T) data[head];
        data[head] = null;
        head++;
        size--;
        if (head == data.length) {
            head = 0;
        }

        return tmp;
    }

    /**
     * Удаляет объект из конца очереди.
     */
    @Override
    public T pollLast() {
        if (isEmpty()) {
            return null;
        }
        T tmp = (T) data[tail - 1];
        data[tail - 1] = null;
        tail--;
        size--;
        if (tail == data.length) {
            tail = 0;
        }

        return tmp;
    }

    /**
     * Удаляет объект из начала очереди.
     */
    @Override
    public T removeFirst() {
        T tmp = pollFirst();
        if (tmp == null)
            throw new NoSuchElementException("Очередь пустая!");

        return tmp;
    }

    /**
     * Удаляет объект из конца очереди.
     */
    @Override
    public T removeLast() {
        T tmp = pollLast();
        if (tmp == null)
            throw new NoSuchElementException("Очередь пустая!");

        return tmp;
    }

    /**
     * Возвращает true, если очередь заполнена.
     */
    private boolean isFull() {
        if (head == 0 && tail == data.length - 1)
            return true;

        if (head == tail + 1)
            return true;

        return false;
    }

    @Override
    public String toString() {
        StringBuilder cb = new StringBuilder();
        cb.append("[");
        int tmp = head;
        while (tmp != tail) {
            cb.append(" " + data[tmp]);
            tmp++;
            if (tmp == data.length) {
                tmp = 0;
            }
        }
        cb.append(" ]");

        return cb.toString();
    }

    /**
     * Возвращает объекты очереди в виде массива.
     */
    @Override
    public Object[] toArray() {
        Object[] newData = new Object[size];
        int tmp = head;
        for (int i = 0; i < size; i++) {
            newData[i] = data[tmp++];
            if (tmp == data.length) {
                tmp = 0;
            }
        }

        return newData;
    }
}
