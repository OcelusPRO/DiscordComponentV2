@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

package fr.ftnl.tools.messageBuilder.core.dsl.components

import fr.ftnl.tools.messageBuilder.core.interfaces.components.ComponentList
import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@DslMarker
annotation class ComponentDsl

@ComponentDsl
interface ComponentBuilder {
    fun build(): DiscordComponent
}

@ComponentDsl
open class BaseComponentBuilder {
    protected var components = ComponentList()

    protected fun <T : DiscordComponent> add(component: T) {
        components = ComponentList(components.elements + component)
    }
    
    protected fun <T: ComponentBuilder> add(builder: T) {
        add(builder.build())
    }
}

