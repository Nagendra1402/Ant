public class EntitlementCacheManager{
	
	private static final Log log = LogFactory.getLog(EntitlementCacheManager.class);

	private static final String DEFAULT_CACHE_NAME = "AUTH_CACHE";
	
	private CacheManager cacheManager;
	private String cacheName;
	
	/**
	 * Check if a valid key is available in cache
	 * 
	 * @param cachedKey
	 * @return
	 * 
	 */
	public boolean hasValidCachedKey(EntitlementCacheKey entCacheKey){
		if (entCacheKey == null)
			return false;
		String cachedKey = entCacheKey.getKey();
		if (!StringUtils.isNotEmpty(cachedKey))
			return false;
		Element cachedEl = getCachedElement(cachedKey);
		if (cachedEl == null || cachedEl.getValue() == null)
			return false;		
		return true;
	}
	
	/**
	 * 
	 * @param entCacheKey
	 * @param status
	 */
	public void addToCache(EntitlementCacheKey entCacheKey, AuthorizationStatus status){	
		try{
			if(status!=null){
				
				Element cachingEl = new Element(entCacheKey.getKey(), status);
				// This will not update statistics, that are not required.		
				getCache().putQuiet(cachingEl);	
			}
		} catch (IllegalStateException ise) {
			log.warn(ise.getMessage(), ise);
		} catch (IllegalArgumentException iae) {
			log.warn(iae.getMessage(), iae);
		} catch (CacheException ce){
			log.warn(ce.getMessage(), ce);
		}
	}
	
	/**
	 * 
	 * @param cacheKey
	 * @return
	 * Gets the cached status
	 */
	public AuthorizationStatus getCachedStatus(String cacheKey)
	{
		AuthorizationStatus status = null;
		try{
			if(cacheKey != null)
			{
				Element cachedEl = getCachedElement(cacheKey);
				if(cachedEl != null && cachedEl.getValue() != null && (cachedEl.getValue() instanceof AuthorizationStatus))
				{
					status = (AuthorizationStatus) cachedEl.getValue();
				}
			}
		}catch(Exception ex){
			log.warn(ex.getMessage());
		}
		
		return status;
	}
	
	/**
	 * Gets the cached element using the key
	 * 
	 * @param key
	 * @return
	 */
	public Element getCachedElement(String key){
		Element cachedEl = null;
		try {
			Cache cache = getCache();
			if (cache != null){
				if (log.isDebugEnabled())
					log.debug("Getting Entitlement from cache");
				// This will not update statistics, that are not required.		
				cachedEl = cache.getQuiet(key);
				if (log.isDebugEnabled())
					log.debug("cachedEl: " + cachedEl);
			}
		} catch (IllegalStateException ise) {
			log.warn(ise.getMessage(), ise);
		} catch (IllegalArgumentException iae) {
			log.warn(iae.getMessage(), iae);
		} catch (CacheException ce){
			log.warn(ce.getMessage(), ce);
		}
		return cachedEl;
	}
	
	/*
	 * Gets the cache object
	 */
	public Cache getCache(){ 
		if (cacheManager != null)
			 return cacheManager.getCache(getCacheName());
		return null;
	}
	/**
	 * @param cacheManager the cacheManager to set
	 */
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	/**
	 * @return the cacheManager
	 */
	public CacheManager getCacheManager() {
		return cacheManager;
	}
	/**
	 * @param cacheName the cacheName to set
	 */
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
	/**
	 * @return the cacheName
	 */
	public String getCacheName() {
		return cacheName.equals("null") || StringUtils.isBlank(cacheName) ? DEFAULT_CACHE_NAME : cacheName;
	}
