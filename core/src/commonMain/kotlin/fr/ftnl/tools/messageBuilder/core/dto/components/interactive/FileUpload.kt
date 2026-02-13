@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dto.components.interactive

import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.LabelChildComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.ModalCompatibleComponent
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
class FileUpload(
    @SerialName("custom_id") var customId: String
) : DiscordComponent, ModalCompatibleComponent, LabelChildComponent {
    
    @JsName("createFull") constructor(
        id: Int? = null,
        customId: String,
        minValues: Int = 1,
        maxValues: Int = 10,
        required: Boolean = true
    ) : this(customId) {
        this.id = id
        this.minValues = minValues
        this.maxValues = maxValues
        this.required = required
    }
    
    override var id: Int? = null
    
    @EncodeDefault @SerialName("min_values") var minValues: Int = 1
    @EncodeDefault @SerialName("max_values") var maxValues: Int = 10
    
    @EncodeDefault var required: Boolean = true
    
    @EncodeDefault override val type: Int = 19
    
    fun setId(id: Int?): FileUpload {
        this.id = id
        return this
    }
    
    fun setMinValues(minValues: Int): FileUpload {
        this.minValues = minValues
        return this
    }
    
    fun setMaxValues(maxValues: Int): FileUpload {
        this.maxValues = maxValues
        return this
    }
    
    fun setRequired(required: Boolean): FileUpload {
        this.required = required
        return this
    }
}