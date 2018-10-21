package message.processor.joke.components

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Joke : Serializable {

    @SerializedName("pergunta")
    public var question: String? = null

    @SerializedName("resposta")
    public var answer: String? = null


}
