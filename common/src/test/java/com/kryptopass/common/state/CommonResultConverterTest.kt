package com.kryptopass.common.state

import com.kryptopass.domain.entity.Result
import com.kryptopass.domain.entity.UseCaseException
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CommonResultConverterTest {

    private val converter = object : CommonResultConverter<String, String>() {
        override fun convertSuccess(data: String): String {
            return "result${data}"
        }
    }

    @Test
    fun testConvertError() {
        val errorMessage = "errorMessage"
        val exception = mock<UseCaseException.MovieException>()

        whenever(exception.localizedMessage).thenReturn(errorMessage)
        val errorResult = Result.Error(exception)
        val result = converter.convert(errorResult)

        assertEquals(UiState.Error(errorMessage), result)
    }

    @Test
    fun testConvertSuccess() {
        val data = "data"
        val successResult = Result.Success(data)
        val result = converter.convert(successResult)

        assertEquals(UiState.Success("result${data}"), result)
    }
}