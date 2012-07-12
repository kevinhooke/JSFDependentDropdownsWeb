package kh.jsf.dropdowns.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

/**
 * JSF ManagedBean to initialize and populate the dropdown lists.
 *  
 * @author Kevin Hooke
 */
@ManagedBean
public class DependentDropdownController {

	private static Map<Long, List<SelectItem>> carModels = new HashMap<>();
	
	private static List<SelectItem> manufacturers = new ArrayList<>();
	
	private static List<SelectItem> emptyModelList = new ArrayList<>();
	
	//the dropdown values would normally be populated from a lookup to either a database or
	//a cache, but just for this example the values are hardcoded and initialized here.
	static
	{
		//init map of Ford models
		List<SelectItem> fordList = new ArrayList<SelectItem>();
		fordList.add( new SelectItem( 0, "Select Model" ));
		fordList.add( new SelectItem( 1, "Mondeo" ));
		fordList.add( new SelectItem( 2, "Fiesta" ));
		fordList.add( new SelectItem( 3, "Focus" ));
		carModels.put( new Long(1), fordList );

		//init map of Honda models
		List<SelectItem> hondaList = new ArrayList<SelectItem>();
		hondaList.add( new SelectItem( 0, "Select Model" ));
		hondaList.add( new SelectItem( 1, "Civic" ));
		hondaList.add( new SelectItem( 2, "Accord" ));
		hondaList.add( new SelectItem( 3, "Element" ));
		carModels.put( new Long(2), hondaList );

		//init map of manufacturers
		manufacturers.add(new SelectItem(new Long(0), "Select Manufacturer"));
		manufacturers.add(new SelectItem(new Long(1), "Ford"));
		manufacturers.add(new SelectItem(new Long(2), "Honda"));
		manufacturers.add(new SelectItem(new Long(3), "Toyota"));
		
		//empty manufacturer list
		emptyModelList.add(new SelectItem(0, ""));

	}
	
	private Long parentSelectedItem;
	private Long childSelectedItem;
	
	//set dependent select to disabled initially - it get's enabled when a value
	//is selected in the parent dropdown
	private boolean modelSelectDisabled = true;
	
	public boolean isModelSelectDisabled() {
		return modelSelectDisabled;
	}

	public void setModelSelectDisabled(boolean modelSelectDisabled) {
		this.modelSelectDisabled = modelSelectDisabled;
	}

	/**
	 * @return list of Manufacturers for the parent dropdown.
	 */
	public List<SelectItem> getParentItems()
	{
		return manufacturers;
	}
	
	/**
	 * @return list of Models for the selected manufacturer in the parent dropdown.
	 */
	public List<SelectItem> getChildItems()
	{
		if(this.parentSelectedItem != null)
		{
			return carModels.get(this.parentSelectedItem);
		}
		else
		{
			return emptyModelList;
		}
	}
	
	public Long getParentSelectedItem() {
		return parentSelectedItem;
	}
	
	/**
	 * Controls the disabled property on the child dropdown. If a parent value is selected
	 * then the child dropdown is enabled, otherwise it is disabled.
	 * 
	 * @param parentSelectedItem parent dropdown list selection.
	 */
	public void setParentSelectedItem(Long parentSelectedItem) {
		this.parentSelectedItem = parentSelectedItem;
		
		if(parentSelectedItem != null && parentSelectedItem.longValue() > 0)
		{
			this.setModelSelectDisabled(false);
		}
		else
		{
			this.setModelSelectDisabled(true);
		}
	}
	
	public Long getChildSelectedItem() {
		return childSelectedItem;
	}
	
	public void setChildSelectedItem(Long childSelectedItem) {
		this.childSelectedItem = childSelectedItem;
	}
	

	
}