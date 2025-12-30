package fr.ftnl.tools.messageBuilder.jda.ext.components.interactive.textInput

import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.TextInput
import net.dv8tion.jda.api.components.textinput.TextInput as JdaTextInput
import net.dv8tion.jda.api.components.textinput.TextInputStyle as JdaTextInputStyle


fun TextInput.toJda(): JdaTextInput {
    val builder =  JdaTextInput.create(customId, JdaTextInputStyle.fromKey(style))
    
    if (minLength != null) builder.minLength = minLength!!
    if (maxLength != null) builder.maxLength = maxLength!!
    
    builder.setRequired(required)
    if (value != null) builder.setValue(value)
    if (placeholder != null) builder.setPlaceholder(placeholder)
    
    return builder.build()
}