package com.jrodriguezh.transformers

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jrodriguezh.transformers.data.TechSpec
import com.jrodriguezh.transformers.data.Transformer
import com.jrodriguezh.transformers.data.serializer.TransformerTypeAdapterFactory
import org.junit.Before
import org.junit.Test

class SerializationUnitTest {

    lateinit var gson: Gson
    val transformer = Transformer(name = "Test Transformer",
                        spec = TechSpec(2,2,2,
                                1,1,7,1,4))

    @Before
    fun setupGson() {
        gson = GsonBuilder().registerTypeAdapterFactory(TransformerTypeAdapterFactory())
                .create()
    }

    @Test
    fun serializeObjectTest() {
        val serialized = gson.toJsonTree(transformer)
        assert(serialized != null) { "This should be a valid object "}
        assert(serialized.isJsonObject) { "This should be a json object" }
        assert(!serialized.asJsonObject.has("id")) { "This object doesn't have an id" }
        assert(serialized.asJsonObject.has("name")) { "This object should have a name" }
        assert(serialized.asJsonObject.getAsJsonPrimitive("strength").isNumber)
            { "This object's member must be a number" }
        assert(serialized.asJsonObject.getAsJsonPrimitive("courage").asInt == 7)
        { "This object's member must be equals to 7" }
    }

    @Test
    fun serializeAndDeserializeObjectTest(){
        val serialized = gson.toJson(transformer)
        val deserialized = gson.fromJson(serialized, Transformer::class.java)

        assert(deserialized.name == transformer.name) { "The member must be the same" }
        assert(deserialized.spec.overall() > 1) { "The overall must be greater than 1" }
        assert(deserialized.spec.overall() == transformer.spec.overall()) { "The overall must be the same" }
        assert(deserialized.spec.rank == transformer.spec.rank) { "The rank must be the same" }
    }

}
