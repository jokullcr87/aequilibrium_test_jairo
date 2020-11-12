package com.jrodriguezh.transformers.data.serializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.jrodriguezh.transformers.data.Team
import com.jrodriguezh.transformers.data.TechSpec
import com.jrodriguezh.transformers.data.Transformer
import java.lang.reflect.Type

class TransformerDeserializer : JsonDeserializer<Transformer?> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Transformer? =
        json!!.asJsonObject.run {
            Transformer(
                id = get("id")!!.asString,
                name = get("name")!!.asString,
                team = Team(get("team")!!.asCharacter, get("team_icon")!!.asString),
                spec = TechSpec(
                    strength = get("strength")!!.asByte,
                    intelligence = get("intelligence")!!.asByte,
                    speed = get("speed")!!.asByte,
                    endurance = get("endurance")!!.asByte,
                    rank = get("rank")!!.asByte,
                    courage = get("courage")!!.asByte,
                    firepower = get("firepower")!!.asByte,
                    skill = get("skill")!!.asByte
                )
            )
        }
}
