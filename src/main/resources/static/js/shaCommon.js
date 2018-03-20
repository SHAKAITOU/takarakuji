try{
	ShaCommon;
}catch(e){
	ShaCommon = {};
}

(function($common) {
	
	//*****************************************************************************
	// common util define
	//*****************************************************************************
	
	if($common.util) { 
		return $common.util; 
	}	
	
	$common.util = {
			
		//------------------------------------------------------------------------------
		// get random int between min and max int
		//------------------------------------------------------------------------------
		getRandomInt : function (min, max) {
			return Math.floor( Math.random() * (max - min + 1) ) + min;
		},
		
		getAverageInt : function (numbers) {
			var sum = 0;
			for( var i = 0; i < numbers.length; i++ ){
				//don't forget to add the base
			    sum += parseInt( numbers[i], 10 ); //10進
			}
			var avg = Math.floor(sum/numbers.length);
			return avg;
		},
		
		isLoto7Number : function(number) {
			var numbers = [];
			for( var i = 1; i <= 37; i++ ){
				if(number == i){
					return true;
				}
			}
			
			return false;
		},
		
		isLoto6Number : function(number) {
			var numbers = [];
			for( var i = 1; i <= 43; i++ ){
				if(number == i){
					return true;
				}
			}
			
			return false;
		},
		
		isMiniLotoNumber : function(number) {
			var numbers = [];
			for( var i = 1; i <= 31; i++ ){
				if(number == i){
					return true;
				}
			}
			
			return false;
		},
		
		triggerWinResize : function(callFun) {
			$(window).resize(function() {
				callFun();
			}).trigger("resize");
		},
	
	}
	
	//*****************************************************************************
	// common ajax define
	//*****************************************************************************
	
	if($common.dialogs) { 
		return $common.dialogs; 
	}	
	
	$common.dialogs = {
		progress : function (showFlg) {
			if(showFlg){
				return $('#progress').modal('show');
			}else{
				return $('#progress').modal('hide');
			}
		},
		alert : function (context) {
			$('#alert').find('#alertBody').html(context);
			return $('#alert').modal('show');
		},
		
		confirm : function (title, context, callBackOk) {
			$('#confirm').find('#confirmTitle').html(title);
			$('#confirm').find('#confirmBody').html(context);
			
			$btn_obj = $('#confirmBtnOk'); 
			$btn_obj.unbind(); 
			$btn_obj.on('click', function(event) {
				event.preventDefault();
				callBackOk();
			});
			
			$('#confirm').modal('show');
		},
		confirmClose : function () {
			$('#confirm').modal('hide');
		},
		success : function (context) {
			$('#success').find('#successBody').html(context);
			$('#success').modal('show');
		},
	
	}
	
	
	//*****************************************************************************
	// common ajax define
	//*****************************************************************************
	
	if($common.ajax) { 
		return $common.ajax; 
	}	
	
	$common.ajax = {
			
		//------------------------------------------------------------------------------
		// execute get ajax
		//------------------------------------------------------------------------------
		get : function (url, formData, callBackOk, callBackNg) {
			
			ShaCommon.dialogs.progress(true);
			
			$.ajax({
				   type: "GET",
				   url: url,
				   data: formData,
				   statusCode: {
					    200: function(data){
					    	callBackOk(data);
					    	ShaCommon.dialogs.progress(false);
					    },
					    404: function() {
					    	alert( "page not found" );
					    	ShaCommon.dialogs.progress(false);
					    },
					    500: function(data){
					    	callBackNg(data);
					    	ShaCommon.dialogs.progress(false);
					    }
				   },
				   timeout: 10000
			});
		},
		
		//------------------------------------------------------------------------------
		// execute post ajax
		//------------------------------------------------------------------------------
		post : function (url, formData, callBackOk, callBackNg) { 
			
			ShaCommon.dialogs.progress(true);
			
			$.ajax({
				   type: "POST",
				   url: url,
				   data: formData,
				   statusCode: {
					    200: function(data){
					    	callBackOk(data);
					    	ShaCommon.dialogs.progress(false);
					    },
					    404: function() {
					    	alert( "page not found" );
					    	ShaCommon.dialogs.progress(false);
					    },
					    500: function(data){
					    	callBackNg(data);
					    	ShaCommon.dialogs.progress(false);
					    }
				   },
				   timeout: 10000
			});
		},
		
		//------------------------------------------------------------------------------
		// execute post with succ ajax
		//------------------------------------------------------------------------------
		postWithSucc : function (url, formData, callBackOk, callBackNg, sucMsg) { 
			
			ShaCommon.dialogs.progress(true);
			
			$.ajax({
				   type: "POST",
				   url: url,
				   data: formData,
				   statusCode: {
					    200: function(data){
					    	callBackOk(data);
					    	ShaCommon.dialogs.progress(false);
					    	ShaCommon.dialogs.confirmClose();
					    	ShaCommon.dialogs.success(sucMsg);
					    },
					    404: function() {
					    	alert( "page not found" );
					    	ShaCommon.dialogs.progress(false);
					    },
					    500: function(data){
					    	callBackNg(data);
					    	ShaCommon.dialogs.progress(false);
					    }
				   },
				   timeout: 10000
			});
		},
	
		//------------------------------------------------------------------------------
		// execute ajax callBackOk
		//------------------------------------------------------------------------------
		callBackOk : function (data) {
			$("#okPanel").show();
			$("#ngPanel").hide();
			$("#okResultBody").html(data);
			$("#ngResultBody").html("");
		},
		
		//------------------------------------------------------------------------------
		// execute ajax callBackNg
		//------------------------------------------------------------------------------
		callBackNg : function (data) {
			$("#okPanel").hide();
			$("#ngPanel").show();
			$("#okResultBody").html("");
			$("#ngResultBody").html(data.responseText);
		}
	}
	
})(ShaCommon);