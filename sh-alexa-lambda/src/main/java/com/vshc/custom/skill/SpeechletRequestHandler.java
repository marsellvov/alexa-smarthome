package com.vshc.custom.skill;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

public class SpeechletRequestHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds;

    static {
        supportedApplicationIds = new HashSet<String>();
            supportedApplicationIds.add("amzn1.echo-sdk-ams.app.db8905f4-2306-4c79-83bd-410b7b8042f6");
    }

    public SpeechletRequestHandler() {
        super(new SmartHomeSpeechlet(), supportedApplicationIds);
    }
}
