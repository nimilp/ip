package com.npeetha.timetracker.bo;

import static com.npeetha.timetracker.constants.MongoConstants.DURATION;
import static com.npeetha.timetracker.constants.MongoConstants.END_DATE;
import static com.npeetha.timetracker.constants.MongoConstants.START_DATE;

import java.util.Calendar;
import java.util.Date;

import org.bson.Document;
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
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.MONTH, 1);
		//Date expire = Date.from(this.startDate.toInstant().plus(30, ChronoUnit.DAYS));
		doc.append(DURATION, this.getDuration()).append(END_DATE, this.getEndDate()).append(START_DATE, this.startDate).append("expireAt", calendar.getTime());
		return doc;
		
	}
	
}
