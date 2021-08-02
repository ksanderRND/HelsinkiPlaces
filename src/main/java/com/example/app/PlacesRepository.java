package com.example.app;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacesRepository extends JpaRepository<Place, Long> {
}
