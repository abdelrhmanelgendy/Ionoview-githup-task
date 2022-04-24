package com.info.ionoviewgithuptask.starredprojects.domain.userinputusecase

typealias IsValid = ValidateSearchQueryInputUseCase

class ValidateSearchQueryInputUseCase {
    operator fun invoke(input: String): Boolean {
        return isSearchQueryValid(input)
    }

    private fun isSearchQueryValid(input: String): Boolean =
        input.isNotBlank() && input.isNotEmpty()

}

