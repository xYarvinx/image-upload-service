package com.yarvin.imageuploadservice;

import org.springframework.boot.SpringApplication;

public class TestImageUploadServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(ImageUploadServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
