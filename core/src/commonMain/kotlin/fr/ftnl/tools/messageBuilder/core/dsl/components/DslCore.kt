package fr.ftnl.tools.messageBuilder.core.dsl.components

import fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent

@DslMarker
annotation class ComponentDsl

@ComponentDsl
interface ComponentBuilder {
    fun build(): DiscordComponent
}

@ComponentDsl
open class BaseComponentBuilder {
    protected val components = mutableListOf<DiscordComponent>()

    protected fun <T : DiscordComponent> add(component: T) {
        components.add(component)
    }
    
    protected fun <T: ComponentBuilder> add(builder: T) {
        add(builder.build())
    }
}

