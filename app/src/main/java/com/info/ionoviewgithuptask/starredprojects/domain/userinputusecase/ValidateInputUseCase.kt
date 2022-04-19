package com.info.ionoviewgithuptask.starredprojects.domain.userinputusecase

typealias IsValid = ValidateInputUseCase

class ValidateInputUseCase {
    operator fun invoke(input: String): Boolean {
        return isPhotoSearchQueryValid(input)
    }

    private fun isPhotoSearchQueryValid(input: String): Boolean =
        input.isNotBlank() && input.isNotEmpty()

}

