package pl.orange.letsmeet.entities;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Schedule implements Serializable {
    
    @Getter
    @Setter
    private WorkingHours workingHours;
    
    @Getter
    private final List<Meeting> plannedMeetings;

    public Schedule(WorkingHours workingHours, List<Meeting> plannedMeetings) {
        this.workingHours = workingHours;
        this.plannedMeetings = plannedMeetings;
    }
}
