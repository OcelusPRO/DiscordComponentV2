package fr.ftnl.tools.messageBuilder.jda.ext.components.layout.separator

import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Separator
import net.dv8tion.jda.api.components.separator.Separator as JdaSeparator

fun Separator.toJda(): JdaSeparator = JdaSeparator.create(divider, JdaSeparator.Spacing.fromKey(spacing))
