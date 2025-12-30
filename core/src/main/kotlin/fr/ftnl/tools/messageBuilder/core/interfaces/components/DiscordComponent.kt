package fr.ftnl.tools.messageBuilder.core.interfaces.components

import fr.ftnl.tools.messageBuilder.core.serializers.components.ComponentSerializer
import kotlinx.serialization.Serializable

/**
 * Interface de base pour tous les composants.
 */
@Serializable(with = _root_ide_package_.fr.ftnl.tools.messageBuilder.core.serializers.components.ComponentSerializer::class)
interface DiscordComponent {
    val type: Int
    val id: Int?
}

interface ActionRowChildComponent   : fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
interface ContainerChildComponent   : fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
interface SectionChildComponent     : fr.ftnl.tools.messageBuilder.core.interfaces.components.DiscordComponent
