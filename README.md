# Ti.PDFRenderer


## Usage
```javascript
const file = Ti.Filesystem(Ti.Filesystem.applicationDataDirectory,"my.pdf");
const PDF = requirte('ti.pdfrenderer');
PDF.createPdfView({
	width :100,
	height : 200,
    page : PDF.createRender(file).getPage(0)
});
```

## Proxies

### Renderer

#### Methods

##### getPageCount()
##### getPage()

### PdfView