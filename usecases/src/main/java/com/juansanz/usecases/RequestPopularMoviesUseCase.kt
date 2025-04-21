package com.juansanz.usecases

import com.juansanz.data.MoviesRepository
import com.juansanz.domain.Movie

/**
 * Caso de uso para obter os filmes populares e selecionar os 20 melhores
 * com base nos critérios de popularidade, número de votos e média de votos.
 *
 * @param moviesRepository Repositório de filmes responsável por recuperar os dados.
 */
const val TOP20 = 20

class RequestPopularMoviesUseCase(
    private val moviesRepository: MoviesRepository,
) {
    /**
     * Recupera todos os filmes populares e retorna uma lista contendo os
     * 20 melhores filmes, ordenados pela popularidade, número de votos e média de votos.
     *
     * @return Lista dos 20 filmes mais relevantes.
     */
    suspend operator fun invoke(): List<Movie> = moviesRepository.getAllPopularMovies().getTop20Items()

    /**
     * Extensão para a lista de filmes que ordena os elementos pelos critérios
     * de popularidade, número de votos e média de votos, retornando os 20 melhores filmes.
     *
     * @receiver List<Movie> Lista de filmes a ser ordenada.
     * @return Lista contendo os 20 filmes mais relevantes.
     */
    fun List<Movie>.getTop20Items(): List<Movie> =
        this
            .sortedWith(
                compareByDescending<Movie> { it.popularity }
                    .thenByDescending { it.voteCount }
                    .thenByDescending { it.voteAverage },
            ).take(TOP20)
}
