package com.wanasit.chrono.parser.fr;

import com.wanasit.chrono.Chrono;
import com.wanasit.chrono.ParserTestAbstract;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FRDayOfWeekDateFormatParserTest extends ParserTestAbstract {

    @Test
    public void test() {

        refDate = createDate(2014, 8, 18, 12, 0); // Monday
        results = Chrono.casual.parse("Lundi", refDate);

        assertEquals(results.size(), 1);
        assertEquals("Lundi", results.get(0).text);
        assertNotNull(results.get(0).start);
        assertDateEquals(createDate(2014, 8, 18, 12, 0), results.get(0).start);

        // =========================

        refDate = createDate(2014, 8, 18, 12, 0); // Monday
        results = Chrono.casual.parse("mercredi", refDate);

        assertEquals(results.size(), 1);
        assertEquals("mercredi", results.get(0).text);
        assertNotNull(results.get(0).start);
        assertDateEquals(createDate(2014, 8, 20, 12, 0), results.get(0).start);

        refDate = createDate(2014, 8, 18, 12, 0);
        results = Chrono.casual.parse("Ce mercredi", refDate);

        assertEquals(results.size(), 1);
        assertEquals("Ce mercredi", results.get(0).text);
        assertNotNull(results.get(0).start);
        assertDateEquals(createDate(2014, 8, 20, 12, 0), results.get(0).start);

        refDate = createDate(2014, 8, 18, 12, 0);
        results = Chrono.casual.parse("Mercredi dernier", refDate);

        assertEquals(results.size(), 1);
        assertEquals("Mercredi dernier", results.get(0).text);
        assertNotNull(results.get(0).start);
        assertDateEquals(createDate(2014, 8, 13, 12, 0), results.get(0).start);

        // =========================

        refDate = createDate(2014, 8, 18, 12, 0); // Monday
        results = Chrono.casual.parse("Vendredi", refDate);

        assertEquals(results.size(), 1);
        assertEquals("Vendredi", results.get(0).text);
        assertNotNull(results.get(0).start);
        assertDateEquals(createDate(2014, 8, 15, 12, 0), results.get(0).start);

        // =========================

        refDate = createDate(2014, 8, 18, 12, 0); // Monday
        results = Chrono.casual.parse("Dimanche", refDate);

        assertEquals(results.size(), 1);
        assertEquals("Dimanche", results.get(0).text);
        assertNotNull(results.get(0).start);
        assertDateEquals(createDate(2014, 8, 24, 12, 0), results.get(0).start);

        refDate = createDate(2014, 8, 18, 12, 0);
        results = Chrono.casual.parse("Dimanche dernier", refDate);

        assertEquals(results.size(), 1);
        assertEquals("Dimanche dernier", results.get(0).text);
        assertNotNull(results.get(0).start);
        assertDateEquals(createDate(2014, 8, 17, 12, 0), results.get(0).start);


        refDate = createDate(2014, 8, 18, 12, 0);
        results = Chrono.casual.parse("Dimanche prochain", refDate);

        assertEquals(results.size(), 1);
        assertEquals("Dimanche prochain", results.get(0).text);
        assertNotNull(results.get(0).start);
        assertDateEquals(createDate(2014, 8, 31, 12, 0), results.get(0).start);

    }

    @Test
    public void testWithAllEveryDayOfWeek() {

        refDate = createDate(2014, 8, 18, 12, 0); // Monday
        results = Chrono.casual.parse("Dimanche", refDate);

        assertEquals("Dimanche", results.get(0).text);
        assertDateEquals(createDate(2014, 8, 24, 12, 0), results.get(0).start);

        refDate = createDate(2014, 8, 18, 12, 0);
        results = Chrono.casual.parse("Dim", refDate);

        assertEquals("Dim", results.get(0).text);
        assertDateEquals(createDate(2014, 8, 24, 12, 0), results.get(0).start);

        //=========

        refDate = createDate(2014, 8, 18, 12, 0);
        results = Chrono.casual.parse("Lundi", refDate);

        assertEquals("Lundi", results.get(0).text);
        assertDateEquals(createDate(2014, 8, 18, 12, 0), results.get(0).start);

        refDate = createDate(2014, 8, 18, 12, 0);
        results = Chrono.casual.parse("Lun", refDate);

        assertEquals("Lun", results.get(0).text);
        assertDateEquals(createDate(2014, 8, 18, 12, 0), results.get(0).start);

        //=========

        refDate = createDate(2014, 8, 18, 12, 0);
        results = Chrono.casual.parse("Mardi", refDate);

        assertEquals("Mardi", results.get(0).text);
        assertDateEquals(createDate(2014, 8, 19, 12, 0), results.get(0).start);

        refDate = createDate(2014, 8, 18, 12, 0);
        results = Chrono.casual.parse("Mar", refDate);

        assertEquals("Mar", results.get(0).text);
        assertDateEquals(createDate(2014, 8, 19, 12, 0), results.get(0).start);

        //=========

        refDate = createDate(2014, 8, 18, 12, 0);
        results = Chrono.casual.parse("Mercredi", refDate);

        assertEquals("Mercredi", results.get(0).text);
        assertDateEquals(createDate(2014, 8, 20, 12, 0), results.get(0).start);

        refDate = createDate(2014, 8, 18, 12, 0);
        results = Chrono.casual.parse("Mer", refDate);

        assertEquals("Mer", results.get(0).text);
        assertDateEquals(createDate(2014, 8, 20, 12, 0), results.get(0).start);

        //=========

        refDate = createDate(2014, 8, 18, 12, 0);
        results = Chrono.casual.parse("jeudi", refDate);

        assertEquals("jeudi", results.get(0).text);
        assertDateEquals(createDate(2014, 8, 21, 12, 0), results.get(0).start);

        refDate = createDate(2014, 8, 18, 12, 0);
        results = Chrono.casual.parse("jeu", refDate);

        assertEquals("jeu", results.get(0).text);
        assertDateEquals(createDate(2014, 8, 21, 12, 0), results.get(0).start);


        //=========

        refDate = createDate(2014, 8, 18, 12, 0);
        results = Chrono.casual.parse("vendredi", refDate);

        assertEquals("vendredi", results.get(0).text);
        assertDateEquals(createDate(2014, 8, 15, 12, 0), results.get(0).start);

        refDate = createDate(2014, 8, 18, 12, 0);
        results = Chrono.casual.parse("ven", refDate);

        assertEquals("ven", results.get(0).text);
        assertDateEquals(createDate(2014, 8, 15, 12, 0), results.get(0).start);

        //=========

        refDate = createDate(2014, 8, 18, 12, 0);
        results = Chrono.casual.parse("samedi", refDate);

        assertEquals("samedi", results.get(0).text);
        assertDateEquals(createDate(2014, 8, 16, 12, 0), results.get(0).start);

        refDate = createDate(2014, 8, 18, 12, 0);
        results = Chrono.casual.parse("sam", refDate);

        assertEquals("sam", results.get(0).text);
        assertDateEquals(createDate(2014, 8, 16, 12, 0), results.get(0).start);
    }

}
