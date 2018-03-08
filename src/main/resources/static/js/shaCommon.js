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
		}
	}
	
})(ShaCommon);