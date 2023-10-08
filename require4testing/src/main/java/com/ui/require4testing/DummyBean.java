package com.ui.require4testing;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Lars Grefer
 */
@Component("dummy")
public class DummyBean {

    public String getText() {
        return "Hello from Spring: " + LocalDateTime.now();
    }
}
