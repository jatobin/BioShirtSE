package com.bioshirt.graphics;

import javax.swing.JPanel;

import com.bioshirt.dao.STIDAO;
import com.bioshirt.dao.STIDAOImpl;



public class Grapher {
	
	private static final STIDAO stidao = new STIDAOImpl();
	
	public JPanel getPanelDataForDate(String date) {

		stidao.getSTIForTime(date);
		return null;
	}

}
