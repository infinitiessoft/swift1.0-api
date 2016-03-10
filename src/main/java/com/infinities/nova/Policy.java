/*******************************************************************************
 * Copyright 2015 InfinitiesSoft Solutions Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/
package com.infinities.nova;

import java.util.Map;

import com.infinities.nova.exception.PolicyNotAuthorizedException;
import com.infinities.nova.policy.BaseCheck;
import com.infinities.nova.policy.Credentials;
import com.infinities.nova.policy.Enforcer;
import com.infinities.nova.policy.EnforcerImpl;
import com.infinities.nova.policy.Target;

public class Policy {

	private static Enforcer enforcer = null;


	private Policy() {

	}

	private synchronized static void init(String policyFile, Map<String, BaseCheck> rules, String defaultRule,
			boolean useConf) {
		if (enforcer == null) {
			enforcer = new EnforcerImpl(policyFile, rules, defaultRule, useConf);
		}
	}

	public static boolean checkIsAdmin(NovaRequestContext context) throws Exception {
		init(null, null, null, true);

		Credentials credentials = context;
		Target target = context;
		return enforcer.enforce("context_is_admin", target, credentials, false, null);
	}

	// doRaise=true, exc=null
	public static boolean enforce(NovaRequestContext context, String action, Target target, boolean doRaise, Exception exc)
			throws Exception {
		init(null, null, null, true);
		Credentials credentials = context;
		if (exc == null) {
			exc = new PolicyNotAuthorizedException(null, action);
		}

		return enforcer.enforce(action, target, credentials, doRaise, exc);

	}
}
