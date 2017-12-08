package simon.com.util

import com.intellij.openapi.actionSystem.AnAction
import javax.swing.Icon

abstract class AbstractPluginAction(var name: String?, var description: String?, icon: Icon?) : AnAction(name, description, icon) {
}