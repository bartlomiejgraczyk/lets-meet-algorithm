package pl.orange.letsmeet.entities;

import java.io.Serializable;
import java.time.LocalTime;
import lombok.Data;

@Data
public abstract class TimePeriod implements Serializable {
    
    protected LocalTime start;
    protected LocalTime end;
}
