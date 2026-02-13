package fr.ftnl.tools.messageBuilder.jda.ext.components.content.fileComponent

import fr.ftnl.tools.messageBuilder.core.dto.components.content.FileComponent
import net.dv8tion.jda.api.components.filedisplay.FileDisplay

@Deprecated("Experimental")
fun FileComponent.toJda(): FileDisplay {
    val display = FileDisplay
        .fromFileName(this.name ?: "unnamed")
        .withSpoiler(this.spoiler)
    id?.let { display.withUniqueId(it) }
    return display
}