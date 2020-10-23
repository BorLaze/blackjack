package ua.in.lbn.bj;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest
class BjWebAppIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void indexHtml() throws Exception {
        final String indexHtml = mockMvc.perform(
                get("/index.html")
        )
                .andReturn()
                .getResponse()
                .getContentAsString();

        final String title = Jsoup.parse(indexHtml)
                .selectFirst("head")
                .selectFirst("title")
                .text();

        assertEquals("Blackjack", title);
    }
}