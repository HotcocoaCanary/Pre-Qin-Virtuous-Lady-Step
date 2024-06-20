package com.example.preqinvirtuousladystep.service.impl;

import com.example.preqinvirtuousladystep.mapper.RecordMapper;
import com.example.preqinvirtuousladystep.member.RunningRecord;
import com.example.preqinvirtuousladystep.service.RunningRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunningRecordImpl implements RunningRecordService {
    @Autowired
    RecordMapper recordMapper;

    @Override
    public void saveRunningRecord(RunningRecord runningRecord) {
        recordMapper.addRunningRecord(runningRecord);
    }

    @Override
    public List<RunningRecord> getRunningRecord(Integer userId) {
        return recordMapper.getRunningRecordByUserId(userId);
    }
}
