# Ti.PdfRenderer


## Basic usage

First we build a pointer to the local pdf file in app folder, resources folder or from SD card:

```javascript
const pdfFile = Ti.Filesystem(Ti.Filesystem.applicationDataDirectory,"my.pdf");
```
Next an instance of module will build:

```javascript
const PDF = require('ti.pdfrenderer');
```
With 

```
const pdfRenderer = PDF.createRenderer(pdfFile);
```
we get a n instance of Renderer.
This renderer has 5 methods:

```javascript
const pagecount = pdfRenderer.getPageCount();
const page1 = pdfRenderer.openFirstPage();
const page2 = pdfRenderer.openPage(PAGENUMBER);
pdfRenderer.shouldScaleForPrinting()
// in the and:
pdfRenderer.close();
```

This extracted pages has 2 methods:

```javascript
page.close();
page.getDimensions(PDF.UNIT_MM); // UNIT_INCH, UNIT_PT, UNIT_CM
page.getIndex();
```

In the end we can create an thumbnail 

```javascript
const pdfView = PDF.createPdfView({
	width : 200,
	height : 280,
	renderMode : PDF.RENDER_MODE_FOR_DISPLAY, //RENDER_MODE_FOR_PRINT
	page : pdfRenderer.openFirstPage()
});
win.add(pdfView);
```

See [more in 	example](./blob/master/example/app.js).

# Usage in TiScrollableView

This example will only work with small PDFs. For complex PDF you need an event listener for scrolling inside container.


```javascript
const PDF = require('ti.pdfrenderer');
const pdfRenderer = PDF.createRenderer(pdfFile);
const pageCount = pdfRenderer.getPagecount();
var views = [];
for (var i=0;i<pageCount;i++) {
	views.push( PDF.createPdfView({
		width : 200,
		height : 280,
		renderMode : PDF.RENDER_MODE_FOR_DISPLAY,
		page : pdfRenderer.openPage(i)
	}));
}
pdfRenderer.close();
 
const container = Ti.UI.createScrollableView();
const pdfView = PDF.createPdfView({
	width : 200,
	height : 280,
	renderMode : PDF.RENDER_MODE_FOR_DISPLAY, //RENDER_MODE_FOR_PRINT
	page : pdfRenderer.openFirstPage()
});
win.add(pdfView);
```
