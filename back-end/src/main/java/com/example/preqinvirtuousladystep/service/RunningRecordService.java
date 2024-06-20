package com.example.preqinvirtuousladystep.service;

import com.example.preqinvirtuousladystep.member.RunningRecord;

import java.util.List;

public interface RunningRecordService {
    void saveRunningRecord(RunningRecord runningRecord);

    List<RunningRecord> getRunningRecord(Integer userId);
}
