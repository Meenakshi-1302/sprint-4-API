package com.rts.tap.service;

import org.springframework.web.multipart.MultipartFile;

public interface ClientOrganizationProfileService {
	String updateOrganizationLogo(Long clientId, MultipartFile organizationLogo);
}
