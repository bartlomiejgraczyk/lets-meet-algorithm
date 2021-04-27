package pl.orange.letsmeet.controller;

import java.time.Duration;
import java.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.orange.letsmeet.dto.DTOModelMapper;
import pl.orange.letsmeet.dto.GetMeetingHoursRequest;
import pl.orange.letsmeet.exception.InvalidScheduleContentException;
import pl.orange.letsmeet.model.Schedule;
import pl.orange.letsmeet.service.ScheduleService;

@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final DTOModelMapper dtoMapper;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, DTOModelMapper dtoMapper) {
        this.scheduleService = scheduleService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping(value = "possible-meetings")
    public ResponseEntity<?> getPossibleMeetingHours(
            @RequestBody GetMeetingHoursRequest request,
            @RequestParam(name = "meeting-duration") LocalTime meetingDuration) {

        Schedule firstSchedule = dtoMapper.toSchedule(request.getFirstSchedule());
        Schedule secondSchedule = dtoMapper.toSchedule(request.getSecondSchedule());
        Duration minMeetingDuration = Duration.between(LocalTime.MIN, meetingDuration);

        try {
            return ResponseEntity.ok().body(dtoMapper.toMeetingsResponse(scheduleService
                    .getPossibleMeetingHours(firstSchedule, secondSchedule, minMeetingDuration))

            );
        } catch (InvalidScheduleContentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
