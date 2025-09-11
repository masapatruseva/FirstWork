package gc;

import java.util.ArrayList;
import java.util.List;
/*
-Xms256m
-Xmx256m

-Xms32g
-Xmx32g
-Xlog:gc=debug:file=C:/Users/1/Desktop/HOMEWORK/FirstWork/hw05/logs/gc-%p-%t.log:tags,uptime,time,level:filecount=5,filesize=10m
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=C:/Users/1/Desktop/HOMEWORK/FirstWork/hw05/logs
1. -XX:+UseSerialGC
2. -XX:+UseParallelGC
3. -XX:+UseG1GC
*/
public class TestGC {
    private static List<byte[]> memory = new ArrayList<>();

    public static void main(String[] args) {
        int count = 0;

        while(true) {
            if(count % 2 == 0) {
                memory.add(new byte[1024 * 1024 * 2]);
            }
            if(!memory.isEmpty() && count % 4 == 0) {
                memory.remove(memory.size() - 1);
            }

            try {
                Thread.sleep(600);  //для 256мб
                //Thread.sleep(5);       //для 32гб
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            count++;

            if (count % 100 == 0) {
                System.out.println("Iteration: " + count);
            }
        }

    }
}
