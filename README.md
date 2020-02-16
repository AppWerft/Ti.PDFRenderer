# Ti.PDFRenderer


## Basic usage

First we build a pointer to the local pdf file in app folder, resources folder or from SD card:

```javascript
const pdfFile = Ti.Filesystem(Ti.Filesystem.applicationDataDirectory,"my.pdf");
```
Next an instance of module will build:

```javascript
const PDF = requirte('ti.pdfrenderer');
```
With 

```
const pdfRenderer = PDF.createRenderer(pdfFile);
```
we get a n instance of Renderer.
This renderer has 5 methods:

```
const pagecount = pdfRenderer.getPageCount();
const page1 = pdfRenderer.openFirstPage();
const page2 = pdfRenderer.openPage(PAGENUMBER);
pdfRenderer.shouldScaleForPrinting()
// in the and:
pdfRenderer.close();
```

This extracted pages has 2 methods:

```
page.close();
page.getDimensions();
page.getIndex();
```

In the end we can create an thumbnail 

```javascript
PDF.createPdfView({
	width : 100,
	height : 200,
	defaultImage : 'images/pdfdefault.png',
	renderMode : PDF.RENDER_MODE_FOR_DISPLAY
    page : pdfRenderer.openFirstPage()
});
```

With the parameter `pdf` you can directly call:

```javascript
PDF.createPdfView({
    width : 100,
    height : 200,
    defaultImage : 'images/pdfdefault.png',
    renderMode : PDF.RENDER_MODE_FOR_DISPLAY
    page :  0, // optional default = 0
	pdf : pdfFile
});
```