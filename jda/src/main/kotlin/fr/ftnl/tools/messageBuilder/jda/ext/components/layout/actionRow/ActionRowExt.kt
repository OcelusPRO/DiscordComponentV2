package fr.ftnl.tools.messageBuilder.jda.ext.components.layout.actionRow

import fr.ftnl.tools.messageBuilder.core.dto.components.layout.ActionRow
import fr.ftnl.tools.messageBuilder.jda.ext.components.toJdaComponents
import net.dv8tion.jda.api.components.actionrow.ActionRow as JdaActionRow
import net.dv8tion.jda.api.components.actionrow.ActionRowChildComponent as JdaActionRowChildComponent

fun ActionRow.toJda() = JdaActionRow.of(this.components.mapNotNull { it.toJdaComponents() as? JdaActionRowChildComponent })