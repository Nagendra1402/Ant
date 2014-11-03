public class EntitlementCacheKey {

	private static final Log log = LogFactory.getLog(EntitlementCacheKey.class);
	private String key;

	/**
	 * Constructor takes four parameters to build a cache key
	 * 
	 * @param appId
	 * @param B2BPartnerPrincipalId
	 * @param requestorId
	 * @param entitlement
	 * 
	 */
	public EntitlementCacheKey(String appId, String B2BPartnerPrincipalId,
			String requestorId, String entitlement) {
		if (log.isDebugEnabled())
			log.debug("appId: [" + appId + "] B2BPartnerPrincipalId: ["
					+ B2BPartnerPrincipalId + "] requestorId: [" + requestorId
					+ "] entitlement: [" + entitlement + "]");

		StringBuilder sbKey = new StringBuilder();
		if (appId != null) {
			sbKey.append(ECAUtils.asHex(appId.getBytes())).append("-");
		}
		if (B2BPartnerPrincipalId != null) {
			sbKey.append(ECAUtils.asHex(B2BPartnerPrincipalId.getBytes()))
					.append("-");
		}
		if (requestorId != null) {
			sbKey.append(ECAUtils.asHex(requestorId.getBytes())).append("-");
		}
		if (entitlement != null) {
			sbKey.append(ECAUtils.asHex(entitlement.getBytes())).append("-");
		}

		key = sbKey.toString();
		if (log.isDebugEnabled())
			log.debug(this.toString());

	}

	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((key == null) ? 0 : key.hashCode());
		if (log.isDebugEnabled())
			log.debug(key + " " + result);
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final EntitlementCacheKey other = (EntitlementCacheKey) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	public int compareTo(EntitlementCacheKey other) {
		return key.compareTo(other.key);
	}

	public String toString() {
		return ("The cache key: [" + this.key + "] ");
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

}
