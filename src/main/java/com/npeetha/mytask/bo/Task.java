package com.npeetha.mytask.bo;

import javax.annotation.Nonnull;


public class Task {

	private int id;
	private @Nonnull String name;
	private @Nonnull String date;
	private String note;
	private int reminderDay;
	private String reminderMeasure;
	private String occurance;
	private String occuranceMeasure;
	private int status;
	
	public String getName() {
		return name;
	}
	public void setName( String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getReminderDay() {
		return reminderDay;
	}
	public void setReminderDay(int reminderDay) {
		this.reminderDay = reminderDay;
	}
	public String getReminderMeasure() {
		return reminderMeasure;
	}
	public void setReminderMeasure(String reminderMeasure) {
		this.reminderMeasure = reminderMeasure;
	}
	public String getOccurance() {
		return occurance;
	}
	public void setOccurance(String occurance) {
		this.occurance = occurance;
	}
	public String getOccuranceMeasure() {
		return occuranceMeasure;
	}
	public void setOccuranceMeasure(String occuranceMeasure) {
		this.occuranceMeasure = occuranceMeasure;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
