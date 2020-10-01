package org.princeton.sedgewick.wayne.week2.containers.stack.array;

public class ResizableGenericStack<Item> {

    private int N;
    private Item[] arr;

    public ResizableGenericStack() {
        arr = (Item[]) new Object[4];
    }

    public void push(Item str) {
        if (arr.length == N)
            resize(N * 2);

        arr[N++] = str;
    }

    private void resize(int newLength) {
        System.out.println("Resize to capacity: " + newLength);
        Item[] temp = (Item[]) new Object[newLength];
        System.arraycopy(arr, 0, temp, 0, N);
        arr = temp;
    }

    public Item pop() {
        Item item = arr[--N];
        arr[N] = null;

        if (N > 0 && N == arr.length / 4)
            resize(arr.length / 2);

        return item;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int getSize() {
        return N;
    }
}

class TestResizableFixedCapacityStack {
    public static void main(String[] args) {
        ResizableGenericStack<Integer> stack = new ResizableGenericStack<>();
        stack.push(10);
        stack.push(9);
        stack.push(8);
        stack.push(7);
        stack.push(6);
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        //Resize to capacity: 8
        //Resize to capacity: 16

        System.out.println(stack.pop()); // 1
        System.out.println(stack.pop()); // 2
        System.out.println(stack.pop()); // 3
        System.out.println(stack.pop()); // 4
        System.out.println(stack.pop()); // 5
        //Resize to capacity: 8
        System.out.println(stack.pop()); // 6
        System.out.println(stack.pop()); // 7
        //Resize to capacity: 4
        System.out.println(stack.pop()); // 8
        //Resize to capacity: 2
        System.out.println(stack.pop()); // 9
        System.out.println(stack.pop()); // 10
    }
}
