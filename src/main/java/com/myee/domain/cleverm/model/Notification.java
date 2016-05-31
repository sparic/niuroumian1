package com.myee.domain.cleverm.model;

import com.myee.domain.AbstractEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "c_notification")
public class Notification extends AbstractEntity {
    @Id
    @Column(name = "c_notification_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_116", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "C_Notification", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_116")
    private Long    notificationID;
    @Column(name = "name")
    private String  name;
    @Column(name = "description")
    private String  description;
    @Column(name = "notification_type")
    private String  notificationType;
    @Column(name = "target_computer_id")
    private Long    targetComputerID;
    @Column(name = "content")
    @Lob
    private String  content;
    @Column(name = "broadcast")
    private boolean broadcast;
    @Column(name = "feedback")
    private boolean feedback;
    @Column(name = "time_expired")
    private Date    timeExpired;
    @Column(name = "push_status")
    private int     pushStatus;

    public Long getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(Long notificationID) {
        this.notificationID = notificationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public Long getTargetComputerID() {
        return targetComputerID;
    }

    public void setTargetComputerID(Long targetComputerID) {
        this.targetComputerID = targetComputerID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isBroadcast() {
        return broadcast;
    }

    public void setBroadcast(boolean broadcast) {
        this.broadcast = broadcast;
    }

    public boolean isFeedback() {
        return feedback;
    }

    public void setFeedback(boolean feedback) {
        this.feedback = feedback;
    }

    public Date getTimeExpired() {
        return timeExpired;
    }

    public void setTimeExpired(Date timeExpired) {
        this.timeExpired = timeExpired;
    }

    public int getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(int pushStatus) {
        this.pushStatus = pushStatus;
    }

}
