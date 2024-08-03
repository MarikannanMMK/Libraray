package com.mmk.library.event.listener;


import com.mmk.library.event.BookRequestEvent;
import com.mmk.library.model.EmailDetails;
import com.mmk.library.utility.Utill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookRequestListener implements ApplicationListener<BookRequestEvent> {

    @Autowired
    private Utill utill;

    @Override
    public void onApplicationEvent(BookRequestEvent event) {

        String url = event.getApplicationUrl() + "/allRequest" ;

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setSubject("New Book Request");
        emailDetails.setMsgBody("Hello Admin new Book request came from the User/faculty");
        emailDetails.setRecipient("mariinbumurugan27@gmail.com");
        utill.sendMail(emailDetails);

        log.info("Click here to verify the newly added request {} " + url);
    }
}
