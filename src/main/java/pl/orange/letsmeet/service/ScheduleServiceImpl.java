package pl.orange.letsmeet.service;

import java.time.Duration;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import pl.orange.letsmeet.exception.InvalidScheduleContentException;
import pl.orange.letsmeet.model.Schedule;
import pl.orange.letsmeet.model.TimePeriod;
import pl.orange.letsmeet.util.ScheduleValidator;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    
    @Override
    public List<TimePeriod> getPossibleMeetingHours(Schedule firstSchedule, Schedule secondSchedule, Duration meetingDuration) throws InvalidScheduleContentException {
        if ((!ScheduleValidator.isScheduleValid(firstSchedule)) || (!ScheduleValidator.isScheduleValid(secondSchedule))) {
            throw new InvalidScheduleContentException(InvalidScheduleContentException.INVALID_SCHEDULE_CONTENT);
        }
        List<TimePeriod> firstScheduleSlots = findScheduleFreeSlots(firstSchedule, meetingDuration);
        List<TimePeriod> secondScheduleSlots = findScheduleFreeSlots(secondSchedule, meetingDuration);
        
        return findPossibleMeetingSlots(firstScheduleSlots, secondScheduleSlots, meetingDuration);
    }
    
    private List<TimePeriod> findScheduleFreeSlots(Schedule schedule, Duration meetingDuration) {
        final List<TimePeriod> meetings = schedule.getPlannedMeetings().stream().sorted().collect(Collectors.toList());
        final List<TimePeriod> freeSlots = new LinkedList<>();
        
        final TimePeriod afterWorkDayStartSlot = new TimePeriod(schedule.getWorkingHours().getStart(), meetings.get(0).getStart());
        final TimePeriod beforeWorkDayEndSlot = new TimePeriod(meetings.get(meetings.size() - 1).getEnd(), schedule.getWorkingHours().getEnd());
        
        checkSinglePeriod(afterWorkDayStartSlot, meetingDuration, freeSlots);
        
        for(int i = 0; i < meetings.size() - 1; i++) {
            TimePeriod betweenMeetingsSlot = new TimePeriod(meetings.get(i).getEnd(), meetings.get(i + 1).getStart());
            checkSinglePeriod(betweenMeetingsSlot, meetingDuration, freeSlots);
        }
        
        checkSinglePeriod(beforeWorkDayEndSlot, meetingDuration, freeSlots);
        
        return freeSlots;
    }
    
    private void checkSinglePeriod(TimePeriod timePeriod, Duration minDuration, List<TimePeriod> freeSlots) {
        if (calculateTimePeriodDuration(timePeriod).compareTo(minDuration) >= 0) {
            freeSlots.add(timePeriod);
        }
    }
    
    private Duration calculateTimePeriodDuration(TimePeriod timePeriod) {
        return Duration.between(timePeriod.getStart(), timePeriod.getEnd());
    }
    
    private List<TimePeriod> findPossibleMeetingSlots(
            List<TimePeriod> firstScheduleSlots, 
            List<TimePeriod> secondScheduleSlots, 
            Duration meetingDuration
    ) {

        final List<TimePeriod> possibleMeetingSlots = new LinkedList<>();
        final List<TimePeriod> allSlotsCombined = new LinkedList<>(firstScheduleSlots);
        allSlotsCombined.addAll(secondScheduleSlots);
        Collections.sort(allSlotsCombined);
        
        for (int i = 0; i < allSlotsCombined.size() - 1; i++) {
            TimePeriod betweenMeetingsSlot = 
                    new TimePeriod(allSlotsCombined.get(i + 1).getStart(), allSlotsCombined.get(i).getEnd());
            checkSinglePeriod(betweenMeetingsSlot, meetingDuration, possibleMeetingSlots);
        }
        
        return possibleMeetingSlots;
    }
}
