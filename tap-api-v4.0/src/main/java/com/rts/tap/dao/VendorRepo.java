package com.rts.tap.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rts.tap.model.Candidate;

public interface VendorRepo extends JpaRepository<Candidate, Long> {

}
