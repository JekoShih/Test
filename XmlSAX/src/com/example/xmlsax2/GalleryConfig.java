package com.example.xmlsax2;

import java.util.ArrayList;

public class GalleryConfig {
	private ArrayList<String> gallery_list=new ArrayList<String>();

	public ArrayList<String> getGallery_list() {
		return gallery_list;
	}

	public void setGallery_list(ArrayList<String> gallery_list) {
		this.gallery_list = gallery_list;
	}

	public GalleryConfig(ArrayList<String> url)
	{
		for(int i=0;i<url.size();i++)
		{
			gallery_list.add(url.get(i));
		}
		/*gallery_list.add("http://www.ethemsulan.com/wp-content/uploads/ara.png");
		*/
	}

}
