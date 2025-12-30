package fr.ftnl.tools.messageBuilder.jda.ext.components.content.textDisplay

import fr.ftnl.tools.messageBuilder.core.dto.components.content.TextDisplay
import net.dv8tion.jda.api.components.textdisplay.TextDisplay as JdaTextDisplay


fun TextDisplay.toJda(): JdaTextDisplay = JdaTextDisplay.of(content)