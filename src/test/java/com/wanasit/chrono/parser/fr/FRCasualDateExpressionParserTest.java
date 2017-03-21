package com.wanasit.chrono.parser.fr;

import com.wanasit.chrono.Chrono;
import com.wanasit.chrono.ParserTestAbstract;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FRCasualDateExpressionParserTest extends ParserTestAbstract {

    @Test
    public void test() throws IOException {

        refDate = createDate(2014, 4, 20, 12, 0);
        results = Chrono.casual.parse("hier", refDate);

        assertEquals(results.size(), 1);
        assertEquals("hier", results.get(0).text);
        assertNotNull(results.get(0).start);
        assertDateEquals(createDate(2014, 4, 19, 12, 0), results.get(0).start);

        refDate = createDate(2014, 4, 20, 12, 0);
        results = Chrono.casual.parse("aujourd'hui", refDate);

        assertEquals(results.size(), 1);
        assertEquals("aujourd'hui", results.get(0).text);
        assertNotNull(results.get(0).start);
        assertDateEquals(createDate(2014, 4, 20, 12, 0), results.get(0).start);

        refDate = createDate(2014, 4, 20, 12, 0);
        results = Chrono.casual.parse("demain", refDate);

        assertEquals(results.size(), 1);
        assertEquals("demain", results.get(0).text);
        assertNotNull(results.get(0).start);
        assertDateEquals(createDate(2014, 4, 21, 12, 0), results.get(0).start);

        // =========================

        refDate = createDate(2014, 4, 20, 13, 0);
        results = Chrono.casual.parse("cette nuit", refDate);

        assertEquals(results.size(), 1);
        assertEquals("cette nuit", results.get(0).text);
        assertNotNull(results.get(0).start);
        assertDateEquals(createDate(2014, 4, 21, 0, 0), results.get(0).start);

        refDate = createDate(2014, 4, 21, 1, 0);
        results = Chrono.casual.parse("cette nuit", refDate);
        assertDateEquals(createDate(2014, 4, 21, 0, 0), results.get(0).start);

        // =========================

        refDate = createDate(2014, 4, 20, 13, 0);
        results = Chrono.casual.parse("la veille", refDate);

        assertEquals(results.size(), 1);
        assertEquals("la veille", results.get(0).text);
        assertNotNull(results.get(0).start);
        assertDateEquals(createDate(2014, 4, 20, 0, 0), results.get(0).start);

        refDate = createDate(2014, 4, 21, 1, 0);
        results = Chrono.casual.parse("la veille", refDate);
        assertDateEquals(createDate(2014, 4, 20, 0, 0), results.get(0).start);

        refDate = createDate(2014, 4, 21, 6, 0);
        results = Chrono.casual.parse("la veille", refDate);
        assertDateEquals(createDate(2014, 4, 21, 0, 0), results.get(0).start);

        // =========================

        refDate = createDate(2014, 4, 20, 12, 0);
        results = Chrono.casual.parse("cet après-midi", refDate);

        assertEquals(results.size(), 1);
        assertEquals("cet après-midi", results.get(0).text);
        assertNotNull(results.get(0).start);
        assertDateEquals(createDate(2014, 4, 20, 15, 0), results.get(0).start);

        refDate = createDate(2014, 4, 20, 20, 0);
        results = Chrono.casual.parse("cet après-midi", refDate);
        assertDateEquals(createDate(2014, 4, 20, 15, 0), results.get(0).start);

        // =========================

        refDate = createDate(2014, 4, 20, 12, 0);
        results = Chrono.casual.parse("ce soir", refDate);

        assertEquals(results.size(), 1);
        assertEquals("ce soir", results.get(0).text);
        assertNotNull(results.get(0).start);
        assertDateEquals(createDate(2014, 4, 20, 18, 0), results.get(0).start);

        refDate = createDate(2014, 4, 20, 20, 0);
        results = Chrono.casual.parse("ce soir", refDate);
        assertDateEquals(createDate(2014, 4, 20, 18, 0), results.get(0).start);

        // =========================

        refDate = createDate(2014, 4, 20, 12, 0);
        results = Chrono.casual.parse("ce matin", refDate);

        assertEquals(results.size(), 1);
        assertEquals("ce matin", results.get(0).text);
        assertNotNull(results.get(0).start);
        assertDateEquals(createDate(2014, 4, 20, 6, 0), results.get(0).start);

        refDate = createDate(2014, 4, 20, 20, 0);
        results = Chrono.casual.parse("ce matin", refDate);
        assertDateEquals(createDate(2014, 4, 20, 6, 0), results.get(0).start);

        refDate = createDate(2014, 4, 20, 1, 0);
        results = Chrono.casual.parse("ce matin", refDate);
        assertDateEquals(createDate(2014, 4, 19, 6, 0), results.get(0).start);
    }

    @Test
    public void testWithinSentence() throws IOException {

        refDate = createDate(2014, 4, 20, 12, 0);
        results = Chrono.casual.parse("Je vais travailler demain.", refDate);

        assertEquals(results.size(), 1);
        assertEquals("demain", results.get(0).text);
        assertEquals(19, results.get(0).index);
        assertDateEquals(createDate(2014, 4, 21, 12, 0), results.get(0).start);
    }

    @Test
    public void testCaseSensitiveTest() throws IOException {

        refDate = createDate(2014, 4, 21, 1, 0);
        results = Chrono.casual.parse("La Veille", refDate);
        assertDateEquals(createDate(2014, 4, 20, 0, 0), results.get(0).start);
        assertEquals(results.get(0).text, "La Veille");

        refDate = createDate(2014, 4, 21, 1, 0);
        results = Chrono.casual.parse("Cette Nuit", refDate);
        assertDateEquals(createDate(2014, 4, 21, 0, 0), results.get(0).start);
        assertEquals(results.get(0).text, "Cette Nuit");

        refDate = createDate(2014, 4, 20, 1, 0);
        results = Chrono.casual.parse("Ce Matin", refDate);
        assertDateEquals(createDate(2014, 4, 19, 6, 0), results.get(0).start);
        assertEquals(results.get(0).text, "Ce Matin");
    }


}
