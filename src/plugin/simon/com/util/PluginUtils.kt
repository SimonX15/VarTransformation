package plugin.simon.com.util

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.actionSystem.DataKeys
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.command.CommandProcessor


object PluginUtils {
    /**
     * Execute an action inside a `CommandProcessor.executeCommand()` and
     * `ApplicationManager.runWriteAction()` command.
     *
     * @param action  Action object triggered by event
     * @param event   Event object triggered by user
     * @param command Runnable object to execute action
     */
    fun executeWriteAction(action: AbstractPluginAction, event: AnActionEvent, command: Runnable) {
        val project = event.getData(DataKeys.PROJECT)
        CommandProcessor.getInstance().executeCommand(
                project, {
            try {
                ApplicationManager.getApplication().runWriteAction(command)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        },
                action.name,
                action.description
        )
    }
}