var pictureSource;   // picture source
var destinationType; // sets the format of returned value
var insertImg;
// Wait for device API libraries to load
//
document.addEventListener("deviceready",onDeviceReady,false);

// device APIs are available
//
function onDeviceReady() {
    pictureSource=navigator.camera.PictureSourceType;
    destinationType=navigator.camera.DestinationType;
}

// Called when a photo is successfully retrieved
//
function onPhotoDataSuccess(imageData) {
  // Uncomment to view the base64-encoded image data
  // console.log(imageData);

  // Get image handle
  //
  var smallImage = document.getElementById('smallImage');

  // Unhide image elements
  //
  smallImage.style.display = 'block';

  // Show the captured photo
  // The in-line CSS rules are used to resize the image
  //
  smallImage.src = "data:image/jpeg;base64," + imageData;
}

// Called when a photo is successfully retrieved
//
function onPhotoURISuccess(imageURI) {
	var largeImage = document.getElementById('cameraImage');
	largeImage.style.display = 'block';
	largeImage.src = imageURI;
}

function onPhotoURISuccess2(imageURI) {
	$('#cartImg1').css('display','block');
	$('#cartImg1').attr('src',imageURI);
}

function capturePhoto() {
	// Take picture using device camera and retrieve image as base64-encoded string
	navigator.camera.getPicture(onPhotoDataSuccess, onFail, { quality: 90,
	saveToPhotoAlbum: true,
    destinationType: destinationType.DATA_URL });
}

// A button will call this function
//
function capturePhotoEdit() {
	// Take picture using device camera, allow edit, and retrieve image as base64-encoded string
	navigator.camera.getPicture(onPhotoDataSuccess, onFail, { quality: 90, allowEdit: true,
	destinationType: destinationType.DATA_URL });
}

// A button will call this function
//
function getPhoto(source) {
	// Retrieve image file location from specified source
	navigator.camera.getPicture(onPhotoURISuccess, onFail, { quality: 90,
	destinationType: destinationType.FILE_URI, 
	sourceType: source });  
}

function getPhotoEmpOrdrRead(source) {
	// Retrieve image file location from specified source
	navigator.camera.getPicture(onPhotoURISuccess2, onFail, { quality: 90,
	destinationType: destinationType.FILE_URI, 
	sourceType: source });  
}

// Called if something bad happens.
//
function onFail(message) {
  alert('선택이 취소되었습니다.');
}

function uploadPhoto(imageNme) {
	var imageURI = $('#hairShopImgId').attr('src');
	var imageNme = imageNme;
    var options = new FileUploadOptions();
    options.chunkedMode = false;
    options.fileKey="file";
    options.fileName=imageNme;
    options.mimeType="image/jpeg";
    options.headers = {
    	Connection: "close"
    };
    var params = new Object();
    params.value1 = "value1";
    params.value2 = "value2";

    options.params = params;

    var fileTransfer = new FileTransfer();
    fileTransfer.upload(
		imageURI, 
		"http://192.168.43.153/android/upload.php", 
		win, 
		fail, 
		options
	);
}

function win(r){
	console.log("Code = " + r.responseCode.toString()+"\n");
    console.log("Response = " + r.response.toString()+"\n");
    console.log("Sent = " + r.bytesSent.toString()+"\n");
}

function fail(error){
	alert("에러발생:Code = " + error.code);
}