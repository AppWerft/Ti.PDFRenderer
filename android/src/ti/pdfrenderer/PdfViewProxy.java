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
import java.io.IOException;
import java.io.InputStream;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.io.TiBaseFile;
import org.appcelerator.titanium.io.TiFile;
import org.appcelerator.titanium.io.TiFileFactory;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.util.TiUIHelper;
import org.appcelerator.titanium.view.TiCompositeLayout;
import org.appcelerator.titanium.view.TiCompositeLayout.LayoutArrangement;
import org.appcelerator.titanium.view.TiUIView;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.pdf.PdfRenderer;
import android.graphics.pdf.PdfRenderer.Page;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;
import ti.modules.titanium.filesystem.FileProxy;

// This proxy can be created by calling Pdfrenderer.createExample({message: "hello world"})
@Kroll.proxy(creatableInModule = PdfrendererModule.class)
public class PdfViewProxy extends TiViewProxy {
	// Standard Debugging variables
	private static final String LCAT = "TiPdf";
	private Bitmap bitmap;
	private int pageIndex = 0;
	PdfRenderer renderer;
	Context ctx = TiApplication.getAppCurrentActivity().getApplicationContext();

	private class Pdfview extends TiUIView {
		public Pdfview(TiViewProxy proxy) {
			super(proxy);
			LayoutArrangement arrangement = LayoutArrangement.DEFAULT;
			TiCompositeLayout container = new TiCompositeLayout(proxy.getActivity(), arrangement);
			ImageView nativeView = new ImageView(proxy.getActivity());
			container.addView(nativeView);
			nativeView.setImageBitmap(bitmap);
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
			} else
				pageIndex = options.getInt("page");
		}
		if (options.containsKey("pdf")) {
			try {
				ParcelFileDescriptor pfd = ParcelFileDescriptor.open(getFileFromObject(options.get("pdf")),
						ParcelFileDescriptor.MODE_READ_ONLY);
				Page page = (new PdfRenderer(pfd)).openPage(pageIndex);
				if (bitmap != null)
					page.render(bitmap, null, null, renderMode);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		bitmap = loadBitmapFromAssets(ctx,"pdf.png");
	}

	
	/* this method reads from folder
	 *  <module>/android/platform/android/bin/assets/filePath
	 *  in native app this is underneath assets folder
	 * */
	private Bitmap loadBitmapFromAssets(Context context,String filePath) {
		AssetManager assetManager = context.getAssets();

	    InputStream istr;
	    Bitmap bitmap = null;
	    try {
	        istr = assetManager.open(filePath);
	        bitmap = BitmapFactory.decodeStream(istr);
	    } catch (IOException e) {
	        // handle exception
	    }
	    return bitmap;
	}
	private File getFileFromObject(Object fileObject) {
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
			return TiFile.getNativeFile();
		} catch (Exception e) {
			Log.d(LCAT, e.getMessage());
		}
		return null;
	}

}
