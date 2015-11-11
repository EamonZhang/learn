package mybatis;


import org.apache.ibatis.annotations.Select;

import mybatis.pojo.Poi;

public interface PoiMapper {
	
	@Select("")
	public Poi findById(String Id);
}
