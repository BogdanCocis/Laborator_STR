public class Main {
    public static void main(String[] args) {
        Integer monitor1 = 1;
        Integer monitor2 = 1;
        new ExecutionThread(monitor1, 2, 4, 0, 4).start();
        new ExecutionThread(monitor2, 2, 5, 0, 5).start();
        new ExecutionThread1(monitor1,monitor2,3,6,0,3).start();
}
}