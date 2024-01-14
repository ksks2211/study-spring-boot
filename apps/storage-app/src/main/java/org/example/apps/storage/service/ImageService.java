package org.example.apps.storage.service;

import lombok.RequiredArgsConstructor;
import org.example.apps.storage.dto.ImageDto;
import org.example.apps.storage.entity.Image;
import org.example.apps.storage.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author rival
 * @since 2023-12-30
 */

@Service
@RequiredArgsConstructor
public class ImageService {
    private final MinioStorageService storageService;
    private final ImageRepository imageRepository;


    private String generateRandomFilename() {
        LocalDate currentDate = LocalDate.now();
        String year = String.valueOf(currentDate.getYear());
        String month = String.format("%02d", currentDate.getMonthValue());
        String day = String.format("%02d", currentDate.getDayOfMonth());
        String uuid = UUID.randomUUID().toString();
        return String.format("%s/%s/%s/%s", year, month, day, uuid);
    }


    @Transactional
    public void uploadImage(MultipartFile multipartFile){
        String filename = generateRandomFilename();
        try{
            multipartFile.getOriginalFilename();
            storageService.upload(filename, multipartFile.getContentType(), multipartFile.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Image image = Image.builder()
            .filename(filename)
            .originalName(multipartFile.getOriginalFilename())
            .contentType(multipartFile.getContentType())
            .size(multipartFile.getSize())
            .build();
        imageRepository.save(image);
    }

    public ImageDto downloadImage(Long id){
        Image image = imageRepository.findById(id).orElseThrow(RuntimeException::new);
        byte[] bytes = storageService.download(image.getFilename());
        return ImageDto.fromEntity(image, bytes);
    }


    public String getImageUrl(Long id){
        Image image = imageRepository.findById(id).orElseThrow(RuntimeException::new);
        String filename = image.getFilename();
        return storageService.getPresignedUrl(filename);
    }

    @Transactional
    public void deleteImage(Long id) throws Exception {
        Image image = imageRepository.findById(id).orElseThrow(RuntimeException::new);
        String filename = image.getFilename();
        imageRepository.deleteById(id);
        storageService.delete(filename);
    }



}
