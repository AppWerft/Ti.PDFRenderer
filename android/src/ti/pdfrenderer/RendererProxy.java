/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2017 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package ti.pdfrenderer;

import java.io.IOException;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.io.TiBaseFile;
import org.appcelerator.titanium.io.TiFile;
import org.appcelerator.titanium.io.TiFileFactory;

import android.graphics.pdf.PdfRenderer;
import android.graphics.pdf.PdfRenderer.Page;
import android.os.ParcelFileDescriptor;
import ti.modules.titanium.filesystem.FileProxy;
import ti.pdfrenderer.PageProxy;

@Kroll.proxy(creatableInModule = PdfrendererModule.class)
public class RendererProxy extends KrollProxy {
	// Standard Debugging variables
	private static final String LCAT = "TiPdfRenderer";
	PdfRenderer renderer;

	// Constructor
	public RendererProxy() {
		super();
	}

	// Handle creation options
	@Override
	public void handleCreationArgs(KrollModule m, Object[] o) {
		super.handleCreationArgs(m, o);
		Object obj = o[0];
		try {
			renderer = new PdfRenderer(ParcelFileDescriptor.open(getTiFileFromObject(obj).getNativeFile(),
					ParcelFileDescriptor.MODE_READ_ONLY));
			Log.d(LCAT,"PdfRenderer created " + renderer.getPageCount());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	@Kroll.method
	public boolean shouldScaleForPrinting() {
		return renderer.shouldScaleForPrinting();
	}
	
	@Kroll.method
	public int getPageCount() {
		return renderer.getPageCount();
	}
	
	
	@Kroll.method
	public PageProxy openPage(int index) {
		Page page = renderer.openPage(index);
		return new PageProxy(page);
	}

	@Kroll.method
	public PageProxy openFirstPage() {
		Page page = renderer.openPage(0);
		return new PageProxy(page);
	}

	

	@Kroll.method
	public void close() {
		renderer.close();
	}

	private TiBaseFile getTiFileFromObject(Object fileObject) {
		TiBaseFile TiFile = null;
		try {
			if (fileObject instanceof TiFile) {
				Log.d(LCAT, "file is TiFile");
				TiFile = TiFileFactory.createTitaniumFile(((TiFile) fileObject).getFile().getAbsolutePath(), false);
			} else {
				if (fileObject instanceof FileProxy) {
					Log.d(LCAT, "file is FileProxy");
					TiFile = ((FileProxy) fileObject).getBaseFile();
				} else {
					if (fileObject instanceof TiBaseFile) {
						Log.d(LCAT, "file is TiBaseFile");
						TiFile = (TiBaseFile) fileObject;
					} else if (fileObject instanceof String) {
						// see:
						// https://github.com/appcelerator/titanium_mobile/blob/master/android/modules/database/src/java/ti/modules/titanium/database/DatabaseModule.java
						String uriString = resolveUrl(null, (String) fileObject);
						Log.d(LCAT, "resolvedUrl=" + uriString);
						TiFile = TiFileFactory.createTitaniumFile(new String[] { uriString }, false);
					}
				}
			}
			if (TiFile == null) {
				Log.d(LCAT, "TiFile is null");
				return null;
			}
			return TiFile;
		} catch (Exception e) {
			Log.d(LCAT, e.getMessage());
		}
		return null;
	}

}
