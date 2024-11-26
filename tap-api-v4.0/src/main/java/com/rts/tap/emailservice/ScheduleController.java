package com.rts.tap.emailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rts.tap.constants.APIConstants;

@RestController
@RequestMapping(path=APIConstants.BASE_SCHEDULE_EMAIL_URL)
@CrossOrigin(origins = APIConstants.FRONT_END_URL)
public class ScheduleController {

    @Autowired
    private MailService mailService;

    @PostMapping(path=APIConstants.SAVE_SCHEDULE_EMAIL_URL)
    public void scheduleAssessment(@RequestBody ScheduleRequest scheduleRequest) {
        // Send assessment notifications
        mailService.sendAssessmentNotifications(scheduleRequest);
    }
}
