package com.npeetha.mytask.resonse;


public class IpResponse {

	private String ipAddress;
	private String lastUpdate;
	private String ipChanged;
	
	
//	@XmlElement(name="address")
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
//	@XmlElement(name="lastUpdated")
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
//	@XmlElement(name="ipChanged")
	public String getIpChanged() {
		return ipChanged;
	}
	public void setIpChanged(String ipChanged) {
		this.ipChanged = ipChanged;
	}
	
	
}
