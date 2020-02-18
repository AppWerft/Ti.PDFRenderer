// This is a test harness for your module
// You should do something interesting in this harness
// to test out the module and to provide instructions
// to users on how to use it by example.

// open a single window
var win = Ti.UI.createWindow({
    backgroundColor : 'white'
});
var label = Ti.UI.createLabel();
win.add(label);
win.open();

var pdfrenderer = require('ti.pdfrenderer');

if (Ti.Platform.name == "android") {
    var foo = Ti.Filesystem.getFile(Ti.Filesystem.resourcesDirectory, 'sample.pdf');
    var bar = Ti.Filesystem.getFile(Ti.Filesystem.applicationDataDirectory, 'sample.pdf');
    bar.write(foo.read());
    var Renderer = pdfrenderer.createRenderer(bar);
    console.log("pageCount: " + Renderer.getPageCount());
    console.log("shouldScaleForPrinting: "+ Renderer.shouldScaleForPrinting());
    console.log("Pagework");
    var Page = Renderer.openFirstPage();
    console.log(Page.getDimensions(pdfrenderer.UNIT_CM));
    var pdfView = pdfrenderer.createPdfView({
        width : 288,
        height : 400,
        borderColor:'silver',
        borderWidth : 1,
        renderMode : pdfrenderer.RENDER_MODE_FOR_DISPLAY,
        page: Page,
        top : 100
    });
    Page.close();
    Renderer.close();
    win.add(pdfView);
}

