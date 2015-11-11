package httpclient;

import java.io.Serializable;
import java.util.Date;

public class PoiData implements Serializable {
	public String simple_address;
	
	public Date add_time;
	public Date last_online_time;
	public Date updata_time;
	//原始类型
	public String OriginalType;
	//原始品牌
	public String OriginalBrand;
	//母库sunmapid
	public String isunmapId;

	public String id;
	transient public String name0;
	transient public String name1;
//	transient public String name2;
	public float lat;
	public float lon;
	// -----------------
	transient public int matchType;
	// -----------
	public String name;
	public String type;
	public String type_format;
	// transient public String cityName;
	public String areaName;
	public String tel;
	public String address;
	public String city;
	public String cityCode;

	// -------------------------------------
	// 与之匹配的数据id
	public String matchId;

	

	@Override
	public String toString() {
		return "ID	" + id + "	name	" + name + "	tel	" + tel
				+ "	adress	" + address + "	" + lon + "	" + lat + "	" + type;
	}

	public String printString() {
		return id + "	" + name + "	" + tel + "	" + address + "	" + lon
				+ "	" + lat;
	}

	@Override
	public boolean equals(Object obj) {
		PoiData poiData = (PoiData) obj;
		if (!this.id.equals(poiData.id)) {
			return false;
		}
		if ((this.address != null && poiData.address == null)
				|| (this.address == null && poiData.address != null)) {
			return false;
		}
		if (this.address != null && poiData.address != null
				&& !this.address.equals(poiData.address)) {
			return false;
		}
		if ((this.tel != null && poiData.tel == null)
				|| (this.tel == null && poiData.tel != null)) {
			return false;
		}
		if (this.tel != null && poiData.tel != null
				&& !this.tel.equals(poiData.tel)) {
			return false;
		}
		return true;
	}
}
