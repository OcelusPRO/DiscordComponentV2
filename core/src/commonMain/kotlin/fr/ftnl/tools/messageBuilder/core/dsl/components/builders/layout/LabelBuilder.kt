package fr.ftnl.tools.messageBuilder.core.dsl.components.builders.layout

import fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.ChannelSelectBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.CheckboxBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.CheckboxGroupBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.FileUploadBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.MentionableSelectBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.RadioGroupBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.RoleSelectBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.StringSelectBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.TextInputBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.interactive.UserSelectBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.MessageComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.dsl.ModalComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.ActionRow
import fr.ftnl.tools.messageBuilder.core.dto.components.layout.Label
import fr.ftnl.tools.messageBuilder.core.interfaces.components.LabelChildComponent


@ComponentDsl
class LabelComponentBuilder(val label: String): ModalComponentBuilder {
    private var component: LabelChildComponent? = null
    
    fun stringSelect(customId: String, block: StringSelectBuilder.() -> Unit) {
        val builder = StringSelectBuilder(customId)
        builder.block()
        component = builder.build()
    }
    
    fun userSelect(customId: String, block: UserSelectBuilder.() -> Unit) {
        val builder = UserSelectBuilder(customId)
        builder.block()
        component = builder.build()
    }
    
    fun roleSelect(customId: String, block: RoleSelectBuilder.() -> Unit) {
        val builder = RoleSelectBuilder(customId)
        builder.block()
        component = builder.build()
    }
    
    fun mentionableSelect(customId: String, block: MentionableSelectBuilder.() -> Unit) {
        val builder = MentionableSelectBuilder(customId)
        builder.block()
        component = builder.build()
    }
    
    fun channelSelect(customId: String, block: ChannelSelectBuilder.() -> Unit) {
        val builder = ChannelSelectBuilder(customId)
        builder.block()
        component = builder.build()
    }
    
    fun textInput(customId: String, style: Int, block: TextInputBuilder.() -> Unit) {
        val builder = TextInputBuilder(customId, style)
        builder.block()
        component = builder.build()
    }
    
    fun checkbox(customId: String, block: CheckboxBuilder.() -> Unit) {
        val builder = CheckboxBuilder(customId)
        builder.block()
        component = builder.build()
    }
    
    fun checkboxGroup(customId: String, block: CheckboxGroupBuilder.() -> Unit) {
        val builder = CheckboxGroupBuilder(customId)
        builder.block()
        component = builder.build()
    }
    
    fun radioGroup(customId: String, block: RadioGroupBuilder.() -> Unit) {
        val builder = RadioGroupBuilder(customId)
        builder.block()
        component = builder.build()
    }
    
    fun fileUpload(customId: String, block: FileUploadBuilder.() -> Unit) {
        val builder = FileUploadBuilder(customId)
        builder.block()
        component = builder.build()
    }
    
    
    override fun build(): Label{
        require(component != null) { "Label component must be defined" }
        return Label(label, component!!)
    }
}