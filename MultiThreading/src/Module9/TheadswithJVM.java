package Module9;

public class TheadswithJVM {

    //In below example we can see the main thread and created thread execute the code in the for loop and then sleep for 1 sec and so on.
    // Using Visual VM, we can run the code directly from Intellij and it integrates with it
    //We can also see the Thread Dump in Threads window to see the state of Threads and stacktrace
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            double var =1;
            while (true){
                for (int i = 0; i < 100000000; i++) {
                    var=Math.tan(var);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.setName("new-thread");
        thread.start();
//        Thread.sleep(1000000000);
        double var =1;
        while (true){
            for (int i = 0; i < 100000000; i++) {
                var=Math.tan(var);
            }
            Thread.sleep(1000);
        }
    }
}
