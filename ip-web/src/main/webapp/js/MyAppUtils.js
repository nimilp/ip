MyUtil = function(){
	
}
MyUtil.Server_Context = function(){
	return window.location.origin;
}
MyUtil.showSuccessOrError = function(success){
	if(success){
		$("#success-alert").alert();
        $("#success-alert").fadeTo(2000, 500).slideUp(500, function(){
       		$("#success-alert").slideUp(500);
        }); 
	}else{
		$("#error-alert").alert();
        $("#error-alert").fadeTo(2000, 500).slideUp(500, function(){
       		$("#error-alert").slideUp(500);
        }); 
	}
}
MyUtil.scrollToTop = function(speed,selector){
	var mySpeed = 1000;
	var mySelector = $('html body');
	if(selector){
		mySelector = selector;
	}
	if(speed){
		mySpeed = speed;
	}
	$('html, body').animate({
		scrollTop: mySelector.offset().top
	},mySpeed);
}