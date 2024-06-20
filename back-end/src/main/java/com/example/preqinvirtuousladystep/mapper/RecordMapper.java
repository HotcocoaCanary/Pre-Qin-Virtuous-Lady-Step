package com.example.preqinvirtuousladystep.mapper;

import com.example.preqinvirtuousladystep.member.RunningRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RecordMapper {

    // 查询特定ID的跑步记录
    @Select("SELECT * FROM RunningRecord WHERE UserID = #{userId}")
    List<RunningRecord> getRunningRecordByUserId(@Param("userId") int userId);

    @Insert("INSERT INTO RunningRecord (UserID, RunningDate, Distance, Duration, StartPosition, EndPosition, START_POSITION_POINT, END_POSITION_POINT) " +
            "VALUES (#{user.userId}, #{runningDate}, #{distance}, #{duration}, #{startPosition}, #{endPosition}, #{startPositionPoint}, #{endPositionPoint})")
    @Options(useGeneratedKeys = true, keyProperty = "recordId")
    void addRunningRecord(RunningRecord runningRecord);
}
