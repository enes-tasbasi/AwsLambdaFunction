package src;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.lambda.runtime.events.CognitoEvent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//public class CognitoPreSignUpTriggerHandler implements RequestStreamHandler {
//
//    // Jackson JSON mapper
//    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//
//    @Override
//    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
//
//        JsonNode eventNode = OBJECT_MAPPER.readTree(input);
//
//        String reqPoolId = eventNode.path("userPoolId").asText();
//        String username = eventNode.path("userName").asText();
//
//        context.getLogger().log("Username is : " + username);
//
//        OBJECT_MAPPER.writeValue(output, eventNode);
//    }
//}

public class CognitoPreSignUpTriggerHandler implements RequestHandler<CognitoEvent, CognitoEvent> {
    @Override
    public CognitoEvent handleRequest(CognitoEvent event, Context context) {
        context.getLogger().log(event.toString());
        context.getLogger().log("Event type: " + event.getEventType());

        CognitoEvent modifiedEvent = event;

        return modifiedEvent;
    }
}
