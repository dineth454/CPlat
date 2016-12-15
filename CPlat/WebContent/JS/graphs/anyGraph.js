/**
 * 
 */
var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;
    
    console.log(sPageURL);

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

var query = getUrlParameter('txtarea');
var filteredQuery = query.replace(/\+/g, " ");
console.log(filteredQuery);

var arr;

 $.ajax({
	    url:  '/CPlat/getDetails/DataController/'+ filteredQuery, //this is the url where json format is generate for graph visualization 
	    type: "GET",
	    dataType: 'html',
	    async: false,
         cache: false,
	    success: function (data) {
	           arr=data;
	    }
	            });
 
 //console.log(arr);
arr = JSON.parse(arr); //
//console.log(arr[0]);
var chart = c3.generate({
	bindto: document.getElementById(arr[0]['bindto']),
    data: {
    	xs: arr[1],
     
//        xFormat: '%Y%m%d', // 'xFormat' can be used as custom format of 'x'
        columns:arr[2],
          
         
        
        type:arr[3]['type'],
    },
    axis: {
        x: {
        	type:arr[4]['xtype'],
            
        }
    }
});


