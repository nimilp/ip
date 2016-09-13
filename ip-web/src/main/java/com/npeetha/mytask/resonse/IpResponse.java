package com.npeetha.mytask.resonse;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="ip-info")
@XmlType(name="ip-info")
public class IpResponse {

	private String ipAddress;
	private String lastUpdate;
	private String ipChanged;
	
	
	@XmlElement(name="address")
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	@XmlElement(name="lastUpdated")
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	@XmlElement(name="ipChanged")
	public String getIpChanged() {
		return ipChanged;
	}
	public void setIpChanged(String ipChanged) {
		this.ipChanged = ipChanged;
	}
	
	
}
