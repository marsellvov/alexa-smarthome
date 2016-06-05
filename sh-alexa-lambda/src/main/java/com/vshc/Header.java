package com.vshc;

public class Header {
	private String payloadVersion;
	private String namespace;
	private String name;

	public String getPayloadVersion() {
		return payloadVersion;
	}
	public void setPayloadVersion(String payloadVersion) {
		this.payloadVersion = payloadVersion;
	}
	public String getNamespace() {
		return namespace;
	}
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
