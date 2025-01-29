package com.mert.spaceflightnews.utils

import com.mert.spaceflightnews.extension.formatToDisplayDateTime
import junit.framework.TestCase.assertEquals
import org.junit.Test

class DateExtensionTest {
    /*@Test
    fun `formatToDisplayDateTime correctly formats valid date string`() {
        val dateString = "2025-01-29T19:30:18Z"

        val result = dateString.formatToDisplayDateTime()

        assertEquals("29 Jan 2025, 19:30", result)
    }*/

    @Test
    fun `formatToDisplayDateTime returns original string when format is invalid`() {
        val invalidDateString = "Invalid Date Format"

        val result = invalidDateString.formatToDisplayDateTime()

        assertEquals(invalidDateString, result)
    }

    @Test
    fun `formatToDisplayDateTime handles null input`() {
        val nullDateString: String? = null

        assertEquals(nullDateString.formatToDisplayDateTime(), "Date Info Not Available")
    }
}