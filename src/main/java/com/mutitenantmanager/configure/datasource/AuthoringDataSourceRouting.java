package com.mutitenantmanager.configure.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class AuthoringDataSourceRouting extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return TenantContext.getCurrentTenant();
	}

}
