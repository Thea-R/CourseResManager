package forDao;

import java.io.UnsupportedEncodingException;

public class Trans {
	public String to(String str) throws UnsupportedEncodingException {
		String ret=new String(str.getBytes("ISO8859_1"),"UTF-8");
		return ret;
	}
}
