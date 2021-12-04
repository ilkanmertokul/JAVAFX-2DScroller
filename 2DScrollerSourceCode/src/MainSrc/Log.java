package MainSrc;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**Log class that logs given arguments. Counts line count too.
 * Uses queue to iterate through.
 * @author ilkan mert okul 1801042649*/
public class Log {

    /**Fixed maxLine that our queue holds.*/
    private final int maxLine = 10;

    /**This holds the total lines this log ever had.*/
    private int currentLine;

    /**Log's strings hold on this structure.*/
    private Queue<String> log = new ConcurrentLinkedQueue<String>();

    Log(){
        currentLine = 0;
        log.offer((currentLine++) + ": The game has started.\n");
    }

    /**Logs the jump mode change.*/
    public void logJumpChange(boolean isBigJump){
        if(isBigJump) this.logMessage("Changed small jump to big jump.");
        else this.logMessage("Changed big jump to small jump.");
    }

    /**logMessage method logs the given string to queue.
     * @param logMessage given message to save.*/
    public void logMessage(String logMessage){
        if(log.size()>maxLine) log.poll(); //To keep log in nice size.
        log.offer((currentLine++) + ": "+ logMessage + "\n");
    }

    /**toString method to get log as string.
     * @return String classic toString() format.*/
    public String toString(){
        return log.toString();
    }
}
