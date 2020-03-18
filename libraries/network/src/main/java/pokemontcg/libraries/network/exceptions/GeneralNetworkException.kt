package pokemontcg.libraries.network.exceptions

import java.lang.Exception

open class GeneralNetworkException(message: String? = null, cause: Throwable? = null) : Exception(message ?: cause?.message, cause)