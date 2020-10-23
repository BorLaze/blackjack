package ua.in.lbn.bj;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = UserController.class)
class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getWithoutName() throws Exception {
        final String indexHtml = mockMvc.perform(
                get("/user")
        )
                .andReturn()
                .getResponse()
                .getContentAsString();

        final String str = Jsoup.parse(indexHtml)
                .selectFirst("body")
                .selectFirst("p")
                .text();

        assertEquals("Hello, World!", str);
    }

    @Test
    void getWithName() throws Exception {
        final String indexHtml = mockMvc.perform(
                get("/user").param("name", "USER")
        )
                .andReturn()
                .getResponse()
                .getContentAsString();

        final String str = Jsoup.parse(indexHtml)
                .selectFirst("body")
                .selectFirst("p")
                .text();

        assertEquals("Hello, USER!", str);
    }
}