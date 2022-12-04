package com.radenyaqien.composecampdicoding.data

import com.radenyaqien.composecampdicoding.data.model.RickMorty

class RickMortyRepository {

    fun getDataRickMorty(): List<RickMorty> = LocalSource.getListRickMorty()

    fun getRickMortyById(id: Int): RickMorty? = LocalSource.getOneCharacter(id)

    fun searchRickMorty(query: String): List<RickMorty> = LocalSource.searchCharacter(query)

}