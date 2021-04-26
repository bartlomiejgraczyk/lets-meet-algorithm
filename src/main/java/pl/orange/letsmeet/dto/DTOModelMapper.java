package pl.orange.letsmeet.dto;

import org.modelmapper.ModelMapper;
import pl.orange.letsmeet.model.Schedule;
import pl.orange.letsmeet.model.TimePeriod;

public final class DTOModelMapper {
    
    private static final ModelMapper modelMapper = new ModelMapper();
    private static DTOModelMapper instance;

    private DTOModelMapper() {
    }
    
    public static DTOModelMapper getInstance() {
        DTOModelMapper result = instance;

        if (result != null) {
            return result;
        }
        
        synchronized (DTOModelMapper.class) {
            if (instance == null) {
                instance = new DTOModelMapper();
            }
            return instance;
        }
    }
    
    public TimePeriod toTimePeriod(TimePeriodDTO timePeriodDTO) {
        return modelMapper.map(timePeriodDTO, TimePeriod.class);
    }

    public TimePeriodDTO toTimePeriodDTO(TimePeriod timePeriod) {
        return modelMapper.map(timePeriod, TimePeriodDTO.class);
    }

    public Schedule toSchedule(ScheduleDTO scheduleDTO) {
        return modelMapper.map(scheduleDTO, Schedule.class);
    }
}
