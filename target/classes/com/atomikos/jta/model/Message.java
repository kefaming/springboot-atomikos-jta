package com.atomikos.jta.model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String msgId;
	private int portNum;
	private String smsNumber;
	private String smsSubject;
	private String smsContent;
	private int smsType;
	private String phoNum;
	private int smsState;
//	private Date pushTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public int getPortNum() {
		return portNum;
	}
	public void setPortNum(int portNum) {
		this.portNum = portNum;
	}
	public String getSmsNumber() {
		return smsNumber;
	}
	public void setSmsNumber(String smsNumber) {
		this.smsNumber = smsNumber;
	}
	public String getSmsSubject() {
		return smsSubject;
	}
	public void setSmsSubject(String smsSubject) {
		this.smsSubject = smsSubject;
	}
	public String getSmsContent() {
		return smsContent;
	}
	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
	public int getSmsType() {
		return smsType;
	}
	public void setSmsType(int smsType) {
		this.smsType = smsType;
	}
	public String getPhoNum() {
		return phoNum;
	}
	public void setPhoNum(String phoNum) {
		this.phoNum = phoNum;
	}
	public int getSmsState() {
		return smsState;
	}
	public void setSmsState(int smsState) {
		this.smsState = smsState;
	}
//	public Date getPushTime() {
//		return pushTime;
//	}
//	public void setPushTime(Date pushTime) {
//		this.pushTime = pushTime;
//	}
	
	
	
}
