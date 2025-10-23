package cattery;

import java.time.LocalTime;
import java.util.List;

public class Cattery {
    private int count;
    private String catteryName;
    private boolean isOpen;
    private List<Cat> cats;

    public Cattery(String catteryName, List<Cat> cats) {
        this.catteryName = catteryName;
        this.isOpen = isOpen();
        this.cats = cats;
        count = cats.size();
    }
    private boolean isOpen() {
        LocalTime now = LocalTime.now();
        return !now.isBefore(LocalTime.of(9,0)) && !now.isAfter(LocalTime.of(18, 0));
    }

    public String getCatteryName() {
        return catteryName;
    }

    public int getCount() {
        return count;
    }
}
