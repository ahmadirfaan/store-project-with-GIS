package com.irfaan.api.inventory.models;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ResponseMessageTest {

    @Test
    void getterSetterTest() {
        String messageAndData = "a";
        int code = 1;
        LocalDateTime time = LocalDateTime.now();
        ResponseMessage<String> responseMessage = new ResponseMessage<>();
        responseMessage.setMessage(messageAndData);
        responseMessage.setCode(code);
        responseMessage.setData(messageAndData);
        responseMessage.setTimestamp(time);

        assertNotNull(responseMessage.getMessage());
        assertEquals(code, responseMessage.getCode());
        assertNotNull(responseMessage.getData());
        assertNotNull(responseMessage.getTimestamp());
        assertNotNull(ResponseMessage.error(HttpStatus.ACCEPTED));
        assertNotNull(ResponseMessage.success(messageAndData));
        assertNotNull(ResponseMessage.error(code,messageAndData));
        assertNotNull(ResponseMessage.error(code,messageAndData,messageAndData));
    }
}
