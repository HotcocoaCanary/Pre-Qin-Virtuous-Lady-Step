package com.example.preqinvirtuousladystep.mapper;

import com.example.preqinvirtuousladystep.member.RunningRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RecordMapper {

    // 增加跑步记录
    @Insert("INSERT INTO RunningRecord (UserID, RunningDate, Distance, Duration, StartPosition, EndPosition) " +
            "VALUES (#{userId}, CURDATE(), #{distance}, #{duration}, #{startPosition}, #{endPosition})")
    void addRunningRecord(@Param("userId") int userId, @Param("distance") float distance,
                          @Param("duration") String duration, @Param("startPosition") String startPosition,
                          @Param("endPosition") String endPosition);

    // 删除跑步记录
    @Delete("DELETE FROM RunningRecord WHERE RecordID = #{recordId}")
    void deleteRunningRecord(@Param("recordId") int recordId);

    // 更新跑步记录
    @Update("UPDATE RunningRecord SET Distance = #{distance}, Duration = #{duration}, " +
            "StartPosition = #{startPosition}, EndPosition = #{endPosition} " +
            "WHERE RecordID = #{recordId}")
    void updateRunningRecord(@Param("recordId") int recordId, @Param("distance") float distance,
                             @Param("duration") String duration, @Param("startPosition") String startPosition,
                             @Param("endPosition") String endPosition);

    // 查询跑步记录
    @Select("SELECT * FROM RunningRecord WHERE UserID = #{userId} AND RunningDate = CURDATE()")
    List<RunningRecord> getTodaysRunningRecords(@Param("userId") int userId);

    // 查询特定ID的跑步记录
    @Select("SELECT * FROM RunningRecord WHERE RecordID = #{recordId}")
    RunningRecord getRunningRecordById(@Param("recordId") int recordId);
}
