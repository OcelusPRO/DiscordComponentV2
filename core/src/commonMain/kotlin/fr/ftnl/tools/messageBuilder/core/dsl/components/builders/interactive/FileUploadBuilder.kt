@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive

import fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.ModalComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.FileUpload
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@ComponentDsl
class FileUploadBuilder(val customId: String): ModalComponentBuilder {
    
    var minValues: Int= 1
    var maxValues: Int= 10
    var required: Boolean = true
    
    
    override fun build(): FileUpload {
        return FileUpload(
            customId=customId,
            minValues = 1,
            maxValues = 10,
            required= true
        )
        
    }
    
}