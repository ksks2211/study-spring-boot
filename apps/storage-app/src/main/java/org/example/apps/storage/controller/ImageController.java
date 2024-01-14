package org.example.apps.storage.controller;

import lombok.RequiredArgsConstructor;
import org.example.apps.storage.dto.ImageDto;
import org.example.apps.storage.service.ImageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author rival
 * @since 2023-12-30
 */

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private boolean isImage(MultipartFile uploadFile){
        return uploadFile!=null && uploadFile.getContentType() !=null && uploadFile.getContentType().startsWith("image");
    }


    @PostMapping(value="",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImage(MultipartFile uploadFile){
        if(!isImage(uploadFile)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        imageService.uploadImage(uploadFile);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable(name="id")Long id) throws Exception {
        imageService.deleteImage(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable(name="id") Long id){
        ImageDto imageDto = imageService.downloadImage(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, imageDto.getContentType());
        return new ResponseEntity<>(imageDto.getBytes(), headers, HttpStatus.OK);
    }



    @GetMapping("/addr/{id}")
    public ResponseEntity<?> getUrl(@PathVariable(name="id") Long id){
        var imageUrl = imageService.getImageUrl(id);
        var map = Map.of("url",imageUrl);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }



}
