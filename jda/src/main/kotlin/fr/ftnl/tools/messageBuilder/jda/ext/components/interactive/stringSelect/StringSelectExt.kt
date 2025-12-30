package fr.ftnl.tools.messageBuilder.jda.ext.components.interactive.stringSelect

import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.SelectOption
import fr.ftnl.tools.messageBuilder.jda.ext.components.utils.emoji.toJda
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.StringSelect
import net.dv8tion.jda.api.components.selections.StringSelectMenu
import net.dv8tion.jda.api.components.selections.SelectOption as JdaSelectOption

fun StringSelect.toJda(): StringSelectMenu {
    
    val builder = StringSelectMenu.create(customId)
    
    if (placeholder != null) builder.setPlaceholder(placeholder)
    builder.setMinValues(minValues)
    builder.setMaxValues(maxValues)
    builder.setDisabled(disabled)
    builder.setRequired(required)
    builder.addOptions(options.map { it.toJda() })
    
    return builder.build()
}

fun SelectOption.toJda(): JdaSelectOption {
    val builder = JdaSelectOption.of(label, value)
    
    if (emoji != null) builder.withEmoji(emoji!!.toJda())
    if (description != null) builder.withDescription(description)
    builder.withDefault(default)
    
    return builder
}

