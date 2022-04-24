package com.info.ionoviewgithuptask.starredprojects.domain.userinputusecase

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidateSearchQueryInputUseCaseTest
{


    @Test
    fun `invoke search query with empty string return false`()
    {
        val input:String =""
        val invokeResult = ValidateSearchQueryInputUseCase().invoke(input)
        assertEquals(invokeResult,false)
    }
    @Test
    fun `invoke search query with string return true`()
    {
        val input:String ="gitHup"
        val invokeResult = ValidateSearchQueryInputUseCase().invoke(input)
        assertEquals(invokeResult,true)
    }
}