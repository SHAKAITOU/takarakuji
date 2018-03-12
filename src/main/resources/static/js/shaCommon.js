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
		
		triggerWinResize : function(callFun) {
			$(window).resize(function() {
				callFun();
			}).trigger("resize");
		}
	
	}
	
	
	//*****************************************************************************
	// common ajax define
	//*****************************************************************************
	
	if($common.ajax) { 
		return $common.ajax; 
	}	
	
	$common.ajax = {
			
		//------------------------------------------------------------------------------
		// execute ajax
		//------------------------------------------------------------------------------
		execute : function (url, type, formData, callBackOk, callBackNg) { 
			$.ajax({
				   type: type,
				   url: url,
				   data: formData,
				   statusCode: {
					    200: function(data){
					    	callBackOk(data);
					    },
					    404: function() {
					      alert( "page not found" );
					    },
					    500: function(data){
					    	callBackNg(data);
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