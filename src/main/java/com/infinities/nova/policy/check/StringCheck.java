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
package com.infinities.nova.policy.check;

import com.infinities.nova.policy.BaseCheck;
import com.infinities.nova.policy.Credentials;
import com.infinities.nova.policy.Enforcer;
import com.infinities.nova.policy.Target;

public class StringCheck implements BaseCheck {

	private final String sequence;


	public StringCheck(String sequence) {
		this.sequence = sequence;
	}

	@Override
	public String getRule() {
		return sequence;
	}

	@Override
	public boolean check(Target target, Credentials creds, Enforcer enforcer) {
		return false;
	}

}
