package ua.`in`.factsofnumbers.data.repository

import android.content.res.Resources
import retrofit2.HttpException
import retrofit2.Response
import ua.`in`.factsofnumbers.R
import ua.`in`.factsofnumbers.domain.model.NumbersFact
import ua.`in`.factsofnumbers.domain.FactMapper
import ua.`in`.factsofnumbers.domain.Resource
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException

abstract class BaseSafeRepo {
    suspend fun getFactByNumberSafe(
        resources: Resources,
        number: Long,
        apiToCall: suspend (Long) -> Response<String>): Resource<NumbersFact>{

        return try {
            val response = apiToCall(number)
            val body = response.body()

            if (response.isSuccessful && body?.isNotEmpty() == true){
                val numbersFact: NumbersFact = FactMapper.getNumbersFactFromString(body)
                Resource.Success(numbersFact)
            }else{
                Resource.Error(resources.getString(R.string.server_error))
            }
        } catch (ex: HttpException) {
            Resource.Error(resources.getString(R.string.error_connection))
        } catch (ex: SocketTimeoutException) {
            Resource.Error(resources.getString(R.string.connect_timed_out))
        } catch (ex: SocketException) {
            Resource.Error(resources.getString(R.string.software_caused_connection_abort))
        } catch (ex: IOException) {
            Resource.Error(resources.getString(R.string.check_connection))
        } catch (ex: Exception) {
            Resource.Error(resources.getString(R.string.something_happened_while_connecting))
        }
    }

    suspend fun getFactByRandomNumberSafe(
        resources: Resources,
        apiToCall: suspend () -> Response<String>): Resource<NumbersFact>{

        return try {
            val response = apiToCall()
            val body = response.body()

            if (response.isSuccessful && body?.isNotEmpty() == true){
                val numbersFact: NumbersFact = FactMapper.getNumbersFactFromString(body)
                Resource.Success(numbersFact)
            }else{
                Resource.Error(resources.getString(R.string.server_error))
            }
        } catch (ex: HttpException) {
            Resource.Error(resources.getString(R.string.error_connection))
        } catch (ex: SocketTimeoutException) {
            Resource.Error(resources.getString(R.string.connect_timed_out))
        } catch (ex: SocketException) {
            Resource.Error(resources.getString(R.string.software_caused_connection_abort))
        } catch (ex: IOException) {
            Resource.Error(resources.getString(R.string.check_connection))
        } catch (ex: Exception) {
            Resource.Error(resources.getString(R.string.something_happened_while_connecting))
        }
    }
}