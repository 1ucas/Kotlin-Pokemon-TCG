package pokemontcg.libraries.network.exceptions

import java.lang.Exception

open class ServerErrorException(message: String?, cause: Throwable? = null) : Exception(message ?: cause?.message, cause)