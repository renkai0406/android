package com.renkai.translation;

import java.io.Serializable;
import java.util.LinkedList;

import com.axone.vsmusic.translation.Translation;

public class InfoList extends Translation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LinkedList<Translation>  trans_list = new LinkedList<Translation>();
	
	public void add(Translation trans) {
		trans_list.add(trans);
	}
	
	public int getLength() {
		return trans_list.size();
	}

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return type;
	}
}
