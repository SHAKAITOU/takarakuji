
try{
	ShaChart;
}catch(e){
	ShaChart = {};;
}

(function($chart) {
	
	
	if($chart.barChart) return $chart.barChart;
	
	$chart.barChart = {

		//------------------------------------------------------------------------------
		// draw a simple bar chart
		//------------------------------------------------------------------------------
		simpleBarChart : function (svg, svgWidth, svgHeight, data, 
									getValue, barColor,
									title, xTitle) {

			var margin = {top: 20, right: 30, bottom: 40, left: 30};
			var width = svgWidth - margin.left - margin.right;
			var height = svgHeight - margin.top - margin.bottom;
			
			var x = d3.scaleBand().rangeRound([0, width]).paddingInner(0.1);
			
			var y = d3.scaleLinear().range([height, 0]);
			
			var chart = svg.attr("width", width + margin.left + margin.right)
			    .attr("height", height + margin.top + margin.bottom)
			  	.append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");
			
			//add title
			
			chart.append("text")
			.attr("x", (width / 2))
			.attr("y", (margin.top / 2))
			.attr("text-anchor", "middle")  
			.style("font-size", "16px") 
			.style("font-weight", "bold")
			.text(title);
			
			//bottom comment
			chart.append("text")
			.attr("x", width)
			.attr("y", height + margin.top + 10)
			.attr("text-anchor", "end")  
			.style("font-size", "12px") 
			.text(xTitle);
			
			var chartTopMargin = 5;
			x.domain(data.map(function(d) { return d.numberName; }));
			y.domain([0, d3.max(data, function(d) { return getValue(d); })+chartTopMargin]);
			

			d3.select(".d3-tip").remove();
			var numberTip = d3.tip().attr("class", "d3-tip")
							.offset([-10, 0])
							.html(function(d) {
								return "<strong>出た回数:</strong> <span style='color:red'>" + getValue(d) + "</span>";
							});
			
			chart.call(numberTip);
			
			var bar = chart.selectAll("g")
				.data(data)
				.enter().append("g")
				.attr("transform", function(d) { return "translate(" + x(d.numberName) + ",0)"; });
			
			bar.append("rect")
			.attr("y", function(d) { return y(getValue(d)); })
			.attr("width",x.bandwidth())
			.attr("class", "bar")
			.attr("fill", barColor)
			.on('mouseover', numberTip.show)
			.on('mouseout', numberTip.hide)
			.attr("height", 0)
					.transition()
					.duration(400)
					.delay(function(d) { return height - y(getValue(d)); })
			.attr("height", function(d) { return height - y(getValue(d)); });
			
			bar.append("text")
			.attr("x", x.bandwidth() / 2)
			.attr("y", function(d) { return y(getValue(d)) + 3; })
			.attr("dy", ".71em")
			.text(function(d) { return ""; });
			
			var xAxis = d3.axisBottom().scale(x);
			var yAxis = d3.axisLeft().scale(y);
			
			chart.append("g")
			.attr("class", "axis")
			.attr("transform", "translate(0," + height + ")")
			.call(xAxis);
			
			chart.append("g")
			.attr("class", "axis")
			.call(yAxis)
			.append("text")
			.attr("transform", "rotate(90)")
			.attr("x", height-10)
			.attr("y", y(y.ticks().pop()) + 25)
			.attr("dy", "0.32em")
			.attr("fill", "#000")
			.attr("font-weight", "bold")
			.attr("text-anchor", "start")
			//.text("回数")
			;
		},
	
		//------------------------------------------------------------------------------
		// draw a group bar chart
		//------------------------------------------------------------------------------
		groupBarChart : function(svg, svgWidth, svgHeight, data, 
								rangeColor, keys, names,
								title, xTitle) {
			
		    var margin = {top: 30, right: 30, bottom: 40, left: 40};
		    var width = svgWidth - margin.left - margin.right;
		    var height = svgHeight - margin.top - margin.bottom;
			var legendTopMargin = 20;
			
			var x0 = d3.scaleBand().rangeRound([0, width]).paddingInner(0.1);
		
			var x1 = d3.scaleBand().padding(0.05);
		
			var y = d3.scaleLinear().range([height, 0]);
			
			var z = d3.scaleOrdinal().range(rangeColor);
		
			data.forEach(function(d) {
				d.numbers = keys.map(function(name) { return {name: name, value: +d[name]}; });
			});
			

			x0.domain(data.map(function(d) { return d.numberName; }));
			x1.domain(keys).rangeRound([0, x0.bandwidth()]);
			y.domain([0, d3.max(data, function(d) { return d3.max(d.numbers, function(d) { return d.value; }); })+legendTopMargin]);
		
			//get a chart
			var chart =svg.attr("width", width + margin.left + margin.right)
							.attr("height", height + margin.top + margin.bottom)
							.append("g")
					  			.attr("transform", "translate(" + margin.left + "," + margin.top + ")");
			
			//add title
			
			chart.append("text")
					.attr("x", (width / 2))
					.attr("y", (margin.top / 2))
					.attr("text-anchor", "middle")  
					.style("font-size", "16px") 
					.style("font-weight", "bold")
					.text(title);
			
			//bottom comment
			chart.append("text")
					.attr("x", width)
					.attr("y", height + margin.top + 5)
					.attr("text-anchor", "end")  
					.style("font-size", "12px") 
					.text(xTitle);
			
			//append bar in chart
			var bar =
				chart.append("g")
					.selectAll("g")
						//bond data
						.data(data)
						.enter().append("g")
							.attr("transform", function(d) { return "translate(" + x0(d.numberName) + ",0)"; });
			
			//active tooptip
			d3.select(".d3-tip").remove();
			var numberTip = d3.tip().attr("class", "d3-tip")
								.offset([-10, 0])
								.html(function(d) {
									return "<strong>出た回数:</strong> <span style='color:red'>" + d.value + "</span>";
								});
			d3.select("body").append("svg").call(numberTip);
			
			bar.selectAll("rect")
				.data(function(d) { return d.numbers; })
				.enter().append("rect")
					.attr("class", "bar")
					.attr("x", function(d) { return x1(d.name); })
					.attr("y", function(d) { return y(d.value); })
					.attr("width", x1.bandwidth())
					.attr("fill", function(d) { return z(d.name); })
					.on('mouseover', numberTip.show)
					.on('mouseout', numberTip.hide)
					.attr("height", 0)
					.transition()
					.duration(400)
					.delay(function(d) { return height - y(d.value); })
					.attr("height", function(d) { return height - y(d.value); });
			
			
			
			//append x axis
			chart.append("g")
				.attr("class", "axis")
				.attr("transform", "translate(0," + height + ")")
				.call(d3.axisBottom(x0));
		
			//append y axis
			chart.append("g")
				.attr("class", "axis")
				.call(d3.axisLeft(y).ticks(null, "s"))
				.append("text")
				    .attr("x", 2)
				    .attr("y", y(y.ticks().pop()) + 0.5)
				    .attr("dy", "0.32em")
				    .attr("fill", "#000")
				    .attr("font-weight", "bold")
				    .attr("text-anchor", "start")
				    .text("");
			
			//append legend
			var legend = 
				chart.append("g")
					.attr("font-family", "sans-serif")
					.attr("font-size", 10)
					.attr("text-anchor", "end")
					.selectAll("g")
						.data(names.slice())
						.enter().append("g")
							.attr("transform", function(d, i) { return "translate(0," + i * 20 + ")"; });
		
			var yLegend = 0;
		
			legend.append("rect")
					.attr("x", width - 19)
					.attr("y", yLegend)
					.attr("width", 19)
					.attr("height", 19)
					.attr("fill", z);
		
			legend.append("text")
					.attr("x", width - 24)
					.attr("y", yLegend+9.5)
					.attr("dy", "0.32em")
					.text(function(d) { return d; });	
		},
		
		//------------------------------------------------------------------------------
		// draw a bar and pie chart
		//------------------------------------------------------------------------------
		barAndPieChart : function(svg, svgWidth, svgHeight, data, numberTip, 
								rangeColor, keys, names,
								title, xTitle) {
			
		}
	}
	
	
})(ShaChart);
