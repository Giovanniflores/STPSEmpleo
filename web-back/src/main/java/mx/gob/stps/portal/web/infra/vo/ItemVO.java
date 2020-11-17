package mx.gob.stps.portal.web.infra.vo;

import java.io.Serializable;

public class ItemVO implements Serializable {
	private static final long serialVersionUID = -6479464698952782532L;
	public String name;
	public String label;
	public String value;
	
	public ItemVO(String name, String label, String value){
		this.name = name;
		this.label = label;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
