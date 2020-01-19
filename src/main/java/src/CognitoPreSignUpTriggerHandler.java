package src;

import com.amazonaws.services.cognitoidentity.model.CognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderAsyncClient;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClient;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.lambda.runtime.events.CognitoEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.amazonaws.services.cognitoidp.model.*;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

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

        AWSCognitoIdentityProviderAsyncClient client = new AWSCognitoIdentityProviderAsyncClient();

        AdminAddUserToGroupRequest request = new AdminAddUserToGroupRequest();
        request.setGroupName("admin");
        request.setUsername("etasbasi");
        request.setUserPoolId("us-east-1_fIw0epfuo");
        client.adminAddUserToGroup(request);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String eventJson = mapper.writeValueAsString(event);
            context.getLogger().log(eventJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

//        try {
//            event.getDatasetRecords().forEach((s, datasetRecord) -> {
//                context.getLogger().log("Key = " + s + ", Value = " + datasetRecord.getOldValue());
//            });
//        } catch (NullPointerException exception) {
//            exception.printStackTrace();
//        }

        event.setEventType("PreSignUp_SignUp\n");
        context.getLogger().log("Event type: " + event.getEventType());
        CognitoEvent modifiedEvent = event;

        return event;
    }
}
