package utility;

import java.io.IOException;

public class VerifyRecaptcha {

	public static final String url = "https://www.google.com/recaptcha/api/siteverify";
	public static final String secret = "6Ld7ZFEcAAAAAHS15Z1ScYQ0BA4EVIYbEdt5DNu4";

	public static boolean verify(String gRecaptchaResponse) throws IOException {
		if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
			return false;
		}
		return true; //if no click recatcha, return false
	}
}