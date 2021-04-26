package pl.orange.letsmeet.dto;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.orange.letsmeet.model.Schedule;
import pl.orange.letsmeet.model.TimePeriod;

@Component
public final class DTOModelMapper {
    
    private static final ModelMapper modelMapper = new ModelMapper();
    
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
