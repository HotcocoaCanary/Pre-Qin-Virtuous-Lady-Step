package com.example.preqinvirtuousladystep.member;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Table(name = "RunningRecord")
public class RunningRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RecordID")
    private Integer recordId;

    @Column(name = "UserID")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID", foreignKey = @ForeignKey(name = "fk_user_id"))
    private User user;

    @Column(name = "RunningDate")
    private LocalDate runningDate;

    @Column(name = "Distance")
    private BigDecimal distance;

    @Column(name = "Duration")
    private LocalTime duration;

    @Column(name = "StartPosition")
    private String startPosition;

    @Column(name = "EndPosition")
    private String endPosition;

    @Column(name = "START_POSITION_POINT")
    private String startPositionPoint;

    @Column(name = "END_POSITION_POINT")
    private String endPositionPoint;
}
