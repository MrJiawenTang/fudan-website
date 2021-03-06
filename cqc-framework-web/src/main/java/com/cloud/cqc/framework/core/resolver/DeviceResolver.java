package com.cloud.cqc.framework.core.resolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cloud.cqc.framework.core.constant.Device;

/**
 * 设备类型解析
 * 
 * @author joy.zhou
 * @date 2017年5月12日
 */
public class DeviceResolver {

	private final List<String> mobileUserAgentPrefixes = new ArrayList<String>();

	private final List<String> mobileUserAgentKeywords = new ArrayList<String>();

	private final List<String> tabletUserAgentKeywords = new ArrayList<String>();

	private final List<String> normalUserAgentKeywords = new ArrayList<String>();

	public DeviceResolver() {
		init();
	}

	public DeviceResolver(List<String> normalUserAgentKeywords) {
		init();
		this.normalUserAgentKeywords.addAll(normalUserAgentKeywords);
	}

	public Device resolveDevice(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		// UserAgent keyword detection of Normal devices
		if (userAgent != null) {
			userAgent = userAgent.toLowerCase();
			for (String keyword : normalUserAgentKeywords) {
				if (userAgent.contains(keyword)) {
					return resolveFallback(request);
				}
			}
		}
		// UAProf detection
		if (request.getHeader("x-wap-profile") != null || request.getHeader("Profile") != null) {
			return Device.MOBILE;
		}
		// User-Agent prefix detection
		if (userAgent != null && userAgent.length() >= 4) {
			String prefix = userAgent.substring(0, 4).toLowerCase();
			if (mobileUserAgentPrefixes.contains(prefix)) {
				return Device.MOBILE;
			}
		}
		// Accept-header based detection
		String accept = request.getHeader("Accept");
		if (accept != null && accept.contains("wap")) {
			return Device.MOBILE;
		}
		// UserAgent keyword detection for Mobile and Tablet devices
		if (userAgent != null) {
			userAgent = userAgent.toLowerCase();
			// Android special case
			if (userAgent.contains("android") && !userAgent.contains("mobile")) {
				return Device.TABLET;
			}
			// Kindle Fire special case
			if (userAgent.contains("silk") && !userAgent.contains("mobile")) {
				return Device.TABLET;
			}
			for (String keyword : tabletUserAgentKeywords) {
				if (userAgent.contains(keyword)) {
					return Device.TABLET;
				}
			}
			for (String keyword : mobileUserAgentKeywords) {
				if (userAgent.contains(keyword)) {
					return Device.MOBILE;
				}
			}
		}
		// OperaMini special case
		@SuppressWarnings("rawtypes")
		Enumeration headers = request.getHeaderNames();
		while (headers.hasMoreElements()) {
			String header = (String) headers.nextElement();
			if (header.contains("OperaMini")) {
				return Device.MOBILE;
			}
		}
		return resolveFallback(request);
	}

	// subclassing hooks

	/**
	 * List of user agent prefixes that identify mobile devices. Used primarily
	 * to match by operator or handset manufacturer.
	 */
	protected List<String> getMobileUserAgentPrefixes() {
		return mobileUserAgentPrefixes;
	}

	/**
	 * List of user agent keywords that identify mobile devices. Used primarily
	 * to match by mobile platform or operating system.
	 */
	protected List<String> getMobileUserAgentKeywords() {
		return mobileUserAgentKeywords;
	}

	/**
	 * List of user agent keywords that identify tablet devices. Used primarily
	 * to match by tablet platform or operating system.
	 */
	protected List<String> getTabletUserAgentKeywords() {
		return tabletUserAgentKeywords;
	}

	/**
	 * List of user agent keywords that identify normal devices. Any items in
	 * this list take precedence over the mobile and tablet user agent keywords,
	 * effectively overriding those.
	 */
	protected List<String> getNormalUserAgentKeywords() {
		return normalUserAgentKeywords;
	}

	/**
	 * Initialize this device resolver implementation. Registers the known set
	 * of device signature strings. Subclasses may override to register
	 * additional strings.
	 */
	protected void init() {
		getMobileUserAgentPrefixes().addAll(Arrays.asList(KNOWN_MOBILE_USER_AGENT_PREFIXES));
		getMobileUserAgentKeywords().addAll(Arrays.asList(KNOWN_MOBILE_USER_AGENT_KEYWORDS));
		getTabletUserAgentKeywords().addAll(Arrays.asList(KNOWN_TABLET_USER_AGENT_KEYWORDS));
	}

	/**
	 * Fallback called if no mobile device is matched by this resolver. The
	 * default implementation of this method returns a "normal" {@link Device}
	 * that is neither mobile or a tablet. Subclasses may override to try
	 * additional mobile or tablet device matching before falling back to a
	 * "normal" device.
	 */
	protected Device resolveFallback(HttpServletRequest request) {
		return Device.NORMAL;
	}

	// internal helpers

	private static final String[] KNOWN_MOBILE_USER_AGENT_PREFIXES = new String[] { "w3c ", "w3c-", "acs-", "alav",
			"alca", "amoi", "audi", "avan", "benq", "bird", "blac", "blaz", "brew", "cell", "cldc", "cmd-", "dang",
			"doco", "eric", "hipt", "htc_", "inno", "ipaq", "ipod", "jigs", "kddi", "keji", "leno", "lg-c", "lg-d",
			"lg-g", "lge-", "lg/u", "maui", "maxo", "midp", "mits", "mmef", "mobi", "mot-", "moto", "mwbp", "nec-",
			"newt", "noki", "palm", "pana", "pant", "phil", "play", "port", "prox", "qwap", "sage", "sams", "sany",
			"sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem", "smal", "smar", "sony", "sph-", "symb",
			"t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v", "voda", "wap-", "wapa", "wapi", "wapp",
			"wapr", "webc", "winw", "winw", "xda ", "xda-" };

	private static final String[] KNOWN_MOBILE_USER_AGENT_KEYWORDS = new String[] { "blackberry", "webos", "ipod",
			"lge vx", "midp", "maemo", "mmp", "mobile", "netfront", "hiptop", "nintendo DS", "novarra", "openweb",
			"opera mobi", "opera mini", "palm", "psp", "phone", "smartphone", "symbian", "up.browser", "up.link", "wap",
			"windows ce" };

	private static final String[] KNOWN_TABLET_USER_AGENT_KEYWORDS = new String[] { "ipad", "playbook", "hp-tablet",
			"kindle" };

}
