package com.vshc.custom.skill;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SmartHomeSpeechlet implements Speechlet {

    private static final Logger log = LoggerFactory.getLogger(SmartHomeSpeechlet.class);

    private CloseableHttpClient httpClient;

    public SmartHomeSpeechlet(){
        httpClient = HttpClients.createDefault();
    }

    @Override
    public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());

    }

    @Override
    public SpeechletResponse onLaunch(LaunchRequest launchRequest, Session session) throws SpeechletException {
        log.info("onLaunch requestId={}, sessionId={}", launchRequest.getRequestId(),
                session.getSessionId());
        String ouput = "Hello, What device you would like to control?";
        String repromptText = "For instructions on what you can say, please say help me.";

        return newAskResponse(ouput, repromptText);
    }

    @Override
    public SpeechletResponse onIntent(IntentRequest intentRequest, Session session) throws SpeechletException {
        log.info("onIntent requestId={}, sessionId={}", intentRequest.getRequestId(),
                session.getSessionId());

        Intent intent = intentRequest.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("GetTargetTemperature".equals(intentName)) {
            PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
            outputSpeech.setText("Current target trmperature is 20 Degrres");
            return SpeechletResponse.newTellResponse(outputSpeech);
        } else if ("WhoIsTheChampion".equals(intentName)) {
            PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
            outputSpeech.setText(getMessageFromServer());
            return SpeechletResponse.newTellResponse(outputSpeech);
        } else if ("AMAZON.CancelIntent".equals(intentName)) {
            PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
            outputSpeech.setText("Goodbye");
            return SpeechletResponse.newTellResponse(outputSpeech);
        } else {
            throw new SpeechletException("Invalid Intent");
        }
    }

    private String getMessageFromServer() {
        HttpGet httpGet = new HttpGet("http://alexa.vzudm.com:8080/alexa/service");

        String response = null;
        CloseableHttpResponse closeableHttpResponse = null;
        try {
            closeableHttpResponse = httpClient.execute(httpGet);
            System.out.println(closeableHttpResponse.getStatusLine());
            HttpEntity entity1 = closeableHttpResponse.getEntity();
            response = IOUtils.toString(entity1.getContent(), "UTF-8");
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity1);
        } catch (IOException io){
        } finally {
            try {
                closeableHttpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(response == null){
            response = "Can't retrieve data from server";
        }

        return response;
    }

    @Override
    public void onSessionEnded(SessionEndedRequest sessionEndedRequest, Session session) throws SpeechletException {

    }

    private SpeechletResponse newAskResponse(String stringOutput, String repromptText) {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText(stringOutput);

        PlainTextOutputSpeech repromptOutputSpeech = new PlainTextOutputSpeech();
        repromptOutputSpeech.setText(repromptText);
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptOutputSpeech);

        return SpeechletResponse.newAskResponse(outputSpeech, reprompt);
    }
}
