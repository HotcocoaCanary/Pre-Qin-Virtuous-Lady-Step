package com.example.preqinvirtuousladystep.controller;

import com.example.preqinvirtuousladystep.member.Result;
import com.example.preqinvirtuousladystep.member.RunningRecord;
import com.example.preqinvirtuousladystep.member.User;
import com.example.preqinvirtuousladystep.service.RunningRecordService;
import com.example.preqinvirtuousladystep.service.UserService;
import com.example.preqinvirtuousladystep.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/run")
@Validated
public class RunController {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserService userService;
    @Autowired
    RunningRecordService runningRecordService;

    @PostMapping("/clock")
    public Result<String> start(String startAddress, String endAddress, String distance, String duration) {
        Map<String, Object> map = jwtUtil.parseToken(jwtUtil.getThisToken());
        String userName = (String) map.get("Name");
        User user = userService.findByUserName(userName);
        RunningRecord runningRecord = new RunningRecord();
        runningRecord.setUser(user);
        runningRecord.setStartPosition(startAddress);
        runningRecord.setEndPosition(endAddress);
        runningRecord.setDistance(distance);
        runningRecord.setDuration(duration);
        runningRecord.setRunningDate(LocalDate.from(LocalDateTime.now()));
        runningRecordService.saveRunningRecord(runningRecord);
        // 返回一个成功的响应
        return Result.success();
    }

    @PostMapping("/report")
    public Result<List<RunningRecord>> getReport() {
        Map<String, Object> map = jwtUtil.parseToken(jwtUtil.getThisToken());
        String userName = (String) map.get("Name");
        User user = userService.findByUserName(userName);
        List<RunningRecord> runningRecordList=runningRecordService.getRunningRecord(user.getUserId());
        // 返回一个成功的响应
        return Result.success(runningRecordList);
    }
}
