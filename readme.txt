implements SessionAware

//接收session的map
	private Map<String, Object> sessionMap;
	
	@Override
	public void setSession(Map<String, Object> arg0)
	{
		this.sessionMap = arg0;
	}

	sessionMap.put("user", user);
