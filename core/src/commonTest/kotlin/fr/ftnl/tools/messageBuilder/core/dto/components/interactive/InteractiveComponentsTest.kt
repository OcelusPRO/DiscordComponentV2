package fr.ftnl.tools.messageBuilder.core.dto.components.interactive

import fr.ftnl.tools.messageBuilder.core.dto.components.utils.DiscordEmoji
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertTrue

class InteractiveComponentsTest {
    private val json = Json { encodeDefaults = true }

    @Test
    fun testButtonSerialization() {
        val button = Button(ButtonStyles.PRIMARY)
            .setCustomId("btn")
            .setLabel("OK")
        val encoded = json.encodeToString(button)

        assertTrue(encoded.contains("\"type\":2"))
        assertTrue(encoded.contains("\"custom_id\":\"btn\""))
        assertTrue(encoded.contains("\"label\":\"OK\""))
        assertTrue(encoded.contains("\"style\":1"))
    }

    @Test
    fun testStringSelectSerialization() {
        val option = SelectOption("Option", "value")
            .setEmoji(DiscordEmoji().setName("smile"))
        val select = StringSelect("select").addOption(option)
        val encoded = json.encodeToString(select)

        assertTrue(encoded.contains("\"type\":3"))
        assertTrue(encoded.contains("\"custom_id\":\"select\""))
        assertTrue(encoded.contains("\"options\":["))
        assertTrue(encoded.contains("\"label\":\"Option\""))
    }

    @Test
    fun testTextInputSerialization() {
        val input = TextInput("input", 1)
            .setPlaceholder("Type here")
            .setRequired(false)
        val encoded = json.encodeToString(input)

        assertTrue(encoded.contains("\"type\":4"))
        assertTrue(encoded.contains("\"custom_id\":\"input\""))
        assertTrue(encoded.contains("\"placeholder\":\"Type here\""))
        assertTrue(encoded.contains("\"required\":false"))
    }

    @Test
    fun testEntitySelectsSerialization() {
        val userSelect = UserSelect("user").apply { placeholder = "User" }
        val roleSelect = RoleSelect("role").apply { placeholder = "Role" }
        val mentionableSelect = MentionableSelect("mention").apply { placeholder = "Mentionable" }
        val channelSelect = ChannelSelect("channel").addChannelType(0)

        val userJson = json.encodeToString(userSelect)
        val roleJson = json.encodeToString(roleSelect)
        val mentionJson = json.encodeToString(mentionableSelect)
        val channelJson = json.encodeToString(channelSelect)

        assertTrue(userJson.contains("\"type\":5"))
        assertTrue(roleJson.contains("\"type\":6"))
        assertTrue(mentionJson.contains("\"type\":7"))
        assertTrue(channelJson.contains("\"type\":8"))
        assertTrue(channelJson.contains("\"channel_types\":[0]"))
    }

    @Test
    fun testFileUploadSerialization() {
        val upload = FileUpload("upload").setRequired(false)
        val encoded = json.encodeToString(upload)

        assertTrue(encoded.contains("\"type\":19"))
        assertTrue(encoded.contains("\"custom_id\":\"upload\""))
        assertTrue(encoded.contains("\"required\":false"))
    }

    @Test
    fun testCheckboxSerialization() {
        val checkbox = Checkbox("check").setDefault(true)
        val encoded = json.encodeToString(checkbox)

        assertTrue(encoded.contains("\"type\":23"))
        assertTrue(encoded.contains("\"custom_id\":\"check\""))
        assertTrue(encoded.contains("\"default\":true"))
    }

    @Test
    fun testCheckboxGroupSerialization() {
        val option = CheckboxGroupOption(value = "v", label = "L")
        val group = CheckboxGroup("group").addOptions(option)
        val encoded = json.encodeToString(group)

        assertTrue(encoded.contains("\"type\":22"))
        assertTrue(encoded.contains("\"custom_id\":\"group\""))
        assertTrue(encoded.contains("\"options\":["))
        assertTrue(encoded.contains("\"label\":\"L\""))
    }

    @Test
    fun testRadioGroupSerialization() {
        val option = RadioGroupOption(value = "v", label = "L")
        val group = RadioGroup("group").addOptions(option, RadioGroupOption("v2", "L2"))
        val encoded = json.encodeToString(group)

        assertTrue(encoded.contains("\"type\":21"))
        assertTrue(encoded.contains("\"custom_id\":\"group\""))
        assertTrue(encoded.contains("\"options\":["))
        assertTrue(encoded.contains("\"label\":\"L\""))
    }

    @Test
    fun testSelectDefaultValueSerialization() {
        val value = SelectDefaultValue("123", "user")
        val encoded = json.encodeToString(value)

        assertTrue(encoded.contains("\"id\":\"123\""))
        assertTrue(encoded.contains("\"type\":\"user\""))
    }
}
