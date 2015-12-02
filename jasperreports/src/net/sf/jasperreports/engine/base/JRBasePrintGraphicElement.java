/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.engine.base;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDefaultStyleProvider;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.JRPrintGraphicElement;
import net.sf.jasperreports.engine.type.FillEnum;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public abstract class JRBasePrintGraphicElement extends JRBasePrintElement implements JRPrintGraphicElement
{


	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/**
	 *
	 */
	protected JRPen linePen;
	protected FillEnum fillValue;


	/**
	 *
	 */
	public JRBasePrintGraphicElement(JRDefaultStyleProvider defaultStyleProvider)
	{
		super(defaultStyleProvider);

		linePen = new JRBasePen(this);
	}


	/**
	 *
	 */
	public JRPen getLinePen()
	{
		return linePen;
	}
		
	/**
	 *
	 */
	public void copyPen(JRPen linePen)
	{
		this.linePen = linePen.clone(this);
	}

	/**
	 *
	 */
	public FillEnum getFillValue()
	{
		return getStyleResolver().getFillValue(this);
	}

	/**
	 *
	 */
	public FillEnum getOwnFillValue()
	{
		return this.fillValue;
	}


	/**
	 *
	 */
	public void setFill(FillEnum fillValue)
	{
		this.fillValue = fillValue;
	}
		

//	/**
//	 * 
//	 */
//	public JRStyleContainer getStyleContainer() 
//	{
//		return this;
//	}

	/**
	 * 
	 */
	public Float getDefaultLineWidth() 
	{
		return JRPen.LINE_WIDTH_1;
	}

	/**
	 * 
	 */
	public Color getDefaultLineColor() 
	{
		return getForecolor();
	}

	
	/*
	 * These fields are only for serialization backward compatibility.
	 */
	private int PSEUDO_SERIAL_VERSION_UID = JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2; //NOPMD
	/**
	 * @deprecated
	 */
	private Byte fill;
	
	@SuppressWarnings("deprecation")
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		in.defaultReadObject();
		
		if (PSEUDO_SERIAL_VERSION_UID < JRConstants.PSEUDO_SERIAL_VERSION_UID_3_7_2)
		{
			fillValue = FillEnum.getByValue(fill);
			fill = null;
		}
	}
		
}
