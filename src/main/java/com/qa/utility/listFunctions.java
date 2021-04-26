package com.qa.utility;

import java.util.Comparator;
import java.util.List;

public class listFunctions {

	public boolean listHasAnyItem(List<String> list,String value) 
	{
		boolean flag=list.stream().anyMatch((a)->a.contains(value));
		return flag;

	}
	public boolean listHasAllItem(List<String> list,String value) 
	{
		boolean flag=list.stream().anyMatch((a)->a.equalsIgnoreCase(value));
		return flag;
	}
	
	public String listMaxText(List<String> list)
	{
		Comparator<String> comp=(a,b)->a.compareTo(b);
		return list.stream().max(comp).get();
	}
	public String listMinText(List<String> list)
	{
		Comparator<String> comp=(a,b)->a.compareTo(b);
		return list.stream().min(comp).get();
	}
	public double listMinNumber(List<String> list)
	{
		Comparator<String> comp=(a,b)->a.compareTo(b);
		return Double.parseDouble(list.stream().min(comp).get());
		
	}
	public boolean listHasProperEmailFormat(List<String> list)
	{
		
		boolean flag=list.stream().allMatch((a)->a.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"));
		return flag;
	}
}
