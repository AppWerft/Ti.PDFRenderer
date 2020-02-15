/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2017 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package ti.pdfrenderer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiC;
import org.appcelerator.titanium.io.TiBaseFile;
import org.appcelerator.titanium.io.TiFile;
import org.appcelerator.titanium.io.TiFileFactory;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.kroll.common.TiConfig;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiCompositeLayout;
import org.appcelerator.titanium.view.TiCompositeLayout.LayoutArrangement;
import org.appcelerator.titanium.view.TiUIView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfRenderer;
import android.graphics.pdf.PdfRenderer.Page;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;
import android.widget.LinearLayout;
import ti.modules.titanium.filesystem.FileProxy;

// This proxy can be created by calling Pdfrenderer.createExample({message: "hello world"})
@Kroll.proxy(creatableInModule = PdfrendererModule.class)
public class PdfViewProxy extends TiViewProxy {
	// Standard Debugging variables
	private static final String LCAT = "ExampleProxy";
	private Bitmap bitmap;
	private int width;
	private int height;

	private class Pdfview extends TiUIView {
		public Pdfview(TiViewProxy proxy) {
			super(proxy);
			LayoutArrangement arrangement = LayoutArrangement.DEFAULT;
			TiCompositeLayout container = new TiCompositeLayout(proxy.getActivity(), arrangement);
			ImageView nativeView = new ImageView(proxy.getActivity());
		    
		
			
			container.addView(nativeView);
			BitmapDrawable drawable = (BitmapDrawable) nativeView.getDrawable();
			bitmap = drawable.getBitmap();
//			nativeView.setImageBitmap(bitmap);
			setNativeView(container);
		}

		@Override
		public void processProperties(KrollDict d) {
			super.processProperties(d);
		}
	}

	// Constructor
	public PdfViewProxy() {
		super();
	}

	@Override
	public TiUIView createView(Activity activity) {
		TiUIView view = new Pdfview(this);
		view.getLayoutParams().autoFillsHeight = true;
		view.getLayoutParams().autoFillsWidth = true;

		return view;
	}

	// Handle creation options
	@Override
	public void handleCreationDict(KrollDict options) {
		int renderMode = 0;
		super.handleCreationDict(options);

		if (options.containsKey("renderMode")) {
			renderMode = options.getInt("renderMode");
		}
		if (options.containsKey("page")) {
			Object o = options.get("page");
			if (o instanceof PageProxy) {
				
				Page page = ((PageProxy) o).getPage();
				
				if (bitmap != null)
					page.render(bitmap, null, null, renderMode);
			}
		}
	}

}
