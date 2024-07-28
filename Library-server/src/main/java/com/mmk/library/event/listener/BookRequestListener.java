package com.mmk.library.event.listener;


import com.mmk.library.event.BookRequestEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookRequestListener implements ApplicationListener<BookRequestEvent> {


    @Override
    public void onApplicationEvent(BookRequestEvent event) {

        String url = event.getApplicationUrl() + "/allRequest" ;

        //sent emailtoAdmin.

        log.info("Click here to verify your account {} " + url);
    }
}
