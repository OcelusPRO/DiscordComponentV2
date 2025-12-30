package fr.ftnl.tools.messageBuilder.jda.ext.components.layout.section

import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Section
import fr.ftnl.tools.messageBuilder.jda.ext.components.toJdaComponents
import net.dv8tion.jda.api.components.section.SectionAccessoryComponent
import net.dv8tion.jda.api.components.section.SectionContentComponent
import net.dv8tion.jda.api.components.section.Section as JdaSection

fun Section.toJda(): JdaSection {
    val accessory = accessory.toJdaComponents() as SectionAccessoryComponent
    val components = components.map { it.toJdaComponents() as SectionContentComponent }
    return JdaSection.of(accessory, components)
}