/**
F * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2018 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package ti.pdfrenderer;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;

import org.appcelerator.titanium.TiApplication;

import android.graphics.pdf.PdfRenderer.Page;

import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;


@Kroll.module(name="Pdfrenderer", id="ti.pdfrenderer")
public class PdfrendererModule extends KrollModule {

	 @Kroll.constant 
	 public static final int RENDER_MODE_FOR_DISPLAY = Page.RENDER_MODE_FOR_DISPLAY;
	 @Kroll.constant 
	 public static final int RENDER_MODE_FOR_PRINT = Page.RENDER_MODE_FOR_PRINT;
	 @Kroll.constant 
	 public static final float UNIT_MM = 25.4f/72;
	 @Kroll.constant 
	 public static final float UNIT_CM = 2.54f/72;
	 @Kroll.constant 
	 public static final float UNIT_INCH = 1.0f/72;
	 @Kroll.constant 
	 public static final float UNIT_PT = 1.0f;
	 
	public PdfrendererModule()
	{
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app)
	{
	
	}

	

}

