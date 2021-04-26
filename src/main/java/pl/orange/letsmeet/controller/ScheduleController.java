package pl.orange.letsmeet.controller;

import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.orange.letsmeet.dto.GetMeetingHoursRequest;
import pl.orange.letsmeet.model.Schedule;
import pl.orange.letsmeet.model.TimePeriod;
import pl.orange.letsmeet.service.ScheduleService;

@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
    
//    @GetMapping("hello")
//    public Schedule hello() {
//        return new Schedule(new TimePeriod("09:00", "16:00"), List.of(new TimePeriod("10:30", "11:00"), new TimePeriod("12:00", "14:00")));
//    }
    
    @PostMapping("possible-meetings")
    public ResponseEntity<?> getPossibleMeetingHours(
            @RequestBody GetMeetingHoursRequest request, 
            @RequestParam(name = "meeting-duration") LocalTime meetingDuration) {
        return ResponseEntity.ok(request);
    }
}
