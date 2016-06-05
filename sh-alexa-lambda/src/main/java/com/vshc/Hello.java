package com.vshc;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Hello {
    
    public String handleRequest(Request request, Context context) throws JsonProcessingException {
        String greetingString = String.format("Hello %s.", request.getHeader().getPayloadVersion());
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(request);
    }
}

