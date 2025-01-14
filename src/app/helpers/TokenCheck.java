package app.helpers;

import org.apache.commons.lang.StringUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public class TokenCheck {
	public static boolean isValidJWT(String jwt) {
		int dotCount = StringUtils.countMatches(jwt, ".");
		if (dotCount !=2) {
			return false;
		}
		
		jwt=jwt.trim();
		if(StringUtils.contains(jwt," ")){
			return false;
		}

		String[] sArray=StringUtils.split(jwt,".");

		for(String value:sArray){
			if(!value.matches("[A-Za-z0-9+/=_-]+")){
				return false;
			}
		}

		try {
			DecodedJWT decoded = JWT.decode(jwt);
			decoded.getAlgorithm();
			return true;
		} catch (Exception exception) {}
		
		return false;
	}
}
