package io.zipcode.rocks.dashamap;

public class DashaMapThree implements HashMapX {
    Node[] dNode = new Node[26];
    Integer size = 0;

    public String HashFunctionThree(String input) {
        if (input.length() > 1) {
            return (String.valueOf(input.charAt(0)).toLowerCase()) + String.valueOf(input.charAt(1)).toLowerCase();
        }
        return null;
    }

    @Override
    public void set(String key, Integer value) {
        String result = HashFunctionThree(key);
        char c = result.charAt(0);
        int index = c - 'a';

        Node newNode = new Node(key, value);
        Node head = dNode[index];
        if (head == null) {
            dNode[index] = newNode;
            size++;
        } else {
            while (head.next != null) {
                head = head.next;
            }
            head.next = newNode;
            size++;
        }
    }

    @Override
    public void delete(String key) {
        String hash = HashFunctionThree(key);
        Integer index = hash.charAt(0) - 'a';
        Node head = dNode[index];
        if (head.getKey().equals(key)) {
            dNode[index] = head.next;
            size--;
        }

        while (head.next != null) {
            if (head.next.getKey().equals(key)) {
                head.next = head.next.next;
                break;
            }
            head = head.next;
            size--;
        }
    }

    @Override
    public Integer get(String key) {
        String hash = HashFunctionThree(key);
        int hashKey = hash.charAt(0) - 'a';
        for (Node node = dNode[hashKey]; node != null; node = node.getNext()) {
            if (node.getKey().equals(key)) {
                return node.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        for (Node n : dNode) {
            if (n != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public long size() {
        return size;
    }

    @Override
    public Integer bucketSize(String key) {
        Integer bucket = 0;
        String hash = HashFunctionThree(key);
        Integer hashKey = hash.toCharArray()[0] - 'a';
        Node head = dNode[hashKey];
        while (head != null) {
            String check = head.getKey().substring(0, 2).toLowerCase();
            if (check.equals(hash)) {
                bucket++;
            }
            head = head.next;
        }
        return bucket;
    }
}
