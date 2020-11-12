package com.jrodriguezh.transformers.data.serializer

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.jrodriguezh.transformers.data.Transformer
import java.lang.reflect.Type

class TransformerSerializer : JsonSerializer<Transformer?> {

    override fun serialize(
        obj: Transformer?,
        foo: Type?,
        context: JsonSerializationContext?
    ): JsonElement =
        obj?.let {
            JsonObject().apply {
               it.id?.let { prop -> addProperty("id", prop) }
               addProperty("name", it.name)
               addProperty("team", it.team.team)
               addProperty("strength", it.spec.strength)
               addProperty("intelligence", it.spec.intelligence)
               addProperty("speed", it.spec.speed)
               addProperty("endurance", it.spec.endurance)
               addProperty("rank", it.spec.rank)
               addProperty("courage", it.spec.courage)
               addProperty("firepower", it.spec.firepower)
           }
       }!!
}