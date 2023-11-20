package jrails;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.isEmptyString;
import static org.junit.Assert.*;

public class HtmlTest {

    private Html html;

    @Before
    public void setUp() throws Exception {
        html = new Html();
    }

    @Test
    public void empty() {
        assertThat(View.empty().toString(), isEmptyString());
    }

    @Test
    public void simpleViewTest() {
        Html simple = View.h1(View.t("Hello")).p(View.t("World"));
        assert (simple.toString().equals("<h1>Hello</h1><p>World</p>"));
    }
}
