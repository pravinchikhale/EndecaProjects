package com.pravin.bean;

public class DimensionBean {

	private String dimensionName;
	private String dimensionID;
	private String parentDimensionID;

	public DimensionBean(String dimensionName, String dimensionID,
			String parentDimensionID) {
		super();
		this.dimensionName = dimensionName;
		this.dimensionID = dimensionID;
		this.parentDimensionID = parentDimensionID;
	}

	public String getDimensionName() {
		return dimensionName;
	}

	public void setDimensionName(String dimensionName) {
		this.dimensionName = dimensionName;
	}

	public String getDimensionID() {
		return dimensionID;
	}

	public void setDimensionID(String dimensionID) {
		this.dimensionID = dimensionID;
	}

	public String getParentDimensionID() {
		return parentDimensionID;
	}

	public void setParentDimensionID(String parentDimensionID) {
		this.parentDimensionID = parentDimensionID;
	}
}
