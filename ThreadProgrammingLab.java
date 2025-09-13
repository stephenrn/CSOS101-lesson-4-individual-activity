import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Thread Programming Mini-Lab
 * 
 * This program demonstrates multithreading by spawning 3 different threads,
 * each performing a unique task, and using synchronization to wait for
 * all threads to complete before the main program exits.
 */
public class ThreadProgrammingLab {
    
    /**
     * Thread that prints numbers from 1 to 10
     */
    static class NumberPrinterThread extends Thread {
        @Override
        public void run() {
            System.out.println("NumberPrinterThread started");
            for (int i = 1; i <= 10; i++) {
                System.out.println("Number: " + i);
                try {
                    Thread.sleep(500); // Sleep for 500ms between numbers
                } catch (InterruptedException e) {
                    System.out.println("NumberPrinterThread interrupted");
                    return;
                }
            }
            System.out.println("NumberPrinterThread finished");
        }
    }
    
    /**
     * Thread that prints letters from A to J
     */
    static class LetterPrinterThread extends Thread {
        @Override
        public void run() {
            System.out.println("LetterPrinterThread started");
            for (char c = 'A'; c <= 'J'; c++) {
                System.out.println("Letter: " + c);
                try {
                    Thread.sleep(750); // Sleep for 750ms between letters
                } catch (InterruptedException e) {
                    System.out.println("LetterPrinterThread interrupted");
                    return;
                }
            }
            System.out.println("LetterPrinterThread finished");
        }
    }
    
    /**
     * Thread that prints current time every second for 8 iterations
     */
    static class TimePrinterThread extends Thread {
        @Override
        public void run() {
            System.out.println("TimePrinterThread started");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            for (int i = 1; i <= 8; i++) {
                LocalDateTime now = LocalDateTime.now();
                System.out.println("Time: " + now.format(formatter));
                try {
                    Thread.sleep(1000); // Sleep for 1 second between time prints
                } catch (InterruptedException e) {
                    System.out.println("TimePrinterThread interrupted");
                    return;
                }
            }
            System.out.println("TimePrinterThread finished");
        }
    }
    
    /**
     * Main method that creates and manages the three threads
     */
    public static void main(String[] args) {
        System.out.println("Thread Programming Mini-Lab Starting...");
        System.out.println("==========================================");
        
        // Create the three threads
        NumberPrinterThread numberThread = new NumberPrinterThread();
        LetterPrinterThread letterThread = new LetterPrinterThread();
        TimePrinterThread timeThread = new TimePrinterThread();
        
        // Start all threads
        System.out.println("Starting all threads...");
        numberThread.start();
        letterThread.start();
        timeThread.start();
        
        try {
            // Wait for all threads to complete using join()
            System.out.println("Main thread waiting for all threads to complete...");
            numberThread.join();
            letterThread.join();
            timeThread.join();
            
            System.out.println("==========================================");
            System.out.println("All threads have completed successfully!");
            System.out.println("Main program exiting...");
            
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted while waiting for threads to complete");
            e.printStackTrace();
        }
    }
}