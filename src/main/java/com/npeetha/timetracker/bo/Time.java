package com.npeetha.timetracker.bo;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Date;

import org.bson.Document;
import static com.npeetha.timetracker.constants.MongoConstants.*;
public class Time {

	private Date startDate;
	private Date endDate;
	private Integer duration;
	
	
	public Date getStartDate() {
		return startDate;
	}
	public Time setStartDate(Date startDate) {
		this.startDate = startDate;
		return this;
	}
	public Date getEndDate() {
		return endDate;
	}
	public Time setEndDate(Date endDate) {
		this.endDate = endDate;
		return this;
	}
	public Integer getDuration() {
		return duration;
	}
	public Time setDuration(Integer duration) {
		this.duration = duration;
		return this;
	}
	
	public Document getDocument(){
		Document doc = new Document();
		Date expire = Date.from(this.startDate.toInstant().plus(30, ChronoUnit.DAYS));
		doc.append(DURATION, this.getDuration()).append(END_DATE, this.getEndDate()).append(START_DATE, this.startDate).append("expireAt", expire);
		return doc;
		
	}
	
}
