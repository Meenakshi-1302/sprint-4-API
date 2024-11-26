package com.rts.tap.dao;

import com.rts.tap.model.ClientOrganization;

public interface ClientOrganizationProfileDAO {

	ClientOrganization findClientOrganizationByClientId(Long clientId);
	ClientOrganization updateOrganizationLogo(ClientOrganization clientOrganization);
}
