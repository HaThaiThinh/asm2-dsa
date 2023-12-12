import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MAX_MESSAGE_LENGTH = 250;

    public Main() {
    }

    public static void main(String[] args) {
        CustomQueue messageQueue = new CustomQueue(250);
        CustomStack messageStack = new CustomStack(250);
        boolean exit = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (!exit) {
            System.out.println("\n----- Menu -----");
            System.out.println("1. Input message");
            System.out.println("2. Show all messages");
            System.out.println("3. Send Message");
            System.out.println("4. Received message / View message");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException var7) {
                System.out.println("Invalid input. Please enter a valid choice.");
                continue;
            }

            switch (choice) {
                case 1:
                    enqueueStringToQueue(messageQueue, reader);
                    break;
                case 2:
                    showAllMessagesInQueue(messageQueue);
                    break;
                case 3:
                    dequeueToStack(messageQueue, messageStack);
                    System.out.println("send success!");
                    break;
                case 4:
                    System.out.println("Messages in the stack:");
                    printMessagesInStack(messageStack);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Exiting the program.");
    }

    private static void enqueueStringToQueue(CustomQueue queue, BufferedReader reader) {
        System.out.print("Enter the string (less than 250 characters): ");

        try {
            String input = reader.readLine();
            if (input.length() <= 250) {
                queue.enqueue(input);
                System.out.println("String enqueued successfully!");
            } else {
                System.out.println("String length exceeds 250 characters. Please try again.");
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        }
    }

    private static void showAllMessagesInQueue(CustomQueue queue) {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty. No messages to show.");
        } else {
            System.out.println("Messages in the queue:");
            for (int i = 0; i < queue.size(); ++i) {
                System.out.println(queue.get(i));
            }
        }
    }

    private static void dequeueToStack(CustomQueue queue, CustomStack stack) {
        while (!queue.isEmpty()) {
            String message = queue.dequeue();
            stack.push(message);
        }
    }

    private static void printMessagesInStack(CustomStack stack) {
        while (!stack.isEmpty()) {
            String message = stack.pop();
            System.out.println("Received message: " + message);
        }
    }

    static class CustomQueue {
        private String[] array;
        private int front;
        private int rear;
        private int capacity;
        private int size;

        public CustomQueue(int capacity) {
            this.capacity = capacity;
            this.array = new String[capacity];
            this.front = 0;
            this.rear = -1;
            this.size = 0;
        }

        public void enqueue(String element) {
            if (this.size == this.capacity) {
                System.out.println("Queue is full. Cannot enqueue.");
            } else {
                this.rear = (this.rear + 1) % this.capacity;
                this.array[this.rear] = element;
                ++this.size;
            }
        }

        public String dequeue() {
            if (this.isEmpty()) {
                System.out.println("Queue is empty. Cannot dequeue.");
                return null;
            } else {
                String element = this.array[this.front];
                this.front = (this.front + 1) % this.capacity;
                --this.size;
                return element;
            }
        }

        public String get(int index) {
            if (index >= 0 && index < this.size) {
                return this.array[(this.front + index) % this.capacity];
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public int size() {
            return this.size;
        }
    }

    static class CustomStack {
        private String[] array;
        private int top;
        private int capacity;
        private int size;

        public CustomStack(int capacity) {
            this.capacity = capacity;
            this.array = new String[capacity];
            this.top = -1;
            this.size = 0;
        }

        public void push(String element) {
            if (this.size == this.capacity) {
                System.out.println("Stack is full. Cannot push.");
            } else {
                ++this.top;
                this.array[this.top] = element;
                ++this.size;
            }
        }

        public String pop() {
            if (this.isEmpty()) {
                System.out.println("Stack is empty. Cannot pop.");
                return null;
            } else {
                String element = this.array[this.top];
                --this.top;
                --this.size;
                return element;
            }
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public int size() {
            return this.size;
        }
    }
}
