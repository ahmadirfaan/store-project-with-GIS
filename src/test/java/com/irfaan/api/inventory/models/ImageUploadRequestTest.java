package com.irfaan.api.inventory.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ImageUploadRequestTest {

    @Mock
    private MultipartFile file;

    @Test
    void getterSetterTest() {

        ImageUploadRequest image = new ImageUploadRequest();
        image.setFile(file);

        assertEquals(file, image.getFile());
    }
}
