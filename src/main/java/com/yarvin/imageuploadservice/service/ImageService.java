package com.yarvin.imageuploadservice.service;

import com.yarvin.imageuploadservice.model.ImageEntity;
import com.yarvin.imageuploadservice.repository.ImageRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {

    private ImageRepository imageRepository;


    private AmqpTemplate amqpTemplate;

    private final String QUEUE_NAME = "image-processing-queue";

    public ImageEntity saveImage(byte[] fileBytes, String filename) throws Exception {
        Path path = Paths.get("uploads/" + filename);
        Files.write(path, fileBytes);

        ImageEntity image = new ImageEntity();
        image.setFilename(filename);
        image.setOriginalImagePath(path.toString());

        ImageEntity savedImage = imageRepository.save(image);

        // Send a message to RabbitMQ for further processing
        amqpTemplate.convertAndSend(QUEUE_NAME, savedImage.getId());

        return savedImage;
    }
}
