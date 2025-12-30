package fr.ftnl.tools.messageBuilder.jda.ext.components.interactive.entitySelect

import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.BaseEntitySelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.ChannelSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.MentionableSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.RoleSelect
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.SelectDefaultValue
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.UserSelect
import net.dv8tion.jda.api.components.selections.EntitySelectMenu

private fun BaseEntitySelect.toJdaBuilder(type: List<EntitySelectMenu.SelectTarget>): EntitySelectMenu.Builder {
    val menu = EntitySelectMenu.create(customId, type)
    
    menu.setPlaceholder(placeholder)
    menu.setMinValues(minValues)
    menu.setMaxValues(maxValues)
    menu.setDisabled(disabled)
    menu.setRequired(required)
    
    return menu
}
private fun BaseEntitySelect.toJdaBuilder(type: EntitySelectMenu.SelectTarget): EntitySelectMenu.Builder = this.toJdaBuilder(listOf(type))

fun UserSelect.toJda(): EntitySelectMenu {
    val target = EntitySelectMenu.SelectTarget.USER
    val menu = this.toJdaBuilder(target)
    if (defaultValues != null) menu.setDefaultValues(defaultValues!!.map { it.toJda() })
    return menu.build()
}
fun RoleSelect.toJda(): EntitySelectMenu {
    val target = EntitySelectMenu.SelectTarget.ROLE
    val menu = this.toJdaBuilder(target)
    if (defaultValues != null) menu.setDefaultValues(defaultValues!!.map { it.toJda() })
    return menu.build()
}
fun MentionableSelect.toJda(): EntitySelectMenu {
    val target = listOf(EntitySelectMenu.SelectTarget.USER, EntitySelectMenu.SelectTarget.ROLE)
    val menu = this.toJdaBuilder(target)
    if (defaultValues != null) menu.setDefaultValues(defaultValues!!.map { it.toJda() })
    return menu.build()
}
fun ChannelSelect.toJda(): EntitySelectMenu {
    val target = EntitySelectMenu.SelectTarget.CHANNEL
    val menu = this.toJdaBuilder(target)
    if (defaultValues != null) menu.setDefaultValues(defaultValues!!.map { it.toJda() })
    return menu.build()
}
fun SelectDefaultValue.toJda(): EntitySelectMenu.DefaultValue {
    return when (type) {
        "user" -> EntitySelectMenu.DefaultValue.user(id)
        "role" -> EntitySelectMenu.DefaultValue.role(id)
        "channel" -> EntitySelectMenu.DefaultValue.channel(id)
        else -> throw IllegalArgumentException("Invalid type: $type")
    }
}
