package forDao;

import java.io.UnsupportedEncodingException;

public class Trans {
	public String to(String str) throws UnsupportedEncodingException {
		String ret=new String(str.getBytes("ISO8859_1"),"UTF-8");
		return ret;
	}
	public boolean db(String str) {
		if(str.charAt(0)=='.')	return false;
		for(int i=0, j=0; i<str.length(); i++) {
			char ch=str.charAt(i);
			if(ch>='0' && ch<='9')	continue;
			if(ch=='.' && j==0)	{
				j++;
				continue;
			}
			return false;
		}
		return true;
	}
}
