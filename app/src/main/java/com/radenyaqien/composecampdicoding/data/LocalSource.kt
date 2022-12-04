package com.radenyaqien.composecampdicoding.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.radenyaqien.composecampdicoding.data.model.RickMorty
import java.lang.reflect.Type

object LocalSource {

    private val json = """
    [{
        "id": 1,
        "name": "Rick Sanchez",
        "status": "Alive",
        "species": "Human",
        "type": "",
        "gender": "Male",
        
        "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        "url": "https://rickandmortyapi.com/api/character/1",
        "created": "2017-11-04T18:48:46.250Z"
    },
    {
        "id": 2,
        "name": "Morty Smith",
        "status": "Alive",
        "species": "Human",
        "type": "",
        "gender": "Male",
        
        "image": "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
       
        "url": "https://rickandmortyapi.com/api/character/2",
        "created": "2017-11-04T18:50:21.651Z"
    },
    {
        "id": 3,
        "name": "Summer Smith",
        "status": "Alive",
        "species": "Human",
        "type": "",
        "gender": "Female",
       
        "image": "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
       
        "url": "https://rickandmortyapi.com/api/character/3",
        "created": "2017-11-04T19:09:56.428Z"
    },
    {
        "id": 4,
        "name": "Beth Smith",
        "status": "Alive",
        "species": "Human",
        "type": "",
        "gender": "Female",

        "image": "https://rickandmortyapi.com/api/character/avatar/4.jpeg",
       
        "url": "https://rickandmortyapi.com/api/character/4",
        "created": "2017-11-04T19:22:43.665Z"
    },
    {
        "id": 5,
        "name": "Jerry Smith",
        "status": "Alive",
        "species": "Human",
        "type": "",
        "gender": "Male",
        "image": "https://rickandmortyapi.com/api/character/avatar/5.jpeg",
       
        "url": "https://rickandmortyapi.com/api/character/5",
        "created": "2017-11-04T19:26:56.301Z"
    },
    {
        "id": 6,
        "name": "Abadango Cluster Princess",
        "status": "Alive",
        "species": "Alien",
        "type": "",
        "image": "https://rickandmortyapi.com/api/character/avatar/6.jpeg",
        "gender": "Female",
        "url": "https://rickandmortyapi.com/api/character/6",
        "created": "2017-11-04T19:50:28.250Z"
    },
    {
        "id": 7,
        "name": "Abradolf Lincler",
        "status": "unknown",
        "species": "Human",
        "type": "Genetic experiment",
        "gender": "Male",
        
        "image": "https://rickandmortyapi.com/api/character/avatar/7.jpeg",

        "url": "https://rickandmortyapi.com/api/character/7",
        "created": "2017-11-04T19:59:20.523Z"
    },
    {
        "id": 8,
        "name": "Adjudicator Rick",
        "status": "Dead",
        "species": "Human",
        "type": "",
        "gender": "Male",
       
        "image": "https://rickandmortyapi.com/api/character/avatar/8.jpeg",
  
        "url": "https://rickandmortyapi.com/api/character/8",
        "created": "2017-11-04T20:03:34.737Z"
    },
    {
        "id": 9,
        "name": "Agency Director",
        "status": "Dead",
        "species": "Human",
        "type": "",
        "gender": "Male",
       
        "image": "https://rickandmortyapi.com/api/character/avatar/9.jpeg",

        "url": "https://rickandmortyapi.com/api/character/9",
        "created": "2017-11-04T20:06:54.976Z"
    },
    {
        "id": 10,
        "name": "Alan Rails",
        "status": "Dead",
        "species": "Human",
        "type": "Superhuman (Ghost trains summoner)",
        "gender": "Male",
     
        "image": "https://rickandmortyapi.com/api/character/avatar/10.jpeg",

        "url": "https://rickandmortyapi.com/api/character/10",
        "created": "2017-11-04T20:19:09.017Z"
    },
    {
        "id": 11,
        "name": "Albert Einstein",
        "status": "Dead",
        "species": "Human",
        "type": "",
        "gender": "Male",
        "image": "https://rickandmortyapi.com/api/character/avatar/11.jpeg",
        "url": "https://rickandmortyapi.com/api/character/11",
        "created": "2017-11-04T20:20:20.965Z"
    }]
""".trimIndent()

    private var listType: Type = object : TypeToken<ArrayList<RickMorty>>() {}.type
    private val dataSource: ArrayList<RickMorty> = Gson().fromJson(json, listType)

    fun getListRickMorty(): ArrayList<RickMorty> {
        return dataSource
    }

    fun getOneCharacter(id: Int): RickMorty? = dataSource.find {
        it.id == id
    }

    fun searchCharacter(query: String) = dataSource.filter {
        it.name.contains(query, ignoreCase = true)
    }
}
