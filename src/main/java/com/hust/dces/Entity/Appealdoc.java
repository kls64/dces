package com.hust.dces.Entity;


import java.util.Date;

public class Appealdoc {

  private int appealid;
  private int userid;
  private boolean status;
  private String docname;
  private String reason;
  private String feedback;
  private Date feedbacktime;
  private Date appealtime;
  private String username;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getAppealid() {
    return appealid;
  }

  public void setAppealid(int appealid) {
    this.appealid = appealid;
  }

  public int getUserid() {
    return userid;
  }

  public void setUserid(int userid) {
    this.userid = userid;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getDocname() {
    return docname;
  }

  public void setDocname(String docname) {
    this.docname = docname;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getFeedback() {
    return feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }

  public Date getFeedbacktime() {
    return feedbacktime;
  }

  public void setFeedbacktime(Date feedbacktime) {
    this.feedbacktime = feedbacktime;
  }

  public Date getAppealtime() {
    return appealtime;
  }

  public void setAppealtime(Date appealtime) {
    this.appealtime = appealtime;
  }
}
