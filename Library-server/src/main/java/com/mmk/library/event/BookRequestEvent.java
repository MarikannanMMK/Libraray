package com.mmk.library.event;

import com.mmk.library.entity.BookRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class BookRequestEvent extends ApplicationEvent {
    private BookRequest bookRequest;

    private String applicationUrl;

    public BookRequestEvent(BookRequest bookRequest , String applicationUrl) {
        super(bookRequest);
        this.bookRequest = bookRequest;
        this.applicationUrl = applicationUrl;
    }
}
