package hanlp;

import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

public class Recogize {

	public static void main(String[] args) {
		//机构名识别
//		Segment segment = HanLP.newSegment().enableOrganizationRecognize(true);
//		    List<Term> termList = segment.seg("东北大学北门");
//		    System.out.println(termList);
		    //==========[东北大学/ntu, 北门/n]
		//地名识别
		    Segment segment = HanLP.newSegment().enableNameRecognize(true);
		    List<Term> termList = segment.seg("东北大学北门");
		    System.out.println(termList);
	}

}
