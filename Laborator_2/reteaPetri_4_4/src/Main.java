

public class Main {
    public static void main(String[] args) {

        Integer monitor1 = 2;
        Integer monitor2 = 2;

        new ExecutionThread(monitor1,monitor2, 4, 4, 4, 6).start();

        new ExecutionThread(monitor2,monitor1, 5, 5, 5, 7).start();

    }
}








