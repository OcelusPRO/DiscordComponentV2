package fr.ftnl.tools.messageBuilder.jda.ext.components.layout.label

import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Label
import fr.ftnl.tools.messageBuilder.jda.ext.components.toJdaLabelChildComponent
import net.dv8tion.jda.api.components.label.Label as JdaLabel

fun Label.toJda(): JdaLabel {
    return JdaLabel.of(label, description, this.component.toJdaLabelChildComponent())
}