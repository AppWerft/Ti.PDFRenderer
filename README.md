# Ti.PDFRenderer


## Usage
```javascript
const file = Ti.Filesystem(Ti.Filesystem.applicationDataDirectory,"my.pdf");
const PDF = requirte('ti.pdfrenderer');
PDF.createPdfView({
	width :100,
	height : 200,
    page : PDF.createRenderer(file).getFirstPage()
});
```

## Proxies

### Renderer

#### Methods

##### getPageCount()
##### getPage(index)
##### getFirstPage()
##### close()

### Page

#### Methods
##### getWidth()
In points (inch/72)
##### getHeight()
In points (inch/72)
##### getIndex()

##### close());



### PdfView