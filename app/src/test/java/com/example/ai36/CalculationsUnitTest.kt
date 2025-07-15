package com.example.ai36

import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class CalculationsUnitTest {

    @Test
    fun add_check(){
        val calculator = Calculator()
        val result = calculator.add(5,5)

        assertEquals(10,result)
    }

    //test using mockito
    @Test
    fun add_mockito(){
        val calculator = mock(Calculator::class.java)
        `when`(calculator.add(5,5)).thenReturn(10)
        assertEquals(calculator.add(5,5),10)
    }

}