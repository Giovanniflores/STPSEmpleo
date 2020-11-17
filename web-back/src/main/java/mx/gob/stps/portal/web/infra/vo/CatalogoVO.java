package mx.gob.stps.portal.web.infra.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CatalogoVO implements Serializable {
	private static final long serialVersionUID = 8223890956206084739L;
	public String identifier;
	public String label;
	public List<ItemVO> items;

	public CatalogoVO(String identifier, String label){
		this.identifier = identifier;
		this.label = label;
		this.items = new ArrayList<ItemVO>();
	}
	
	public void addItem(String name, String label, String value){
		addItem(new ItemVO(name, label, value));
	}
	public void addItem(ItemVO item){
		items.add(item);
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<ItemVO> getItems() {
		return items;
	}
	public void setItems(List<ItemVO> items) {
		this.items = items;
	}
}
