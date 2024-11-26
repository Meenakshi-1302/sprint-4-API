package com.rts.tap.daoimplementation;

import org.springframework.stereotype.Repository;

import com.rts.tap.dao.VendorOrganizationProfileDAO;
import com.rts.tap.dto.VendorDto;
import com.rts.tap.dto.VendorProfileDto;
import com.rts.tap.model.Vendor;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class VendorOrganizationProfileDAOImpl implements VendorOrganizationProfileDAO {

	private EntityManager entityManager;

	public VendorOrganizationProfileDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public Vendor findVendorOrganizationByVendorId(Long vendorId) {
		String hql = "FROM Vendor v WHERE v.vendorId = :vendorId";
		Query q = entityManager.createQuery(hql, Vendor.class);
		q.setParameter("vendorId", vendorId);
		return (Vendor) q.getSingleResult();
	}

	@Override
	public Vendor updateVendorOrganizationLogo(Vendor vendor) {
		return entityManager.merge(vendor);
	}

	@Override
	public Vendor updateVendorById(Long vendorId, VendorProfileDto vendorDto) {
		Vendor v = entityManager.find(Vendor.class, vendorId);
		v.toString();
		System.out.println(vendorDto.getAddress());

		if (v != null) {
			if (vendorDto.getAddress() != null) {
				v.setAddress(vendorDto.getAddress());
			}
			if (vendorDto.getContactName() != null) {
				v.setContactName(vendorDto.getContactName());
			}
			if (vendorDto.getContactNumber() != null) {
				v.setContactNumber(vendorDto.getContactNumber());
			}
			if (vendorDto.getWebsiteUrl() != null) {
				v.setWebsiteUrl(vendorDto.getWebsiteUrl());
			}

			entityManager.merge(v);
			System.out.println(v);
		}

		return v;
	}
	
	@Override
    public void updateVendor(Vendor vendor) {
        entityManager.merge(vendor);
    }
	@Override
    public Vendor findVendorById(Long vendorId) {
        return entityManager.find(Vendor.class, vendorId);
    }
}
