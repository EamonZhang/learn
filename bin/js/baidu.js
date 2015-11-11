function getx(bd_lat, bd_lon) {
        x = bd_lon - 0.0065, y = bd_lat - 0.006;
        z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * 3.14159265358979324 * 3000.0 / 180.0);
        theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * 3.14159265358979324 * 3000.0 / 180.0);
        return z * Math.cos(theta);
    }
    function gety(bd_lat, bd_lon) {
        x = bd_lon - 0.0065, y = bd_lat - 0.006;
        z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * 3.14159265358979324 * 3000.0 / 180.0);
        theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * 3.14159265358979324 * 3000.0 / 180.0);
        return z * Math.sin(theta);
    }
    var global = {};
    global.tempVar = {};
    global.index = 0;
    global.lineNo = 0;
    global.lineCity = 0;
    var busline = new BMap.BusLineSearch('', {
       renderOptions: { panel: "results" },
        onGetBusListComplete: function (result) {
            if (result) {
                 global.tempVar = result;
            }
        },
        onGetBusLineComplete: function (result) {
            if (result) {
                getLineCoordinate(result.getPath());
            }
        }, 
        onBusLineHtmlSet: function () {
            try {
                getCoordinate(global.tempVar);
            } catch (e) {
            }
        }
    });
    function search(lineCity,lineNo){
	    global.lineNo = lineNo;
	    global.lineCity = lineCity;
	    busline.setLocation(global.lineCity);
	    busline.getBusList(global.lineNo);
	    return global.tempVar;
    }
    
    function getCoordinate(result) {
        var stations = result[global.index]._stations;
        var html = [];
        stations.forEach(function (item, index) {
            html.push(item.name.replace(",","，") + ',' + getx(item.position.lat, item.position.lng) + ',' + gety(item.position.lat, item.position.lng) + ',');
        });
       // html.push('站点完毕');
        return html;
    }