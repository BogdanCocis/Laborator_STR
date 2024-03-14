public class Main {

    public static void main(String[] args) {

        Integer monitor = 1;
        Integer monitor1 = 1;

        new ExecutionThread(monitor, 2, 4, 3, 6).start();

        new ExecutionThread(monitor, 3, 5, 4, 7).start();
        new ExecutionThread1(monitor1, 2, 4, 3, 6).start();

        new ExecutionThread1(monitor1, 3, 5, 4, 7).start();
    }

}