@file:JsExport
@file:OptIn(ExperimentalJsExport::class)

import fr.ftnl.tools.messageBuilder.core.dto.components.content.UnfurledMediaItem
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.CheckboxGroupOption
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.RadioGroupOption
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.SelectDefaultValue
import fr.ftnl.tools.messageBuilder.core.dto.components.interactive.SelectOption
import fr.ftnl.tools.messageBuilder.core.dto.components.utils.DiscordEmoji
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@JsExport
object JsFactories {
    fun emoji() = DiscordEmoji()
    fun media(url: String) = UnfurledMediaItem(url)
    fun selectOption(label: String, value: String) = SelectOption(label, value)
    fun checkboxOption(value: String, label: String) = CheckboxGroupOption(value, label)
    fun radioOption(value: String, label: String) = RadioGroupOption(value, label)
    fun selectDefaultValue(id: String, type: String) = SelectDefaultValue(id, type)
}