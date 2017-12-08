package plugin.simon.com.util

import com.intellij.openapi.actionSystem.AnAction

import javax.swing.*

/**
 * Abstract class for plugin actions with [.name] and
 * [.description] properties.
 */
abstract class AbstractPluginAction(var name: String?, var description: String?, icon: Icon?) : AnAction(name, description, icon)