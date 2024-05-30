package com.kryptopass.domain.usecase

import com.kryptopass.domain.entity.Result
import com.kryptopass.domain.entity.UseCaseException
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class)
class UseCaseTest {

    private val configuration = UseCase.Configuration(UnconfinedTestDispatcher())
    private val request = Mockito.mock<UseCase.Request>()
    private val response = Mockito.mock<UseCase.Response>()

    private lateinit var useCase: UseCase<UseCase.Request, UseCase.Response>

    @Before
    fun setUp() {
        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                TestCase.assertEquals(this@UseCaseTest.request, request)
                return flowOf(response)
            }
        }
    }

    @Test
    fun testExecuteSuccess() = runTest {
        val result = useCase.execute(request).first()
        TestCase.assertEquals(Result.Success(response), result)
    }

    @Test
    fun testExecuteMovieException() {
        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                TestCase.assertEquals(this@UseCaseTest.request, request)
                return flow {
                    throw UseCaseException.MovieException(Throwable())
                }
            }
        }

        runTest {
            val result = useCase.execute(request).first()
            TestCase.assertTrue((result as Result.Error).exception is UseCaseException.MovieException)
        }
    }

    @Test
    fun testExecuteUnknownException() {
        useCase = object : UseCase<UseCase.Request, UseCase.Response>(configuration) {
            override fun process(request: Request): Flow<Response> {
                TestCase.assertEquals(this@UseCaseTest.request, request)
                return flow {
                    throw Throwable()
                }
            }
        }

        runTest {
            val result = useCase.execute(request).first()
            TestCase.assertTrue((result as Result.Error).exception is UseCaseException.UnknownException)
        }
    }
}