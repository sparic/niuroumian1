package com.myee.domain.weixin.model;

import com.myee.domain.AbstractEntity;

import javax.persistence.*;

/**
 * Created by Ray.Fu on 2016/5/10.
 */
@Entity
@Table(name = "w_feed_back")
public class WFeedBack extends AbstractEntity {
    @Id
    @Column(name = "feedback_id", columnDefinition = "INT")
    @TableGenerator(name = "PkGen_155", table = "ad_sequence", pkColumnName = "name", pkColumnValue = "W_Feed_Back", valueColumnName = "currentnextsys", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PkGen_155")
    private Long   feedbackId;

    @Column(name = "feedback_openid")
    private String feedBackOpenId;

    @Column(name = "feedback_context")
    private String feedBackContext;

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedBackOpenId() {
        return feedBackOpenId;
    }

    public void setFeedBackOpenId(String feedBackOpenId) {
        this.feedBackOpenId = feedBackOpenId;
    }

    public String getFeedBackContext() {
        return feedBackContext;
    }

    public void setFeedBackContext(String feedBackContext) {
        this.feedBackContext = feedBackContext;
    }
}
