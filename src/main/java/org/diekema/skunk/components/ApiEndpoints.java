package org.diekema.skunk.components;

/**
 * Created by rdiekema on 2/24/2016.
 */

public interface ApiEndpoints
{
	final static String SCORER_ROOT = "/sanctionslistscorer";
	final static String SCORER_SCORE = SCORER_ROOT + "/api/scorer/score";

	final static String SECURITY_ROOT = SCORER_ROOT + "/api/security";
	final static String CSRF_TOKEN = SECURITY_ROOT + "/csrftoken";
	final static String HANDSHAKE = SECURITY_ROOT + "/handshake";
	final static String LOGIN = SECURITY_ROOT + "/login";
	final static String PING = SECURITY_ROOT + "/ping";

}
