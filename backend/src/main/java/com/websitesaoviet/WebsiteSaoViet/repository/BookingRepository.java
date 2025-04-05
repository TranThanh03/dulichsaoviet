package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {

}