package com.study.sample.entity;

public enum AttributeEnum {
	COUNTRY("country"),
	GROUP("group"),
	PHARM("pharm"),
	VERSION("version"),
	MEDICINEID("medicine-id"),
	MEDICINENAME("medicine-name"),
	ENTERPRISEID("enterprise-id"),
	ENTERPRISENAME("enterprise-name"),
	CERTNUMBER("cert-number"),
	EXPIRATIONDATE("expiration-date"),
	PACKAGEAMOUNT("package-amount"),
	PACKAGETYPE("package-type"),
	DOSAGEAMOUNT("dosage-amount"),
	DOSAGEPERIODICITY("dosage-periodicity"),
	PRICE("price");
	
	private String value;
	
	private AttributeEnum(String value){
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
