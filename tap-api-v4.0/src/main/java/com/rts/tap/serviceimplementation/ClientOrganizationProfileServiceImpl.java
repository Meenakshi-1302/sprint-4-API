package com.rts.tap.serviceimplementation;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rts.tap.dao.ClientOrganizationProfileDAO;
import com.rts.tap.model.ClientOrganization;
import com.rts.tap.service.ClientOrganizationProfileService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClientOrganizationProfileServiceImpl implements ClientOrganizationProfileService{

	private static final List<String> ALLOWED_CONTENT_TYPES = Arrays.asList("image/jpeg", "image/jpg", "image/png");
	
	private ClientOrganizationProfileDAO clientOrganizationProfileDAO;

	public ClientOrganizationProfileServiceImpl(ClientOrganizationProfileDAO clientOrganizationProfileDAO) {
		super();
		this.clientOrganizationProfileDAO = clientOrganizationProfileDAO;
	}

	@Override
	public String updateOrganizationLogo(Long clientId, MultipartFile organizationLogo) {
		if (organizationLogo == null || organizationLogo.isEmpty()) { 
			return "Organization logo is missing";
		}
		if (organizationLogo.getSize() > 153600) { // 150 KB in bytes
	        return "File size exceeds the limit of 150 KB.";
	    }
		if (!ALLOWED_CONTENT_TYPES.contains(organizationLogo.getContentType())) {
			return "Invalid file type. Only JPG, JPEG, and PNG files are allowed.";
		}
		try {
			ClientOrganization clientOrganization = clientOrganizationProfileDAO.findClientOrganizationByClientId(clientId);
			if (clientOrganization == null) {
				return "Client organization not found";
			}
			byte[] logoBytes = organizationLogo.getBytes();
			clientOrganization.setOrganizationLogo(logoBytes);
			clientOrganizationProfileDAO.updateOrganizationLogo(clientOrganization);
			return "Organization logo updated successfully!";
		} catch (IOException e) {
			return "Error updating organization logo: " + e.getMessage();
		}
	}
	
}
