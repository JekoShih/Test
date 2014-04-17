package com.example.xmlsax2;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class XmlHandler extends DefaultHandler {  
	  private boolean _inInfo;
	  private List<Data> all_data;
	  private Data _data;   
	  private String condition,type,key;
	  private boolean isSearch;
	  XmlHandler(boolean isSearch){
		  this.isSearch=isSearch;
	  }
	  public List<Data> gatAllData()
	  {
		  return all_data;
	  }
	  public void setCondition(String _condition,String _type,String _key)
	  {
		  condition=_condition;
		  type=_type;
		  key=_key;
	  }
	  @Override   
	  public void startDocument() throws SAXException {   
		  all_data = new ArrayList<Data>();   
	  }   
	  
	  @Override   
	  public void endDocument() throws SAXException {   
	  
	  }   
	  
	  @Override   
	  public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {   
	  
	    if(localName.equals("Info")) {   
	    	_inInfo = true; 
	      _data=new Data();
	      //String wtype=(atts.getValue("Town").trim());
	      //if(atts.getValue("Region").equals(condition)&&wtype==type)
	      if(isSearch&&!key.equals(""))
	      {
	    	  if(atts.getValue("Name").contains(key))
	    	  {
	    		  _data.Town = atts.getValue("Town");
		    	  _data.Name = atts.getValue("Name");  
			      _data.Toldescribe = atts.getValue("Toldescribe"); 
			      _data.Description = atts.getValue("Description");
			      _data.Tel = atts.getValue("Tel"); 
			      _data.Add = atts.getValue("Add");
			      _data.Website = atts.getValue("Website");
			      _data.Opentime = atts.getValue("Opentime"); 
			      _data.Region = atts.getValue("Region");
			      _data.Class1 = atts.getValue("Class1");
			      _data.Picture1 = atts.getValue("Picture1");
			      _data.Picture2 = atts.getValue("Picture2");
			      _data.Picture3 = atts.getValue("Picture3");
			      _data.Picture4 = atts.getValue("Picture4");
			      _data.Picture5 = atts.getValue("Picture5");
			      _data.Picture6 = atts.getValue("Picture6");
			      _data.Picdescribe1 = atts.getValue("Picdescribe1");
			      _data.Picdescribe2 = atts.getValue("Picdescribe2");
			      _data.Picdescribe3 = atts.getValue("Picdescribe3");
			      _data.Picdescribe4 = atts.getValue("Picdescribe4");
			      _data.Picdescribe5 = atts.getValue("Picdescribe5");
			      _data.Picdescribe6 = atts.getValue("Picdescribe6");
			      _data.Remarks = atts.getValue("Remarks");
			      _data.Px = atts.getValue("Px");
			      _data.Py = atts.getValue("Py");
			      Log.e("value",_data.Region+","+_data.Name);
	    	  }
	      }else
	      {
	    	  if(atts.getValue("Region").equals(condition)&&atts.getValue("Town").equals(type))
		      {
		    	  _data.Town = atts.getValue("Town");
		    	  _data.Name = atts.getValue("Name");  
			      _data.Toldescribe = atts.getValue("Toldescribe"); 
			      _data.Description = atts.getValue("Description");
			      _data.Tel = atts.getValue("Tel"); 
			      _data.Add = atts.getValue("Add");
			      _data.Website = atts.getValue("Website");
			      _data.Opentime = atts.getValue("Opentime"); 
			      _data.Region = atts.getValue("Region");
			      _data.Class1 = atts.getValue("Class1");
			      _data.Picture1 = atts.getValue("Picture1");
			      _data.Picture2 = atts.getValue("Picture2");
			      _data.Picture3 = atts.getValue("Picture3");
			      _data.Picture4 = atts.getValue("Picture4");
			      _data.Picture5 = atts.getValue("Picture5");
			      _data.Picture6 = atts.getValue("Picture6");
			      _data.Picdescribe1 = atts.getValue("Picdescribe1");
			      _data.Picdescribe2 = atts.getValue("Picdescribe2");
			      _data.Picdescribe3 = atts.getValue("Picdescribe3");
			      _data.Picdescribe4 = atts.getValue("Picdescribe4");
			      _data.Picdescribe5 = atts.getValue("Picdescribe5");
			      _data.Picdescribe6 = atts.getValue("Picdescribe6");
			      _data.Remarks = atts.getValue("Remarks");
			      _data.Px = atts.getValue("Px");
			      _data.Py = atts.getValue("Py");
			      Log.e("value",_data.Region+","+_data.Name);
		      }
	      }
	    }   
	  }   
	   
	  @Override   
	  public void endElement(String namespaceURI, String localName, String qName) throws SAXException {   
	    //Log.e("endElement", localName);   
	  
	    if(localName.equals("Info")) {   
	    	_inInfo = false; 
		    if(_data.Region!=null)
		    {
		    	all_data.add(_data);
		    }
	    }  
	  }   
	  
	  @Override   
	  public void characters(char ch[], int start, int length) {   
	    String chars = new String(ch, start, length);   
	    chars = chars.trim();   
	  
	    if(_inInfo) {   
	      //_data.section = chars;   
	    }  
	  }   
}
