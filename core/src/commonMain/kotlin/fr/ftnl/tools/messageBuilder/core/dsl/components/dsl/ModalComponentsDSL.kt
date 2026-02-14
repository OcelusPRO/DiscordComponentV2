@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dsl.components.dsl

import fr.ftnl.tools.messageBuilder.core.dsl.components.BaseComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentBuilder
import fr.ftnl.tools.messageBuilder.core.dsl.components.ComponentDsl
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.content.TextDisplayBuilder
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
import fr.ftnl.tools.messageBuilder.core.dsl.components.builders.layout.LabelComponentBuilder
import fr.ftnl.tools.messageBuilder.core.interfaces.components.ModalCompatibleComponent
import fr.ftnl.tools.messageBuilder.core.interfaces.components.ModalCompatibleList

interface ModalComponentBuilder: ComponentBuilder {
    override fun build(): ModalCompatibleComponent
}

@ComponentDsl
class ModalComponentsDSL: BaseComponentBuilder() {
    
    // Contents
    fun textDisplay(block: TextDisplayBuilder.() -> Unit) {
        val builder = TextDisplayBuilder()
        builder.block()
        add(builder)
    }
    
    // interactive
    fun checkbox(customId: String, block: CheckboxBuilder.() -> Unit) {
        val builder = CheckboxBuilder(customId)
        builder.block()
        add(builder)
    }
    fun checkboxGroup(customId: String, block: CheckboxGroupBuilder.() -> Unit) {
        val builder = CheckboxGroupBuilder(customId)
        builder.block()
        add(builder)
    }
    fun radioGroup(customId: String, block: RadioGroupBuilder.() -> Unit) {
        val builder = RadioGroupBuilder(customId)
        builder.block()
        add(builder)
    }
    fun textInput(customId: String, style: Int, block: TextInputBuilder.() -> Unit) {
        val builder = TextInputBuilder(customId, style)
        builder.block()
        add(builder)
    }
    fun fileUpload(customId: String, block: FileUploadBuilder.() -> Unit) {
        val builder = FileUploadBuilder(customId)
        builder.block()
        add(builder)
    }
    fun stringSelect(customId: String, block: StringSelectBuilder.() -> Unit) {
        val builder = StringSelectBuilder(customId)
        builder.block()
        add(builder.build())
    }
    fun userSelect(customId: String, block: UserSelectBuilder.() -> Unit) {
        val builder = UserSelectBuilder(customId)
        builder.block()
        add(builder.build())
    }
    fun roleSelect(customId: String, block: RoleSelectBuilder.() -> Unit) {
        val builder = RoleSelectBuilder(customId)
        builder.block()
        add(builder.build())
    }
    fun mentionableSelect(customId: String, block: MentionableSelectBuilder.() -> Unit) {
        val builder = MentionableSelectBuilder(customId)
        builder.block()
        add(builder.build())
    }
    fun channelSelect(customId: String, block: ChannelSelectBuilder.() -> Unit) {
        val builder = ChannelSelectBuilder(customId)
        builder.block()
        add(builder.build())
    }
    
    // layout
    fun label(label: String, block: LabelComponentBuilder.() -> Unit) {
        val builder = LabelComponentBuilder(label)
        builder.block()
        add(builder)
    }
    
    fun build(): ModalCompatibleList {
        val filteredList =  components.elements.filterIsInstance<ModalCompatibleComponent>()
        return ModalCompatibleList(filteredList)
    }
}

/**
 * Entry point for the Message Component DSL.
 * Returns a list of constructed components.
 */
fun modalComponents(block: ModalComponentsDSL.() -> Unit): ModalCompatibleList {
    val builder = ModalComponentsDSL()
    builder.block()
    return builder.build()
}
