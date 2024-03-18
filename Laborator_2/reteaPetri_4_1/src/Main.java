public class Main {

    public static void main(String[] args) {

        Integer monitor = 1;

        new ExecutionThread(monitor, 2, 4, 3, 6).start();

        new ExecutionThread(monitor, 3, 5, 4, 7).start();

    }

}