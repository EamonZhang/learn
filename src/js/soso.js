window.soso=window.soso||
{};soso.maps=soso.maps||
{};(
		function(){
			function getScript(src){
				document.write('<' + 'script src="' + src + '"' +' type="text/javascript"><' + '/script>');
				}
			soso.maps.__load=function(apiLoad){
				delete soso.maps.__load;
				apiLoad(["http://open.map.qq.com/apifiles/v1.0","","1.0.131203.1"]);};
				var loadScriptTime=(new Date).getTime();
				getScript("http://open.map.qq.com/apifiles/v1.0/app.js?v=1.0.131203.1");
				})();

var map,
polyline,
stations_label = [],
lines_id = [],
searchService = new soso.maps.SearchService(),
lineService = new soso.maps.LineService();
function init() {
	//map = new soso.maps.Map(document.getElementById("container"), {
    // 地图的中心地理坐标。
   // center: new soso.maps.LatLng(39.916527,116.397128)
//});
}

function searchKeyword(keyword,region) {
	
//    var keyword = document.getElementById("keyword").value;
//    var region = document.getElementById("region").value;
   
    var request = {
        keyword: keyword,
        region:region,
        searchMode:soso.maps.SearchMode.BUS
    };
    var latlngBounds = new soso.maps.LatLngBounds();
    lines_id.length=0;
    searchService.search(request, function(response, status) {
        if (status == soso.maps.SearchStatus.OK) {
            var pois = response.pois;
            var info_html = [];
            for(var i = 0,l = pois.length;i < l; i++){
                var poi = pois[i];
            }
           }
        });
    return "good";
}