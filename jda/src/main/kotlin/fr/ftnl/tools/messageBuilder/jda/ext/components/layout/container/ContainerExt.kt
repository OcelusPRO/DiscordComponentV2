package fr.ftnl.tools.messageBuilder.jda.ext.components.layout.container

import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Container
import fr.ftnl.tools.messageBuilder.jda.ext.components.toJdaComponents
import net.dv8tion.jda.api.components.container.ContainerChildComponent as JdaContainerChild
import net.dv8tion.jda.api.components.container.Container as JdaContainer


fun Container.toJda(): JdaContainer {
    val builder = JdaContainer.of(this.components.mapNotNull { it.toJdaComponents() as? JdaContainerChild })
    
    if (accentColor != null) builder.withAccentColor(accentColor)
    builder.withSpoiler(spoiler)
    
    return builder
}