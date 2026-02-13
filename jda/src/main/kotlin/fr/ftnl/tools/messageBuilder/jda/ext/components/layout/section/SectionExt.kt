package fr.ftnl.tools.messageBuilder.jda.ext.components.layout.section

import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Section
import fr.ftnl.tools.messageBuilder.jda.ext.components.convertToJdaComponent
import fr.ftnl.tools.messageBuilder.jda.ext.components.toJdaComponents
import net.dv8tion.jda.api.components.section.SectionAccessoryComponent
import net.dv8tion.jda.api.components.section.SectionContentComponent
import net.dv8tion.jda.api.components.section.Section as JdaSection

fun Section.toJda(): JdaSection {
    requireNotNull(accessory) { "Section must have an accessory" }
    require(components.elements.isNotEmpty()) { "Section must have at least one component" }
    val accessory = accessory!!.convertToJdaComponent() as SectionAccessoryComponent
    val components = components.elements.map { it.convertToJdaComponent() as SectionContentComponent }
    return JdaSection.of(accessory, components)
}