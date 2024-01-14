package org.example.apps.storage.repository;

import org.example.apps.storage.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rival
 * @since 2023-12-30
 */
public interface ImageRepository extends JpaRepository<Image, Long> {
}
