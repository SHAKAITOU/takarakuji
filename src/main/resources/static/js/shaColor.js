try{
	ShaColor;
}catch(e){
	ShaColor = {};
}

(function($color) {
	
	//*****************************************************************************
	// html color define
	//*****************************************************************************
	
	if($color.htmlColor) { 
		return $color.htmlColor; 
	}	
	
	
	$color.htmlColor = {
			
		randomColor : function (size, colorFactory) {
			var randomColor = [];
			var min = 0;
			var max = colorFactory.length;
			var usedNumber = [];
			for(i=0; i<size; i++) {
				var rdNum;
				do {
					rdNum = ShaCommon.util.getRandomInt(min, max);
					var existFlg = false;
					for(j=0; j<usedNumber.length; j++) {
						if(usedNumber[j] == rdNum) {
							existFlg = true;
							break;
						}
					}
				}while(existFlg);
				usedNumber.push(rdNum);
				randomColor.push(colorFactory[rdNum]);
			}
			return randomColor;
		},
			
		jpColor : [
			"#fef4f4",
			"#96514d",
			"#e6b422",
			"#006e54",
			"#895b8a",
			"#fdeff2",
			"#8d6449",
			"#d9a62e",
			"#00a381",
			"#824880",
			"#e9dfe5",
			"#deb068",
			"#d3a243",
			"#38b48b",
			"#915c8b",
			"#e4d2d8",
			"#bf794e",
			"#c89932",
			"#00a497",
			"#9d5b8b",
			"#f6bfbc",
			"#bc763c",
			"#d0af4c",
			"#80aba9",
			"#7a4171",
			"#f5b1aa",
			"#b98c46",
			"#8b968d",
			"#5c9291",
			"#bc64a4",
			"#f5b199",
			"#b79b5b",
			"#6e7955",
			"#478384",
			"#b44c97",
			"#efab93",
			"#b77b57",
			"#767c6b",
			"#43676b",
			"#aa4c8f",
			"#e5abbe",
			"#956f29",
			"#726250",
			"#4c6473",
			"#a69abd",
			"#e597b2",
			"#8c7042",
			"#9d896c",
			"#455765",
			"#a89dac",
			"#e198b4",
			"#7b6c3e",
			"#94846a",
			"#44617b",
			"#9790a4",
			"#e4ab9b",
			"#d8a373",
			"#897858",
			"#393f4c",
			"#9e8b8e",
			"#e09e87",
			"#cd8c5c",
			"#716246",
			"#393e4f",
			"#95859c",
			"#d69090",
			"#cd5e3c",
			"#cbb994",
			"#203744",
			"#95949a"
		]
	}
	
})(ShaColor);