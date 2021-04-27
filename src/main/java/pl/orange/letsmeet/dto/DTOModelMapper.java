package pl.orange.letsmeet.dto;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.orange.letsmeet.model.Schedule;
import pl.orange.letsmeet.model.TimePeriod;

@Component
public final class DTOModelMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public Schedule toSchedule(ScheduleDTO scheduleDTO) {
        return modelMapper.map(scheduleDTO, Schedule.class);
    }

    public List<String[]> toMeetingsResponse(List<TimePeriod> meetingHours) {
        return meetingHours
                .stream().map(timePeriod -> new String[]{
                        timePeriod.getStart().toString(), timePeriod.getEnd().toString()
                }).collect(Collectors.toList());
    }
}
