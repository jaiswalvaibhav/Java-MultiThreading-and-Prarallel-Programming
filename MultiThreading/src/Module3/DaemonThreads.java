package Module3;

public class DaemonThreads {

    public static void main(String[] args) throws InterruptedException {
        //User Thread vs Daemon Thread
        /*

        Thread thread = new Thread(()-> System.out.println("MyThread"));
        //User Thread: This is a user created thread
        //JVM considers this thread as high priority
        //It waits for all user level threads to end (either via exception or normally) before exiting

        thread.setDaemon(true);//Daemon thread
        //Low priority thread
        //This thread doesn't ned to complete when JVM ends

         */

        Thread thread1 = new Thread(new MyThread(5), "Thread1");
        Thread thread2 = new Thread(new MyThread(2), "Thread2");

        thread1.setDaemon(true);

        thread1.start();
        thread2.start();

        /*
        Above code stops the JVM after 3 secs only coz it doesn't wait for Daemon threads to end. Output:
            Sleeping for 1sec, thread: Thread2
            Sleeping for 1sec, thread: Thread1
            Sleeping for 1sec, thread: Thread1
            Sleeping for 1sec, thread: Thread2
            Sleeping for 1sec, thread: Thread1
         */

        //A Glitch in JVM api
        /*
        The join operation on the daemon thread makes the JVM wait for the Daemon thread to end which shouldn't be the case coz it's a Daemon thread
        So, eg of a Daemon Thread is Garbage collector of an application doesn't end even if JVM stops.
        But if we use join on the garbage collector, then it JVm waits for it to end.
         */

        thread1.join();

        /*
        Above code output with join:
            Sleeping for 1sec, thread: Thread2
            Sleeping for 1sec, thread: Thread1
            Sleeping for 1sec, thread: Thread2
            Sleeping for 1sec, thread: Thread1
            Sleeping for 1sec, thread: Thread1
            Sleeping for 1sec, thread: Thread1
            Sleeping for 1sec, thread: Thread1
         */
    }

    //Class to make a thread wit for a configurable numberOfSeconds
    static class MyThread implements Runnable{
        private final int numberOfSeconds;

        private MyThread(int numberOfSeconds){
            this.numberOfSeconds=numberOfSeconds;
        }

        @Override
        public void run(){
            for (int i = 0; i < numberOfSeconds; i++) {
                try {
                    System.out.println("Sleeping for 1 sec, thread: "+Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
