package com.jrodriguezh.transformers.data.serializer

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.jrodriguezh.transformers.data.Team
import com.jrodriguezh.transformers.data.TechSpec
import com.jrodriguezh.transformers.data.Transformer
import com.jrodriguezh.transformers.data.TransformersList

class TransformerTypeAdapterFactory : TypeAdapterFactory {
    override fun <T : Any?> create(gson: Gson?, type: TypeToken<T>?): TypeAdapter<T>? {
        var rawClass = type!!.rawType
        if (Transformer::class.java.isAssignableFrom(rawClass))
            return TransformerTypeAdapter(rawClass as Class<in Transformer>, gson!!).nullSafe() as TypeAdapter<T>?
        //if (TransformersList::class.java.isAssignableFrom(rawClass))
            //return TransformersListTypeAdapter(rawClass as Class<in TransformersList>, gson!!).nullSafe() as TypeAdapter<T>

        return null
    }

    class TransformerTypeAdapter(private val rawClass: Class<in Transformer>,
                                 private val gson: Gson) : TypeAdapter<Transformer>() {

        override fun read(reader: JsonReader?): Transformer =
            with(reader!!) {
                var id:String? = null
                var name: String = ""
                var team: Char = 'A'
                var icon: String = ""
                var strength: Byte = 0
                var intelligence: Byte = 0
                var speed: Byte = 0
                var endurance: Byte = 0
                var rank: Byte = 0
                var courage: Byte = 0
                var firepower: Byte = 0
                var skill: Byte = 0

                beginObject()
                while (hasNext()) {
                    when (nextName()) {
                        "id" -> id = gson.getAdapter(String::class.java).read(reader)
                        "name" -> name = gson.getAdapter(String::class.java).read(reader)
                        "team" -> team = gson.getAdapter(Char::class.java).read(reader)
                        "team_icon" -> icon = gson.getAdapter(String::class.java).read(reader)
                        "strength" -> strength = gson.getAdapter(Byte::class.java).read(reader)
                        "intelligence" -> intelligence = gson.getAdapter(Byte::class.java).read(reader)
                        "speed" -> speed = gson.getAdapter(Byte::class.java).read(reader)
                        "endurance" -> endurance = gson.getAdapter(Byte::class.java).read(reader)
                        "rank" -> rank = gson.getAdapter(Byte::class.java).read(reader)
                        "courage" -> courage = gson.getAdapter(Byte::class.java).read(reader)
                        "firepower" -> firepower = gson.getAdapter(Byte::class.java).read(reader)
                        "skill" -> skill = gson.getAdapter(Byte::class.java).read(reader)
                        else -> reader.skipValue()
                    }
                }
                endObject()

                Transformer(id, name,
                    Team(team, icon),
                    TechSpec(strength, intelligence, speed,
                        endurance, rank, courage, firepower, skill))
            }


        override fun write(writer: JsonWriter?, value: Transformer?) {
            value?.let { t: Transformer ->
                with(writer!!) {
                    beginObject()
                    t.id?.let { name("id").value(it) }
                    name("name").let { gson.getAdapter(String::class.java).write(this, t.name) }
                    name("strength").let { gson.getAdapter(Byte::class.java).write(this, t.spec.strength) }
                    name("intelligence").let { gson.getAdapter(Byte::class.java).write(this, t.spec.intelligence) }
                    name("speed").let { gson.getAdapter(Byte::class.java).write(this, t.spec.speed) }
                    name("endurance").let { gson.getAdapter(Byte::class.java).write(this, t.spec.endurance) }
                    name("rank").let { gson.getAdapter(Byte::class.java).write(this, t.spec.rank) }
                    name("courage").let { gson.getAdapter(Byte::class.java).write(this, t.spec.courage) }
                    name("firepower").let { gson.getAdapter(Byte::class.java).write(this, t.spec.firepower) }
                    name("skill").let { gson.getAdapter(Byte::class.java).write(this, t.spec.skill) }
                    name("team").let { gson.getAdapter(Char::class.java).write(this, t.team.team) }
                    endObject()
                }
            }
        }

    }

}