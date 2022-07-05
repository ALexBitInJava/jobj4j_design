import java.util.Iterator;

public class LinkedList {
    public static void main(String[] args) {
        SingleLinkedList<Contact> contactList = new SingleLinkedList<>();
        contactList.addToEnd(new Contact(1, "Alex1", "8989891"));
        contactList.addToEnd(new Contact(1, "Alex2", "8989892"));
        contactList.addToEnd(new Contact(1, "Alex3", "8989893"));
        contactList.addToEnd(new Contact(1, "Alex4", "8989894"));
        contactList.addToEnd(new Contact(1, "Alex5", "8989895"));

        for (Contact c : contactList) {
            System.out.println(c);
        }

        contactList.revers();
        System.out.println("--------------");

        for (Contact c : contactList) {
            System.out.println(c);
        }
    }

    static class Contact {
        int id;
        String name;
        String phone;

        public Contact(int id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Contact{" + "id=" + id + ", name='" + name + '\'' + ", phone='" + phone + '\'' + '}';
        }
    }

    public static class SingleLinkedList<T> implements Iterable<T> {

        ListItem<T> head;
        ListItem<T> tail;

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                ListItem<T> current = head;
                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public T next() {
                    T data = current.data;
                    current = current.next;
                    return data;
                }
            };
        }

        private static class ListItem<T> {
            T data;
            ListItem<T> next;
        }

        public boolean isEmpty() {
            return head == null;
        }

        private void addToEnd(T item) {
            ListItem<T> newItem = new ListItem<>();
            newItem.data = item;
            if (isEmpty()) {
                head = newItem;
                tail = newItem;
            } else {
                tail.next = newItem;
                tail = newItem;
            }
        }

        public void revers() {
            if (head.next != null && !isEmpty()) {
                tail = head;
                ListItem<T> current = head.next;
                head.next = null;
                while (current != null) {
                    ListItem<T> next = current.next;
                    current.next = head;
                    head = current;
                    current = next;
                }
            }
        }
    }
}
