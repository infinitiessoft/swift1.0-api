package com.infinities.swift.accounts.api;

import java.util.List;
import java.util.Map;

import com.infinities.nova.NovaRequestContext;
import com.infinities.swift.accounts.model.Container;


public interface AccountsApi {

	/**
	 * @param context
	 * @return
	 * @throws Exception
	 */
	List<Container> getContainers(NovaRequestContext context) throws Exception;

	/**
	 * @param context
	 * @return
	 * @throws Exception
	 */
	Map<String, String> getAccountMetadata(NovaRequestContext context) throws Exception;
}
