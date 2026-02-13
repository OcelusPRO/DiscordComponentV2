package fr.ftnl.tools.messageBuilder.jda.ext.components.interactive.fileUpload

import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.FileUpload
import net.dv8tion.jda.api.components.attachmentupload.AttachmentUpload


fun FileUpload.toJda(): AttachmentUpload {
    val builder = AttachmentUpload.create(customId)
    builder.setRequired(required)
    builder.setCustomId(customId)
    id?.let { builder.setUniqueId(id!!) }
    builder.setMaxValues(maxValues)
    builder.setMinValues(minValues)
    return builder.build()
}