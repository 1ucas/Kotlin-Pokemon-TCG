package pokemontcg.libraries.common

interface UseCase<Param, Return> {
    fun execute(param:Param) : Return
}